<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysYmd"><fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일" /></c:set>
<div class="flex-between baseline">
    <div class="mini_dash_box_title">
        <span class="display-inline-block vm" style="color:white;">사고예측 지역별 위험 링크 TOP 20</span>
        <span class="tooltip-common" title="- 빅데이터 분석을 통해 예측된 주요 사고 지역 내 링크 수 순위 현황입니다.&#10;* 사용 데이터&#10;- 수집데이터: 지자체별 장치수집데이터(VDS,DSRC), 경기도교통DB(소통정보)&#10;* 로직(처리개요)&#10;- 표준노드링크 기반 전일 시간대별 평균데이터를 입력으로 AI엔진에 의해 예측을 진행한다.&#10;- 예측데이터는 과거데이터를 기반으로 현재 시점에서의 사고위험구간을 의미한다.&#10;- 주입력값은 지자체별 수집된 전날의 소통정보이며, 예측값은 매일 단위로 업데이트 된다.">?</span>
    </div>
</div>
<div class="flex-between">
    <div class="mini_time">${sysYmd} 집계 데이터 정보(00:00 ~ 24:00)</div>
</div>
<div class="mini_chart_wrap  is-absolute">
    <div class="mini_chart dashboard_scroll"  style="max-height:none;height:100%;">
        <table class="monitor_table">
            <colgroup>
                <col>
                <col>
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>행정시</th>
                <th>행정동</th>
                <th>위험 링크 수</th>
            </tr>
            </thead>
            <tbody>
	   	   <c:choose>
            	<c:when test="${not empty acdntPredictionTop10}">
            		<c:forEach var="acdntPredictionTop10Item" items="${acdntPredictionTop10}">
			            <tr>
			                <td><c:out value="${acdntPredictionTop10Item.adsiNm}"/></td>
			                <td><c:out value="${acdntPredictionTop10Item.adstdgNm}"/></td>
			                <td><c:out value="${acdntPredictionTop10Item.safeIdex}"/></td>
			            </tr>
            		</c:forEach>
            	</c:when>
            	<c:otherwise>
	            	<tr>
		            	<td colspan="3">
		            		조회된 결과가 없습니다.
		            	</td>
	            	</tr>
            	</c:otherwise>
          </c:choose>
            </tbody>
        </table>
    </div>
</div>