/**
 * 시설물 DSRC 정보 조회
 * @returns {Promise<any>}
 * @constructor
 */
const F_DSRC = async function(){
    let list = await self.util.getJsonFormApi("/facility/getDSRCList.ajax");
    let sectionLinkList = await self.util.getJsonFormApi("/facility/getDSRCSectionInfoList.ajax");
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': {
                'type': 'Point',
                'coordinates': [info.lonCrdn, info.latCrdn]
            }
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        features.push(obj);
    }
    for(const info of sectionLinkList) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}