/**
 * 유동인구 밀집조회
 * @param date
 * @param time
 * @constructor
 */
const BD_Population = async function(date, stTime, endTime){
    let list = await self.util.getJsonFormApi("/bigdata/getPopulationInfo.ajax?date="+date);
    return {
        list : list,
        stTime : stTime,
        endTime : endTime
    };
}