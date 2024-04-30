package com.neighbor21.ggits.web.controller.monitoring;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.neighbor21.ggits.api.module.common.CMBusComponent;
import com.neighbor21.ggits.api.module.common.CMBusStationComponent;
import com.neighbor21.ggits.api.module.monitoring.MBusComponent;
import com.neighbor21.ggits.api.module.monitoring.MEmergencyComponent;
import com.neighbor21.ggits.api.module.monitoring.MTrafficComponent;
import com.neighbor21.ggits.api.module.monitoring.MWarningComponent;
import com.neighbor21.ggits.api.module.monitoring.MWeatherComponent;
import com.neighbor21.ggits.common.dto.MonitoringDashboardDTO;
import com.neighbor21.ggits.common.dto.MonitoringTrafficCurDto;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1m;
import com.neighbor21.ggits.common.entity.GgbisBusRoute;
import com.neighbor21.ggits.common.entity.GgbisBusStation;
import com.neighbor21.ggits.common.entity.GgbisBusrouteLink;
import com.neighbor21.ggits.common.entity.GgsplBusPeriodicinfo;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.entity.KmaShtrmWthrFrcst;
import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.entity.MrtTrfHlctcCngstnSctn;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclCurInfo;
import com.neighbor21.ggits.common.entity.TsDggdVhclRungInfoCur;
import com.neighbor21.ggits.common.enums.MonitoringModuleInfo;
import com.neighbor21.ggits.common.mapper.GgbisBusStationMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfHlctcCngstnSctnMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.historymng.HistoryMngService;
import com.neighbor21.ggits.web.service.monitoring.MonitoringDashboardService;

/**
 * The type Monitoring controller.
 */
@Controller
public class MonitoringController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HistoryMngService historyMngService;

    @Autowired
    MTrafficComponent trafficInfoComponent;

    @Autowired
    MEmergencyComponent mEmergencyComponent;
    
    @Autowired
    MWeatherComponent mWeatherComponent;
    
    @Autowired
    MBusComponent mBusComponent;

    @Autowired
    MWarningComponent mWarningComponent;
    
    @Autowired
    MonitoringDashboardService monitoringDashboardService;
    
    @Autowired
    MOpMenuMapper mOpMenuMapper;
    
    @Autowired
    MOpOperatorMapper mOpOperatorMapper;
    
    @Autowired
    MOpCodeMapper mOpCodeMapper;

    @Autowired
    CMBusStationComponent cmBusStationComponent;

    @Autowired
    CMBusComponent cmBusComponent;

    @Autowired
    GgbisBusStationMapper ggbisBusStationMapper;
    
	@Autowired
	MrtBusSttnAnalMapper mrtBusSttnAnalMapper;
	
	@Autowired
	MrtTrfHlctcCngstnSctnMapper mrtTrfHlctcCngstnSctnMapper;
    
    @GetMapping("/monitoring.do")
    public String viewMonitoring(){
        return "view/monitoring/monitoring";
    }
    
    /**
     * @Method Name : viewMonitoringVipDashboard
     * @작성일 : 2023. 12. 27.
     * @작성자 : KY.LEE
     * @Method 설명 : VIP 대시보드 화면
     * @return
     */
    @GetMapping("/monitoring/vipDashboard.do")
    public String viewMonitoringVipDashboard(Model model){
    	
    	model.addAttribute("mOpOperator", LoginSessionUtils.getMOpOperatorInfo());
    	model.addAttribute("now", BDDateFormatUtil.isNowStr("yyyy년 MM월 dd일"));
        return "vip/monitoring/monitoringVipDashboard";
    }
    
