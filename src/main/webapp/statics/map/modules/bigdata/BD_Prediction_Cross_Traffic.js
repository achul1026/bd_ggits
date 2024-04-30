/**
 * 교차로 교통량 예측
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Prediction_Cross_Traffic = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getCrossRoadTrafficQuantityPrediction.ajax?"+searchOption);
    /*let chartDataList = await self.util.getJsonFormApi("/bigdata/getCrossRoadTrafficQuantityPredictionForChat.ajax?"+searchOption);
    if(chartDataList.length === 0) {
        return {
            error : true,
            errorMsg : "조회된 데이터가 없습니다."
        }
    }*/
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    const so = self.util.convertParamToObject(searchOption);
    let features = [];
    if(list.length === 0) {
        return {
            error : true,
            errorMsg : "조회된 데이터가 없습니다."
        }
    }
    for(const item of list) {
        let popup = `
        `;
        const obj = {
            'type': 'Feature',
            'id' : item.acsRoadId,
            'properties' : {
                description : popup
            },
            'geometry': JSON.parse(item.st)
        }

        for(const prop in item){
            obj.properties[prop] = item[prop];
        }
       /* const timeContentArray = item.timeGroupTxt.split(",");
        item.timer = {};
        obj.properties.timer = {};
        for(const a of timeContentArray) {
            const cont = a.split("=");
            const date = cont[0];
            const trfvlmTotal = cont[1];
            /!*const strghtTrfvlm = cont[1]; // 직진 데이터
            const trnghtTrfvlm = cont[2]; // 우회전 데이터
            const trnlftTrfvlm = cont[3]; // 좌회전 데이터*!/
            obj.properties["trfvlmTotal_"+date] = parseFloat(trfvlmTotal);
        }*/
        features.push(obj);
    }

    //매트릭스 차트데이터 가공
   /* let sggNmGroupList = [];
    let sggCdGroupList = [];
    let dateGroupList = [];
    let start = new Date(so.startDate);
    let end = new Date(so.endDate);

    dateGroupList.push(so.startDate);

    let loop = new Date(start);
    while(loop <= end){
        let newDate = loop.setDate(loop.getDate() + 1);
        newDate = new Date(newDate);
        let day = newDate.getDate();
        let month = newDate.getMonth()+1;
        if(month < 10) {
            month = "0"+month;
        }
        if(day < 10) {
            day = "0"+day;
        }
        let year = newDate.getFullYear();
        loop = new Date(newDate);
        dateGroupList.push(year+"-"+(month)+"-"+day);
    }
    let data = [];
    for(const data of chartDataList) {
        const sggInfo = self.util.getSGGInfoByCode(data.adstdgCd,GITS_ENV);

        if(typeof sggInfo !== "undefined" && sggNmGroupList.indexOf(sggInfo.sggNm) === -1){
            sggNmGroupList.push(sggInfo.sggNm);
            sggCdGroupList.push(data.adstdgCd);
        }else{
            if(sggNmGroupList.indexOf("알수없음") === -1) {
                sggNmGroupList.push("알수없음");
                sggCdGroupList.push(null);
            }
        }

    }
    let sggIdx = 0;
    for(const sggCode of sggCdGroupList) {
        for(const date of dateGroupList) {
            let d = chartDataList.filter((obj) => obj.adstdgCd == sggCode && obj.yyyymmdd == date)[0];
            let dataset = {
                x : date,
                y : sggNmGroupList[sggIdx]
            }
            if(d) {
                dataset.v = d.trfvlmTotal
            }else{
                const unsgg = chartDataList.filter((obj) => obj.adstdgCd === null && obj.yyyymmdd == date)[0];
                if(unsgg) {
                    dataset.v = unsgg.trfvlmTotal;
                }else{
                    dataset.v = 0
                }

            }
            data.push(dataset);
        }
        sggIdx++;
    }
    let chartOption = {
        type : "matrix",
        data : {
            datasets : [{
                label : "교차로 교통량 예측",
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
                    labels: dateGroupList,
                    ticks: {
                        display: true
                    },
                    grid: {
                        display: false
                    }
                },
                y: {
                    type: 'category',
                    labels: sggNmGroupList,
                    offset: true,
                    ticks: {
                        display: true
                    },
                    grid: {
                        display: false
                    }
                }
            }
        }
    };*/

    return {
        collection : self.util.wrapFeatureCollection(features),
        /*matrixChartData : chartOption,
        sggNmGroupList : sggNmGroupList,
        dateGroupList : dateGroupList*/
    };
}