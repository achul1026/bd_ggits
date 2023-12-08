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
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/traffic_info_stats/list.do'" >교통현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/unept_sitn_stats/list.do'">돌발현황 통계</button>
	            <button type="button" class="tab_btn_item is-dark-btn is-darkgreen-btn" onclick="location.href='${pageContext.request.contextPath}/statistics/traffic/analysis/weather_condt_stats/list.do'">기상현황 / 별 교통량 / 돌발대기오염현황 통계</button>
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
		document.getElementById('searchForm').action= "${pageContext.request.contextPath}/statistics/traffic/analysis/weather_condt_stats/list.do";
		document.getElementById('searchForm').submit();
	}
	
	/* 검색결과 */
	//	id name 바꿔서 사용하세요~
	$('#search_test').on('click', function(){
		$('.search_head').removeClass('none')
	})

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
</script>