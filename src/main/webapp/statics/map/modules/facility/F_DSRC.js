/**
 * 시설물 DSRC 정보 조회
 * @returns {Promise<any>}
 * @constructor
 */
const F_DSRC = async function(type ="all"){
    let features = [];
    if(type === "all" || type === "dsrc"){
        let list = await self.util.getJsonFormApi("/facility/getDSRCList.ajax");
        if(list?.noLogin){
            return {
                error : true,
                noLogin : true
            }
        }
        for(const info of list) {
            const obj = {
                'type': 'Feature',
                'properties' : {
                    type : "dsrc"
                },
                'geometry': {
                    'type': 'Point',
                    'coordinates': [info.lonCrdn, info.latCrdn]
                }
            }
            for (const prop in info) {
                obj.properties[prop] = info[prop];
            }
            features.push(obj);
        }
    }
    if(type === "all" || type === "link") {
        let sectionLinkList = await self.util.getJsonFormApi("/facility/getDSRCSectionInfoList.ajax");
        console.log("sectionLinkList", sectionLinkList);
        for (const info2 of sectionLinkList) {
            const obj2 = {
                'type': 'Feature',
                'properties': {
                    type : "dsrc_link"
                },
                'geometry': JSON.parse(info2.geojson)
            }
            for (const prop2 in info2) {
                obj2.properties[prop2] = info2[prop2];
            }
            features.push(obj2);
        }
    }
    return self.util.wrapFeatureCollection(features);
}