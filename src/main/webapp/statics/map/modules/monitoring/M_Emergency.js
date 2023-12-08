/**
 * 긴급차량 & 위험물 차량 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Emergency = async function(){
    let features = [];
    let list = await self.util.getJsonFormApi("/monitoring/getEmergencyInfo.ajax");
    let dangerVehicleList = await self.util.getJsonFormApi("/monitoring/getDangerVehicleInfo.ajax");
    for(const info of list) {
        let carPopup = `
                    <li class="popup_item">서비스명 : <span>${info.servicename}</span></li>
                    <li class="popup_item">차량번호 : <span>${info.evono}</span></li>
                    <li class="popup_item">재난번호 : <span>${info.ocrno}</span></li>
                    <li class="popup_item">재난별칭 : <span>${info.ocrtype}</span></li>
                    <li class="popup_item">현재속도 : <span>${info.speed} km/h</span></li>
                    <li class="popup_item">예상도착시간 : <span>${info.arrivaltime} 초</span></li>`;
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
                    <li><span>${info.servicename} 종료지점</span></li>`;
        features.push({
            'type': 'Feature',
            'properties' : {
                'description' : arrivalPopup,
                'icon' : "end_icon"
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.arrivallng, info.arrivallat]
            }
        });
    }
    for(const info of dangerVehicleList) {
        let carPopup = `
                    <li>차량번호 : <span>${info.vhclRegistNo}</span></li>
                    <li>위험물명 : <span>${info.dggdNm}</span></li>
                    <li>현재속도 : <span>${info.speed} km/h</span></li>`;
        features.push({
            'type': 'Feature',
            'properties' : {
                'type' : "police",
                'description' : carPopup,
                'icon' : "police",
                'vhclRegistNo' : info.vhclRegistNo
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.vhclLcLon, info.vhclLcLat]
            }
        });
    }
    return self.util.wrapFeatureCollection(features);
}