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
            <button type="button" class="tab_btn_item is-dark-btn modal_input_bg" data-index="1">교통량</button>
            <button type="button" class="tab_btn_item is-dark-btn" data-index="2">소통정보</button>
            <button type="button" class="tab_btn_item is-dark-btn" data-index="3">평균속도</button>
        </div>
    </div>
    <div class="contents_wrap tab_area mt16">
        <div class="tab1">
          	<div style="height:260px">
          		<canvas id="modal_chart1"></canvas>
          	</div>
          	<div class="mt8">수원시의 인계동의 교통량이 가장 많습니다.</div>			
        </div>
        <div class="tab-none tab2">
          	<div style="height:260px">
          		<canvas id="modal_chart2"></canvas>
          	</div>
          	<div class="mt8">수원시의 인계동의 소통정보가 가장 많습니다.</div>	
        </div>
        <div class="tab-none tab3">
          	<div style="height:260px">
          		<canvas id="modal_chart3"></canvas>
          	</div>
          	<div class="mt8">수원시의 인계동의 평균속도가 가장 높습니다.</div>	
        </div>
	</div>
</div>