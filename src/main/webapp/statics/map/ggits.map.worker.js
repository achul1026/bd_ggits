let monitoring_roadlist = null;
let interval_control = {};
let pause = false;
self.importScripts(
    "./util/proj4js.js"
    ,"./util/wellknown.js"
    ,"./ggits.map.util.js"
    ,"./env/ggits.map.env.js"
    ,"./modules/common/CM_BusStation.js"
    ,"./modules/common/CM_WarningAlarm.js"
    ,"./modules/common/CM_CrossRoad_Camera.js"
    ,"./modules/common/CM_BusRoute.js"
    ,"./modules/monitoring/M_Traffic.js"
    ,"./modules/monitoring/M_Warning.js"
    ,"./modules/monitoring/M_Bus.js"
    ,"./modules/monitoring/M_Emergency.js"
    ,"./modules/monitoring/M_Danger_Vehicle.js"
    ,"./modules/monitoring/M_Weather.js"
    ,"./modules/facility/F_SmartIntersection.js"
    ,"./modules/facility/F_VDS.js"
    ,"./modules/facility/F_DSRC.js"
    ,"./modules/facility/F_Signal.js"
    ,"./modules/bigdata/BD_Population.js"
    ,"./modules/bigdata/BD_Pattern_Traffic_Quantity.js"
    ,"./modules/bigdata/BD_Traffic_Active_Effect_Analysis.js"
    ,"./modules/bigdata/BD_Traffic_Active_Effect_Analysis_Merge.js"
    ,"./modules/bigdata/BD_Danger_Zone.js"
    ,"./modules/bigdata/BD_Danger_Zone_By_Type.js"
    ,"./modules/bigdata/BD_Accident_By_SGG.js"
    ,"./modules/bigdata/BD_PT_Danger_Analysis.js"
    ,"./modules/bigdata/BD_Prediction_Accident.js"
    ,"./modules/bigdata/BD_Prediction_Cross_Traffic.js"
);

Proj4js.defs["EPSG:5179"] = "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +towgs84=0,0,0,0,0,0,0 +units=m +no_defs";
Proj4js.defs["EPSG:4326"] = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";
const source_srs = new Proj4js.Proj("EPSG:5179");
const target_srs = new Proj4js.Proj("EPSG:4326");
self.convertSrs = function(){

}
self.util = new GitsMapUtil();
self.env = GITS_ENV;
self.onmessage = function (e){
    if(e.data.event){
        e.data.event = e.data.event.toUpperCase();
    }
    if(e.data.pause === true) {
        pause = true;
    }
    if(e.data.resume === true) {
        pause = false;
    }
    if(e.data.clearIntervallAll === true) {
        clearWorkerIntervalAll();
        return;
    }
    if(e.data.stopInterval === true) {
        clearWorkerInterval(e);
        return;
    }

    /*console.log(`Event In : ${e.data.event}`);*/
    if(e.data.loop === true) {
        clearWorkerInterval(e);
        let loopTime = e.data.loopTime ? e.data.loopTime : 5000;
        console.log(`Loop ${e.data.event} Start [${loopTime} ms]`);
        getData(e).then(r => self.postMessage(r));
        interval_control[e.data.event] = setInterval(function(){
            /*console.log("L"e.data.envet)*/
            if(!pause) getData(e).then(r => self.postMessage(r));
        }, e.data.loopTime ? e.data.loopTime : 5000);
    }else{
        clearWorkerInterval(e);
        getData(e).then(r => self.postMessage(r));
    }
}
function clearWorkerIntervalAll(){
    if(interval_control.length)
        console.log("Worker Loop All clear.");
    for(const eventName in interval_control) {
        if(interval_control[eventName] !== null){
            clearInterval(interval_control[eventName]);
            interval_control[eventName] = null;
        }
    }
}
function clearWorkerInterval(e){
    if(interval_control[e.data.event] !== null) {
        console.log("Worker Loop clear. ["+e.data.event+"]");
        clearInterval(interval_control[e.data.event]);
        interval_control[e.data.event] = null;
    }
}
async function getData(e){
    let resultData = {};
    if(pause) {
        return resultData;
    }
    try {
        switch (e.data.event) {
            case "CM_BUS_STATION" :
                resultData = await CM_BusStation();
                break;
            case "CM_BUS_ROUTE" :
                resultData = await CM_BusRoute(e.data.routeId);
                break;
            case "CM_WARNING_ALARM" :
                resultData = await CM_WarningAlarm();
                break;
            case "CM_CROSSROAD_CAMERA" :
                resultData = await CM_CrossRoad_Camera();
                break;
            case "M_TRAFFIC" :
                resultData = await M_Traffic();
                break;
            case "M_WARNING" :
                resultData = await M_Warning();
                break;
            case "M_EMERGENCY" :
                resultData = await M_Emergency();
                break;
            case "M_DANGER_VEHICLE" :
                resultData = await M_Danger_Vehicle();
                break;
            case "M_BUS" :
                resultData = await M_Bus();
                break;
            case "M_WEATHER" :
                resultData = await M_Weather();
                break;
            case "F_SMART" :
                resultData = await F_SmartIntersection();
                break;
            case "F_VDS" :
                resultData = await F_VDS();
                break;
            case "F_DSRC" :
                resultData = await F_DSRC();
                break;
            case "F_SIGNAL" :
                resultData = await F_SIGNAL();
                break;
            case "BD_POPULATION" :
                resultData = await BD_Population(e.data.data.date, e.data.data.stTime, e.data.data.endTime);
                break;
            case "BD_PATTERN_TRAFFIC_QUANTITY" :
   				resultData = await BD_Pattern_Traffic_Quantity(e.data.searchOption);
                break;
            case "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS" :
   				resultData = await BD_Traffic_Active_Effect_Analysis(e.data.searchOption);
                break;
            case "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE" :
                resultData = await BD_Traffic_Active_Effect_Analysis_Merge(e.data.searchOption);
                break;
            case "BD_DANGER_ZONE" :
                resultData = await BD_Danger_Zone();
                break;
            case "BD_PT_DANGER_ANALYSIS" :
                resultData = await BD_PT_Danger_Analysis();
                break;
            case "BD_DANGER_ZONE_BY_TYPE" :
                resultData = await BD_Danger_Zone_By_Type(e.data.searchOption);
                break;
            case "BD_ACCIDENT_BY_SGG" :
                resultData = await BD_Accident_By_SGG(e.data.searchOption);
                break;
            case "BD_PREDICTION_ACCIDENT" :
                resultData = await BD_Prediction_Accident(e.data.searchOption);
                break;
            case "BD_PREDICTION_CROSS_TRAFFIC" :
                resultData = await BD_Prediction_Cross_Traffic();
                break;
        }
    }catch(e) {
        console.error(e);
        resultData.error = true;
    }
    resultData.jobId = e.data.jobId;
    resultData.event = e.data.event;
    resultData = Object.assign(resultData, e.data);
    return resultData;
}