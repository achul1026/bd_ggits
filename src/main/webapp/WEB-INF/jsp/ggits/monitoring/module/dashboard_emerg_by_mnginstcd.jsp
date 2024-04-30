<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysYmd"><fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일" /></c:set>
<div class="flex-between baseline">
    <div class="mini_dash_box_title">지자체별 긴급차량 발생건수</div>
    <div class="mini_dash_box_title">
    	총 <span id="sggCnt">0</span> 개 지자체/ <span id="totalCnt">0</span> 건
    </div>
</div>
<div class="flex-between">
    <div class="mini_time">${sysYmd} 집계 데이터 정보(00:00 ~ 24:00)</div>
</div>
<div class="mini_chart_wrap  is-absolute">
<div id="emergCityChartDiv" class="mini_chart dashboard_scroll" style="max-height: none;height:100%;">
    	<canvas id="emerg_by_mnginstcd_chart"></canvas>
</div>
</div>


<%--         <c:forEach var="emergByMnginstcdItem" items="${emergByMnginstcdList}"> --%>
<%--         	<c:set var="totalCnt" value="${totalCnt + emergByMnginstcdItem.cnt}"/> --%>
<!-- 	        <div class="mini_dashboard_card_wrap is-twice"> -->
<!-- 	            <div class="mini_dashboard_card flex-card"> -->
<%-- 	                <p class="mini_dashboard_card_title"> <c:out value="${emergByMnginstcdItem.cd_nm}"/> </p> --%>
<!-- 	                <p class="mini_dashboard_card_value_wrap"> -->
<%-- 	                    <span class="mini_dashboard_card_value emerg-cnt comma"><c:out value="${emergByMnginstcdItem.cnt}"/></span> --%>
<!-- 	                    <span>건</span> -->
<!-- 	                </p> -->
<!-- 	            </div> -->
<!-- 	        </div> -->
<%--         </c:forEach> --%>
<!--         <div class="mini_dashboard_card_wrap is-twice"> -->
<!--             <div class="mini_dashboard_card flex-card"> -->
<!--                 <p class="mini_dashboard_card_title">고양시</p> -->
<!--                 <p class="mini_dashboard_card_value_wrap"> -->
<!--                     <span class="mini_dashboard_card_value emerg-cnt" data-sggcode="41280">0</span> -->
<!--                     <span>건</span> -->
<!--                 </p> -->
<!--             </div> -->
<!--         </div> -->
<!--         <div class="mini_dashboard_card_wrap is-twice"> -->
<!--             <div class="mini_dashboard_card flex-card"> -->
<!--                 <p class="mini_dashboard_card_title">파주시</p> -->
<!--                 <p class="mini_dashboard_card_value_wrap"> -->
<!--                     <span class="mini_dashboard_card_value emerg-cnt" data-sggcode="41480">0</span> -->
<!--                     <span>건</span> -->
<!--                 </p> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->
<!-- </div> -->
<script>
	$(document).ready(function(){
		var commaEl = $(".comma");
		var totalCnt = '<c:out value="${totalCnt}"/>';
		
		for(var i = 0; i < commaEl.length; i++){
			var commaElStr = commaEl.eq(i).text();

			commaEl.eq(i).text(numberComma(commaElStr));
		}
		
		$("#totalCnt").text(numberComma(totalCnt));
	});
	
   	// emerg_by_mnginstcd_chart
    var labelArrayStr = '${labelArray}';
    var dataArrayStr = '${dataArray}';
    
    var labelArray = JSON.parse(labelArrayStr);
    var dataArray = JSON.parse(dataArrayStr);
    var colorArray = new Array();
    var totalCnt = 0;
    
    for(var i = 0; i < labelArray.length; i++){
    	const staticSggInfo = GITS_ENV.SGG_INFO[labelArray[i]];
    	const emrgCnt = Number(dataArray[i]);
    	totalCnt += emrgCnt;
    	colorArray.push(staticSggInfo.COLOR);
    }
    
    $("#sggCnt").text(numberComma(labelArray.length));
    $("#totalCnt").text(numberComma(totalCnt));
	
	new GITSChart(GITSChartType.DOUGHNUT).init("emerg_by_mnginstcd_chart")
        .setViewLabel()
    .setData({
             labels : labelArray,
             datasets: [{
            	 label : labelArray,
            	 data : dataArray,
            	 backgroundColor : colorArray,
                 datalabels: {
                     anchor: 'end'
                 }
             }],
         })
        .setOption({
            plugins : {
                legend : {
                  position : "right",
                    labels: {
                        usePointStyle: true,
                        color : "white"
                    },
                },
                datalabels: {
                    backgroundColor: function(context) {
                        return context.dataset.backgroundColor;
                    },
                    borderColor: 'white',
                    borderRadius: 25,
                    borderWidth: 2,
                    color: 'white',
                    display: function(context) {
                        var dataset = context.dataset;
                        var count = dataset.data.length;
                        var value = dataset.data[context.dataIndex];
                        return true;
                        //return value > count * 1.5;
                    },
                    font: {
                        weight: 'bold'
                    },
                    padding: 6,
                    formatter: Math.round
                }
            },
            cutoutPercentage: 32,
            layout: {
                padding: 16
            },
            elements: {
                line: {
                    fill: false
                },
                point: {
                    hoverRadius: 7,
                    radius: 5
                }
            },
        })
	.draw();

//     try {
//         let emergByMnginstcdString = '${emergByMnginstcd}';
//         let emergByMnginstcdList = JSON.parse(emergByMnginstcdString);
//         let emergCnt = {
//             "41480" : 0, // 파주시
//             "41280" : 0, // 고양시
//         };
//         const SGG_FEATURE_GOYANG = GITS_ENV.SGG_INFO['고양시'].FEATURE;
//         const SGG_FEATURE_PAJU = GITS_ENV.SGG_INFO['파주시'].FEATURE;
//         console.log(emergByMnginstcdList);
//         for(let item of emergByMnginstcdList) {
//             const point = {
//                 "type": "Feature",
//                 "properties": {
//                     "marker-color": "#0f0"
//                 },
//                 "geometry": {
//                     "type": "Point",
//                     "coordinates": [item.currentlng, item.currentlat]
//                 }
//             }
//             if(turf.inside(point, SGG_FEATURE_GOYANG)) emergCnt['41280']++;
//             if(turf.inside(point, SGG_FEATURE_PAJU)) emergCnt['41480']++;
//         }
//         console.log(emergByMnginstcdList);
//         for(let code in emergCnt) {
//             $(".emerg-cnt[data-sggcode='"+code+"']").text(numberComma(emergCnt[code]));
//         }
//     }catch(e){
//         console.error(e);
//     }
</script>