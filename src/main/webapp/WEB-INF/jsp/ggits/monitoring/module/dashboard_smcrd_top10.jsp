<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysYmd"><fmt:formatDate value="${now}" pattern="yyyy년 MM월 dd일" /></c:set>
<div class="flex-between baseline">
	<div class="mini_dash_box_title">
		<span class="display-inline-block vm" style="color:white;">스마트교차로 교통량 TOP 10</span>
		<span class="tooltip-common" title="경기도 내 스마트교차로를 통해 수집된 교통량이 많은 도로 순위 현황입니다.">?</span>
	</div>
</div>
<div class="flex-between">
	<div class="mini_time">${sysYmd} 집계 데이터 정보(00:00 ~ 24:00)</div>
</div>
<div class="mini_chart_wrap is-absolute">
	<div class="mini_chart dashboard_scroll" style="max-height:none;height:100%;">
		<%--<table class="monitor_table">
            <colgroup>
                <col>
                <col>
                <col>
            </colgroup>
            <thead>
                <tr>
                    <th>관리기관</th>
                    <th>교차로명</th>
                    <th>교통량(대)</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${not empty smcrdTop10}">
                        <c:forEach var="smcrdTop10Item" items="${smcrdTop10}">
                            <tr>
                                <td><c:out value="${smcrdTop10Item.mngInstCd}"/></td>
                                <td><c:out value="${smcrdTop10Item.crsrdNm}"/></td>
                                <td class="comma"><c:out value="${smcrdTop10Item.vhclTrfvlm}"/></td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="3">
                                조회된 결과가 없습니다.
                            </td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>--%>
		<canvas id="smcrsdtop10_canvas"></canvas>
	</div>
</div>
<script>
	var smcrd_x = [];
	var smcrd_value = [];

	<c:forEach var="smcrdTop10Item" items="${smcrdTop10}">
	smcrd_x.push('${smcrdTop10Item.mngInstCd}-${smcrdTop10Item.crsrdNm}');
	smcrd_value.push(${smcrdTop10Item.vhclTrfvlm});
	</c:forEach>
	new GITSChart(GITSChartType.BAR).init("smcrsdtop10_canvas")
			.setViewLabel()
			.setDataSetArrayLabel(smcrd_x)
			.setDataSet({
				label : '교통량',
				data : smcrd_value,
				backgroundColor: function(context) {
					const chart = context.chart;
					const {ctx, chartArea} = chart;
					let gradient, width, height;
					if (!chartArea) {
						// This case happens on initial chart load
						return;
					}
					const chartWidth = chartArea.right - chartArea.left;
					const chartHeight = chartArea.bottom - chartArea.top;
					if (!gradient || width !== chartWidth || height !== chartHeight) {
						// Create the gradient because this is either the first render
						// or the size of the chart has changed
						width = chartWidth;
						height = chartHeight;
						gradient = ctx.createLinearGradient(0, chartArea.top, chartArea.right, chartArea.bottom);
						gradient.addColorStop(0, "#40ff00");
						gradient.addColorStop(0.5, "#FFFF00");
						gradient.addColorStop(1, "#ff003b");
					}

					return gradient;
				},
				borderRadius:1,
				fill: true
			}).setOption({
		indexAxis: 'y',
		plugins: {
			legend: {
				display:false,
				labels : {
					color : "white"
				}
			},
			datalabels: {
				anchor: 'end', // remove this line to get label in middle of the bar
				align: 'start',
				clamp : true,
				formatter: function(val) {
					return numberComma(val);
				},
				labels: {
					value: {
						color: '#fff'
					}
				}

			}
		},
		interaction: {
			mode: 'nearest',
			axis: 'x',
			intersect: false
		},
		scales : {
			y : {
				ticks : {
					autoSkip : false,
					color:"white"
				}
			},
			x : {
				ticks : {
					autoSkip : false,
					color:"white"
				},
				grid: {
					color : "rgba(255,255,255,0.2)",
					tickColor : "rgba(255,255,255,0.2)",
					display:true
				}
			}
		}
	})
			.draw();

	$(document).ready(function(){
		var commaEl = $(".comma");
		for(var i = 0; i < commaEl.length; i++){
			var commaElStr = commaEl.eq(i).text();

			commaEl.eq(i).text(numberComma(commaElStr));
		}
	});
</script>