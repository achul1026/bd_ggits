<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="pt16 pb16">
	<div class="flex-center gap16">
	    <div class="">집계 기간</div>
	    <div class="flex-center">
	        <div class="calendar">
	            <input type="text" class="date_picker input_same mr8 input_picker modal_input_bg" placeholder="날짜를 선택해주세요.">
	            ~
	            <div class="end_calendar_box">
					<div class="date_picker_block"></div>								            
		            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker modal_input_bg" placeholder="날짜를 선택해주세요.">
	            </div>	            
	        </div>
	    </div>
	</div>
</div>
<div class="tab_set">
    <div class="tab_fc">
        <div class="table_btn_left">
            <button type="button" class="tab_btn_item is-dark-btn modal_input_bg" data-index="1">빅데이터 조회수</button>
            <button type="button" class="tab_btn_item is-dark-btn" data-index="2">다운로드 수</button>
        </div>
    </div>
    <div class="contents_wrap tab_area mt16">
        <div class="tab1">
          	<div style="height:260px">
          		<canvas id="modal_chart1"></canvas>
          	</div>			
	        <div class="mt8">출퇴근 시간의 소통량 정보 조회 수가 가장 많습니다.</div>
        </div>
        <div class="tab-none tab2">
          	<div style="height:260px">
          		<canvas id="modal_chart2"></canvas>
          	</div>	
	        <div class="mt8">출퇴근 시간의 소통량 다운로드가 가장 많습니다.</div>
        </div>
	</div>
</div>