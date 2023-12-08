<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc mt16 mb8">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">교차로별<br>교통신호 연동</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">교차로별 교통신호 연동</div>
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
                        <div class="tab_item_box">
                            <div class="btn_search_wrap float-none mb16 flex-between">
                                <input type="text" placeholder="교차로명을 입력해주세요." class="input_same search_box wd100">
                                <input type="button" value="검색" class="input_same search_box2">
                            </div>
                            <div class="bigdata_table">
	                            <table>
	                                <colgroup>
	                                    <col style="width:7%">
	                                    <col style="width:7%">
	                                    <col style="width:21.5%">
	                                    <col style="width:21.5%">
	                                    <col style="width:21.5%">
	                                    <col style="width:21.5%">
	                                </colgroup>
	                                <thead>
	                                    <tr>
	                                        <th scope="col" rowspan="2">NO</th>
	                                        <th scope="col" rowspan="2">SA</th>
	                                        <th scope="col" rowspan="2">교차로명</th>
	                                        <th scope="col" rowspan="2">거리</th>
	                                        <th scope="col" class="th_padding_bt center">상행연동</th>
	                                        <th scope="col" class="th_padding_bt center">하행연동</th>
	                                    </tr>
	                                    <tr>
	                                        <th class="center">링 현시 이동류</th>
	                                        <th class="center">링 현시 이동류</th>
	                                    </tr>
	                                </thead>
	                                <tbody>
	                                    <tr>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">신동사거리</td>
	                                        <td rowspan="2">586</td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
	                                    </tr>
	                                    <tr>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">신동사거리</td>
	                                        <td rowspan="2">586</td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
	                                    </tr>
	                                    <tr>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">신동사거리</td>
	                                        <td rowspan="2">586</td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
	                                    </tr>
	                                    <tr>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">1</td>
	                                        <td rowspan="2">신동사거리</td>
	                                        <td rowspan="2">586</td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="border-none th_padding_bt center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
	                                    </tr>
	                                    <tr>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">←2</span></td>
	                                        <td class="center"><span class="mr8">A</span><span class="mr8">2</span><span class="mr8">→2</span></td>
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
	datePickerInit();
</script>