<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <div class="main_container">
            <aside class="snb_container">
                <div class="snb_wrap">
                    <h3 class="side_title">교통 DB화 통계</h3>
                    <div class="side_txt">
                        교통 DB화 통계 정보를 <br>조회합니다.
                    </div>
                    <div class="side_btn">
                        <a href="traffic_dblist.html" class="on">교통총괄지표</a>
                        <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do">교통영향평가</a>
                    </div>
                </div>
            </aside>
            <section class="main_section">
                <h2 class="blind">통계 리포트</h2>
                <div class="contents_wrap">
                <form id="searchForm" method="get">
                    <div class="group2">
                        <div class="group_text2">주소 검색</div>
                        <div class="flex-center gap24">
                            <div>
                                <span class="group_btn_title">시군</span>
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
										<select class="selectBox selectTime" name="startTime" id="startTime"></select>
	                                    ~
	                                    <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" placeholder="날짜를 선택해주세요.">
										<select class="selectBox selectTime" name="endTime" id="endTime"></select>
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
	                        <div class="group2">
	                            <div class="group_text2">상세 설정</div>
	                            <div class="flex-center gap24">
	                                <div>
	                                    <span class="group_btn_title">분류</span>
	                                    <button type="button" class="is-dark-btn all_selector">전체</button>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">교차로 별</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">구간 별</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">시간대 별</label>
	                                    <label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">차종 별</label>
	                                </div>
	                            </div>
	                        </div>
                    	</div>
                    	<div class="group2_btn">
                        	<button type="button" class="is-darkgreen-btn" id="search_test">찾기</button>
                        	<input type="reset" class="is-dark-btn selected_reset" value="검색값 초기화">
                    	</div>
	               </form>
                </div>
                <div class="search_container">
				    <div class="search_head none">
				        <div class="search_number">
				            <span>"121534개"</span>의 검색결과를 찾았습니다.
				        </div>
				    </div>
                    <table class="mt16">
                        <colgroup>
                            <col style="width:15%">
                            <col style="width:17%">
                            <col style="width:10%">
                            <col style="width:12%">
                            <col style="width:10%">
                            <col style="width:10%">
                            <col style="width:10%">
                        </colgroup>
						<thead>
	                        <tr>
	                            <th scope="col">기간</th>
	                            <th scope="col">도로명</th>
	                            <th scope="col">방향</th>
	                            <th scope="col">교통량</th>
	                            <th scope="col">평균속도</th>
	                            <th scope="col" class="qustion_title">
		                            혼잡레벨
		                            <span class="question_img pointer">
		                            	<img src="${pageContext.request.contextPath}/statics/images/question_mark.png" alt="물음표">
		                            </span>
		                            <div class="question_box none">
		                            	<span class="question_box_title">혼잡레벨</span>
		                            	<ul class="question_list_box">
		                            		<li>1 : 혼잡강도 20이상</li>
		                            		<li>2 : 혼잡강도 40이상</li>
		                            		<li>3 : 혼잡강도 60이상</li>
		                            		<li>4 : 혼잡강도 80이상</li>
		                            		<li>5 : 혼잡강도 100이상</li>
		                            		<li>6 : 혼잡강도 120이상</li>
		                            		<li>7 : 혼잡강도 140이상</li>
		                            	</ul>
		                            </div>
	                            </th>
	                            <th scope="col">돌발발생</th>
	                        </tr>
						</thead>
						<tbody>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>1</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>2</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>4</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>5</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>1</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>5</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>2</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>2</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>3</td>
	                            <td>1,700건</td>
	                        </tr>
	                        <tr>
	                            <td>2023.12</td>
	                            <td>수원1로</td>
	                            <td>상행</td>
	                            <td>22,000</td>
	                            <td>70km</td>
	                            <td>4</td>
	                            <td>1,700건</td>
	                        </tr>
                        </tbody>
                    </table>
                    <div class="page-wrap">
                        <ul class="pagination">
                            <li><a href="#" class="first">처음 페이지</a></li>
                            <li><a href="#" class="arrow left"></a></li>
                            <li><a href="#" class="active num">1</a></li>
                            <li><a href="#" class="num">2</a></li>
                            <li><a href="#" class="num">3</a></li>
                            <li><a href="#" class="num">4</a></li>
                            <li><a href="#" class="num">5</a></li>
                            <li><a href="#" class="arrow right">></a></li>
                            <li><a href="#" class="last">마지막 페이지</a></li>
                        </ul>
                    </div>
                    <div class="table_chart_wrap">
	                    <div class="table_chart_list">
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">교통량</div>
	                        	<div style="height:380px">
	                        		<canvas id="traffic_chart1"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">평균속도</div>
	                        	<div style="height:380px">
	                        		<canvas id="traffic_chart2"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">혼잡레벨</div>
	                        	<div style="height:380px">
	                        		<canvas id="traffic_chart3"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">돌발발생</div>
	                        	<div style="height:380px">
	                        		<canvas id="traffic_chart4"></canvas>
	                        	</div>
	                        </div>
	                        <div class="chart">
	                        	<div class="tab_box_title left mb16">대기오염 배출량</div>
	                        	<div style="height:380px">
	                        		<canvas id="traffic_chart5"></canvas>
	                        	</div>
	                        </div>
	                     </div>
                     </div>
            	</div>
            </section> 
        </div>



	<script>
   		$('.question_img').mouseenter(function(){
   			$('.question_box').removeClass('none')
   		})
   		$('.question_img').mouseleave(function(){
   			$('.question_box').addClass('none')
   		})
   		
   	/* chart */
	   	// 교통량
	    new GITSChart(GITSChartType.BAR).init("traffic_chart1")
   		.setData({
	        labels: [
	            '부천시','성남시','안양시','남양주시','하남시'
	        ],
	        datasets: [{
	            data:[400, 450, 350, 600, 740],
	            backgroundColor: '#58edd2',
	            borderRadius:2,
	            borderWidth:8,
	            fill: false,
	        }]
    	})
	    .setTicksStep(100)
	    .setLabelDisplay(false)
	    .setBarGridY(true)
	    .setAxisStackedX(false)
	    .setTickColorY('#2e2e2e')
	    .draw();
   		
   		// 평균속도
   		new GITSChart(GITSChartType.LINE).init("traffic_chart2")
   		.setData({
	        labels: [
	            '부천시','성남시','안양시','남양주시','하남시'
	        ],
	        datasets: [{
	            data:[75, 50, 35, 40, 60],
	            backgroundColor: '#58edd2',
	            borderRadius:2,
	            borderWidth:3,
	            fill: false,
	            borderColor:'#58edd2',
	        }]
    	})
    	.setTicksStep(10)
    	.setLabelDisplay(false)
    	.setPoint(3)
    	.setTickColorY('#2e2e2e')
    	.draw();

	   	// 혼잡레벨
	    new GITSChart(GITSChartType.BAR).init("traffic_chart3")
   		.setData({
	        labels: [
	            '부천시','성남시','안양시','남양주시','하남시'
	        ],
	        datasets: [{
	            data:[2, 5, 3, 2, 3],
	            backgroundColor: '#58edd2',
	            borderRadius:2,
	            borderWidth:8,
	            fill: false,
	        }]
    	})
	    .setTicksStep(1)
	    .setLabelDisplay(false)
	    .setBarGridY(true)
	    .setAxisStackedX(false)
	    .setTickColorY('#2e2e2e')
	    .draw();
	   	
	    // 돌발발생
	    new GITSChart(GITSChartType.BAR).init("traffic_chart4")
	    .setDataSetLabel('부천시','성남시','남양주시','하남시')
	    .setDataSet({
	            label:'행사',
	            data: [3, 4, 2, 4],
	            backgroundColor:'#00bcb1'
	        },{
	            label:'기상',
	            data: [1, 2, 5, 6],
	            backgroundColor:'#ad49fb'
	        },{
	            label:'통제',
	            data: [4, 3, 1, 6],
	            backgroundColor:'#91de6c'
	     })
	    .setTicksStep(2)
	    .setLabelDisplay(true)
	    .setBarGridY(true)
	    .setLabelPadding(16)
	    .draw();
	    
	    // 대기오염 배출량
	    new GITSChart(GITSChartType.BAR).init("traffic_chart5")
	    .setDataSetLabel('부천시','성남시','남양주시','하남시')
	    .setDataSet({
	            label:'이산화질소',
	            data: [1, 3, 1, 3],
	            backgroundColor:'#00bcb1'
	        },{
	            label:'오존',
	            data: [1, 4, 1, 2],
	            backgroundColor:'#ad49fb'
	        },{
	            label:'일산화탄소',
	            data: [4, 3, 1, 6],
	            backgroundColor:'#91de6c'
	     })
	    .setTicksStep(2)
	    .setLabelDisplay(true)
	    .setBarGridY(true)
	    .setLabelPadding(16)
	    .draw();
	    
	    
		/* 검색결과 */
// 		id name 바꿔서 사용하세요~
		$('#search_test').on('click', function(){
			$('.search_head').removeClass('none')
		})
    </script>
