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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.entity.AdsiDsrcColctInfoCur;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadStatsOnhr;
import com.neighbor21.ggits.common.entity.AdsiVdsColctInfoCur;
import com.neighbor21.ggits.common.entity.AirkrAdsiArqltMsrmtInfo;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.EqpLogDTO;
import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1d;
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
import com.neighbor21.ggits.common.entity.MrtTrfFcltsSttsAnls;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclLogInfo;
import com.neighbor21.ggits.common.entity.TaasAdsiAcdntDstrct;
import com.neighbor21.ggits.common.mapper.AdsiDsrcColctInfoCurMapper;
import com.neighbor21.ggits.common.mapper.AdsiRseSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCameraSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdDrctStatsOnhrMapper;
import com.neighbor21.ggits.common.mapper.AdsiVdsColctInfoCurMapper;
import com.neighbor21.ggits.common.mapper.AdsiVdsColctInfoMapper;
import com.neighbor21.ggits.common.mapper.AdsiVdsSttsInfoMapper;
import com.neighbor21.ggits.common.mapper.AirkrAdsiArqltMsrmtInfoMapper;
import com.neighbor21.ggits.common.mapper.ExtGgitsLinkStd1dMapper;
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
import com.neighbor21.ggits.common.mapper.TaasAdsiAcdntDstrctMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
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
	ExtGgitsLinkStd1dMapper extGgitsLinkStd1dMapper;
	
	@Autowired
	AirkrAdsiArqltMsrmtInfoMapper airkrAdsiArqltMsrmtInfoMapper;
	
	@Autowired
	AdsiSmcrsrdCameraSttsInfoMapper adsiSmcrsrdCameraSttsInfoMapper;
	
	@Autowired
	AdsiVdsSttsInfoMapper adsiVdsSttsInfoMapper;
	
	@Autowired
	AdsiRseSttsInfoMapper adsiRseSttsInfoMapper;
	
	@Autowired
	AdsiVdsColctInfoMapper adsiVdsColctInfoMapper;
	
	@Autowired
	AdsiVdsColctInfoCurMapper adsiVdsColctInfoCurMapper;
	
	@Autowired
	AdsiDsrcColctInfoCurMapper adsiDsrcColctInfoCurMapper;

	@Autowired
	AdsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper adsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper;
	
	@Autowired
	TaasAdsiAcdntDstrctMapper taasAdsiAcdntDstrctMapper;
	
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
		
		// 처음 접근 시
		if(GgitsCommonUtils.isNull(commonEntity.getStrDt()) && GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			commonEntity.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			commonEntity.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "traffic_info_stats":
			// 교통현황 통계
			List<ExtGgitsLinkStd1d> trafficInfoStatsList = extGgitsLinkStd1dMapper.findAllTrafficInfoStats(commonEntity);
			totalCnt = extGgitsLinkStd1dMapper.countTrafficInfoStats(commonEntity);
			
			model.addAttribute("statsList", trafficInfoStatsList);
			break;
		case "unept_sitn_stats":
			// 돌발현황 통계
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("dayOfTheWeek", commonEntity.getDayOfTheWeek());
			paramMap.put("searchContent", commonEntity.getSearchContent());
			paramMap.put("searchLocation", commonEntity.getSigunCdId());
			paramMap.put("selInciCate", commonEntity.getSelInciCate());
			
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
			// 지역별 대기질 통계(airkr_adsi_arqlt_msrmt_info)
			totalCnt = airkrAdsiArqltMsrmtInfoMapper.countArqltMsrmtInfo(commonEntity);
			List<AirkrAdsiArqltMsrmtInfo> arqltMsrmtInfoList = airkrAdsiArqltMsrmtInfoMapper.findAllArqltMsrmtInfoList(commonEntity);
			
			model.addAttribute("statsList", arqltMsrmtInfoList);
			break;
		case "emer_move_stats":
			// 긴급차량 이동 현황통계
			totalCnt = scsEmrgVhclLogInfoMapper.countEmrgVhclLog(commonEntity);
			List<ScsEmrgVhclLogInfo> emrgVhclLogList = scsEmrgVhclLogInfoMapper.findAllEmrgVhclLogList(commonEntity);
			
			model.addAttribute("statsList", emrgVhclLogList);
			
			// 긴급차량 유형별 발생 건수
			Map<String, Object> emrgChartMap = scsEmrgVhclLogInfoMapper.findAllEmrgCntMap(commonEntity);
			model.addAttribute("emrgRateArr", emrgChartMap.get("emrgRateArr"));
			model.addAttribute("emrgLabelArr", emrgChartMap.get("emrgLabelArr"));
			break;
		case "bus_rout_stats":
			// 시내버스 이동현황 통계(mrt_bus_tot_move_info)
			totalCnt = mrtBusTotMoveInfoMapper.countBusTotMoveInfoList(commonEntity);
			List<MrtBusTotMoveInfo> busTotMoveInfoList = mrtBusTotMoveInfoMapper.findAllMrtBusToMoveInfo(commonEntity);
			
			model.addAttribute("statsList", busTotMoveInfoList);
			break;
		case "risk_prdn_stats":
			// 위험예측구간 현황 통계(mrt_road_accnt_anal)
			totalCnt = mrtRoadAccntAnalMapper.countRiskZnPrdt(commonEntity);
			List<MrtRoadAccntAnal> riskZnPrdtList = mrtRoadAccntAnalMapper.findAllRiskZnPrdtList(commonEntity);
			
			// 통계 데이터 조회
			Map<String, Object> riskPrdnStatsMap = mrtRoadAccntAnalMapper.findAllRiskPrdnStatsMap(commonEntity);
			model.addAttribute("acdntTypeCdArr", riskPrdnStatsMap.get("acdntTypeCdArr"));
			model.addAttribute("acdntCntArr", riskPrdnStatsMap.get("acdntCntArr"));
			
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
	
	@PostMapping("/traffic/analysis/traffic_info_stats/list.ajax")
	public @ResponseBody CommonResponse<?> getTrfInfoStatsData(CommonEntity commonEntity){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	List<ExtGgitsLinkStd1d> trafficAnalList = new ArrayList<ExtGgitsLinkStd1d>();
    	trafficAnalList = extGgitsLinkStd1dMapper.findAllTraffcAnalList(commonEntity);
    	int totalCnt = commonEntity.getTotalCntChk().intValue();
		int maxList = 10;
    	if(totalCnt < 10) {
    		maxList = totalCnt;
    	}
		if(trafficAnalList.size() > 0) {
			// 소통 정체 도로 순위
			resultMap.put("smoothTrafficList", trafficAnalList.stream()
					.sorted(Comparator.comparing(ExtGgitsLinkStd1d::getAvgSpd).reversed())
					.collect(Collectors.toList())
					.subList(0, maxList));
			// 소통 원활 도로 순위
			resultMap.put("jamTrafficList", trafficAnalList.stream()
					.sorted(Comparator.comparing(ExtGgitsLinkStd1d::getAvgSpd))
					.collect(Collectors.toList())
					.subList(0, maxList));
			// 최고 교통량 도로 순위
			resultMap.put("maxTrafficValList", trafficAnalList.stream()
						.sorted(Comparator.comparing(ExtGgitsLinkStd1d::getAvgVol).reversed())
						.collect(Collectors.toList())
						.subList(0, maxList));
			// 최저 교통량 도로 순위
			resultMap.put("minTrafficValList", trafficAnalList.stream()
					.sorted(Comparator.comparing(ExtGgitsLinkStd1d::getAvgVol))
					.collect(Collectors.toList())
					.subList(0, maxList));
			
		}
    	return CommonResponse.successToData(resultMap, "");
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
	public String viewTrafficCommunicationList(Model model, String initAppchYn, AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr) throws ParseException{
		if(!GgitsCommonUtils.isNull(initAppchYn) && initAppchYn.equals("Y")) {
		// 처음 접근일시
		adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -1));
		adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -1));
		}
		
		model.addAttribute("strDt", adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
    	model.addAttribute("endDt", adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());

		// 1.검색 조건 세팅
		if(GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum())) {
			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setTabNum("1");
		}
		
		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getDayOfTheWeekStr().split(",");
			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		// 날짜 데이터 format (시간, 일, 월, 연도 구분)
