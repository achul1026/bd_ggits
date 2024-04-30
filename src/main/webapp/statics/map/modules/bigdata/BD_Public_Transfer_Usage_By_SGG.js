/**
 * 대중교통 이용현환분석 > 권역별 대중교통 이용현황
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Public_Transfer_Usage_By_SGG = async function(searchOption = ''){
    let list = await self.util.getJsonFormApi("/bigdata/getPublicTransferUsageBySGG.ajax?"+searchOption);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    let features = self.util.getSGGFeatures(self.env);
    let so = self.util.convertParamToObject(searchOption);
    if(so.sigunCdId && so.sigunCdId !== '' && so.sigunCdId !== 'searchAllLocation') {
        const filterSggCd = self.util.strSplice(so.sigunCdId, 5, 5);
        features = features.filter((obj) => obj.sggCode === filterSggCd);
    }
    for(const point of list) {
        let sggCode = self.util.strSplice(point.sidoCd, 5, 5);
        let feature = features.filter((obj) => obj.sggCode === sggCode)[0];
        if(feature) {
            feature.properties.total = point.lndiUserCnt + point.rideUserCnt + point.trnsitUserCnt;
            feature.properties.lndiUserCnt = point.lndiUserCnt;
            feature.properties.rideUserCnt = point.rideUserCnt;
            feature.properties.trnsitUserCnt = point.trnsitUserCnt;
        }
    }
    return self.util.wrapFeatureCollection(features);
}