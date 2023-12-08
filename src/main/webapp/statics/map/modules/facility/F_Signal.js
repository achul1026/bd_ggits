/**
 * 시설물 신호정보 조회
 * @returns {Promise<any>}
 * @constructor
 */
const F_Signal = async function(){
    let data = await self.util.getJsonFormApi("/facility/getSmartIntersection.ajax");
    return data;
}