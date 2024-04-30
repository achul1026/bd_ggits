/**
 * 교통패턴분석-교통량
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Pattern_Traffic_Quantity = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getPatternTrafficQuantity.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    if(list.length === 0) {
        return {
            error : true,
            errorMsg : "해당일자에 조회된 데이터가 없습니다."
        }
    }
    let features = [];
    const so =  Object.fromEntries(new URLSearchParams(searchOption));
    let legend = "vhclTrfvlmTotal";
    let label = "";
    let unit = "대";
    switch(so.type) {
        case "speed" :
            legend = "avgVhclSpeedAvg";
            label = "평균속도";
            unit = "km/h"
            break;
        case "quantity" :
            if(so.searchResultType === "totalSum"){
                legend = "vhclTrfvlmTotal";
                label = "누적 교통량";
            }else{
                legend = "vhclTrfvlmAvg";
                label = "평균 교통량";
            }
            break;
    }
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        obj.properties['addLabel'] = `<li class="popup_item">${label} : ${self.util.numberComma(Math.round(info[legend]))}${unit}</li>`;
        obj.properties['conggrade'] = self.util.getTrafficConGrade(info.roadRank, info.avgVhclSpeedAvg);
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}