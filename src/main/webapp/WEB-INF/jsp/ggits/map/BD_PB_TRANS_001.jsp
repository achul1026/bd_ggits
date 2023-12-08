<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">권역별 대중교통<br>이용현황 순위</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="2">권역별 대중교통<br>환승현황 순위</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">권역별 대중교통 이용현황 순위</div>
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
						<div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">시군</h5>
	                        <select class="selectBox radius selectbox_width">
	                            <option value="" selected="selected">전체</option>
	                            <option value="option2">테스트</option>
	                            <option value="option3">테스트</option>
	                            <option value="option4">테스트</option>
	                        </select>
	                    </div>                     
						<div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">유형</h5>
	                        <select class="selectBox radius selectbox_width">
	                            <option value="" selected="selected">광역버스</option>
	                            <option value="option2">테스트</option>
	                            <option value="option3">테스트</option>
	                            <option value="option4">테스트</option>
	                        </select>
	                    </div>                     
                        <div class="tab_item_box" style="width:400px; height:300px;">
                            <canvas id="tab1"></canvas>
                        </div>	                	
	                </div>
	            </div>
        	</div>
        	<div class="tab tab2 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">권역별 대중교통 환승현황 순위</div>
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
						<div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">시군</h5>
	                        <select class="selectBox radius selectbox_width">
	                            <option value="" selected="selected">전체</option>
	                            <option value="option2">테스트</option>
	                            <option value="option3">테스트</option>
	                            <option value="option4">테스트</option>
	                        </select>
	                    </div>                     
						<div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">유형</h5>
	                        <select class="selectBox radius selectbox_width">
	                            <option value="" selected="selected">광역버스</option>
	                            <option value="option2">테스트</option>
	                            <option value="option3">테스트</option>
	                            <option value="option4">테스트</option>
	                        </select>
	                    </div>                  
                        <div class="tab_item_box" style="width:400px; height:300px;">
                            <canvas id="tab2"></canvas>
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
    new GITSChart(GITSChartType.BAR).init("tab1")
    .setDataSetLabel('4240', '1300', '3400', '6000', '2300')
    .setDataSet({
        label:'이용현황',
        data:[600, 400, 500, 700, 300],
        backgroundColor:'#FF5454'
    })
    .setTickStepX(100)
    .setAxis('y')
    .setBarGridX(true)
    .setLabelDisplay(false)
    .draw();
	
    //tab2
    new GITSChart(GITSChartType.BAR).init("tab2")
    .setDataSetLabel('4240', '1300', '3400', '6000', '2300')
    .setDataSet({
        label:'환승현황',
        data:[80, 100, 120, 160, 60],
        backgroundColor:'#FF5454'
    })
    .setTickStepX(50)
    .setAxis('y')
    .setBarGridX(true)
    .setLabelDisplay(false)
    .draw();
</script>