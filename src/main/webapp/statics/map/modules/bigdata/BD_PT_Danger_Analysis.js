/**
 * 대중교통 위험운영 구간 분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_PT_Danger_Analysis = async function(searchOption){
    const so =  Object.fromEntries(new URLSearchParams(searchOption));
    let list = await self.util.getJsonFormApi("/bigdata/getPublicTransferDangerInfo.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    if(list.length === 0) {
        return {
            error : true,
            errorMsg : "해당일자 또는 차량정보에 조회된 데이터가 없습니다."
        }
    }
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties': {
                type: "dsrc"
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [parseFloat(info.lon), parseFloat(info.lat)]
            }
        }
        for (const prop in info) {
            obj.properties[prop] = info[prop];
        }
        features.push(obj);
    }

    return self.util.wrapFeatureCollection(features);
}