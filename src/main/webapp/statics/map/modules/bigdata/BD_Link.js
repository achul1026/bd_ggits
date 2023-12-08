/**
 * 교통신호 연동분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_Link = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getBusInfo.ajax");
}