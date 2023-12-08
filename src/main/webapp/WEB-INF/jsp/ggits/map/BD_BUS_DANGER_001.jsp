<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">대중교통 안전 운행<br>위험 빈도</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">대중교통 안전 운행 위험 빈도</div>
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
		                    <div class="tab_item_box list_tab1" style="width:400px;">
		                        <canvas id="tab1"></canvas>
		                    </div>
		                    <div class="tab_item_box list_tab2 none">
		                    	<div class="bigdata_table">
			                        <table>
			                            <colgroup>
			                                <col style="width:10%">
			                                <col style="width:50%">
			                                <col style="width:40%">
			                            </colgroup>
			                            <thead>
			                                <tr>
			                                    <th scope="col">순위</th>
			                                    <th scope="col">지역명</th>
			                                    <th scope="col">빈도 수</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>445</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>445</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>445</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>445</td>
			                                </tr>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>445</td>
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
	
	//tab1
    new GITSChart(GITSChartType.BAR).init("tab1")
    .setDataSetLabel('부천시', '성남시', '용인시', '고양시', '수원시')
    .setDataSet({
        label:'위험빈도',
        data:[40, 60, 80, 40, 100],
        backgroundColor:'#FF5454'
    })
    .setTickStepX(20)
    .setAxis('y')
    .setBarGridX(true)
    .setLabelDisplay(false)
    .draw();	
	
	//대중교통 위험빈도 표/그래프 
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