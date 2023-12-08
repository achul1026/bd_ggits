package com.neighbor21.ggits.web.controller.statistics;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadStatsOnhr;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdDrctStatsOnhr;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.entity.MrtAccntLog;
import com.neighbor21.ggits.common.entity.MrtBusRoutePasngAnal;
import com.neighbor21.ggits.common.entity.MrtBusSttnAnal;
import com.neighbor21.ggits.common.entity.MrtBusSttnFctlAnal;
import com.neighbor21.ggits.common.entity.MrtBusSttnPasngAnal;
import com.neighbor21.ggits.common.entity.MrtBusTotMoveInfo;
import com.neighbor21.ggits.common.entity.MrtDtgDangerSectn;
import com.neighbor21.ggits.common.entity.MrtRoadAccntAnal;
import com.neighbor21.ggits.common.entity.MrtSmcAbnLos;
import com.neighbor21.ggits.common.entity.MrtSmcSpotAbn;
import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1m;
import com.neighbor21.ggits.common.entity.MrtTrfFcltsSttsAnls;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclLogInfo;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdDrctStatsOnhrMapper;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MrtAccntLogMapper;
import com.neighbor21.ggits.common.mapper.MrtBusRoutePasngAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnFctlAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnPasngAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtBusTotMoveInfoMapper;
import com.neighbor21.ggits.common.mapper.MrtDtgDangerSectnMapper;
import com.neighbor21.ggits.common.mapper.MrtRoadAccntAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcAbnLosMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcSpotAbnMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfFcltsSttsAnlsMapper;
import com.neighbor21.ggits.common.mapper.ScsEmrgVhclLogInfoMapper;
import com.neighbor21.ggits.common.mapper.ExtGgitsLinkStd1mMapper;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.web.service.statistics.TrafficInfoStatisticsAnalysisService;

@Controller
@RequestMapping("/statistics")
public class TrafficInfoStatisticsAnalysisController {
	
	@Autowired
	TrafficInfoStatisticsAnalysisService trafficInfoStatisticsAnalysisService;
	
	@Autowired
	AdsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper;
	
	@Autowired
	GimsMngInciDetailMapper gimsMngInciDetailMapper;
	
	@Autowired
	MrtDtgDangerSectnMapper mrtDtgDangerSectnMapper;
	
	@Autowired
	MrtSmcAbnLosMapper mrtSmcAbnLosMapper;
	
	@Autowired
	MrtSmcSpotAbnMapper mrtSmcSpotAbnMapper; 
	
	@Autowired
	ScsEmrgVhclLogInfoMapper scsEmrgVhclLogInfoMapper;
	
	@Autowired
	AdsiSmcrsrdCrsrdDrctStatsOnhrMapper adsiSmcrsrdCrsrdDrctStatsOnhrMapper;
	
	@Autowired
	MrtAccntLogMapper mrtAccntLogMapper;
	
	@Autowired
	MrtRoadAccntAnalMapper mrtRoadAccntAnalMapper;
	
	@Autowired
	MrtTrfFcltsSttsAnlsMapper mrtTrfFcltsSttsAnlsMapper;
	
	@Autowired
	MrtBusSttnPasngAnalMapper mrtBusSttnPasngAnalMapper;
	
	@Autowired
	MrtBusSttnFctlAnalMapper mrtBusSttnFctlAnalMapper;
	
	@Autowired
	MrtBusRoutePasngAnalMapper mrtBusRoutePasngAnalMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	MrtBusTotMoveInfoMapper mrtBusTotMoveInfoMapper;
	
	@Autowired
	MrtBusSttnAnalMapper mrtBusSttnAnalMapper;
	
	@Autowired
	ExtGgitsLinkStd1mMapper extGgitsLinkStd1mMapper;

