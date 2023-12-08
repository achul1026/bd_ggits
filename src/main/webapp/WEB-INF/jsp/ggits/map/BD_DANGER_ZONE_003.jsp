<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
        <form id="searchForm">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear">
	                <option value="searchAllYear">전체</option>
	           	<c:forEach var="yearsList" items="${yearsList}">
	                <option value="${yearsList.year}">${yearsList.year}년</option>
	           	</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
	                <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" name="searchPeriod" value="weekday" checked="checked">평일</label>
	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchPeriod" value="weekend">주말</label>
	<!-- 	                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchPeriod" value="holiday">공휴일</label> -->
	                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none" name="searchPeriod" value="directDate">직접입력</label>
	            </div>
	            <div class="calendar direct_time none">
		            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요.">
		            ~
	            <div class="end_calendar_box">
					<div class="date_picker_block"></div>								            
		            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" placeholder="날짜를 선택해주세요.">
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
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
					</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">사고 유형별</h5>
	            <select class="selectBox radius" name="accidentType">
	                <option value="">전체</option>
	                <option value="BCYCL">자전거 사고</option>
	                <option value="JAYWK">무단횡단 보행자 사고구역</option>
	                <option value="LAWVLTN">법위반 보행자 사고구역</option>
					<option value="HLDY">휴일기간 보행자 사고구역</option>
					<option value="FROST">결빙사고 구역</option>
					<option value="TWHLVH">이륜차 사고 구역</option>
					<option value="PDSN">보행자 사고 구역</option>
					<option value="DRNKG">음주 사고 구역</option>
					<option value="TRUCK">화물차 사고 구역</option>
					<option value="OLMAN">노인 보행자 사고 구역</option>
	            </select>
	        </div>
        </form>
        <div class="bottom_btn">
            <button type="button" onclick="accidentResult()" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	tabTitleCss();
	resultRemove();

	function accidentResult(){
		map.bigdata.getAccidentBySGGInfo($("#searchForm").serialize());
		map.bigdata.getAccidentDangerAreaInfo($("#searchForm").serialize());
		
	    var remarksItem =$(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 사고 원인</h6>
			        </div>
		        	<div class="remarks_wrap tab-none">
		            	<div>
			                <div class="check_line_container">
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor1">과속</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor2">신호 위반</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor3">중앙선 침범</span>
			                    </div>
			                    <div class="check_line_box check_border">
				                	<span class="remarks_circle bgcolor4">안전거리 미확보</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor5">교차로 통행방법 위반</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor6">보행자 보호 의무 위반</span>
			                    </div>
			                </div>
		            	</div>
			            <div class="unit">단위 : 사고 원인 유형</div>
		        	</div>
		    	</div>`)        
	        $('#map-container').append(remarksItem);
	        legendToggle();			
	}
</script>
