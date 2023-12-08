/**
 * 전체 소통 정보
 * @param roadData
 * @returns {Promise<any>}
 * @constructor
 */
const F_SmartIntersection = async function(){
    let list = await self.util.getJsonFormApi("/facility/getSmartIntersection.ajax");
    let linkList = await self.util.getJsonFormApi("/facility/getSmartIntersectionLinkList.ajax");
    let features = [];
    for(const info of list) {
        const obj = {
            'type': 'Feature',
            'properties' : {
                "avgPdstSpeed" : info['avgPdstSpeed'] !== null ? info['avgPdstSpeed'] : 0,
                "avgVhclSpeed" : info['avgVhclSpeed'] !== null ? info['avgVhclSpeed'] : 0,
                "pdstCnt" : info['pdstCnt'] !== null ? info['pdstCnt'] : 0,
                "vhclTrfvlm" : info['vhclTrfvlm'] !== null ? info['vhclTrfvlm'] : 0,
                "crsrdId" : info['crsrdId'],
                "crsrdNm" : info['crsrdNm'],
                "maxPdstCnt" : info['maxPdstCnt'],
                "maxTrfvlm" : info['maxTrfvlm'],
                "mngInstCd" : info['mngInstCd'],
                "nodeId" : info['nodeId']
            },
            'geometry': {
                'type': 'Point',
                'coordinates': [info.lonCrdn, info.latCrdn]
            }
        }
        features.push(obj);
    }
    for(const info of linkList) {
        const obj = {
            'type': 'Feature',
            'properties' : {
                "avgPdstSpeed" : info['avgPdstSpeed'] !== null ? info['avgPdstSpeed'] : 0,
                "avgVhclSpeed" : info['avgVhclSpeed'] !== null ? info['avgVhclSpeed'] : 0,
                "pdstCnt" : info['pdstCnt'] !== null ? info['pdstCnt'] : 0,
                "vhclTrfvlm" : info['vhclTrfvlm'] !== null ? info['vhclTrfvlm'] : 0,
                "crsrdId" : info['crsrdId'],
                "crsrdNm" : info['crsrdNm'],
                "acsRoadId" : info['acsRoadId'],
                "acsRoadNm" : info['acsRoadNm'],
                "angl" : info['angl'],
                "maxPdstCnt" : info['maxPdstCnt'],
                "laneCnt" : info['laneCnt'],
                "maxTrfvlm" : info['maxTrfvlm'],
                "mngInstCd" : info['mngInstCd'],
                "linkId" : info['linkId']
            },
            'geometry': JSON.parse(info.geojson)
        }
        features.push(obj);
    }
    return self.util.wrapFeatureCollection(features);
}