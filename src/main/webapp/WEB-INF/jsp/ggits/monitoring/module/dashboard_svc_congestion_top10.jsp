<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysYmd"><fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일" /></c:set>
<div class="flex-between baseline">
    <div class="mini_dash_box_title">도로별 주요 정체구간 TOP 10</div>
	<div>
		<%--<select id="roadDiv" name="roadDiv" class="selectBox">
			<c:forEach var="roadDivItem" items="${roadDivList}">
				<option value='<c:out value="${roadDivItem}"/>'${roadDiv eq roadDivItem?'selected':''}><c:out value="${roadDivItem}"/></option>
			</c:forEach>
		</select>--%>
		<select id="roadDiv" name="roadDiv" class="selectBox">
			<option value="고속국도" selected="">고속국도</option>
			<option value="도시고속국도">도시고속국도</option>
			<option value="일반국도">일반국도</option>
			<option value="지방도">지방도</option>
			<option value="특별/광역시도">특별/광역시도</option>
			<option value="시군도">시군도</option>
			<option value="국가지원지방도">국가지원지방도</option>
		</select>
	</div>
</div>
<div class="flex-between">
    <div class="mini_time"><c:out value="${anlsMm}"/> 집계 데이터 정보</div>
	<a href='javascript:void(0)' style="color:rgba(255,255,255,0.5);font-size:12px;text-decoration: underline;color:white;" onclick="openSvcCongestionCalcInfo()">정체구간 산정방법</a>
</div>
<div class="mini_chart_wrap  is-absolute" style="top:67px;">
    <div class="mini_chart dashboard_scroll" style="max-height:none;height:100%;">
        <table class="monitor_table">
            <colgroup>
                <col style="width:63px;">
                <col>
                <col>
                <col>
                <col>
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>구분</th>
                <th>노선명</th>
                <th>방면</th>
                <th>구간명</th>
                <th>일일 총 정체시간<br/>(시간/일)</th>
				<th>거리</th>
				<th>평균통행속도</th>
            </tr>
            </thead>
            <tbody>
			<c:choose>
				<c:when test="${not empty svcCongestionTop10}">
		            <c:forEach var="svcCongestion" items="${svcCongestionTop10}" varStatus="status">
			            <tr>
			            	<c:choose>
			            		<c:when test="${roadDiv ne null and roadDiv ne '' and status.index eq 0}">
					                <td rowspan="${fn:length(svcCongestionTop10)}"><c:out value="${svcCongestion.roadDiv}"/></td>
			            		</c:when>
			            		<c:when test="${roadDiv ne null and roadDiv ne '' and status.index > 0}">
			            		
			            		</c:when>
			            		<c:otherwise>
					                <td><c:out value="${svcCongestion.roadDiv}"/></td>
			            		</c:otherwise>
			            	</c:choose>
			                <td><c:out value="${svcCongestion.routeNm}"/></td>
			                <td><c:out value="${svcCongestion.routeDrct ne null?svcCongestion.routeDrct:'-'}"/></td>
			                <td><c:out value="${svcCongestion.startNodeNm}"/> - <c:out value="${svcCongestion.endNodeNm}"/></td>
			                <td><c:out value="${svcCongestion.avgCngstnTime}"/>시간</td>
							<td><c:out value="${svcCongestion.sctnLen}"/>Km</td>
							<td><c:out value="${svcCongestion.avgSpeedStr}"/>km/h</td>
			            </tr>
		            </c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">조회된 정보가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
<!--             <tr> -->
<!--                 <td rowspan="10" style="border-right:1px solid var(--line-color-600)">고속도로</td> -->
<!--                 <td>수도권 제1순환고속도로</td> -->
<!--                 <td>판교(일산)</td> -->
<!--                 <td>서운JC→중동IC</td> -->
<!--                 <td>1.7km</td> -->
<!--                 <td>43.8km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>서해안고속도로</td> -->
<!--                 <td>목포</td> -->
<!--                 <td>일직JC→금천IC</td> -->
<!--                 <td>4.8km</td> -->
<!--                 <td>49.3km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>수도권 제1순환고속도로</td> -->
<!--                 <td>판교(일산)</td> -->
<!--                 <td>중동IC→송내IC</td> -->
<!--                 <td>2.7km</td> -->
<!--                 <td>54.4km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>경인고속도로</td> -->
<!--                 <td>인천</td> -->
<!--                 <td>부천IC→신월IC</td> -->
<!--                 <td>5.4km</td> -->
<!--                 <td>53.9km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>수도권 제1순환고속도로</td> -->
<!--                 <td>판교(구리)</td> -->
<!--                 <td>시흥T/G→시흥IC</td> -->
<!--                 <td>1.2km</td> -->
<!--                 <td>62.2km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>서해안고속도로</td> -->
<!--                 <td>목포</td> -->
<!--                 <td>광명역IC→일직JC</td> -->
<!--                 <td>3.0km</td> -->
<!--                 <td>58.9</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>수도권 제1순환고속도로</td> -->
<!--                 <td>판교(구리)</td> -->
<!--                 <td>토평IC→강일IC</td> -->
<!--                 <td>1.2km</td> -->
<!--                 <td>58.8km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>수도권 제1순환고속도로</td> -->
<!--                 <td>판교(일산)</td> -->
<!--                 <td>하남JC→상일IC</td> -->
<!--                 <td>2.5km</td> -->
<!--                 <td>58.6km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>수도권 제1순환고속도로</td> -->
<!--                 <td>판교(구리)</td> -->
<!--                 <td>시흥IC→장수IC</td> -->
<!--                 <td>3.9km</td> -->
<!--                 <td>59.8km/h</td> -->
<!--             </tr> -->
<!--             <tr> -->
<!--                 <td>영동고속도로</td> -->
<!--                 <td>강릉</td> -->
<!--                 <td>군자JC→월곶JC</td> -->
<!--                 <td>3.0km</td> -->
<!--                 <td>61.6km/h</td> -->
<!--             </tr> -->
            </tbody>
        </table>
    </div>
</div>
<script>
	$(document).ready(function(){
		$("#roadDiv").on('change',function(){
			svcCongestionTop10Init(true , $(this).val());
		})
	});
</script>