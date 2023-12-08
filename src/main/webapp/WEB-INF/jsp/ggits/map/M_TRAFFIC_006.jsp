<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="tab_box_sub_title">
	<div class="mt16">
	    <div class="dashboard_sub_title">도로명으로 찾기</div>
	    <input type="text" class="dashboard_input" placeholder="도로명을 입력해주세요.">
	</div>
	<div class="mt16">
	    <div class="dashboard_sub_title">시간대별로 찾기</div>
	    <select class="selectBox selectTime tab_selectbox"></select>
	</div>
	<div class="dashboard_btn_box">
	    <button type="button" class="is-darkgreen-btn">찾기</button>
	</div>	
</div>
<div class="table_search_on">광명 IC ~ 일직 IC 의 교통량 정보입니다.</div>
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
				        <td>${trafficList.totalCnt}대</td>
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


<script>
	dateTiemInit()
</script>