/**
 * 후보경로 노선 정보 조회
 * @constructor
 * @param searchOption
 */
const BD_Public_Transfer_Cndcy_Route = async function(searchOption){
    const so = self.util.convertParamToObject(searchOption);
    let list = await self.util.getJsonFormApi("/bigdata/getPublicTransferCndcyPathLinkInfo.ajax?candRouteId="+so.candRouteId+"&baseym="+so.baseym+"&btcId="+so.btcId);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    let stationList = await self.util.getJsonFormApi("/bigdata/getPublicTransferCndcyStationInfo.ajax?candRouteId="+so.candRouteId+"&baseym="+so.baseym+"&btcId="+so.btcId);
    if(list.length === 0) {
        return {
            error : true,
            errorMsg : "데이터가 존재하지 않습니다."
        }
    }
    let features = [];
    for(const item of list) {
        const obj = {
            'type': 'Feature',
            'id' : item.stdLinkId,
            'properties' : {
                "route" : true
            },
            'geometry': JSON.parse(item.geojson)
        }
        for(const prop in item){
            obj.properties[prop] = item[prop];
        }
        features.push(obj);
    }
    for(const station of stationList) {
        let coordinates = [station.mapX, station.mapY];
        const obj = {
            'type': 'Feature',
            'properties' : {
                "station" : true
            },
            'geometry': {
                'type': 'Point',
                'coordinates': coordinates
            }
        }

        for(const prop in station){
            obj.properties[prop] = station[prop];
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}