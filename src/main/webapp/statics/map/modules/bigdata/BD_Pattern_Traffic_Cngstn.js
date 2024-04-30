/**
 * 교통패턴분석-상습정체구간
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Pattern_Traffic_Cngstn = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getPatternTrafficCngstn.ajax?"+searchOption);
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
        if(info.avgSpeed){
            obj.properties['conggrade'] = self.util.getTrafficConGrade(info.roadRank, info.avgSpeed);
        }else{
            obj.properties['conggrade'] = '1'
        }

        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}