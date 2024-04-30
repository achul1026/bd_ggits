/*대중교통 노선별 분석 > 승하차 승객수(하차만 이용)*/
const BD_Public_Transfer_Lndi = async function(searchOption) {
    const so = self.util.convertParamToObject(searchOption);
    let list = await self.util.getJsonFormApi("/bigdata/getPublicTransferLndiCntByRouteId.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'id' : info.stationId,
            'geometry': {
                'type': 'Point',
                'coordinates': [info.mapX, info.mapY]
            }
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        obj.properties.totalPassengers = info['lndiPassengers'] + info['ridePassengers'];
        obj.properties.passengerText = self.util.numberComma(info['lndiPassengers']) + "\\n" + self.util.numberComma(info['ridePassengers'])
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}