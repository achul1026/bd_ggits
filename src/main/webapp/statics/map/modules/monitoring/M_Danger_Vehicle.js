/**
 * 위험물 차량 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Danger_Vehicle = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getDangerVehicleInfo.ajax");
    let features = [];

    for(const info of list) {
        let carPopup = `
                    <li class="popup_item">차량번호 : <span>${info.vhclRegistNo}</span></li>
                    <li class="popup_item">위험물명 : <span>${info.dggdNm}</span></li>
                    <li class="popup_item">현재속도 : <span>${info.speed} km/h</span></li>`;
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