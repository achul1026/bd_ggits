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
                <a href="${pageContext.request.contextPath}/statistics/traffic/database/impact/list.do"  class="on" onclick="startLoading()">교통영향평가</a>
            </div>
        </div>
    </aside>
    <section class="main_section">
        <h2 class="blind">교통 DB화 통계</h2>
        <div class="contents_wrap mt0">
            <div class="group2">
                <div class="group_text2">검색</div>
                <div class="btn_search_wrap">
                    <ul>
                    	<li>
                    		분류 : 
                    		<input type="hidden" id="selectedType" value="<c:out value='${type}'/>">
	                        <select class="selectBox" id="selectBox">
	                            <option value="crossroad_by_traffic_weekday">평일 교차로별 교통량</option>
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
                    	</li>
						<li>
							지역 :  <select class="selectBox">
		                            <option>팔달구</option>
		                            <option>test1</option>
		                            <option>test2</option>
		                            <option>test3</option>
		                            <option>test4</option>
		                            <option>test5</option>
		                        </select>
						</li>
						<li>
							교차로명 :  <select class="selectBox">
			                            <option>팔달사거리 ~ 팔달삼거리</option>
			                            <option>test1</option>
			                            <option>test2</option>
			                            <option>test3</option>
			                            <option>test4</option>
			                            <option>test5</option>
			                        </select>
						</li>
                    </ul>
                </div>
                <div class="search_detail_btn">
                    상세 검색 <i></i>
                </div>
            </div>
           	<form>
	            <div class="search_detail_wrap">
	            		<input type="hidden" id="ipcssMngNo" value="<c:out value='${ipcssMngNo}'/>">
	                 <div class="group2">
	                     <div class="group_text2">기간 설정</div>
	                     <div class="btn_search_wrap">
	                         <ul class="calendar">
	                         	<li>
	                         		 <input type="text" class="date_picker input_same" placeholder="날짜를 선택해주세요." autocomplete="off">
	                         	</li>
	                            <li>
	                         		 ~
	                         	</li>
	                         	<li>
	                         		<input type="text" class="end_date_picker input_same" placeholder="날짜를 선택해주세요." autocomplete="off">
	                         	</li>
	                         </ul>
	                     </div>
	                 </div>
	                 <div class="group2">
	                     <div class="group_text2">요일 설정</div>
	                     <div class="btn_search_wrap">
	                         <ul>
	                         	<li>
	                         		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">일</label>
	                         	</li>
	                         	<li>
	                         		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">월</label>
	                         	</li>
	                         	<li>
	                         		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">화</label>
	                         	</li>
	                         	<li>
	                         		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">수</label>
	                         	</li>
	                         	<li>
	                         		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">목</label>
	                         	</li>
	                         	<li>
	                         		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">금</label>
	                         	</li>
	                         	<li>
	                         		<label class="group_btn_item is-dark-btn"><input type="checkbox" class="none">토</label>
	                         	</li>
	                         </ul>
	                     </div>
	                 </div>	                        
	            </div>
	            <div class="btn_search_wrap btn_search_wrap_center">
					<ul>
               			<li>
               				<button type="button" class="is-darkgreen-btn" id="searchBtn" >찾기</button>
               			</li>
               			<li>
               				<input type="button" class="is-dark-btn selected_reset" value="검색값 초기화">
               			</li>
               		</ul>	                
	            </div>
           </form>
        </div>
