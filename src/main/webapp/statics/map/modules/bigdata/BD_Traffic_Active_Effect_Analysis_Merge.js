/**
 * 교통활동 효과분석 병합
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Traffic_Active_Effect_Analysis_Merge = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getTrafficActiveEffectAnalysisMerge.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
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