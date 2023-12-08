/**
 * VDS 시설물 정보 조회
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const F_VDS = async function(){
    let list = await self.util.getJsonFormApi("/facility/getVDSList.ajax");
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': {
                'type': 'Point',
                'coordinates': [info.lon, info.lat]
            }
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}