/**
 * 스마트교차로 카메라정보
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const CM_CrossRoad_Camera = async function(){
    let list = await self.util.getJsonFormApi("/bigdata/getCrossRoadCameraList.ajax");
    let features = [];
    for(const camera of list) {
        let popup = `
        <li class="popup_item">카메라명 : <span>${camera.cameraNm}</span></li>
        `;
        const obj = {
            'id' : camera.cameraId,
            'type': 'Feature',
            'properties' : {
                description : popup
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [camera.lonCrdn, camera.latCrdn]
            }
        }

        for(const prop in camera){
            obj.properties[prop] = camera[prop];
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}