<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="unex_list mt24 mb10">
	<div class="unex_list_title">긴급차량 이동 발생건수</div>
	<div class="unex_list_number">${fn:length(emergencyList)}건</div>
</div>
<div class="unex_wrap gis_scroll">
		<c:forEach var="emergencyList" items="${emergencyList}">
		<div class="tab_box_content map_movement_status map_movement_status1">
			<div class="unex_history">
				<div class="red unex_title">
					<h3>[이동중(컬럼 필요)] ${emergencyList.evno}</h3>
				</div>
			</div>
			<div class="red unex_content">
				<ul>
					<li>[이동중(컬럼 필요)] : ${emergencyList.evno}</li>
					<li>출발지/출발시간 : 팔달로(컬럼 필요) / 14:65(컬럼 필요)</li>
					<li>도착지/도착예측시간 : 수원역(컬럼 필요) / 34:55(컬럼 필요)</li>
					<li>예상운행시간 : 10분(컬럼 필요)</li>
					<li>실제도착시간 : 10분(컬럼 필요)</li>
				</ul>
			</div>
		</div>
	</c:forEach>
<!-- 	<div class="tab_box_content map_movement_status map_movement_status2"> -->
<!-- 		<div class="unex_history"> -->
<!-- 			<div class="blue unex_title"> -->
<!-- 				<h3>[이동완료] 55우 2440</h3> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="blue unex_content"> -->
<!-- 			<ul> -->
<!-- 				<li>[이동중] : 88우 1010</li> -->
<!-- 				<li>출발지/출발시간 : 팔달로 / 14:65</li> -->
<!-- 				<li>도착지/도착예측시간 : 수원역 / 34:55</li> -->
<!-- 				<li>예상운행시간 : 10분</li> -->
<!-- 				<li>실제도착시간 : 10분</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="tab_box_content map_movement_status map_movement_status3"> -->
<!-- 		<div class="unex_history"> -->
<!-- 			<div class="red unex_title"> -->
<!-- 				<h3>[이동중] 55우 2440</h3> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="red unex_content"> -->
<!-- 			<ul> -->
<!-- 				<li>[이동중] : 88우 1010</li> -->
<!-- 				<li>출발지/출발시간 : 팔달로 / 14:65</li> -->
<!-- 				<li>도착지/도착예측시간 : 수원역 / 34:55</li> -->
<!-- 				<li>예상운행시간 : 10분</li> -->
<!-- 				<li>실제도착시간 : 10분</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="tab_box_content map_movement_status map_movement_status4"> -->
<!-- 		<div class="unex_history"> -->
<!-- 			<div class="blue unex_title"> -->
<!-- 				<h3>[이동완료] 55우 2440</h3> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 		<div class="blue unex_content"> -->
<!-- 			<ul> -->
<!-- 				<li>[이동중] : 88우 1010</li> -->
<!-- 				<li>출발지/출발시간 : 팔달로 / 14:65</li> -->
<!-- 				<li>도착지/도착예측시간 : 수원역 / 34:55</li> -->
<!-- 				<li>예상운행시간 : 10분</li> -->
<!-- 				<li>실제도착시간 : 10분</li> -->
<!-- 			</ul> -->
<!-- 		</div> -->
<!-- 	</div> -->
</div>
<script>
	$('.map_movement_status .unex_history').click(function(){
		if ($(this).parent().find('.unex_title').hasClass('on')) {
			$('.unex_title').removeClass('on').parent().next().slideUp();
		} else {
			$('.unex_title').removeClass('on').parent().next().slideUp();
			$(this).parent().find('.unex_title').addClass('on').parent().next().slideDown(200);
		}
	})
</script>