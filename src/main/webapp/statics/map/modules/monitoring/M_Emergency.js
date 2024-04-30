/**
 * 긴급차량 & 위험물 차량 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Emergency = async function(){
    let features = [];
    let routeFeatures = [];
    let monitoringEmergencyBoundBox = {};
    let list = await self.util.getJsonFormApi("/monitoring/getEmergencyInfo.ajax");
    let dangerVehicleList = await self.util.getJsonFormApi("/monitoring/getDangerVehicleInfo.ajax");
    for(const info of list) {
        let carPopup = `
                    <li class="popup_item">서비스명 : <span>${info.servicename}</span></li>
                    <li class="popup_item">차량번호 : <span>${info.evno}</span></li>
                    <li class="popup_item">재난번호 : <span>${info.ocrno}</span></li>
                    <li class="popup_item">재난별칭 : <span>${info.ocrtype}</span></li>
                    <li class="popup_item">현재속도 : <span>${info.speed} km/h</span></li>
                    <li class="popup_item">예상도착시간 : <span>${info.arrivaltimeFormat}</span></li>
					`;
        features.push({
            'type': 'Feature',
            'properties' : {
                'type' : "ambulance",
                'description' : carPopup,
                'icon' : "ambulance",
                'serviceid' : info.serviceid
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.currentlng, info.currentlat]
            }
        });
        let arrivalPopup = `
                    <li>${info.servicename} 종료지점</li>`;
        features.push({
            'type': 'Feature',
            'properties' : {
                'type' : 'loc',
                'description' : arrivalPopup,
                'icon' : "end_icon"
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.arrivallng, info.arrivallat]
            }
        });
        if(info.startlng && info.startlat) {
            features.push({
                'type': 'Feature',
                'properties': {
                    'type': 'loc',
                    'icon': "emerg_start_icon",
                    'description' : carPopup,
                },
                'geometry': {
                    'type': 'Point',
                    'coordinates': [info.startlng, info.startlat]
                }
            });
        }
        let routeGeoJson = JSON.parse(info.routeGeojson);
        let routeFeaturesByServiceId = [];
        for(const geometry of routeGeoJson.geometries) {
            let f = {
                'type': 'Feature',
                'properties' : {
                    "type" : geometry.type
                },
                'geometry': geometry
            };
            routeFeatures.push(f);
            routeFeaturesByServiceId.push(f);
        }
        monitoringEmergencyBoundBox[info.serviceid] = {};
        const routeFeatureCollectionByServiceId = self.util.wrapFeatureCollection(routeFeaturesByServiceId);
        monitoringEmergencyBoundBox[info.serviceid].bbox = self.util.getBBOX(routeFeatureCollectionByServiceId.featureCollection);
        monitoringEmergencyBoundBox[info.serviceid].evno = info.evno;
        monitoringEmergencyBoundBox[info.serviceid].ocrno = info.ocrno;
        monitoringEmergencyBoundBox[info.serviceid].ocrtype = info.ocrtype;
    }
    let alertTargetCoordinates = [];
    let alertTargetCoordinatesString = [];
    for(const info of dangerVehicleList) {
        let carPopup = `
                    <li class="popup_item">차량번호 : <span>${info.vhclRegistNo}</span></li>
                    <li class="popup_item">발생일시 : <span>${info.mapOccurDtFormat}</span></li>
                    <li class="popup_item">위험물명 : <span>${info.dggdNm}</span></li>
                    <li class="popup_item">현재속도 : <span>${info.speed} km/h</span></li>`;
        features.push({
            'type': 'Feature',
            'properties' : {
                'type' : "danger",
                'description' : carPopup,
                'icon' : "danger",
                'vhclRegistNo' : info.vhclRegistNo
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.vhclLcLon, info.vhclLcLat]
            }
        });
        if(info.targetLon && info.targetLat && alertTargetCoordinatesString.indexOf(info.targetLon+"|"+info.targetLat) < 0) {
            let alertAreaFeature = turf.circle([info.targetLon,info.targetLat], 3);
            alertAreaFeature.properties = {
                'alertArea' : true
            }
            features.push(alertAreaFeature);
            alertTargetCoordinates.push([info.targetLon,info.targetLat]);
            alertTargetCoordinatesString.push(info.targetLon+"|"+info.targetLat);
        }
    }
    let routeFeatureCollection = self.util.wrapFeatureCollection(routeFeatures);
    return {
        dangerCar : dangerVehicleList,
        monitoringEmergencyBoundBox : monitoringEmergencyBoundBox,
        collection : self.util.wrapFeatureCollection(features),
        routeCollection : routeFeatureCollection,
        alertTargets : alertTargetCoordinates
    };
}