<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="flex-between baseline">
    <div class="mini_dash_box_title">
        <span class="display-inline-block vm" style="color:white;">유동인구 밀집 예측 TOP 10</span>
        <span class="tooltip-common" title="- 빅데이터 분석을 통해 예측된 주요 유동인구 밀집 지역 순위 현황입니다.&#10;* 사용 데이터&#10;- 수집데이터: KT유동인구데이터(시간,요일별 데이터), 버스노선 승하차이력, 날씨정보이력&#10;* 로직(처리개요)&#10;- 수집된 데이터를 전처리 후 학습하여 AI 알고리즘에 의해 모델을 생성하고, 예측값을 산출한다.&#10;- 주입력값은 유동인구 과거데이터(현재월 기준 2개월전)이며, 예측값은 매월 단위로 업데이트 된다.">?</span>
    </div>
</div>
<div class="flex-between">
    <div class="mini_time">(<c:out value="${baseYmd}"/>) 예측 정보</div>
</div>
<div class="mini_chart_wrap is-absolute">
    <div class="mini_chart dashboard_scroll" style="max-height:none;height:100%;">
        <table class="monitor_table">
            <colgroup>
                <col>
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>주소</th>
                <th>예측 유동인구수(명)</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
	            	<c:when test="${not empty populationPredictionTop10}">
	            		<c:forEach var="populationPredictionTop10Item" items="${populationPredictionTop10}">
				            <tr>
				                <td><c:out value="${populationPredictionTop10Item.adstsdgNm}"/></td>
				                <td class="comma"><c:out value="${populationPredictionTop10Item.fltPop}"/></td>
				            </tr>
	            		</c:forEach>
	            	</c:when>
	            	<c:otherwise>
		            	<tr>
			            	<td colspan="2">
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