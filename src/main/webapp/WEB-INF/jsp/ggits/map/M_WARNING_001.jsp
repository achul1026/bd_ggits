<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="tab_box_body_wrap">
	<div class="unex_list mb10">
	<div class="unex_list_title">돌발현황 건수 (<c:out value="${baseYmd}"/>)</div>
	<div class="unex_list_number"><c:out value='${fn:length(waringList)}'/>건</div>
</div>
<div class="unex_wrap gis_scroll">
	<table id="warningTable" class="monitor_table has-hover">
		<colgroup>
			<col style="width:80px;">
			<col style="width:80px;">
			<col style="width:180px;">
			<col style="width:120px;">
			<col style="width:100px;">
			<col style="width:100px;">
			<col style="width:80px;">
		</colgroup>
		<thead>
		<tr>
			<th>수집처</th>
			<th>종류</th>
			<th>설명</th>
			<th>발생시간</th>
			<th>종료(예정)시간</th>
			<th>장소</th>
			<th>차로</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="waringList" items="${waringList}">
			<tr data-srcorg="${waringList.infoSrcOrg}" data-gpsx="<c:out value='${waringList.gpsX}'/>" data-gpsy="<c:out value='${waringList.gpsY}'/>">
				<td>
					<c:choose>
						<c:when test="${waringList.infoSrcOrg == 'GITS'}">
							경기도교통센터
						</c:when>
						<c:when test="${waringList.infoSrcOrg == 'UTIC' || waringList.infoSrcOrg == 'UTIS'}">
							도로교통공단
						</c:when>
						<c:when test="${waringList.infoSrcOrg == '119'}">
							경기 소방본부
						</c:when>
						<c:when test="${waringList.infoSrcOrg == 'SISUL'}">
							경기도 내 터널
						</c:when>
						<c:when test="${waringList.infoSrcOrg == 'SK'}">
							T-map
						</c:when>
						<c:when test="${waringList.infoSrcOrg == 'EX'}">
							도로공사
						</c:when>
						<c:otherwise>
							기타
						</c:otherwise>
					</c:choose>
					<c:out value='${waringList.infoSrcOrg}'/>
				</td>
				<td><c:out value='${waringList.inciCateNm}'/></td>
				<td><c:out value='${waringList.description}'/></td>
				<td><c:out value='${waringList.timeData}'/></td>
				<td><c:out value='${waringList.endDate ? waringList.endDate : "미정"}'/></td>
				<td><c:out value='${fn:split(waringList.roadwayNm,"|")[0]}'/></td>
				<td><c:out value='${waringList.occurredLane}'/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</div>

<script>
$('#warningTable tr').off("click").on("click",function(){
    if ($(this).parent().find('.unex_title').hasClass('on')) {
        $('.unex_title').removeClass('on').parent().next().slideUp();
    } else {
        $('.unex_title').removeClass('on').parent().next().slideUp();
        $(this).parent().find('.unex_title').addClass('on').parent().next().slideDown(200);
        var gpsX = $(this).data("gpsx"); 
        var gpsY = $(this).data("gpsy"); 
        map.control.moveMap([gpsX,gpsY], 15);
    }
});
</script>