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
   				<!-- tab1 -->
                <div class="search_container tab1">
                    <div class="btn_search_wrap flex-end mt16">
                        <button type="button" class="is-darkgreen-btn">일괄 다운로드</button>
                        <button type="button" class="is-darkgreen-btn mj0">수정하기</button>
                    </div>
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
		                            <th scope="col" colspan="7">교차로 방향 (동)</th>
		                            <th scope="col" colspan="7">교차로 방향 (서)</th>
		                        </tr>
		                        <tr>
		                            <th scope="col">승용차</th>
		                            <th scope="col">소형버스</th>
		                            <th scope="col">대형버스</th>
		                            <th scope="col">소형화물</th>
		                            <th scope="col">중형화물</th>
		                            <th scope="col">대형화물</th>
		                            <th scope="col">합계(대)</th>
		                            <th scope="col">승용차</th>
		                            <th scope="col">소형버스</th>
		                            <th scope="col">대형버스</th>
		                            <th scope="col">소형화물</th>
		                            <th scope="col">중형화물</th>
		                            <th scope="col">대형화물</th>
		                            <th scope="col">합계(대)</th>
		                        </tr>
							</thead>
							<tbody>
								<tr class="tr_total">
									<td rowspan="2">23.12.21</td>
									<td>합계(대)</td>
									<td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
								</tr>
		                        <tr>
		                            <td>07:00 ~ 07:15</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">교차로 방향 (동)</div>
	                        	<div style="height:380px">
	                        		<canvas id=""></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">교차로 방향 (서)</div>
	                        	<div style="height:380px">
	                        		<canvas id=""></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
            	
				<!-- tab2 -->
                <div class="search_container tab2 none">
                    <div class="btn_search_wrap flex-end mt16">
                        <button type="button" class="is-darkgreen-btn">일괄 다운로드</button>
                        <button type="button" class="is-darkgreen-btn mj0">수정하기</button>
                    </div>
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
		                            <th scope="col" colspan="7">상행</th>
		                            <th scope="col" colspan="7">하행</th>
		                        </tr>
		                        <tr>
		                            <th scope="col">승용차</th>
		                            <th scope="col">소형버스</th>
		                            <th scope="col">대형버스</th>
		                            <th scope="col">소형화물</th>
		                            <th scope="col">중형화물</th>
		                            <th scope="col">대형화물</th>
		                            <th scope="col">합계(대)</th>
		                            <th scope="col">승용차</th>
		                            <th scope="col">소형버스</th>
		                            <th scope="col">대형버스</th>
		                            <th scope="col">소형화물</th>
		                            <th scope="col">중형화물</th>
		                            <th scope="col">대형화물</th>
		                            <th scope="col">합계(대)</th>
		                        </tr>
							</thead>
							<tbody>
								<tr class="tr_total">
									<td rowspan="2">23.12.21</td>
									<td>합계(대)</td>
									<td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
								</tr>							
		                        <tr>
		                            <td>07:00 ~ 07:15</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">구간 방향 (상행)</div>
	                        	<div style="height:380px">
	                        		<canvas id=""></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">구간 방향 (하행)</div>
	                        	<div style="height:380px">
	                        		<canvas id=""></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
    
				<!-- tab3 -->
                <div class="search_container tab3 none">
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
	                            <col style="width:100px">
	                            <col style="width:100px">
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
		                            <th scope="col" colspan="4">양도중학교[35710]버스정류장(대)</th>
		                            <th scope="col" colspan="4">양도중학교[35709]버스정류장(대)</th>
		                            <th scope="col" colspan="4">양도중학교[35709]버스정류장(대)</th>
		                            <th scope="col" colspan="4">양도중학교[35709]버스정류장(대)</th>
		                            <th scope="col" colspan="4">양도중학교[35709]버스정류장(대)</th>
		                        </tr>
		                        <tr>
		                            <th scope="col">보행</th>
		                            <th scope="col">자전거</th>
		                            <th scope="col">PM</th>
		                            <th scope="col">합계</th>
		                            <th scope="col">보행</th>
		                            <th scope="col">자전거</th>
		                            <th scope="col">PM</th>
		                            <th scope="col">합계</th>
		                           	<th scope="col">보행</th>
		                            <th scope="col">자전거</th>
		                            <th scope="col">PM</th>
		                            <th scope="col">합계</th>
		                           	<th scope="col">보행</th>
		                            <th scope="col">자전거</th>
		                            <th scope="col">PM</th>
		                            <th scope="col">합계</th>
		                           	<th scope="col">보행</th>
		                            <th scope="col">자전거</th>
		                            <th scope="col">PM</th>
		                            <th scope="col">합계</th>
		                        </tr>
							</thead>
							<tbody>
								<tr class="tr_total">
									<td rowspan="3">23.12.21</td>
									<td>합계(대)</td>
									<td>100</td>
									<td>120</td>
									<td>101</td>
									<td>106</td>
									<td>88</td>
									<td>66</td>
									<td>55</td>
									<td>77</td>
									<td>96</td>
									<td>124</td>
									<td>66</td>
									<td>55</td>
									<td>44</td>
									<td>12</td>
									<td>12</td>
									<td>12</td>
									<td>12</td>
									<td>12</td>
									<td>12</td>
									<td>12</td>
								</tr>
		                        <tr>
		                            <td>07:00 ~ 07:15</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>24</td>
		                            <td>24</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">양도중학교(테스트)</div>
	                        	<div style="height:380px">
	                        		<canvas id=""></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">양도중학교(테스트)</div>
	                        	<div style="height:380px">
	                        		<canvas id=""></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
            	
				<!-- tab4 -->
                <div class="search_container tab4 none">
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
            	
				<!-- tab5 -->
                <div class="search_container tab5 none">
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
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                        </colgroup>
							<thead>
		                        <tr>
		                            <th scope="col" rowspan="2">날짜</th>
		                            <th scope="col" rowspan="2">시간</th>
		                            <th scope="col" colspan="6">통근</th>
		                            <th scope="col" colspan="6">통학</th>
		                        </tr>
		                        <tr>
		                            <th scope="col">승용차</th>
		                            <th scope="col">택시</th>
		                            <th scope="col">버스</th>
		                            <th scope="col">전철/지하철</th>
		                            <th scope="col">도보 및 기타</th>
		                            <th scope="col">합계</th>
		                            <th scope="col">승용차</th>
		                            <th scope="col">택시</th>
		                            <th scope="col">버스</th>
		                            <th scope="col">전철/지하철</th>
		                            <th scope="col">도보 및 기타</th>
		                            <th scope="col">합계</th>
		                        </tr>
							</thead>
							<tbody>
								<tr class="tr_total">
									<td rowspan="2">23.12.21</td>
									<td>합계(대)</td>
									<td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>24</td>
								</tr>							
		                        <tr>
		                        	<td>07:00 ~ 07:15</td>
		                            <td>10</td>
		                            <td>2</td>
		                            <td>16</td>
		                            <td>20</td>
		                            <td>10</td>
		                            <td>58</td>
		                            <td>10</td>
		                            <td>2</td>
		                            <td>16</td>
		                            <td>20</td>
		                            <td>10</td>
		                            <td>58</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">통근</div>
	                        	<div style="height:380px">
	                        		<canvas id="means_of_transportation_chart1"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">통학</div>
	                        	<div style="height:380px">
	                        		<canvas id="means_of_transportation_chart2"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
            
				<!-- tab6 -->
                <div class="search_container tab6 none">
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
	                            <col style="width:100px">
	                            <col style="width:100px">
	                            <col style="width:100px">
	                        </colgroup>
							<thead>
		                        <tr>
		                            <th scope="col" rowspan="2">날짜</th>
		                            <th scope="col" rowspan="2">시간</th>
		                            <th scope="col" colspan="6">통근</th>
		                            <th scope="col" colspan="6">통학</th>
		                        </tr>
		                        <tr>
		                            <th scope="col">승용차</th>
		                            <th scope="col">택시</th>
		                            <th scope="col">버스</th>
		                            <th scope="col">전철/지하철</th>
		                            <th scope="col">도보 및 기타</th>
		                            <th scope="col">합계</th>
		                            <th scope="col">승용차</th>
		                            <th scope="col">택시</th>
		                            <th scope="col">버스</th>
		                            <th scope="col">전철/지하철</th>
		                            <th scope="col">도보 및 기타</th>
		                            <th scope="col">합계</th>
		                        </tr>
							</thead>
							<tbody>
								<tr class="tr_total">
									<td rowspan="2">23.12.21</td>
									<td>합계(대)</td>
									<td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>2</td>
		                            <td>1</td>
		                            <td>24</td>
		                            <td>2</td>
		                            <td>5</td>
		                            <td>6</td>
		                            <td>4</td>
		                            <td>24</td>
								</tr>							
		                        <tr>
		                        	<td>07:00 ~ 07:15</td>
		                            <td>10</td>
		                            <td>2</td>
		                            <td>16</td>
		                            <td>20</td>
		                            <td>10</td>
		                            <td>58</td>
		                            <td>10</td>
		                            <td>2</td>
		                            <td>16</td>
		                            <td>20</td>
		                            <td>10</td>
		                            <td>58</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">통근</div>
	                        	<div style="height:380px">
	                        		<canvas id="avg_passenger_chart1"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">통학</div>
	                        	<div style="height:380px">
	                        		<canvas id="avg_passenger_chart2"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
            	
				<!-- tab7 -->
                <div class="search_container tab7 none">
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
		                            <th scope="col">평일</th>
		                            <th scope="col">주말</th>
		                            <th scope="col">평일</th>
		                            <th scope="col">주말</th>
		                            <th scope="col">평일</th>
		                            <th scope="col">주말</th>
		                            <th scope="col">평일</th>
		                            <th scope="col">주말</th>
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
	                        		<canvas id="parking_chart1"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">근린생활시설</div>
	                        	<div style="height:380px">
	                        		<canvas id="parking_chart2"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">공연집회</div>
	                        	<div style="height:380px">
	                        		<canvas id="parking_chart3"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">문화시설</div>
	                        	<div style="height:380px">
	                        		<canvas id="parking_chart4"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>            	

				<!-- tab8 -->
                <div class="search_container tab8 none">
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
	                        </colgroup>
							<thead>
		                        <tr>
		                            <th scope="col" rowspan="2">날짜</th>
		                            <th scope="col" rowspan="2">시간</th>
		                            <th scope="col">싱주/상근 유입</th>
		                            <th scope="col">방문/이용 유입</th>
		                            <th scope="col">유입합계</th>
		                            <th scope="col">상주/상근 유출</th>
		                            <th scope="col">방문/이용 유출</th>
		                            <th scope="col">유출합계</th>
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
		                        </tr>
		                        <tr>
		                        	<td>06:00 ~ 07:00</td>
		                            <td>40</td>
		                            <td>20</td>
		                            <td>60</td>
		                            <td>30</td>
		                            <td>50</td>
		                            <td>20</td>
		                        </tr>
		                        <tr>
		                        	<td>08:00 ~ 09:00</td>
		                            <td>40</td>
		                            <td>20</td>
		                            <td>60</td>
		                            <td>30</td>
		                            <td>50</td>
		                            <td>60</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart wd100">
	                        	<div class="tab_box_title left mb16">시간대별 유출입 통행</div>
	                        	<div style="height:380px">
	                        		<canvas id="inflow_outflow_chart"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
            	
				<!-- tab9 -->
                <div class="search_container tab9 none">
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
	                        </colgroup>
							<thead>
		                        <tr>
		                            <th scope="col" rowspan="2">날짜</th>
		                            <th scope="col" rowspan="2">시간</th>
		                            <th scope="col">통근유입</th>
		                            <th scope="col">통근유출</th>
		                            <th scope="col">통학유입</th>
		                            <th scope="col">통학유출</th>
		                            <th scope="col">기타유입</th>
		                            <th scope="col">기타유출</th>
		                            <th scope="col">방문유입</th>
		                            <th scope="col">방문유출</th>
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
		                            <td>40</td>
		                            <td>40</td>
		                        </tr>
		                        <tr>
		                        	<td>06:00 ~ 07:00</td>
		                            <td>40</td>
		                            <td>20</td>
		                            <td>60</td>
		                            <td>30</td>
		                            <td>50</td>
		                            <td>50</td>
		                            <td>20</td>
		                            <td>20</td>
		                        </tr>
		                        <tr>
		                        	<td>08:00 ~ 09:00</td>
		                            <td>40</td>
		                            <td>20</td>
		                            <td>60</td>
		                            <td>30</td>
		                            <td>50</td>
		                            <td>60</td>
		                            <td>60</td>
		                            <td>60</td>
		                        </tr>
	                        </tbody>
	                    </table>
	                </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart wd100">
	                        	<div class="tab_box_title left mb16">시간대별 통행</div>
	                        	<div style="height:380px">
	                        		<canvas id="passage_chart"></canvas>
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
	
	//selectbox show/hide
	$(document).ready(function() {
		$('#selectBox').change(function() {
			var result = $('#selectBox option:selected').val();
	    	if (result == '1') {
	      		$('.tab1').removeClass('none')
	    	} else {
	      		$('.tab1').addClass('none');
	    	}
	    	
	    	if (result == '2') {
	      		$('.tab2').removeClass('none')
	    	} else {
	      		$('.tab2').addClass('none');
	    	}
	    	
	    	if (result == '3') {
	      		$('.tab3').removeClass('none')
	    	} else {
	      		$('.tab3').addClass('none');
	    	}
	    	
	    	if (result == '4') {
	      		$('.tab4').removeClass('none')
	    	} else {
	      		$('.tab4').addClass('none');
	    	}
	    	
	    	if (result == '5') {
	      		$('.tab5').removeClass('none')
	    	} else {
	      		$('.tab5').addClass('none');
	    	}
	    	
	    	if (result == '6') {
	      		$('.tab6').removeClass('none')
	    	} else {
	      		$('.tab6').addClass('none');
	    	}
	    	
	    	if (result == '7') {
	      		$('.tab7').removeClass('none')
	    	} else {
	      		$('.tab7').addClass('none');
	    	}
	    	
	    	if (result == '8') {
	      		$('.tab8').removeClass('none')
	    	} else {
	      		$('.tab8').addClass('none');
	    	}
	    	
	    	if (result == '9') {
	      		$('.tab9').removeClass('none')
	    	} else {
	      		$('.tab9').addClass('none');
	    	}
	    	
	    	if (result == '10') {
	      		$('.tab10').removeClass('none')
	    	} else {
	      		$('.tab10').addClass('none');
	    	}
	  	}); 
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
   	
	    
	
   	// 용도별 교통수단 분담률 > 통근
	new GITSChart(GITSChartType.DOUGHNUT).init("means_of_transportation_chart1")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:[40, 60, 45, 50, 10],
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
   	
   	// 용도별 교통수단 분담률 > 통학
	new GITSChart(GITSChartType.DOUGHNUT).init("means_of_transportation_chart2")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:[10, 10, 50, 60, 20],
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();
   	
   	// 용도별 재차수단 > 통근
	new GITSChart(GITSChartType.DOUGHNUT).init("avg_passenger_chart1")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:[10, 10, 50, 60, 20],
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();	
   	
   	// 용도별 교통수단 분담률 > 통학
	new GITSChart(GITSChartType.DOUGHNUT).init("avg_passenger_chart2")
    .setDataSetLabel('승용차', '택시', '버스', '전철/지하철', '도보 및 기타')
    .setDataSet({
            label: ['승용차', '택시', '버스', '전철/지하철', '도보 및 기타'],
            data:[10, 10, 50, 60, 20],
            backgroundColor: ['#00BCB1', 'red',  'yellow', 'green', '#fff'],
            borderColor:'transparent'
    })
	.setLabelPadding(30)
	.draw();		

   	// 주차발생 > 주거용도
	new GITSChart(GITSChartType.BAR).init("parking_chart1")
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
  	
   	// 주차발생 > 근린시설
	new GITSChart(GITSChartType.BAR).init("parking_chart2")
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
   	
   	// 주차발생 > 공연집회
	new GITSChart(GITSChartType.BAR).init("parking_chart3")
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
   	
   	// 주차발생 > 문화시설
	new GITSChart(GITSChartType.BAR).init("parking_chart4")
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
   	
	// 시간대별 유출입 통행
    new GITSChart(GITSChartType.BAR).init("inflow_outflow_chart")
    .setDataSetLabel('00~01시','01~02시','02~03시','03~04시','04~05시','04~05시','05~06시','06~07시',
    		'07~08시','08~09시','09~10시','10~11시','11~12시','12~13시','13~14시','14~15시','15~16시',
    		'16~17시','17~18시','18~19시','19~20시','20~21시','21~22시','22~23시','23~24시')
    .setDataSet({
        label:'싱주/상근 유입',
        data: [3, 4, 2, 5, 0, 1, 3, 4, 5, 5, 3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 0, 1, 2, 3, 0],
        backgroundColor:'#00bcb1'
    }, {
        label:'방문/이용 유입',
        data: [3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 3, 4, 2, 5, 3, 1, 3, 4, 5, 5, 2, 1, 2, 3, 1],
        backgroundColor:'#ad49fb'
    }, {
        label:'상주/상근 유출',
        data: [3, 4, 2, 5, 2, 1, 3, 4, 5, 5, 3, 4, 2, 5, 0, 1, 3, 4, 5, 5, 3, 1, 2, 3, 3],
        backgroundColor:'#91de6c'
    }, {
        label:'방문/이용 유출',
        data: [3, 4, 2, 5, 4, 1, 3, 4, 5, 5, 3, 4, 2, 5, 6, 1, 3, 4, 5, 5, 1, 1, 2, 3, 0],
        backgroundColor:'#ff5454'
    })
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
	
	// 시간대별 통행
    new GITSChart(GITSChartType.BAR).init("passage_chart")
    .setDataSetLabel('00~01시','01~02시','02~03시','03~04시','04~05시','04~05시','05~06시','06~07시',
    		'07~08시','08~09시','09~10시','10~11시','11~12시','12~13시','13~14시','14~15시','15~16시',
    		'16~17시','17~18시','18~19시','19~20시','20~21시','21~22시','22~23시','23~24시')
    .setDataSet({
        label:'통근유입',
        data: [3, 4, 2, 5, 0, 1, 3, 4, 5, 5, 3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 0, 1, 2, 3, 0],
        backgroundColor:'#00bcb1'
    }, {
        label:'통근유츨',
        data: [3, 4, 2, 5, 1, 1, 3, 4, 5, 5, 3, 4, 2, 5, 3, 1, 3, 4, 5, 5, 2, 1, 2, 3, 1],
        backgroundColor:'#ad49fb'
    }, {
        label:'통학유입',
        data: [3, 4, 2, 5, 2, 1, 3, 4, 5, 5, 3, 4, 2, 5, 0, 1, 3, 4, 5, 5, 3, 1, 2, 3, 3],
        backgroundColor:'#91de6c'
    }, {
        label:'통학유출',
        data: [3, 4, 2, 5, 4, 1, 3, 4, 5, 5, 3, 4, 2, 5, 6, 1, 3, 4, 5, 5, 1, 1, 2, 3, 1],
        backgroundColor:'#ff5454'
    }, {
        label:'기타유입',
        data: [3, 4, 2, 5, 4, 1, 3, 4, 5, 5, 3, 4, 2, 5, 6, 1, 3, 4, 5, 5, 1, 1, 2, 3, 0],
        backgroundColor:'#65abdd'
    }, {
        label:'기타유출',
        data: [3, 4, 2, 5, 4, 1, 3, 4, 5, 5, 3, 4, 2, 5, 6, 1, 3, 4, 5, 5, 1, 1, 2, 3, 5],
        backgroundColor:'#c38686'
    }, {
        label:'방문유입',
        data: [3, 4, 2, 5, 4, 1, 3, 4, 5, 5, 3, 4, 2, 5, 6, 1, 3, 4, 5, 5, 1, 1, 2, 3, 4],
        backgroundColor:'#e983ce'
    }, {
        label:'방문유출',
        data: [3, 4, 2, 5, 4, 1, 3, 4, 5, 5, 3, 4, 2, 5, 6, 1, 3, 4, 5, 5, 1, 1, 2, 3, 2],
        backgroundColor:'#c9cb5a'
    })
    .setBarGridY(true)
    .setLabelPadding(20)
    .draw();
	
    </script>
