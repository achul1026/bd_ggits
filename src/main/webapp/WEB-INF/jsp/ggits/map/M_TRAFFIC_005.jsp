<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="tab_box_body_wrap">
	<div class="tab_box_sub_title">- 날짜 : <span><c:out value='${today}'/></span></div>
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
				<th>교통량</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${fn:length(trafficInfo.trafficList) > 0}">
					<c:forEach var="trafficList" items="${trafficInfo.trafficList}" varStatus="status">
					    <tr>
					        <td><c:out value='${trafficList.rownum}'/></td>
					        <td><c:out value='${trafficList.roadName}'/></td>
					        <td><c:out value='${trafficList.totalCnt}'/>대</td>
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
