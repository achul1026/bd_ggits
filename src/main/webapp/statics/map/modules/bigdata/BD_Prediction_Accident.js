/**
 * 사고예측분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Prediction_Accident = async function(searchOption = ''){
    return await self.util.getJsonFormApi("/bigdata/getTrafficAccidentPrediction.ajax?"+searchOption);
}