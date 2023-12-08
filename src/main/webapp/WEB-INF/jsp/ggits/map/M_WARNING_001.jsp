<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="unex_list mt24 mb10">
	<div class="unex_list_title">돌발상활 발생건수</div>
	<div class="unex_list_number">${fn:length(waringList)}건</div>
</div>
<div class="unex_wrap gis_scroll">
	<c:forEach var="waringList" items="${waringList}">
		<div class="tab_box_content map_movement_status">
			<div class="unex_history">
				<div class="red unex_title">
					<h3>${waringList.description}</h3>
				</div>
			</div>
			<div class="red unex_content">
				<ul>
					<li>발생시간 : ${waringList.description}</li>
					<li>장소 : ${fn:split(waringList.roadwayNm,'|')[0]}</li>
					<li>상세 위치 : ${fn:split(waringList.roadwayNm,'|')[0]} ${waringList.occurredLane}번 차로</li>
				</ul>
			</div>
		</div>
	</c:forEach>
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