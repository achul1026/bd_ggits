<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc pd16">
	        <ul>
	            <li><button type="button" class="sub_data_btn" data-index="1">교차로 교통량 순위</button></li>
	            <li><button type="button" class="sub_data_btn acdntBtn" data-index="2">사고예측 지역<br>순위</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">교차로 교통량 순위</div>
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
	                		<button type="button" class="prev_text rollbackBtn prevIndexBtn"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
                        <div class="tab_item_box flex-center">
                            <h5 class="tab_item_title">일자</h5>
							<input type="text" class="date_picker input_same mr8 input_picker end_date_check today searchTrgt" name="searchTime" placeholder="날짜를 선택해주세요." autocomplete="off" data-value="1" >
                        	<input type="hidden" class="todayTxt">
                        </div>
                        <div class="tab_item_box chartBox1" style="width:400px; height:300px;">
                            <canvas id="tab2" class="chartCan1"></canvas>
                        </div>	                	
	                </div>
	            </div>
        	</div>
        	<div class="tab tab2 tab-none">
	            <div>
					<div class="tab_box_sub_header">
						<div class="tab_box_title">사고예측 지역 순위</div>
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
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd"><input type="checkbox" class="none" name="searchStandard" checked="checked" value="sgg" data-standard-name="지역명">시군 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchStandard" value="umd" data-standard-name="읍면동명">읍면동 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none" name="searchStandard" value="road" data-standard-name="도로명명">도로 별</label>
	                    </div>
	                    <div class="tab_item_box flex-center list_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn is-darkgreen-btn radius inpd" data-tab="1"><input type="checkbox" class="none" name="searchType" checked="checked" value="graph">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-tab="2"><input type="checkbox" class="none" name="searchType" value="table">표</label>
	                    </div>
	                    <div class="list_tab_area">
		                    <div class="tab_item_box list_tab1">
	                            <div class="mt8 chartBox4" style="width:400px; height:300px;">
			                        <canvas id="tab4" class="chartCan4"></canvas>
	                            </div>	                    
		                    </div>
		                    <div class="tab_item_box list_tab2 none">
		                    	<div class="bigdata_table">
			                        <table>
			                            <colgroup>
			                                <col style="width:10%">
			                                <col style="width:30%">
			                                <col style="width:15%">
			                                <col style="width:15%">
			                                <col style="width:15%">
			                                <col style="width:15%">
			                            </colgroup>
			                            <thead id="addThead">
			                            </thead>
			                            <tbody id="addTbody">
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
	datePickerInit();
	
	let endDate = $('.end_date_check').val();
	$(".todayTxt").val(endDate);
	
	$(".searchTrgt").on("change", function(){
    	var searchTime = $(this).val();
		$(".chartCan1").remove();
		$(".chartBox1").append(
				 '<canvas id="tab2" class="chartCan1"></canvas>'
		)
		fnSearchList(searchTime);
	})
	
	function fnSearchList(searchTime){
		$.ajax({
    		type : "get",
    		data : {
    			"searchTime" : searchTime,
    			"subType" : "cross"
    		},
    		url : "${pageContext.request.contextPath}/map/bigdata/prediction/BD_PREDICTION_001/data.ajax",
    		success : function(result){
    		    //tab2
    			var crsrdNmArr = result.data.crsrdNmArr;
    			var trfvlmTotalArr = result.data.trfvlmTotalArr;
    			settingChart(crsrdNmArr, trfvlmTotalArr);
    		}
    	})
	}
	
    function settingChart(crsrdNmArr, trfvlmTotalArr){
		//tab1
	    var crsrdNmArr = crsrdNmArr;
	    var trfvlmTotalArr = trfvlmTotalArr;
	    new GITSChart(GITSChartType.BAR).init("tab2")
	    .setData({
	             labels: crsrdNmArr.split(','),
	             datasets: [{
	            	 label:'교통량',
	            	 data: trfvlmTotalArr.split(','),
	            	 backgroundColor:'#00BCB1',
	            	 borderWidth:2
	             }]
	         })	
	    .setTickStepX(100)
	    .setAxis('y')
	    .setBarGridX(true)
	    .draw();
	}
	
    
	$(".prevIndexBtn").on("click", function(){
		$(".chartCan1").remove();
		$(".chartBox1").append(
				 '<canvas id="tab2" class="chartCan1"></canvas>'
		)
		$(".today").val($(".todayTxt").val());
		
		
		var crsrdNmArr = '<c:out value="${crsrdNmArr}"/>';
		var trfvlmTotalArr = '<c:out value="${trfvlmTotalArr}"/>';

		settingChart(crsrdNmArr, trfvlmTotalArr);
	})    
	
	//tab2
	var crsrdNmArr = '<c:out value="${crsrdNmArr}"/>';
	var trfvlmTotalArr = '<c:out value="${trfvlmTotalArr}"/>';

    new GITSChart(GITSChartType.BAR).init("tab2")
    .setData({
             labels: crsrdNmArr.split(','),
             datasets: [{
            	 label:'교통량',
            	 data: trfvlmTotalArr.split(','),
            	 backgroundColor:'#00BCB1',
            	 borderWidth:2
             }]
         })	
    .setTickStepX(100)
    .setAxis('y')
    .setBarGridX(true)
    .draw();
	
	
	
	//사고예측 지역 순위보기 표/그래프 
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
	
	
	//더 많은 데이터 보기 > 교통 예측
	$(".acdntBtn").on("click",function(){
	    var obj = new Object();
		var acdntPredictionData = '<c:out value="${acdntPredictionData}"/>';
		var adstdgNmArray = '<c:out value="${acdntPredictionData.adstdgNmArray}"/>';
		var safeCntArray = '<c:out value="${acdntPredictionData.safeCntArray}"/>';
		var cautionCntArray = '<c:out value="${acdntPredictionData.cautionCntArray}"/>';
		var seriousCntArray = '<c:out value="${acdntPredictionData.seriousCntArray}"/>';
		var dangerCntArray = '<c:out value="${acdntPredictionData.dangerCntArray}"/>';
	    
	    obj.adstdgNmArray = adstdgNmArray;
	    obj.safeCntArray = safeCntArray;
	    obj.cautionCntArray = cautionCntArray;
	    obj.seriousCntArray = seriousCntArray;
	    obj.dangerCntArray = dangerCntArray;
	    
	    drawGraphAcdntPrediction(obj)
	})
    
    //기준 , 차트 유형 클릭 데이터 호출
    $("input[name='searchStandard']").on("click",function(){
    	var searchStandard = $(this).val();
    	var searchType = $("input[name='searchType']:checked").val();
    	var standardName = $(this).data("standard-name");
    	getAcdntPredictionData(searchStandard,searchType,standardName);
    })
    $("input[name='searchType']").on("click",function(){
    	var searchType = $(this).val();
    	var searchStandard = $("input[name='searchStandard']:checked").val();
    	var standardName = $("input[name='searchStandard']:checked").data("standard-name");
    	getAcdntPredictionData(searchStandard,searchType,standardName);
    })
    
    function getAcdntPredictionData(searchStandard,searchType,standardName){
    	$.ajax({
    		type : "get",
    		data : {
    			"searchStandard" : searchStandard,
    			"searchType" : searchType,
    			"subType" : "acdnt"
    		},
    		url : "${pageContext.request.contextPath}/map/bigdata/prediction/BD_PREDICTION_001/data.ajax",
    		success : function(result){
    			var acdntPredictionData = result.data.acdntPredictionData;
    			if("graph" === searchType){
    				drawGraphAcdntPrediction(acdntPredictionData)
    			}else{
    				$("#addThead > tr").remove();
    				$("#addTbody > tr").remove();
    				var theadHtml = '<tr>'+
				                        '<th scope="col">순위</th>'+
				                        '<th scope="col">'+standardName+'</th>'+
				                        '<th scope="col" class="center">안전</th>'+
				                        '<th scope="col" class="center">주의</th>'+
				                        '<th scope="col" class="center">위험</th>'+
				                        '<th scope="col" class="center">심각</th>'+
				                    '</tr>'	
				    $("#addThead").append(theadHtml);
				                    
					var tbodyHtml = "";
				    $(acdntPredictionData).each(function(index, item){
				    	tbodyHtml += 	'<tr>'+
					                        '<td>'+(index+1)+'</td>'+
					                        '<td>'+item.adstdgNm+'</td>'+
					                        '<td class="center">'+item.safeCnt+'</td>'+
					                        '<td class="center">'+item.cautionCnt+'</td>'+
					                        '<td class="center">'+item.dangerCnt+'</td>'+
					                        '<td class="center">'+item.seriousCnt+'</td>'+
					                    '</tr>';
				    });
				    
				    $("#addTbody").append(tbodyHtml);
    			}
    		}
    	})	    	
    }
    //-----------------------
    
	function drawGraphAcdntPrediction(data){
		$(".chartCan4").remove();
		$(".chartBox4").append(
				 '<canvas id="tab4" class="chartCan4"></canvas>'
		)
		//tab4
	    new GITSChart(GITSChartType.BAR).init("tab4")
	    .setDataSetArrayLabel(data.adstdgNmArray.split(','))
	    .setDataSet({
	        label:'안전',
	        data:data.safeCntArray.split(','),
	        backgroundColor:'#7BDE4D',
	    },{
	        label:'주의',
	        data:data.cautionCntArray.split(','),
	        backgroundColor:'#FFD600',
	    },{
	        label:'위험',
	        data:data.dangerCntArray.split(','),
	        backgroundColor:'#FF9900',
	    },{
	        label:'심각',
	        data:data.seriousCntArray.split(','),
	        backgroundColor:'#FF3636',
	    })
	    .setTickStepX(50)
	    .setAxis('y')
	    .setBarGridX(true)
	    .draw();
		
	}
</script>