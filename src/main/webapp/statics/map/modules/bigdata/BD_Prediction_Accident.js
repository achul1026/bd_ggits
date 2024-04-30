/**
 * 사고예측분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Prediction_Accident = async function(searchOption = ''){
    const list = await self.util.getJsonFormApi("/bigdata/getTrafficAccidentPrediction.ajax?"+searchOption);
    let features = self.util.getSGGFeatures(self.env);

    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    if(list.length === 0) {
        return {
            error : true,
            errorMsg : "해당일자에 조회된 데이터가 없습니다."
        }
    }


    const listBySgg = await self.util.getJsonFormApi("/bigdata/getTrafficAccidentPredictionGroupSgg.ajax?"+searchOption);

    // 행정구역별로 그룹
    for(const point of listBySgg) {
        let feature = features.filter((obj) => obj.sggCode === point.sggCd+"0")[0];
        if(feature) {
            feature.properties.noneLinkCntBySgg = point.noneLinkCntBySgg;
            feature.properties.speedOverCntBySgg = point.speedOverCntBySgg;
            feature.properties.speedUnderCntBySgg = point.speedUnderCntBySgg;
            feature.properties.safeCntBySgg = point.safeCntBySgg;
            feature.properties.warnCntBySgg = point.warnCntBySgg;
            feature.properties.dangerCntBySgg = point.dangerCntBySgg;
            feature.properties.seriousCntBySgg = point.seriousCntBySgg;
            feature.properties.total = point.noneLinkCntBySgg
                +point.speedOverCntBySgg
                +point.speedUnderCntBySgg
                +point.safeCntBySgg
                +point.warnCntBySgg
                +point.dangerCntBySgg
                +point.seriousCntBySgg
        }
    }
    let featureCollection = self.util.wrapFeatureCollection(features);
    return {
        sggDataFeatureCollection : featureCollection,
        linkData : list
    };
}