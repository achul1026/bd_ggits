package com.neighbor21.ggits.web.controller.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.neighbor21.ggits.api.module.monitoring.MTrafficComponent;
import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.entity.GyCommInfoNode;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclPathInfo;
import com.neighbor21.ggits.common.enums.MapMonitoringSubMenuCd;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.mapper.GyCommInfoNodeMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.ScsEmrgVhclPathInfoMapper;
import com.neighbor21.ggits.common.mapper.ScsTConIntflowMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.web.service.map.MapMonitoringService;

/**
 * 맵 서브메뉴 컨텐츠 컨트롤러
 */
@Controller
@RequestMapping("/map/monitoring")
public class MapMonitoringController {

    @Autowired
    MTrafficComponent trafficInfoComponent;
    
    @Autowired
    MapMonitoringService mapMonitoringService;
    
    @Autowired
    GimsMngInciDetailMapper gimsMngInciDetailMapper;
    
    @Autowired
    ScsEmrgVhclPathInfoMapper scsEmrgVhclPathInfoMapper;
    
    @Autowired
    ScsTConIntflowMapper scsTConIntflowMapper;
    
    /**
      * @Method Name : getMonitoringTrafficPage
      * @작성일 : 2023. 9. 19.
      * @작성자 : NK.KIM
      * @Method 설명 : 교통 현황 
      * @param mapMonitoringMenuDTO
      * @param model
      * @return
      */
    @GetMapping("/traffic/{type}.ajax")
    public String getMonitoringTrafficPage(@PathVariable String type, MapMonitoringMenuDTO mapMonitoringMenuDTO, Model model) {
    	
    	MapMonitoringMenuDTO trafficInfo = new MapMonitoringMenuDTO(); 
    	
    	MOpOperator mOpOperator = LoginSessionUtils.getMOpOperatorInfo();
    	String currentTime 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd HH:mm:ss", Calendar.HOUR);
    	String beforeTime 	= GgitsCommonUtils.getCalculationDateToString(-1, "yyyy-MM-dd HH:mm:ss", Calendar.HOUR);
    	String mngInstCd 	= mapMonitoringMenuDTO.getMngInstCd() != null ? mapMonitoringMenuDTO.getMngInstCd() : mOpOperator.getAddngCd(); //받아오는 행정 기관 파라미터없는 경우 로그인 세션 값에서 호출
    	
    	mapMonitoringMenuDTO.setCurrentTime(currentTime);
    	mapMonitoringMenuDTO.setBeforeTime(beforeTime);
    	mapMonitoringMenuDTO.setMngInstCd(mngInstCd);
    	
    	switch (MapMonitoringSubMenuCd.getEnum(type)) {
			case CUMULATIVE_TRAFFIC_VOLUME_BY_TIME_ZONE: // 시간대별 누적교통량
				trafficInfo = mapMonitoringService.findOneCumulativeTrafficVolumeByTimeZone(mapMonitoringMenuDTO);
				break;
			case AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE: // 시간대별 평균동행속도
				trafficInfo = mapMonitoringService.findOneAverageEntrainmentSpeedByTimeZone(mapMonitoringMenuDTO);
				break;
			case CUMULATIVE_TRAFFIC_VOLUME_BY_ROAD: // 도로별 누적교통량
				trafficInfo = mapMonitoringService.findOneCumulativeTrafficVolumeByRoad(mapMonitoringMenuDTO);
				break;
			case AVERAGE_ENTRAINMENT_SPEED_BY_ROAD: // 도로별 평균동행속도
				trafficInfo = mapMonitoringService.findOneAverageEntrainmentSpeedByRoad(mapMonitoringMenuDTO);
				break;
			case TRAFFIC_INFORMATION: // 소통 정보
//				if("DSRC".equals(mapMonitoringMenuDTO.getSearchType())) {
//					trafficInfo = mapMonitoringService.findOneAverageEntrainmentSpeedByRoad(mapMonitoringMenuDTO);
//					totalCnt = adsiMFaDsrcMapper.countDSRCForFacility(mapFacilityMenuDTO);
//				}else {
//					trafficInfo = mapMonitoringService.findOneAverageEntrainmentSpeedByRoad(mapMonitoringMenuDTO);
//					totalCnt = adsiMFaDsrcMapper.countDSRCForFacility(mapFacilityMenuDTO);
//				}
				break;
			case TRAFFIC_VOLUME_BY_TIME_ZONE_AND_ROAD: // 도로/시간대별 교통량
				trafficInfo = mapMonitoringService.findOneCumulativeTrafficVolumeByTimeZoneAndRoad(mapMonitoringMenuDTO);
				break;
			case AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE_AND_ROAD: // 도로/시간대별 평균동행속도
				trafficInfo = mapMonitoringService.findOneAverageEntrainmentSpeedByTimeZoneAndRoad(mapMonitoringMenuDTO);
				break;
			default:
				break;
		}
    	
    	beforeTime = GgitsCommonUtils.getCalculationDateToString(-1, "HH", Calendar.HOUR)+":00";
    	currentTime = GgitsCommonUtils.getCalculationDateToString(0, "HH", Calendar.HOUR)+":00";
    	
//    	if(totalCnt != 0) {
//    		Paging paging = new Paging();
//    		paging.setPageSize(5);
//    		paging.setPageNo(mapMonitoringMenuDTO.getPage());
//    		paging.setTotalCount(totalCnt);
//    		model.addAttribute("paging", paging);
//    	}
    	
    	model.addAttribute("beforeTime", beforeTime);
    	model.addAttribute("currentTime", currentTime);
    	model.addAttribute("trafficInfo", trafficInfo);
    	
        return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 실시간 교통신호 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/signal/{type}.ajax")
    public String getMonitoringSignalPage(@PathVariable String type,Model model,GyCommInfoNode gyCommInfoNode) {
    	
    	List<Map<String,Object>> nodeList = scsTConIntflowMapper.findAllSignalNodeList(gyCommInfoNode);
    	
    	String nodeListByJson = "";
    	if(nodeList.size() > 0) {
    		nodeListByJson = new Gson().toJson(nodeList);
    	}
    	model.addAttribute("nodeList", nodeListByJson);
    	
    	return "map/"+type;
    }
    
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 돌발현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/warning/{type}.ajax")
    public String getMonitoringWarningPage(@PathVariable String type, Map<String,Object> paramMap, Model model) {
    	
    	String startToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR);
    	String endToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR);
    	
//    	paramMap.put("startToday", startToday);
//    	paramMap.put("endToday", endToday);
    	
