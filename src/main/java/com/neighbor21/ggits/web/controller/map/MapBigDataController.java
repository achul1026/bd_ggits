package com.neighbor21.ggits.web.controller.map;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.enums.RouteTpCd;
import com.neighbor21.ggits.common.hcisql.mapper.HciTsLogDriveanalMapper;
import com.neighbor21.ggits.common.mapper.*;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.api.module.bigdata.BDPatternComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPredictionComponent;
import com.neighbor21.ggits.api.module.bigdata.BDTrafficActiveEffectAnalysisComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.dto.MapChartDataDTO;
import com.neighbor21.ggits.common.enums.MapBigdataSubMenuCd;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
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
	MrtSmcSpotAbnMapper mrtSmcSpotAbnMapper;
	
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
	
	@Autowired
	ExtGgbisBusrouteStationMapper extGgbisBusrouteStationMapper;
	
	@Autowired
	GgbisBusRouteMapper ggbisBusRoutemapper;
	
	@Autowired
	MrtBusRoutePasngAnalMapper mrtBusRoutePasngAnalMapper;
	
	@Autowired
	GgbisBusrouteInfounitMapper ggbisBusrouteInfounitMapper;
	
	@Autowired
	TaasAcdntDstrctMasterMapper taasAcdntDstrctMasterMapper; 
	
	@Autowired
	MrtSmcrsrdTrfvlmAnalMapper mrtSmcrsrdTrfvlmAnalMapper;
	
	@Autowired
	GgbisBusStationMapper ggbisBusStationMapper;
	
	@Autowired
	MrtTrfAcdntDngrPrdctnMapper mrtTrfAcdntDngrPrdctnMapper;
	
	@Autowired
	MrtBusRoutePrdctnAnlsMapper mrtBusRoutePrdctnAnlsMapper;
	
	@Autowired
	MrtDynmcPopltnCell500RsltMapper mrtDynmcPopltnCell500RsltMapper;
	
	@Autowired
	MrtBusArvlTimePrdctnRsltMapper mrtBusArvlTimePrdctnRsltMapper;
	
	@Autowired
	BDPredictionComponent bdPredictionComponent;

	@Autowired
	GbmsBusUseCalcInfoMapper gbmsBusUseCalcInfoMapper;
	
	@Autowired
	UticRoadDngrSttsFrcstMapper uticRoadDngrSttsFrcstMapper;

	@Autowired
	MrtTrfHlctcCngstnSctnMapper mrtTrfHlctcCngstnSctnMapper;

	@Autowired
	MrtSmcTrfPatMapper mrtSmcTrfPatMapper;

	@Autowired
	TsLogDriveanalMapper tsLogDriveanalMapper;

	@Autowired
	HciTsLogDriveanalMapper hciTsLogDriveanalMapper;

	@Autowired
	MrtSigCrsdTrfAnalMapper mrtSigCrsdTrfAnalMapper;
	
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
			case TRAFFIC_PATTERN_ANALYSIS_CONGESTION_SECTION: // 교통패턴분석 > 상습정체구간
				yearsList = mrtTrfHlctcCngstnSctnMapper.findAllDataYears();
				break;
			default:
				break;
    	}
    	//시군구 코드 목록
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
     	model.addAttribute("yearsList", yearsList);
     	model.addAttribute("type", type);
        return "map/"+type;
    }
    
    
    /**
	 * @Method Name : loadTrafficPattrnData
	 * @작성일 : 2023. 10. 31.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 빅데이터 분석 -> 교통패턴분석 -> 더많은 데이터 보기 -> 상위 메뉴 클릭시
	 * @return
	 */
    @PostMapping("/loadTrafficPattrnData.ajax")
    public @ResponseBody CommonResponse<?> loadTrafficPattrnData(@RequestParam Map<String,Object> paramMap){
    	MapChartDataDTO data = mapBigDataService.getTrafficPttrnInfoData(paramMap);
    	
    	if(data == null) {
    		return CommonResponse.ResponseSuccess(HttpStatus.OK, "조회된 차트데이터가 없습니다.", null, null);
    	}
    	
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "차트 데이터 조회 성공", null, data);
    }

    /**
     * @Method Name : loadTrafficPattrnData
     * @작성일 : 2023. 10.31.
     * @작성자 : KY.LEE
     * @Method 설명 : 빅데이터 분석 -> 교통위험 구간 분석 -> 더많은 데이터 보기
     * @return
     */
    @PostMapping("/loadDangerZoneData.ajax")
    public @ResponseBody CommonResponse<?> loadDangerZoneData(@RequestParam Map<String,Object> paramMap){
    	MapChartDataDTO data = mapBigDataService.getDangerZoneInfo(paramMap);
    	if(data == null) {
    		return CommonResponse.ResponseSuccess(HttpStatus.OK, "차트 데이터 없음", null, null);
    	}
    	return CommonResponse.ResponseSuccess(HttpStatus.OK, "차트 데이터 조회 성공", null, data);
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
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("type", type);
    	return "map/"+type;
    }

	@GetMapping("/effect/analysis/{type}/data.ajax")
	public @ResponseBody CommonResponse<?> getEffectAnalysisData(MapBigdataSearchDTO mapBigdataSearchDTO , @PathVariable String type) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Integer totalCnt = 0;
		switch (MapBigdataSubMenuCd.getEnum(type)) {
			case TRAFFIC_EFFECT_CONGESTION_SECTION: // 교통활동 효과분석 > 정체구간 개선효과
				resultMap.put("resultList",mrtSmcTrfPatMapper.findAllByLinkIdOrRoadNm(mapBigdataSearchDTO));
				totalCnt = mrtSmcTrfPatMapper.countByLinkIdOrRoadNm(mapBigdataSearchDTO);
				break;
			case TRAFFIC_EFFECT_CONGESTION_SECTION_SVC :
				List<MrtSigCrsdTrfAnal> resultList = mrtSigCrsdTrfAnalMapper.findAllBySvcLinkIdOrRoadNm(mapBigdataSearchDTO);
				if(!resultList.isEmpty()){
					totalCnt = resultList.get(0).getPagingTotalCount().intValue();
				}
				resultMap.put("resultList",resultList);
				break;
			default :
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
     * 빅데이터 맵 교통 위험 구간 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/danger/zone/{type}.ajax")
    public String getDangerZone(Model model , @PathVariable String type) {
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case TRAFFIC_DANGER_SEACTION_MORE_INFO:
    		break;
    	case TRAFFIC_DANGER_ROAD_SAFTY_INFO:
    		yearsList = uticRoadDngrSttsFrcstMapper.findAllDataYears();
    		break;
    	case TRAFFIC_DANGER_ACDNT_RESION_INFO:
    		yearsList = taasAcdntDstrctMasterMapper.findAllDataYears();
    		break;
		default:
			break;
    	}
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	model.addAttribute("type", type);
    	return "map/"+type;
    }

    /**
     * 빅데이터 맵 예측 분석 viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/prediction/{type}.ajax")
    public String getPrediction(Model model , @PathVariable String type) {
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case TRAFFIC_PREDICTION_MORE_INFO:		// 더 많은 데이터 보기
    		// 교차로 교통량 순위
			String today = BDDateFormatUtil.isNowStr("yyyy-MM-dd");
    		Map<String, Object> smcrsrdTrfvlmRank = mrtSmcrsrdTrfvlmAnalMapper.findAllsmcrsrdTrfvlmRank(today);
    		model.addAttribute("crsrdNmArr", smcrsrdTrfvlmRank.get("crsrdNmArr"));
    		model.addAttribute("trfvlmTotalArr",smcrsrdTrfvlmRank.get("trfvlmTotalArr"));
    		
    		//사고예측 지역 순위 DEFAULT 그래프
    		Map<String,Object> searchMap = new HashMap<String, Object>();
    		searchMap.put("searchType", "graph");
    		searchMap.put("searchStandard", "sgg");
    		List<Map<String, Object>> acdntPredictionData = mrtTrfAcdntDngrPrdctnMapper.findAllBySearchOptionForDataView(searchMap);
    		model.addAttribute("acdntPredictionData",acdntPredictionData.get(0));
    		
    		break;
    	case TRAFFIC_PREDICTION_CROSSROAD_INFO:	// 교차로 교통량 예측
    		yearsList = mrtSmcrsrdTrfvlmAnalMapper.findAllDataYears();
    		break;
    	case TRAFFIC_PREDICTION_ACCDNT_INFO:	// 사고 예측 구간
    		/*yearsList = mrtTrfAcdntDngrPrdctnMapper.findAllDataYears();*/
    		break;
		default:
			break;
    	}
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	model.addAttribute("type", type);
    	return "map/"+type;
    }
    
    @GetMapping("/prediction/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> findPredictionAjax(	@PathVariable String type, 
    															@RequestParam(value = "searchTime", required = false) String searchTime,
    															@RequestParam(value = "subType", required = false) String subType,
    															@RequestParam(value = "searchType", required = false) String searchType,
    															@RequestParam(value = "searchStandard", required = false) String searchStandard
    															
    		) throws ParseException{
    	Map<String, Object> resultMap = new HashMap<String, Object>();

    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case TRAFFIC_PREDICTION_MORE_INFO:		// 더 많은 데이터 보기
    		if("acdnt".equals(subType)) {
    			Map<String,Object> searchMap = new HashMap<String, Object>();
    			searchMap.put("searchType", searchType);
    			searchMap.put("searchStandard", searchStandard);
    			List<Map<String, Object>> acdntPredictionData = mrtTrfAcdntDngrPrdctnMapper.findAllBySearchOptionForDataView(searchMap);
    			if("table".equals(searchType)) {
    				resultMap.put("acdntPredictionData", acdntPredictionData);
    			}else {
    				resultMap.put("acdntPredictionData", acdntPredictionData.get(0));
    				
    			}
    		}else {
    			Map<String, Object> smcrsrdTrfvlmRank = mrtSmcrsrdTrfvlmAnalMapper.findAllsmcrsrdTrfvlmRank(searchTime);
    			resultMap.put("crsrdNmArr", smcrsrdTrfvlmRank.get("crsrdNmArr"));
    			resultMap.put("trfvlmTotalArr", smcrsrdTrfvlmRank.get("trfvlmTotalArr"));
    		}
    		break;
		default:
			break;
    	}
    	
