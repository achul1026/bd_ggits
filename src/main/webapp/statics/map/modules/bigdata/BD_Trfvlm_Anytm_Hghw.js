/**
 * 교통패턴분석-교통량
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Trfvlm_Anytm_Hghw = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getAnytmTrfvlmInfo-hghw.ajax?"+searchOption);
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
            'id' : info.exmnPointId,
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
            if(prop === "trfvlmInfo" && info[prop]) {
                const trfvlmInfoArray = info[prop].split(',');
                const trfVolTotal = trfvlmInfoArray[0].split("=")[1];
                const trfVolUp = trfvlmInfoArray[1].split("=")[1];
                const trfVolDown = trfvlmInfoArray[2].split("=")[1];
                obj.properties['trfVol'] = parseInt(trfVolTotal);
                obj.properties['trfVolUp'] = parseInt(trfVolUp);
                obj.properties['trfVolDown'] = parseInt(trfVolDown);
            }
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}