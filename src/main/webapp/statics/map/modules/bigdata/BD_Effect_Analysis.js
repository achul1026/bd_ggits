/**
 * 교통패턴분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Effect_Analysis = async function(){
    let list = await self.util.getJsonFormApi("/bigdata/getTrafficEffectAnalysis.ajax");
}