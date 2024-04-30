/**
 * 시설물 신호정보 조회
 * @returns {Promise<any>}
 * @constructor
 */
const F_Signal = async function(){
    let list = await self.util.getJsonFormApi("/facility/getSignalList.ajax");
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
            'id' : info['intLcno'],
            'properties' : {
                "updateDate" : info['updateDate'] !== null ? info['updateDate'] : 0,
                "intName" : info['intName'] !== null ? info['intName'] : 0,
                "intLcno" : info['intLcno'],
                "intLctype" : info['intLctype'],
                "intType" : info['intType'],
                "intLamptype" : info['intLamptype'],
                "mainLcNo" : info['mainLcNo'],
                "saNo" : info['saNo'],
                "intNodeId" : info['intNodeId'],
                "ppcType" : info['ppcType']
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.intLng, info.intLat]
            }
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}