//    	resultMap.put("searchOption", mapBigdataSearchDTO);
    	
    	return CommonResponse.successToData(resultMap, "");
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
    	model.addAttribute("type", type);
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
			
			MapBigdataSearchDTO mapBigdataSearchDTO = new MapBigdataSearchDTO();
			mapBigdataSearchDTO.setSearchTime(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
			mapBigdataSearchDTO.setRouteTp("13");
			
			Map<String, Object> busUseUserRankMap = ggbisBusRoutemapper.findAllBusUserRankList(mapBigdataSearchDTO);
    		model.addAttribute("busUseRouteIdArr", busUseUserRankMap.get("busRouteIdArr"));
    		model.addAttribute("busUserCntArr", busUseUserRankMap.get("busUserCntArr"));
    		
    		Map<String, Object> busTrsfrUserRankMap = ggbisBusRoutemapper.findAllBusTrnsitUserRankList(mapBigdataSearchDTO);
    		model.addAttribute("busTrsfrRouteIdArr", busTrsfrUserRankMap.get("busRouteIdArr"));
    		model.addAttribute("busTrsfrUserCntArr", busTrsfrUserRankMap.get("busTrsfrUserCntArr"));
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
			yearsList = mrtBusArvlTimePrdctnRsltMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_BUS_ROUTE_USE_CALC :	// 노선별 교통 카드 이용 현황;
			yearsList = gbmsBusUseCalcInfoMapper.findAllDataYears();
			break;
		default:
			break;
		}
    	model.addAttribute("yearsList", yearsList);
    	model.addAttribute("type", type);
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
    	
    	Integer totalCnt = 0;
    	
    	mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);
    	mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));
    	
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case PUBLIC_TRAFFIC_ANALYSIS_MORE_INFO:		// 더 많은 데이터 보기
    		if(mapBigdataSearchDTO.getSearchResultType().equals("1")) {
    			Map<String, Object> busUseUserRankMap = ggbisBusRoutemapper.findAllBusUserRankList(mapBigdataSearchDTO);
    			resultMap.put("busUseRouteIdArr", busUseUserRankMap.get("busRouteIdArr"));
        		resultMap.put("busUserCntArr", busUseUserRankMap.get("busUserCntArr"));
    		}
			break;
		case PUBLIC_TRAFFIC_START_END_USAGE:		// 기종점 대중교통 이용량
			/*totalCnt = extGgbisBusrouteStationMapper.countAllSttnPbTrfUseStatsAnal(mapBigdataSearchDTO);
			resultMap.put("resultList", extGgbisBusrouteStationMapper.findAllSttnPbTrfUseStatsAnsl(mapBigdataSearchDTO));*/
			totalCnt = mrtBusRungLogAnalMapper.countByRouteNmPaging(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusRungLogAnalMapper.findListByRouteNmPaging(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_REGION_USAGE:			// 권역별 대중교통 이용현황
			totalCnt = ggbisBusRoutemapper.countPubTrfRouteInfo(mapBigdataSearchDTO);
			resultMap.put("resultList", ggbisBusRoutemapper.findAllPubTrfRouteInfoList(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_REGION_TRANSFER:		// 권역별 환승현황 분석(삭제)
			break;
		case PUBLIC_TRAFFIC_FACILITY_INFO:			// 정류장별 대중교통 및 노선 시설물
			totalCnt = ggbisBusStationMapper.countAllBusSttnFcltInfo(mapBigdataSearchDTO);
			resultMap.put("resultList", ggbisBusStationMapper.findAllBusSttnFcltInfo(mapBigdataSearchDTO));	
			break;
		case PUBLIC_TRAFFIC_BUS_USE_RATE:			// 정류장별 버스 이용률
			totalCnt = ggbisBusStationMapper.countAllBusSttnInfo(mapBigdataSearchDTO);
			resultMap.put("resultList", ggbisBusStationMapper.findAllBusSttnInfo(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_BUS_ROUTE_BIT:			// 정류장별 버스노선 및 BIT
			totalCnt = ggbisBusStationMapper.countAllBusSttnRouteInfo(mapBigdataSearchDTO);
			resultMap.put("resultList", ggbisBusStationMapper.findAllBusSttnRouteInfo(mapBigdataSearchDTO));	
			break;
		case PUBLIC_TRAFFIC_BUS_ROUTE_USE_CALC:
			if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getRouteTp())) {
				mapBigdataSearchDTO.setRouteTpList(RouteTpCd.getRouteTpListFromRouteTpCd(mapBigdataSearchDTO.getRouteTp()));
			}
			totalCnt = gbmsBusUseCalcInfoMapper.countAllBySearchOption(mapBigdataSearchDTO);
			resultMap.put("resultList", gbmsBusUseCalcInfoMapper.findAllBySearchOption(mapBigdataSearchDTO));
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
			MapBigdataSearchDTO mapBigdataSearchDTO = new MapBigdataSearchDTO();
			mapBigdataSearchDTO.setSearchTime(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
			
			Map<String, Object> routeUserMap = mrtBusRoutePasngAnalMapper.findAllRouteUserRankList(mapBigdataSearchDTO);
			model.addAttribute("sttnInfoArr", routeUserMap.get("sttnInfoArr"));
			model.addAttribute("userCntArr", routeUserMap.get("userCntArr"));
			
			Map<String, Object> busReciveCurveMap = mrtBusRouteDetAnalMapper.findAllBusReciveCurveRankList();
    		model.addAttribute("busUseRouteIdArr", busReciveCurveMap.get("busRouteIdArr"));
    		model.addAttribute("busUserCntArr", busReciveCurveMap.get("busCurveArr"));
			break;
		case PUBLIC_TRAFFIC_ROUTE_USER_CNT:			// 노선구간별 승하차/재차 승객수 조회
			yearsList = mrtBusRoutePasngAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_ROUTE_RECIVE_CURVE:		// 노선구간별 수용성 및 굴곡도 조회
//			yearsList = mrtBusRouteDetAnalMapper.findAllDataYears();
			break;
		case PUBLIC_TRAFFIC_ROUTE_DUPL_SEC_ADEQUACY: // 노선구간별 중복구간 도출 및 적정성 조회
//			yearsList = mrtBusRouteSectnAnalMapper.findAllDataYears();
			break;
		default:
			break;
		}
    	model.addAttribute("yearsList", yearsList);
    	model.addAttribute("type", type);
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
    	
    	Integer totalCnt = 0;
    	

    	
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
		case PUBLIC_TRAFFIC_ROUTE_MORE_INFO:			// 더 많은 데이터 보기
			mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);
			mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));
			Map<String, Object> routeUserMap = mrtBusRoutePasngAnalMapper.findAllRouteUserRankList(mapBigdataSearchDTO);
			resultMap.put("sttnInfoArr", routeUserMap.get("sttnInfoArr"));
			resultMap.put("userCntArr", routeUserMap.get("userCntArr"));
			break;
		case PUBLIC_TRAFFIC_ROUTE_USER_CNT:				// 노선구간별 승하차/재차 승객수 조회
			if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getRouteTp())) {
				mapBigdataSearchDTO.setRouteTpList(RouteTpCd.getRouteTpListFromRouteTpCd(mapBigdataSearchDTO.getRouteTp()));
			}
