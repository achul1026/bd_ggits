<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">교통 DB화 통계</h3>
                    <div class="side_txt">
                        교통 DB화 통계 정보를 <br>조회합니다.
                    </div>
                    <div class="side_btn">
                        <a href="${pageContext.request.contextPath}/statistics/traffic/database/list.do">교통총괄지표</a>
                        <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do"  class="on">교통영향평가</a>
                    </div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">교통 DB화 통계</h2>
                <div class="contents_wrap">
                    <div class="group2">
                        <div class="group_text2">검색</div>
                        <div class="flex-center gap24">
                            <div>
                                <span class="group_btn_title">분류</span>
                                <input type="hidden" id="selectedType" value="${type}">
                                <select class="selectBox" id="selectBox">
                                    <option value="crossroad_by_traffic">교차로별 교통량</option>
                                    <option value="section_by_traffic">구간별 교통량</option>
                                    <option value="mean_by_traffic">이용수단별</option>
                                    <option value="similar_facilities_by_floating_population">유사시설별 유동인구</option>
                                    <option value="use_by_traffic_mean_share_rate">용도별 교통수단 분담률</option>
                                    <option value="use_by_nbopl_cnt">용도별 재차인원</option>
                                    <option value="parking_occurrence">주차발생</option>
                                    <option value="time_by_in_and_out_pass">시간대별 유출입 통행</option>
                                    <option value="time_by_pass_distribution">시간대별 통행 분포</option>
                                </select>
                            </div>
                            <div>
                                <span class="group_btn_title">지역</span>
                                <select class="selectBox">
                                    <option>팔달구</option>
                                    <option>test1</option>
                                    <option>test2</option>
                                    <option>test3</option>
                                    <option>test4</option>
                                    <option>test5</option>
                                </select>
                            </div>
                            <div>
                                <span class="group_btn_title">교차로명</span>
                                <select class="selectBox">
                                    <option>팔달사거리 ~ 팔달삼거리</option>
                                    <option>test1</option>
                                    <option>test2</option>
                                    <option>test3</option>
                                    <option>test4</option>
                                    <option>test5</option>
                                </select>
                            </div>
                        </div>
                        <div class="search_detail_btn">
                            상세 검색 <i></i>
                        </div>
                    </div>
                    <div class="search_detail_wrap">
                    	<form>
                    		<input type="hidden" id="ipcssMngNo" value="${ipcssMngNo}">
	                        <div class="group2">
	                            <div class="group_text2">기간 설정</div>
	                            <div class="flex-center">
	                                <div class="calendar">
	                                    <input type="text" class="date_picker input_same mr8" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="startTime"></select>
	                                    ~
	                                    <input type="text" class="end_date_picker input_same mr8 ml8" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" id="endTime"></select>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">첨두</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">비첨두</label>
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
		                    <div class="group2_btn">
		                    	<!-- button id  name 바꿔서 사용하세요  -->
		                        <button type="button" class="is-darkgreen-btn" id="search_test">찾기</button>
		                        <input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
		                    </div>
	                    </form>
                    </div>
                </div>
   				
				<!-- tab4 -->
                <div class="search_container tab4">
                	<div class="search_table_width">
	                    <table class="mt16">
	                        <colgroup>
	                            <col style="width:100px">
	                            <col style="width:200px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                        </colgroup>
							<thead>
		                        <tr>
		                            <th scope="col" rowspan="2">날짜</th>
		                            <th scope="col" rowspan="2">시간</th>
		                            <th scope="col" colspan="2">주거용도</th>
		                            <th scope="col" colspan="2">근린시설</th>
		                            <th scope="col" colspan="2">공연집회</th>
		                            <th scope="col" colspan="2">문화시설</th>
		                        </tr>
		                        <tr>
		                            <th scope="col">상주/상근</th>
		                            <th scope="col">방문/이용</th>
		                            <th scope="col">상주/상근</th>
		                            <th scope="col">방문/이용</th>
		                            <th scope="col">상주/상근</th>
		                            <th scope="col">방문/이용</th>
		                            <th scope="col">상주/상근</th>
		                            <th scope="col">방문/이용</th>
		                        </tr>
							</thead>
							<tbody>
		                        <tr class="tr_total">
		                            <td rowspan="3">23.12.21</td>
		                            <td>합계</td>
		                            <td>80</td>
		                            <td>40</td>
		                            <td>120</td>
		                            <td>60</td>
		                            <td>100</td>
		                            <td>40</td>
		                            <td>160</td>
		                            <td>120</td>
		                        </tr>
		                        <tr>
		                        	<td>07:00 ~ 07:15</td>
		                            <td>40</td>
		                            <td>20</td>
		                            <td>60</td>
		                            <td>30</td>
		                            <td>50</td>
		                            <td>20</td>
		                            <td>80</td>
		                            <td>60</td>
		                        </tr>
		                        <tr>
		                        	<td>07:00 ~ 07:15</td>
		                            <td>40</td>
		                            <td>20</td>
		                            <td>60</td>
		                            <td>30</td>
		                            <td>50</td>
		                            <td>20</td>
		                            <td>80</td>
		                            <td>60</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">주거용도</div>
	                        	<div style="height:380px">
	                        		<canvas id="facility_chart1"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">근린생활시설</div>
	                        	<div style="height:380px">
	                        		<canvas id="facility_chart2"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">공연집회</div>
	                        	<div style="height:380px">
	                        		<canvas id="facility_chart3"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">문화시설</div>
	                        	<div style="height:380px">
	                        		<canvas id="facility_chart4"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>       	            	
            </section> 
        </div>
	<script>
	
	/* 검색결과 */
	//		id name 바꿔서 사용하세요~
	$('#search_test').on('click', function(){
		$('.search_head').removeClass('none')
	})

	$(document).ready(function() {
		$('#selectBox').change(function() {
			var type = $('#selectBox option:selected').val();
			var ipcssMngNo = $("#ipcssMngNo").val();
	    	location.href="${pageContext.request.contextPath}/statistics/traffic/database/assessment/"+type+"/list.do?ipcssMngNo="+ipcssMngNo
	  	}); 

		var selectedType = $("#selectedType").val();
		$('#selectBox').val(selectedType).prop("selected",true);
	});

   	/* chart */
   	// 주거용도
	new GITSChart(GITSChartType.BAR).init("facility_chart1")
	.setDataSetLabel('주거용도')
	.setDataSet({
	        label:"상주/상근",
	        data:[80],
	        backgroundColor: '#3A7DFF',
	        borderRadius:1,
	        fill: false,
	    },{
	        label:"방문/이용",
	        data:[40],
	        backgroundColor: '#F90',
	        borderRadius:1,
	        fill: false,
	})
	.setTicksStep(20)
	.setBarGridY(true)
	.SetMaxWidth(120)
	.setAxisStackedX(false)
	.setAxisStackedY(false)
	.setLabelPadding(20)
	.draw();
  	
   	// 근린시설
	new GITSChart(GITSChartType.BAR).init("facility_chart2")
	.setDataSetLabel('근린생활시설')
	.setDataSet({
	        label:"상주/상근",
	        data:[120],
	        backgroundColor: '#3A7DFF',
	        borderRadius:1,
	        fill: false,
	    },{
	        label:"방문/이용",
	        data:[60],
	        backgroundColor: '#F90',
	        borderRadius:1,
	        fill: false,
	})
	.setTicksStep(20)
	.setBarGridY(true)
	.SetMaxWidth(120)
	.setAxisStackedX(false)
	.setAxisStackedY(false)
	.setLabelPadding(20)
	.draw();
   	
   	// 공연집회
	new GITSChart(GITSChartType.BAR).init("facility_chart3")
	.setDataSetLabel('공연집회')
	.setDataSet({
	        label:"상주/상근",
	        data:[100],
	        backgroundColor: '#3A7DFF',
	        borderRadius:1,
	        fill: false,
	    },{
	        label:"방문/이용",
	        data:[40],
	        backgroundColor: '#F90',
	        borderRadius:1,
	        fill: false,
	})
	.setTicksStep(20)
	.setBarGridY(true)
	.SetMaxWidth(120)
	.setAxisStackedX(false)
	.setAxisStackedY(false)
	.setLabelPadding(20)
	.draw();
   	
   	// 문화시설
	new GITSChart(GITSChartType.BAR).init("facility_chart4")
	.setDataSetLabel('문화시설')
	.setDataSet({
	        label:"상주/상근",
	        data:[160],
	        backgroundColor: '#3A7DFF',
	        borderRadius:1,
	        fill: false,
	    },{
	        label:"방문/이용",
	        data:[120],
	        backgroundColor: '#F90',
	        borderRadius:1,
	        fill: false,
	})
	.setTicksStep(20)
	.setBarGridY(true)
	.SetMaxWidth(120)
	.setAxisStackedX(false)
	.setAxisStackedY(false)
	.setLabelPadding(20)
	.draw();
   
	
    </script>
