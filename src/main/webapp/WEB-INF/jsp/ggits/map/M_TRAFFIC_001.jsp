<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="tab_box_chart">
    <div class="tab_box_chart_header">
        <fmt:formatNumber value="${trafficInfo.averageCnt}" pattern="#,###" />대 (${beforeTime} ~ ${currentTime})<br>
        <span id="compareText"></span>
    </div>
    <div class="tab_box_chart_content">
        <canvas id="m_traffic_001_chart" width="400" height="150" style="display: block; box-sizing: border-box; height: 150px; width: 400px;"></canvas>
    </div>
</div>
<!-- 
<div class="tab_box_chart_btn">
    <ul>
        <li>
            <label><input type="checkbox" class="none">시군</label>
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
    <c:forEach var="trafficList" items="${trafficInfo.trafficList}" varStatus="status">
	    <tr>
	        <td>${status.index+1}</td>
	        <td>${trafficList.vdsSctnNm}</td>
	        <td>${trafficList.totalCnt}대</td>
	    </tr>
    </c:forEach>
    </tbody>
</table>
 -->
<script>
	var graphTime = '${trafficInfo.graphTime}';
	var graphCnt = '${trafficInfo.graphCnt}';
	var compareCnt = '${trafficInfo.compareCnt}';
	
	if(compareCnt > 0){
		$("#compareText").css("color","#58EDD2");
		$("#compareText").text("전일 동시간 대비 "+compareCnt+"% ▲");
	}else{
		compareCnt = compareCnt * -1;
		$("#compareText").text("전일 동시간 대비 "+compareCnt+"% ▼");
	}
    new GITSChart(GITSChartType.BAR).init("m_traffic_001_chart")
        .setData({
            labels: graphTime.split(','),
            datasets: [{
                data: graphCnt.split(','),
                backgroundColor: '#58edd2',
                borderRadius:2,
                borderWidth:8,
                fill: false,
            }]
        })
        .setTicksStep(200)
        .setLabelDisplay(false)
        .setBarGridY(true)
        .draw();
</script>