//		adsiSmcrsrdCrsrdAcsRoadStatsOnhr = trafficInfoStatisticsAnalysisService.chngSearchDatetime(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
		int totalCnt = 0;
    	Paging paging = new Paging();
    	
    	String strSearchMonth = "";
    	String endSearchMonth = "";
		switch (adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum()) {
		case "1":
		case "2":
		case "3":
		case "4":
	    	if(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum().equals("3") || adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum().equals("4")) {
	    		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth())
	    				&& !adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth().equals("searchAllMonth")
	    				&& Integer.parseInt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth()) < 10) {
	    			strSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth();
	    			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrSearchMonth("0"+strSearchMonth);
	    		}else {
	    			strSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth();
	    		}
	    		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth())
	    				&& !adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth().equals("searchAllMonth")
	    				&& Integer.parseInt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth()) < 10) {
	    			endSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth();
	    			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndSearchMonth("0"+endSearchMonth);
	    		}else {
	    			endSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth();	    			
	    		}
	    		List<Map<String,Object>> yearsList = new ArrayList<Map<String,Object>>();
	    		yearsList = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.findAllDataYears();
	    		model.addAttribute("yearsList", yearsList);
	    	}
			// 2.리스트 조회
			totalCnt = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.countcomunicationList(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
			
			List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> comunicationList = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.findAllComunicationList(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
			
	    	paging.setPageNo(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
	    	paging.setTotalCount(totalCnt);
			
	    	model.addAttribute("paging", paging);
	    	model.addAttribute("comunicationList", comunicationList);
	    	model.addAttribute("totalCnt", totalCnt);
	    	
	    	adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrSearchMonth(strSearchMonth);
	    	adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndSearchMonth(endSearchMonth);
			break;
		case "5":
			// VDS
			AdsiVdsColctInfoCur adsiVdsColctInfoCur = new AdsiVdsColctInfoCur();
			adsiVdsColctInfoCur.setSearchContent(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSearchContent());
			adsiVdsColctInfoCur.setPage(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
			adsiVdsColctInfoCur.setSigunCdId(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSigunCdId());
			adsiVdsColctInfoCur.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
			adsiVdsColctInfoCur.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());			
			
			List<AdsiVdsColctInfoCur> vdsColctInfoList = adsiVdsColctInfoCurMapper.findAdsiVdsColctInfoList(adsiVdsColctInfoCur);
			totalCnt = adsiVdsColctInfoCurMapper.countVdsColctInfo(adsiVdsColctInfoCur);

			paging.setPageNo(adsiVdsColctInfoCur.getPage());
	    	paging.setTotalCount(totalCnt);
			
	    	model.addAttribute("paging", paging);
	    	model.addAttribute("comunicationList", vdsColctInfoList);
	    	model.addAttribute("totalCnt", totalCnt);
			
	    	break;
		case "6":
			// DSRC
			AdsiDsrcColctInfoCur adsiDsrcColctInfoCur = new AdsiDsrcColctInfoCur();
			adsiDsrcColctInfoCur.setSearchContent(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSearchContent());
			adsiDsrcColctInfoCur.setPage(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
			adsiDsrcColctInfoCur.setSigunCdId(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSigunCdId());
			adsiDsrcColctInfoCur.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
			adsiDsrcColctInfoCur.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());	
			
			List<AdsiDsrcColctInfoCur> dsrcColctInfoList = adsiDsrcColctInfoCurMapper.findAdsiDsrcColctInfoList(adsiDsrcColctInfoCur);
			totalCnt = adsiDsrcColctInfoCurMapper.countDsrcColctInfo(adsiDsrcColctInfoCur);

			paging.setPageNo(adsiDsrcColctInfoCur.getPage());
	    	paging.setTotalCount(totalCnt);
			
	    	model.addAttribute("paging", paging);
	    	model.addAttribute("comunicationList", dsrcColctInfoList);
	    	model.addAttribute("totalCnt", totalCnt);
			
			break;
		case "7":
			// 스마트교차로
			MrtSmcSpotAbn mrtSmcSpotAbn = new MrtSmcSpotAbn();
			mrtSmcSpotAbn.setSearchContent(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSearchContent());
			mrtSmcSpotAbn.setPage(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
			mrtSmcSpotAbn.setSigunCdId(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSigunCdId());
			mrtSmcSpotAbn.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
			mrtSmcSpotAbn.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());	
			
			List<MrtSmcSpotAbn> smcSpotAbnList = mrtSmcSpotAbnMapper.findAllSmcSpotAbnListForStats(mrtSmcSpotAbn);
			totalCnt = mrtSmcSpotAbnMapper.countSmcSpotAbnForStats(mrtSmcSpotAbn);
			
			paging.setPageNo(mrtSmcSpotAbn.getPage());
	    	paging.setTotalCount(totalCnt);
			
	    	model.addAttribute("paging", paging);
	    	model.addAttribute("comunicationList", smcSpotAbnList);
	    	model.addAttribute("totalCnt", totalCnt);
			
			break;
		}
		
		model.addAttribute("tabNum", adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum());
		model.addAttribute("searchOption", adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
		
		//시군구 코드 목록
		model.addAttribute("sggCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD"));
    	return "view/statistics/trafficCommunicationList";
	}
	
	@PostMapping("/traffic/analysis/communication/list.ajax")
	public @ResponseBody CommonResponse<?> getComunicationData(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	int maxList = 10;
    	int totalCnt = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTotalCntChk().intValue();
    	String strSearchMonth = "";
    	String endSearchMonth = "";
    	switch (adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum()) {
    	case "1":
		case "2":
		case "3":
		case "4":
			if(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum().equals("3") || adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum().equals("4")) {
	    		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth())
	    				&& !adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth().equals("searchAllMonth")
	    				&& Integer.parseInt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth()) < 10) {
	    			strSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth();
	    			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrSearchMonth("0"+strSearchMonth);
	    		}else {
	    			strSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrSearchMonth();
	    		}
	    		if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth())
	    				&& !adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth().equals("searchAllMonth")
	    				&& Integer.parseInt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth()) < 10) {
	    			endSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth();
	    			adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndSearchMonth("0"+endSearchMonth);
	    		}else {
	    			endSearchMonth = adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndSearchMonth();	    			
	    		}
	    	}
			
			List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> comunicationListWithoutPaging = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.findAllComunicationListWithoutPaging(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
			//분석 데이터 조회
	    	if(totalCnt < 10) {
	    		maxList = totalCnt;
	    	}
	    	//소통 원활 리스트
	    	if(comunicationListWithoutPaging.size() > 0) {
	    		resultMap.put("smoothList", comunicationListWithoutPaging.stream()
	    				.sorted(Comparator.comparing(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr::getAvgSpd).reversed())
	    				.collect(Collectors.toList())
	    				.subList(0, maxList));
	    	//소통 정체 리스트
	    		resultMap.put("jamList", comunicationListWithoutPaging.stream()
	    				.sorted(Comparator.comparing(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr::getAvgSpd))
	    				.collect(Collectors.toList())
	    				.subList(0, maxList)); 
	    	}
			break;
		case "5":
			AdsiVdsColctInfoCur adsiVdsColctInfoCur = new AdsiVdsColctInfoCur();
			adsiVdsColctInfoCur.setSearchContent(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSearchContent());
			adsiVdsColctInfoCur.setPage(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
			adsiVdsColctInfoCur.setSigunCdId(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSigunCdId());
			adsiVdsColctInfoCur.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
			adsiVdsColctInfoCur.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());	
			
			// 소통 원활,정체 도로 순위 top 10
	    	adsiVdsColctInfoCur.setPage(0);
	    	List<AdsiVdsColctInfoCur> vdsColctInfoListWithoutPaging = new ArrayList<AdsiVdsColctInfoCur>();
			vdsColctInfoListWithoutPaging = adsiVdsColctInfoCurMapper.findAdsiVdsColctInfoList(adsiVdsColctInfoCur);
			
	    	if(totalCnt < 10) {
	    		maxList = totalCnt;
	    	}
	    	//소통 원활 리스트
	    	if(vdsColctInfoListWithoutPaging.size() > 0) {
	    		resultMap.put("smoothList", vdsColctInfoListWithoutPaging.stream()
	    				.sorted(Comparator.comparing(AdsiVdsColctInfoCur::getAvgSpd).reversed())
	    				.collect(Collectors.toList())
	    				.subList(0, maxList));
	    	//소통 정체 리스트
	    		resultMap.put("jamList", vdsColctInfoListWithoutPaging.stream()
	    				.sorted(Comparator.comparing(AdsiVdsColctInfoCur::getAvgSpd))
	    				.collect(Collectors.toList())
	    				.subList(0, maxList)); 
	    	}
			break;
		case "6":
			AdsiDsrcColctInfoCur adsiDsrcColctInfoCur = new AdsiDsrcColctInfoCur();
			adsiDsrcColctInfoCur.setSearchContent(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSearchContent());
			adsiDsrcColctInfoCur.setPage(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
			adsiDsrcColctInfoCur.setSigunCdId(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSigunCdId());
			adsiDsrcColctInfoCur.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
			adsiDsrcColctInfoCur.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());	
			
			// 소통 원활,정체 도로 순위 top 10
	    	adsiDsrcColctInfoCur.setPage(0);
	    	List<AdsiDsrcColctInfoCur> dsrcColctInfoListWithoutPaging = new ArrayList<AdsiDsrcColctInfoCur>();
			dsrcColctInfoListWithoutPaging = adsiDsrcColctInfoCurMapper.findAdsiDsrcColctInfoList(adsiDsrcColctInfoCur);
			
	    	if(totalCnt < 10) {
	    		maxList = totalCnt;
	    	}
	    	//소통 원활 리스트
	    	if(dsrcColctInfoListWithoutPaging.size() > 0) {
	    		resultMap.put("smoothList", dsrcColctInfoListWithoutPaging.stream()
	    				.sorted(Comparator.comparing(AdsiDsrcColctInfoCur::getAvgSpd).reversed())
	    				.collect(Collectors.toList())
	    				.subList(0, maxList));
	    	//소통 정체 리스트
	    		resultMap.put("jamList", dsrcColctInfoListWithoutPaging.stream()
	    				.sorted(Comparator.comparing(AdsiDsrcColctInfoCur::getAvgSpd))
	    				.collect(Collectors.toList())
	    				.subList(0, maxList)); 
	    	}
			break;
		case "7":
			MrtSmcSpotAbn mrtSmcSpotAbn = new MrtSmcSpotAbn();
			mrtSmcSpotAbn.setSearchContent(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSearchContent());
			mrtSmcSpotAbn.setPage(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getPage());
			mrtSmcSpotAbn.setSigunCdId(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getSigunCdId());
			mrtSmcSpotAbn.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt());
			mrtSmcSpotAbn.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt());
			
			// 소통 원활,정체 도로 순위 top 10
			mrtSmcSpotAbn.setPage(0);
			List<MrtSmcSpotAbn> smcSpotAbnList = new ArrayList<MrtSmcSpotAbn>();
			smcSpotAbnList = mrtSmcSpotAbnMapper.findAllSmcSpotAbnListForStats(mrtSmcSpotAbn);
			
			if(totalCnt < 10) {
	    		maxList = totalCnt;
	    	}
			//소통 원활 리스트
	    	if(smcSpotAbnList.size() > 0) {
	    		resultMap.put("smoothList", smcSpotAbnList.stream()
	    				.sorted(Comparator.comparing(MrtSmcSpotAbn::getAvgSpd).reversed())
	    				.collect(Collectors.toList())
	    				.subList(0, maxList));
	    	//소통 정체 리스트
	    		resultMap.put("jamList", smcSpotAbnList.stream()
	    				.sorted(Comparator.comparing(MrtSmcSpotAbn::getAvgSpd))
	    				.collect(Collectors.toList())
	    				.subList(0, maxList)); 
	    	}
			break;
    	}
    	return CommonResponse.successToData(resultMap, "");
    }
	
	@PostMapping("/traffic/analysis/communication/list/chart.ajax")
	public @ResponseBody CommonResponse<?> getComunicationChartData(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrDt(BDDateFormatUtil.isDateCal("yyyyMMdd", -1));
		adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(BDDateFormatUtil.isDateCal("yyyyMMdd", -1));
		
    	resultMap.put("totCntVhcl", adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.countVhclDivInfo(adsiSmcrsrdCrsrdAcsRoadStatsOnhr));
    	
    	Map<String, Object> vhclInfoArrayMap = adsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper.findOneVhclDivInfo(adsiSmcrsrdCrsrdAcsRoadStatsOnhr);
    	resultMap.put("vhclInfoLabelArray", vhclInfoArrayMap.get("vhclDivLabelArray"));
		resultMap.put("vhclInfoDataArray", vhclInfoArrayMap.get("vhclDivCntArray"));
    	
    	return CommonResponse.successToData(resultMap, "");
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
		
		// 처음 접근 시
		if(GgitsCommonUtils.isNull(commonEntity.getStrDt()) && GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			commonEntity.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			commonEntity.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
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
			
			Integer avgSpd = mrtSmcSpotAbnMapper.findOneSpotAbnAvgSpd(commonEntity);
			if(GgitsCommonUtils.isNull(avgSpd)) {
				avgSpd = 0;
			}
			model.addAttribute("avgSpd", avgSpd);
			break;
		case "traffic_congest_stats":
			// 혼잡도 (mrt_smc_abn_los)
			totalCnt = mrtSmcAbnLosMapper.countSmcAbnLosList(commonEntity);
			
			List<MrtSmcAbnLos> smcAbnLosList = mrtSmcAbnLosMapper.findAllSmcAbnLosList(commonEntity);
			
			model.addAttribute("crowdedList", smcAbnLosList);
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
		
		// 처음 접근 시
		if(GgitsCommonUtils.isNull(commonEntity.getStrDt()) && GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			commonEntity.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			commonEntity.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
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
			
			// 통계 데이터 조회
	    	Map<String, Object> paramMap = new HashMap<String, Object>();
	    	if(!busDtgInfoList.isEmpty() && !GgitsCommonUtils.isNull(busDtgInfoList.get(0).getRouteId())) {
	    		paramMap.put("routeId", busDtgInfoList.get(0).getRouteId());
	    		
	    		paramMap.put("todayStartDt", GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00", Calendar.HOUR));
	    		paramMap.put("todayEndDt", GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23", Calendar.HOUR));
	    		
	    		Map<String, Object> busDangrStatsMap = mrtDtgDangerSectnMapper.findOneBusDangrStatsMap(paramMap);
	    		model.addAttribute("graphTimeArr", busDangrStatsMap.get("graphTimeArr"));
	    		model.addAttribute("spdngRungCntArr", busDangrStatsMap.get("spdngRungCntArr"));
	    		model.addAttribute("sdacelRungCntArr", busDangrStatsMap.get("sdacelRungCntArr"));
	    		model.addAttribute("rpddclRungCntArr", busDangrStatsMap.get("rpddclRungCntArr"));
	    		model.addAttribute("sdstopRungCntArr", busDangrStatsMap.get("sdstopRungCntArr"));
	    		model.addAttribute("sdstrtRungCntArr", busDangrStatsMap.get("sdstrtRungCntArr"));
	    		model.addAttribute("lngtrmaclRungCntArr", busDangrStatsMap.get("lngtrmaclRungCntArr"));
	    		model.addAttribute("routeNm", busDtgInfoList.get(0).getRouteNm());
	    	}
			
			model.addAttribute("statsList", busDtgInfoList);
			
			break;
		case "bus_sttn_rout":
			// 정류장별 버스노선, 버스유형(mrt_bus_sttn_anal)
			totalCnt = mrtBusSttnAnalMapper.countBusSttnAnalList(commonEntity);
			List<MrtBusSttnAnal> busSttnInfoList = mrtBusSttnAnalMapper.findAllBusSttnInfoList(commonEntity);
			
			model.addAttribute("statsList", busSttnInfoList);
			
			// 통계 데이터 조회
			Map<String, Object> busStatsListMap = mrtBusSttnAnalMapper.findAllBusStatsList(commonEntity);
			model.addAttribute("busRouteRateArr", busStatsListMap.get("busRouteRateArr"));
			model.addAttribute("busRouteTpArr", busStatsListMap.get("busRouteTpArr"));
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
			List<MrtBusRoutePasngAnal> busRoutePasngList = mrtBusRoutePasngAnalMapper.findAllBusRoutePasngMapper(commonEntity);
			totalCnt = mrtBusRoutePasngAnalMapper.countBusRoutePasng(commonEntity);
			
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
	 * @Method Name : findBusDtgData
	 * @작성일 : 2023. 11. 02.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 버스 DTG 통계정보 조회
	 * @param : commonEntity
	 * @return
	 */
	@GetMapping("/traffic/analysis/public/bus_dtg_info/data.ajax")
    public @ResponseBody CommonResponse<?> findBusDtgData(@RequestParam String routeId){
    	Map<String, Object> resultMap = new HashMap<String, Object>();
    	
    	Map<String, Object> paramMap = new HashMap<String, Object>();
    	paramMap.put("routeId", routeId);
    	
    	paramMap.put("todayStartDt", GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00", Calendar.HOUR));
		paramMap.put("todayEndDt", GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23", Calendar.HOUR));
    	
		Map<String, Object> busDangrStatsMap = mrtDtgDangerSectnMapper.findOneBusDangrStatsMap(paramMap);
		resultMap.put("graphTimeArr", busDangrStatsMap.get("graphTimeArr"));
		resultMap.put("spdngRungCntArr", busDangrStatsMap.get("spdngRungCntArr"));
		resultMap.put("sdacelRungCntArr", busDangrStatsMap.get("sdacelRungCntArr"));
		resultMap.put("rpddclRungCntArr", busDangrStatsMap.get("rpddclRungCntArr"));
		resultMap.put("sdstopRungCntArr", busDangrStatsMap.get("sdstopRungCntArr"));
		resultMap.put("sdstrtRungCntArr", busDangrStatsMap.get("sdstrtRungCntArr"));
		resultMap.put("lngtrmaclRungCntArr", busDangrStatsMap.get("lngtrmaclRungCntArr"));
		
    	return CommonResponse.successToData(resultMap, "");
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
		
		// 처음 접근 시
		if(GgitsCommonUtils.isNull(commonEntity.getStrDt()) && GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			commonEntity.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			commonEntity.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
		}
		
		if(!GgitsCommonUtils.isNull(commonEntity.getDayOfTheWeekStr())) {
			String[] dayOfTheWeekArr = commonEntity.getDayOfTheWeekStr().split(",");
			commonEntity.setDayOfTheWeek(Arrays.asList(dayOfTheWeekArr));
		}
		
		switch (type) {
		case "traffic_acndt_gen_log":
			// 교통사고 발생이력 ext_taas_adsi_acdnt_dstrct
			List<TaasAdsiAcdntDstrct> acdntGenLogInfo = taasAdsiAcdntDstrctMapper.findAllAcdntGenLogInfo(commonEntity);
			totalCnt = taasAdsiAcdntDstrctMapper.countAcdntGenLog(commonEntity);
			
			// 통계 데이터 조회
			Map<String, Object> trafficAcdntGenLogMap = taasAdsiAcdntDstrctMapper.findAllAcdntGenLogMap(commonEntity);
			model.addAttribute("cdNmArr", trafficAcdntGenLogMap.get("cdNmArr"));
			model.addAttribute("acdntCntArr", trafficAcdntGenLogMap.get("acdntCntArr"));
			model.addAttribute("casltCntArr", trafficAcdntGenLogMap.get("casltCntArr"));
			model.addAttribute("dcsdCntArr", trafficAcdntGenLogMap.get("dcsdCntArr"));
			model.addAttribute("swpsnCntArr", trafficAcdntGenLogMap.get("swpsnCntArr"));
			model.addAttribute("sinjpsnCntArr", trafficAcdntGenLogMap.get("sinjpsnCntArr"));
			model.addAttribute("injDclrCntArr", trafficAcdntGenLogMap.get("injDclrCntArr"));
			
			model.addAttribute("logInfoList", acdntGenLogInfo);
			break;
		case "traffic_acdnt_catg_log_colt":
			// 교통사고 유형별 이력집계
			List<MrtRoadAccntAnal> acdntCatgLogColtList = mrtRoadAccntAnalMapper.findAllAcdntCatgLogColt(commonEntity);
			totalCnt = mrtRoadAccntAnalMapper.countAcdntCatgLog(commonEntity);
			
			// 통계 데이터 조회
			Map<String, Object> acdntlogListMap = mrtRoadAccntAnalMapper.findAllTrafficAcdntLogMapByType(commonEntity);
			model.addAttribute("acdntTypeCdArr", acdntlogListMap.get("acdntTypeCdArr"));
			model.addAttribute("acdntOccurCntArr", acdntlogListMap.get("acdntOccurCntArr"));
			model.addAttribute("totCasltCntArr", acdntlogListMap.get("totCasltCntArr"));
			model.addAttribute("dcsdCntArr", acdntlogListMap.get("dcsdCntArr"));
			model.addAttribute("ftltyRateArr", acdntlogListMap.get("ftltyRateArr"));
			
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
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 교통시설물 통계(메뉴 숨김)
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/traffic/analysis/facilities/{type}/list.do")
	public String viewTrafficFacilitiesList(@PathVariable String type, CommonEntity commonEntity, Model model) throws ParseException{
		int totalCnt = 0;
		
		// 처음 접근 시
		if(GgitsCommonUtils.isNull(commonEntity.getStrDt()) && GgitsCommonUtils.isNull(commonEntity.getEndDt())) {
			commonEntity.setStrDt(BDDateFormatUtil.isDateCal("yyyy-MM-dd", -7));
			commonEntity.setEndDt(BDDateFormatUtil.isNowStr("yyyy-MM-dd"));
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
			// 교통시설물 장비 로그 상세
			commonEntity.setSearchType(!GgitsCommonUtils.isNull(commonEntity.getSearchType()) ? commonEntity.getSearchType() : "VDS");
			List<EqpLogDTO> eqpLogList = new ArrayList<EqpLogDTO>();
			if(commonEntity.getSearchType().equals("VDS")) {
				totalCnt = adsiVdsSttsInfoMapper.countVdsInfoList(commonEntity);
				eqpLogList = adsiVdsSttsInfoMapper.findAllVdsInfoList(commonEntity);
			}else if(commonEntity.getSearchType().equals("RSE")) {
				totalCnt = adsiRseSttsInfoMapper.countRseInfoList(commonEntity);
				eqpLogList = adsiRseSttsInfoMapper.findAllRseInfoList(commonEntity);
			}else if(commonEntity.getSearchType().equals("SMC")) {
				totalCnt = adsiSmcrsrdCameraSttsInfoMapper.countSmcInfoList(commonEntity);
				eqpLogList = adsiSmcrsrdCameraSttsInfoMapper.findAllSmcInfoList(commonEntity);
			}
			model.addAttribute("trfFcltsSttsList", eqpLogList);

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
		// 관리 기관 코드
		model.addAttribute("mngInstCdList", mOpCodeMapper.findAllCodeListByGrpCdId("MNG_INST_CD"));
		// 도로 유형 코드 목록
		model.addAttribute("roadRankList", mOpCodeMapper.findAllCodeListByGrpCdId("ROAD_RANK"));
		
		return "view/statistics/"+BDStringUtil.convertCamelCase(type);
	}
	
}