//			totalCnt = mrtBusRoutePasngAnalMapper.countPubTrfRouteUserCnt(mapBigdataSearchDTO);
			List<MrtBusRoutePasngAnal> resultList = mrtBusRoutePasngAnalMapper.findAllPubTrfRouteUserCnt(mapBigdataSearchDTO);
			if(!resultList.isEmpty()) {
				totalCnt = Math.toIntExact(resultList.get(0).getPagingTotalCount());
			}
			resultMap.put("resultList", resultList);
			break;
		case PUBLIC_TRAFFIC_ROUTE_RECIVE_CURVE:			// 노선구간별 수용성 및 굴곡도 조회
			mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);
			mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));
			totalCnt = mrtBusRouteDetAnalMapper.countAllPubTrfRouteReciveCurveList(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusRouteDetAnalMapper.findAllPubTrfRouteReciveCurveList(mapBigdataSearchDTO));
			break;
		case PUBLIC_TRAFFIC_ROUTE_DUPL_SEC_ADEQUACY: 	// 노선구간별 중복구간 도출 및 적정성 조회
			mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);
			mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));
			if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getRouteTp())) {
				mapBigdataSearchDTO.setRouteTpList(RouteTpCd.getRouteTpListFromRouteTpCd(mapBigdataSearchDTO.getRouteTp()));
			}
			totalCnt = mrtBusRouteSectnAnalMapper.countDuplicateSectionInfo(mapBigdataSearchDTO);
			resultMap.put("resultList", mrtBusRouteSectnAnalMapper.findAllDuplicateSectionInfo(mapBigdataSearchDTO));
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

		MapBigdataSearchDTO mapBigdataSearchDTO = new MapBigdataSearchDTO();
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case PUBLIC_TRAFFIC_DANGER_SECTION_MORE_INFO:

    		mapBigdataSearchDTO.setSearchResultType("city");
    		
    		Map<String, Object> pubTrfDagrfrecRankMap = mrtDtgDangerSectnMapper.findAllPubTrfDagrFrecRank(mapBigdataSearchDTO);
    		model.addAttribute("cityInfoArr", pubTrfDagrfrecRankMap.get("cityInfoArr"));
    		model.addAttribute("danrFrecArr", pubTrfDagrfrecRankMap.get("danrFrecArr"));
    		
    		List<Map<String, Object>> pubTrfDagrfrecRankList = mrtDtgDangerSectnMapper.findAllPubTrfDagrFrecRankList(mapBigdataSearchDTO);
    		model.addAttribute("pubTrfDagrfrecRankList", pubTrfDagrfrecRankList);
    		break;
    	case PUBLIC_TRAFFIC_DANGER_SECTION:
