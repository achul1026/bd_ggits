<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
        <form id="searchForm" class="result_change">
        	<input type="hidden" name="pageType" value="${type}">
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">날짜<span class="required-alert">*</span></h5>
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
	            </div>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">시간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchTime" value="workingTime" checked="checked">출근 <span class="group_btn_span">(06시~10시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchTime" value="workingEndTime">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchTime" value="directTime">시간 설정</label>
	            </div>
	            <div class="calendar direct_time none" id="directTime">
					<select class="selectBox selectTime" name="startTime" id="startTime"></select>
	                ~
					<select class="selectBox selectTime ml8" name="endTime" id="endTime"></select>
	            </div>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius"  name="searchLocation">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdId}'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	        </div>
        </form>
        <div class="bottom_btn">
            <button type="button" onclick="trafficQuantityPredictionResult()" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	tabListOnOff();
	bigdataPredction();
	settingBigdataSearchParam("BD_PREDICTION_002");
	
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
	});

	function trafficQuantityPredictionResult(){
		
		var startDate = $('.date_picker').val();
		var endDate = $('.end_date_picker').val();
		
		if(startDate != "" && endDate != "") {
			let searchOption = $("#searchForm").serialize();
			map.bigdata.getCrossPrediction(searchOption);
			bigdataSearchForm = $("#searchForm").serializeObject();
	        var remarksItem =$(`
	    	        <div class="remarks_container">
	    		        <div class="remarks_title_box">
	    		            <h6 class="remarks_title">범례 - 교통량예측</h6>
	    		        </div>
	    	        	<div class="remarks_wrap" style="padding:0.5rem 1.5rem 0.5rem 0.5rem;">
	    	            	<div>
	    		                <div class="check_line_container">
	    		                	<div class="remarks_gradient_wrap">
										<div class="remarks_gradient"></div>
		    		                	<div class="remarks_gradinent_txt_wrap">
		    		                		<div class="remarks_gradinent_txt">20,000</div>
		    		                		<div class="remarks_gradinent_txt">0</div>
		    		                	</div>
	    		                	</div>
	    		                </div>
	    	            	</div>
	    		            <div class="unit">단위 : 대</div>
	    	        	</div>
	    	    	</div>`)
			$('#map-container').find(".remarks_container").remove();
			$('#map-container').append(remarksItem);
			legendToggle();
			resultChange();
		} else {
			new ModalBuilder().init().alertBoby("날짜를 선택해주세요.").footer(4,'확인',function(button, modal){modal.close();}).open();
			modalAlertWrap();
		}
		
	}
	
</script>
