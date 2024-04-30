/**
 * 버스 정류장 위치 호출
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const CM_STARTENDSTATIONBYROUTE = async function(routeId){
    let data = await self.util.getJsonFormApi("/monitoring/getStartEndStationInfoByRouteId.ajax?routeId="+routeId);
    let features = [];
    let stPopup = `
    <li class="popup_item">기점 : <span>${data.stStationNm}</span></li>
    `;
    let edPopup = `
    <li class="popup_item">종점 : <span>${data.edStationNm}</span></li>
    `;
    let stCoordinates = [data.stMapX, data.stMapY];
    let edCoordinates = [data.edMapX, data.edMapY];
    const stObj = {
        'type': 'Feature',
        'properties' : {
            description : stPopup,
            'icon' : 'bus_station',
            stationNm : data.stStationNm
        },
        'geometry': {
            'type': 'Point',
            'coordinates': stCoordinates,
        }
    }
    const edObj = {
        'type': 'Feature',
        'properties' : {
            description : edPopup,
            'icon' : 'bus_station_2',
            stationNm : data.edStationNm
        },
        'geometry': {
            'type': 'Point',
            'coordinates': edCoordinates,
        }
    }
    features.push(stObj);
    features.push(edObj);
    return self.util.wrapFeatureCollection(features);
}