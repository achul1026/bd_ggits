<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="tab_box_body_wrap">
	<div class="tab_box_sub_title">- 시간대 : <span>(<c:out value='${beforeTime}'/> ~ <c:out value='${currentTime}'/>)</span></div>
	<table class="monitor_table">
		<colgroup>
			<col width="16%">
			<col width="64%">
			<col width="20%">
		</colgroup>
		<thead>
			<tr>
				<th>순위</th>
				<th>도로명</th>
				<th>평균속도</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(trafficInfo.trafficList) > 0}">
					<c:forEach var="trafficList" items="${trafficInfo.trafficList}" varStatus="status">
					    <tr>
					        <td><c:out value='${trafficList.rownum}'/></td>
					        <td><c:out value='${trafficList.roadName}'/></td>
					        <td><c:out value='${trafficList.totalCnt}'/>km/h</td>
					    </tr>
				    </c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">데이터가 없습니다.</td>
					</tr>
				</c:otherwise>	
			</c:choose>
		</tbody>
	</table>
</div>
