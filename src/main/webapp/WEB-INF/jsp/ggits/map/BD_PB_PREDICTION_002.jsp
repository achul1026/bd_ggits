<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
        <div class="tab_item_box flex-center">
            <h5 class="tab_item_title">연도별</h5>
            <select class="selectBox radius">
                <option>전체 메인 메뉴</option>
                <option>테스트</option>
                <option>테스트</option>
                <option>테스트</option>
            </select>
        </div>
        <div class="tab_item_box">
            <div class="flex-center">
                <h5 class="tab_item_title">기간</h5>
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">평일</label>
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">주말</label>
                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none">직접입력</label>
            </div>
            <div class="calendar direct_time none">
	            <input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
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
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">출근 <span class="group_btn_span">(06시~10시)</span></label>
                <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">퇴근 <span class="group_btn_span">(17시~20시)</span></label>
                <label class="group_btn_item is-dark-btn radius inpd direct"><input type="checkbox" class="none">직접 입력</label>
            </div>
            <div class="calendar direct_time none">
				<select id="stTime" class="selectBox selectTime" id="startTime"></select>
                ~
				<select id="endTime" class="selectBox selectTime" id="endTime"></select>
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
        <div class="bottom_btn">
            <button type="button" onclick="viewResult()" class="is-darkgreen-btn radius original_result_btn">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	tabTitleCss();
	resultRemove();
    function viewResult(){
        window.map.bigdata.getPopulationCongestionInfo(null, $("#stTime").val(), $("#endTime").val())

	    var remarksItem =$(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 사고 원인</h6>
			        </div>
		        	<div class="remarks_wrap tab-none">
		            	<div>
			                <div class="check_line_container">
			                    <div class="check_line_box check_border">
		                        	<span class="remarks_circle bgcolor1">경기</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor2">인천</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor3">서울</span>
			                    </div>
			                    <div class="check_line_box check_border">
				                	<span class="remarks_circle bgcolor4">강원</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor5">충청</span>
			                    </div>
			                    <div class="check_line_box check_border">
			                        <span class="remarks_circle bgcolor6">전남</span>
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
