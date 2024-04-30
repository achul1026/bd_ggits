/**
 * 전체 소통 정보
 * @param roadData
 * @returns {Promise<any>}
 * @constructor
 */
const M_Traffic = async function(minimize = "false"){
    const list = await self.util.getJsonFormApi("/monitoring/getTrafficInfo.ajax?minimize="+minimize);
    if(list?.noLogin){
        return {
            error : true,
            noLogin : true
        }
    }
    return list;

}