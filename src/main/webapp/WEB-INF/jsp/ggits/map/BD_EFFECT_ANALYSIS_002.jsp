<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm" class="result_change">
    		<input type="hidden" name="menuCode" value="BD_EFFECT_ANALYSIS_002">
    		<input type="hidden" name="pageType" value="<c:out value='${type}'/>">
	        <%--<div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear">
	                <option value="searchAllYear">전체</option>
	           	<c:forEach var="yearsList" items="${yearsList}">
	                <option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
	           	</c:forEach>
	            </select>
	        </div>--%>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">개선 전<span class="required-alert">*</span></h5>
		            <div class="calendar">
			            <input type="text" class="date_picker input_same mr8 input_picker start_date_check yesterday" name="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
			            <select class="selectBox selectTime effect_start_time start_time_check hour" name="startTime" id="startTime"></select>	            
					</div>
	            </div>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">개선 후<span class="required-alert">*</span></h5>
		            <div class="calendar">
			            <div class="end_calendar_box">
							<div class="date_picker_block"></div>								            
				            <input type="text" class="end_date_picker input_same mr8 input_picker end_date_check today" name="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
				            <select class="selectBox selectTime effect_end_time end_time_check hour" name="endTime" id="endTime"></select>
			            </div>		            
					</div>
	            </div>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius" name="searchLocation">
	                <option value="searchAllLocation">전체 지역</option>
	                <c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">항목</h5>
	            <%--<label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">혼잡강도</label>--%>
	            <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" id="traffic" name="searchResultType" value="vhclTrfvlm" checked>교통량</label>
	            <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchResultType" value="speedAvg" id="speed">평균속도</label>
	            <!-- <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">돌발/사고</label> -->
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
	dateTiemInit(false);
	
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
	
	function analysisResultEvent(){
    	
		//도로 선택이 없어짐으로 인한 주석
/*     	var roadRank = $("input[name='roadRank']");
		var roadRankCheckedVal = roadRank.is(":checked");
		if(!roadRankCheckedVal){
			new ModalBuilder().init().alertBoby("도로를 1개 이상 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
			return;
		}
		var roadRankVal = "";
		roadRank.each(function(idx,item){
			if($(item).parent().hasClass("is-darkgreen-btn")){
				if(roadRankVal == ""){
					roadRankVal = $(item).val();
				}else{
					roadRankVal += ","+$(item).val();
				}
			}
		})
		$("#searchRoadRank").val(roadRankVal); */
		
		/*var crossroadsType = $("input[name='searchCrossroadsType']:checked").val();
		if(crossroadsType == 'map'){
			var mapNodeIdList = __Map.querySourceFeatures("GITS_NODE", {sourceLayer : "ggits_node", filter: ["==", "NODE_TYPE", "101"]}).map(item => { return item.id});
			$("#searchCrossroadsNodeId").val(mapNodeIdList);
		}*/
		
		var searchResultType = $("input[name='type']:checked").val();
		$("#searchResultType").val(searchResultType);
		bigdataSearchForm = $("#searchForm").serializeObject();
		
 		let startDate = $('.start_date_check').val();
        let endDate = $('.end_date_check').val();
        var startTime = $('.start_time_check').val();
        var endTime = $('.end_time_check').val();
       	
		var stForm = $('[name!=endDate]', "#searchForm").serialize();
		var edForm = $('[name!=startDate]', "#searchForm").serialize();
		
		
        window.dualMap.bigdata.getTrafficActiveEffectAnalysis(edForm);
        window.map.bigdata.getTrafficActiveEffectAnalysis(stForm);
        gitsApp.drawMergeButton(function(){
			window.afterData = window.dualMap.getSavedData();
			window.beforeData = window.map.getSavedData();
			const afterSearchOption =  Object.fromEntries(new URLSearchParams(window.afterData.searchOption));
			const beforeSearchOption =  Object.fromEntries(new URLSearchParams(window.beforeData.searchOption));
			
			const mergeSearchOption = "menuCode=BD_EFFECT_ANALYSIS_002&type="+beforeSearchOption.searchResultType+"&startDate="+beforeSearchOption.startDate+"&endDate="+afterSearchOption.endDate+"&searchCrossroadsType=all&page=1";
			window.map.bigdata.getTrafficEffectAnalysisMerge(mergeSearchOption);

			gitsApp.removeDualMap();
        });
        
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
 	   
		settingBigdataSearchParam("BD_EFFECT_ANALYSIS_002");
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
