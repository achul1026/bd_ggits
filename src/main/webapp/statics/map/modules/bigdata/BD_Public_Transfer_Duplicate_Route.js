/**
 * 대중교통 노션별 분석 > 노선구간별 중복구간 도출 및 적정성 분석(노선 정보 호출)
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Public_Transfer_Duplicate_Route = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getDuplicateRouteGeometryInfoByStationId.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    let features = [];
    /*let stationList = await self.util.getJsonFormApi("/monitoring/getBusStationListByRouteIds.ajax?routeIds=");*/
    // let routes = "";
    let idx = 0;
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'id' : info.linkId,
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        features.push(obj);
        idx++;
    }
    let stationFeatures = [];
    // const set = new Set(routes.split(","));
    // const uniqueRouteIds = [...set];
    /*let stationList = await self.util.getJsonFormApi("/monitoring/getBusStationListByRouteIds.ajax?routeIds="+uniqueRouteIds.join(","));
    for(const station of stationList) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': {
                'type': 'Point',
                'coordinates': [station.mapX, station.mapY]
            }
        }

        for(const prop in station){
            obj.properties[prop] = station[prop];
        }
        obj.properties.icon = "bus_station"
        stationFeatures.push(obj);
    }*/
    return {
        routeFeatureCollection : self.util.wrapFeatureCollection(features),
        stationFeatureCollection : self.util.wrapFeatureCollection(stationFeatures)
    };
}