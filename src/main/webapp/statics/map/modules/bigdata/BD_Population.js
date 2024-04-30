/**
 * 유동인구 밀집조회
 * @constructor
 * @param searchOption
 */
const BD_Population = async function(searchOption){
    let gridList = await self.util.getJsonFormApi("/bigdata/getPopulationInfo.ajax?"+searchOption);

    let chartDataList = [];
    /*let chartDataList = await self.util.getJsonFormApi("/bigdata/getPopulationInfoForChart.ajax?"+searchOption);
    if(chartDataList.length === 0) {
        return {
            error : true,
            errorMsg : "조회된 데이터가 없습니다."
        }
    }*/
    if(gridList?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    let sggNmGroupList = [];
    let sggCdGroupList = [];
    let timeGroupList = [
        "00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00",
        "12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00",
    ];
    let data = [];
    for(const data of chartDataList) {
        const sggInfo = self.util.getSGGInfoByCode(data.cityCd+"0",GITS_ENV);
        if(sggNmGroupList.indexOf(sggInfo.sggNm) === -1){
            sggNmGroupList.push(sggInfo.sggNm);
            sggCdGroupList.push(data.cityCd);
        }
    }
    let sggIdx = 0;
    for(const sggCode of sggCdGroupList) {
        for(let h = 0; h < 24; h++) {
            let hh = h;
            if(hh < 10) {
                hh = "0"+hh;
            }
            let d = chartDataList.filter((obj) => obj.cityCd == sggCode && obj.timezn == hh)[0];
            let dataset = {
                x : hh+":00",
                y : sggNmGroupList[sggIdx]
            }
            if(d) {
                dataset.v = d.fltPop
            }else{
                dataset.v = 0
            }
            data.push(dataset);
        }
        sggIdx++;
    }



    const chartHeight = sggNmGroupList.length * 10;
    //매트릭스 차트데이터 가공
    let chartOption = {
        type : "matrix",
        data : {
            datasets : [{
                label : "인구밀집예측",
                data : data,
                borderWidth: 1
            }]
        },
        options: {
            events : ['mousemove', 'mouseout', 'click', 'touchstart', 'touchmove', 'scroll'],
            aspectRatio: sggNmGroupList.length == 1 ? 5 : 0.7,
            plugins: {
                legend: false,
                tooltip: {
                    displayColors: false,
                    callbacks: {}
                },
            },
            scales: {
                x: {
                    type: 'category',
                    labels: timeGroupList,
                    ticks: {
						color:"#fff",
                        display: true
                    },
                    grid: {
                        display: false
                    },
                    position: 'top'
                },
                y: {
                    type: 'category',
                    labels: sggNmGroupList,
                    offset: true,
                    ticks: {
						color:"#fff",
                        display: true
                    },
                    grid: {
                        display: false
                    }
                }
            }
        }
    };

    return {
        gridList : gridList,
        matrixChartData : chartOption,
        sggNmGroupList : sggNmGroupList
    }
}