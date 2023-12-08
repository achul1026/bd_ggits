<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="tab_box_sub_title">(${beforeTime} ~ ${currentTime})</div>
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
				        <td>${status.index+1}</td>
				        <td>${trafficList.vdsSctnNm}</td>
				        <td>${trafficList.totalCnt}km/h</td>
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