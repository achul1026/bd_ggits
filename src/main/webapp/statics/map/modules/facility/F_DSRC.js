/**
 * 시설물 DSRC 정보 조회
 * @returns {Promise<any>}
 * @constructor
 */
const F_DSRC = async function(){
    let list = await self.util.getJsonFormApi("/facility/getDSRCList.ajax");
    let sectionLinkList = await self.util.getJsonFormApi("/facility/getDSRCSectionInfoList.ajax");
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': {
                'type': 'Point',
                'coordinates': [info.lonCrdn, info.latCrdn]
            }
        }
        for(const prop in info){
            if(prop === "colctInfo" && info[prop]) {
                obj.properties["colctInfo"] = [];
                let clctInfoList = info[prop].split(",");
                for(const clctInfoStr of  clctInfoList) {
                    let clctInfo = clctInfoStr.split("$$");
                    let dsrcSctnNm = clctInfo[0];
                    let speed = clctInfo[1];
                    let dsrcSctnLen = clctInfo[2];
                    let startRseId = clctInfo[3];
                    let endRseId = clctInfo[4];
                    obj.properties["colctInfo"].push({
                        dsrcSctnNm : dsrcSctnNm,
                        speed : speed,
                        dsrcSctnLen : dsrcSctnLen,
                        startRseId : startRseId,
                        endRseId : endRseId,
                    });
                }
            }else {
                obj.properties[prop] = info[prop];
            }
        }
        features.push(obj);
    }
    for(const info of sectionLinkList) {
        const obj = {
            'type': 'Feature',
            'properties' : {},
            'geometry': JSON.parse(info.geojson)
        }
        for(const prop in info){
            obj.properties[prop] = info[prop];
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}