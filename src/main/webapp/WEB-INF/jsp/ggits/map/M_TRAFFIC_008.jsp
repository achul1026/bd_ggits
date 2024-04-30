<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<form id="chartDataForm">
    <div class="tab_box_body_wrap" style="min-width:700px">
    	<div class="right mb8">
	        <a class="is-darkgreen-btn" href="javascript:void(0)" onclick="loadingChart()">결과보기/새로고침</a>
    	</div>
        <div class="tab_box_chart">
            <div class="tab_box_chart_content has-preloading">
                <canvas id="m_traffic_008_chart" width="400" height="150" style="display: block; box-sizing: border-box; height: 150px; width: 400px;"></canvas>
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
            url : __contextPath__+"/map/monitoring/traffic/vhclDivChartData.ajax",
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
                    $(".tab_box_chart_content.has-preloading").append(GITS_ENV.UI.CHART_PRELOADING("데이터를 수집중입니다. 잠시 후 시도해주세요."));
                    return;
                }
                
                let sggGroupData = data.reduce((acc, curr) => {
                	const { mnginstcd } = curr;
                    if (acc[mnginstcd]) acc[mnginstcd].push(curr);
                    else acc[mnginstcd] = [curr];
                    return acc;
                }, {});

                let vhclDivLabel = ["소형","버스","화물","확인불가"];

                let dataSets = [];
                let dataKey = "trfvol";
                
                for(const sggNm in GITS_ENV.SGG_INFO){
                    const staticSggInfo = GITS_ENV.SGG_INFO[sggNm];
                    if(typeof sggGroupData[staticSggInfo.MNGCD] !== "undefined") {
                        let data = [];
                        for(const vhclDiv of vhclDivLabel) {
                            const d = sggGroupData[staticSggInfo.MNGCD].find((d) => d['vhcldiv'] === vhclDiv);
                            if (d) {
                                data.push(d[dataKey]);
                            } else {
                                data.push(0);
                            }
                        }
                        let dataSet = {
                            label:sggNm,
                            data: data,
                            backgroundColor: staticSggInfo.COLOR,
                            stacked : false,
                            fill : true
                        };
                        dataSets.push(dataSet);
                    }
                }
//                 console.log(dataSets);
//                 console.log(vhclDivLabel);
            	
                window.monitoringChart = dataChart = new GITSChart(GITSChartType.BAR).init("m_traffic_008_chart")
                .setDataSetArrayLabel(vhclDivLabel)
                .setDataArraySet(dataSets)
                    .setOption({
                        indexAxis: 'x',
                        plugins: {
                            legend: {
                                usePointStyle:false,
                                labels: {
                                    color:"#fff",
                                    padding:10,
                                    boxWidth:5,
                                    font: {
                                        family:'Pretendard',
                                        size:10,
                                    }
                                },
                            },
                            datalabels: {
                                anchor: 'middle', // remove this line to get label in middle of the bar
                                align: 'end',
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
                            mode: 'index',
                            axis: 'x',
                            intersect: false
                        },
                        scales : {
                            x : {
                                ticks : {
                                    autoSkip : false,
                                    color:"white",
                                    font: {
                                        size : 12
                                    },
                                }
                            },
                            y : {
                                ticks : {
                                    autoSkip : false,
                                    color:"white",
                                    font: {
                                        size : 12
                                    }
                                },
                                grid: {
                                    color : "rgba(255,255,255,0.2)",
                                    tickColor : "rgba(255,255,255,0.2)",
                                    display:true
                                }
                            }
                        }
                    })
				// .setTickStepX(100)
				// .setBarGridX(true)
				.draw();
            }
        })
    
    }
	
    loadingChart();
</script>