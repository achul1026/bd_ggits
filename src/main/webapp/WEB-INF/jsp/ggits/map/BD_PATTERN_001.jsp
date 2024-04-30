<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc pd16">
	        <ul>
	            <li><button type="button" class="sub_data_btn dataCtgryBtn" data-value="trfVlm" data-index="1">교통량 순위</button></li>
	            <li><button type="button" class="sub_data_btn dataCtgryBtn" data-value="speed" data-index="2">평균 속도 순위</button></li>
	            <li><button type="button" class="sub_data_btn dataCtgryBtn" data-value="delay" data-index="3">정체 구간 순위</button></li>
	        </ul>
    	</div>
        <div class="tab_area">
        	<div class="tab tab1 tab-none">
	            <div>
					<div class="tab_box_sub_header tab_title_box">
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
	                		<button type="button" class="prev_text rollbackBtn" data-value="trfVlm"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none trfVlmDataOption" value="city">시군 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none trfVlmDataOption" value="town">읍면동 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none trfVlmDataOption" value="cross">교차로 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none trfVlmDataOption" value="link">도로 별</label>
	                    </div>
	                    <div class="tab_item_box flex-center list_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-tab="1"><input type="checkbox" class="none trfVlmType" value="graph">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-tab="2"><input type="checkbox" class="none trfVlmType" value="table">표</label>
	                    </div>
	                    <div class="list_tab_area">
		                    <div id="trfVlm_chart_div" class="tab_item_box list_tab1" style="width:400px; height:300px;">
		                        <canvas id="trfVlm_chart"></canvas>
		                    </div>	                    
		                    <div class="tab_item_box list_tab2 none">
								<div id="trfVlm_table" class="bigdata_table">
			                        
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
	                		<button type="button" class="prev_text mb8 mt8 rollbackBtn" data-value="speed"><span class="prev_arrow">←</span> 이전</button>
	                	</div>	                
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none speedDataOption" value="city">시군 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none speedDataOption" value="town">읍면동 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none speedDataOption" value="cross">교차로 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none speedDataOption" value="link">도로 별</label>
	                    </div>
	                    <div class="tab_item_box flex-center list_speend_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-speend="1"><input type="checkbox" class="none speedType" value="graph">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-speend="2"><input type="checkbox" class="none speedType" value="table">표</label>
	                    </div>
	                    <div class="list_speend_tab_area">
		                    <div id="speed_chart_div" class="tab_item_box list_speend_tab1" style="width:400px; height:300px;">
		                        <canvas id="speed_chart"></canvas>
		                    </div>		                    	
		                    <div class="tab_item_box list_speend_tab2 none">
		                    	<div id="speed_table" class="bigdata_table">

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
	                		<button type="button" class="prev_text mb8 mt8 rollbackBtn" data-value="delay"><span class="prev_arrow">←</span> 이전</button>
	                	</div>	                
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none delayDataOption" value="city">시군 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none delayDataOption" value="town">읍면동 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none delayDataOption" value="cross">교차로 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd"><input type="checkbox" class="none delayDataOption" value="link">도로 별</label>
	                    </div>
	                    <div class="tab_item_box flex-center list_confusion_tab_button">
	                        <h5 class="tab_item_title">차트 유형</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-confusion="1"><input type="checkbox" class="none delayType" value="graph">그래프</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-confusion="2"><input type="checkbox" class="none delayType" value="table">표</label>
	                    </div>
	                    <div class="list_confusion_tab_area">
		                    <div id="delay_chart_div" class="tab_item_box list_confusion_tab1" style="width:400px; height:300px;">
		                        <canvas id="delay_chart"></canvas>
		                    </div>		            	                    
		                    <div class="tab_item_box list_confusion_tab2 none">
		                    	<div id="delay_table" class="bigdata_table" style="width:30rem;">
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
	
	$(".trfVlmDataOption").on('click',function(){
		var dataOption = $(this).val();
		optionTapInit(dataOption,"trfVlm");
	});
	
	$(".speedDataOption").on('click',function(){
		var dataOption = $(this).val();
		optionTapInit(dataOption,"speed");
	});

	$(".delayDataOption").on('click',function(){
		var dataOption = $(this).val();
		optionTapInit(dataOption,"delay");
	});
	
	function optionTapInit(dataOption,dataCtgry){
		
		var colNm = "";
    	switch(dataOption){
    	case "city":
    		colNm = "시,군";
    		break
    	case "town":
			colNm = "읍,면,동"
    		break;
    	case "cross":
			colNm = "교차로"
    		break;
    	case "link":
			colNm = "도로명"
    		break;
    	}
    	
    	 $.ajax ({
             type : "post",
             data : {
             	"dataCtgry" : dataCtgry,
             	"dataOption" : dataOption
             },
             url : "${pageContext.request.contextPath}/map/bigdata/loadTrafficPattrnData.ajax",
             cache : false,
             dataType : "json",
             success : function(result) {
            	var data = result.data;
            	
            	html = "";
            	
            	var tableData = data.tableData;
            	
            	switch(dataCtgry){
            		case "trfVlm":
	        		$("#trfVlm_chart_div").empty();
	        		$("#trfVlm_table").empty();
	        		
	        		var chartDiv = '<canvas id="trfVlm_chart"></canvas>';
	        		$("#trfVlm_chart_div").append(chartDiv);
	        		
	            	 new GITSChart(GITSChartType.BAR).init("trfVlm_chart")
	                 .setData({
	                     labels: data.chartLabel.split(','),
	                     datasets: [{
	                    	 label:'교통량',
	                         data : data.chartData.split(','),
	                         backgroundColor:'#00bcb1',
	                         borderRadius:2,
	                         fill: false,
	                     }]
	                 })	
	                 .setTicksStep(200)
	                 .setAxis('y')
	                 .setBarGridY(false)
	                 .setBarGridX(true)
	                 .draw();
	            	 
	              	//표 그리기
	 	             html += ' <table>';
	 	             html += '    <colgroup>';
	 	             html += '        <col style="width:10%">';
	 	             html += '        <col style="width:26%">';
	 	             html += '        <col style="width:30%">';
	 	             html += '    </colgroup>';
	 	             html += '    <thead>';
	 	             html += '        <tr>';
	 	             html += '            <th scope="col">순위</th>';
	 	             html += '            <th scope="col">'+colNm+'</th>';
	 	             html += '            <th scope="col">교통량</th>';
	 	             html += '        </tr>';
	 	             html += '    </thead>';
	 	             html += '    <tbody>';
	 	           	for(var i = 0; i < tableData.length; i++){
	 		             html += '        <tr>';
	 		             html += '            <td>'+(i+1)+'</td>';
	 		             html += '            <td>'+tableData[i].adsiNm+'</td>';
	 		             html += '            <td>'+tableData[i].vhclTrfvlm.toLocaleString("ko-KR")+'</td>';
	 		             html += '        </tr>';
	 	           	}
	 	             html += '    </tbody>';
	 	             html += ' </table>';
	 	             $("#trfVlm_table").append(html);
            			break;
            		case "speed":
   	        		$("#speed_chart_div").empty();
   	        		$("#speed_table").empty();
   	        		
   	        		var chartDiv = '<canvas id="speed_chart"></canvas>';
   	        		$("#speed_chart_div").append(chartDiv);
   	        		
   	            	 new GITSChart(GITSChartType.BAR).init("speed_chart")
   	                 .setData({
   	                     labels: data.chartLabel.split(','),
   	                     datasets: [{
   	                    	 label:'평균속도',
   	                         data : data.chartData.split(','),
   	                         backgroundColor: '#00bcb1',
   	                         borderRadius:2,
   	                         fill: false,
   	                     }]
   	                 })	
   	                 .setTicksStep(20)
   	                 .setAxis('y')
   	                 .setBarGridY(false)
   	                 .setBarGridX(true)
   	                 .draw();
   	            	 
   	              	//표 그리기
   	 	             html += ' <table>';
   	 	             html += '    <colgroup>';
   	 	             html += '        <col style="width:10%">';
   	 	             html += '        <col style="width:26%">';
   	 	             html += '        <col style="width:30%">';
   	 	             html += '    </colgroup>';
   	 	             html += '    <thead>';
   	 	             html += '        <tr>';
   	 	             html += '            <th scope="col">순위</th>';
   	 	             html += '            <th scope="col">'+colNm+'</th>';
   	 	             html += '            <th scope="col" class="center">혼잡 횟수 | 평균 혼잡 강도 | 속도 </th>';
   	 	             html += '        </tr>';
   	 	             html += '    </thead>';
   	 	             html += '    <tbody>';
      	           for(var i = 0; i < tableData.length; i++){
 	                  html += '    	 <tr>';
 	    	          html += '             <td>'+(i+1)+'</td>';
 	                  html += '        		<td>'+tableData[i].adsiNm+'</td>';
 	                  html += '         	<td class="center"> 100 | 4101 |' +tableData[i].avgVhclSpeed.toLocaleString("ko-KR")+'km/h</td>';
 	                  html += '    	 </tr>';
     	           }
   	 	             html += '    </tbody>';
   	 	             html += ' </table>';
   	 	             $("#speed_table").append(html);
            			break;
            		case "delay":
     	        	$("#delay_chart_div").empty();
     	        	$("#delay_table").empty();
     	        		
     	        	var chartDiv = '<canvas id="delay_chart"></canvas>';
    	        	$("#delay_chart_div").append(chartDiv);
               		
    	        	//교통량 순위 보기 차트
                       new GITSChart(GITSChartType.BAR).init("delay_chart")
                       .setData({
                           labels: data.chartLabel.split(','),
                           datasets: [{
                        	   label:'정체구간',
                               data : data.chartData.split(','),
                               backgroundColor: '#00bcb1',
                               borderRadius:2,
                               fill: false,
                           }]
                       })	
                       .setTicksStep(200)
                       .setAxis('y')
                       .setBarGridY(false)
                       .setBarGridX(true)
                       .draw();
                   	
                   	//표 그리기
       	             html += ' <table>';
       	             html += '    <colgroup>';
       	             html += '        <col style="width:10%">';
       	             html += '        <col style="width:26%">';
       	             html += '        <col style="width:26%">';
       	             html += '        <col style="width:15%">';
       	             html += '        <col style="width:15%">';
       	             html += '    </colgroup>';
       	             html += '    <thead>';
       	             html += '        <tr>';
       	             html += '            <th scope="col">순위</th>';
       	             html += '            <th scope="col">'+colNm+'</th>';
       	             html += '            <th scope="col">혼잡도</th>';
       	             html += '            <th scope="col">교통량</th>';
       	             html += '            <th scope="col">속도</th>';
       	             html += '        </tr>';
       	             html += '    </thead>';
       	             html += '    <tbody>';
       	           	for(var i = 0; i < tableData.length; i++){
       					var delayStatus = tableData[i].delayStts;
       					var delay = "";
       					if(delayStatus == 1){
       						delay = "원활";
       					} else if(delayStatus == 2){
       						delay = "지체(서행)";
       					} else {
       						delay = "정체";
       					}
       		             html += '        <tr>';
       		             html += '            <td>'+(i+1)+'</td>';
       		             html += '            <td>'+tableData[i].adsiNm+'</td>';
       		             html += '            <td>'+delay+'</td>';
       		             html += '            <td>'+tableData[i].vhclTrfvlm.toLocaleString("ko-KR")+'</td>';
       		             html += '            <td>'+tableData[i].avgVhclSpeed.toLocaleString("ko-KR")+'km/h</td>';
       		             html += '        </tr>';
       	           	}
       	             html += '    </tbody>';
       	             html += ' </table>';
       	             $("#delay_table").append(html);
            			break;
            	}
             }
    	 })
	}
	
	$(".dataCtgryBtn").on('click',function(){
		var $this = $(this);
		var dataCtgry = $this.data('value');
		
        $.ajax ({
            type : "post",
            data : {
            	"dataCtgry" : dataCtgry
            },
            url : "${pageContext.request.contextPath}/map/bigdata/loadTrafficPattrnData.ajax",
            cache : false,
            dataType : "json",
            success : function(result) {
            	var data = result.data;
            	
            	html = "";
            	
            	var dataCtgry = data.subTitle;
            	var tableData = data.tableData;

            	switch(dataCtgry){
            	case "trfVlm":
            		//교통량 순위 보기 차트
	            	$(".trfVlmDataOption").eq(0).prop("checked",true);
	            	$(".trfVlmDataOption").eq(0).parent('label').addClass('is-darkgreen-btn');

	            	$(".trfVlmType").eq(0).prop("checked",true);
	            	$(".trfVlmType").eq(0).parent('label').addClass('is-darkgreen-btn');
	            	
                    new GITSChart(GITSChartType.BAR).init("trfVlm_chart")
                    .setData({
                        labels: data.chartLabel.split(','),
                        datasets: [{
                        	label: '교통량',
                            data : data.chartData.split(','),
                            backgroundColor: '#00bcb1',
                            borderRadius:2,
                            fill: false,
                        }]
                    })	
                    .setTicksStep(200)
                    .setAxis('y')
                    .setBarGridY(false)
                    .setBarGridX(true)
                    .draw();
                	
                	//표 그리기
    	             html += ' <table>';
    	             html += '    <colgroup>';
    	             html += '        <col style="width:10%">';
    	             html += '        <col style="width:26%">';
    	             html += '        <col style="width:30%">';
    	             html += '    </colgroup>';
    	             html += '    <thead>';
    	             html += '        <tr>';
    	             html += '            <th scope="col">순위</th>';
    	             html += '            <th scope="col">시군별</th>';
    	             html += '            <th scope="col">교통량</th>';
    	             html += '        </tr>';
    	             html += '    </thead>';
    	             html += '    <tbody>';
    	           	for(var i = 0; i < tableData.length; i++){
    		             html += '        <tr>';
    		             html += '            <td>'+(i+1)+'</td>';
    		             html += '            <td>'+tableData[i].adsiNm+'</td>';
    		             html += '            <td>'+tableData[i].vhclTrfvlm.toLocaleString("ko-KR")+'</td>';
    		             html += '        </tr>';
    	           	}
    	             html += '    </tbody>';
    	             html += ' </table>';
    	             $("#trfVlm_table").append(html);
            		break;
            	case "speed":
	            	$(".speedDataOption").eq(0).prop("checked",true);
	            	$(".speedDataOption").eq(0).parent('label').addClass('is-darkgreen-btn');
	            	
	            	$(".speedType").eq(0).prop("checked",true);
	            	$(".speedType").eq(0).parent('label').addClass('is-darkgreen-btn');
            		//평균 속도 순위
                    new GITSChart(GITSChartType.BAR).init("speed_chart")
                    .setData({
                        labels: data.chartLabel.split(','),
                        datasets: [{
                        	label:'평균속도',
                            data : data.chartData2.split(','),
                            backgroundColor: '#00bcb1',
                            borderRadius:2,
                            fill: false,
                        }]
                    })	
                    .setTicksStep(20)
                    .setAxis('y')
                    .setBarGridY(false)
                    .setBarGridX(true)
                    .draw();
            		
                   html += '<table>';
                   html += ' 	<colgroup>';
                   html += '     	<col style="width:10%">';
                   html += '     	<col style="width:26%">';
                   html += '    	<col style="width:30%">';
                   html += ' 	</colgroup>';
                   html += ' 	<thead>';
                   html += '    	 <tr>';
                   html += '         	<th scope="col">순위</th>';
                   html += '         	<th scope="col">시군별</th>';
                   html += '         	<th scope="col" class="center">혼잡 횟수 | 평균 혼잡 강도 | 속도 </th>';
                   html += '     	 </tr>';
                   html += ' 	</thead>';
                   html += '	<tbody>';
    	           for(var i = 0; i < tableData.length; i++){
	                  html += '    	 <tr>';
	    	          html += '            <td>'+(i+1)+'</td>';
	                  html += '        	<td>'+tableData[i].adsiNm+'</td>';
	                  html += '         	<td class="center"> 100 | 4109 | '+tableData[i].avgVhclSpeed.toLocaleString("ko-KR")+'km/h</td>';
	                  html += '    	 </tr>';
    	           }
                   html += ' 	</tbody>';
                   html += '</table>';
                	
    	           $("#speed_table").append(html);
            		break;
            	case "delay":
	            	$(".delayDataOption").eq(0).prop("checked",true);
	            	$(".delayDataOption").eq(0).parent('label').addClass('is-darkgreen-btn');
	            	    
	            	$(".delayType").eq(0).prop("checked",true);
	            	$(".delayType").eq(0).parent('label').addClass('is-darkgreen-btn');
            		//교통량 순위 보기 차트
                    new GITSChart(GITSChartType.BAR).init("delay_chart")
                    .setData({
                        labels: data.chartLabel.split(','),
                        datasets: [{
                        	label:'정체구간',
                            data : data.chartData.split(','),
                            backgroundColor: ['#00bcb1'],
                            borderRadius:2,
                            fill: false,
                        }]
                    })	
                    .setTicksStep(200)
                    .setAxis('y')
                    .setBarGridY(false)
                    .setBarGridX(true)
                    .draw();
                	
                	//표 그리기
    	             html += ' <table>';
    	             html += '    <colgroup>';
    	             html += '        <col style="width:10%">';
    	             html += '        <col style="width:26%">';
    	             html += '        <col style="width:26%">';
    	             html += '        <col style="width:15%">';
    	             html += '        <col style="width:15%">';
    	             html += '    </colgroup>';
    	             html += '    <thead>';
    	             html += '        <tr>';
    	             html += '            <th scope="col">순위</th>';
    	             html += '            <th scope="col">시군별</th>';
    	             html += '            <th scope="col">혼잡도</th>';
    	             html += '            <th scope="col">교통량</th>';
    	             html += '            <th scope="col">속도</th>';
    	             html += '        </tr>';
    	             html += '    </thead>';
    	             html += '    <tbody>';
    	           	for(var i = 0; i < tableData.length; i++){
    					var delayStatus = tableData[i].delayStts;
    					var delay = "";
    					if(delayStatus == 1){
    						delay = "원활";
    					} else if(delayStatus == 2){
    						delay = "지체(서행)";
    					} else {
    						delay = "정체";
    					}
    		             html += '        <tr>';
    		             html += '            <td>'+(i+1)+'</td>';
    		             html += '            <td>'+tableData[i].adsiNm+'</td>';
    		             html += '            <td>'+delay+'</td>';
    		             html += '            <td>'+tableData[i].vhclTrfvlm.toLocaleString("ko-KR")+'</td>';
    		             html += '            <td>'+tableData[i].avgVhclSpeed.toLocaleString("ko-KR")+'km/h</td>';
    		             html += '        </tr>';
    	           	}
    	             html += '    </tbody>';
    	             html += ' </table>';
    	             $("#delay_table").append(html);
            		break;
            		
            	}
            }
        })
	});
	
	$(".rollbackBtn").on('click',function(){
		var $this = $(this);
		
		html = "";
    	switch($this.data('value')){
    	case "trfVlm":
    		$("#trfVlm_chart_div").empty();
    		$("#trfVlm_table").empty();
    		
    		html += '<canvas id="trfVlm_chart"></canvas>';
    		$("#trfVlm_chart_div").append(html);
    		break
    	case "speed":
    		$("#speed_chart_div").empty();
    		$("#speed_table").empty();
    		
    		html += '<canvas id="speed_chart"></canvas>';
    		$("#speed_chart_div").append(html);
    		break;
    	case "delay":
    		$("#delay_chart_div").empty();
    		$("#delay_table").empty();
    		
    		html += '<canvas id="delay_chart"></canvas>';
    		$("#delay_chart_div").append(html);
    		break;
    	}
	});
	
	
</script>