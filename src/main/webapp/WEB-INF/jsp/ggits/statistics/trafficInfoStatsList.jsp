<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="main_container">
	<aside class="snb_container">
	    <div class="snb_wrap">
	        <h3 class="side_title">교통 정보 통계 분석</h3>
	        <div class="side_txt">
	            교통 정보 통계 분석 자료입니다.
	        </div>
	        <div class="side_btn">
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do" class="on">교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/communication/list.do">소통정보 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/crowded/traffic_crowded_stats/list.do">혼잡도 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/public/bus_dtg_info/list.do">대중교통 지표 총괄 통계</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/safety/traffic_acndt_gen_log/list.do">도로안전</a>
	            <a href="${pageContext.request.contextPath}/statistics/traffic/analysis/facilities/traffic_facility_obtc_colt/list.do">교통시설물 통계</a>
	        </div>
	    </div>
	</aside>
	<section class="main_section tab_set">
	    <h2 class="blind">교통정보 통계분석</h2>
	    <div class="group_btn_wrap tab_fc">
	        <div class="table_btn_left">
	            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 1}">is-darkgreen-btn</c:if>" data-index="1">교통현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 2}">is-darkgreen-btn</c:if>" data-index="2">돌발현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 3}">is-darkgreen-btn</c:if>" data-index="3">기상현황 / 별 교통량 / 돌발대기오염현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 4}">is-darkgreen-btn</c:if>" data-index="4">긴급차량 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 5}">is-darkgreen-btn</c:if>" data-index="5">시내버스 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn tabMenu <c:if test="${tabNum eq 6}">is-darkgreen-btn</c:if>" data-index="6">위험예측구간 현황통계</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	    	<c:if test="${tabNum eq 1}">
		        <div class="">
		        	<form id="searchForm" method="get">
		        		<input type="hidden" id="tabNum" name="tabNum" value="${tabNum}">
		        		<input type="hidden" id="page" name="page"  value="1"/>
			            <div class="group2">
			                <div class="group_text2">주소 검색</div>
			                <input type="text" placeholder="시/군/구/도로명을 입력해 주세요." name="searchContent" id="searchContent" class="input_same group_box" value="${searchContent}">
			                <div class="search_detail_btn">
			                    상세 검색 <i></i>
			                </div>
			            </div>
			            <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
			                            <input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." data-value="${strDt}" autocomplete="off">
			                            ~
			                            <input type="text" class="end_date_picker input_same ml8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." data-value="${endDt}" autocomplete="off">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="group2">
	                            <div class="group_text2">요일 설정</div>
	                            <div class="flex-center gap24">
	                                <div>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                                </div>
	                            </div>
	                        </div>
			            </div>
		                <div class="group2_btn">
							<!-- button id  name 바꿔서 사용하세요  -->
							<button type="button" class="is-darkgreen-btn" id="search_test" onclick="fnSearchList();">찾기</button>
							<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
		        	</form>
		            <div class="search_container">
		                <div class="search_head">
		                    <div class="search_number">
		                        <span>${totalCnt}개</span>의 검색결과를 찾았습니다.
		                    </div>
		                    <div class="table_right_btn">
		                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
		                    </div>
		                </div>
		            </div>
		            <table class="mt16">
		                <colgroup>
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="left">도로명</th>
			                    <th scope="col">전체 차량 수</th>
			                    <th scope="col">평균속도</th>
			                    <th scope="col">최고속도</th>
			                    <th scope="col">최저속도</th>
			                    <th scope="col">
		                            <select class="table-filter">
			                            <option selected="selected">차로</option>
			                            <option>전체</option>
			                            <option>일반</option>
			                            <option>버스전용</option>
			                        </select>		                    
			                    </th>
			                    <th scope="col">
		                            <select class="table-filter">
			                            <option selected="selected">기상</option>
			                            <option>전체</option>
			                            <option>맑음</option>
			                            <option>비</option>
			                            <option>눈</option>
			                        </select>		                    
			                    </th>
			                </tr>
		                </thead>
		                <tbody>
			                <tr>
			                    <td class="left">안양1로</td>
			                    <td>22,500</td>
			                    <td>25km/h</td>
			                    <td>30km/h</td>
			                    <td>10km/h</td>
			                    <td>일반</td>
			                    <td>비</td>
			                </tr>
			                <tr>
			                    <td class="left">안양2로</td>
			                    <td>22,500</td>
			                    <td>50km/h</td>
			                    <td>60km/h</td>
			                    <td>30km/h</td>
			                    <td>버스전용</td>
			                    <td>맑음</td>
			                </tr>
		                </tbody>
		            </table>
		            <div>
		                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            </div>
		    
		            <div class="table_chart_wrap">
		                <div class="table_chart_text">
		                    안양시 안양로의 비오는 월/화/수 요일의 첨두시간 교통 지표 분석 결과입니다
		                </div>
		                <div class="table_chart_list">
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_b">최고 교툥량</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:50%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <th scope="col">도로명</th>
				                                <th scope="col">교통량</th>
				                            </tr>
			                            </thead>
										<tbody>
				                            <tr>
				                                <td>1</td>
				                                <td>안양4로</td>
				                                <td>42,500</td>
				                            </tr>
				                            <tr>
				                                <td>2</td>
				                                <td>안양6로</td>
				                                <td>32,400</td>
				                            </tr>
				                            <tr>
				                                <td>3</td>
				                                <td>안양10로</td>
				                                <td>30,400</td>
				                            </tr>
				                            <tr>
				                                <td>4</td>
				                                <td>안양8로</td>
				                                <td>28,100</td>
				                            </tr>
				                            <tr>
				                                <td>5</td>
				                                <td>안양3로</td>
				                                <td>20,200</td>
				                            </tr>
				                            <tr>
				                                <td>6</td>
				                                <td>안양16로</td>
				                                <td>10,600</td>
				                            </tr>
				                            <tr>
				                                <td>7</td>
				                                <td>안양66로</td>
				                                <td>5,900</td>
				                            </tr>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_g">최저 교툥량</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:50%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <th scope="col">도로명</th>
				                                <th scope="col">교통량</th>
				                            </tr>
			                            </thead>
										<tbody>
				                            <tr>
				                                <td>1</td>
				                                <td>안양4로</td>
				                                <td>10,800</td>
				                            </tr>
				                            <tr>
				                                <td>2</td>
				                                <td>안양6로</td>
				                                <td>20,800</td>
				                            </tr>
				                            <tr>
				                                <td>3</td>
				                                <td>안양10로</td>
				                                <td>30,400</td>
				                            </tr>
				                            <tr>
				                                <td>4</td>
				                                <td>안양8로</td>
				                                <td>32,100</td>
				                            </tr>
				                            <tr>
				                                <td>5</td>
				                                <td>안양3로</td>
				                                <td>42,200</td>
				                            </tr>
				                            <tr>
				                                <td>6</td>
				                                <td>안양1로</td>
				                                <td>58,200</td>
				                            </tr>
				                            <tr>
				                                <td>7</td>
				                                <td>안양10로</td>
				                                <td>60,200</td>
				                            </tr>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_b">소통 정체</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <th scope="col">도로명</th>
				                                <th scope="col">주행거리</th>
				                                <th scope="col">평균속도</th>
				                            </tr>
			                            </thead>
										<tbody>
				                            <tr>
				                                <td>1</td>
				                                <td>안양4로</td>
				                                <td>8km</td>
				                                <td>80km</td>
				                            </tr>
				                            <tr>
				                                <td>2</td>
				                                <td>안양2로</td>
				                                <td>18km</td>
				                                <td>75km</td>
				                            </tr>
				                            <tr>
				                                <td>3</td>
				                                <td>안양3로</td>
				                                <td>5km</td>
				                                <td>60km</td>
				                            </tr>
				                            <tr>
				                                <td>4</td>
				                                <td>안양4로</td>
				                                <td>3km</td>
				                                <td>58km</td>
				                            </tr>
				                            <tr>
				                                <td>5</td>
				                                <td>안양5로</td>
				                                <td>40km</td>
				                                <td>55km</td>
				                            </tr>
				                            <tr>
				                                <td>6</td>
				                                <td>안양6로</td>
				                                <td>3km</td>
				                                <td>40km</td>
				                            </tr>
				                            <tr>
				                                <td>7</td>
				                                <td>안양7로</td>
				                                <td>30km</td>
				                                <td>30km</td>
				                            </tr>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
		                    <div class="chart chart_table">
		                        <div class="chart_table_title">
		                            <span class="traffic_g">소통 원활</span> 도로 순위
		                        </div>
		                        <div class="table_chart_box">
			                        <table>
			                            <colgroup>
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                                <col style="width:25%">
			                            </colgroup>
			                            <thead>
				                            <tr>
				                                <th scope="col">순위</th>
				                                <th scope="col">도로명</th>
				                                <th scope="col">주행거리</th>
				                                <th scope="col">평균속도</th>
				                            </tr>
			                            </thead>
										<tbody>
				                            <tr>
				                                <td>1</td>
				                                <td>안양4로</td>
				                                <td>8km</td>
				                                <td>30km</td>
				                            </tr>
				                            <tr>
				                                <td>2</td>
				                                <td>안양2로</td>
				                                <td>18km</td>
				                                <td>40km</td>
				                            </tr>
				                            <tr>
				                                <td>3</td>
				                                <td>안양3로</td>
				                                <td>5km</td>
				                                <td>50km</td>
				                            </tr>
				                            <tr>
				                                <td>4</td>
				                                <td>안양4로</td>
				                                <td>3km</td>
				                                <td>55km</td>
				                            </tr>
				                            <tr>
				                                <td>5</td>
				                                <td>안양5로</td>
				                                <td>40km</td>
				                                <td>58km</td>
				                            </tr>
				                            <tr>
				                                <td>6</td>
				                                <td>안양6로</td>
				                                <td>3km</td>
				                                <td>80km</td>
				                            </tr>
				                            <tr>
				                                <td>7</td>
				                                <td>안양7로</td>
				                                <td>30km</td>
				                                <td>84km</td>
				                            </tr>
										</tbody>	    
			                        </table>
		                        </div>
		                    </div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">차종 <span>총 22,400대</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab1_1_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">사고건수 (+ 유형)</div>
	                       		<div style="height:380px">
		                       		<canvas id="tab1_2_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">차로별 교통량</div>
	                       		<div style="height:380px">
		                       		<canvas id="tab1_3_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">차로별 차종</div>
	                       		<div style="height:380px">
		                       		<canvas id="tab1_4_chart"></canvas>
	                       		</div>
	                       	</div>
		                </div>
		            </div>
		        </div>
	    	</c:if>
			
			<c:if test="${tabNum eq 2}">
		        <div class="tab2">
		        	<form id="searchForm" method="get">
		        		<input type="hidden" id="tabNum" name="tabNum" value="${tabNum}">
		        		<input type="hidden" id="page" name="page"  value="1"/>
			            <div class="group2">
			                <div class="group_text2">주소 검색</div>
			                <input type="text" placeholder="시/군/구/도로명을 입력해 주세요." name="searchContent" class="input_same group_box" value="${searchContent}">
			                <div class="search_detail_btn">
			                    상세 검색 <i></i>
			                </div>
			            </div>
			            <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
			                            <input type="text" class="date_picker input_same mr8 input_picker" name="strDt" id="strDt" placeholder="날짜를 선택해주세요." data-value="${strDt}" autocomplete="off">
			                            ~
			                            <input type="text" class="end_date_picker input_same ml8 input_picker" name="endDt" id="endDt" placeholder="날짜를 선택해주세요." data-value="${endDt}" autocomplete="off">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="group2">
	                            <div class="group_text2">요일 설정</div>
	                            <div class="flex-center gap24">
	                                <div>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                                </div>
	                            </div>
	                        </div>
			            </div>
		                <div class="group2_btn">
							<!-- button id  name 바꿔서 사용하세요  -->
							<button type="button" class="is-darkgreen-btn" id="search_test" onclick="fnSearchList();">찾기</button>
							<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
		        	</form>
		            <div class="search_container">
		                <div class="search_head">
		                    <div class="search_number">
		                        <span>${totalCnt}개</span>의 검색결과를 찾았습니다.
		                    </div>
		                    <div class="table_right_btn">
		                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
		                    </div>
		                </div>
		            </div>
		            <table class="mt16">
		                <colgroup>
		                    <col style="width:10%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:20%">
		                    <col style="width:20%">
		                    <col style="width:10%">
		                    <col style="width:10%">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="left">돌발유형</th>
			                    <th scope="col">돌발상황</th>
			                    <th scope="col">도로명</th>
			                    <th scope="col">발생시간</th>
			                    <th scope="col">종료시간</th>
			                    <th scope="col">
		                            <select class="table-filter">
			                            <option selected="selected">차로</option>
			                            <option>전체</option>
			                            <option>일반</option>
			                            <option>버스전용</option>
			                        </select>    
			                    </th>
			                    <th scope="col">수집원</th>
			                </tr>
		                </thead>
		                <tbody>
		                	<c:choose>
		                		<c:when test="${fn:length(statsList) > 0}">
		                			<c:forEach var="statsInfo" items="${statsList}">
		                				<tr>
		                					<td>${statsInfo.inciCate}</td>
		                					<td>${statsInfo.description}</td>
		                					<td>
		                						${fn:split(statsInfo.roadwayNm,'|')[0]}
		                					</td>
		                					<td>
		                						<fmt:parseDate value="${statsInfo.beginDate}" var="beginDt" pattern="yyyyMMddHHmmss"/>
		                						<fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${beginDt}"/>
		                					</td>
		                					<td>
		                						<fmt:parseDate value="${statsInfo.endDate}" var="endDt" pattern="yyyyMMddHHmmss"/>
		                						<fmt:formatDate pattern="yyyy년 MM월 dd일 hh시 mm분" value="${endDt}"/>
		                					</td>
		                					<td>${statsInfo.occurredLane}</td>
		                					<td>-</td>
		                				</tr>
		                			</c:forEach>
		                		</c:when>
		                		<c:otherwise>
		                			<tr>
		                				<td colspan="7">돌발현황 정보가 존재하지 않습니다.</td>
		                			</tr>
		                		</c:otherwise>
		                	</c:choose>
		                </tbody>
		            </table>
		            <div>
		                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            </div>
		            <div class="table_chart_wrap">
		                <div class="table_chart_text">
		                    안양시 안양로의 비오는 월/화/수 요일의 첨두시간 교통 지표 분석 결과입니다
		                </div>
		                <div class="table_chart_list">
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">돌발 상황 비율 <span>총 22,400건</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab2_1_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">시간대별 돌발 상황 발생 건수</div>
	                       		<div style="height:380px">
		                       		<canvas id="tab2_2_chart"></canvas>
	                       		</div>
	                       	</div>
		                </div>
		            </div>
		        </div>
			</c:if>
			
			<c:if test="${tabNum eq 3}">
		        <div class="">
		        	<form id="searchForm" method="get">
		        		<input type="hidden" id="page" name="page"  value="1"/>
			            <div class="group2">
			                <div class="group_text2">주소 검색</div>
			                <input type="text" placeholder="시/군/구/도로명을 입력해 주세요." class="input_same group_box">
			                <div class="search_detail_btn">
			                    상세 검색 <i></i>
			                </div>
			            </div>
			            <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
			                            <input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
			                            ~
			                            <input type="text" class="end_date_picker input_same ml8 input_picker" placeholder="날짜를 선택해주세요.">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="group2">
	                            <div class="group_text2">요일 설정</div>
	                            <div class="flex-center gap24">
	                                <div>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                                </div>
	                            </div>
	                        </div>
			            </div>
		                <div class="group2_btn">
							<!-- button id  name 바꿔서 사용하세요  -->
							<button type="button" class="is-darkgreen-btn" id="search_test" onclick="fnSearchList();">찾기</button>
							<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
		        	</form>
		            <div class="search_container">
		                <div class="search_head">
		                    <div class="search_number">
		                        <span>${totalCnt}개</span>의 검색결과를 찾았습니다.
		                    </div>
		                    <div class="table_right_btn">
		                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
		                    </div>
		                </div>
		            </div>
		            <table class="mt16">
		                <colgroup>
		                    <col style="width:10%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:10%">
		                    <col style="width:10%">
		                    <col style="width:16%">
		                    <col style="width:16%">
		                    <col style="width:10%">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="left">
		                            <select class="table-filter">
			                            <option selected="selected">유형</option>
			                            <option>전체</option>
			                            <option>비</option>
			                            <option>눈</option>
			                            <option>맑음</option>
			                        </select>		          		                    
			                    </th>
			                    <th scope="col">도로명</th>
			                    <th scope="col">교통량</th>
			                    <th scope="col">돌발현황</th>
			                    <th scope="col">돌발상황명</th>
			                    <th scope="col">시작시간</th>
			                    <th scope="col">종료시간</th>
			                    <th scope="col">소통정보</th>
			                </tr>
		                </thead>
		                <tbody>
			                <tr>
			                    <td class="left">맑음</td>
			                    <td>안양1로</td>
			                    <td>22,500</td>
			                    <td>[사고]</td>
			                    <td>차량 추돌 사고</td>
			                    <td>2023년 12월 21일 12시 21분</td>
			                    <td>2023년 12월 21일 12시 41분</td>
			                    <td>[정체]</td>
			                </tr>
			                <tr>
			                    <td class="left">비</td>
			                    <td>안양4로</td>
			                    <td>12,500</td>
			                    <td>[통제]</td>
			                    <td>도로통제</td>
			                    <td>2023년 12월 21일 12시 21분</td>
			                    <td>2023년 12월 21일 12시 41분</td>
			                    <td>[혼잡]</td>
			                </tr>
		                </tbody>
		            </table>
			        <div>
		                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            </div>
			
			        <div class="table_chart_wrap">
			            <div class="table_chart_text">
			                안양시 안양로의 비오는 월/화/수 요일의 첨두시간 교통 지표 분석 결과입니다
			            </div>
			            <div class="table_chart_list">
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">돌발 상황 비율 <span>총 22,400건</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab3_1_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">기상현황/대기오염별 돌발 상황 발생 건수</div>
	                       		<div style="height:380px">
		                       		<canvas id="tab3_2_chart"></canvas>
	                       		</div>
	                       	</div>
			            </div>
			        </div>
			    </div>
			</c:if>
			
			<c:if test="${tabNum eq 4}">
		        <div class="">
		        	<form id="searchForm" method="get">
		        		<input type="hidden" id="page" name="page"  value="1"/>
			            <div class="group2">
			                <div class="group_text2">주소 검색</div>
			                <input type="text" placeholder="시/군/구/도로명을 입력해 주세요." class="input_same group_box">
			                <div class="search_detail_btn">
			                    상세 검색 <i></i>
			                </div>
			            </div>
			            <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
			                            <input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
			                            ~
			                            <input type="text" class="end_date_picker input_same ml8 input_picker" placeholder="날짜를 선택해주세요.">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="group2">
	                            <div class="group_text2">요일 설정</div>
	                            <div class="flex-center gap24">
	                                <div>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                                </div>
	                            </div>
	                        </div>
			            </div>
		                <div class="group2_btn">
							<!-- button id  name 바꿔서 사용하세요  -->
							<button type="button" class="is-darkgreen-btn" id="search_test" onclick="fnSearchList();">찾기</button>
							<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
		        	</form>
		            <div class="search_container">
		                <div class="search_head">
		                    <div class="search_number">
		                        <span>${totalCnt}개</span>의 검색결과를 찾았습니다.
		                    </div>
		                    <div class="table_right_btn">
		                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
		                    </div>
		                </div>
		            </div>
		            <table class="mt16">
		                <colgroup>
		                    <col style="width:10%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:10%">
		                    <col style="width:10%">
		                    <col style="width:16%">
		                    <col style="width:16%">
		                    <col style="width:10%">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="left">차량분류</th>
			                    <th scope="col">차량번호</th>
			                    <th scope="col">시작 도로명</th>
			                    <th scope="col">종착 도로명</th>
			                    <th scope="col">예상 운행시간</th>
			                    <th scope="col">실제 운행시간</th>
			                    <th scope="col">평균속도</th>
			                    <th scope="col">신호횟수</th>
			                </tr>
		                </thead>
		                <tbody>
			                <tr>
			                    <td class="left">구급차</td>
			                    <td>389루 7458</td>
			                    <td>안양1로</td>
			                    <td>안양10로</td>
			                    <td>16분</td>
			                    <td>12분</td>
			                    <td>80km/h</td>
			                    <td>6</td>
			                </tr>
			                <tr>
			                    <td class="left">소방차</td>
			                    <td>389루 7456</td>
			                    <td>안양10로</td>
			                    <td>안양15로</td>
			                    <td>30분</td>
			                    <td>20분</td>
			                    <td>90km/h</td>
			                    <td>12</td>
			                </tr>
		                </tbody>
		            </table>
			        <div>
		                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            </div>
			        <div class="table_chart_wrap">
			            <div class="table_chart_text">
			                안양시 안양로의 비오는 월/화/수 요일의 첨두시간 교통 지표 분석 결과입니다
			            </div>
			            <div class="table_chart_list">
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">긴급차량 이동 건수 <span>총 400건</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab4_1_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">긴급차량 운행시간 감소율<span>(예상 운행 시간 대비 34% 감소)</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab4_2_chart"></canvas>
	                       		</div>
	                       	</div>
			                <div class="chart">
			                    <div class="chart_title">평균 신호 대기 시간</div>
			                    <div class="chart_title_sub">4분 30초</div>
			                </div>
			            </div>
			        </div>
		        </div>
			</c:if>
			
			<c:if test="${tabNum eq 5}">
		        <div class="">
		        	<form id="searchForm" method="get">
		        		<input type="hidden" id="page" name="page"  value="1"/>
			            <div class="group2">
			                <div class="group_text2">주소 검색</div>
			                <input type="text" placeholder="시/군/구/도로명을 입력해 주세요." class="input_same group_box">
			                <div class="search_detail_btn">
			                    상세 검색 <i></i>
			                </div>
			            </div>
			            <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
			                            <input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
			                            ~
			                            <input type="text" class="end_date_picker input_same ml8 input_picker" placeholder="날짜를 선택해주세요.">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="group2">
	                            <div class="group_text2">요일 설정</div>
	                            <div class="flex-center gap24">
	                                <div>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                                </div>
	                            </div>
	                        </div>
			            </div>
		                <div class="group2_btn">
							<!-- button id  name 바꿔서 사용하세요  -->
							<button type="button" class="is-darkgreen-btn" id="search_test">찾기</button>
							<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
		        	</form>
		            <div class="search_container">
		                <div class="search_head">
		                    <div class="search_number">
		                        <span>${totalCnt}개</span>의 검색결과를 찾았습니다.
		                    </div>
		                    <div class="table_right_btn">
		                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
		                    </div>
		                </div>
		            </div>
		            <table class="mt16">
		                <colgroup>
		                    <col style="width:10%">
		                    <col style="width:14%">
		                    <col style="width:14%">
		                    <col style="width:10%">
		                    <col style="width:10%">
		                    <col style="width:16%">
		                    <col style="width:16%">
		                    <col style="width:10%">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="left">노선번호</th>
			                    <th scope="col">출발정류장</th>
			                    <th scope="col">도착정류장</th>
			                    <th scope="col">예상 운행시간</th>
			                    <th scope="col">실제 운행시간</th>
			                    <th scope="col">평균속도</th>
			                    <th scope="col">거리</th>
			                    <th scope="col">신호횟수</th>
			                </tr>
		                </thead>
		                <tbody>
			                <tr>
			                    <td class="left">340</td>
			                    <td>수원빌라1단지</td>
			                    <td>수원여대입구</td>
			                    <td>16분</td>
			                    <td>12분</td>
			                    <td>80km/h</td>
			                    <td>3km</td>
			                    <td>6</td>
			                </tr>
			                <tr>
			                    <td class="left">340-2</td>
			                    <td>수원빌라3단지</td>
			                    <td>수원역</td>
			                    <td>20분</td>
			                    <td>18분</td>
			                    <td>60km/h</td>
			                    <td>4km</td>
			                    <td>8</td>
			                </tr>
		                </tbody>
		            </table>
			        <div>
		                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            </div>
			        <div class="table_chart_wrap">
			            <div class="table_chart_text">
			                안양시 안양로의 비오는 월/화/수 요일의 첨두시간 교통 지표 분석 결과입니다
			            </div>
			            <div class="table_chart_list">
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">시내버스 이동 건수 <span>총 400건</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab5_1_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">시내버스 운행시간대별 감가율<span>(예상 운행 시간 대비 34% 감소)</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab5_2_chart"></canvas>
	                       		</div>
	                       	</div>
			                <div class="chart">
			                    <div class="chart_title">평균 신호 대기 시간</div>
			                    <div class="chart_title_sub">4분 30초</div>
			                </div>
			            </div>
			        </div>
		        </div>
			</c:if>
			
			<c:if test="${tabNum eq 6}">
		        <div class="">
		        	<form id="searchForm" method="get">
		        		<input type="hidden" id="page" name="page"  value="1"/>
			            <div class="group2">
			                <div class="group_text2">주소 검색</div>
			                <input type="text" placeholder="시/군/구/도로명을 입력해 주세요." class="input_same group_box">
			                <div class="search_detail_btn">
			                    상세 검색 <i></i>
			                </div>
			            </div>
			            <div class="search_detail_wrap">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
			                            <input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
			                            ~
			                            <input type="text" class="end_date_picker input_same ml8 input_picker" placeholder="날짜를 선택해주세요.">
	                                </div>
	                            </div>
	                        </div>
	                        <div class="group2">
	                            <div class="group_text2">요일 설정</div>
	                            <div class="flex-center gap24">
	                                <div>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                                </div>
	                            </div>
	                        </div>
			            </div>
		                <div class="group2_btn">
							<!-- button id  name 바꿔서 사용하세요  -->
							<button type="button" class="is-darkgreen-btn" id="search_test">찾기</button>
							<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
		              	</div>
		        	</form>
		            <div class="search_container">
		                <div class="search_head">
		                    <div class="search_number">
		                        <span>${totalCnt}개</span>의 검색결과를 찾았습니다.
		                    </div>
		                    <div class="table_right_btn">
		                        <button type="button" class="is-darkgreen-btn">통계분석 바로보기</button>
		                    </div>
		                </div>
		            </div>
		            <table class="mt16">
		                <colgroup>
		                    <col style="width:22.5%">
		                    <col style="width:22.5%">
		                    <col style="width:11%">
		                    <col style="width:11%">
		                    <col style="width:11%">
		                    <col style="width:11%">
		                    <col style="width:11%">
		                </colgroup>
		                <thead>
			                <tr>
			                    <th scope="col" class="left">시작 도로명</th>
			                    <th scope="col">종착 도로명</th>
			                    <th scope="col">주의 등급 횟수</th>
			                    <th scope="col">위험 등급 횟수</th>
			                    <th scope="col">심각 등급 횟수</th>
			                    <th scope="col">사고 횟수</th>
			                    <th scope="col">통제 횟수</th>
			                </tr>
		                </thead>
		                <tbody>
			                <tr>
			                    <td class="left">안양1로</td>
			                    <td>안양4로</td>
			                    <td>23</td>
			                    <td>7</td>
			                    <td>4</td>
			                    <td>24</td>
			                    <td>14</td>
			                </tr>
			                <tr>
			                    <td class="left">안양2로</td>
			                    <td>안양5로</td>
			                    <td>23</td>
			                    <td>7</td>
			                    <td>4</td>
			                    <td>24</td>
			                    <td>14</td>
			                </tr>
			                <tr>
			                    <td class="left">안양3로</td>
			                    <td>안양6로</td>
			                    <td>23</td>
			                    <td>7</td>
			                    <td>4</td>
			                    <td>24</td>
			                    <td>14</td>
			                </tr>
		                </tbody>
		            </table>
	                <div>
		                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
		            </div>
	                <div class="table_chart_wrap">
	                    <div class="table_chart_text">
	                        안양시 안양로의 비오는 월/화/수 요일의 첨두시간 교통 지표 분석 결과입니다
	                    </div>
	                    <div class="table_chart_list">
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">위험 구간 발생 빈도 <span>총 400건</span></div>
	                       		<div style="height:380px">
		                       		<canvas id="tab6_1_chart"></canvas>
	                       		</div>
	                       	</div>
	                       	<div class="chart">
	                       		<div class="tab_box_title left mb16">사고 유형</div>
	                       		<div style="height:380px">
		                       		<canvas id="tab6_2_chart"></canvas>
	                       		</div>
	                       	</div>
	                    </div>
	                </div>
	            </div>
			</c:if>
        </div>
	</section>
