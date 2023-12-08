/**
 * 돌발정보조회
 * @param roadData
 * @returns {Promise<any>}
 * @constructor
 */
const M_Warning = async function(){
	return await self.util.getJsonFormApi("/monitoring/getWarningInfo.ajax");
}
