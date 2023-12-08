/**
 * 교통위험 구간분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Danger_Zone_By_Type = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getAllAccidentInfo.ajax?"+searchOption);
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
        const accidentPolygon = {
            'type': 'Feature',
            'properties' : {},
            'geometry': JSON.parse(point.acdntDstrctPyn)
        }
        /*
        BCYCL : 자전거 사고 구역
        JAYWK : 무단횡단 보행자 사고구역
        LAWVLTN : 법위반 보행자 사고구역
        HLDY : 휴일기간 보행자 사고구역
        FROST : 결빙사고 구역
        TWHLVH : 이륜차 사고 구역
        PDSN : 보행자 사고 구역
        DRNKG : 음주 사고 구역
        TRUCK : 화물차 사고 구역
        OLMAN : 노인 보행자 사고 구역
         */
        for(const prop in point){
            accidentPolygon.properties[prop] = point[prop];
            obj.properties[prop] = point[prop];
            if(prop === "type" && !obj.properties.icon) {
                console.log(prop["type"]);
                obj.properties.icon = "accident_"+point["type"];
                /*switch (point['type']) {
                    case "BCYCL" :

                        break;
                    case "DRNKG" :
                        obj.properties.icon = "location_yellow";
                        break;
                }*/
            }
        }
        features.push(obj);
        features.push(accidentPolygon);
    }
    return self.util.wrapFeatureCollection(features);
}