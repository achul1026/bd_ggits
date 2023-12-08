<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">교차로 일주일<br>교통량 분석</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="2">교차로 교통량 순위</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="3">사고예측 지역<br>순위</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">교차로 일주일 교통량 변화보기</div>
						<div class="tab_box_close">
							<div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
								<span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
							</div>
							<button><img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="tab_popup_close"></button>
						</div>
					</div>	            
	                <div class="result_item">
	                	<div>
	                		<button type="button" class="prev_text mb8 mt8"><span class="prev_arrow">←</span> 이전</button>
	                	</div>	           
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">교차로명</h5>
	                        <select class="selectBox radius selectbox_width">
	                            <option value="" selected="selected">교차로 선택</option>
	                            <option value="option2">수원시 사거리 교차로</option>
	                            <option value="option3">테스트</option>
	                            <option value="option4">테스트</option>
	                        </select>
	                    </div>
	                    <div class="tab_item_box">
                            <div class="mt8">
		                        <canvas id="tab1"></canvas>
                            </div>	   
	                    </div>
	                </div>
	            </div>
        	</div>
        	<div class="tab tab2 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">교차로 교통량 순위</div>
						<div class="tab_box_close">
							<div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
								<span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
							</div>
							<button><img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="tab_popup_close"></button>
						</div>
					</div>	            
	                <div class="result_item">
	                	<div>
	                		<button type="button" class="prev_text mb8 mt8"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
                        <div class="tab_item_box flex-center">
                            <h5 class="tab_item_title">일자</h5>
							<input type="text" class="date_picker input_same mr8 input_picker" placeholder="날짜를 선택해주세요.">
                        </div>
                        <div class="tab_item_box" style="width:400px; height:300px;">
                            <canvas id="tab2"></canvas>
                        </div>	                	
	                </div>
	            </div>
        	</div>
        	<div class="tab tab3 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">사고예측 지역 순위</div>
						<div class="tab_box_close">
							<div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
								<span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
							</div>
							<button><img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="tab_popup_close"></button>
						</div>
					</div>	            
	                <div class="result_item">
	                	<div>
	                		<button type="button" class="prev_text mb8 mt8"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">시군 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">읍면동 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">교차로 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">도로 별</label>
	                    </div>
	                    <div class="tab_item_box flex-center list_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd" data-tab="1"><input type="checkbox" class="none" checked="checked">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-tab="2"><input type="checkbox" class="none">표</label>
	                    </div>
	                    <div class="list_tab_area">
		                    <div class="tab_item_box list_tab1">
	                            <div class="mt8" style="width:400px;">
			                        <canvas id="tab4"></canvas>
	                            </div>	                    
		                    </div>
		                    <div class="tab_item_box list_tab2 none">
		                    	<div class="bigdata_table">
			                        <table>
			                            <colgroup>
			                                <col style="width:10%">
			                                <col style="width:30%">
			                                <col style="width:15%">
			                                <col style="width:15%">
			                                <col style="width:15%">
			                                <col style="width:15%">
			                            </colgroup>
			                            <thead>
			                                <tr>
			                                    <th scope="col">순위</th>
			                                    <th scope="col">지역명</th>
			                                    <th scope="col" class="center">안전</th>
			                                    <th scope="col" class="center">주의</th>
			                                    <th scope="col" class="center">위험</th>
			                                    <th scope="col" class="center">심각</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td class="center">12</td>
			                                    <td class="center">34</td>
			                                    <td class="center">56</td>
			                                    <td class="center">78</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td class="center">12</td>
			                                    <td class="center">34</td>
			                                    <td class="center">56</td>
			                                    <td class="center">78</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td class="center">12</td>
			                                    <td class="center">34</td>
			                                    <td class="center">56</td>
			                                    <td class="center">78</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td class="center">12</td>
			                                    <td class="center">34</td>
			                                    <td class="center">56</td>
			                                    <td class="center">78</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td class="center">12</td>
			                                    <td class="center">34</td>
			                                    <td class="center">56</td>
			                                    <td class="center">78</td>
			                                </tr>
			                            </tbody>
			                         </table>
			                	</div>
			            	</div>    	 	                    
	                    </div>
	                </div>
	            </div>
        	</div>
        </div>
    </div>
</div>

<script>
	bigdataTabMenu();
	bigdataPopupPrev();
	gisCheckInit();
	bigdataPopupClose();
	datePickerInit();
	
	//tab1
    new GITSChart(GITSChartType.LINE).init("tab1")
    .setDataSetLabel('5월12일','5월13일','5월14일','5월15일', '5월16일', '5월17일')
    .setDataSet({
        label:'교통량',
        data: [40, 15, 25, 42, 127, 80],
        backgroundColor:'#58edd2',
        borderWidth:3,
        borderColor:'#58edd2',
        fill: false
    })
    .setTicksStep(30)
    .setLabelDisplay(false)
    .draw();
	
	//tab2
    new GITSChart(GITSChartType.BAR).init("tab2")
    .setDataSetLabel('교차로A', '교차로B', '교차로C', '교차로D')
    .setDataSet({
        label: '교통량',
        data:[40, 20, 30, 60],
        backgroundColor: '#00BCB1',
        borderWidth:2
    })
    .setTickStepX(10)
    .setAxis('y')
    .setBarGridX(true)
    .setLabelDisplay(false)
    .draw();
	
	//tab4
    new GITSChart(GITSChartType.BAR).init("tab4")
    .setDataSetLabel('부천시', '성남시', '용인시', '고양시', '수원시')
    .setDataSet({
        label:'안전',
        data:[40, 50, 60, 30, 70],
        backgroundColor:'#3CBC00',
    },{
        label:'주의',
        data:[30, 40, 80, 10, 25],
        backgroundColor:'#FFD439',
    },{
        label:'위험',
        data:[70, 10, 20, 60, 10],
        backgroundColor:'#F90',
    },{
        label:'심각',
        data:[10, 20, 10, 10, 20],
        backgroundColor:'#FF2828',
    })
    .setTickStepX(50)
    .setAxis('y')
    .setBarGridX(true)
    .setLabelDisplay(false)
    .draw();
	
	
	//사고예측 지역 순위보기 표/그래프 
    $(".list_tab_button label").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-tab"); i++){
                if($(this).attr("data-tab") == i){
                    $(this).closest(".result_item").find(".list_tab_area").children(".list_tab"+i).removeClass('none');
                    $(this).closest(".result_item").find(".list_tab_area").children(".list_tab"+i).siblings().addClass('none');
                }
            }
        });
    });	
</script>