	/**
     * @Method Name : viewtraffic_info_statsReport
     * @작성일 : 2023. 9. 7.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 교통 지표 총괄 통계
     * @return
	 * @throws ParseException 
     */
	@GetMapping("/traffic/analysis/{type}/list.do")
    public String viewtraffic_info_statsReport(Model model, CommonEntity commonEntity, @PathVariable String type) throws ParseException{
		int totalCnt = 0;
		
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(commonEntity.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getStrDt(), "startDateHour");
			if(!GgitsCommonUtils.isNull(commonEntity.getStartTime())) {
				int startTime = Integer.parseInt(commonEntity.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday, startTime, "yyyy-MM-dd HH", Calendar.HOUR);
			}
		}
		if(!GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getEndDt(), "endDateHour");			
			if(!GgitsCommonUtils.isNull(commonEntity.getEndTime())) {
				int endTime = Integer.parseInt(commonEntity.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday, endTime, "yyyy-MM-dd HH", Calendar.HOUR);
			}
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "traffic_info_stats":
			// 교통현황 통계
			totalCnt = extGgitsLinkStd1mMapper.countTrafficInfoStats(commonEntity);
			List<ExtGgitsLinkStd1m> trafficInfoStatsList = extGgitsLinkStd1mMapper.findAllTrafficInfoStats(commonEntity);
			break;
		case "unept_sitn_stats":
			// 돌발현황 통계
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("startToday", startToday);
			paramMap.put("endToday", endToday);
			paramMap.put("dayOfTheWeek", commonEntity.getDayOfTheWeek());
			
			totalCnt = gimsMngInciDetailMapper.countGimsMngInciDetailList(paramMap);
			
			paramMap.put("page", commonEntity.getPage());
			List<GimsMngInciDetail> gimsDetailList = gimsMngInciDetailMapper.findAllWarningList(paramMap);
			model.addAttribute("statsList", gimsDetailList);
	    	
			// 통계 데이터 조회
			// 	돌발 상황 비율 
			Map<String, Object> uneptSitnRate = gimsMngInciDetailMapper.findOneGimsStatsRateInfo(paramMap);			
			model.addAttribute("uneptSitnRateLabelArray", uneptSitnRate.get("uneptSitnRateLabelArray"));
			model.addAttribute("uneptSitnRateDataArray", uneptSitnRate.get("uneptSitnRateDataArray"));
			