<!-- tab1 -->
        <div class="search_container tab1">
        	<div class="flex-between mt16">
        		<div class="search_head">
					<div class="search_number">
						<span id="totalCnt"></span>개의 검색결과를 찾았습니다.
					</div>
				</div>
				<c:if test="${authCd eq 'AUC000'}">
		            <div class="btn_search_wrap">
		            	<ul>
		            		<li>
		            			<button type="button" class="is-darkgreen-btn">일괄 다운로드</button>
		            		</li>
		            		<li>
		            			<button type="button" class="is-darkgreen-btn">수정하기</button>
		            		</li>
		            	</ul>
		            </div>
				</c:if>
        	</div>
        	<div class="search_table_width">
        		<!-- 전체 -->
        		<div class="flex">
	        		<!-- left -->
					<div>
						<div class="flex">
							<div class="table_middle table_title">번호</div>
							<div class="table_middle table_title">교차로명칭</div>
						</div>
						<div class="flex" style="height:55.549rem;">
							<div class="table_middle table_txt table_line">1</div>
							<div class="table_middle table_txt table_line">풍무사거리</div>
						</div>
					</div>
					<!-- right -->
					<div>
						<div>
							<div class="row1 flex">
								<div class="flex">
									<div class="table_middle table_title">교차로방향</div>
									
									<!-- 교차로명 숫자 -->
									<div class="flex">
										<div class="table_big table_title">1</div>
										<div class="table_big table_title">2</div>
									</div>
								</div>
							</div>
							<div class="row2 flex">
								<div class="table_middle table_title table_line">시간대</div>
								
								<!-- 타입 -->
								<div class="flex">
									<div class="table_small table_title table_line">승용차</div>
									<div class="flex-column">
										<div class="table_column200 table_title">버스</div>
										<ul class="flex">
											<li class="table_small table_title">소형</li>
											<li class="table_small table_title">대형</li>
										</ul>
									</div>
									<div class="flex-column">
										<div class="table_column300 table_title">화물</div>
										<ul class="flex">
											<li class="table_small table_title">소형</li>
											<li class="table_small table_title">중형</li>
											<li class="table_small table_title">대형</li>
										</ul>
									</div>
									<div class="table_small table_title table_line">합계(대)</div>
								</div>
								
								<div class="flex">
									<div class="table_small table_title table_line">승용차</div>
									<div class="flex-column">
										<div class="table_column200 table_title">버스</div>
										<ul class="flex">
											<li class="table_small table_title">소형</li>
											<li class="table_small table_title">대형</li>
										</ul>
									</div>
									<div class="flex-column">
										<div class="table_column300 table_title">화물</div>
										<ul class="flex">
											<li class="table_small table_title">소형</li>
											<li class="table_small table_title">중형</li>
											<li class="table_small table_title">대형</li>
										</ul>
									</div>
									<div class="table_small table_title table_line">합계(대)</div>
								</div>
							</div>
							<div class="row_value flex max-content">
								<div class="table_middle table_txt">07:00~07:15</div>
								
								<ul class="flex">
									<!--  -->
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
									
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
								</ul>
							</div>
							<div class="row_value flex max-content">
								<div class="table_middle table_txt">07:15~07:30</div>
								
								<ul class="flex">
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
									
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
								</ul>
							</div>
							
							<div class="row_value flex max-content">
								<div class="table_middle table_txt">07:30~07:45</div>
								
								<ul class="flex">
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
								</ul>
							</div>
							
							<div class="row_value flex max-content">
								<div class="table_middle table_txt">07:45~08:00</div>
								
								<ul class="flex">
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
								</ul>
							</div>
							<div class="row_value flex max-content">
								<div class="table_middle table_txt">합계</div>
								
								<ul class="flex">
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
									<li class="table_small table_txt">10</li>
									<li class="table_small table_txt">2</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">1</li>
									<li class="table_small table_txt">0</li>
									<li class="table_small table_txt">14</li>
								</ul>
							</div>
						</div>

					</div>
        		</div>
			</div>
			
			
			
			
			
             <div class="table_chart_wrap">
              <div class="table_chart_list">
                 <div class="chart chart_height">
                  	<div class="tab_box_title left mb16">교차로 방향 (동)</div>
                 	<div id="chart1"></div>
                 </div>
                 <div class="chart chart_height">
                 	<div class="tab_box_title left mb16">교차로 방향 (서)</div>
               		<div id="chart2"></div>
                 </div>
              </div>
             </div>
    	</div>    	            	
    </section> 
