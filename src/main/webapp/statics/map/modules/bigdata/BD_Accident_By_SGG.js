/**
 * 도로안전 정보 행정시 그룹
 *
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Accident_By_SGG = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getRoadAccidentInfoGroupBySGG.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    /*
    dcsdCnt : 사망자수
    injpsnCnt : 부상자수
    swpsnCnt : 중상자수
    sinjpsnCnt : 경상자수
    injDclrCnt : 부상신고수
    wrngdorLawVltnCd : 위법 코드
    occurAdsiCd : 행정시 코드
     */
    let features = self.util.getSGGFeatures(self.env);
    // 행정구역별로 그룹
    for(const point of list) {
        let feature = features.filter((obj) => obj.sggCode === point.occurAdsiCd)[0];
        if(feature) {
            if (!feature.properties.acdntCntTotal) feature.properties.acdntCntTotal = 0;
            if (!feature.properties.dcsdCntTotal) feature.properties.dcsdCntTotal = 0;
            if (!feature.properties.injpsnCntTotal) feature.properties.injpsnCntTotal = 0;
            if (!feature.properties.swpsnCntTotal) feature.properties.swpsnCntTotal = 0;
            if (!feature.properties.sinjpsnCntTotal) feature.properties.sinjpsnCntTotal = 0;
            if (!feature.properties.injDclrCntTotal) feature.properties.injDclrCntTotal = 0;
            feature.properties.acdntCntTotal = feature.properties.acdntCntTotal + (point.dcsdCnt + point.injpsnCnt + point.swpsnCnt + point.sinjpsnCnt + point.injDclrCnt);
            feature.properties.dcsdCntTotal = feature.properties.dcsdCntTotal + point.dcsdCnt;
            feature.properties.injpsnCntTotal = feature.properties.injpsnCntTotal + point.injpsnCnt;
            feature.properties.swpsnCntTotal = feature.properties.swpsnCntTotal + point.swpsnCnt;
            feature.properties.sinjpsnCntTotal = feature.properties.sinjpsnCntTotal + point.sinjpsnCnt;
            feature.properties.injDclrCntTotal = feature.properties.injDclrCntTotal + point.injDclrCnt;
        }
    }
    return self.util.wrapFeatureCollection(features);
}