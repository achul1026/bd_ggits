/**
 * 대중교통 이용현환분석 > 기종점 대중교통 이용량
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Public_Transfer_Usage_By_St_End = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getPublicTransferStartEndUsage.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    if(list.length === 0){
        return {
            error : true,
            errorMsg : "데이터가 존재하지 않습니다."
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
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}