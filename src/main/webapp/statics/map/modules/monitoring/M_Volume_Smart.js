/**
 * 스마트교차로 교통량 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Volume_Smart = async function(){
    const list = await self.util.getJsonFormApi("/monitoring/getVolumeSmart.ajax");
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
    let drctFeatures = [];
    const drctList = await self.util.getJsonFormApi("/monitoring/getVolumeSmartDrct.ajax");
    for(const item of drctList) {
        let popup = ``;
        const obj = {
            'type': 'Feature',
            'id': item.acsRoadId,
            'properties': {
                description: popup
            },
            'geometry': JSON.parse(item.st)
        }

        for (const prop in item) {
            obj.properties[prop] = item[prop];
        }
        drctFeatures.push(obj);
    }
    return {
        crsrd : self.util.wrapFeatureCollection(features),
        drct : self.util.wrapFeatureCollection(drctFeatures),
    };
}