<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tab_box_body_wrap">
	<div class="unex_list mb10">
		<div class="unex_list_title">위험물 차량 이동현황</div>
		<div class="unex_list_number">총 <span id="danger_move_cnt">-</span>건</div>
	</div>
	<div class="unex_wrap gis_scroll">
		<table class="monitor_table has-hover" style="width:540px;">
			<colgroup>
				<col style="width:60%">
				<col style="width:20%">
<%-- 				<col style="width:20%"> --%>
				<col style="width:20%">
			</colgroup>
			<thead class="mapbox_tablehead">
			<tr>
				<th>위험물명</th>
				<th>차량번호</th>
<!-- 				<th>현재속도</th> -->
				<th>사고이력여부</th>
			</tr>
			</thead>
			<tbody id="dangerTbody">
			</tbody>
		</table>
	</div>
</div>