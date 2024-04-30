/**
 * 교통활동 효과분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Traffic_Active_Effect_Analysis_SvcLink = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getSvcLinkTrafficActiveEffectAnalysis.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    const so =  Object.fromEntries(new URLSearchParams(searchOption));
    let errorMsg = "";
    if(so.startDate){
        errorMsg = "개선 전 데이터가 존재하지 않습니다.";
    }
    if(so.endDate){
        errorMsg = "개선 후 데이터가 존재하지 않습니다.";
    }
    if(list.length === 0){
        return {
            error : true,
            errorMsg : errorMsg
        }
    }
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'id' : info.linkId,
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        obj.properties['conggrade'] = self.util.getTrafficConGrade(info.roadRank, info.avgVhclSpeedAvg);
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}