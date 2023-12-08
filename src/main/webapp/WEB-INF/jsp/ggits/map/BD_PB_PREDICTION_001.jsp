<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">유동인구 분류</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="2">버스노선 최적화<br>전후비교</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">유동인구 분류</div>
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
	                    </div>
	                    <div class="tab_item_box"  style="width:450px; height:300px;">
	                        <canvas id="tab1"></canvas>
	                    </div>
	                </div>
	            </div>
        	</div>
        	<div class="tab tab2 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">버스노선 최적화 전후비교</div>
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
	                        <h5 class="tab_item_title">항목</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">승객 예상 수요</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">운행 거리/시간</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">가중치</label>
	                    </div>
	                    <div class="tab_item_box"  style="width:450px; height:300px;">
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
	
    //tab1
    new GITSChart(GITSChartType.BAR).init("tab1")
    .setDataSetLabel('파장동', '마교동', '염화동', '길동')
    .setDataSet({
        label:'경기',
        data:[40, 60, 80, 40],
        backgroundColor:'#00BCB1'
    },{
        label:'인천',
        data:[20, 40, 60, 10],
        backgroundColor:'#AD49FB'
    },{
        label:'서울',
        data:[30, 20, 20, 40],
        backgroundColor:'#F00'
    },{
        label:'강원',
        data:[20, 20, 10, 30],
        backgroundColor:'#F90'
    },{
        label:'충청',
        data:[40, 30, 10, 10],
        backgroundColor:'#0075FF'
    },{
        label:'전남',
        data:[10, 10, 15, 20],
        backgroundColor:'#91DE6C'
    }
    )
    .setTickStepX(10)
    .setAxis('y')
    .setBarGridX(true)
    .draw();

    //tab2
    new GITSChart(GITSChartType.BAR).init("tab2")
    .setDataSetLabel('기존노선', '최적화 노선1', '최적화 노선2')
    .setDataSet({
        label:'운행 거리(km)',
        data:[100, 150, 120],
        backgroundColor:'#00BCB1'
    },{
        label:'운행 시간(분)',
        data:[40, 60, 50],
        backgroundColor:'#AD49FB'
    })
    .setTickStepX(40)
    .setAxis('y')
    .setBarGridX(true)
    .setAxisStackedX(false)
    .setAxisStackedY(false)
    .draw();	
</script>