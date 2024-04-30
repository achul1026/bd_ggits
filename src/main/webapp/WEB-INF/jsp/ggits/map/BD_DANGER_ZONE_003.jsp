<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
        <form id="searchForm" method="get" class="result_change">
        <input type="hidden" name="pageType" value="<c:out value='${type}'/>">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear">
	           	<c:forEach var="yearsList" items="${yearsList}">
					<c:if test="${fn:startsWith(yearsList.year, '20')}">
	                <option value="<c:out value='${yearsList.year}'/>"><c:out value='${yearsList.year}'/>년</option>
					</c:if>
	           	</c:forEach>
	            </select>
	        </div>
			<div class="tab_item_box flex-center">
				<h5 class="tab_item_title">월별</h5>
				<select class="selectBox radius" name="searchMonth">
					<option value="">전체</option>
					<option value="01">1월</option>
					<option value="02">2월</option>
					<option value="03">3월</option>
					<option value="04">4월</option>
					<option value="05">5월</option>
					<option value="06">6월</option>
					<option value="07">7월</option>
					<option value="08">8월</option>
					<option value="09">9월</option>
					<option value="10">10월</option>
					<option value="11">11월</option>
					<option value="12">12월</option>
				</select>
			</div>
	        <%--<div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchPeriod" value="weekday" checked="checked">평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchPeriod" value="weekend">주말</label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchPeriod" value="directDate">직접입력</label>
	            </div>
	            <div class="calendar direct_time none">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		            ~
	            <div class="end_calendar_box">
					<div class="date_picker_block"></div>								            
		            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" placeholder="날짜를 선택해주세요." autocomplete="off">
		        </div>	  		            
				</div>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">시간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchTime" value="workingTime" checked="checked">출근 <span class="group_btn_span">(06시~10시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchTime" value="workingEndTime">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchTime" value="directTime">시간 설정</label>
	            </div>
	            <div class="calendar direct_time none">
					<select class="selectBox selectTime" name="startTime" id="startTime"></select>
	                ~
					<select class="selectBox selectTime" name="endTime" id="endTime"></select>
	            </div>
	        </div>--%>
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
	            <h5 class="tab_item_title">사고 유형별</h5>
	            <select class="selectBox radius" name="accidentType">
	                <option value="">전체</option>
	                <option value="BCYCL">자전거 사고</option>
					<option value="FROST">결빙사고 구역</option>
					<option value="DRNKG">음주 사고 구역</option>
					<option value="TWHLVH">이륜차 사고 구역</option>
					<option value="PDSN">보행자 사고 구역</option>
					<option value="TRUCK">화물차 사고 구역</option>
	                <option value="LAWVLTN">법위반 보행자 사고구역</option>
	                <option value="JAYWK">무단횡단 보행자 사고구역</option>
					<option value="HLDY">휴일기간 보행자 사고구역</option>
					<option value="OLMAN">노인 보행자 사고 구역</option>
					<option value="CHILD">어린이 보행자 사고 구역</option>
	            </select>
	        </div>
        </form>
        <div class="bottom_btn">
            <button type="button" onclick="accidentResult()" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
    </div>
</div>
 			                    <!-- <div class="check_line_box check_border">
			                        <span class="remarks_circle set4-7">노인 보행자 사고 구역</span>
			                    </div> -->
<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	
	settingBigdataSearchParam("BD_DANGER_ZONE_003")
	
	function accidentResult(){
		/*map.bigdata.getAccidentBySGGInfo($("#searchForm").serialize());*/
		map.bigdata.getAccidentDangerAreaInfo($("#searchForm").serialize());
		
		bigdataSearchForm = $("#searchForm").serializeObject();
		
	    var remarksItem =$(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 사고 유형</h6>
			        </div>
		        	<div class="remarks_wrap">
		            	<div>
			                <div class="check_line_container">
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-1">자전거 사고</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-2">결빙사고 구역</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-3">음주 사고 구역</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
				                	<span class="remarks_circle set4-4">이륜차 사고 구역</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-5">보행자 사고 구역</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-6">화물차 사고 구역</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-8">법위반 보행자 사고 구역</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-9">무단횡단 보행자 사고 구역</span>
			                    </div>
			                    <div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-10">휴일기간 보행자 사고 구역</span>
			                    </div>
								<div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-7">노인 보행자 사고 구역</span>
			                    </div>
								<div class="check_line_box check_border is-double">
			                        <span class="remarks_circle set4-9">어린이 보행자 사고 구역</span>
			                    </div>
								<div style='clear:both;'></div>
			                </div>
		            	</div>
			            <div class="unit">단위 : 사고 유형</div>
		        	</div>
		    	</div>`)
		$('#map-container').find(".remarks_container").remove();
	        $('#map-container').append(remarksItem);
	        legendToggle();
	        resultChange();
	}
</script>
