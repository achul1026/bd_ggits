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
            <h5 class="tab_item_title">노선유형</h5>
            <button type="button" class="is-dark-btn road_all_selector radius">전체선택/해제</button>
            <label class="road_item is-dark-btn radius inpd"><input type="checkbox" class="none">시내</label>
            <label class="road_item is-dark-btn radius inpd"><input type="checkbox" class="none">시외</label>
            <label class="road_item is-dark-btn radius inpd"><input type="checkbox" class="none">순환</label>
            <label class="road_item is-dark-btn radius inpd"><input type="checkbox" class="none">마을</label>
            <label class="road_item is-dark-btn radius inpd"><input type="checkbox" class="none">공항</label>
        </div>
		<div class="tab_item_box flex-center">
	        <h5 class="tab_item_title">노선</h5>
	        <input type="text" placeholder="버스 번호를 입력해 주세요." class="input_same search_box">
	        <button type="button" class="is-darkgreen-btn ml8">검색</button>
        </div>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="viewResult()">결과보기</button>
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
	    var remarksItem =$(`
		        <div class="remarks_container">
			        <div class="remarks_title_box">
			            <h6 class="remarks_title">범례 - 유동인구 밀집도</h6>
			        </div>
		        	<div class="remarks_wrap tab-none">
		            	<div>
			                <div class="check_line_container">
		                        <button type="button" class="check_line_box remarks-number1">81 -</button>
		                        <button type="button" class="check_line_box remarks-number2">1 - 80</button>
		                        <button type="button" class="check_line_box remarks-number3">41 - 60</button>
		                        <button type="button" class="check_line_box remarks-number4">- 40</button>
			                </div>
		            	</div>
			            <div class="unit">단위 : (%)</div>
		        	</div>
		    	</div>`)        
	        $('#map-container').append(remarksItem);
	        legendToggle();	
	}
</script>
