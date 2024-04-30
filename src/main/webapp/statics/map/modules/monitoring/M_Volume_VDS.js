/**
 * VDS 교통량 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Volume_VDS = async function(){
    const list = await self.util.getJsonFormApi("/monitoring/getVolumeVDS.ajax");
    let features = [];
    for(const info of list) {
        let props = {};
        for(const k in info){
            props[k] = info[k];
        }
        features.push({
            'type': 'Feature',
            'properties' : props,
            'geometry': {
                'type': 'Point',
                'coordinates': [info.lonCrdn, info.latCrdn]
            }
        });
    }
    return self.util.wrapFeatureCollection(features);
}