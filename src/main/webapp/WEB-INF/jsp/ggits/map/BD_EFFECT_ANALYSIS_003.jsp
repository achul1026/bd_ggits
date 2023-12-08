<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_bigbox_close">
    <div class="original_box clearfix">
    	<form id="searchForm">
    		<input type="hidden" name="menuCode" value="BD_EFFECT_ANALYSIS_003">
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">연도별</h5>
	            <select class="selectBox radius" name="searchYear">
	                <option value="searchAllYear">전체</option>
	           	<c:forEach var="yearsList" items="${yearsList}">
	                <option value="${yearsList.year}">${yearsList.year}년</option>
	           	</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">기간</h5>
		            <div class="calendar">
			            <input type="text" class="date_picker input_same mr8 input_picker" name="startDate" placeholder="날짜를 선택해주세요.">
			            ~
			            <div class="end_calendar_box">
							<div class="date_picker_block"></div>								            
				            <input type="text" class="end_date_picker input_same mr8 ml8 input_picker" name="endDate" placeholder="날짜를 선택해주세요.">
			            </div>
					</div>
	            </div>
	        </div>
	        <div class="tab_item_box">
	            <div class="flex-center">
	                <h5 class="tab_item_title">시간</h5>
					<select class="selectBox selectTime start_time_check" name="startTime" id="startTime"></select>
					~
					<select class="selectBox selectTime end_time_check ml8" name="endTime" id="endTime"></select>
	            </div>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">지역별</h5>
	            <select class="selectBox radius">
	                <option value="searchAllLocation">전체 지역</option>
					<c:forEach var="sggCdList" items="${sggCdList}">
	                	<option value="${sggCdList.cdId}">${sggCdList.cdNm}</option>
					</c:forEach>
	            </select>
	        </div>
	        <div class="tab_item_box flex-center">
	            <h5 class="tab_item_title">차량번호</h5>
	            <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none" id="selectMap" name="searchCrossroadsType" value="all" checked="checked">전체</label>
	            <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" id="selectList" name="crossroadsType" value="list">리스트에서 선택</label>
	        </div>
	        <div class="tab_item_box flex-center pt8 none" id="crossroadsListDiv">
				<h5 class="tab_item_title"></h5>
				<div class="gis_table_scroll">
					<div class="mt16">
					    <div class="dashboard_sub_title">차량번호로 찾기</div>
					    <input type="text" class="dashboard_input" id="searchCrsrdNm" placeholder="차량번호를 입력해주세요.">
					    <input type="hidden" id="mapPage" name="page" value="1"/>
					</div>
					<div class="dashboard_btn_box">
					    <button type="button" class="is-darkgreen-btn modal_input_srbtn" id="searchCrossBtn">찾기</button>
					</div>
					<div class="mt16">
						<table>
						    <colgroup>
						        <col style="width:10%">
						        <col style="width:10%">
						        <col style="width:80%">
						    </colgroup>
						    <thead>
						        <tr>
						            <th scope="col"></th>
						            <th scope="col">번호</th>
						            <th scope="col">교차로명</th>
						        </tr>
						    </thead>
						    <tbody>
						    </tbody>
						</table>
					</div>
					<div id="pagingDiv">
					</div>
				</div>
	        </div>	        	        
        </form>
        <div class="bottom_btn">
            <button type="button" class="is-darkgreen-btn radius original_result_btn" onclick="analysisResultEvent();">결과보기</button>
        </div>
    </div>
</div>

