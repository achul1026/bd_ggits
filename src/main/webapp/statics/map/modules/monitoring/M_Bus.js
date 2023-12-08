/**
 * 긴급차량 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Bus = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getBusInfo.ajax");
    let features = [];
    for(const info of list) {
        let popup = `<div style="color:#000">
                        <strong>${info.vehicleNumber}</strong>
                        <p>asdf</p>
                    </div>
                        `;
        let props = {
            'description' : popup,
            'icon' : "bus_icon"
        }
        features.push({
            'type': 'Feature',
            'properties' : props,
            'geometry': {
                'type': 'Point',
                'coordinates': [info.lng, info.lat]
            }
        });
    }
    return self.util.wrapFeatureCollection(features);
}