//    @GetMapping("/monitoring/dashboardBackup.do")
//    public String viewMonitoringDashboardBackup(Model model){
//    	//추후 테이블에서ㅏ가져오기
//    	long layoutNo = mOpOperatorMapper.findLayoutNoByOprtrId(LoginSessionUtils.getOprtrId());
//    	//시군구 코드 목록
//    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
//    	model.addAttribute("layoutNo", layoutNo);
//    	model.addAttribute("monitoringDashboardDTO", monitoringDashboardService.getUserLayoutInfo());
//    	return "view/monitoring/monitoringDashboardNew";
//    }

    @GetMapping("/monitoring/dashboard.do")
    public String viewMonitoringDashboard(Model model){
        //시군구 코드 목록
        model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
        return "view/monitoring/monitoringDashboard";
    }

    @PostMapping("/layout/update.ajax")
    public @ResponseBody CommonResponse<?>updateLayoutMstInfo(@RequestBody MonitoringDashboardDTO monitoringDashboardDTO){
    	
		monitoringDashboardService.updateMOpLayoutMstInfo(monitoringDashboardDTO.getLayoutList(),monitoringDashboardDTO.getLayoutNo());
		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "현재 레이아웃 정보가 수정되었습니다.");
    }
    
    @GetMapping("/monitoring/{type}/loadMonitoringModule.ajax")
    public String loadMonitoringModule(Model model,@PathVariable(required = true) String type , @RequestParam (name="roadDiv", required = false) String roadDiv)  {
    	MonitoringModuleInfo monitoringModuleInfo = MonitoringModuleInfo.getMonitoringModuleInfo(type);
    	
    	switch(monitoringModuleInfo) {
	    	case DASHBOARD_SMCRD_TOP10 :
	    		model.addAttribute("smcrdTop10", monitoringDashboardService.getSmcrdTop10Info());
	    		break;
	    	case DASHBOARD_EMERG_BY_MNGINSTCD :
//                List<ScsEmrgVhclPathLog> result = monitoringDashboardService.getEmergByMnginstcd();
//                Gson gson = new Gson();
//                String jsonArray = gson.toJson(result);
//	    		model.addAttribute("emergByMnginstcd", jsonArray);
//	    		if(!result.isEmpty()) {
//	    			model.addAttribute("totalCnt", result.size());
	    		
//	    		}

	    		List<Object> labelArray = new ArrayList<Object>();
	    		List<Object> dataArray = new ArrayList<Object>();
	    		
	    		Map<String,Object> chartMap = monitoringDashboardService.getEmergByMnginstcdChartDataInfo();

	    		if(chartMap != null) {
	    			labelArray = chartMap.get("labelArray")!= null ? GgitsCommonUtils.pgArrayToJavaArray(chartMap.get("labelArray")):null;
	    			dataArray = chartMap.get("dataArray")!= null ? GgitsCommonUtils.pgArrayToJavaArray(chartMap.get("dataArray")):null;
	    		}
	    		
                Gson gson = new Gson();
                
                String labelArrayStr = gson.toJson(labelArray);
                String dataArrayStr = gson.toJson(dataArray);
              
	    		model.addAttribute("labelArray", labelArrayStr);
	    		model.addAttribute("dataArray", dataArrayStr);
	    		
	    		break;
	    	case DASHBOARD_WARNING_BY_MNGINSTCD :
	    		model.addAttribute("warningByMnginstcd", monitoringDashboardService.getWarningByMnginstcd());
	    		break;
	    	case DASHBOARD_SVC_CONGESTION_TOP10 :
    		   MrtTrfHlctcCngstnSctn mrtTrfHlctcCngstnSctn = new MrtTrfHlctcCngstnSctn();
    		   String anlsMm =  mrtTrfHlctcCngstnSctnMapper.findMaxAnlsMm();
    		   mrtTrfHlctcCngstnSctn.setAnlsMm(anlsMm);
    		   if(roadDiv != null && !"".equals(roadDiv)) {
    			   mrtTrfHlctcCngstnSctn.setRoadDiv(roadDiv);
    		   }
    		   
				try {
					model.addAttribute("anlsMm", GgitsCommonUtils.formatDate(anlsMm, "yyyyMM", "yyyy년 MM월"));
				} catch (ParseException e1) {
					
				}
	    		model.addAttribute("roadDiv", roadDiv);
	    		model.addAttribute("roadDivList", mrtTrfHlctcCngstnSctnMapper.findRoadDivList());
	    		model.addAttribute("svcCongestionTop10", monitoringDashboardService.getSvcCongestionTop10(mrtTrfHlctcCngstnSctn));
	    		break;
	    	case DASHBOARD_ACDNT_PREDICTION_TOP10 :
	    		model.addAttribute("acdntPredictionTop10", monitoringDashboardService.getAcdntPredictionTop10Info());
	    		break;
	    	case DASHBOARD_BUS_STATION_USAGE :
	    		String rideYmd = mrtBusSttnAnalMapper.findMaxRideYmd();
				try {
					model.addAttribute("rideYmd", GgitsCommonUtils.formatDate(rideYmd,"yyyyMMdd","yyyy년 MM월 dd일"));
				} catch (ParseException e) {
					throw new CommonException(ErrorCode.DATE_PARSE_ERROR);
				}
	    		model.addAttribute("busStationUsage", monitoringDashboardService.getBusStationUsageInit(rideYmd));
	    		break;
	    	case DASHBOARD_POPULATION_PREDICTION_TOP10 :
	    		String baseYmd = GgitsCommonUtils.getCalculationDateToString(1,"yyyy년 MM월 dd일 HH시mm분",Calendar.HOUR);
	    		model.addAttribute("baseYmd", baseYmd);
	    		model.addAttribute("populationPredictionTop10", monitoringDashboardService.getPopulationPredictionTop10());
	    		break;
	    	case DASHBOARD_EMERG_ACHEIVE_PTG :
	    		String nowStr = BDDateFormatUtil.isNowStr("HH시 mm분");
	    		model.addAttribute("nowStr", nowStr);
	    		model.addAttribute("emergAcheivePtg", monitoringDashboardService.getEmergAcheivePtg());
	    		break;
	    	default :
	    		throw new CommonException(ErrorCode.DEFAULT_ERROR);
    	}
    	return "monitoring/module/"+monitoringModuleInfo.getName();
    }
    
    /**
     * 실시간 교통현황 data
     *
     * @return the list
     */
    @GetMapping("/monitoring/getTrafficInfo.ajax")
    public @ResponseBody ResponseEntity<?> getTrafficInfo(
            @RequestParam(name = "minimize", required = false, defaultValue = "false") String minimize
    ){
        List<ExtGgitsLinkStd1m> list = trafficInfoComponent.getRealtimeTrafficInfo(minimize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 스마트교차로 교통량 조회
     * @return
     */
    @GetMapping("/monitoring/getVolumeSmart.ajax")
    public @ResponseBody ResponseEntity<?> getVolumeSmart(){
        List<MonitoringTrafficCurDto> list = trafficInfoComponent.getRealtimeVolumeSmart();
         return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/monitoring/getVolumeSmartDrct.ajax")
    public @ResponseBody ResponseEntity<?> getVolumeSmartDrct(){
        List<MonitoringTrafficCurDto> list = trafficInfoComponent.getRealtimeVolumeSmartDrct();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * VDS 교통량 조회
     * @return
     */
    @GetMapping("/monitoring/getVolumeVDS.ajax")
    public @ResponseBody ResponseEntity<?> getVolumeVDS(){
        List<MonitoringTrafficCurDto> list = trafficInfoComponent.getRealtimeVolumeVDS();
         return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 실시간 긴급차량 data
     *
     * @return the list
     */
    @GetMapping("/monitoring/getEmergencyInfo.ajax")
    public @ResponseBody ResponseEntity<?> getEmergencyInfo(){
        long startTime = System.currentTimeMillis();
        List<ScsEmrgVhclCurInfo> list = mEmergencyComponent.getEmergencyVehicleMoveInfoList();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        logger.info("[실시간 긴급차량 조회] 실행시간 "+((double)timeElapsed / 1000)+" 초" );
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 실시간 위험물 차량 data
     * @return
     */
    @GetMapping("/monitoring/getDggdVechicleMoveInfoListAll.ajax")
    public @ResponseBody ResponseEntity<?> getDggdVechicleMoveInfoListAll(){

        long startTime = System.currentTimeMillis();
        List<TsDggdVhclRungInfoCur> list = mEmergencyComponent.getDggdVechicleMoveInfoListAll();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        logger.info("[실시간 위험물 조회] 실행시간 "+((double)timeElapsed / 1000)+" 초" );
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 실시간 위험물 차량 data(긴급차량 반경 1km)
     * @return
     */
    @GetMapping("/monitoring/getDangerVehicleInfo.ajax")
    public @ResponseBody ResponseEntity<?> getDangerVehicleInfo(){
        List<TsDggdVhclRungInfoCur> list = mEmergencyComponent.getDggdVechicleMoveInfoList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 실시간 위험물 차량 data(돌발위치 반경 1km)
     * @return
     */
    @GetMapping("/monitoring/getDangerVehicleInfoByWarning.ajax")
    public @ResponseBody ResponseEntity<?> getDangerVehicleInfoByWarning(){

        long startTime = System.currentTimeMillis();
        List<TsDggdVhclRungInfoCur> list = mEmergencyComponent.getDggdVechicleMoveInfoListByWarning();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        logger.info("[실시간 위험물 차량 data(돌발위치 반경 1km)] 실행시간 "+((double)timeElapsed / 1000)+" 초" );
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    
    /**
      * @Method Name : getWarningInfo
      * @작성일 : 2023. 9. 25.
      * @작성자 : NK.KIM
      * @Method 설명 : 일일 돌발 현황 데이터 조회
      * @return
      */
    @GetMapping("/monitoring/getWarningInfo.ajax")
    public @ResponseBody ResponseEntity<?> getWarningInfo(){
        long startTime = System.currentTimeMillis();
        List<GimsMngInciDetail> list = mWarningComponent.findAllForMapMarker();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        logger.info("[일일 돌발 현황] 실행시간 "+((double)timeElapsed / 1000)+" 초" );
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 오늘 발생한 돌발이력
     * @return
     */
    @GetMapping("/monitoring/getWarningInfoToday.ajax")
    public @ResponseBody ResponseEntity<?> getWarningInfoToday(){
        List<GimsMngInciDetail> list = mWarningComponent.findAllToday();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }



    /**
     * 실시간 돌발상황 이력 조회(모니터링 우측하단 알리미)
     * @return
     */
    @GetMapping("/monitoring/getAlarmHistory.ajax")
    public @ResponseBody ResponseEntity<?> getAlarmHistory(){
        long startTime = System.currentTimeMillis();
        List<GimsMngInciDetail> warninglist = mWarningComponent.findAllWarningAlarmList();
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        logger.info("[일일 돌발 알리미 ] 실행시간 "+((double)timeElapsed / 1000)+" 초" );

        
        startTime = System.currentTimeMillis();
        List<LTcDataLog> collectErrorList = historyMngService.findAllLTcDataLogTodayListForAlarm();
        endTime = System.currentTimeMillis();
        timeElapsed = endTime - startTime;
        logger.info("[일일 장애이력 알리미 ] 실행시간 "+((double)timeElapsed / 1000)+" 초" );

        List<Map<String,Object>> alarmList = new ArrayList<>();
        for(GimsMngInciDetail item : warninglist) {
            Map<String,Object> map = new HashMap<>();
            map.put("subject", item.getDescription());
            map.put("date", item.getBeginDate());
            map.put("type", "warning");
            map.put("data", item);
            alarmList.add(map);
        }

        for(LTcDataLog item : collectErrorList) {
            Map<String,Object> map = new HashMap<>();
            map.put("subject", item.getJobNm());
            map.put("date", BDDateFormatUtil.format(item.getClctStartDt(), "yyyy-MM-dd HH:mm:ss"));
            map.put("type", "error");
            map.put("data", item);
            alarmList.add(map);
        }
        return new ResponseEntity<>(alarmList, HttpStatus.OK);
    }

    /**
     * 실시간 버스  data
     *
     * @return the list
     */
    @GetMapping("/monitoring/getBusInfo.ajax")
    public @ResponseBody ResponseEntity<?> getBusInfo(){

        List<GgsplBusPeriodicinfo> list = mBusComponent.getRealtimeBusMoveInfo(null);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 버스 노선 정보 조회
     * @return
     */
    @GetMapping("/monitoring/getBusRouteInfo.ajax")
    public @ResponseBody ResponseEntity<?> getBusRouteInfo(
            @RequestParam(name = "routeId", required = false) String routeId,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "updown", required = false) String updown

    ){
        List<GgbisBusrouteLink> list = null;
        if(BDStringUtil.isNull(routeId)){
            list = cmBusComponent.getAllBusRouteLinkInfo();
        }else{
            if(BDStringUtil.isNull(direction)){
                if(BDStringUtil.isNull(updown)){
                    list = cmBusComponent.getBusRouteLinkInfoByRouteId(routeId);
                }else{
                    list = cmBusComponent.getBusRouteLinkInfoUpDownByRouteId(routeId);
                }
            }else{
                if(direction.equals("reverse")) {
                    // 역방향
                    list = cmBusComponent.getBusRouteLinkInfoByRouteIdReverse(routeId);
                }else {
                    // 정방향
                    list = cmBusComponent.getBusRouteLinkInfoByRouteIdForward(routeId);
                }
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 버스 정류장 정보 조회
     * @return
     */
    @GetMapping("/monitoring/getBusStationList.ajax")
    public @ResponseBody ResponseEntity<?> getBusStationList(
            @RequestParam(name = "routeId", required = false) String routeId
    ){
        List<GgbisBusStation> list = cmBusStationComponent.getBusStationList(routeId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 버스정류장 노선여러개로 정보 조회
     * @param routeIds
     * @return
     */
    @GetMapping("/monitoring/getBusStationListByRouteIds.ajax")
    public @ResponseBody ResponseEntity<?> getBusStationListByRouteIds(
            @RequestParam(name = "routeIds", required = false) String routeIds
    ){
        List<GgbisBusStation> list = cmBusStationComponent.getBusStationListByRouteIds(routeIds.split(","));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 버스정류장 노선리스트 조회
     * @param stationId
     * @return
     */
    @GetMapping("/monitoring/getBusStationRouteList.ajax")
    public @ResponseBody ResponseEntity<?> getBusStationRouteList(
            @RequestParam("stationId") String stationId
    ){
        List<GgbisBusRoute> list = cmBusStationComponent.getBusStationRouteList(stationId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 버스정류장 노선리스트 조회
     * @param routeId
     * @return
     */
    @GetMapping("/monitoring/getStartEndStationInfoByRouteId.ajax")
    public @ResponseBody ResponseEntity<?> getStartEndStationInfoByRouteId(
            @RequestParam("routeId") String routeId
    ){
        GgbisBusStation data = ggbisBusStationMapper.findOneStartEndStationInfoByRouteId(routeId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
    

    /**
     * 기상예보 조회
     * @param date
     * @param time
     * @return
     */
    @GetMapping("/monitoring/getWeatherInfo.ajax")
    public @ResponseBody ResponseEntity<?> getWeatherInfo(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "time", required = false) String time
    ){

        List<KmaShtrmWthrFrcst> list = mWeatherComponent.getWeatherList(date, time);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
