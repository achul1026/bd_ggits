/**
 * 대중교통 위험운영 구간 분석
 * @returns {Promise<any>}
 * @constructor
 */
const BD_PT_Danger_Analysis = async function(searchOption){
    let list = await self.util.getJsonFormApi("/bigdata/getPublicTransferDangerInfo.ajax?"+searchOption);
    return list;
}