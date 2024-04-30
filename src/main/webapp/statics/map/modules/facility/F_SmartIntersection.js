/**
 * 전체 소통 정보
 * @returns {Promise<any>}
 * @constructor
 * @param type
 * @param mngInstCd
 */
const F_SmartIntersection = async function(type = "all", mngInstCd ){
    let features = [];
    if(type === "all" || type === "crsrd") {
        let list = await self.util.getJsonFormApi("/facility/getSmartIntersection.ajax"+(mngInstCd ? '?mngInstCd='+mngInstCd : '' ));
        if(list?.noLogin){
            return {
                error : true,
                noLogin : true
            }
        }
        for (const info of list) {
            const obj = {
                'type': 'Feature',
                'properties': {
                    "avgPdstSpeed": info['avgPdstSpeed'] !== null ? info['avgPdstSpeed'] : 0,
                    "avgVhclSpeed": info['avgVhclSpeed'] !== null ? info['avgVhclSpeed'] : 0,
                    "pdstCnt": info['pdstCnt'] !== null ? info['pdstCnt'] : 0,
                    "vhclTrfvlm": info['vhclTrfvlm'] !== null ? info['vhclTrfvlm'] : 0,
                    "crsrdId": info['crsrdId'],
                    "crsrdNm": info['crsrdNm'],
                    "maxPdstCnt": info['maxPdstCnt'],
                    "maxTrfvlm": info['maxTrfvlm'],
                    "mngInstCd": info['mngInstCd'],
                    "nodeId": info['nodeId']
                },
                'geometry': {
                    'type': 'Point',
                    'coordinates': [info.lonCrdn, info.latCrdn]
                }
            }
            features.push(obj);
        }
    }

    if(type === "all" || type === "link") {
        let linkList = await self.util.getJsonFormApi("/facility/getSmartIntersectionLinkList.ajax");
        for (const info of linkList) {
            const obj = {
                'type': 'Feature',
                'properties': {
                    "avgPdstSpeed": info['avgPdstSpeed'] !== null ? info['avgPdstSpeed'] : 0,
                    "avgVhclSpeed": info['avgVhclSpeed'] !== null ? info['avgVhclSpeed'] : 0,
                    "pdstCnt": info['pdstCnt'] !== null ? info['pdstCnt'] : 0,
                    "vhclTrfvlm": info['vhclTrfvlm'] !== null ? info['vhclTrfvlm'] : 0,
                    "crsrdId": info['crsrdId'],
                    "crsrdNm": info['crsrdNm'],
                    "acsRoadId": info['acsRoadId'],
                    "acsRoadNm": info['acsRoadNm'],
                    "angl": info['angl'],
                    "maxPdstCnt": info['maxPdstCnt'],
                    "laneCnt": info['laneCnt'],
                    "maxTrfvlm": info['maxTrfvlm'],
                    "mngInstCd": info['mngInstCd'],
                    "linkId": info['linkId']
                },
                'geometry': JSON.parse(info.geojson)
            }
            features.push(obj);
        }
    }
    return self.util.wrapFeatureCollection(features);
}