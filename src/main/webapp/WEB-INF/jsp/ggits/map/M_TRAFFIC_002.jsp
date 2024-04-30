<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<form id="chartDataForm">
<div class="tab_item_box flex-center">
	<h5 class="tab_item_title">수집원별</h5>
	<select class="selectBox radius result_change change-detect" name="collectType">
		<option value="vds">VDS</option>
		<option value="dsrc">DSRC</option>
		<option value="smc">스마트교차로-접근로</option>
		<option value="smc-drct-mngcd">스마트교차로-방향별</option>
	</select>
</div>
<div class="tab_item_box flex-center">
	<h5 class="tab_item_title">주기</h5>
	<select class="selectBox radius result_change change-detect" name="collectTimeType">
		<option value="fivemin">5분 주기</option>
		<option value="fifteenmin">15분 주기</option>
		<option value="onehour">1시간 주기</option>
	</select>
	<a class="is-darkgreen-btn" href="javascript:void(0)" onclick="loadingChart()">결과보기/새로고침</a>
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
	let loadingAjax = null;
	let dataChart = null;
	function loadingChart(){
		if(loadingAjax) loadingAjax.abort();
		loadingAjax = $.ajax({
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
				dataChart ? dataChart.destroy() : void(0);
				$(".tab_box_chart_content.has-preloading .chart-preloading-wrap").remove();
				if(data.length === 0) {
					$(".tab_box_chart_content.has-preloading").append(GITS_ENV.UI.CHART_PRELOADING("데이터를 수집중입니다. 잠시 후 접속해주세요."));
					return;
				}
                let sggGroupData = data.reduce((acc, curr) => {
					const { mngInstCd } = curr;
					if (acc[mngInstCd]) acc[mngInstCd].push(curr);
					else acc[mngInstCd] = [curr];
					return acc;
				}, {});
				let timeLabel = []
				data.forEach((item) => {
					let onlytime = item.time.substring(11, 16);
					if(timeLabel.indexOf(onlytime) == -1) timeLabel.push(onlytime);
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
					if(typeof sggGroupData[staticSggInfo.MNGCD] !== "undefined") {
						let data = [];
						for(const time of timeLabel) {
							const d = sggGroupData[staticSggInfo.MNGCD].find((d) => d['time'].substring(11, 16) === time);
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
	// loadingChart();
	$(".tab_box_chart_content.has-preloading").append(GITS_ENV.UI.CHART_PRELOADING("결과보기/새로고침 버튼을 눌러주세요."));
	/*$(".change-detect").change(loadingChart);*/
</script>