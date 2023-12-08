<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">도로 안전 위험 순위</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="2">교통사고 위험<br>지역 순위</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">도로 안전 위험 순위</div>
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
	                    <div class="tab_item_box" style="width:450px; height:300px;">
	                        <canvas id="tab3"></canvas>
	                    </div>
	                </div>
	            </div>
        	</div>
        	<div class="tab tab2 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">교통사고 위험 지역 순위</div>
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
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">항목</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">사고대상</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">사고원인</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">사고유형</label>
	                    </div>
	                    <div class="tab_item_box">
                            <div class="mt8" style="width:450px; height:300px;">
		                        <canvas id="tab4"></canvas>
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
	
	//tab3
    new GITSChart(GITSChartType.BAR).init("tab3")
    .setDataSetLabel('마장동','매교동','영화동','고읍동')
    .setDataSet({
            label: '도로형태',
            data:[300, 150, 200, 100],
            backgroundColor: '#00BCB1',
        },{
            label: '기상',
            data:[200, 120, 140, 80],
            backgroundColor: '#F90',
        },{
            label: '취약구간',
            data:[400, 80, 110, 110],
            backgroundColor: '#FF5454',
        },{
            label: '기타',
            data:[200, 120, 140, 40],
            backgroundColor: '#ACDC87',
    })
    .setTickStepX(200)
    .setAxis('y')
    .setBarGridX(true)
    .draw();
	
	//tab4
    new GITSChart(GITSChartType.BAR).init("tab4")
    .setDataSetLabel('마장동','매교동','영화동','고읍동')
    .setDataSet({
            label: '보험자 보호 의무 위반',
            data:[40, 60, 20, 100, 50, 10],
            backgroundColor: '#00BCB1',
        },{
            label: '교차로 통행방법 위반',
            data:[20, 40, 80, 50, 120, 40],
            backgroundColor: '#AD49FB',
        },{
            label: '안전거리 미확보',
            data:[10, 30, 60, 80, 60, 100],
            backgroundColor: '#91DE6C',
        },{
            label: '신호 위반',
            data:[30, 50, 10, 120, 80, 60],
            backgroundColor: '#FF5454',
        },{
            label: '중앙선 침범',
            data:[100, 10, 20, 40, 60, 10],
            backgroundColor: '#0075FF',
        },{
            label: '과속',
            data:[80, 20, 60, 80, 10, 50],
            backgroundColor: '#F90',
    })
    .setTickStepX(100)
    .setAxis('y')
    .setBarGridX(true)
    .draw();
</script>