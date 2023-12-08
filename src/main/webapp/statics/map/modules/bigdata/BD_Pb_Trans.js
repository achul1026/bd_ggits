/**
 * 대중교통 이용현황 분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Pb_Trans = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getBusInfo.ajax");
}