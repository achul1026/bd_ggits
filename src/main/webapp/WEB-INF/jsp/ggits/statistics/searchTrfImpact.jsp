<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<input type="hidden" id="ipcssMngNo" value="<c:out value='${ipcssMngNo}'/>">
<h2 class="blind">교통 DB화 통계</h2>
<div class="contents_wrap">
	<div class="group2">
		<div class="group_text2">검색</div>
		<div class="flex-center gap24">
			<div>
				<span class="group_btn_title">분류</span> <input type="hidden" id="selectedType" value="<c:out value='${type}'/>"> 
					<select class="selectBox" id="selectBox">
						<option value="crossroad_by_traffic_weekday">평일 교차로별 교통량</option>
						<option value="crossroad_by_traffic_weekend">주말 교차로별 교통량</option>
						<option value="section_by_traffic_weekday">평일 가로구간 교통량</option>
						<option value="section_by_traffic_weekend">주말 가로구간 교통량</option>
						<option value="mean_by_traffic">이용수단별</option>
						<option value="similar_facilities_by_floating_population">유사시설별 유동인구</option>
						<option value="use_by_traffic_mean_share_rate_dwell">주거용도 교통수단 분담률</option>
						<option value="use_by_traffic_mean_share_rate_non_dwell">비주거용도 교통수단 분담률</option>
						<option value="use_by_nbopl_cnt_dwell">주거용도 재차인차인원</option>
						<option value="use_by_nbopl_cnt_non_dwell">비주거용도 재차인차인원</option>
						<option value="parking_occurrence">주차발생</option>
						<option value="time_by_in_and_out_pass">시간대별 유출입 통행</option>
						<option value="time_by_pass_distribution">시간대별 통행 분포</option>
					</select>
			</div>
			<!-- 교차로 만 -->
			<c:if test="${type eq 'crossroad_by_traffic_weekday' or type eq 'crossroad_by_traffic_weekend'}">
			<div>
				<span class="group_btn_title">교차로명</span> 
					<select class="selectBox" id="searchCrsrdNm">
						<option value="all">전체</option>
						<c:forEach var="crsrdNmList" items="${crsrdNmList}">
							<option value="<c:out value='${crsrdNmList.crsrdNo}'/>" ${crsrdNmList.crsrdNo eq dataNo ? 'selected="selected"' : ''}><c:out value='${crsrdNmList.crsrdNm}'/></option>
						</c:forEach>
					</select>
			</div>
			</c:if>
		</div>
	</div>
</div>
<c:if test="${authCd eq 'AUC000'}">
	<div class="flex-end mt16 mb16" id="trafficDownload">  
	    <div class="btn_search_wrap">
			<a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do" class="is-darkgreen-btn mr8">목록</a>
	        <button type="button" class="is-darkgreen-btn mr8" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/database/impact/download.do?ipcssMngNo=<c:out value='${ipcssMngNo}'/>'">일괄 다운로드</button>
	        <button type="button" class="is-darkgreen-btn" id="upload_btn">수정하기</button>
	    </div>
	</div>	
</c:if>