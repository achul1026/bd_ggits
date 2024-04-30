<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
	<div class="original_box clearfix">
		<form id="searchForm" class="result_change">
			<input type="hidden" name="menuCode" value="BD_EFFECT_ANALYSIS_003">
			<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
			<input type="hidden" name="linkId" id="linkId">
			<input type="hidden" name="endDate" id="endDate">
			<input type="hidden" name="before_startDate" id="before_startDate">
			<input type="hidden" name="before_endDate" id="before_endDate">
			<input type="hidden" id="mapPage" name="page" value="1"/>
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">기준일<span class="required-alert">*</span></h5>
					<div class="calendar">
						<input type="text" class="date_picker input_same mr8 input_picker start_date_check" name="startDate" id="startDate" onchange="changeEndDateByMonthlySection()" placeholder="날짜를 선택해주세요." autocomplete="off">
					</div>
				</div>
			</div>
			<div class="tab_item_box">
				<div class="flex-center">
					<h5 class="tab_item_title">조회구간<span class="required-alert">*</span></h5>
					<div class="calendar">
						<select name="monthly_section" id="monthly_section" class="selectBox radius" onchange="changeEndDateByMonthlySection()">
							<option value="10">10일 전후</option>
							<option value="30">30일 전후</option>
							<option value="90">90일 전후</option>
							<option value="120">120일 전후</option>
						</select>
					</div>
				</div>
			</div>
			<%--<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">지역별</h5>
				<select class="selectBox radius" name="searchLocation">
					<option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
						<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
				</select>
			</div>--%>
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">검색</h5>
				<input type="text" placeholder="도로명/링크아이디" name="searchContent" id="searchContent" class="input_same search_box radius">
				<button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList();">검색</button>
			</div>
<%--			<div class="tab_item_box flex-center">--%>
<%--				<h5 class="tab_item_title">항목</h5>--%>
<%--				<label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" id="traffic" name="searchResultType" value="vhclTrfvlm" checked>교통량</label>--%>
<%--				<label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchResultType" value="speedAvg" id="speed">평균속도</label>--%>
<%--			</div>--%>
			<input type="hidden" name="searchResultType" value="speedAvg">
			<div class="tab_item_box flex-center pt8 none" id="tableHeader">
				<h5 class="tab_item_title"></h5>
				<div class="gis_table_scroll" style="width:600px;">
					<div class="table_search_number tableTitle">
						<span id="totalCnt"><c:out value='${totalCnt}'/></span>개의 검색결과를 찾았습니다.
					</div>
					<table class="all_center" id="modalTable">
						<colgroup>
							<col style="width:10%">
							<col>
							<col>
						</colgroup>
						<thead>
						<tr>
							<th scope="col">선택</th>
							<th scope="col">도로명</th>
							<th scope="col">링크ID</th>
						</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					<div id="modalPaging">
						<%@ include file="/WEB-INF/jsp/ggits/utils/gis_paging.jsp" %>
					</div>
				</div>
			</div>
		</form>
		<div class="bottom_btn">
			<button type="button" class="is-darkgreen-btn radius original_result_btn" id="resulutButton" onclick="analysisResultEvent();">결과보기</button>
		</div>
	</div>
</div>

