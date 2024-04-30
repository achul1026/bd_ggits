<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<div class="mt16">
    <label class="dashboard_sub_title">지역명 검색</label>
    <input type="text" class="dashboard_input" placeholder="지역명을 입력해주세요.">
</div>
<div class="mt24">
    <label class="dashboard_sub_title">시간대 선택</label>
    <select id="startTiem" class="selectBox selectTime mt8 mr8 wd44"></select>~<select id="endTime" class="selectBox selectTime mt8 ml8 wd44"></select>
</div>
<div class="mt24 mb10">
	<div class="dashboard_sub_title">밀집 수준 선택</div>
	<div class="time_choice">
		<label class="time_green"><input type="checkbox" class="none">원활</label>
		<label class="time_yellow"><input type="checkbox" class="none">혼잡</label>
		<label class="time_red"><input type="checkbox" class="none">심각</label>
	</div>
</div>
<div class="dashboard_btn_box mb8">
    <button type="button" onclick="populationResultEvent()" class="is-darkgreen-btn">찾기</button>
</div>


<script>
	dateTiemInit();
	function populationResultEvent(){
		window.map.monitoring.getPopulationCongestionInfo(null, $("#startTiem").val(), $("#endTime").val());
	}
</script>