    	List<GimsMngInciDetail> waringList = gimsMngInciDetailMapper.findAllWarningList(paramMap);
    	
    	model.addAttribute("waringList",waringList);
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 기상현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/weather/{type}.ajax")
    public String getMonitoringWeatherPage(@PathVariable String type) {
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 긴급차량 이동현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/emergency/{type}.ajax")
    public String getMonitoringEmergencyPage(@PathVariable String type, Map<String,Object> paramMap, Model model) {
    	
    	String startToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR);
    	String endToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR);
    	
    	paramMap.put("startToday", startToday);
    	paramMap.put("endToday", endToday);
    	
    	List<ScsEmrgVhclPathInfo> emergencyList = scsEmrgVhclPathInfoMapper.findAllEmergencyList(paramMap);
    	
    	model.addAttribute("emergencyList",emergencyList);
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 시내버스 이동현황
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/bus/{type}.ajax")
    public String getMonitoringBusPage(@PathVariable String type) {

    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 유동인구 밀집예측
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/population/{type}.ajax")
    public String getMonitoringPopulationPage(@PathVariable String type) {
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 연계대상 데이터 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/linkdata/{type}.ajax")
    public String getMonitoringLinkdataPage(@PathVariable String type) {
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 서비스 운영현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/operation/{type}.ajax")
    public String getMonitoringOperationPage(@PathVariable String type,Model model) {
    	model.addAttribute("mapTotalServiceInfoDTO",mapMonitoringService.getTotalServiceInfo());
    	model.addAttribute("mapUseCaseInfoDTO", mapMonitoringService.getUseCaseInfo());
    	model.addAttribute("userLoginStats", mapMonitoringService.getUserLoginStats());
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 유스케이스 항목접속현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/usecase/{type}.ajax")
    public String getMonitoringUsecasePage(@PathVariable String type) {
    	
    	return "map/"+type;
    }
    /**
     * @Method Name : getMonitoringTrafficPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 유스케이스 항목접속현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/bigdata/{type}.ajax")
    public String getMonitoringBigdataPage(@PathVariable String type) {
    	
    	return "map/"+type;
    }


}
