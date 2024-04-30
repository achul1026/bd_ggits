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
	            <input type="hidden" id="startYear" value="<c:out value='${yearsList[0].year}'/>">
	            <input type="hidden" id="endYear" value="<c:out value='${yearsList[fn:length(yearsList) -1].year}'/>">
	            <select class="selectBox radius" name="searchYear" id="searchYear">
	           		<c:forEach var="yearsList" items="${yearsList}" varStatus="status">
	                	<option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
	           		</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
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
	        <%--<div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">시간</h5>
	                <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd"><input type="checkbox" class="none" name=searchTime value="workingTime" checked>출근 <span class="group_btn_span">(06시~10시)</span></label>
		            <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchTime" value="workingEndTime">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchTime" value="directTime">직접 입력</label>
	            </div>
	            <div class="calendar direct_time none" id="directTime">
					<select class="selectBox selectTime" id="startTime" name="startTime"></select>
	                ~
					<select class="selectBox selectTime" id="endTime" name="endTime"></select>
	            </div>
	        </div>--%>
			<div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius" name="sigunCdId">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="<c:out value='${sggCdList.cdId}00000'/>"><c:out value='${sggCdList.cdNm}'/></option>
					</c:forEach>
	            </select>
	        </div>
        </form>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="publicTransportResult()">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	settingBigdataSearchParam("BD_PB_TRANS_003");
	var dataTotalCnt = '<c:out value="${paging.totalCount eq null || paging.totalCount eq '' ? '0' : ''}"/>';

	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	function publicTransportResult() {
		var remarksItem =$(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 승하차/환승 분류</h6>
			        </div>
		        	<div class="remarks_wrap">
		            	<div>
			                <ul class="check_line_container">
			                    <li class="check_line_box remarks-red">승차수</li>
			                    <li class="check_line_box remarks-orange">하차수</li>
			                    <li class="check_line_box remarks-green">환승수</li>
			                </ul>
		            	</div>
			            <div class="unit">단위 : 승하차/ 환승 횟수</div>
		        	</div>
		    	</div>`)
		$('#map-container').find(".remarks_container").remove();
		$('#map-container').append(remarksItem);
		legendToggle();
		window.map.bigdata.getPublicTransferUsageGroupBySGG($("#searchForm").serialize());
		bigdataSearchForm = $("#searchForm").serializeObject();
		resultChange();
	}
	
	$(document).ready(function() {
		if(!isNull($("#startDate").val())){
			$('.date_picker_block').remove();
		}
	})
	
</script>