<script>
	gisCheckInit();
	datePickerInit();

	$(document).ready(function(){
		$('.date_picker_block').remove();
	})

	$("#searchAllRoadRankBtn").on("click",function(){
		var allChkVal = $(this).hasClass("is-darkgreen-btn");
		var roadRank = $("input[name='roadRank']");
		var roadRankVal = "";
		if(allChkVal){
			roadRank.each(function(idx,item){
				if(idx == 0){
					roadRankVal = $(item).val();
				}else{
					roadRankVal += ","+$(item).val();
				}
			})
		}
		$("#searchRoadRank").val(roadRankVal);
	});

	function fnSearchList(){
		$("#modalTable>tbody > tr").remove();
		$("#modalPaging > .dashboard-pg-wrap").remove();
		var page = $("#mapPage").val();
		$("#linkId").val("");
		$.ajax({
			type : "get",
			data : $("#searchForm").serialize(),
			timeout : 60*1000*10,
			url : "${pageContext.request.contextPath}/map/bigdata/effect/analysis/BD_EFFECT_ANALYSIS_003/data.ajax",
			beforeSend : function(){
				startLoading();
			},
			success : function(result){
				if(result.code == 200){
					var html = '';
					var title = '';
					var startDate = '';
					var endDate = '';
					if(result.data.resultList.length == 0){
						html += '<tr>' +
								'<td colspan="3">도로 정보를 찾을 수 없습니다.</td>' +
								'</tr>';
					}else{
						$(result.data.resultList).each(function(index, item){
							html += '<tr onclick="setLinkId(\'' + item.linkId + '\')">' +
									'<td>' + '<input type="radio" id="listItem'+index+'" name="listItem" class="bigdata_input_radio" value="' + item.linkId + '">' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.roadName + '</label>' + '</td>' +
									'<td>' + '<label for="listItem'+index+'">' + item.linkId + '</label>' + '</td>' +
									'</tr>';
						})
					}
					$("#tableHeader").removeClass("none");
					$("#modalTable>tbody").append(html);
					var paging = result.data.paging;
					if(paging != null && paging != ''){
						title += '<span id="totalCnt2">'+paging.totalCount+'</span>개의 검색결과를 찾았습니다.';
						$("#modalPaging").append(getGisPagingHtml(paging, page));
						var dataTotalCnt2 = '+paging.totalCount+';
						$("#totalCnt2").text(numberComma(dataTotalCnt2))
					};
					$(".tableTitle").html(title);
				}else{
					endLoading();
					new ModalBuilder().init().alertBoby("도로정보 조회에 실패 하였습니다.").footer(4,'확인',function(button, modal){modal.close();}).open();
					modalAlertWrap();
				}
			},
			complete : function(){
				endLoading();
			}
		});
	}
	function setLinkId(linkId) {
		$("#linkId").val(linkId);
	}

	function changeEndDateByMonthlySection(){
		let stdt = $("#startDate").val();
		if(!stdt) return;
		let du = $("#monthly_section").val();

		let stDate = new Date(stdt);

		let beforeStDate = stDate.addDays(-1*parseInt(du));
		let beforeEndDate = stDate;
		let afterEndDate = stDate.addDays(parseInt(du));
		let bf_st_yyyy = beforeStDate.getFullYear();
		let bf_st_mm = beforeStDate.getMonth() + 1; // getMonth() is zero-based
		let bf_st_dd = beforeStDate.getDate() < 10 ? "0"+beforeStDate.getDate() : beforeStDate.getDate();
		let bf_ed_yyyy = beforeEndDate.getFullYear();
		let bf_ed_mm = beforeEndDate.getMonth() + 1; // getMonth() is zero-based
		let bf_ed_dd = beforeEndDate.getDate() < 10 ? "0"+beforeEndDate.getDate() : beforeEndDate.getDate();
		let af_ed_yyyy = afterEndDate.getFullYear();
		let af_ed_mm = afterEndDate.getMonth() + 1; // getMonth() is zero-based
		let af_ed_dd = afterEndDate.getDate() < 10 ? "0"+afterEndDate.getDate() : afterEndDate.getDate();

		$("#before_startDate").val(bf_st_yyyy+'-'+(bf_st_mm < 10 ? "0"+bf_st_mm : bf_st_mm)+'-'+bf_st_dd);
		$("#before_endDate").val(bf_ed_yyyy+'-'+(bf_ed_mm < 10 ? "0"+bf_ed_mm : bf_ed_mm)+'-'+bf_ed_dd);
		$("#endDate").val(af_ed_yyyy+'-'+(af_ed_mm < 10 ? "0"+af_ed_mm : af_ed_mm)+'-'+af_ed_dd);
	}
	function analysisResultEvent(){

		if($("#startDate").val() == '') {
			alert("기준일을 선택해주세요.");
			return;
		}
		var searchResultType = $("input[name='type']:checked").val();
		$("#searchResultType").val(searchResultType);
		bigdataSearchForm = $("#searchForm").serializeObject();

		let startDate = $('.start_date_check').val();
		let endDate = $('.end_date_check').val();
		var startTime = $('.start_time_check').val();
		var endTime = $('.end_time_check').val();

		var edForm = $(':not([name^=before_])', "#searchForm").serialize(); // 후
		var stForm = $('[name!=startDate][name!=endDate]', "#searchForm").serialize(); // 전


		stForm = stForm+"&startDate="+$("#before_startDate").val()+"&endDate="+$("#before_endDate").val();
		window.dualMap.bigdata.getTrafficActiveEffectAnalysisSvcLink(edForm); // 후
		window.map.bigdata.getTrafficActiveEffectAnalysisSvcLink(stForm); // 전
		/* gitsApp.drawMergeButton(function(){
             window.afterData = window.dualMap.getSavedData();
             window.beforeData = window.map.getSavedData();
             const afterSearchOption =  Object.fromEntries(new URLSearchParams(window.afterData.searchOption));
             const beforeSearchOption =  Object.fromEntries(new URLSearchParams(window.beforeData.searchOption));

             const mergeSearchOption = "menuCode=BD_EFFECT_ANALYSIS_002&type="+beforeSearchOption.searchResultType+"&startDate="+beforeSearchOption.startDate+"&endDate="+afterSearchOption.endDate+"&startTime="+beforeSearchOption.startTime+"&endTime="+afterSearchOption.endTime+"&searchCrossroadsType=all&page=1";
             window.map.bigdata.getTrafficEffectAnalysisMerge(mergeSearchOption);

             gitsApp.removeDualMap();
         });*/

		$(".start_date").text(startDate);
		$(".end_date").text(endDate);

//        	let oneHourStartLeft = Number(startTime)+1 < 10 ? "0"+(Number(startTime)+1):Number(startTime)+1;
//        	let oneHourAfterLeft = Number(endTime)+1 < 10 ? "0"+(Number(endTime)+1):Number(endTime)+1;

		//숫자 정규식
		var numRegex = /^[0-9]+$/;
		if(numRegex.test(startTime) && numRegex.test(endTime)){
			let oneHourStartLeft = Number(startTime)+1;
			let oneHourAfterLeft = Number(endTime)+1;

			$(".start_time").text(Number(startTime) + "시 ~ " + oneHourStartLeft+"시");
			$(".end_time").text(Number(endTime) + "시 ~ " + oneHourAfterLeft+"시");
		}

		var trafficRemrks= $(`
	        <div class="remarks_container remarksClose">
		        <div class="remarks_title_box">
		            <h6 class="remarks_title">범례 - 교통량</h6>
		        </div>
	        	<div class="remarks_wrap">
	            	<div>
		                <ul class="check_line_container">
		                    <li class="check_line_box remarks-red">14,001 - 100,000</li>
		                    <li class="check_line_box remarks-orange">8,001 - 14,000</li>
		                    <li class="check_line_box remarks-light-orange">5,001 - 8,000</li>
		                    <li class="check_line_box remarks-light-green">2,001 - 5,000</li>
		                    <li class="check_line_box remarks-green">0 - 2,000</li>
		                </ul>
	            	</div>
		            <div class="unit">단위 : (일/대)</div>
	        	</div>
	    	</div>`)

		var speedRemrks= $(`
	        <div class="remarks_container speed remarksClose">
		        <div class="remarks_title_box">
		            <h6 class="remarks_title">범례 - 평균속도</h6>
		        </div>
	        	<div class="remarks_wrap">
	            	<div>
		                <ul class="check_line_container">
		                    <li class="check_line_box remarks-red">정체</li>
		                    <li class="check_line_box remarks-orange">지체(서행)</li>
		                    <li class="check_line_box remarks-green">원활</li>
		                </ul>
	            	</div>
		            <div class="unit">단위 : 소통등급</div>
	        	</div>
	    	</div>`)
		$('#map-container').find(".remarks_container").remove();
		const trafficCheck = $('#traffic').is(':checked');
		if(trafficCheck == true){
			$('#map-container').append(trafficRemrks);
		} else {
			$('#map-container').append(speedRemrks);
		}
		legendToggle();

		resultChange();
	}

	$("#searchCrossBtn").on('click', function(){
		$("#mapPage").val("1");
		fnSearchCrossListForBigdata();
	});

	$("#selectList").on("click",function(){
		var checkedVal = $(this).is(":checked");
		$("#crossroadsListDiv").removeClass("none");
		fnSearchCrossListForBigdata();
	})

	$("#selectMap").on("click",function(){
		$("#crossroadsListDiv").addClass("none");
	})

	if(window.afterData && window.beforeData){
		const afterSearchOption =  Object.fromEntries(new URLSearchParams(window.afterData.searchOption));
		const beforeSearchOption =  Object.fromEntries(new URLSearchParams(window.beforeData.searchOption));
		$('.yesterday').datepicker('setDate', beforeSearchOption.startDate);
		$('.today').datepicker('setDate', afterSearchOption.endDate);
	}

	$(document).ready(function(){
		var toDayTime = new Date();
		var year = toDayTime.getFullYear();
		var month = ('0' + (toDayTime.getMonth() + 1)).slice(-2);
		var day = ('0' + toDayTime.getDate()).slice(-2);
		var dateString = year + '-' + month  + '-' + day;

		//실시간 select time
		var optionHtml = "";
		let dayTime = new Date()
		$(".hour").append(optionHtml);
		var hour = dayTime.getHours();
		$(".hour").val(hour -1);

		//실시간보다 높은 option disabled
		let endDay = $('.end_date_picker').val();
		if(dateString == endDay){
			$.each($('#endTime option'), function(){
				var idx = $(this).index();
				if (hour <= idx){
					$(this).attr('disabled','disabled');
				} else {
					$(this).removeAttr('disabled').prop('selected', true);
				}
			})
			$.each($('#startTime option'), function(){
				var idx = $(this).index();
				if (hour <= idx){
					$(this).attr('disabled','disabled');
				} else {
					$(this).removeAttr('disabled').prop('selected', true);
				}
			})
		}

		//날짜 같은날 지정못하게
		$('.date_picker').datepicker();
		$('.date_picker').datepicker("option", "onClose", function (selectedDate) {
			var nextDay = new Date(selectedDate);
			nextDay.setDate(nextDay.getDate() + 1);
			$(".end_date_picker").datepicker("option", "minDate", nextDay);
		});

		$('.end_date_picker').datepicker();
		$('.end_date_picker').datepicker("option", "minDate", $(".date_picker").val());
		$('.end_date_picker').datepicker("option", "onClose", function (selectedDate) {
			var prevDay = new Date(selectedDate);
			prevDay.setDate(prevDay.getDate() - 1);
			$(".date_picker").datepicker("option", "maxDate", prevDay);
		});

		$('.date_picker').datepicker('option', 'maxDate', '-1D');
	})

	$('.date_picker').on('change', function(){
		var toDayTime = new Date();
		var year = toDayTime.getFullYear();
		var month = ('0' + (toDayTime.getMonth() + 1)).slice(-2);
		var day = ('0' + toDayTime.getDate()).slice(-2);
		var dateString = year + '-' + month  + '-' + day;

		var getHours = toDayTime.getHours();
		var afterTime = $("#endTime").val();
		var beforeTime = $("#startTime").val();
		var beforeDate = $(".date_picker").val();
		var afterDate = $(".end_date_picker").val();

		if(afterDate == dateString){
			$.each($('#startTime option'), function(){
				var idx = $(this).index();
				if (getHours <= idx){
					$(this).attr('disabled','disabled');
				} else {
					$(this).removeAttr('disabled').prop('selected', true);
				}
			})
		}
	})

</script>
