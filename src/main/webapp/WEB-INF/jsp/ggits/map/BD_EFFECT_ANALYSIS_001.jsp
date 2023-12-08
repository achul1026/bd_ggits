<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">정체 개선 순위</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="2">주요 긴급차량<br>이동 도로 분석</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">정체 개선 순위 보기</div>
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
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">항목</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">혼잡강도</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">교통량</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">평균속도</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">돌발위험</label>
	                    </div>
	                    <div class="tab_item_box flex-center list_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd" data-tab="1"><input type="checkbox" class="" checked="checked">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-tab="2"><input type="checkbox" class="">표</label>
	                    </div>
	                    <div class="list_tab_area">
		                    <div class="tab_item_box list_tab1">
	                            <select class="selectBox radius block">
	                                <option>첨두</option>
	                                <option>테스트</option>
	                                <option>테스트</option>
	                                <option>테스트</option>
	                            </select>
	                            <div class="mt8" style="width:400px;">
			                        <canvas id="tab1"></canvas>
	                            </div>	                    
		                    </div>
		                    <div class="tab_item_box list_tab2 none">
	                            <select class="selectBox radius block mb8">
	                                <option>첨두</option>
	                                <option>테스트</option>
	                                <option>테스트</option>
	                                <option>테스트</option>
	                            </select>		                    
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
			                                    <th scope="col">지역명</th>
			                                    <th scope="col">개선 전</th>
			                                    <th scope="col">개선 후</th>
			                                </tr>
			                            </thead>
			                            <tbody>
			                                <tr>
			                                    <td>1</td>
			                                    <td>도로A</td>
			                                    <td>4656</td>
			                                    <td>4565</td>
			                                </tr>
			                                <tr>
			                                    <td>2</td>
			                                    <td>도로G</td>
			                                    <td>1233</td>
			                                    <td>1054</td>
			                                </tr>
			                                <tr>
			                                    <td>3</td>
			                                    <td>도로C</td>
			                                    <td>1123</td>
			                                    <td>998</td>
			                                </tr>
			                                <tr>
			                                    <td>4</td>
			                                    <td>도로B</td>
			                                    <td>654</td>
			                                    <td>600</td>
			                                </tr>
			                                <tr>
			                                    <td>5</td>
			                                    <td>도로H</td>
			                                    <td>521</td>
			                                    <td>411</td>
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
						<div class="tab_box_title">주요 긴급차량 이동 도로 분석</div>
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
	                    <div class="tab_item_box">
							<div class="btn_search_wrap float-none mb16 flex-between">
                                <input type="text" placeholder="도로명을 입력해주세요." class="input_same search_box wd100">
                                <input type="button" value="검색" class="input_same search_box2">
                            </div>
	                    </div>
	                    <div class="tab_item_box">
							<button type="button" class="map_on_button is-dark-btn radius mb8">지도에 표시</button>
							<div class="bigdata_table">
		                        <table>
		                            <colgroup>
		                                <col style="width:10%">
		                                <col style="width:16%">
		                                <col style="width:26%">
		                                <col style="width:48%">
		                            </colgroup>
		                            <thead>
		                                <tr>
		                                    <th scope="col">순위</th>
		                                    <th scope="col">도로명</th>
		                                    <th scope="col">도로 주소</th>
		                                    <th scope="col">이동 경로 사용 횟수</th>
		                                </tr>
		                            </thead>
		                            <tbody class="tbody_hover">
		                                <tr>
		                                    <td>1</td>
		                                    <td>도로A</td>
		                                    <td>경기도 수원시 화서동</td>
		                                    <td>97</td>
		                                </tr>
		                                <tr>
		                                    <td>2</td>
		                                    <td>도로G</td>
		                                    <td>경기도 수원시 화서동</td>
		                                    <td>93</td>
		                                </tr>
		                                <tr>
		                                    <td>3</td>
		                                    <td>도로C</td>
		                                    <td>경기도 수원시 화서동</td>
		                                    <td>97</td>
		                                </tr>
		                                <tr>
		                                    <td>4</td>
		                                    <td>도로B</td>
		                                    <td>경기도 수원시 화서동</td>
		                                    <td>92</td>
		                                </tr>
		                                <tr>
		                                    <td>5</td>
		                                    <td>도로H</td>
		                                    <td>경기도 수원시 화서동</td>
		                                    <td>87</td>
		                                </tr>
		                                <tr>
		                                    <td>6</td>
		                                    <td>도로H</td>
		                                    <td>경기도 수원시 화서동</td>
		                                    <td>55</td>
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

<script>
	bigdataTabMenu();
	bigdataPopupPrev();
	gisCheckInit();
	bigdataPopupClose();
	
	//tab2
    new GITSChart(GITSChartType.BAR).init("tab1")
    .setDataSetLabel('도로구간A','도로구간B','도로구간C','도로구간D','도로구간E')
    .setDataSet({
            label:"효과 전",
            data:[300, 150, 200, 500, 240],
            backgroundColor: '#3A7DFF',
            borderRadius:1,
            borderWidth:1,
            fill: false,
        },{
            label:"효과 후",
            data:[200, 120, 140, 350, 160],
            backgroundColor: '#F90',
            borderRadius:1,
            borderWidth:1,
            fill: false,
    })
    .setTicksStep(100)
    .setLabelDisplay(false)
    .setAxis('y')
    .setBarGridY(false)
    .setBarGridX(true)
    .setAxisStackedX(false)
    .setAxisStackedY(false)
    .draw();
	
	$('.map_on_button').on('click', function(){
		$(this).toggleClass('is-darkgreen-btn');
	})
	
	//정체개선 순위보기 표/그래프 
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