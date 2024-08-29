/**
 * 대중교통 위험운영 구간 분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_PT_Danger_Analysis = async function(searchOption){
    let list = await self.util.getJsonFormApi("/bigdata/getPublicTransferDangerInfo.ajax?"+searchOption);

    let chartDataList = await self.util.getJsonFormApi("/bigdata/getPublicTransferDangerInfoForChart.ajax?"+searchOption);
    if(chartDataList.length) {
        return {
            error : true,
            errorMsg : "조회된 데이터가 없습니다."
        }
    }
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            if(prop !== "geojson") {
                obj.properties[prop] = info[prop];
            }
        }
        features.push(obj);
    }
    let roadGroupList = [];
    let timeGroupList = [
        "00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00",
        "12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00",
    ];
    let data = [];
    for(const data of chartDataList) {
        if(roadGroupList.indexOf(data.roadName) === -1){
            roadGroupList.push(data.roadName)
        }
    }
    for(const roadName of roadGroupList) {
        for(let h = 0; h < 24; h++) {
            let hh = h;
            if(hh < 10) {
                hh = "0"+hh;
            }
            let d = chartDataList.filter((obj) => obj.roadName == roadName && obj.hh == hh)[0];
            let dataset = {
                x : hh+":00",
                y : roadName
            }
            if(d) {
                dataset.v = d.riskJugCnt
            }else{
                dataset.v = 0
            }
            data.push(dataset);
        }
    }




    //매트릭스 차트데이터 가공
    let chartOption = {
        type : "matrix",
        data : {
            datasets : [{
                label : "위험운전수",
                data : data,
                borderWidth: 1
            }]
        },
        options: {
            events : ['mousemove', 'mouseout', 'click', 'touchstart', 'touchmove', 'scroll'],
            aspectRatio: 5,
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
                    }
                },
                y: {
                    type: 'category',
                    labels: roadGroupList,
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
        busRouteFeatureCollection : self.util.wrapFeatureCollection(features),
        matrixChartData : chartOption,
        roadGroupList : roadGroupList,
        timeGroupList : timeGroupList
    };
}