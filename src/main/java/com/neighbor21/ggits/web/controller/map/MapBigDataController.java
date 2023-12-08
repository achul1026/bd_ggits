package com.neighbor21.ggits.web.controller.map;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.api.module.bigdata.BDPatternComponent;
import com.neighbor21.ggits.api.module.bigdata.BDTrafficActiveEffectAnalysisComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdInfo;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.MrtDtgDangerSectn;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.enums.MapBigdataSubMenuCd;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdInfoMapper;
import com.neighbor21.ggits.common.mapper.GgdtdrBusSttnInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MrtBusRouteDetAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusRouteSectnAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusRungLogAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnPasngAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtDtgDangerSectnMapper;
import com.neighbor21.ggits.common.util.BDDateUtil;
import com.neighbor21.ggits.web.service.map.MapBigDataService;

/**
 * 맵 서브메뉴 컨텐츠 컨트롤러
 */
@Controller
@RequestMapping("/map/bigdata")
public class MapBigDataController {

	@Autowired
	BDPatternComponent bdPatternComponent;
	
    @Autowired
    BDTrafficActiveEffectAnalysisComponent bdTrafficActiveEffectAnalysisComponent;
    
    @Autowired
    MapBigDataService mapBigDataService;
    
	@Autowired
	AdsiSmcrsrdCrsrdInfoMapper adsiSmcrsrdCrsrdInfoMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	MrtDtgDangerSectnMapper mrtDtgDangerSectnMapper;
	
	@Autowired
	MrtBusRungLogAnalMapper mrtBusRungLogAnalMapper;
	
	@Autowired
	MrtBusSttnAnalMapper mrtBusSttnAnalMapper;
	
	@Autowired
	MrtBusRouteDetAnalMapper mrtBusRouteDetAnalMapper;
	
	@Autowired
	MrtBusRouteSectnAnalMapper mrtBusRouteSectnAnalMapper;
	
	@Autowired
	GgdtdrBusSttnInfoMapper ggdtdrBusSttnInfoMapper; 
	
	@Autowired
	MrtBusSttnPasngAnalMapper mrtBusSttnPasngAnalMapper;
	
    /**
     * 빅데이터 맵 교통패턴분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/pattern/{type}.ajax")
    public String getPattern(Model model , @PathVariable String type) {
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
			case TRAFFIC_PATTERN_ANALYSIS_VHCL_TRFVLM: // 교통패턴분석 > 교통량
				yearsList = bdPatternComponent.findAllDataYears(type);
				break;
			case TRAFFIC_PATTERN_ANALYSIS_AVG_SPPED: // 교통패턴분석 > 평균 속도
				yearsList = bdPatternComponent.findAllDataYears(type);
				break;
			case TRAFFIC_PATTERN_ANALYSIS_CONGESTION_SECTION: // 교통패턴분석 > 정체구간
				yearsList = bdPatternComponent.findAllDataYears(type);
				break;
			default:
				break;
    	}
    	//시군구 코드 목록
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
     	model.addAttribute("yearsList", yearsList);
        return "map/"+type;
    }
    
    /**
	 * @Method Name : crossroadsListAjax
	 * @작성일 : 2023. 8. 22.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 교차로 목록 비동기 호출
	 * @return
	 */
	@GetMapping(value ="/crossroads/list.ajax")
    public @ResponseBody CommonResponse<?> crossroadsListAjax(AdsiSmcrsrdCrsrdInfo adsiSmcrsrdCrsrdInfo){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
    	List<AdsiSmcrsrdCrsrdInfo> crossroadsList = adsiSmcrsrdCrsrdInfoMapper.findAllForMapList(adsiSmcrsrdCrsrdInfo);
    	int totalCnt = adsiSmcrsrdCrsrdInfoMapper.countAllForMapList(adsiSmcrsrdCrsrdInfo);
		
		Paging paging = new Paging();
		paging.setPageSize(5);
		paging.setPageNo(adsiSmcrsrdCrsrdInfo.getPage());
    	paging.setTotalCount(totalCnt);
    	resultMap.put("list", crossroadsList);
    	resultMap.put("paging", paging);
    	
		return CommonResponse.successToData(resultMap,"");
    }
	
