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
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do'" >교통현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do'">돌발현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/weather_condt_stats/list.do'">기상현황 / 별 교통량 / 돌발대기오염현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/emer_move_stats/list.do'">긴급차량 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/bus_rout_stats/list.do'">시내버스 이동 현황통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/risk_prdn_stats/list.do'">위험예측구간 현황통계</button>
	        </div>
	    </div>
	    <div class="contents_wrap tab_area">
	        <div class="">
	        	<form id="searchForm" method="get">
	        		<input type="hidden" id="tabNum" name="tabNum" value="${tabNum}">
	        		<input type="hidden" id="page" name="page"  value="1"/>
		            <div class="group2">
						<div class="flex-center gap16">
							<div class="group_text2">주소</div>
							<div class="flex-center gap16 pl64">
								<div class="group_text3">시군</div>
								<select class="selectBox">
									<option value="searchAllLocation">전체</option>
									<c:forEach var="sggCdList" items="${sggCdList}">
										<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
									</c:forEach>
								</select>
							</div>
							<div class="flex-center gap16">
								<div class="group_text3">검색</div>
								<input type="text" class="input_same" placeholder="도로명을 입력해주세요.">
							</div>
						</div>
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
						<button type="button" class="is-darkgreen-btn" id="searchBtn" onclick="fnSearchList();">찾기</button>
						<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
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
	                    <col style="width:16%">
	                    <col style="width:16%">
	                    <col style="width:16%">
	                    <col style="width:16%">
	                    <col style="width:16%">
	                    <col style="width:16%">
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
		                </tr>
		                <tr>
		                    <td class="left">안양2로</td>
		                    <td>22,500</td>
		                    <td>50km/h</td>
		                    <td>60km/h</td>
		                    <td>30km/h</td>
		                    <td>버스전용</td>
		                </tr>
	                </tbody>
	            </table>
	            <div>
	                <%@ include file="/WEB-INF/jsp/ggits/utils/paging.jsp"%>
	            </div>
	    
	            <div class="table_chart_wrap">
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
	
	function fnSearchList(){
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#searchBtn').on('click', function(){
		$('.search_head').removeClass('none')
	})
	
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

</script>