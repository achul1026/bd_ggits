<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc pd16">
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
	                		<button type="button" class="prev_text rollbackBtn"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
<!-- 	                    <div class="tab_item_box flex-center"> -->
<!-- 	                        <h5 class="tab_item_title">기준</h5> -->
<!-- 	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">시군 별</label> -->
<!-- 	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none">읍면동 별</label> -->
<!-- 	                    </div> -->
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
	                		<button type="button" class="prev_text rollbackBtn"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">항목</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd is-darkgreen-btn"><input type="checkbox" class="none selectType" value="score" selected>평가점수</label>
	                    </div>
	                    <div class="tab_item_box chartBox2"  style="width:450px; height:300px;">
	                        <canvas id="tab2" class="chartCan2"></canvas>
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
	var flatPopltnDataArr = '<c:out value="${flatPopltnDataArr}"/>';
	var flatPopltnLabelArr = '<c:out value="${flatPopltnLabelArr}"/>';

    new GITSChart(GITSChartType.BAR).init("tab1")
    .setData({
             labels: flatPopltnLabelArr.split(','),
             datasets: [{
            	 label:'유동인구',
            	 data: flatPopltnDataArr.split(','),
            	 backgroundColor:['#F00','#AD49FB','#00BCB1','#F90','#0075FF','#00E0FF','#00D870','#FFF500','#FFB800','#FF7CD3']
             }]
         })	
    .setTickStepX(10)
    .setAxis('y')
    .setBarGridX(true)
    .draw();	
	
    //tab2
	var routeNmArr = '<c:out value="${routeNmArr}"/>';
	var scoreArr = '<c:out value="${scoreArr}"/>';

	 new GITSChart(GITSChartType.BAR).init("tab2")
     .setData({
              labels: routeNmArr.split(','),
              datasets: [{
             	 label:'평가점수',
             	 data: scoreArr.split(','),
            	 backgroundColor:['#F00','#AD49FB','#00BCB1','#F90','#0075FF','#00E0FF','#00D870','#FFF500','#FFB800','#FF7CD3']
              }]
          })	
     .setTickStepX(10)
     .setAxis('y')
     .setBarGridX(true)
     .draw();	
    
    $(".selectType").on("click", function(){
    	$(".chartCan2").remove();
		$(".chartBox2").append(
				 '<canvas id="tab2" class="chartCan2"></canvas>'
		)
		var searchType = $(this).val();
		fnSearchList(searchType);
    })
    
    function fnSearchList(searchType){
		$.ajax({
    		type : "get",
    		data : {
    			"searchResultType" : searchType
    		},
    		url : "${pageContext.request.contextPath}/map/bigdata/pb/prediction/BD_PB_PREDICTION_001/data.ajax",
    		beforeSend : function(){
    			startLoading();
    		},
    		success : function(result){
   				var routeNmArr = result.data.routeNmArr
   				var scoreArr = result.data.scoreArr
   				busPrdctnRankScore(routeNmArr, scoreArr);
    		},
    		complete : function(){
    			endLoading();
    		}
    	});
    }
    
    function busPrdctnRankScore(routeNmArr, scoreArr){
        new GITSChart(GITSChartType.BAR).init("tab2")
        .setData({
                 labels: routeNmArr.split(','),
                 datasets: [{
                	 label:'평가점수',
                	 data: scoreArr.split(','),
                	 backgroundColor:['#F00','#AD49FB','#00BCB1','#F90','#0075FF','#00E0FF','#00D870','#FFF500','#FFB800','#FF7CD3']
                 }]
             })	
        .setTickStepX(10)
        .setAxis('y')
        .setBarGridX(true)
        .draw();
    }
    
    $(".rollbackBtn").on("click", function(){
    	$(".chartCan2").remove();
		$(".chartBox2").append(
				 '<canvas id="tab2" class="chartCan2"></canvas>'
		)
		fnSearchList("score");
    })
</script>