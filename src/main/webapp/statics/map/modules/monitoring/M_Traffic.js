/**
 * 전체 소통 정보
 * @param roadData
 * @returns {Promise<any>}
 * @constructor
 */
const M_Traffic = async function(){
    const list = await self.util.getJsonFormApi("/monitoring/getTrafficInfo.ajax");
    for(const info of list) {
        info['conggrade'] = self.util.getTrafficConGrade(info.roadRank, info.avgVhclSpeedAvg);
    }
    return list;

}