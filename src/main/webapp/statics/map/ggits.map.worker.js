let monitoring_roadlist = null;
let interval_control = {};
let pause = false;
self.importScripts(
    "./util/proj4js.js"
    ,"./util/turf.min.js"
    ,"./util/wellknown.js"
    ,"./ggits.map.util.js"
    ,"./env/ggits.map.env.js"
    ,"./modules/common/CM_BusStation.js"
    ,"./modules/common/CM_StartEndStationByRoute.js"
    ,"./modules/common/CM_WarningAlarm.js"
    ,"./modules/common/CM_CrossRoad_Camera.js"
    ,"./modules/common/CM_BusRoute.js"
    ,"./modules/monitoring/M_Traffic.js?v=1.0"
    ,"./modules/monitoring/M_Warning.js?v=1.0"
    ,"./modules/monitoring/M_Bus.js?v1.1"
    ,"./modules/monitoring/M_Emergency.js?v=1.0"
    ,"./modules/monitoring/M_Danger_Vehicle.js"
    ,"./modules/monitoring/M_Volume_Smart.js"
    ,"./modules/monitoring/M_Volume_VDS.js"
    ,"./modules/monitoring/M_Weather.js"
    ,"./modules/facility/F_SmartIntersection.js?v=1.1"
    ,"./modules/facility/F_VDS.js"
    ,"./modules/facility/F_DSRC.js"
    ,"./modules/facility/F_Signal.js"
    ,"./modules/bigdata/BD_Population.js?v=1.3"
    ,"./modules/bigdata/BD_Pattern_Traffic_Quantity.js?v=1.2"
    ,"./modules/bigdata/BD_Pattern_Traffic_Abn_Los.js?v=1.2"
    ,"./modules/bigdata/BD_Pattern_Traffic_Cngstn.js?v=1.0"
    ,"./modules/bigdata/BD_Traffic_Active_Effect_Analysis.js?v=1.0"
    ,"./modules/bigdata/BD_Traffic_Active_Effect_Analysis_SvcLink.js?v=1.0"
    ,"./modules/bigdata/BD_Traffic_Active_Effect_Analysis_Merge.js?v=1.0"
    ,"./modules/bigdata/BD_Danger_Road.js?v=1.0"
    ,"./modules/bigdata/BD_Danger_Zone.js?v=1.0"
    ,"./modules/bigdata/BD_Danger_Zone_By_Type.js?v=1.0"
    ,"./modules/bigdata/BD_Accident_By_SGG.js?v=1.0"
    ,"./modules/bigdata/BD_PT_Danger_Analysis.js?v=1.0"
    ,"./modules/bigdata/BD_Prediction_Accident.js?v=1.0"
    ,"./modules/bigdata/BD_Prediction_Cross_Traffic.js?v=1.0"
    ,"./modules/bigdata/BD_Public_Transfer_Usage_By_St_End.js?v=1.0"
    ,"./modules/bigdata/BD_Public_Transfer_Usage_By_SGG.js?v=1.0"
    ,"./modules/bigdata/BD_Public_Transfer_Duplicate_Route.js?v=1.0"
    ,"./modules/bigdata/BD_Public_Transfer_Cndcy_Route.js?v=1.0"
    ,"./modules/bigdata/BD_Public_Transfer_Lndi.js?v=1.0"
    ,"./modules/bigdata/BD_Public_Transfer_Lndi_All.js?v=1.0"
    ,"./modules/bigdata/BD_Trfvlm_Anytm_Hghw.js?v=1.0"
    ,"./modules/bigdata/BD_Trfvlm_Anytm_Nlrd.js?v=1.0"
    ,"./modules/bigdata/BD_Trfvlm_Ordtm.js?v=1.0"
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
    const eventCode = e.data.event;
    let eventName = "";
    try {
        switch (e.data.event) {
            case "CM_BUS_STATION" :
                eventName = "버스정류장 정보";
                resultData = await CM_BusStation(e.data.searchOption);
                break;
            case "CM_STARTENDSTATIONBYROUTE" :
                eventName = "버스정류장 정보";
                resultData = await CM_STARTENDSTATIONBYROUTE(e.data.routeId);
                break;
            case "BD_BUS_BIT_STATION" :
            case "BD_BUS_STATION" :
                eventName = "버스정류장 정보";
                resultData = await CM_BusStation(e.data.searchOption);
                break;
            case "BD_BUS_USAGE_BY_STATION" :
                eventName = "버스정류장 정보";
                resultData = await CM_BusStation(e.data.searchOption);
                break;
            case "BD_BUS_ROUTE_CURVE_ANL" :
            case "BD_BUS_ROUTE_PASSENGER_ANL" :
            case "BD_BUS_BIT_ROUTE" :
            case "BD_BUS_ROUTE_USE_CALC" :
            case "CM_BUS_ROUTE" :
                eventName = "버스노선 정보";
                resultData = await CM_BusRoute(e.data.routeId, e.data.direction, e.data.updown);
                if(!resultData.error)
                    resultData.bbox = self.util.getBBOX(resultData.featureCollection);
                break;
            case "CM_WARNING_ALARM" :
                eventName = "푸쉬 알람 ";
                resultData = await CM_WarningAlarm();
                break;
            case "CM_CROSSROAD_CAMERA" :
                eventName = "스마트 교차로 카메라";
                resultData = await CM_CrossRoad_Camera();
                break;
            case "M_TRAFFIC" :
                eventName = "실시간 소통정보";
                resultData = await M_Traffic();
                break;
            case "M_TRAFFIC_MINIMIZE" :
                eventName = "실시간 소통정보";
                resultData = await M_Traffic("true");
                break;
            case "M_WARNING" :
                eventName = "실시간 돌발정보";
                resultData = await M_Warning();
                break;
            case "M_EMERGENCY" :
                eventName = "실시간 긴급차량 정보";
                resultData = await M_Emergency();
                break;
            case "M_DANGER_VEHICLE" :
                eventName = "위험물 차량 정보";
                resultData = await M_Danger_Vehicle();
                break;
            case "M_BUS" :
                eventName = "실시간 버스 이동현황 정보";
                resultData = await M_Bus();
                break;
            case "M_WEATHER" :
                eventName = "실시간 날씨정보";
                resultData = await M_Weather();
                break;
            case "M_VOLUME_SMART" :
                eventName = "실시간 스마트교차로 교통량 정보";
                resultData = await M_Volume_Smart();
                break;
            case "M_VOLUME_VDS" :
                eventName = "실시간 VDS 교통량 정보";
                resultData = await M_Volume_VDS();
                break;
            case "F_SMART" :
                eventName = "스마트 교차로 정보";
                resultData = await F_SmartIntersection(e.data.type, e.data.mngInstCd);
                break;
            case "F_VDS" :
                eventName = "VDS 정보";
                resultData = await F_VDS();
                break;
            case "F_DSRC" :
                eventName = "DSRC 정보";
                resultData = await F_DSRC(e.data.type);
                break;
            case "F_SIGNAL" :
                eventName = "신호 정보";
                resultData = await F_Signal();
                break;
            case "BD_POPULATION" :
                eventName = "인구 밀집예측 정보";
                resultData = await BD_Population(e.data.searchOption);
                break;
            case "BD_PATTERN_TRAFFIC_QUANTITY" :
                eventName = "교통패턴 정보";
   				resultData = await BD_Pattern_Traffic_Quantity(e.data.searchOption);
                break;
            case "BD_PATTERN_TRAFFIC_ABN_LOS" :
                eventName = "교통패턴 정보";
                resultData = await BD_Pattern_Traffic_Abn_Los(e.data.searchOption);
                break;
            case "BD_PATTERN_TRAFFIC_CNGSTN" :
                eventName = "교통패턴 상습정체구간 정보";
                resultData = await BD_Pattern_Traffic_Cngstn(e.data.searchOption);
                break;
            case "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS" :
                eventName = "교통활동효과 정보";
   				resultData = await BD_Traffic_Active_Effect_Analysis(e.data.searchOption);
                const so =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
                if(!resultData.error && so.linkId)
                    resultData.bbox = self.util.getBBOX(resultData.featureCollection);
                break;
            case "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_SVC_LINK" :
                eventName = "교통활동효과 정보";
                resultData = await BD_Traffic_Active_Effect_Analysis_SvcLink(e.data.searchOption);
                const svcSo =  Object.fromEntries(new URLSearchParams(e.data.searchOption));
                if(!resultData.error && svcSo.linkId)
                    resultData.bbox = self.util.getBBOX(resultData.featureCollection);
                break;
            case "BD_TRAFFIC_ACTIVE_EFFECT_ANALYSIS_MERGE" :
                eventName = "교통활동효과 통합 정보";
                resultData = await BD_Traffic_Active_Effect_Analysis_Merge(e.data.searchOption);
                break;
            case "BD_DANGER_ZONE" :
                eventName = "교통 위험구간 정보";
                resultData = await BD_Danger_Zone();
                break;
            case "BD_PT_DANGER_ANALYSIS" :
                eventName = "대중교통 위험운영 구간분석 정보";
                resultData = await BD_PT_Danger_Analysis(e.data.searchOption);
                if(!resultData.error)
                    resultData.bbox = self.util.getBBOX(resultData.featureCollection);
                break;
            case "BD_DANGER_ROAD" :
                eventName = "도로안전 정보";
                resultData = await BD_Danger_Road(e.data.searchOption);
                break;
            case "BD_DANGER_ZONE_BY_TYPE" :
            case "BD_DANGER_ZONE_BY_TYPE_HEATMAP" :
                eventName = "교통사고 위험지역 정보";
                resultData = await BD_Danger_Zone_By_Type(e.data.searchOption);
                break;
            case "BD_ACCIDENT_BY_SGG" :
                eventName = "시군구별 교통사고 위험지역";
                resultData = await BD_Accident_By_SGG(e.data.searchOption);
                break;
            case "BD_PREDICTION_ACCIDENT" :
                eventName = "사고 예측 정보";
                resultData = await BD_Prediction_Accident(e.data.searchOption);
                break;
            case "BD_PREDICTION_CROSS_TRAFFIC" :
                eventName = "교차로 교통량 예측 정보";
                resultData = await BD_Prediction_Cross_Traffic(e.data.searchOption);
                break;
            case "BD_PUBLIC_TRANSFER_CNDCY_ROUTE" :
                eventName = "대중교통 최적화 노선 정보";
                resultData = await BD_Public_Transfer_Cndcy_Route(e.data.searchOption);
                if(!resultData.error)
                    resultData.bbox = self.util.getBBOX(resultData.featureCollection);
                break;
            case "BD_PUBLIC_TRANSFER_LNDI" :
                eventName = "대중교통 하차정보 정보";
                resultData = await BD_Public_Transfer_Lndi(e.data.searchOption);
                break;
            case "BD_PUBLIC_TRANSFER_LNDI_ALL" :
                eventName = "대중교통 하차정보 정보";
                resultData = await BD_Public_Transfer_Lndi_All(e.data.searchOption);
                break;
            case "BD_PUBLIC_TRANSFER_USAGE_BY_ST_END" :
                resultData = await BD_Public_Transfer_Usage_By_St_End(e.data.searchOption);
                if(!resultData.error)
                    resultData.bbox = self.util.getBBOX(resultData.featureCollection);
                break;
            case "BD_PUBLIC_TRANSFER_USAGE_BY_SGG" :
                resultData = await  BD_Public_Transfer_Usage_By_SGG(e.data.searchOption);
                break;
            case "BD_BUS_ROUTE_DUPLICATE_ROUTE" :
                eventName = "대중교통 중복구간 정보";
                resultData = await  BD_Public_Transfer_Duplicate_Route(e.data.searchOption);
                if(!resultData.error)
                    resultData.bbox = self.util.getBBOX(resultData.routeFeatureCollection.featureCollection);
                break;
            case "BD_TRFVLM_ANYTM_HGHW" :
                resultData = await  BD_Trfvlm_Anytm_Hghw(e.data.searchOption);
                break;
            case "BD_TRFVLM_ANYTM_NLRD" :
                resultData = await  BD_Trfvlm_Anytm_Nlrd(e.data.searchOption);
                break;
            case "BD_TRFVLM_ORDTM" :
                resultData = await  BD_Trfvlm_Ordtm(e.data.searchOption);
                break;
        }
    }catch(err) {
        console.log(eventCode + " 에러", e);
        console.log(err);
        clearWorkerInterval(e);
        resultData.error = true;
        resultData.isThrow = true;
    }
    resultData.eventName = eventName;
    resultData.jobId = e.data.jobId;
    resultData.event = e.data.event;
    resultData = Object.assign(resultData, e.data);
    return resultData;
}