<script>
	gisCheckInit();
	datePickerInit();
	dateTiemInit();
	tabTitleCss();
	resultRemove();
	tabListOnOff();
	
	function analysisResultEvent(){
/* 		var searchForm = $("#searchForm").serialize();
        window.dualMap.bigdata.getTrafficActiveEffectAnalysis(searchForm);
        window.map.monitoring.getTrafficInfo(false);
        gitsApp.drawMergeButton(function(){
            gitsApp.removeDualMap();
        }); */
		var resultItem = $(      
			`
	    		<div class="dashboard_boxTest">
	   				<div class="dashboard_wrap dash_bg">
			            <div class="original_box">
							<div class="tab_box_sub_header">
								<div class="tab_box_title">긴급차량 운행정보</div>
								<div class="tab_box_close">
									<div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
										<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
										<span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
									</div>
									<button><img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="tab_popup_close"></button>
								</div>
							</div>
			                <div class="tab_item_box">
			                    <div class="tab_item_txt flex-center">
			                        <div>2022년 5월 7일</div>
			                        <div><span class="tab_item_span">차량번호</span> : 389루 7458</div>
			                    </div>
			                    <div class="tab_item_txt flex-center">
			                        <div><span class="tab_item_span">출/도착지</span> : 장안구청/인계사거리</div>
			                        <div><span class="tab_item_span">인근교차로</span> : 신동사거리 외 8</div>
			                    </div>
			                </div>
			                <div class="tab_item_box">
			                    <div class="tab_item_txt flex-center">
			                        <div><span class="tab_item_span">예상 운행시간</span> : 45분</div>
			                        <div><span class="tab_item_span">단축시간</span> : 20분</div>
			                    </div>
			                </div>
			                <div class="tab_item_box">
			                    <div class="tab_item_txt flex-center">
			                        <div><span class="tab_item_span">실 운행시간</span> : 45분</div>
			                        <div><span class="tab_item_span">골든타임 달성여부</span> : 달성</div>
			                    </div>
			                </div>
			                <div class="tab_item_box flex-center select_arrow">
			                	<div class="ftsize12">긴급차량 운행 구간 내 인접 도로 및 교차로 소통 변화</div>
			                </div>
			                <div class="tab_item_box flex-center">
			                    <h5 class="tab_item_title">기준</h5>
			                    <label class="group_btn_item is-dark-btn radius inpd ftsize12"><input type="checkbox" class="none">소통등급</label>
			                    <label class="group_btn_item is-dark-btn radius inpd ftsize12"><input type="checkbox" class="none">평균속도</label>
			                </div>
			                <div class="tab_item_box">
		                        <select class="selectBox radius ftsize12">
		                            <option>신동사거리</option>
		                            <option>테스트</option>
		                            <option>테스트</option>
		                            <option>테스트</option>
		                        </select>
			                    <div class="mt8">
			                        <canvas id="chart"></canvas>
			                    </div>
			                </div>
		            	</div>   				
					</div>
	   			</div>`     
	   	);   
        $('#aside_dashboard_container').append(resultItem);
        
    /*     new GITSChart(GITSChartType.LINE).init("chart")
        .setDataSetLabel('원활','서행','지체/혼잡', '', '', '')
        .setDataSet({
            label:'교통량',
            data: [40, 15, 25, 42, 60, 50],
            backgroundColor:'#58edd2',
            borderWidth:3,
            borderColor:'#58edd2',
            fill: false
        })
        .setTicksStep(20)
        .setAxis('y')
    	.setBarGridY(false)
    	.setBarGridX(true)
         .draw(); */
         
        var remarksItem = $(`
    	        <div class="remarks_container">
    		        <div class="remarks_title_box">
    		            <h6 class="remarks_title">범례 - 소통등급</h6>
    		        </div>
    	        	<div class="remarks_wrap tab-none">
    	            	<div>
    		                <div class="check_line_container">
	    	                    <button type="button" class="check_line_box remarks-red">정체</button>
	    	                    <button type="button" class="check_line_box remarks-orange">서행</button>
	    	                    <button type="button" class="check_line_box remarks-green">원활</button>
    		                </div>
    	            	</div>
    		            <div class="unit">단위 : 소통등급</div>
    	        	</div>
    	    	</div>`)        
            $('#map-container').append(remarksItem);
            legendToggle();	          
	}
	
	
	$("#selectList").on("click",function(){
		var checkedVal = $(this).is(":checked");
		$("#crossroadsListDiv").removeClass("none");
		fnSearchCrossListForBigdata();
	})
</script>
