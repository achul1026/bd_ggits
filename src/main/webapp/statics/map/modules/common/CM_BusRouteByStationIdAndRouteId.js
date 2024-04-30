/**
 * 버스 출발지,도착지 구간 노선만 가져오기
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const CM_BusRouteByStationIdAndRouteId = async function(stStationId, edStationId, routeId){
    let param = "?stStationId="+stStationId+"&edStationId="+edStationId+"&routeId="+routeId;
    let list =  await self.util.getJsonFormApi("/bigdata/getBusRouteInfoByStationIdAndRouteId.ajax"+param);
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'id' : info.routeId,
            'properties' : {
                routeId : info.routeId,
                routeNm : info.routeNm
            },
            'geometry': JSON.parse(info.geojson)
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}