    /**
     * 빅데이터 맵 교통 활동 효과 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/effect/analysis/{type}.ajax")
    public String getEffectAnalysis(Model model , @PathVariable String type) {
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
			case TRAFFIC_EFFECT_CONGESTION_SECTION: // 교통활동 효과분석 > 정체구간 개선효과
				yearsList = bdTrafficActiveEffectAnalysisComponent.findAllDataYears(type);
				break;
			case TRAFFIC_EFFECT_EMERGENCY_VEHICLE: // 교통활동 효과분석 > 긴급차량 우선 신호시스템 제어효과
				yearsList = bdTrafficActiveEffectAnalysisComponent.findAllDataYears(type);
				break;
			default:
				break;
    	}
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	return "map/"+type;
    }

    /**
     * 빅데이터 맵 교통 위험 구간 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/danger/zone/{type}.ajax")
    public String getDangerZone(Model model , @PathVariable String type) {
    	//TODO:: DB에서 호출 기능 추가 필요
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	return "map/"+type;
    }

    /**
     * 빅데이터 맵 예측 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/prediction/{type}.ajax")
    public String getPrediction(Model model , @PathVariable String type) {
    	//TODO:: DB에서 호출 기능 추가 필요
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	return "map/"+type;
    }

    /**
     * 빅데이터 맵 교통 신호 연동 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/link/{type}.ajax")
    public String getLink(Model model , @PathVariable String type) {
    	//TODO:: DB에서 호출 기능 추가 필요
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	return "map/"+type;
    }
    
    /**
     * 빅데이터 맵 대중 교통 이용 현황 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/tb/trans/{type}.ajax")
    public String getTbTrans(Model model , @PathVariable String type) {
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
		case PUBLIC_TRAFFIC_ANALYSIS_MORE_INFO:		// 더 많은 데이터 보기	
			break;
		case PUBLIC_TRAFFIC_START_END_USAGE:		// 기종점 대중교통 이용량
			yearsList = mrtBusRungLogAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_REGION_USAGE:			// 권역별 대중교통 이용현황
			yearsList = mrtBusSttnPasngAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_REGION_TRANSFER:		// 권역별 환승현황 분석
			yearsList = mrtBusSttnPasngAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_FACILITY_INFO:			// 정류장별 대중교통 및 노선 시설물
			yearsList = mrtBusSttnAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_BUS_USE_RATE:			// 정류장별 버스 이용률
			yearsList = mrtBusSttnAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_BUS_ROUTE_BIT:			// 정류장별 버스노선 및 BIT
			break;
		default:
			break;
		}
    	model.addAttribute("yearsList", yearsList);
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	return "map/"+type;
    }
    
    /**
	 * @Method Name : findFacilityAjax
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 빅데이터 맵 대중 교통 이용 현황 분석 비동기 호출
	 * @return
     * @throws ParseException 
	 */
    @GetMapping("/bus/trans/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> findTbTransAjax(@PathVariable String type, MapBigdataSearchDTO mapBigdataSearchDTO) throws ParseException{
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int totalCnt = 0;
    	
    	mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);
    	mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));
    	
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case PUBLIC_TRAFFIC_ANALYSIS_MORE_INFO:		// 더 많은 데이터 보기	
			break;
		case PUBLIC_TRAFFIC_START_END_USAGE:		// 기종점 대중교통 이용량
			totalCnt = mrtBusRungLogAnalMapper.countAllSttnPbTrfUseStatsAnal(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusRungLogAnalMapper.findAllSttnPbTrfUseStatsAnsl(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_REGION_USAGE:			// 권역별 대중교통 이용현황
			mapBigdataSearchDTO.setStartDate(mapBigdataSearchDTO.getStartDate().substring(0, 10));
			mapBigdataSearchDTO.setEndDate(mapBigdataSearchDTO.getEndDate().substring(0, 10));
			totalCnt = mrtBusSttnPasngAnalMapper.countAllRegionTransStats(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusSttnPasngAnalMapper.findAllRegionTransStats(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_REGION_TRANSFER:		// 권역별 환승현황 분석
			totalCnt = mrtBusSttnPasngAnalMapper.countAllRegionTransStats(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusSttnPasngAnalMapper.findAllRegionTransStats(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_FACILITY_INFO:			// 정류장별 대중교통 및 노선 시설물
			totalCnt = ggdtdrBusSttnInfoMapper.countAllBusSttnBusAndFclt(mapBigdataSearchDTO);
			resultMap.put("resultList", ggdtdrBusSttnInfoMapper.findAllBusSttnBusAndFclt(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_BUS_USE_RATE:			// 정류장별 버스 이용률
			totalCnt = mrtBusSttnAnalMapper.countAllBusUseStatAnal(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusSttnAnalMapper.findAllBusUseStatsAnal(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_BUS_ROUTE_BIT:			// 정류장별 버스노선 및 BIT
			break;
		default:
			break;
		}
    	
    	Paging paging = new Paging();
    	paging.setPageSize(5);
    	paging.setPageNo(mapBigdataSearchDTO.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	resultMap.put("paging", paging);
    	resultMap.put("searchOption", mapBigdataSearchDTO);
    	
    	return CommonResponse.successToData(resultMap, "");
    }

    /**
     * 빅데이터 맵 버스 노선 별 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/bus/road/{type}.ajax")
    public String getBusRoad(Model model , @PathVariable String type) {
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
		case PUBLIC_TRAFFIC_ROUTE_MORE_INFO:		// 더 많은 데이터 보기
			break;
		case PUBLIC_TRAFFIC_ROUTE_USER_CNT:			// 노선구간별 승하차/재차 승객수 조회
			break;
		case PUBLIC_TRAFFIC_ROUTE_RECIVE_CURVE:		// 노선구간별 수용성 및 굴곡도 조회
			yearsList = mrtBusRouteDetAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_ROUTE_DUPL_SEC_ADEQUACY: // 노선구간별 중복구간 도출 및 적정성 조회
			yearsList = mrtBusRouteSectnAnalMapper.findAllDataYears();
			break;
		default:
			break;
		}
    	model.addAttribute("yearsList", yearsList);
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	return "map/"+type;
    }
    
    /**
	 * @Method Name : findFacilityAjax
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 빅데이터 맵 버스 노선 별 분석 비동기 호출
	 * @return
     * @throws ParseException 
	 */
    @GetMapping("/bus/road/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> findBusRoadAjax(@PathVariable String type, MapBigdataSearchDTO mapBigdataSearchDTO) throws ParseException{
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int totalCnt = 0;
    	
    	mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);
    	mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));
    	
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
		case PUBLIC_TRAFFIC_ROUTE_MORE_INFO:			// 더 많은 데이터 보기
			break;
		case PUBLIC_TRAFFIC_ROUTE_USER_CNT:				// 노선구간별 승하차/재차 승객수 조회
			break;
		case PUBLIC_TRAFFIC_ROUTE_RECIVE_CURVE:			// 노선구간별 수용성 및 굴곡도 조회
			totalCnt = mrtBusRouteDetAnalMapper.countAllPubTrfRouteReciveCurveList(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusRouteDetAnalMapper.findAllPubTrfRouteReciveCurveList(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_ROUTE_DUPL_SEC_ADEQUACY: 	// 노선구간별 중복구간 도출 및 적정성 조회
			totalCnt = mrtBusRouteSectnAnalMapper.countAllPubTrfDuplSetAdequacyList(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusRouteSectnAnalMapper.findAllPubTrfDuplSetAdequacyList(mapBigdataSearchDTO));
			break;
		default:
			break;
		}
    	
    	Paging paging = new Paging();
    	paging.setPageSize(5);
    	paging.setPageNo(mapBigdataSearchDTO.getPage());
    	paging.setTotalCount(totalCnt);
    	
    	resultMap.put("paging", paging);
    	resultMap.put("searchOption", mapBigdataSearchDTO);
    	return CommonResponse.successToData(resultMap, "");
    }
    
    
    /**
     * 빅데이터 맵 버스 위험운영 구간 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/bus/danger/{type}.ajax")
    public String getBusDanger(Model model , @PathVariable String type) {
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	
    	yearsList = mrtDtgDangerSectnMapper.findAllDataYears();
    	
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	
    	return "map/"+type;
    }
    
    /**
	 * @Method Name : findFacilityAjax
	 * @작성일 : 2023. 10. 25.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 빅데이터 맵 버스 위험운영 구간 분석 비동기 호출
	 * @return
     * @throws ParseException 
	 */
    @GetMapping(value="/bus/danger/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> findBusDangerAjax(@PathVariable String type, MapBigdataSearchDTO mapBigdataSearchDTO) throws ParseException{
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int totalCnt = 0;
    	List<MrtDtgDangerSectn> resultList = new ArrayList<MrtDtgDangerSectn>();
    	
    	mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);
		mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));
		
    	totalCnt = mrtDtgDangerSectnMapper.countAllPubTrfSafeDrvAnal(mapBigdataSearchDTO);
    	resultList = mrtDtgDangerSectnMapper.findAllPubTrfSafeDrvAnal(mapBigdataSearchDTO);
    	
    	Paging paging = new Paging();
    	paging.setPageSize(5);
    	paging.setPageNo(mapBigdataSearchDTO.getPage());
    	paging.setTotalCount(totalCnt);
    	resultMap.put("paging", paging);
    	resultMap.put("resultList", resultList);
    	resultMap.put("searchOption", mapBigdataSearchDTO);
    	
    	return CommonResponse.successToData(resultMap, "");
    }
    
    /**
     * 빅데이터 맵 버스 위험운영 구간 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/pb/prediction/{type}.ajax")
    public String getPdPrediction(Model model , @PathVariable String type) {
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));

    	return "map/"+type;
    }

}
