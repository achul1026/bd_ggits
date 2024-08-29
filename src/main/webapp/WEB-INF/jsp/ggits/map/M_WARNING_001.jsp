<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tab_box_body_wrap">
	<div class="unex_list mb10">
	<div class="unex_list_title">돌발상활 발생건수</div>
	<div class="unex_list_number"><c:out value='${fn:length(waringList)}'/>건</div>
</div>
<div class="unex_wrap gis_scroll">
	<c:forEach var="waringList" items="${waringList}">
		<div class="red tab_box_content map_movement_status">
			<div class="unex_history"  data-gpsx="<c:out value='${waringList.gpsX}'/>" data-gpsy="<c:out value='${waringList.gpsY}'/>">
				<div class="unex_title">
					<h3>[<c:out value='${waringList.inciCateNm}'/>]<c:out value='${waringList.description}'/></h3>
				</div>
			</div>
			<div class="unex_content">
				<ul>
					<li>발생시간 :  <c:out value='${waringList.timeData}'/></li>
					<li>종료(예정)시간 : <c:out value='${waringList.endDate ? waringList.endDate : "미정"}'/></li>
					<li>장소 : <c:out value='${fn:split(waringList.roadwayNm,"|")[0]}'/></li>
					<li>상세 위치 : <c:out value='${fn:split(waringList.roadwayNm,"|")[0]}'/> <c:out value='${waringList.occurredLane}'/>번 차로</li>
				</ul>
			</div>
		</div>
	</c:forEach>
</div>
</div>

<script>
$('.map_movement_status .unex_history').click(function(){
    if ($(this).parent().find('.unex_title').hasClass('on')) {
        $('.unex_title').removeClass('on').parent().next().slideUp();
    } else {
        $('.unex_title').removeClass('on').parent().next().slideUp();
        $(this).parent().find('.unex_title').addClass('on').parent().next().slideDown(200);
        var gpsX = $(this).data("gpsx"); 
        var gpsY = $(this).data("gpsy"); 
        map.control.moveMap([gpsX,gpsY]);
    }
})
</script>