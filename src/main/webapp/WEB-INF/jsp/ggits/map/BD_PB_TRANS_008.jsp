<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" method="get" class="result_change">
    		<input type="hidden" id="mapPage" name="page" value="1"/>
    		<input type="hidden" id="routeId" name="routeId"/>
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
       		<div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear" id="searchYear">
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="<c:out value="${yearsList.year}"/>"><c:out value="${yearsList.year}"/>년</option>
	           		</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간<span class="required-alert">*</span></h5>
	                <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekday" checked>평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" name="searchPeriod" class="none" value="weekend">주말</label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" name="searchPeriod" class="none" value="directDate">직접입력</label>
	            </div>
	            <div class="calendar direct_time none" id="directDate">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" id="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		            ~
		            <div class="end_calendar_box">
						<div class="date_picker_block"></div>								            
			            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" id="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		            </div>	            
				</div>
	        </div>
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">시군구</h5>
				<select class="selectBox radius change-detect" <%--onchange="getDongListBySggCd(this.value, '#sggDongSelect')"--%> name="districtGnm">
					<option value="">시군구 전체</option>
					<c:forEach var="sggCd" items="${sggCdList}" varStatus="status">
						<option value="<c:out value='${sggCd.cdNm}'/>"><c:out value='${sggCd.cdNm}'/></option>
					</c:forEach>
				</select>
			</div>
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">버스종류</h5>
				<select class="selectBox radius change-detect" id="routeTp" name="routeTp">
					<option value="">전체</option>
					<option value="RTC000">시외버스</option>
					<option value="RTC001">일반버스</option>
					<option value="RTC002">마을버스</option>
					<option value="RTC003">광역버스</option>
					<option value="RTC004">공항버스</option>
				</select>
			</div>
			<div class="tab_item_box flex-column gap8">
				<div class="flex-center">
					<h5 class="tab_item_title">노선</h5>
					<input type="text" placeholder="노선을 입력해주세요." name="routeNm" class="input_same search_box radius">
					<button type="button" class="is-darkgreen-btn ml8" onclick="fnSearchList();">검색</button>
				</div>
			</div>
        </form>
		<div class="tab_item_box flex-center pt8 none" id="tableHeader">
			<h5 class="tab_item_title"></h5>
			<div class="gis_table_scroll height-360" style="width:560px;">
				<div class="table_search_number tableTitle">
					<span id="totalCnt"><c:out value='${paging.totalCount eq null || paging.totalCount eq "" ? "0" : ""}'/></span>개의 검색결과를 찾았습니다.
				</div>
				<table id="modalTable" class="result_change">
					<colgroup>
						<col style="">
						<col style="">
						<col style="">
						<col style="">
						<col style="">
						<col style="">
						<col style="">
					</colgroup>
					<thead>
					<tr>
						<th scope="col">회사명</th>
						<th scope="col">버스유형</th>
						<th scope="col">버스번호</th>
						<th scope="col">지역</th>
						<th scope="col">이용자수</th>
						<th scope="col">수익</th>
						<th scope="col">노선보기</th>
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
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	settingBigdataSearchParam("BD_PB_TRANS_008");
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))

	function fnSearchList(){
		$("#modalTable>tbody > tr").remove();
		$("#modalPaging > .dashboard-pg-wrap").remove();
		var page = $("#mapPage").val();

		$.ajax({
			type : "get",
			data : $("#searchForm").serialize(),
			url : "${pageContext.request.contextPath}/map/bigdata/bus/trans/BD_PB_TRANS_008/data.ajax",
			beforeSend : function(){
				startLoading();
			},
			success : function(result){
				let html = '';
				let title = '';
				let startDate = $("#startDate").val();
				let endDate = $("#endDate").val();
				
				let searchOption = result.data.searchOption;
				let PeriodText = '';
				
				if(typeof(searchOption) != 'undefined'){
					Year = searchOption.searchYear;
					switch(searchOption.searchPeriod){
					case'weekday':
						PeriodText = searchOption.searchYear+'년 평일';
						break;
					case'weekend':
						PeriodText = searchOption.searchYear+'년 주말';
						break;	
					case'directDate':
						PeriodText = startDate+"일 ~ "+endDate+"일";
						break;	
					default:
						break;
					}
				}
				
				if(result.data.resultList.length == 0){
					html += '<tr>' +
							'<td colspan="5">노선 정보를 찾을 수 없습니다.</td>' +
							'</tr>';
				}else{
					$(result.data.resultList).each(function(index, item){
						html += '<tr onclick="viewDetailUseCalc(this,'+item.routeId+','+item.busUseTotAmt+', '+item.card1+', '+item.card2+', '+item.card3+', '+item.etc+', '+item.busUserCnt+', '+item.user1+', '+item.user2+', '+item.user3+', '+item.userEtc+')" data-conm="'+item.coNm+'" data-routetp="'+(GITS_ENV.ROUTE_TP[item.routeTy] ? GITS_ENV.ROUTE_TP[item.routeTy] : item.routeTy)+'" data-routeno="'+item.busRouteNo+'" data-period="'+PeriodText+'">'+
								'<td>' + '<label for="listItem'+index+'">' + item.coNm + '</label>' + '</td>' +
								'<td>' + '<label for="listItem'+index+'">' + item.routeTy + '</label>' + '</td>' +
								'<td>' + '<label for="listItem'+index+'">' + item.busRouteNo + '</label>' + '</td>' +
								'<td>' + '<label for="listItem'+index+'">' + (item.districtGnm ? item.districtGnm : item.districtSnm) + '</label>' + '</td>' +
								'<td>' + '<label for="listItem'+index+'">' + numberComma(item.busUserCnt) + ' 명</label>' + '</td>' +
								'<td>' + '<label for="listItem'+index+'">' + numberComma(item.busUseTotAmt) + ' 원</label>' + '</td>' +
								'<td>' + '<label for="listItem'+index+'">노선보기</label>' + '</td>' +
								'</tr>';
					})
				}
				$("#tableHeader").removeClass("none");
				$("#modalTable>tbody").append(html);
				let paging = result.data.paging;
				$("#totalCnt").text(numberComma(paging.totalCount));
				if(paging != null && paging != ''){
					$("#modalPaging").append(getGisPagingHtml(paging, page));
				};
			},
			complete : function(){
				endLoading();
			}
		});
	}
	function viewDetailUseCalc(_this,routeId, busUseTotAmt, card1, card2, card3, etc, busUserCnt, user1, user2, user3, userEtc){
		card1 = typeof card1 === "undefined" || card1 == null ? 0 : card1;
		card2 = typeof card2 === "undefined" || card2 == null ? 0 : card2;
		card3 = typeof card3 === "undefined" || card3 == null ? 0 : card3;
		etc = typeof etc === "undefined" || etc == null ? 0 : etc;
		user1 = typeof user1 === "undefined" || user1 == null ? 0 : user1;
		user2 = typeof user2 === "undefined" || user2 == null ? 0 : user2;
		user3 = typeof user3 === "undefined" || user3 == null ? 0 : user3;
		userEtc = typeof userEtc === "undefined" || userEtc == null ? 0 : userEtc;
		const conm = $(_this).data("conm");
		const routetp = $(_this).data("routetp");
		const routeno = $(_this).data("routeno");
		const searchPeriod = $(_this).data("period");

		$("#routeId").val(routeId);
		window.map.bigdata.getPublicTransferUseCalc($("#searchForm").serialize());

		let busOptimizationItem = $(GITS_ENV.UI.BD_PB_TRANS_008(__contextPath__, conm,routetp,routeno,routeId,busUserCnt,busUseTotAmt,card1,card2,card3,etc, user1, user2, user3, userEtc, searchPeriod));

		$('#map-container').append(busOptimizationItem);
		$('.optimization_close').on('click', function(){
			$(this).closest('.optimization_container').remove();
		})
	}
	$(document).ready(function() {
		if(!isNull($("#startDate").val())){
			$('.date_picker_block').remove();
		}
	})
	
</script>
