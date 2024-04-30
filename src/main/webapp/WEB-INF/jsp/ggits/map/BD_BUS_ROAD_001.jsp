<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc pd16">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">노선구간별<br>승하차/재차<br>승객수 집계</button></li>
	            <li><button type="button" class="sub_data_btn" data-index="2">노선구간별 수용성<br>및 굴곡도 분석</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">노선구간별 승하차/재차 승객수 집계</div>
						<div class="tab_box_close">
							<div class="opa_slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all">
								<div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 100%;"></div>
								<span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0" style="left: 100%;"></span>
							</div>
							<button><img src="${pageContext.request.contextPath}/statics/images/wh_close.png" class="tab_popup_close"></button>
						</div>
					</div>	            
	                <div class="result_item">
	                	<form id="searchForm1" method="get" class="wd100">
		                	<div>
		                		<button type="button" class="prev_text rollbackBtn prevIndexBtn"><span class="prev_arrow">←</span> 이전</button>
		                	</div>
	                        <div class="tab_item_box flex-center">
	                            <h5 class="tab_item_title">일자</h5>
								<input type="text" class="date_picker input_same mr8 input_picker end_date_check today searchTrgt"name="searchTime" placeholder="날짜를 선택해주세요." autocomplete="off">
	                        	<input type="hidden" class="todayTxt">
	                        </div>       
	                    </form>             
                        <div class="tab_item_box chartBox1" style="width:400px; height:300px;">
                            <canvas id="tab1" class="chartCan1"></canvas>
                        </div>	  
	                </div>
	            </div>
        	</div>
        	<div class="tab tab2 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">노선구간별 수용성 및 굴곡도 분석</div>
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
	                		<button type="button" class="prev_text rollbackBtn"><span class="prev_arrow">←</span> 이전</button>
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
	
	let endDate = $('.end_date_check').val();
	$(".todayTxt").val(endDate);
	
	$(".prevIndexBtn").on("click", function(){
		$(".chartCan1").remove();
		$(".chartBox1").append(
				 '<canvas id="tab1" class="chartCan1"></canvas>'
		)
		$(".today").val($(".todayTxt").val());
		var sttnInfoArr = '<c:out value="${sttnInfoArr}"/>';
		var userCntArr = '<c:out value="${userCntArr}"/>';
		settingChart(sttnInfoArr, userCntArr, 1);
	})
	
	$(".searchTrgt").on("change", function(){
		$(".chartCan1").remove();
		$(".chartBox1").append(
				 '<canvas id="tab1" class="chartCan1"></canvas>'
		)
		fnSearchList();
	})
	
	function fnSearchList(){
		$.ajax({
    		type : "get",
    		data : $("#searchForm1").serialize(),
    		url : "${pageContext.request.contextPath}/map/bigdata/bus/road/BD_BUS_ROAD_001/data.ajax",
    		success : function(result){
    		    //tab1
    		    var sttnInfoArr = result.data.sttnInfoArr; 
    			var userCntArr = result.data.userCntArr; 
    			settingChart(sttnInfoArr, userCntArr, 1);	
    			
    		}
    	})
	}
	
	function settingChart(sttnInfoArr, userCntArr, chartNum){
		//tab1
	    var sttnInfoArr = sttnInfoArr;
	    var userCntArr = userCntArr;
	    new GITSChart(GITSChartType.BAR).init("tab1")
	    .setData({
	             labels: sttnInfoArr.split(','),
	             datasets: [{
	            	 label:'승차',
	            	 data:userCntArr.split(','),
	            	 backgroundColor:'#00BCB1'
	             }]
	         })	
	    .setTickStepX(20)
	    .setAxis('y')
	    .setBarGridX(true)
	    .draw();
	}
	
    //tab1
	var sttnInfoArr = '<c:out value="${sttnInfoArr}"/>';
	var userCntArr = '<c:out value="${userCntArr}"/>';

    new GITSChart(GITSChartType.BAR).init("tab1")
    .setData({
             labels: sttnInfoArr.split(','),
             datasets: [{
            	 label:'승차',
            	 data:userCntArr.split(','),
            	 backgroundColor:'#00BCB1'
             }]
         })	
    .setTickStepX(20)
    .setAxis('y')
    .setBarGridX(true)
    .draw();

    //tab2
	var busUseRouteIdArr = '<c:out value="${busUseRouteIdArr}"/>';
	var busUserCntArr = '<c:out value="${busUserCntArr}"/>';
    new GITSChart(GITSChartType.BAR).init("tab2")
    .setData({
             labels: busUseRouteIdArr.split(','),
             datasets: [{
            	 label:'굴곡도',
            	 data:busUserCntArr.split(','),
            	 backgroundColor:'#00BCB1'
             }]
         })	
    .setTickStepX(1)
    .setAxis('y')
    .setBarGridX(true)
    .draw();
</script>