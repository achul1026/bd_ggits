/**
 * 교통위험 구간분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Danger_Zone = async function(){
    let list = await self.util.getJsonFormApi("/bigdata/getCityAccidentInfo.ajax");
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
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
            obj.properties[prop] = point[prop];
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}