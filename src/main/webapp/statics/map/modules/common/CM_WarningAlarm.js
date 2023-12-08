/**
 * 버스 정류장 위치 호출
 * @returns {Promise<{featureCollection: {features: *, type: string}}>}
 * @constructor
 */
const CM_WarningAlarm = async function(){
    let list = await self.util.getJsonFormApi("/monitoring/getAlarmHistory.ajax");
    const sorted_list = list.sort(function(a,b){
        return new Date(a.date).getTime() - new Date(b.date).getTime();
    }).reverse();
    return sorted_list;
}