</div>
<script>
	var dataTotalCnt = '<c:out value="${paging.totalCount}"/>';
	$("#totalCnt").text(numberComma(dataTotalCnt))
	
	/* 검색결과 */
	$('#searchBtn').on('click', function(){
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
	
 	$(function(){
			var data = [{
	            type: "sunburst",
	 				ids: [
	                    "root",
	                    "am", "pm",
	                    "am00", "am06", "pm12", "pm18",
	                    "am00Child1", "am00Child2", "am00Child3", "am00Child4", "am00Child5", "am00Child6", 
	                    "am06Child1", "am06Child2", "am06Child3", "am06Child4", "am06Child5", "am06Child6", 
	                    "pm12Child1", "pm12Child2", "pm12Child3", "pm12Child4", "pm12Child5", "pm12Child6", 
	                    "pm18Child1", "pm18Child2", "pm18Child3", "pm18Child4", "pm18Child5", "pm18Child6", 
					],
					labels:["", "오전", "오후", "06:00~12:00", "00:00~06:00", "12:00~18:00", "18:00~24:00",
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물", 
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물",
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물",
					          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물"
					         ],
					parents:["", "root", "root", "am", "am", "pm", "pm",
	                            "am00", "am00", "am00", "am00", "am00", "am00", 
	                            "am06", "am06", "am06", "am06", "am06", "am06", 
	                            "pm12", "pm12", "pm12", "pm12", "pm12", "pm12", 
	                            "pm18", "pm18", "pm18", "pm18", "pm18", "pm18", 
					          ],
					values: [ 
	                            120,
	                            60, 60,
	                            30, 30, 30, 30, 
	                            5, 5, 5, 5, 5, 5,
	                            5, 5, 5, 5, 5, 5,
	                            5, 5, 5, 5, 5, 5,
	                            5, 5, 5, 5, 5, 5
	                    ],
					leaf: {"opacity": 0.9},
					marker: {"line": {"width": 2}},
	                branchvalues: 'total',
				}];
		
				var layout = {
				  margin: {"l": 0, "r": 0, "b": 0, "t": 5}
				};

	            Plotly.newPlot('chart1', data, layout, {showSendToCloud: true, displayModeBar:false})
	            myPlot = document.getElementById("chart1");	
			
	    		var data2 = [{
	                type: "sunburst",
	     				ids: [
	                        "root",
	                        "am", "pm",
	                        "am00", "am06", "pm12", "pm18",
	                        "am00Child1", "am00Child2", "am00Child3", "am00Child4", "am00Child5", "am00Child6", 
	                        "am06Child1", "am06Child2", "am06Child3", "am06Child4", "am06Child5", "am06Child6", 
	                        "pm12Child1", "pm12Child2", "pm12Child3", "pm12Child4", "pm12Child5", "pm12Child6", 
	                        "pm18Child1", "pm18Child2", "pm18Child3", "pm18Child4", "pm18Child5", "pm18Child6", 
	    				],
	    				labels:["", "오전", "오후", "06:00~12:00", "00:00~06:00", "12:00~18:00", "18:00~24:00",
	    				          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물", 
	    				          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물",
	    				          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물",
	    				          "승용차", "소형버스", "대형버스", "소형화물", "중형화물", "대형화물"
	    				         ],
	    				parents:["", "root", "root", "am", "am", "pm", "pm",
	                                "am00", "am00", "am00", "am00", "am00", "am00", 
	                                "am06", "am06", "am06", "am06", "am06", "am06", 
	                                "pm12", "pm12", "pm12", "pm12", "pm12", "pm12", 
	                                "pm18", "pm18", "pm18", "pm18", "pm18", "pm18", 
	    				          ],
	    				values: [ 
	                                120,
	                                60, 60,
	                                30, 30, 30, 30, 
	                                5, 5, 5, 5, 5, 5,
	                                5, 5, 5, 5, 5, 5,
	                                5, 5, 5, 5, 5, 5,
	                                5, 5, 5, 5, 5, 5
	                        ],
	    				leaf: {"opacity": 0.9},
	    				marker: {"line": {"width": 2}},
	                    branchvalues: 'total',
	    			}];
	
			var layout2 = {
			  "margin": {"l": 0, "r": 0, "b": 0, "t": 5},
			}
	
			Plotly.newPlot('chart2', data2, layout2, {showSendToCloud: true, displayModeBar:false})
	
			myPlot = document.getElementById("chart2");
	})

</script>
