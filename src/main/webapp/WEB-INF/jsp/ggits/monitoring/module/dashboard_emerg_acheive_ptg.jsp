<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="flex-between baseline">
    <div class="mini_dash_box_title">실시간 광역 긴급차량 분석 정보</div>
<!--     <div class="mini_dash_box_title"><span id="compareCnt">0</span>%</div> -->
    <div class="mini_dash_box_title">일일 총 평균 단축시간 <span id="avgDifferentTime">0</span>초</div>
</div>
<div class="flex-between">
    <div class="mini_time">최근 갱신 시간(<c:out value="${nowStr}"/>)</div>
</div>
<div id="emergChartDiv" class="mini_chart_wrap is-absolute">
    <div class="mini_chart dashboard_scroll"  style="max-height:none;height:100%;">
    <canvas id="emerg_acheive_chart"></canvas>
    <div>
</div>
<script>

	$(document).ready(function(){
		var chartHtml = '<canvas id="emerg_acheive_chart"></canvas>';
		var dataNullHtml = '<div> 조회된 차트 데이터가 없습니다.</div>';
		var avgDifferentTime = '<c:out value="${emergAcheivePtg.avgDifferentTime}"/>';
		$("#avgDifferentTime").text(avgDifferentTime);
		
	<c:choose>
		<c:when test="${emergAcheivePtg != null}">
		var evnoArr = '<c:out value="${emergAcheivePtg.evnoArr}"/>';
		var avgSrvcTimeArr = '<c:out value="${emergAcheivePtg.avgSrvcTimeArr}"/>';
		var avgArvlPrnmntTimeArr = '<c:out value="${emergAcheivePtg.avgArvlPrnmntTimeArr}"/>';
		var differnceTimeArr = '<c:out value="${emergAcheivePtg.differnceTimeArr}"/>';
		var firenameArr = '<c:out value="${emergAcheivePtg.firenameArr}"/>';
		
		var evnoList = evnoArr.split(',');
		
		    new GITSChart(GITSChartType.LINE).init("emerg_acheive_chart")
		        .setDataSetArrayLabel(firenameArr.split(","))
		        .setDataSet({
		            label : '예측시간(초)',
		            data : avgArvlPrnmntTimeArr.split(','),
		            backgroundColor: 'rgba(255,0,0,0.5)',
		            borderColor : 'rgba(255,0,0,0.5)',
		            borderRadius:1,
		                pointStyle: 'circle',
		                pointRadius: 2,
		                order :2,
                        tension: 0.4
		        },
		            {
		                label : '실제시간(초)',
		                data : avgSrvcTimeArr.split(','),
		                backgroundColor: 'rgba(63,191,222,0.7)',
		                borderColor : 'rgba(63,191,222,0.7)',
		                borderRadius:1,
		                pointStyle: 'rectRot',
		                pointRadius: 2,
		                order :1,
                        tension: 0.4
		            },
		            /*{
		                label : '격차(초)',
		                data : differnceTimeArr.split(','),
		                backgroundColor:   function(c) {
		                    const value = c.dataset.data[c.dataIndex];
		                    if (value < 0) {
		                        return 'rgb(5,239,71)'
		                    } else {
		                        return 'rgb(203,2,248)'
		                    }
		                } ,
		                borderRadius:1,
		                type : "bar",
		                order :0,
		                barPercentage : 0.5
		            }*/)
		        .setOption({
		            plugins: {
		                legend: {
		                    labels: {
		                        usePointStyle: true,
		                        color : "white"
		                    }
		                }
		                ,tooltip: {
		                    callbacks: {
		                    	 title: function(tooltipItems, data) {
		                    		 //서비스네임으로 hover 커스텀
		                    		 
		                             var dataIndex = tooltipItems[0].dataIndex;
		                             
		                             var titleVal = evnoList[dataIndex];

		                             return titleVal;
		                         }
		                    }
		                }
		            	},
		            interaction: {
		                mode: 'nearest',
		                axis: 'x',
		                intersect: false
		            },
                    scales: {
                        y : {
                          ticks : {
                              stepSize : 10
                          }
                        },
                        x: {
                            ticks: {
                                display:true,
                                color :'#fff',
                                maxRotation: 50,
                                minRotation: 50,
                                font: {
                                    size : 8
                                }
                            }
                            ,grid: {
                                color : "rgba(255,255,255,0.1)",
                                tickColor : "rgba(255,255,255,0.1)",
                                display:true
                            }
                        }
                    },		        	
		        })
		        .draw();
		  </c:when>
		  <c:otherwise>
			$("#emergChartDiv").empty().append(dataNullHtml);
		  </c:otherwise>
	  </c:choose>
	});
</script>