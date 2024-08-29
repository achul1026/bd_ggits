/**
 * 실시간 버스 이동 조회
 * @returns {Promise<any>}
 * @constructor
 */
const M_Bus = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getBusInfo.ajax");
    let features = [];
    for(const info of list) {
        let popup = `<div style="color:#000">
                        <strong>${info.plateNo}</strong>
                        <p>asdf</p>
                    </div>
                        `;
        let props = {
            'description' : popup,
            'icon' : "bus_icon"
        }
        for(const k in info){
            props[k] = info[k];
        }
        features.push({
            'type': 'Feature',
            'properties' : props,
            'geometry': {
                'type': 'Point',
                'coordinates': [info.gpsX, info.gpsY]
            }
        });
    }
    return self.util.wrapFeatureCollection(features);
}