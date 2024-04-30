/**
 * 위험물 차량 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Danger_Vehicle = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getDggdVechicleMoveInfoListAll.ajax");
    let features = [];
    for(const info of list) {
        let carPopup = `
                    <li class="popup_item">차량번호 : <span>${info.vhclRegistNo}</span></li>
                    <li class="popup_item">위험물명 : <span>${info.dggdNm}</span></li>
                    <li class="popup_item">시동여부 : <span>${info.strtgCd ? "ON" : "OFF"}</span></li>
                    <li class="popup_item">현재속도 : <span>${info.speed} km/h</span></li>
                    <li class="popup_item">사고이력여부 : <span>${info.acdntYn === 'Y' ? '사고이력있음' : '-'}</span></li>`;
        features.push({
            'type': 'Feature',
            'properties' : {
                'type' : "danger",
                'description' : carPopup,
                'icon' : (info.acdntYn === 'Y' ? "danger_acdnt" : "danger"),
                'strtgCd' : info.strtgCd,
                'vhclRegistNo' : info.vhclRegistNo,
                'dggdNm' : info.dggdNm,
                "speed" : info.speed,
                "vhclLcLon" : info.vhclLcLon,
                "vhclLcLat" : info.vhclLcLat
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.vhclLcLon, info.vhclLcLat]
            }
        });
    }
    return self.util.wrapFeatureCollection(features);
}