//    		yearsList = mrtDtgDangerSectnMapper.findAllDataYears();
			List<Map<String, String>> companyList = tsLogDriveanalMapper.findAllGgbisCompany();
			/*mapBigdataSearchDTO = new MapBigdataSearchDTO();
			List<GgbisVehicle> pubTrfRouteInfoList = hciTsLogDriveanalMapper.findAllBusVehicleByPlateNo(mapBigdataSearchDTO);
			int totalCnt = 0;
			if(!pubTrfRouteInfoList.isEmpty()){
				totalCnt = pubTrfRouteInfoList.get(0).getPagingTotalCount().intValue();
			}
			Paging paging = new Paging();
			paging.setPageSize(5);
			paging.setPageNo(mapBigdataSearchDTO.getPage());
			paging.setTotalCount(totalCnt);
			model.addAttribute("paging", paging);
			model.addAttribute("resultList", pubTrfRouteInfoList);*/
			model.addAttribute("companyList", companyList);
    		break;
    	default:
			break;
    	}
    	
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	model.addAttribute("yearsList", yearsList);
    	model.addAttribute("type", type);
    	
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
    	List<GgbisVehicle> pubTrfRouteInfoList = new ArrayList<GgbisVehicle>();
    	
    	/*mapBigdataSearchDTO = mapBigDataService.setSearchDateInfo(mapBigdataSearchDTO);*/
		/*mapBigdataSearchDTO.setDayOfTheWeek(BDDateUtil.findWeekdaysAndWeekend(mapBigdataSearchDTO.getSearchPeriod()));*/
		
		switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case PUBLIC_TRAFFIC_DANGER_SECTION_MORE_INFO:
			Map<String, Object> pubTrfDagrfrecRankMap = mrtDtgDangerSectnMapper.findAllPubTrfDagrFrecRank(mapBigdataSearchDTO);
			resultMap.put("cityInfoArr", pubTrfDagrfrecRankMap.get("cityInfoArr"));
			resultMap.put("danrFrecArr", pubTrfDagrfrecRankMap.get("danrFrecArr"));
			
			List<Map<String, Object>> pubTrfDagrfrecRankList = mrtDtgDangerSectnMapper.findAllPubTrfDagrFrecRankList(mapBigdataSearchDTO);
			resultMap.put("pubTrfDagrfrecRankList", pubTrfDagrfrecRankList);
		
    		break;
    	case PUBLIC_TRAFFIC_DANGER_SECTION:
			pubTrfRouteInfoList =  hciTsLogDriveanalMapper.findAllBusVehicleByPlateNo(mapBigdataSearchDTO);
			if(!pubTrfRouteInfoList.isEmpty()){
				totalCnt = pubTrfRouteInfoList.get(0).getPagingTotalCount().intValue();
			}
    		break;
    	default:
			break;
    	}
		
    	Paging paging = new Paging();
    	paging.setPageSize(5);
    	paging.setPageNo(mapBigdataSearchDTO.getPage());
    	paging.setTotalCount(totalCnt);
    	resultMap.put("paging", paging);
    	resultMap.put("resultList", pubTrfRouteInfoList);
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
    	List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
    	model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case PUBLIC_TRAFFIC_PREDICTION_MORE_INFO:	// 더 많은 데이터 보기
    		// 유동인구 분류    				
    		Map<String, Object> flatPopltnRank = mrtDynmcPopltnCell500RsltMapper.findAllFlatPopltnRank();
    		model.addAttribute("flatPopltnLabelArr", flatPopltnRank.get("flatPopltnLabelArr"));
    		model.addAttribute("flatPopltnDataArr", flatPopltnRank.get("flatPopltnDataArr"));
    		
    		// 버스노선 최적화 전후 비교(평가점수)
			Map<String, Object> busRoutePrdctnRank = mrtBusRoutePrdctnAnlsMapper.findAllBusRouteScoreRank();
			model.addAttribute("routeNmArr", busRoutePrdctnRank.get("routeNmArr"));
    		model.addAttribute("scoreArr", busRoutePrdctnRank.get("scoreArr"));
    		break;
    	case PUBLIC_TRAFFIC_DISASTER_PREDICTION:	// 유동인구 밀집 예측 분석
    		break;
    	case BUS_ROUTE_OPTIMIZATION_PREDICTION: 	// 버스노선 최적화 예측 분석
    		break;
		default:
			break;
    	}
    	model.addAttribute("type", type);
    	return "map/"+type;
    }
    
    
    @GetMapping(value="/pb/prediction/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> findPdPredictionAjax(@PathVariable String type, MapBigdataSearchDTO mapBigdataSearchDTO) throws ParseException{
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	int totalCnt = 0;
    	
    	switch (MapBigdataSubMenuCd.getEnum(type)) {
    	case PUBLIC_TRAFFIC_PREDICTION_MORE_INFO:	// 더 많은 데이터 보기
    			Map<String, Object> busRoutePrdctnRank = mrtBusRoutePrdctnAnlsMapper.findAllBusRouteScoreRank();
    			resultMap.put("routeNmArr", busRoutePrdctnRank.get("routeNmArr"));
    			resultMap.put("scoreArr", busRoutePrdctnRank.get("scoreArr"));
    		break;
    	case BUS_ROUTE_OPTIMIZATION_PREDICTION:		// 버스노선 최적화 예측 분석
    		totalCnt = mrtBusRoutePrdctnAnlsMapper.countAllBusRouteOptPrdt(mapBigdataSearchDTO);
    		List<MrtBusRoutePrdctnAnls> busRouteOptPrdt = mrtBusRoutePrdctnAnlsMapper.findAllBusRouteOptPrdt(mapBigdataSearchDTO);
    		
    		resultMap.put("resultList", busRouteOptPrdt);
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
    
    
    @PostMapping(value = "/ajax/autocomplete.ajax")
	public @ResponseBody Map<String, Object> autocomplete(@RequestParam Map<String, Object> paramMap) throws Exception{
    	List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
    	resultList = extGgbisBusrouteStationMapper.findAllStationNmListBySearchContent(paramMap);
		paramMap.put("resultList", resultList);

		return paramMap;
	}
}
