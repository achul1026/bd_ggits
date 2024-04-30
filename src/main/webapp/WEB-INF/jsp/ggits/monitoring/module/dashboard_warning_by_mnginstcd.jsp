<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysYmd"><fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일" /></c:set>
<div class="flex-between baseline">
    <div class="mini_dash_box_title">수집기관별 돌발현황 발생 집계건수</div>

    <div class="mini_dash_box_title">
        총 <span id="warningTotalCnt">0</span> 건
    </div>
</div>
<div class="flex-between">
    <div class="mini_time">${sysYmd} 집계 데이터 정보(00:00 ~ 24:00)</div>
</div>
<div class="mini_chart_wrap  is-absolute">
<div id="warningChartDiv" class="mini_chart dashboard_scroll" style="max-height:none;height:100%;">
    <canvas id="warning_chart"></canvas>
</div>
</div>

<%--<div class="mini_chart_wrap">
    <div class="dashboard_scroll clearfix mini_dashboard_card_container" style="max-height: 13rem">
        <div class="mini_dashboard_card_wrap">
            <div class="mini_dashboard_card">
                <p class="mini_dashboard_card_title">경기도교통센터</p>
                <p class="mini_dashboard_card_value_wrap">
                    <span class="mini_dashboard_card_value" id="GITS_CNT">0</span>
                    <span>건</span>
                </p>
            </div>
        </div>
        <div class="mini_dashboard_card_wrap">
            <div class="mini_dashboard_card">
                <p class="mini_dashboard_card_title">도로교통공단</p>
                <p class="mini_dashboard_card_value_wrap">
                    <span class="mini_dashboard_card_value" id="UTIS_CNT">0</span>
                    <span>건</span>
                </p>
            </div>
        </div>
        <div class="mini_dashboard_card_wrap">
            <div class="mini_dashboard_card">
                <p class="mini_dashboard_card_title">T-map</p>
                <p class="mini_dashboard_card_value_wrap">
                    <span class="mini_dashboard_card_value" id="SK_CNT">0</span>
                    <span>건</span>
                </p>
            </div>
        </div>
        <div class="mini_dashboard_card_wrap">
            <div class="mini_dashboard_card">
                <p class="mini_dashboard_card_title">도로공사</p>
                <p class="mini_dashboard_card_value_wrap">
                    <span class="mini_dashboard_card_value" id="EX_CNT">0</span>
                    <span>건</span>
                </p>
            </div>
        </div>
        <div class="mini_dashboard_card_wrap">
            <div class="mini_dashboard_card">
                <p class="mini_dashboard_card_title">경기소방본부</p>
                <p class="mini_dashboard_card_value_wrap">
                    <span class="mini_dashboard_card_value" id="119_CNT">0</span>
                    <span>건</span>
                </p>
            </div>
        </div>
        <div class="mini_dashboard_card_wrap">
            <div class="mini_dashboard_card">
                <p class="mini_dashboard_card_title">경기도 내 터널</p>
                <p class="mini_dashboard_card_value_wrap">
                    <span class="mini_dashboard_card_value" id="SISUL_CNT">0</span>
                    <span>건</span>
                </p>
            </div>
        </div>
    </div>
</div>--%>
<script>
    let warningDataArray = [0,0,0,0,0,0];
    let warningTotalCnt = 0;
<c:if test="${not empty warningByMnginstcd}">

		<c:forEach var="warningByMnginstcdItem" items="${warningByMnginstcd}">
			var totalCnt = '<c:out value="${warningByMnginstcdItem.totalCnt}"/>';
            warningTotalCnt += parseInt(totalCnt);
			var infoSrcOrg = '<c:out value="${warningByMnginstcdItem.infoSrcOrg}"/>';
			switch(infoSrcOrg){
			case "GITS":
				/*$("#GITS_CNT").text(numberComma(totalCnt));*/
                warningDataArray[0] = totalCnt;
				break;
			case "UTIS":
            case "UTIC":
				/*$("#UTIS_CNT").text(numberComma(totalCnt));*/
                warningDataArray[1] = totalCnt;
				break;
			case "SK":
                warningDataArray[2] = totalCnt;
				break;
			case "EX":
                warningDataArray[3] = totalCnt;
				break;
			case "119":
                warningDataArray[4] = totalCnt;
				break;
			case "SISUL":
                warningDataArray[5] = totalCnt;
				break;
			default :
				break;
			}
		</c:forEach>
</c:if>
    $("#warningTotalCnt").text(warningTotalCnt);
    let warningLabelArray = ['경기도교통센터','도로교통공단','T-map','도로공사','경기소방본부','경기도 내 터널'];

    new GITSChart(GITSChartType.DOUGHNUT).init("warning_chart")
        .setViewLabel()
        .setData({
            labels : warningLabelArray,
            datasets: [{
                label : warningLabelArray,
                data : warningDataArray,
                backgroundColor : [
                    GITS_ENV.INCI_COLOR['4'],
                    GITS_ENV.INCI_COLOR['3'],
                    GITS_ENV.INCI_COLOR['5'],
                    GITS_ENV.INCI_COLOR['7'],
                    GITS_ENV.INCI_COLOR['13'],
                    GITS_ENV.INCI_COLOR['6']
                ],
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
</script>
