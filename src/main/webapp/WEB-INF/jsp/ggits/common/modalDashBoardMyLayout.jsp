<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="tab_container">
	<div class="layout_button_container">
		<ul class="layout_button_wrap">
			<li class="layout_list"><button type="button" class="Layout_button is-dark-btn is-darkgreen-btn" data-layout="1">레이아웃 1</button>
			<li class="layout_list"><button type="button" class="Layout_button is-dark-btn" data-layout="2">레이아웃 2</button>
			<li class="layout_list"><button type="button" class="Layout_button is-dark-btn" data-layout="3">레이아웃 3</button>
		</ul>
	</div>
	<div class="layout_list_container">
		<div class="layout_list_wrap tab1">
			<div class="layout_box">
				<div class="layout_flex_box">
					<div class="layout_flex_item">
						<div class="modal_layout_title">경기도 통합 교통 현황</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/chart.png" alt="차트"></div>
								<span>차트</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu1-1"><label for="menu1-1">돌발현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-2"><label for="menu1-2">주요 정체 구간</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-3"><label for="menu1-3">시내버스 운행 현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-4"><label for="menu1-4">광역 긴급차량 운행 현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-5"><label for="menu1-5">시간대별 누적 교통량</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-6"><label for="menu1-6">시긴대별 평균 통행 속도</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-7"><label for="menu1-7">교차로 및 구간 소통 정보</label></li>
							</ul>
						</div>
					</div>
					<div class="layout_flex_item">
						<div class="modal_layout_title">경기도 통합 교통 현황</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/layout.png" alt="레이어"></div>
								<span>레이어</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu2-1"><label for="menu2-1">교통량</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu2-2"><label for="menu2-2">돌발현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu2-3"><label for="menu2-3">광역 긴급차량 운행</label></li>
							</ul>						
						</div>
					</div>
					<div class="layout_flex_item">
						<div class="modal_layout_title">수집시스템 운영 현황</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/chart.png" alt="차트"></div>
								<span>차트</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu3-1"><label for="menu3-1">데이터 수집 장애 알림</label></li>
							</ul>						
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layout_list_wrap tab2 none">
			<div class="layout_box">
				<div class="layout_flex_box">
					<div class="layout_flex_item">
						<div class="modal_layout_title">경기도 통합 교통 현황(바뀌는지테스트1)</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/chart.png" alt="차트"></div>
								<span>차트</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu1-1"><label for="menu1-1">돌발현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-2"><label for="menu1-2">주요 정체 구간</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-3"><label for="menu1-3">시내버스 운행 현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-4"><label for="menu1-4">광역 긴급차량 운행 현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-5"><label for="menu1-5">시간대별 누적 교통량</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-6"><label for="menu1-6">시긴대별 평균 통행 속도</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-7"><label for="menu1-7">교차로 및 구간 소통 정보</label></li>
							</ul>
						</div>
					</div>
					<div class="layout_flex_item">
						<div class="modal_layout_title">경기도 통합 교통 현황</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/layout.png" alt="레이어"></div>
								<span>레이어</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu2-1"><label for="menu2-1">교통량</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu2-2"><label for="menu2-2">돌발현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu2-3"><label for="menu2-3">광역 긴급차량 운행</label></li>
							</ul>						
						</div>
					</div>
					<div class="layout_flex_item">
						<div class="modal_layout_title">수집시스템 운영 현황</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/chart.png" alt="차트"></div>
								<span>차트</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu3-1"><label for="menu3-1">데이터 수집 장애 알림</label></li>
							</ul>						
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="layout_list_wrap tab3 none">
			<div class="layout_box">
				<div class="layout_flex_box">
					<div class="layout_flex_item">
						<div class="modal_layout_title">경기도 통합 교통 현황(바뀌는지테스트2)</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/chart.png" alt="차트"></div>
								<span>차트</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu1-1"><label for="menu1-1">돌발현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-2"><label for="menu1-2">주요 정체 구간</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-3"><label for="menu1-3">시내버스 운행 현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-4"><label for="menu1-4">광역 긴급차량 운행 현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-5"><label for="menu1-5">시간대별 누적 교통량</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-6"><label for="menu1-6">시긴대별 평균 통행 속도</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu1-7"><label for="menu1-7">교차로 및 구간 소통 정보</label></li>
							</ul>
						</div>
					</div>
					<div class="layout_flex_item">
						<div class="modal_layout_title">경기도 통합 교통 현황</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/layout.png" alt="레이어"></div>
								<span>레이어</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu2-1"><label for="menu2-1">교통량</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu2-2"><label for="menu2-2">돌발현황</label></li>
								<li class="modal_layout_item"><input type="checkbox" id="menu2-3"><label for="menu2-3">광역 긴급차량 운행</label></li>
							</ul>						
						</div>
					</div>
					<div class="layout_flex_item">
						<div class="modal_layout_title">수집시스템 운영 현황</div>
						<div class="layout_item_box">
							<div class="modal_layout_sub_title">
								<div class="modal_traffic_imgbox"><img src="${pageContext.request.contextPath}/statics/images/chart.png" alt="차트"></div>
								<span>차트</span>
							</div>
							<ul class="pd16">
								<li class="modal_layout_item"><input type="checkbox" id="menu3-1"><label for="menu3-1">데이터 수집 장애 알림</label></li>
							</ul>						
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
$('.layout_list button').on('click', function(){
	$(this).parent('.layout_list').siblings().children('button').removeClass('is-darkgreen-btn');
	$(this).addClass('is-darkgreen-btn');
})

$(".layout_button_wrap .layout_list button").each(function() {
    $(this).click(function(){
        for( var i = 1;  i <= $(this).parent().children().last().attr("data-layout"); i++){
            if($(this).attr("data-layout") == i){
                $(this).closest(".tab_container").find('.layout_list_container').children(".tab"+i).removeClass('none');
                $(this).closest(".tab_container").find('.layout_list_container').children(".tab"+i).siblings(".layout_list_wrap").addClass('none');
            }
        }
    });
});
</script>

