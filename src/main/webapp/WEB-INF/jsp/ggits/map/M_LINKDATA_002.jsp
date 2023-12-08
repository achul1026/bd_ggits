<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="mt16 flex-between ftsize14">
    <div>위치</div>
    <div>중부대로</div>
</div>
<div class="mt4 flex-between ftsize14">
    <div>수집완료/장애발생</div>
    <div>200건/3건</div>
</div>
<div class="dashboard_dark_container mt24">
	<div class="dashboard_dark_title">상세정보</div>
    <div class="mt16 flex-between">
        <div>관리 ID</div>
        <div>SYSTEM01</div>
    </div>
    <div class="mt4 flex-between">
        <div>온도</div>
        <div>16</div>
    </div>
    <div class="mt4 flex-between">
        <div>습도</div>
        <div>97</div>
    </div>
    <div class="mt4 flex-between">
        <div>풍속</div>
        <div>1m/s</div>
    </div>
    <div class="mt4 flex-between">
        <div>수집주기</div>
        <div>5분</div>
    </div>
    <div class="mt4 flex-between">
        <div>사고(누적)</div>
        <div>23건</div>
    </div>
</div>
<div class="dashboard_dark_container mt10">
	<div class="dashboard_dark_title">선택 데이터별 수집 이력</div>
    <div class="mt16 darkdash_btn_wrap">
        <label class="dash_check_box is-dark-btn"><input type="checkbox" class="none">온도</label>
        <label class="dash_check_box is-dark-btn"><input type="checkbox" class="none">습도</label>
        <label class="dash_check_box is-dark-btn"><input type="checkbox" class="none">풍속</label>
        <label class="dash_check_box is-dark-btn"><input type="checkbox" class="none">사고</label>
    </div>
    <div class="gis_data_scroll">
	    <div class="mt10 mb8 flex-between dark_green_bg">
	        <div><span>[20:08:04]</span> 온도</div>
	        <div>16.6</div>
	    </div>
	    <div class="mb8 flex-between dark_green_bg">
	        <div><span>[20:08:04]</span> 온도</div>
	        <div>16.6</div>
	    </div>
	    <div class="mb8 flex-between dark_green_bg red">
	        <div><span>[20:08:04]</span> 온도</div>
	        <div>수집실패</div>
	    </div> 
    </div>
</div>



<script>
	// 선택 데이터별 수집 이력
	$('.dash_check_box').on('change', function(){
		$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').find('input').prop('checked', false);
		$(this).find('input').prop('checked', true);
		$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').removeClass('is-darkgreen-btn');
		$(this).addClass('is-darkgreen-btn');
	})
</script> 