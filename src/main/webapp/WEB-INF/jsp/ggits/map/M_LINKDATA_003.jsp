<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="mt16 flex-between ftsize14">
    <div>위치</div>
    <div>중부대로</div>
</div>
<div class="mt4 flex-between ftsize14">
    <div>장애발생</div>
    <div>6건</div>
</div>
<div class="dashboard_dark_container mt10">
	<div class="dashboard_dark_title">선택 데이터별 수집 이력</div>
    <div class="mt16 darkdash_btn_wrap">
        <label class="dash_check_box_all is-dark-btn">전체</label>
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
	$('.dash_check_box').on('change', function(){
		$(this).toggleClass('is-darkgreen-btn');
	})
	$('.dash_check_box_all').on('click', function(){
		if($('.dash_check_box').hasClass('is-darkgreen-btn')){
			$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').find('input').prop('checked', false);
			$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').removeClass('is-darkgreen-btn');
			$(this).removeClass('is-darkgreen-btn');
		} else {
			$(this).parent('.darkdash_btn_wrap').find('.dash_check_box').find('input').prop('checked', true);
			$(this).siblings().addClass('is-darkgreen-btn');
			$(this).addClass('is-darkgreen-btn')
		}
		console.log('zzz')
	})
	
</script> 