			//  시간대별 돌발 상황 발생 건수
			List<String> inciCateList = new ArrayList<String>();
			paramMap.put("todayStartDt", GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00", Calendar.HOUR));
			paramMap.put("todayEndDt", GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23", Calendar.HOUR));
			inciCateList = gimsMngInciDetailMapper.findOneInciCateList();
			for(String inciCate : inciCateList) {
				paramMap.put("inciCate", inciCate);
				
				Map<String, Object> cntArrayMap = gimsMngInciDetailMapper.findOneGimsStatsCntInfo(paramMap);
				
				model.addAttribute("timeDataArray", cntArrayMap.get("timeDataArray"));
				model.addAttribute("array"+inciCate, cntArrayMap.get("gimsDataArray"));
			}
			
			break;
		case "weather_condt_stats":
			// 기상현황 / 별 교통량 / 돌발대기오염현황 통계(mrt_rpt_tot_info)
			// TODO 보류중
			break;
		case "emer_move_stats":
			// 긴급차량 이동 현황통계
			totalCnt = scsEmrgVhclLogInfoMapper.countEmrgVhclLogList(commonEntity);
			
			List<ScsEmrgVhclLogInfo> emrgVhclLogList = scsEmrgVhclLogInfoMapper.findAllEmrgVhclLogList(commonEntity);
			
			model.addAttribute("statsList", emrgVhclLogList);
			
			break;
		case "bus_rout_stats":
			// 시내버스 이동현황 통계(mrt_bus_tot_move_info)
			totalCnt = mrtBusTotMoveInfoMapper.countBusTotMoveInfoList(commonEntity);
			List<MrtBusTotMoveInfo> busTotMoveInfoList = mrtBusTotMoveInfoMapper.findAllMrtBusToMoveInfo(commonEntity);
			
			model.addAttribute("statsList", busTotMoveInfoList);
			break;
		case "risk_prdn_stats":
			// 위험예측구간 현황 통계(mrt_road_accnt_anal)
			totalCnt = mrtRoadAccntAnalMapper.countAcdntCatgLogColt(commonEntity);
			
			List<MrtRoadAccntAnal> riskZnPrdtList = mrtRoadAccntAnalMapper.findAllRiskZnPrdtList(commonEntity);
			model.addAttribute("statsList", riskZnPrdtList);
			break;
		}
		
    	Paging paging = new Paging();
    	paging.setPageNo(commonEntity.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchOption", commonEntity);
		
		//시군구 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
        return "view/statistics/"+BDStringUtil.convertCamelCase(type);
    }
	
	/**
	 * @Method Name : trafficCommunicationList
	 * @작성일 : 2023. 9. 13.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 소통정보 통계
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/traffic/analysis/communication/list.do")
	public String viewTrafficCommunicationList(Model model, AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr) throws ParseException{
    	model.addAttribute("strDt", adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
    	model.addAttribute("endDt", adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());
    	
		// 1.검색 조건 세팅
		if(GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum())) {
			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setTabNum("1");
		}
		
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStartTime())) {
				int startTime = Integer.parseInt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndTime())) {
				int endTime = Integer.parseInt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(endToday);
		}
		
		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getDayOfTheWeekStr().split(",");
			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		// 날짜 데이터 format (시간, 일, 월, 연도 구분)
		adsiSmcrsrdCrsrdAcsRoadStatsOnhr = trafficInfoStatisticsAnalysisService.chngSearchDatetime(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
		
		// 2.리스트 조회
		int totalCnt = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.countcomunicationList(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
		
		List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> comunicationList = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.findAllComunicationList(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
		
		List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> comunicationListWithoutPaging = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.findAllComunicationListWithoutPaging(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
		
    	Paging paging = new Paging();
    	paging.setPageNo(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
    	model.addAttribute("comunicationList", comunicationList);
    	model.addAttribute("totalCnt", totalCnt);
    	
		// 3.분석 데이터 조회
    	int maxList = 10;
    	if(totalCnt < 10) {
    		maxList = totalCnt;
    	}
    	//	3-1.소통 원활 리스트
    	if(comunicationList.size() > 0) {
    		model.addAttribute("smoothList", comunicationListWithoutPaging.stream()
    				.sorted(Comparator.comparing(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr::getVhclTrfvlm))
    				.collect(Collectors.toList())
    				.subList(0, maxList));
    	//	3-2.소통 정체 리스트
    		model.addAttribute("jamList", comunicationListWithoutPaging.stream()
    				.sorted(Comparator.comparing(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr::getVhclTrfvlm).reversed())
    				.collect(Collectors.toList())
    				.subList(0, maxList)); 
    	}
    	
    	// 3-3.차종별 대수 조회
    	AdsiSmcrsrdCrsrdDrctStatsOnhr adsiSmcrsrdCrsrdDrctStatsOnhr = new AdsiSmcrsrdCrsrdDrctStatsOnhr();
    	adsiSmcrsrdCrsrdDrctStatsOnhr.setSearchContent(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSearchContent());
    	
    	model.addAttribute("totCntVhcl", adsiSmcrsrdCrsrdDrctStatsOnhrMapper.countVhclDivInfo(adsiSmcrsrdCrsrdDrctStatsOnhr));
    	
    	Map<String, Object> vhclInfoArrayMap = adsiSmcrsrdCrsrdDrctStatsOnhrMapper.findOneVhclDivInfo(adsiSmcrsrdCrsrdDrctStatsOnhr);			
		List<Object> vhclInfoLabelArray = new ArrayList<Object>();
		List<Object> vhclInfoDataArray = new ArrayList<Object>();
		if(vhclInfoArrayMap.get("vhclDivLabelArray") != null && vhclInfoArrayMap.get("vhclDivCntArray") != null) {
			vhclInfoLabelArray = GgitsCommonUtils.pgArrayToJavaArray(vhclInfoArrayMap.get("vhclDivLabelArray"));
			vhclInfoDataArray = GgitsCommonUtils.pgArrayToJavaArray(vhclInfoArrayMap.get("vhclDivCntArray"));
		}
		model.addAttribute("vhclInfoLabelArray", vhclInfoLabelArray);
		model.addAttribute("vhclInfoDataArray", vhclInfoDataArray);
		
    	model.addAttribute("tabNum", adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum());
    	model.addAttribute("searchOption", adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
    	
		//시군구 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	return "view/statistics/trafficCommunicationList";
	}
	
	/**
	 * @Method Name : trafficCrowdedList
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 혼잡도 통계
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/traffic/analysis/crowded/{type}/list.do")
	public String viewTrafficCrowdedList(Model model, CommonEntity commonEntity, @PathVariable String type) throws ParseException{

		int totalCnt = 0;
		
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(commonEntity.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(commonEntity.getStartTime())) {
				int startTime = Integer.parseInt(commonEntity.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(commonEntity.getEndTime())) {
				int endTime = Integer.parseInt(commonEntity.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setEndDt(endToday);
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "traffic_crowded_stats":
			// 정체발생량 (mrt_smc_spot_abn)
			totalCnt = mrtSmcSpotAbnMapper.countSmcSpotAbnList(commonEntity);
			
			List<MrtSmcSpotAbn> smcSpotAbnList =  mrtSmcSpotAbnMapper.findAllSmcSpotAbnList(commonEntity);
			
			model.addAttribute("crowdedList", smcSpotAbnList);
			
			// TODO 정체 발생 빈도 통계
			// 평균 속도 통계
			int avgSpd = GgitsCommonUtils.isNull(mrtSmcSpotAbnMapper.findOneSpotAbnAvgSpd(commonEntity)) ? 0 : mrtSmcSpotAbnMapper.findOneSpotAbnAvgSpd(commonEntity); 
			model.addAttribute("avgSpd", avgSpd);
			break;
		case "traffic_congest_stats":
			// 혼잡도 (mrt_smc_abn_los)
			totalCnt = mrtSmcAbnLosMapper.countSmcAbnLosList(commonEntity);
			
			List<MrtSmcAbnLos> smcAbnLosList = mrtSmcAbnLosMapper.findAllSmcAbnLosList(commonEntity);
			
			model.addAttribute("crowdedList", smcAbnLosList);
			
			//TODO 시간대별 차종 통계
			//TODO 평균 혼잡도 통계
			break;
		}
		
    	Paging paging = new Paging();
    	paging.setPageNo(commonEntity.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchOption", commonEntity); 
		
		//시군구 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
	}
	
	/**
	 * @Method Name : trafficPublicTransportList
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 대중교통 지표 총괄 통계
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/traffic/analysis/public/{type}/list.do")
	public String viewTrafficPublicTransportList(@PathVariable String type, Model model, CommonEntity commonEntity) throws ParseException{
		
		int totalCnt = 0;
		
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(commonEntity.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(commonEntity.getStartTime())) {
				int startTime = Integer.parseInt(commonEntity.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(commonEntity.getEndTime())) {
				int endTime = Integer.parseInt(commonEntity.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setEndDt(endToday);
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "bus_dtg_info":
			// 버스DTG
			totalCnt = mrtDtgDangerSectnMapper.countBusDtgInfo(commonEntity);
			List<MrtDtgDangerSectn> busDtgInfoList = mrtDtgDangerSectnMapper.findAllBusDtgInfoList(commonEntity);
			
			model.addAttribute("statsList", busDtgInfoList);
			break;
		case "bus_sttn_rout":
			// 정류장별 버스노선, 버스유형(mrt_bus_sttn_anal)
			totalCnt = mrtBusSttnAnalMapper.countBusSttnAnalList(commonEntity);
			List<MrtBusSttnAnal> busSttnInfoList = mrtBusSttnAnalMapper.findAllBusSttnInfoList(commonEntity);
			
			model.addAttribute("statsList", busSttnInfoList);
			break;
		case "bus_sttn_facility_info":
			// 정류장별 시설물 및 기타정보(mrt_bus_sttn_fctl_anal)
			totalCnt = mrtBusSttnFctlAnalMapper.countBusSttnFcltList(commonEntity);
			List<MrtBusSttnFctlAnal> busSttnFcltList = mrtBusSttnFctlAnalMapper.findAllBusSttnFcltsList(commonEntity);
			
			model.addAttribute("statsList", busSttnFcltList);
			break;
		case "bus_sttn_pasnr":
			// 정류장별 승하차 승객(mrt_bus_sttn_pasng_anal)
			totalCnt = mrtBusSttnPasngAnalMapper.countBusSttnPasngList(commonEntity);
			List<MrtBusSttnPasngAnal> busSttnPasngList = mrtBusSttnPasngAnalMapper.findAllBusSttnPasngList(commonEntity);
			
			model.addAttribute("statsList", busSttnPasngList);
			break;
		case "bus_rout_pasnr":
			// 노선구간별 승하차/재차 승객수(mrt_bus_route_pasng_anal)
			totalCnt = mrtBusRoutePasngAnalMapper.countBusRoutePasngList(commonEntity);
			List<MrtBusRoutePasngAnal> busRoutePasngList = mrtBusRoutePasngAnalMapper.findAllBusRoutePasngMapper(commonEntity);
			
			model.addAttribute("statsList", busRoutePasngList);
			break;
		}
		
		Paging paging = new Paging();
    	paging.setPageNo(commonEntity.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchOption", commonEntity);
		
		//시군구 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
	}
	
	/**
	 * @Method Name : trafficRoadSafetyList
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 도로 안전
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/traffic/analysis/safety/{type}/list.do")
	public String viewTrafficRoadSafetyList(@PathVariable String type, CommonEntity commonEntity, Model model) throws ParseException{
		int totalCnt = 0;
		
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(commonEntity.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(commonEntity.getStartTime())) {
				int startTime = Integer.parseInt(commonEntity.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(commonEntity.getEndTime())) {
				int endTime = Integer.parseInt(commonEntity.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setEndDt(endToday);
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "traffic_acndt_gen_log":
			// 교통사고 발생이력
			totalCnt = mrtAccntLogMapper.countAcdntGenLogInfo(commonEntity);
			List<MrtAccntLog> acdntGenLogInfo = mrtAccntLogMapper.findAllAcdntGenLogInfo(commonEntity);
			
			// 통계 데이터 조회
			model.addAttribute("logInfoList", acdntGenLogInfo);
			break;
		case "traffic_acdnt_catg_log_colt":
			// 교통사고 유형별 이력집계
			totalCnt = mrtRoadAccntAnalMapper.countAcdntCatgLogColt(commonEntity);
			List<MrtRoadAccntAnal> acdntCatgLogColtList = mrtRoadAccntAnalMapper.findAllAcdntCatgLogColt(commonEntity);
			
			// 통계 데이터 조회
			model.addAttribute("logInfoList", acdntCatgLogColtList);
			break;
		}
		
		Paging paging = new Paging();
    	paging.setPageNo(commonEntity.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchOption", commonEntity);
		
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
	}
	
	/**
	 * @Method Name : trafficFacilitiesList
	 * @작성일 : 2023. 9. 14.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 교통시설물 통계
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/traffic/analysis/facilities/{type}/list.do")
	public String viewTrafficFacilitiesList(@PathVariable String type, CommonEntity commonEntity, Model model) throws ParseException{
		int totalCnt = 0;
		
		String startToday = "";
		String endToday = "";
		if(!GgitsCommonUtils.isNull(commonEntity.getStrDt())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getStrDt(), "startDate");
			if(!GgitsCommonUtils.isNull(commonEntity.getStartTime())) {
				int startTime = Integer.parseInt(commonEntity.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday,startTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setStrDt(startToday);
		}
		if(!GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(commonEntity.getEndDt(), "endDate");			
			if(!GgitsCommonUtils.isNull(commonEntity.getEndTime())) {
				int endTime = Integer.parseInt(commonEntity.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday,endTime,"yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
			commonEntity.setEndDt(endToday);
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "traffic_facility_obtc_colt":
			// 교통시설물 장애 통계(mrt_trf_fclts_stts_anls)
			totalCnt = mrtTrfFcltsSttsAnlsMapper.countTrfFcltsSttsAnls(commonEntity);
			List<MrtTrfFcltsSttsAnls> trfFcltsSttsAnlsList = mrtTrfFcltsSttsAnlsMapper.findAllTrfFcltsSttsAnls(commonEntity);
			
			model.addAttribute("trfFcltsSttsList", trfFcltsSttsAnlsList);
			break;
		case "traffic_facility_equipment_log_info":
			// 교통시설물 장비 로그 상세(mrt_trf_fclts_stts_anls)
			totalCnt = mrtTrfFcltsSttsAnlsMapper.countTrfFcltsSttsAnls(commonEntity);
			List<MrtTrfFcltsSttsAnls> trfFcltsEqpLogList = mrtTrfFcltsSttsAnlsMapper.findAllTrfFcltsEqpLogList(commonEntity);
			
			model.addAttribute("trfFcltsSttsList", trfFcltsEqpLogList);
			break;
		}
		
		Paging paging = new Paging();
    	paging.setPageNo(commonEntity.getPage());
    	paging.setTotalCount(totalCnt);
		
    	model.addAttribute("paging", paging);
		
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("searchOption", commonEntity);
		
		//시군 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
	}
	
}
