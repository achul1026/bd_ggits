/**
 * VDS 시설물 정보 조회
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const F_VDS = async function(){
    let list = await self.util.getJsonFormApi("/facility/getVDSList.ajax");
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
            'geometry': {
                'type': 'Point',
                'coordinates': [info.lon, info.lat]
            }
        }
        for(const prop in info){
            if(prop === "colctInfo" && info[prop]) {
                obj.properties["colctInfo"] = [];
                let clctInfoList = info[prop].split(",");
                for(const clctInfoStr of  clctInfoList) {
                    let clctInfo = clctInfoStr.split("$$");
                    let laneNo = clctInfo[0];
                    let avgSpeed = clctInfo[1];
                    let trfvlm = clctInfo[2];
                    let occpRt = clctInfo[3];
                    obj.properties["colctInfo"].push({
                        laneNo : laneNo,
                        avgSpeed : avgSpeed,
                        trfvlm : trfvlm,
                        occpRt : occpRt,
                    });
                }
            }else{
                obj.properties[prop] = info[prop];
            }
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}