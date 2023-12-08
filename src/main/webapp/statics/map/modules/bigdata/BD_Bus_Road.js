/**
 * 버스 노선별 분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Bus_Road = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getBusInfo.ajax");
}