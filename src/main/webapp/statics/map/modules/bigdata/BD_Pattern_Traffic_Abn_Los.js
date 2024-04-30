/**
 * 교통패턴분석-교통량
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Pattern_Traffic_Abn_Los = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getPatternTrafficAbnLos.ajax?"+searchOption);
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
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        obj.properties['conggrade'] = info.trfvlmCngrt;
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}