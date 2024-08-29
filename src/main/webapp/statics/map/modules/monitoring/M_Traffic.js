/**
 * 전체 소통 정보
 * @param roadData
 * @returns {Promise<any>}
 * @constructor
 */
const M_Traffic = async function(){
    const list = await self.util.getJsonFormApi("/monitoring/getTrafficInfo.ajax");
    return list;

}