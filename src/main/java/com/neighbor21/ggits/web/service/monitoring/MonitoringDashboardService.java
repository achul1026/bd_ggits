package com.neighbor21.ggits.web.service.monitoring;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.xmlbeans.impl.xb.xsdschema.NamedGroup.All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.dto.MapChartDataDTO;
import com.neighbor21.ggits.common.dto.MonitoringDashboardDTO;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.entity.MOpLayoutMstInfo;
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.MrtBusTotMoveInfo;
import com.neighbor21.ggits.common.entity.MrtSigCrsdTrfAnal;
import com.neighbor21.ggits.common.entity.MrtSmcAbnLos;
import com.neighbor21.ggits.common.entity.MrtSmcSpotAbn;
import com.neighbor21.ggits.common.entity.MrtStdLinkSectnInfo;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclLogInfo;
import com.neighbor21.ggits.common.enums.LinkedTableInfo;
import com.neighbor21.ggits.common.enums.ServerMngType;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.mapper.LTcDataLogMapper;
import com.neighbor21.ggits.common.mapper.MOpLayoutMstInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.mapper.MrtSigCrsdTrfAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcAbnLosMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcSpotAbnMapper;
import com.neighbor21.ggits.common.mapper.MrtStdLinkSectnInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class MonitoringDashboardService{
	
   @Autowired
   MOpLayoutMstInfoMapper mOpLayoutMstInfoMapper;
   
   @Autowired
   MOpMenuMapper mOpMenuMapper;
    
   @Autowired
   MOpOperatorMapper mOpOperatorMapper;
   
   @Autowired
   MrtSmcSpotAbnMapper mrtSmcSpotAbnMapper;
   
   @Autowired
   MrtSmcAbnLosMapper mrtSmcAbnLosMapper;
   
   @Autowired
   MrtSigCrsdTrfAnalMapper mrtSigCrsdTrfAnalMapper;

   @Autowired
   MrtStdLinkSectnInfoMapper mrtStdLinkSectnInfoMapper;
   
   @Autowired
   GimsMngInciDetailMapper gimsMngInciDetailMapper;
   
	@Autowired
	LTcDataLogMapper lTcDataLogMapper;
   
   public MonitoringDashboardDTO getUserLayoutInfo(){
	   MonitoringDashboardDTO monitoringDashboardDTO = new MonitoringDashboardDTO();
	   String[] menuPttnTypeArr = {"EVC000","EVC001","EVC002","EVC004","EVC007"};
	   String[] menuCtgryTypeArr = {"MCT000","MCT001"}; //MCT000 경기도 통합 교통 현황, MCT001수집시스템 운영 현황
	   MOpMenu mOpMenu = new MOpMenu();
	   mOpMenu.setMenuPttrnTypeArr(menuPttnTypeArr);
	   List<String> menuIdList = mOpMenuMapper.findMenuIdByMenuPttrnType(mOpMenu);
	   
	   MOpLayoutMstInfo mOpLayoutMstInfo = new MOpLayoutMstInfo();
	   mOpLayoutMstInfo.setMenuIdList(menuIdList);
	   mOpLayoutMstInfo.setOprtrId(LoginSessionUtils.getOprtrId());
	   
	   List<MOpLayoutMstInfo> mOpLayoutMstInfoList = mOpLayoutMstInfoMapper.findAllByOprtrIdAndMenuIdList(mOpLayoutMstInfo);
	   
	   List<Map<String,Object>> layoutList = new ArrayList<Map<String,Object>>();
	   for(int i = 0; i < 3; i++) {
		   Map<String,Object> layout = new HashMap<String,Object>();
		   layout.put("layoutMenu", mOpLayoutMstInfoList);
		   layoutList.add(layout);
	   }
	   monitoringDashboardDTO.setMenuPttrnTypeList(Arrays.asList(menuCtgryTypeArr));
	   monitoringDashboardDTO.setLayoutList(layoutList);
	   monitoringDashboardDTO.setmOpLayoutMstInfoList(mOpLayoutMstInfoList);
	   return monitoringDashboardDTO;
   }
   
   public static String getLayoutUseYn(MOpLayoutMstInfo mOpLayoutMstInfo, int idx) {
	   String layOutUseYn = "N";
	   switch (idx) {  
	   case 1:
		   layOutUseYn = mOpLayoutMstInfo.getLayout1UseYn();
		   break;
	   case 2:
		   layOutUseYn = mOpLayoutMstInfo.getLayout2UseYn();
		   break;
	   case 3:
		   layOutUseYn = mOpLayoutMstInfo.getLayout3UseYn();
		   break;
	   default:
		   layOutUseYn = "N";
	   }
	   return layOutUseYn;
   }
   
   public void updateMOpLayoutMstInfo(List<Map<String,Object>> paramList , String layoutNo) {
	   if(!paramList.isEmpty()) {
		   for(Map<String,Object> paramMap : paramList) {
			   String layoutId = (String) paramMap.get("layoutId");
			   String useYn = (String) paramMap.get("useYn");
			   
			   MOpLayoutMstInfo dbMOpLayoutMstInfo = mOpLayoutMstInfoMapper.findOneByLayoutId(layoutId);
			   switch (layoutNo) {
				case "1":
					dbMOpLayoutMstInfo.setLayout1UseYn(useYn);
					break;
				case "2":
					dbMOpLayoutMstInfo.setLayout2UseYn(useYn);
					break;
				case "3":
					dbMOpLayoutMstInfo.setLayout3UseYn(useYn);
					break;
				default:
					break;
				}
			   mOpLayoutMstInfoMapper.updateMOpLayoutMstInfo(dbMOpLayoutMstInfo);
		   }
		   //레이아웃 정보 수정후 유저 레이아웃 정보수정
		   MOpOperator mOpOperator = new MOpOperator();
		   mOpOperator.setOprtrId(LoginSessionUtils.getOprtrId());
		   MOpOperator dbMOpOperator = mOpOperatorMapper.findOneUserDetailByOprtrId(mOpOperator);
		   dbMOpOperator.setLayoutNo(Integer.parseInt(layoutNo));
		   mOpOperatorMapper.update(dbMOpOperator);
	   }
   }
   
   public List<MapChartDataDTO> getChartDataInfo(Map<String,Object> paramMap) throws ParseException {
	   List<MapChartDataDTO> resultList = new ArrayList<MapChartDataDTO>();

	   int layoutNo = paramMap.get("layoutNo") != null ? Integer.parseInt(String.valueOf(paramMap.get("layoutNo"))):1;
	   
	   MOpLayoutMstInfo mOpLayoutMstInfo = new MOpLayoutMstInfo();
	   mOpLayoutMstInfo.setDataTypeCd("DTC000");
	   mOpLayoutMstInfo.setOprtrId(LoginSessionUtils.getOprtrId());
	   
	   List<MOpLayoutMstInfo> mOpLayoutMstInfoList = mOpLayoutMstInfoMapper.findAllByOprtrIdAndMenuIdList(mOpLayoutMstInfo);

	    //금일
		String startToday = GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR);
		String endToday = GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR);
		String now = GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd HH:mm:ss", Calendar.HOUR);
		String oneHoursAgoTime = GgitsCommonUtils.getCalculationDateToString(-1, "yyyy-MM-dd HH:mm:ss", Calendar.HOUR);

		//전일
		String startYesterDay = GgitsCommonUtils.getCalculationDateToString(-1, "yyyy-MM-dd 00:00:00", Calendar.DAY_OF_MONTH);
		String nowTimeYesterDay = GgitsCommonUtils.getCalculationDateToString(-1, "yyyy-MM-dd HH:mm:ss", Calendar.DAY_OF_MONTH);
	   
	   if(!mOpLayoutMstInfoList.isEmpty()) {
		   for(MOpLayoutMstInfo dbMOpLayoutMstInfo : mOpLayoutMstInfoList) {
			   MapChartDataDTO mapChartDataDTO = new MapChartDataDTO();
			   mapChartDataDTO.setTitle(dbMOpLayoutMstInfo.getLayoutMenuNm());
			   if("Y".equals(MonitoringDashboardService.getLayoutUseYn(dbMOpLayoutMstInfo, layoutNo))) {
				   switch(dbMOpLayoutMstInfo.getFnctType()) {
				   case "FTC004":
					   mapChartDataDTO.setFnctType("FTC004");
					   //교차로 및 구간 소통정보
					   String commTabOption = paramMap.get("commTabOption") != null ? String.valueOf(paramMap.get("commTabOption")):"link";
					   List<Map<String,Object>> commTop5List = new ArrayList<Map<String,Object>>();
					   	switch(commTabOption) {
					   	case "cross" :
					   		MrtSmcSpotAbn crossMrtSmcSpotAbn = new MrtSmcSpotAbn();
					   		crossMrtSmcSpotAbn.setStrDt(startToday);
					   		crossMrtSmcSpotAbn.setEndDt(now);
					   		crossMrtSmcSpotAbn.setOrderByOption("trfvlm");
					   		commTop5List = mrtSmcSpotAbnMapper.findTop5CrossRoadsInfo(crossMrtSmcSpotAbn);
					   		break;
					   	case "link" : 
					   		MrtStdLinkSectnInfo commMrtStdLinkSectnInfo = new MrtStdLinkSectnInfo();
					   		commMrtStdLinkSectnInfo.setStrDt(startToday);
					   		commMrtStdLinkSectnInfo.setEndDt(now);
					   		commTop5List = mrtStdLinkSectnInfoMapper.findTop5ByAnlsDt(commMrtStdLinkSectnInfo);
					   		break;
					   	}
					   	mapChartDataDTO.setTableData(commTop5List);
					   	mapChartDataDTO.setStartDt(GgitsCommonUtils.getTimeForStringDate(startToday, "yyyy-MM-dd HH:mm:ss"));
					   	mapChartDataDTO.setEndDt(GgitsCommonUtils.getTimeForStringDate(now, "yyyy-MM-dd HH:mm:ss"));
					   	mapChartDataDTO.setTableOption(commTabOption);
					   break;
				   case "FTC005":
					   //시간대별 누적 교통량
					   mapChartDataDTO.setFnctType("FTC005");
					   MrtSmcSpotAbn mrtSmcSpotAbn = new MrtSmcSpotAbn();
					   mrtSmcSpotAbn.setStrDt(startToday);
					   mrtSmcSpotAbn.setEndDt(endToday);
					   mapChartDataDTO.setTotalCnt(mrtSmcSpotAbnMapper.findOneSumVhclTrfvlmByAnlsDt(mrtSmcSpotAbn));
					   //차트데이터
					   List<Map<String,Object>> chartDataList = mrtSmcSpotAbnMapper.findVhclFrfvlTodaysStatistics(mrtSmcSpotAbn);
				
		     			int[] chartArr = new int[24];
		    			
		     			if(!resultList.isEmpty()) {
		      				for(Map<String,Object> resultMap : chartDataList) {
		      					String hoursStr = String.valueOf(resultMap.get("hours"));
		       					int hours = Integer.parseInt(hoursStr);
		     					chartArr[hours] = Integer.parseInt(String.valueOf(resultMap.get("cnt")));
		     				}
		     			}
					   mapChartDataDTO.setChartData(Arrays.toString(chartArr));
					   //동일시간대 비교
					   mrtSmcSpotAbn.setEndDt(now);
					   int todaysCnt = mrtSmcSpotAbnMapper.findOneSumVhclTrfvlmByAnlsDt(mrtSmcSpotAbn);
					   mrtSmcSpotAbn.setStrDt(startYesterDay);
					   mrtSmcSpotAbn.setEndDt(nowTimeYesterDay);
					   int yesterDayCnt = mrtSmcSpotAbnMapper.findOneSumVhclTrfvlmByAnlsDt(mrtSmcSpotAbn);
					   int compareCnt = 0;
					   //전일대비 증가
					   if(todaysCnt == yesterDayCnt) {
						   mapChartDataDTO.setCompareStts("CSC003");
					   } else if(todaysCnt == 0 && yesterDayCnt > 0) {
						   compareCnt = yesterDayCnt;
						   mapChartDataDTO.setCompareStts("CSC001");
					   } else if(todaysCnt > 0 && yesterDayCnt == 0) {
						   compareCnt = todaysCnt;
						   mapChartDataDTO.setCompareStts("CSC000");
					   } else if(todaysCnt > yesterDayCnt) {
						   compareCnt = ((todaysCnt - yesterDayCnt)/yesterDayCnt)*100;
						   mapChartDataDTO.setCompareStts("CSC000");
					   } else {
						   compareCnt = ((yesterDayCnt - todaysCnt)/todaysCnt)*100;
						   mapChartDataDTO.setCompareStts("CSC001");
					   }
					   mapChartDataDTO.setCompareCnt(compareCnt);

					   //현재시각 -1시간 ~ 현재시간 TOP5 리스트 가져오기
					   mrtSmcSpotAbn.setStrDt(oneHoursAgoTime);
					   mrtSmcSpotAbn.setEndDt(now);
					   List<Map<String,Object>> top5MrtSmcSpotAbnList = mrtSmcSpotAbnMapper.findTop5ByAnlsDtOrderByVhclTrfVlm(mrtSmcSpotAbn);

					   mapChartDataDTO.setStartDt(GgitsCommonUtils.getTimeForStringDate(oneHoursAgoTime, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setEndDt(GgitsCommonUtils.getTimeForStringDate(now, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setTableData(top5MrtSmcSpotAbnList);
					   break;
				   case "FTC006":
					   //시간대별 평균 통행 속도
					   mapChartDataDTO.setFnctType("FTC006");
					   MrtSmcSpotAbn mrtSmcSpotAbnI = new MrtSmcSpotAbn();
					   mrtSmcSpotAbnI.setStrDt(startToday);
					   mrtSmcSpotAbnI.setEndDt(endToday);
					   mapChartDataDTO.setTotalCnt(mrtSmcSpotAbnMapper.findOneVclSpeedAvgByAnlsDt(mrtSmcSpotAbnI));
					   //차트데이터
					   List<Map<String,Object>> speedChartDataList = mrtSmcSpotAbnMapper.findSpeedAvgTodaysStatistics(mrtSmcSpotAbnI);
				
		     			int[] speedChartArr = new int[24];
		    			
		     			if(!resultList.isEmpty()) {
		      				for(Map<String,Object> resultMap : speedChartDataList) {
		      					String hoursStr = String.valueOf(resultMap.get("hours"));
		       					int hours = Integer.parseInt(hoursStr);
		       					speedChartArr[hours] = Integer.parseInt(String.valueOf(resultMap.get("cnt")));
		     				}
		     			}
					   mapChartDataDTO.setChartData(Arrays.toString(speedChartArr));
					   //동일시간대 비교
					   mrtSmcSpotAbnI.setEndDt(now);
					   int todaysSpeedCnt = mrtSmcSpotAbnMapper.findOneVclSpeedAvgByAnlsDt(mrtSmcSpotAbnI);
					   mrtSmcSpotAbnI.setStrDt(startYesterDay);
					   mrtSmcSpotAbnI.setEndDt(nowTimeYesterDay);
					   int yesterSpeedDayCnt = mrtSmcSpotAbnMapper.findOneVclSpeedAvgByAnlsDt(mrtSmcSpotAbnI);
					   int compareSpeedCnt = 0;
					   //전일대비 증가
					   if(todaysSpeedCnt == yesterSpeedDayCnt) {
						   mapChartDataDTO.setCompareStts("CSC003");
					   } else if(todaysSpeedCnt == 0 && yesterSpeedDayCnt > 0) {
						   compareCnt = yesterSpeedDayCnt;
						   mapChartDataDTO.setCompareStts("CSC001");
					   } else if(todaysSpeedCnt > 0 && yesterSpeedDayCnt == 0) {
						   compareCnt = todaysSpeedCnt;
						   mapChartDataDTO.setCompareStts("CSC000");
					   } else if(todaysSpeedCnt > yesterSpeedDayCnt) {
						   compareSpeedCnt = todaysSpeedCnt - yesterSpeedDayCnt;
						   mapChartDataDTO.setCompareStts("CSC000");
					   } else {
						   compareSpeedCnt = yesterSpeedDayCnt - todaysSpeedCnt;
						   mapChartDataDTO.setCompareStts("CSC001");
					   }
					   mapChartDataDTO.setCompareCnt(compareSpeedCnt);

					   //현재시각 -1시간 ~ 현재시간 TOP5 리스트 가져오기
					   mrtSmcSpotAbnI.setStrDt(oneHoursAgoTime);
					   mrtSmcSpotAbnI.setEndDt(now);
					   List<Map<String,Object>> top5MrtSmcSpotAbnSpeedList = mrtSmcSpotAbnMapper.findTop5ByAvgVhclSpeedOrderByAvgVhclSpeed(mrtSmcSpotAbnI);

					   try {
						mapChartDataDTO.setStartDt(GgitsCommonUtils.getTimeForStringDate(oneHoursAgoTime, "yyyy-MM-dd HH:mm:ss"));
						mapChartDataDTO.setEndDt(GgitsCommonUtils.getTimeForStringDate(now, "yyyy-MM-dd HH:mm:ss"));
					   } catch (ParseException e) {
						   new CommonException(ErrorCode.DATE_PARSE_ERROR);
					   }
					   mapChartDataDTO.setTableData(top5MrtSmcSpotAbnSpeedList);
					   break;
				   case "FTC007":
					   //시내버스 운행 현황
					   mapChartDataDTO.setFnctType("FTC007");
					   MrtBusTotMoveInfo mrtBusTotMoveInfo = new MrtBusTotMoveInfo();
					   
					   break;
				   case "FTC008":
					   //주요 정체 구간
					   List<Map<String,Object>> delayTop5List = new ArrayList<Map<String,Object>>();
					   mapChartDataDTO.setFnctType("FTC008");
					   String delayTabOption = paramMap.get("delayTabOption") != null ? String.valueOf(paramMap.get("delayTabOption")):"city";
					   
					   	switch(delayTabOption) {
					   	case "city" : 
					   		
					   		break;
					   	case "cross" :
					   		MrtSigCrsdTrfAnal mrtSigCrsdTrfAnal = new MrtSigCrsdTrfAnal();
					   		mrtSigCrsdTrfAnal.setStrDt(startToday);
					   		mrtSigCrsdTrfAnal.setEndDt(now);
					   		
					   		delayTop5List = mrtSigCrsdTrfAnalMapper.findTop5DelayTrfInfo(mrtSigCrsdTrfAnal);
					   		break;
					   	case "link" : 
					   		MrtStdLinkSectnInfo delMrtStdLinkSectnInfo = new MrtStdLinkSectnInfo();
					   		delMrtStdLinkSectnInfo.setStrDt(startToday);
					   		delMrtStdLinkSectnInfo.setEndDt(now);
					   		
					   		delayTop5List = mrtStdLinkSectnInfoMapper.findTop5DelayInfoByAnlsDt(delMrtStdLinkSectnInfo);
					   		break;
					   	}
					   
					   mapChartDataDTO.setTableData(delayTop5List);
					   mapChartDataDTO.setStartDt(GgitsCommonUtils.getTimeForStringDate(startToday, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setEndDt(GgitsCommonUtils.getTimeForStringDate(now, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setTableOption(delayTabOption);
					   break;
				   case "FTC009":
					   //돌발 현황
					   GimsMngInciDetail gimsMngInciDetail = new GimsMngInciDetail();
					   gimsMngInciDetail.setBeginDate(startToday);
					   gimsMngInciDetail.setEndDate(endToday);
					   mapChartDataDTO.setTotalCnt(gimsMngInciDetailMapper.countByBeginDateAndEndDate(gimsMngInciDetail));
					   gimsMngInciDetail.setUpdateCate("TERM");
					   //진행중
					   mapChartDataDTO.setStartCnt(gimsMngInciDetailMapper.countByBeginDateAndEndDateAndNotInUpdateCate(gimsMngInciDetail));
					   //종료
					   mapChartDataDTO.setEndCnt(gimsMngInciDetailMapper.countByBeginDateAndEndDateAndInUpdateCate(gimsMngInciDetail));
					   
					   Map<String, Object> gimsParamMap = new HashMap<String, Object>();
					   gimsParamMap.put("startToday", startToday);
					   gimsParamMap.put("endToday", endToday);
					   
					   //통계 데이터 조회
					   //돌발 상황 비율 
					   Map<String, Object> uneptSitnRate = gimsMngInciDetailMapper.findOneGimsCountInfo(gimsParamMap);
					   mapChartDataDTO.setFnctType("FTC009");
					   mapChartDataDTO.setChartLabel((String)uneptSitnRate.get("uneptSitnRateLabelArray"));
					   mapChartDataDTO.setChartData((String)uneptSitnRate.get("uneptSitnRateDataArray"));
					   //돌발현황 리스트 조회
					   List<Map<String,Object>> gimsMngInciDetailList = gimsMngInciDetailMapper.findTop3GimsMngInciDetailByBeginDateAndEndDate(gimsMngInciDetail);
					   mapChartDataDTO.setTableData(gimsMngInciDetailList);
					   break;
				   case "FTC010":
					   //데이터 수집 장애 알림
					   mapChartDataDTO.setFnctType("FTC010");
					   mapChartDataDTO.setStartDt(GgitsCommonUtils.getTimeForStringDate(startToday, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setEndDt(GgitsCommonUtils.getTimeForStringDate(now, "yyyy-MM-dd HH:mm:ss"));
					   String collTabOption = paramMap.get("collTabOption") != null ? String.valueOf(paramMap.get("collTabOption")):"all";

					   LTcDataLog lTcDataLog = new LTcDataLog();
					   ServerMngType linkedType = null;
					   lTcDataLog.setStrDt(startToday);
					   lTcDataLog.setEndDt(now);
					   lTcDataLog.setPrgrsStts("ERROR"); 
					   
					   if(!"all".equals(collTabOption)) {
						   linkedType = ServerMngType.getServerMngTypeFromCode(collTabOption);
						   List<String> linkedList = LinkedTableInfo.getLinkedTableInfoList(linkedType);
						   if(!linkedList.isEmpty()) {
							   lTcDataLog.setLinkedList(linkedList);
						   }
					   }
					   
					   List<Map<String,Object>> collectList = lTcDataLogMapper.findTop5ByClctStartDtAndEtlClsfAndLinkedList(lTcDataLog);
					   mapChartDataDTO.setTableData(collectList);
					   mapChartDataDTO.setTableOption(collTabOption);
					   
					   break;
				   case "FTC011":
					   //긴급 차량 이동 현황
					   mapChartDataDTO.setFnctType("FTC011");
					   break;
				   default:
					   break;
				   }
				   resultList.add(mapChartDataDTO);
			   }
			   
		   }
	   }
	   
	   return resultList;
   }
   
   public List<Map<String,Object>> getTableData(Map<String,Object> paramMap){
	   List<Map<String,Object>> tableDataList = new ArrayList<Map<String,Object>>();
	   String tableNm = String.valueOf(paramMap.get("tableNm"));
	   String tabType = String.valueOf(paramMap.get("tabType"));
	   
	   String startToday = GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR);
	   String now = GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd HH:mm:ss", Calendar.HOUR);

	   
	   switch(tableNm) {
	   case "communication":
		   //소통 정보
		    switch(tabType) {
		    case "cross" :
		   		MrtSmcSpotAbn crossMrtSmcSpotAbn = new MrtSmcSpotAbn();
		   		crossMrtSmcSpotAbn.setStrDt(startToday);
		   		crossMrtSmcSpotAbn.setEndDt(now);
		   		crossMrtSmcSpotAbn.setOrderByOption("trfvlm");
		   		tableDataList = mrtSmcSpotAbnMapper.findTop5CrossRoadsInfo(crossMrtSmcSpotAbn);
		    	break;
		   	case "link" : 
		   		MrtStdLinkSectnInfo commMrtStdLinkSectnInfo = new MrtStdLinkSectnInfo();
		   		commMrtStdLinkSectnInfo.setStrDt(startToday);
		   		commMrtStdLinkSectnInfo.setEndDt(now);
		   		tableDataList = mrtStdLinkSectnInfoMapper.findTop5ByAnlsDt(commMrtStdLinkSectnInfo);
		   		break;
		   	}
		   break;
	   case "delay":
		   //주요 정체 구간
		   	switch(tabType) {
		   	case "city" : 
		   		
		   		break;
		   	case "cross" :
		   		MrtSigCrsdTrfAnal mrtSigCrsdTrfAnal = new MrtSigCrsdTrfAnal();
		   		mrtSigCrsdTrfAnal.setStrDt(startToday);
		   		mrtSigCrsdTrfAnal.setEndDt(now);
		   		
		   		tableDataList = mrtSigCrsdTrfAnalMapper.findTop5DelayTrfInfo(mrtSigCrsdTrfAnal);
		   		break;
		   	case "link" : 
		   		MrtStdLinkSectnInfo delMrtStdLinkSectnInfo = new MrtStdLinkSectnInfo();
		   		delMrtStdLinkSectnInfo.setStrDt(startToday);
		   		delMrtStdLinkSectnInfo.setEndDt(now);

		   		tableDataList = mrtStdLinkSectnInfoMapper.findTop5DelayInfoByAnlsDt(delMrtStdLinkSectnInfo);
		   		break;
		   	}
		   
		   break;
	   case "collection":
		   //데이터 수집 장애 알림
		   LTcDataLog lTcDataLog = new LTcDataLog();
		   ServerMngType linkedType = null;
		   lTcDataLog.setStrDt(startToday);
		   lTcDataLog.setEndDt(now);
		   lTcDataLog.setPrgrsStts("ERROR"); 
		   if(!"all".equals(tabType)) {
			   linkedType = ServerMngType.getServerMngTypeFromCode(tabType);
			   List<String> linkedList = LinkedTableInfo.getLinkedTableInfoList(linkedType);
			   if(!linkedList.isEmpty()) {
				   lTcDataLog.setLinkedList(linkedList);
			   }
		   }
		   tableDataList = lTcDataLogMapper.findTop5ByClctStartDtAndEtlClsfAndLinkedList(lTcDataLog);
		   break;
	   }
	   return tableDataList;
   }
   
}
