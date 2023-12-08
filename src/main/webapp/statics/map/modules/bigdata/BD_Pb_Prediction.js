/**
 * 대중교통 예측분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Pb_Prediction = async function(){
    let list = await self.util.getJsonFormApi("/bigdata/getBusInfo.ajax");
}