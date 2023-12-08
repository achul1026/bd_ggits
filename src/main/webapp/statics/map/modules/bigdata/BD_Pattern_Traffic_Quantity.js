/**
 * 교통패턴분석-교통량
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Pattern_Traffic_Quantity = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getPatternTrafficQuantity.ajax?"+searchOption);
    let features = [];
    const so =  Object.fromEntries(new URLSearchParams(searchOption));
    let legend = "vhclTrfvlmTotal";
    let label = "";
    switch(so.type) {
        case "speed" :
            legend = "avgVhclSpeedAvg";
            label = "평균속도";
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
        obj.properties['addLabel'] = `<li class="popup_item">${label} : ${info[legend]}</li>`;
        obj.properties['conggrade'] = self.util.getTrafficConGrade(info.roadRank, info.avgVhclSpeedAvg);
        const timeContentArray = info.timeGroupTxt.split("$$");
        let timer = {};
        for(const a of timeContentArray) {
            const cont = a.split("|");
            const time = cont[0];
            const spd = cont[1];
            const vol = cont[2];
            timer[time] = {spd : spd, vol: vol}
        }
        obj.properties['timer'] = timer;
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}