<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="flex-between baseline">
    <div class="mini_dash_box_title">버스 정류장 이용량 TOP 10</div>
</div>
<div class="flex-between">
    <div class="mini_time"><c:out value="${rideYmd}"/> 정보</div>
</div>
<div class="mini_chart_wrap is-absolute">
    <div class="mini_chart dashboard_scroll" style="max-height:none;height:100%;">
        <table class="monitor_table">
            <colgroup>
                <col>
                <col>
                <col>
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>시군</th>
                <th>정류장명</th>
                <th>이용객수</th>
                <th>환승수</th>
            </tr>
            </thead>
            <tbody>
           <c:choose>
            	<c:when test="${not empty busStationUsage}">
            		<c:forEach var="busStationUsageItem" items="${busStationUsage}">
			            <tr>
			                <td><c:out value="${busStationUsageItem.adminNm}"/></td>
			                <td><c:out value="${busStationUsageItem.stationNm}"/>(${busStationUsageItem.mobileNo == null ? busStationUsageItem.bstpId : busStationUsageItem.mobileNo})</td>
			                <td class="comma"><c:out value="${busStationUsageItem.rideUserCnt}"/></td>
			                <td class="comma"><c:out value="${busStationUsageItem.trnsitUserCnt}"/></td>
			            </tr>
            		</c:forEach>
            	</c:when>
            	<c:otherwise>
	            	<tr>
		            	<td colspan="4">
		            		조회된 결과가 없습니다.
		            	</td>
	            	</tr>
            	</c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>
<script>
	$(document).ready(function(){
		var commaEl = $(".comma");
		for(var i = 0; i < commaEl.length; i++){
			var commaElStr = commaEl.eq(i).text();
			
			commaEl.eq(i).text(numberComma(commaElStr));
		}
	});
</script>