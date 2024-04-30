/**
 * 돌발정보조회
 * @param roadData
 * @returns {Promise<any>}
 * @constructor
 */
const M_Warning = async function(){
	let list =  await self.util.getJsonFormApi("/monitoring/getWarningInfo.ajax");
	let dangerVehicleList = await self.util.getJsonFormApi("/monitoring/getDangerVehicleInfoByWarning.ajax");
	let features = [];
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
	return {
		list : list,
		dangerCar : dangerVehicleList,
		collection :self.util.wrapFeatureCollection(features),
		alertTargets : alertTargetCoordinates
	};
}
