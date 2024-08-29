/**
 * 도로안전정보 데이터
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Danger_Road = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getDangerRoadInfo.ajax?"+searchOption);
    let features = [];
    for(const point of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': {
                'type': 'Point',
                'coordinates': [point.lonCrdn, point.latCrdn]
            }
        }
        for(const prop in point){
            if(prop === "dngrSttsNm") {
                obj.properties['icon'] = point[prop];
                obj.properties['dngrSttsNm'] = GITS_ENV.ROAD_DANGER_CODE[point[prop]];
            }else{
                obj.properties[prop] = point[prop];
            }
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}