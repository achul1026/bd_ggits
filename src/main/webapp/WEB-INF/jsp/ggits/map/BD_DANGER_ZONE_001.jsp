<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bigdata_wrap">
    <div class="sub_data_list tab_set mj0">
    	<div class="tab_fc pd16">
	        <ul>
	            <li><button type="button" class="sub_data_btn dataOption" data-index="1" data-value="outbreak">도로 안전 위험 순위</button></li>
	            <li><button type="button" class="sub_data_btn dataOption" data-index="2" data-value="accident">교통사고 위험<br>지역 순위</button></li>
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
	                		<button type="button" class="prev_text rollbackBtn"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-value="city"><input type="checkbox" name="outbreakDataOption" class="none" value="city">시군 별</label>
<!-- 	                        <label class="group_btn_item is-dark-btn radius inpd" data-value="town"><input type="checkbox" name="outbreakDataOption" class="none" value="town">읍면동 별</label> -->
	                    </div>
	                    <div id="dangerZoneRoadDiv" class="tab_item_box" style="width:450px; height:300px;">
	                        <canvas id="dangerZoneRoad"></canvas>
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
	                		<button type="button" class="prev_text rollbackBtn"><span class="prev_arrow">←</span> 이전</button>
	                	</div>
	                    <div class="tab_item_box flex-center">
	                        <h5 class="tab_item_title">기준</h5>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-value="city"><input type="checkbox" name="accidentDataOption" class="none" value="city">시군 별</label>
	                        <label class="group_btn_item is-dark-btn radius inpd" data-value="town"><input type="checkbox" name="accidentDataOption" class="none" value="town">읍면동 별</label>
	                    </div>
	                    <div class="tab_item_box">
                            <div id="accidentChartDiv" class="mt8" style="width:450px; height:300px;">
		                        <canvas id="accidentChart"></canvas>
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
	
//     $("input[name='outbreakDataOption']").on('click',function(){
// 		var $this = $(this);
// 		var dataOption = $this.parents('label').data('value');
		
// 		outbreakChartInit(dataOption);
// 	});

    $("input[name='accidentDataOption']").on('click',function(){
		var $this = $(this);
		var dataOption = $this.parents('label').data('value');
		
		accidentChartInit(dataOption);
	});
	
	$(".dataOption").on('click',function(){
		$this = $(this);
		
		//초기세팅
		switch($this.data('value')){
		case "outbreak":
			var dataOptionFirstChild = $("input[name='outbreakDataOption']").eq(0);
			dataOptionFirstChild.prop("checked",true);
			dataOptionFirstChild.parent('label').addClass('is-darkgreen-btn');
			
			outbreakChartInit(dataOptionFirstChild.val());
			
			break;
		case "accident":
			var dataOptionFirstChild = $("input[name='accidentDataOption']").eq(0);
			
			dataOptionFirstChild.prop("checked",true);
			dataOptionFirstChild.parent('label').addClass('is-darkgreen-btn');

			accidentChartInit(dataOptionFirstChild.val());
			break;
		}
	});
	
	function outbreakChartInit(dataOption){
		$.ajax ({
            type : "post",
            data : {
            	"dataCtgry" : "outbreak",
            	"dataOption" : dataOption
            },
            url : "${pageContext.request.contextPath}/map/bigdata/loadDangerZoneData.ajax",
            cache : false,
            dataType : "json",
            success : function(result) {
            	$("#dangerZoneRoadDiv").empty();
            	html = '<canvas id="dangerZoneRoad"></canvas>';
            	$("#dangerZoneRoadDiv").append(html);
            	
            	var chartData = result.data.objectData;
            	
            	//위험구역 차트
                new GITSChart(GITSChartType.BAR).init("dangerZoneRoad")
                .setDataSetArrayLabel(chartData.chartLabel.split(","))
                .setDataSet({
                        label: '안전거리미확보',
                        data:chartData.notSafetyChartData.split(","),
                        backgroundColor: '#E7A600',
                    },{
                        label: '과속',
                        data:chartData.speedingChartData.split(","),
                        backgroundColor: '#FF2E00',
                    },{
                        label: '급커브(굽은도로)',
                        data:chartData.sharpCurveChartData.split(","),
                        backgroundColor: '#FF6332',
            	    },{
            	        label: '급경사(내리막)',
            	        data:chartData.scarpChartData.split(","),
            	        backgroundColor: '#00B288',
            	    },{
            	        label: '미끄러운도로',
            	        data:chartData.slipperyChartData.split(","),
            	        backgroundColor: '#00B65E',
            	    },{
            	        label: '침수',
            	        data:chartData.floodingChartData.split(","),
            	        backgroundColor: '#7A77FF',
	                },{
	        	        label: '기상-비',
	        	        data:chartData.rainChartData.split(","),
	        	        backgroundColor: '#0085FF',
	            	},{
		    	        label: '기상-눈',
		    	        data:chartData.snowChartData.split(","),
		    	        backgroundColor: '#78BEFF',
			        },{
				        label: '기상-결빙',
				        data:chartData.frostRoadChartData.split(","),
				        backgroundColor: '#00B4ED',
				    })
                .setTickStepX(200)
                .setAxis('y')
                .setBarGridX(true)
                .draw();
            }
		})
	}
	
	function accidentChartInit(dataOption){
		$.ajax ({
            type : "post",
            data : {
            	"dataCtgry" : "accident",
            	"dataOption" : dataOption
            },
            url : "${pageContext.request.contextPath}/map/bigdata/loadDangerZoneData.ajax",
            cache : false,
            dataType : "json",
            success : function(result) {
            	$("#accidentChartDiv").empty();
            	html = '<canvas id="accidentChart"></canvas>';
            	$("#accidentChartDiv").append(html);
            	
            	var chartData = result.data.objectData;
            	
            	//사고 차트 데이터
                new GITSChart(GITSChartType.BAR).init("accidentChart")
                .setDataSetArrayLabel(chartData.chartLabel.split(","))
                .setDataSet({
                        label: '자전거 사고',
                        data:chartData.bcyclChartData.split(","),
                        backgroundColor: '#8AD22E',
                    },{
                        label: '결빙 사고 구역',
                        data:chartData.frostChartData.split(","),
                        backgroundColor: '#397DFF',
                    },{
                        label: '음주 사고 구역',
                        data:chartData.drnkgChartData.split(","),
                        backgroundColor: '#FA5430',
                    },{
                        label: '이륜차 사고 구역',
                        data:chartData.twhlvhChartData.split(","),
                        backgroundColor: '#1DCEE7',
                    },{
                        label: '보행자 사고 구역',
                        data:chartData.pdsnChartData.split(","),
                        backgroundColor: '#FF7A00',
                    },{
                        label: '화물차 사고 구역',
                        data:chartData.truckChartData.split(","),
                        backgroundColor: '#FC67FF',
                	},{
                        label: '법위반 보행자 사고 구역',
                        data:chartData.lawvltnChartData.split(","),
                        backgroundColor: '#00C09E',
                	},{
                        label: '무단횡단 보행자 사고 구역',
                        data:chartData.jaywkChartData.split(","),
                        backgroundColor: '#DF8D41',
                	},{
                        label: '휴일기간 보행자 사고 구역',
                        data:chartData.hldyChartData.split(","),
                        backgroundColor: '#20CC27',
                	},{
                        label: '노인 보행자 사고 구역',
                        data:chartData.olmanChartData.split(","),
                        backgroundColor: '#21CC27',
                	})
                .setTickStepX(100)
                .setAxis('y')
                .setBarGridX(true)
                .draw();
            }
		})
		
	}
</script>