<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<form id="chartDataForm">
<div class="tab_item_box flex-center">
	<h5 class="tab_item_title">수집원별</h5>
	<select class="selectBox radius result_change change-detect" name="collectType">
		<option value="vds">VDS</option>
		<option value="dsrc">DSRC</option>
		<option value="smc">스마트교차로</option>
	</select>
</div>
<div class="tab_item_box flex-center">
	<h5 class="tab_item_title">주기</h5>
	<select class="selectBox radius result_change change-detect" name="collectTimeType">
		<option value="fivemin">5분 주기</option>
		<option value="fifteenmin">15분 주기</option>
		<option value="onehour">1시간 주기</option>
	</select>
</div>
<div class="tab_box_body_wrap">
	<div class="tab_box_chart">
	    <div class="tab_box_chart_content has-preloading">
	        <canvas id="m_traffic_002_chart"></canvas>
	    </div>
	</div>
</div>
</form>

<script>
	let dataChart = null;
	function loadingChart(){
		dataChart ? dataChart.destroy() : void(0);
		$.ajax({
			type : "get",
			url : __contextPath__+"/map/monitoring/traffic/M_TRAFFIC_002/chartData.ajax",
			data : $("#chartDataForm").serialize(),
			beforeSend : function(){
				$(".tab_box_chart_content.has-preloading .chart-preloading-wrap").remove();
				$(".tab_box_chart_content.has-preloading").append(GITS_ENV.UI.CHART_PRELOADING());
			},
			error : function(){
				$(".tab_box_chart_content.has-preloading .chart-preloading-wrap").remove();
			},
			success : function(data){
				$(".tab_box_chart_content.has-preloading .chart-preloading-wrap").remove();
				if(data.length === 0) {
					$(".tab_box_chart_content.has-preloading").append(GITS_ENV.UI.CHART_PRELOADING("데이터를 수집중입니다. 잠시 후 접속해주세요."));
					return;
				}
				let sggGroupData = data.reduce((groups, item) => {
					const group = (groups[item.sggCd+"0"] || []);
					group.push(item);
					groups[item.sggCd+"0"] = group;
					return groups;
				});
				let timeLabel = []
				data.forEach((item) => {
					if(timeLabel.indexOf(item.time) == -1) timeLabel.push(item.time);
				});
				timeLabel = timeLabel.sort(function(d1, d2){
					const d1Time = new Date(d1).getTime();
					const d2Time = new Date(d2).getTime();
					return d1Time - d2Time;
				});
				let dataSets = [];
				let dataKey = "avgSpeed";
				for(const sggNm in GITS_ENV.SGG_INFO){
					const staticSggInfo = GITS_ENV.SGG_INFO[sggNm];
					if(typeof sggGroupData[staticSggInfo.CODE] !== "undefined") {
						let data = [];
						for(const time of timeLabel) {
							const d = sggGroupData[staticSggInfo.CODE].find((d) => d['time'] === time);
							if(d) {
								data.push(d[dataKey]);
							}else{
								data.push(0);
							}
						}
						let dataSet = {
							label:sggNm,
							data: data,
							backgroundColor: staticSggInfo.COLOR,
							borderColor : staticSggInfo.COLOR,
							borderRadius:2,
							borderWidth:1,
							fill: false,
						};
						dataSets.push(dataSet);
					}
				}
				dataChart = new GITSChart(GITSChartType.LINE).init("m_traffic_002_chart")
						.setDataSetArrayLabel(timeLabel)
						.setDataArraySet(dataSets)
						.setOption({
							interaction: {
								mode: 'nearest',
								axis: 'x',
								intersect: false
							},
						})
						.setTicksStep(10)
						.draw();
			}
		})
	}
	loadingChart();
	$(".change-detect").change(loadingChart);
</script>