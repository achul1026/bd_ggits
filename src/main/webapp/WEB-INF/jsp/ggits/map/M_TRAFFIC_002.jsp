<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="tab_box_chart">
    <div class="tab_box_chart_header">
        ${trafficInfo.averageCnt}km/h (${beforeTime} ~ ${currentTime})
        <br>
        <span id="compareText"></span>
    </div>
    <div class="tab_box_chart_content">
        <canvas id="section1_sidebox2_chart"></canvas>
    </div>
</div>
<!--
<div class="tab_box_chart_btn">
    <ul>
        <li>
            <label ><input type="checkbox" class="none">시군</label>
        </li>
        <li>
            <label><input type="checkbox" class="none">읍면동</label>
        </li>
        <li>
            <label><input type="checkbox" class="none">도로</label>
        </li>
    </ul>
</div>
<table class="monitor_table center_table">
	<colgroup>
		<col width="16%">
		<col width="64%">
		<col width="20%">
	</colgroup>
	<tbody>
		  <tr>
			  <td>1</td>
			  <td>광명IC → 일직JC</td>
			  <td>4579대</td>
		  </tr>
		  <tr>
			  <td>2</td>
			  <td>광명IC → 일직JC</td>
			  <td>4579대</td>
		  </tr>
		  <tr>
			  <td>3</td>
			  <td>광명IC → 일직JC</td>
			  <td>4579대</td>
		  </tr>
	</tbody>
</table>
 -->
<script>
	
	var graphTime = '${trafficInfo.graphTime}';
	var graphCnt = '${trafficInfo.graphCnt}';
	var compareCnt = '${trafficInfo.compareCnt}';
	
	if(compareCnt > 0){
		$("#compareText").css("color","#58EDD2");
		$("#compareText").text("전일 동시간 대비 "+compareCnt+"km/h ▲");
	}else{
		compareCnt = compareCnt * -1;
		$("#compareText").text("전일 동시간 대비 "+compareCnt+"km/h ▼");
	}
	
	new GITSChart(GITSChartType.BAR).init("section1_sidebox2_chart")
	.setData({
	    labels: graphTime.split(','),
	    datasets: [{
	        data:graphCnt.split(','),
	        backgroundColor: '#58edd2',
	        borderRadius:2,
	        borderWidth:8,
	        fill: false,
	    }]
	})
	.setTicksStep(100)
	.setLabelDisplay(false)
	.setBarGridY(true)
	.draw();
</script>