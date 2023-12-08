/**
 * 버스 정류장 위치 호출
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const CM_BusStation = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getBusStationList.ajax");
    let features = [];
    for(const station of list) {
        let popup = `
        <li class="popup_item">정류장명 : <span>${station.stationNm}</span></li>
        <li class="popup_item">도로명 : <span>${station.roadNm}</span></li>
        <li class="popup_item">BIT여부 : <span>${station.bitYn === 'Y' ? "사용" : "미사용"}</span></li>
        <li class="popup_item">BIT갯수 : <span>${station.bitCount}</span></li>
        `;
        const obj = {
            'type': 'Feature',
            'properties' : {
                description : popup
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [station.mapX, station.mapY]
            }
        }

        for(const prop in station){
            obj.properties[prop] = station[prop];
        }
        obj.properties.icon = "bus_station"
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}