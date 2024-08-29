package com.neighbor21.ggits.web.controller.monitoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.api.module.common.CMBusComponent;
import com.neighbor21.ggits.api.module.common.CMBusStationComponent;
import com.neighbor21.ggits.api.module.monitoring.MBusComponent;
import com.neighbor21.ggits.api.module.monitoring.MEmergencyComponent;
import com.neighbor21.ggits.api.module.monitoring.MTrafficComponent;
import com.neighbor21.ggits.api.module.monitoring.MWarningComponent;
import com.neighbor21.ggits.api.module.monitoring.MWeatherComponent;
import com.neighbor21.ggits.common.dto.MapChartDataDTO;
import com.neighbor21.ggits.common.dto.MonitoringDashboardDTO;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1m;
import com.neighbor21.ggits.common.entity.GgbisBusRoute;
import com.neighbor21.ggits.common.entity.GgbisBusStation;
import com.neighbor21.ggits.common.entity.GgbisBusrouteLink;
import com.neighbor21.ggits.common.entity.GgsplBusPeriodicinfo;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.entity.KmaShtrmWthrFrcst;
import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclCurInfo;
import com.neighbor21.ggits.common.entity.TsDggdVhclRungInfoCur;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.web.service.historymng.HistoryMngService;
import com.neighbor21.ggits.web.service.monitoring.MonitoringDashboardService;

/**
 * The type Monitoring controller.
 */
@Controller
public class MonitoringController {

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

        return "vip/monitoring/monitoringVipDashboard";
    }
    
    /**
     * @Method Name : getEmergencyChartInfo
     * @작성일 : 2023. 12. 27.
     * @작성자 : KY.LEE
     * @Method 설명 : 긴급차량 차트데이터 조회
     * @return
     */
    @PostMapping("/monitoring/vip/emergency.ajax")
    public @ResponseBody CommonResponse<?> getEmergencyChartInfo(){
//    	List<Map<String,Object>> data = monitoringDashboardService.getTableData(paramMap);
		
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "차트 데이터 조회 성공", null, null);
    }
    
    /**
     * @Method Name : getLinkDelayChartInfo
     * @작성일 : 2023. 12. 27.
     * @작성자 : KY.LEE
     * @Method 설명 : 경기도 서비스 링크 정체 순위
     * @return
     */
    @PostMapping("/monitoring/vip/link/delay.ajax")
    public @ResponseBody CommonResponse<?> getLinkDelayChartInfo(){
//    	List<Map<String,Object>> data = monitoringDashboardService.getTableData(paramMap);
		
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "차트 데이터 조회 성공", null, null);
    }
    
    /**
     * @Method Name : getTrafficInfoChart
     * @작성일 : 2023. 12. 27.
     * @작성자 : KY.LEE
     * @Method 설명 : 경기도 서비스 링크 교통량 순위
     * @return
     */
    @PostMapping("/monitoring/vip/traffic.ajax")
    public @ResponseBody CommonResponse<?> getTrafficInfoChart(){
//    	List<Map<String,Object>> data = monitoringDashboardService.getTableData(paramMap);
		
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "차트 데이터 조회 성공", null, null);
    }
    
    @GetMapping("/monitoring/dashboard.do")
    public String viewMonitoringDashboard(Model model){
    	//추후 테이블에서ㅏ가져오기
    	long layoutNo = mOpOperatorMapper.findLayoutNoByOprtrId(LoginSessionUtils.getOprtrId());
    	//시군구 코드 목록
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("layoutNo", layoutNo);
    	model.addAttribute("monitoringDashboardDTO", monitoringDashboardService.getUserLayoutInfo());
    	return "view/monitoring/monitoringDashboard";
    }

    @PostMapping("/layout/update.ajax")
    public @ResponseBody CommonResponse<?>updateLayoutMstInfo(@RequestBody MonitoringDashboardDTO monitoringDashboardDTO){
    	
		monitoringDashboardService.updateMOpLayoutMstInfo(monitoringDashboardDTO.getLayoutList(),monitoringDashboardDTO.getLayoutNo());
		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "현재 레이아웃 정보가 수정되었습니다.");

    }

    @PostMapping("/monitoring/loadChartDataAjax.ajax")
    public @ResponseBody CommonResponse<?> loadChartDataAjax(@RequestParam Map<String,Object> paramMap){
    	List<MapChartDataDTO> data = monitoringDashboardService.getChartDataInfo(paramMap);
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "차트 데이터 조회 성공", null, data);
    	
    }
    
    @PostMapping("/monitoring/getTableData.ajax")
    public @ResponseBody CommonResponse<?> getTableData(@RequestParam Map<String,Object> paramMap){
    	List<Map<String,Object>> data = monitoringDashboardService.getTableData(paramMap);
    	if(data == null) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "테이블 정보 조회중 오류가 발생했습니다.");
    	}
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "테이블 데이터 조회 성공", null, data);
    }
    
    /**
     * TEST용 실시간 교통현황 data
     *
     * @return the list
     */
    @GetMapping("/monitoring/getTrafficInfo.ajax")
    public @ResponseBody ResponseEntity<?> getTrafficInfo(){
        List<ExtGgitsLinkStd1m> list = trafficInfoComponent.getRealtimeTrafficInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 실시간 긴급차량 data
     *
     * @return the list
     */
    @GetMapping("/monitoring/getEmergencyInfo.ajax")
    public @ResponseBody ResponseEntity<?> getEmergencyInfo(){
        List<ScsEmrgVhclCurInfo> list = mEmergencyComponent.getEmergencyVehicleMoveInfoList();
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
        List<TsDggdVhclRungInfoCur> list = mEmergencyComponent.getDggdVechicleMoveInfoListByWarning();
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
    	List<GimsMngInciDetail> list = mWarningComponent.findAllForMapMarker();
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 실시간 돌발상황 이력 조회(모니터링 우측하단 알리미)
     * @return
     */
    @GetMapping("/monitoring/getAlarmHistory.ajax")
    public @ResponseBody ResponseEntity<?> getAlarmHistory(){
        List<GimsMngInciDetail> warninglist = mWarningComponent.findAllWarningAlarmList();
        List<LTcDataLog> collectErrorList = historyMngService.findAllLTcDataLogTodayListForAlarm();

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
            @RequestParam(name = "routeId", required = false) String routeId
    ){
        List<GgbisBusrouteLink> list = null;
        if(BDStringUtil.isNull(routeId)){
            list = cmBusComponent.getAllBusRouteLinkInfo();
        }else{
            list = cmBusComponent.getBusRouteLinkInfoByRouteId(routeId);
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
