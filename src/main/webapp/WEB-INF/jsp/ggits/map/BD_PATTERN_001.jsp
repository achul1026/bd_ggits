<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">교통량 순위</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="2">평균 속도 순위</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="3">정체 구간 순위</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">교통량 순위 보기</div>
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
			                                <col style="width:26%">
			                                <col style="width:30%">
			                            </colgroup>
			                            <thead>
			                                <tr>
			                                    <th scope="col">순위</th>
			                                    <th scope="col">지역</th>
			                                    <th scope="col">교통량</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                <tr>
			                                    <td>1</td>
			                                    <td>수원시</td>
			                                    <td>156556</td>
			                                </tr>
			                                <tr>
			                                    <td>2</td>
			                                    <td>평택시</td>
			                                    <td>44561</td>
			                                </tr>
			                                <tr>
			                                    <td>3</td>
			                                    <td>안양시</td>
			                                    <td>44005</td>
			                                </tr>
			                                <tr>
			                                    <td>4</td>
			                                    <td>부천시</td>
			                                    <td>40645</td>
			                                </tr>
			                                <tr>
			                                    <td>5</td>
			                                    <td>시흥시</td>
			                                    <td>30546</td>
			                                </tr>
			                            </tbody>
			                         </table>
			                	</div>
		                	</div>

		            	</div>
	            	</div>
	            </div>
        	</div>
        	<div class="tab tab2 tab-none">
  				<div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">평균 속도 순위 보기</div>
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
	                    <div class="tab_item_box flex-center list_speend_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn radius is-darkgreen-btn inpd" data-speend="1"><input type="checkbox" class="none" checked="checked">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-speend="2"><input type="checkbox" class="none">표</label>
	                    </div>
	                    <div class="list_speend_tab_area">
		                    <div class="tab_item_box list_speend_tab1" style="width:400px;">
		                        <canvas id="tab2"></canvas>
		                    </div>		                    	
		                    <div class="tab_item_box list_speend_tab2 none">
		                    	<div class="bigdata_table">
			                        <table>
			                            <colgroup>
			                                <col style="width:10%">
			                                <col style="width:30%">
			                                <col style="width:30%">
			                                <col style="width:30%">
			                            </colgroup>
			                            <thead>
			                                <tr>
			                                    <th scope="col">순위</th>
			                                    <th scope="col">도로명</th>
			                                    <th scope="col">도로 주소</th>
			                                    <th scope="col">평균 속도<br>전체 | 첨두 | 비첨두</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>97 | 78 | 100km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>2</td>
			                                    <td>도로G</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>97 | 78 | 100km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>3</td>
			                                    <td>도로C</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>97 | 78 | 100km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>4</td>
			                                    <td>도로B</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>50 | 30 | 100km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>5</td>
			                                    <td>도로H</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>100 | 68 | 100km/h</td>
			                                </tr>
			                            </tbody>
			                         </table>
			                	</div>
		                    </div>
						</div>		                    
	                </div>
	            </div>
        	</div>
        	<div class="tab tab3 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">정체 구간 순위 보기</div>
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
	                    <div class="tab_item_box flex-center list_confusion_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd" data-confusion="1"><input type="checkbox" class="none" checked="checked">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-confusion="2"><input type="checkbox" class="none">표</label>
	                    </div>
	                    <div class="list_confusion_tab_area">
		                    <div class="tab_item_box list_confusion_tab1" style="width:400px;">
		                        <canvas id="tab3"></canvas>
		                    </div>		            	                    
		                    <div class="tab_item_box list_confusion_tab2 none">
		                    	<div class="bigdata_table" style="width:30rem;">
			                        <table>
			                            <colgroup>
			                                <col style="width:10%">
			                                <col style="width:26%">
			                                <col style="width:30%">
			                                <col style="width:34%">
			                            </colgroup>
			                            <thead>
			                                <tr>
			                                    <th scope="col">순위</th>
			                                    <th scope="col">도로명</th>
			                                    <th scope="col">도로 주소</th>
			                                    <th scope="col">혼잡 횟수 | 평균 혼잡강도 | 속도</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>97 | 7845 | 100km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>2</td>
			                                    <td>도로G</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>97 | 7866 | 90km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>3</td>
			                                    <td>도로C</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>97 | 7841 | 80km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>4</td>
			                                    <td>도로B</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>50 | 3065 | 50km/h</td>
			                                </tr>
			                                <tr>
			                                    <td>5</td>
			                                    <td>도로H</td>
			                                    <td>경기도 수원시 화서동</td>
			                                    <td>100 | 6801 | 100km/h</td>
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
    .setData({
        labels: [
            '부천시','성남시','용인시','고양시','수원시'
        ],
        datasets: [{
            data:[300, 150, 200, 400, 460],
            backgroundColor: ['#FF2828','#F90','#FED501','#ACDC87','#3CBC00'],
            borderRadius:2,
            borderWidth:3,
            fill: false,
        }]
    })	
    .setTicksStep(200)
    .setLabelDisplay(false)
    .setAxis('y')
    .setBarGridY(false)
    .setBarGridX(true)
    .draw();
	
	//tab2
    new GITSChart(GITSChartType.BAR).init("tab2")
    .setData({
        labels: [
            '부천시','성남시','용인시','고양시','수원시'
        ],
        datasets: [{
            data:[300, 150, 200, 400, 460],
            backgroundColor: ['#FF2828','#F90','#FED501','#ACDC87','#3CBC00'],
            borderRadius:2,
            borderWidth:3,
            fill: false,
        }]
    })	
    .setTicksStep(200)
    .setLabelDisplay(false)
    .setAxis('y')
    .setBarGridY(false)
    .setBarGridX(true)
    .draw();
	
	//tab3
    new GITSChart(GITSChartType.BAR).init("tab3")
    .setData({
        labels: [
            '부천시','성남시','용인시','고양시','수원시'
        ],
        datasets: [{
            data:[300, 150, 200, 400, 460],
            backgroundColor: ['#FF2828','#F90','#FED501','#ACDC87','#3CBC00'],
            borderRadius:2,
            borderWidth:3,
            fill: false,
        }]
    })	
    .setTicksStep(200)
    .setLabelDisplay(false)
    .setAxis('y')
    .setBarGridY(false)
    .setBarGridX(true)
    .draw();
	
	//교통량 순위보기 표/그래프 
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
	
	//평균속도 순위보기 표/그래프 
    $(".list_speend_tab_button label").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-speend"); i++){
                if($(this).attr("data-speend") == i){
                    $(this).closest(".result_item").find(".list_speend_tab_area").children(".list_speend_tab"+i).removeClass('none');
                    $(this).closest(".result_item").find(".list_speend_tab_area").children(".list_speend_tab"+i).siblings().addClass('none');
                }
            }
        });
    });
	
	//정체구간 순위보기 표/그래프 
    $(".list_confusion_tab_button label").each(function() {
        $(this).click(function(){
            for( var i = 1;  i <= $(this).parent().children().last().attr("data-confusion"); i++){
                if($(this).attr("data-confusion") == i){
                    $(this).closest(".result_item").find(".list_confusion_tab_area").children(".list_confusion_tab"+i).removeClass('none');
                    $(this).closest(".result_item").find(".list_confusion_tab_area").children(".list_confusion_tab"+i).siblings().addClass('none');
                }
            }
        });
    });	
	
	
</script>