package com.neighbor21.ggits.web.controller.map;

import java.util.*;

import com.neighbor21.ggits.api.module.monitoring.MWarningComponent;
import com.neighbor21.ggits.common.dto.MonitoringTrafficCurDto;
import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.mapper.*;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.neighbor21.ggits.api.module.monitoring.MBusComponent;
import com.neighbor21.ggits.api.module.monitoring.MTrafficComponent;
import com.neighbor21.ggits.common.dto.MapMonitoringLinkDataDTO;
import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.enums.MapMonitoringSubMenuCd;
import com.neighbor21.ggits.common.enums.RouteTpCd;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
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
    MBusComponent mBusComponent;

	@Autowired
	MWarningComponent mWarningComponent;
    
    @Autowired
    MapMonitoringService mapMonitoringService;
    
    @Autowired
    GimsMngInciDetailMapper gimsMngInciDetailMapper;
    
    @Autowired
    ScsEmrgVhclPathInfoMapper scsEmrgVhclPathInfoMapper;
    
    @Autowired
    ScsTConIntflowMapper scsTConIntflowMapper;
    
    @Autowired
    LTcDataLogMapper lTcDataLogMapper;

	@Autowired
	MonitoringTrafficCurMapper monitoringTrafficCurMapper;

	@Autowired
	CGmStdLinkAdstdgMppgMapper cGmStdLinkAdstdgMppgMapper;

	@Autowired
	GgsplBusRouteVltnInfoMapper ggsplBusRouteVltnInfoMapper;

	@Autowired
	MOpCodeMapper mOpCodeMapper;
    
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
    	
    	int totalCnt = 0;

    	mapMonitoringMenuDTO.setPage(mapMonitoringMenuDTO.getPage() == 0 ? 1 : mapMonitoringMenuDTO.getPage());
    	List<CGmStdLinkAdstdgMppg> dongList = new ArrayList<>();
		List<MOpCode> sggCdList = new ArrayList<>();
		List<MonitoringTrafficCurDto> list = new ArrayList<>();
    	switch (MapMonitoringSubMenuCd.getEnum(type)) {
			case CUMULATIVE_TRAFFIC_VOLUME_BY_TIME_ZONE: // 시간대별 누적교통량
//				trafficInfo = mapMonitoringService.findOneCumulativeTrafficVolumeByTimeZone(mapMonitoringMenuDTO);
				break;
			case AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE: // 시간대별 평균동행속도
//				trafficInfo = mapMonitoringService.findOneAverageEntrainmentSpeedByTimeZone(mapMonitoringMenuDTO);
				break;
			case CUMULATIVE_TRAFFIC_VOLUME_BY_ROAD: // 도로별 교통량
				trafficInfo = mapMonitoringService.findOneCumulativeTrafficVolumeByRoad(mapMonitoringMenuDTO);
				break;
			case AVERAGE_ENTRAINMENT_SPEED_BY_ROAD: // 도로별 평균동행속도
				trafficInfo = mapMonitoringService.findOneAverageEntrainmentSpeedByRoad(mapMonitoringMenuDTO);
				break;
			case TRAFFIC_INFORMATION: // 소통 정보
				String today = GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd", Calendar.HOUR);
				model.addAttribute("today" , today);
				trafficInfo = mapMonitoringService.findOneCumulativeTrafficVolumeByToday(mapMonitoringMenuDTO);
				break;
			case TRAFFIC_VOLUME_BY_TIME_ZONE_AND_ROAD: // 도로/시간대별 교통량
				dongList = cGmStdLinkAdstdgMppgMapper.findAllGroupDongNmBySggCd(mapMonitoringMenuDTO.getMngInstCd());
				sggCdList = mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD");
				list = monitoringTrafficCurMapper.findAllTrafficVolumeVDSBySearchOptionPaging(mapMonitoringMenuDTO);
				totalCnt = monitoringTrafficCurMapper.countTrafficVolumeVDSBySearchOption(mapMonitoringMenuDTO);
				break;
			case AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE_AND_ROAD: // 도로/시간대별 평균동행속도
				dongList = cGmStdLinkAdstdgMppgMapper.findAllGroupDongNmBySggCd(mapMonitoringMenuDTO.getMngInstCd());
				sggCdList = mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD");
				list = monitoringTrafficCurMapper.findAllAvgSpeedVDSBySearchOptionPaging(mapMonitoringMenuDTO);
				totalCnt = monitoringTrafficCurMapper.countAvgSpeedVDSBySearchOption(mapMonitoringMenuDTO);
				break;
			case CUMULATIVE_TRAFFIC_VOLUME_BY_VHCL_DIV:
				break;
			default:
				break;
		}

    	if(totalCnt != 0) {
    		Paging paging = new Paging();
    		paging.setPageSize(5);
    		paging.setPageNo(mapMonitoringMenuDTO.getPage());
    		paging.setTotalCount(totalCnt);
    		model.addAttribute("paging", paging);
    	}
		model.addAttribute("list", list);
		model.addAttribute("sggCdList", sggCdList);
    	model.addAttribute("dongList", dongList);
    	model.addAttribute("trafficInfo", trafficInfo);
    	
        return "map/"+type;
    }

	@GetMapping("/traffic/{type}/chartData.ajax")
	public @ResponseBody ResponseEntity<?> getMonitoringTrafficData(
			@PathVariable String type,
			@RequestParam("collectType") String collectType, /*수집원*/
			@RequestParam("collectTimeType") String collectTimeType /*주기*/
	) {
		List<MonitoringTrafficCurDto> list = mapMonitoringService.getMonitoringChartData(type, collectType, collectTimeType);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	
	/**
	 * @Method Name : getMonitoringTrafficData
	 * @작성일 : 2024. 01. 17.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 -> 교통현황 -> 차종별 교통량 그래프
	 * @param mapMonitoringMenuDTO
	 * @param model
	 * @return
	 */
	@GetMapping("/traffic/vhclDivChartData.ajax")
	public @ResponseBody ResponseEntity<?> getMonitoringTrafficData() {
		List<MonitoringTrafficCurDto> list = mapMonitoringService.findOneCumulativeTrafficVolumeByVhclDiv();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
    
    /**
     * @Method Name : getMonitoringTrafficData
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 교통 현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/traffic/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> getMonitoringTrafficData(@PathVariable String type, MapMonitoringMenuDTO mapMonitoringMenuDTO, Model model) {
    	
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	
    	int totalCnt = 0;


		List<MonitoringTrafficCurDto> list = new ArrayList<>();
    	switch (MapMonitoringSubMenuCd.getEnum(type)) {
    	case TRAFFIC_VOLUME_BY_TIME_ZONE_AND_ROAD: // 도로/시간대별 교통량
			switch (mapMonitoringMenuDTO.getCollectType()){
				case "vds" :
					list = monitoringTrafficCurMapper.findAllTrafficVolumeVDSBySearchOptionPaging(mapMonitoringMenuDTO);
					totalCnt = monitoringTrafficCurMapper.countTrafficVolumeVDSBySearchOption(mapMonitoringMenuDTO);
					break;
				case "dsrc" :
					list = monitoringTrafficCurMapper.findAllTrafficVolumeDSRCBySearchOptionPaging(mapMonitoringMenuDTO);
					totalCnt = monitoringTrafficCurMapper.countTrafficVolumeDSRCBySearchOption(mapMonitoringMenuDTO);
					break;
				case "smc" :
					list = monitoringTrafficCurMapper.findAllTrafficVolumeSmartBySearchOptionPaging(mapMonitoringMenuDTO);
					totalCnt = monitoringTrafficCurMapper.countTrafficVolumeSmartBySearchOption(mapMonitoringMenuDTO);
					break;
			}
    		break;
    	case AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE_AND_ROAD: // 도로/시간대별 평균동행속도
			switch (mapMonitoringMenuDTO.getCollectType()){
				case "vds" :
					list = monitoringTrafficCurMapper.findAllAvgSpeedVDSBySearchOptionPaging(mapMonitoringMenuDTO);
					totalCnt = monitoringTrafficCurMapper.countAvgSpeedVDSBySearchOption(mapMonitoringMenuDTO);
					break;
				case "dsrc" :
					list = monitoringTrafficCurMapper.findAllAvgSpeedDSRCBySearchOptionPaging(mapMonitoringMenuDTO);
					totalCnt = monitoringTrafficCurMapper.countAvgSpeedDSRCBySearchOption(mapMonitoringMenuDTO);
					break;
				case "smc" :
					list = monitoringTrafficCurMapper.findAllAvgSpeedSmartBySearchOptionPaging(mapMonitoringMenuDTO);
					totalCnt = monitoringTrafficCurMapper.countAvgSpeedSmartBySearchOption(mapMonitoringMenuDTO);
					break;
			}
    		break;
    	default:
    		break;
    	}
    	
    	Paging paging = new Paging();
    	if(totalCnt != 0) {
    		paging.setPageSize(5);
    		paging.setPageNo(mapMonitoringMenuDTO.getPage());
    		paging.setTotalCount(totalCnt);
    	}
    	
    	resultMap.put("list", list);
    	resultMap.put("paging", paging);
    	resultMap.put("totalCnt", totalCnt);
    	
    	return CommonResponse.successToData(resultMap,"");
    }
    
    /**
     * @Method Name : getMonitoringSignalPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 실시간 교통신호
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
     * @Method Name : getMonitoringWarningPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 돌발현황
     * @param model
     * @return
     */
    @GetMapping("/warning/{type}.ajax")
    public String getMonitoringWarningPage(@PathVariable String type, Map<String,Object> paramMap, Model model) {
		switch(type) {
			case "M_WARNING_001" :
				String baseYmd = BDDateFormatUtil.format(new Date(), "yyyy년 MM월 dd일 HH시mm분");
				List<GimsMngInciDetail> waringList = mWarningComponent.findAllForMapMarker();

				model.addAttribute("baseYmd", baseYmd);
				model.addAttribute("waringList",waringList);
				break;
			case "M_WARNING_002" :
				break;
		}
    	return "map/"+type;
    }

	/**
	 * 위험물 이동현황
	 * @param type
	 * @param paramMap
	 * @param model
	 * @return
	 */
	@GetMapping("/danger/{type}.ajax")
	public String getMonitoringDangerPage(@PathVariable String type, Map<String,Object> paramMap, Model model) {
		/*List<GimsMngInciDetail> waringList = gimsMngInciDetailMapper.findAllWarningListForList();
		model.addAttribute("waringList",waringList);*/
		return "map/"+type;
	}
    
    /**
     * @Method Name : getMonitoringWeatherPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 기상현황
     * @return
     */
    @GetMapping("/weather/{type}.ajax")
    public String getMonitoringWeatherPage(@PathVariable String type) {
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringEmergencyPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 긴급차량 이동현황
     * @param model
     * @return
     */
    @GetMapping("/emergency/{type}.ajax")
    public String getMonitoringEmergencyPage(@PathVariable String type, Map<String,Object> paramMap, Model model) {
    	
//    	String startToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR);
//    	String endToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR);
//    	paramMap.put("startToday", startToday);
//    	paramMap.put("endToday", endToday);
    	
    	List<ScsEmrgVhclPathInfo> emergencyList = scsEmrgVhclPathInfoMapper.findAllEmergencyListForToday();
    	
    	model.addAttribute("emergencyList",emergencyList);
    	
    	return "map/"+type;
    }
    
    @GetMapping("/emergency/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> getMonitoringEmergencyData(@PathVariable String type, Map<String,Object> paramMap, Model model) {
    	
//    	String startToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR);
//    	String endToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR);
//    	
//    	paramMap.put("startToday", startToday);
//    	paramMap.put("endToday", endToday);
    	
       	List<ScsEmrgVhclPathInfo> emergencyList = scsEmrgVhclPathInfoMapper.findAllEmergencyListForToday();
    	
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK,"",emergencyList);
    }
    
    
    /**
     * @Method Name : getMonitoringEmergencyDetail
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 긴급차량 이동현황 상세정보
     * @return
     */
    @GetMapping("/emergency/detail.ajax")
    public @ResponseBody CommonResponse<?> getMonitoringEmergencyDetail(@RequestParam(name = "serviceid",required = true) String serviceid) {
    	
    	ScsEmrgVhclPathInfo scsEmrgVhclPathInfo = scsEmrgVhclPathInfoMapper.findOneByServiceid(serviceid);
    	if(scsEmrgVhclPathInfo == null) {
    		throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"긴급차량 이동 정보가 존재하지 않습니다.");
    	}
    		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK,"",scsEmrgVhclPathInfo);
    }

    
    /**
     * @Method Name : getMonitoringBusPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 시내버스 이동현황
     * @param model
     * @return
     */
    @GetMapping("/bus/{type}.ajax")
    public String getMonitoringBusPage(@PathVariable String type, Model model, GgsplBusPeriodicinfo ggsplBusPeriodicinfo) {

		int totalCnt = 0;
		switch(type) {
			case "M_BUS_001" :
				List<GgsplBusPeriodicinfo>  list = mBusComponent.getRealtimeBusMoveInfo(ggsplBusPeriodicinfo);
				totalCnt = mBusComponent.getRealtimeBusMoveInfoTotalCnt(ggsplBusPeriodicinfo);
				model.addAttribute("list",list);
				break;
			case "M_BUS_002" :
				List<GgsplBusRouteVltnInfo> vllist = ggsplBusRouteVltnInfoMapper.findAllBySearchOption(ggsplBusPeriodicinfo);
				totalCnt = ggsplBusRouteVltnInfoMapper.countAllBySearchOption(ggsplBusPeriodicinfo);
				model.addAttribute("list",vllist);
				break;
		}
    	
    	Paging paging = new Paging();
		paging.setPageSize(5);
		paging.setPageNo(ggsplBusPeriodicinfo.getPage());
		paging.setTotalCount(totalCnt);

		List<MOpCode> sggCdList = mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD");

		model.addAttribute("sggCdList",sggCdList);
		model.addAttribute("paging", paging);

    	model.addAttribute("totalCnt",totalCnt);
    	
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringBusData
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 시내버스 이동현황
     * @param model
     * @return
     */
    @GetMapping("/bus/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> getMonitoringBusData(@PathVariable String type, Model model, GgsplBusPeriodicinfo ggsplBusPeriodicinfo) {
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	if(!GgitsCommonUtils.isNull(ggsplBusPeriodicinfo.getRouteTp())) {
    		ggsplBusPeriodicinfo.setRouteTpList(RouteTpCd.getRouteTpListFromRouteTpCd(ggsplBusPeriodicinfo.getRouteTp()));
    	}

		int totalCnt = 0;
		switch(type) {
			case "M_BUS_001" :
				List<GgsplBusPeriodicinfo>  list = mBusComponent.getRealtimeBusMoveInfo(ggsplBusPeriodicinfo);
				totalCnt = mBusComponent.getRealtimeBusMoveInfoTotalCnt(ggsplBusPeriodicinfo);
				resultMap.put("list", list);
				break;
			case "M_BUS_002" :
				List<GgsplBusRouteVltnInfo> vllist = ggsplBusRouteVltnInfoMapper.findAllBySearchOption(ggsplBusPeriodicinfo);
				totalCnt = ggsplBusRouteVltnInfoMapper.countAllBySearchOption(ggsplBusPeriodicinfo);
				resultMap.put("list", vllist);
				break;
		}
    	Paging paging = new Paging();
    	paging.setPageSize(5);
    	paging.setPageNo(ggsplBusPeriodicinfo.getPage());
    	paging.setTotalCount(totalCnt);

    	resultMap.put("paging", paging);
    	resultMap.put("totalCnt", totalCnt);
    	
    	return CommonResponse.successToData(resultMap,"");
    }
    
    /**
     * @Method Name : getMonitoringPopulationPage
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
     * @Method Name : getMonitoringLinkdataPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 연계대상 데이터 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/linkdata/{type}.ajax")
    public String getMonitoringLinkdataPage(@PathVariable String type,Model model) {
    	List<MapMonitoringLinkDataDTO> mapMonitoringLinkDataList = new ArrayList<MapMonitoringLinkDataDTO>();
    	switch (MapMonitoringSubMenuCd.getEnum(type)) {
    	case DATA_COLLECTION_STATUS:
    		mapMonitoringLinkDataList = lTcDataLogMapper.findAllForMonitoringLinkData();
    		break;
    	case DATA_COLLECTION_HISTORY: 
    		mapMonitoringLinkDataList = mapMonitoringService.getDataCollectHistory();
    		break;
    	case DATA_COLLECTION_FAILURE: 
    		mapMonitoringLinkDataList = lTcDataLogMapper.findAllErrorDataForMonitoringLinkData();
    		long errorTotalCnt = mapMonitoringLinkDataList.size();
    		if(errorTotalCnt > 0) {
    			mapMonitoringLinkDataList.get(0).setErrorTotalCnt(errorTotalCnt);
    		}
    		break;
    	default:
    		break;
    	}
    	
    	model.addAttribute("mapMonitoringLinkDataList",mapMonitoringLinkDataList);
    	
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringOperationPage
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 서비스 운영현황 
     * @param mapMonitoringMenuDTO
     * @param model
     * @return
     */
    @GetMapping("/operation/{type}.ajax")
    public String getMonitoringOperationPage(@PathVariable String type,Model model) {
    	String today = GgitsCommonUtils.getCalculationDateToString(0, "yyyy년 MM월 dd일", Calendar.HOUR);
    	switch (MapMonitoringSubMenuCd.getEnum(type)) {
    	case ALL_SERVICE_OPERATION_STATUS: 
    		model.addAttribute("mapTotalServiceInfoDTO",mapMonitoringService.getTotalServiceInfo());
    		break;
    	case USE_CASE_CONNECTION_STATUS: 
    		model.addAttribute("mapUseCaseInfoDTO", mapMonitoringService.getUseCaseInfo());
    		break;
    	case LOGIN_CONNECTION_STATUS: 
    		model.addAttribute("userLoginStats", mapMonitoringService.getUserLoginStats());
    		break;
    	default:
    		break;
    	}
    	model.addAttribute("today", today);
    	return "map/"+type;
    }
    
    /**
     * @Method Name : getMonitoringUsecasePage 
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
     * @Method Name : getMonitoringBigdataPage
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
