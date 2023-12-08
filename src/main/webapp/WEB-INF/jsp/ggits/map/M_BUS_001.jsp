<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="mt16">
	<div class="dashboard_sub_title">총 운행중인 버스 대수</div>
	<div class="bus_search_on">330대</div>
</div>
<div class="mt16">
	<div class="dashboard_sub_title">버스 노선 검색</div>
	<input type="text" class="dashboard_input" placeholder="버스 번호를 입력해주세요.">
</div>
<div class="dashboard_btn_box">
	<button type="button" class="is-darkgreen-btn">찾기</button>
</div>
<div class="dashboard_node_box">
	<div class="node_title">
		<span>1140번</span> 버스가 현재 124대 이동중 입니다.
	</div>
	<ul>
		<li class="node_item node_map_bus"><button type="button"
				class="node_button_item">11우 1151</button></li>
		<li class="node_item"><button type="button"
				class="node_button_item">12우 2151</button></li>
		<li class="node_item"><button type="button"
				class="node_button_item">13우 3151</button></li>
		<li class="node_item"><button type="button"
				class="node_button_item">14우 4151</button></li>
		<li class="node_item"><button type="button"
				class="node_button_item">15우 5151</button></li>
	</ul>
	<div class="dashboard-pg-wrap">
		<ul class="dashboard-pg">
			<li><a href="#" class="arrow left"><</a></li>
			<li><a href="#" class="active num">1</a></li>
			<li><a href="#" class="num">2</a></li>
			<li><a href="#" class="num">3</a></li>
			<li><a href="#" class="num">4</a></li>
			<li><a href="#" class="arrow right">></a></li>
		</ul>
	</div>
</div>