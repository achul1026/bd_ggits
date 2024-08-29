/**
 * 버스 정류장 위치 호출
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const CM_BusStation = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/monitoring/getBusStationList.ajax?"+searchOption);
    let features = [];
    for(const station of list) {
        let popup = `
        <li class="popup_item">정류장명 : <span>${station.stationNm}</span></li>
        <li class="popup_item">BIT여부 : <span>${station.bitYn === 'Y' ? "사용" : "미사용"}</span></li>
        <li class="popup_item">BIT갯수 : <span>${station.bitCount}</span></li>
        `;
        /*let coordinates = [station.gpsX > 1000 ? station.gpsX/100 : station.gpsX, station.gpsY > 100 ? station.gpsY/100 : station.gpsY];*/
        let coordinates = [station.mapX, station.mapY];
        const obj = {
            'type': 'Feature',
            'properties' : {
                description : popup
            },
            'geometry': {
                'type': 'Point',
                'coordinates': coordinates
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