</div>
<script>
	$(document).ready(function(){
		var strDt = $("#strDt").data("value");
		var endDt = $("#endDt").data("value");
		if(!isNull(strDt)&& strDt != ''){
			$("#strDt").val(strDt);
		}
		if(!isNull(endDt)&& endDt != ''){
			$("#endDt").val(endDt);
		}
	})
	
	$(".tabMenu").on('click', function(){
		// 시간, 일, 월, 연
		var tabNum = $(this).data("index");
		window.location.href="${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do?tabNum="+tabNum;

	})
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#search_test').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
	if($("#tabNum").val() == 1){
	   	// tab1-1 chart
	   	new GITSChart(GITSChartType.DOUGHNUT).init("tab1_1_chart")
	    .setDataSetLabel('승용차', '대형차', '버스', '화물차')
	    .setDataSet({
	            label: ['승용차', '대형차', '버스', '화물차'],
	            data:[10, 10, 50, 60],
	            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green'],
	            borderColor:'transparent'
	    })
		.setLabelPadding(30)
		.draw();
	   	
	    // tab1-2 chart
	    new GITSChart(GITSChartType.BAR).init("tab1_2_chart")
	    .setDataSetLabel('0시~2시', '2~4시', '20~22시', '22~24시')
	    .setDataSet({
	        label: '사고건수',
	        data:[40, 20, 30, 60],
	        backgroundColor: '#00BCB1',
	        borderWidth:2
	    })
	    .setTickStepX(10)
	    .setAxis('y')
	    .setBarGridX(true)
	    .setLabelDisplay(false)
	    .SetMaxWidth(80)
	    .draw();

	    // tab1-3 chart
	    new GITSChart(GITSChartType.BAR).init("tab1_3_chart")
	    .setDataSetLabel('1차로', '2차로', '3차로', '4차로')
	    .setDataSet({
	        label: '사고건수',
	        data:[40, 20, 30, 60],
	        backgroundColor: '#00BCB1',
	        borderWidth:2
	    })
	    .setTicksStep(10)
	    .setBarGridX(false)
	    .setBarGridY(true)
	    .setLabelDisplay(false)
	    .SetMaxWidth(80)
	    .draw();

	    // tab1-4 chart
	    new GITSChart(GITSChartType.BAR).init("tab1_4_chart")
	    .setDataSetLabel('1차선','2차선','3차선','4차선')
	    .setDataSet({
	            label: '승용차',
	            data:[300, 150, 200, 100],
	            backgroundColor: '#00BCB1',
	        },{
	            label: '대형',
	            data:[200, 120, 140, 80],
	            backgroundColor: '#F90',
	        },{
	            label: '버스',
	            data:[400, 80, 110, 110],
	            backgroundColor: '#FF5454',
	        },{
	            label: '화물차',
	            data:[200, 120, 140, 40],
	            backgroundColor: '#ACDC87',
	    })
	    .setTickStepX(200)
	    .setAxis('y')
	    .setBarGridX(true)
	    .setLabelPadding(30)
	    .draw();
	}
	
   	if($("#tabNum").val() == 2){
	   	// tab2-1 chart
		new GITSChart(GITSChartType.DOUGHNUT).init("tab2_1_chart")
	    .setDataSetLabel('사고', '행사', '통제', '공사')
	    .setDataSet({
	            label: ['사고', '행사', '통제', '공사'],
	            data:[10, 10, 50, 60],
	            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green'],
	            borderColor:'transparent'
	    })
		.setLabelPadding(30)
		.draw();

		// tab2-2 chart
	    new GITSChart(GITSChartType.BAR).init("tab2_2_chart")
	    .setDataSetLabel('0~2시','2~4시','20~22시','22~24시')
	    .setDataSet({
	            label: '사고',
	            data:[300, 150, 200, 100],
	            backgroundColor: '#00BCB1',
	        },{
	            label: '통제',
	            data:[200, 120, 140, 80],
	            backgroundColor: '#F90',
	        },{
	            label: '행사',
	            data:[400, 80, 110, 110],
	            backgroundColor: '#FF5454',
	        },{
	            label: '공사',
	            data:[200, 120, 140, 40],
	            backgroundColor: '#ACDC87',
	    })
	    .setTickStepX(200)
	    .setAxis('y')
	    .setBarGridX(true)
	    .setLabelPadding(30)
	    .draw();
    }
   	
 
    
   	if($("#tabNum").val() == 3){
	   	// tab3-1 chart
		new GITSChart(GITSChartType.DOUGHNUT).init("tab3_1_chart")
	    .setDataSetLabel('사고', '행사', '통제', '공사')
	    .setDataSet({
	            label: ['사고', '행사', '통제', '공사'],
	            data:[10, 10, 50, 60],
	            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green'],
	            borderColor:'transparent'
	    })
		.setLabelPadding(30)
		.draw();
	   	
	    // tab3-2 chart
	    new GITSChart(GITSChartType.BAR).init("tab3_2_chart")
	    .setDataSetLabel('재난','우박','비','눈')
	    .setDataSet({
	            label: '사고',
	            data:[300, 150, 200, 100],
	            backgroundColor: '#00BCB1',
	        },{
	            label: '통제',
	            data:[200, 120, 140, 80],
	            backgroundColor: '#F90',
	        },{
	            label: '행사',
	            data:[400, 80, 110, 110],
	            backgroundColor: '#FF5454',
	        },{
	            label: '공사',
	            data:[200, 120, 140, 40],
	            backgroundColor: '#ACDC87',
	    })
	    .setTickStepX(200)
	    .setAxis('y')
	    .setBarGridX(true)
	    .setLabelPadding(30)
	    .draw();
   	}
    
   	if($("#tabNum").val() == 4){
	   	// tab4-1 chart
		new GITSChart(GITSChartType.DOUGHNUT).init("tab4_1_chart")
	    .setDataSetLabel('구급차', '소방차', '경찰차', '경호차')
	    .setDataSet({
	            label: ['구급차', '소방차', '경찰차', '경호차'],
	            data:[10, 10, 50, 60],
	            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green'],
	            borderColor:'transparent'
	    })
		.setLabelPadding(30)
		.draw();
	
	    // tab4-2 chart
	    new GITSChart(GITSChartType.BAR).init("tab4_2_chart")
	    .setDataSetLabel('구급차','소방차','경찰차','경호차')
	    .setDataSet({
	            label:"예상 운행 시간",
	            data:[300, 150, 200, 500],
	            backgroundColor: '#3A7DFF',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	        },{
	            label:"실제 운행 시간",
	            data:[250, 120, 160, 400],
	            backgroundColor: '#F90',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	    })
	    .setTicksStep(100)
	    .setLabelDisplay(false)
	    .setAxis('x')
	    .setBarGridY(true)
	    .setBarGridX(false)
	    .setAxisStackedX(false)
	    .setAxisStackedY(false)
	    .draw();
   	}
    
   	if($("#tabNum").val() == 5){
	   	// tab5-1 chart
		new GITSChart(GITSChartType.DOUGHNUT).init("tab5_1_chart")
	    .setDataSetLabel('원활', '지체', '정체')
	    .setDataSet({
	            label: ['원활', '지체', '정체'],
	            data:[10, 10, 50],
	            backgroundColor: ['#00BCB1', 'red',  'yellow'],
	            borderColor:'transparent'
	    })
		.setLabelPadding(30)
		.draw();
	
	    // tab5-2 chart
	    new GITSChart(GITSChartType.BAR).init("tab5_2_chart")
	    .setDataSetLabel('6~7시','7~8시','8~9시','9~10시')
	    .setDataSet({
	            label:"예상 운행 시간",
	            data:[300, 150, 200, 500],
	            backgroundColor: '#3A7DFF',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	        },{
	            label:"실제 운행 시간",
	            data:[250, 120, 160, 400],
	            backgroundColor: '#F90',
	            borderRadius:1,
	            borderWidth:1,
	            fill: false,
	    })
	    .setTicksStep(100)
	    .setLabelDisplay(false)
	    .setAxis('x')
	    .setBarGridY(true)
	    .setBarGridX(false)
	    .setAxisStackedX(false)
	    .setAxisStackedY(false)
	    .draw();
   	}
    
   	if($("#tabNum").val() == 6){
	   	// tab6-1 chart
		new GITSChart(GITSChartType.DOUGHNUT).init("tab6_1_chart")
	    .setDataSetLabel('주의', '위험', '심각')
	    .setDataSet({
	            label: ['주의', '위험', '심각'],
	            data:[10, 10, 50],
	            backgroundColor: ['#00BCB1', 'red',  'yellow'],
	            borderColor:'transparent'
	    })
		.setLabelPadding(30)
		.draw();    
	    
	    // tab6-2 chart
	    new GITSChart(GITSChartType.BAR).init("tab6_2_chart")
	    .setDataSetLabel('사고유형1', '사고유형2', '사고유형3', '사고유형4')
	    .setDataSet({
	        label: '사고건수',
	        data:[40, 20, 30, 60],
	        backgroundColor: '#00BCB1',
	        borderWidth:2
	    })
	    .setTickStepX(10)
	    .setAxis('y')
	    .setBarGridX(true)
	    .setLabelDisplay(false)
	    .SetMaxWidth(80)
	    .draw();
   	}
</script>