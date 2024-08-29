package com.neighbor21.ggits.web.service.monitoring;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.dto.MapChartDataDTO;
import com.neighbor21.ggits.common.dto.MonitoringDashboardDTO;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.entity.MOpLayoutMstInfo;
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.MrtSigCrsdTrfAnal;
import com.neighbor21.ggits.common.entity.MrtSmcSpotAbn;
import com.neighbor21.ggits.common.entity.MrtStdLinkSectnInfo;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclLogInfo;
import com.neighbor21.ggits.common.enums.LayoutMenuInfo;
import com.neighbor21.ggits.common.enums.LinkedTableInfo;
import com.neighbor21.ggits.common.enums.ServerMngType;
import com.neighbor21.ggits.common.mapper.GgsplBusPeriodicinfoCurMapper;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.mapper.LTcDataLogMapper;
import com.neighbor21.ggits.common.mapper.MOpLayoutMstInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.mapper.MrtSigCrsdTrfAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcAbnLosMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcSpotAbnMapper;
import com.neighbor21.ggits.common.mapper.MrtStdLinkSectnInfoMapper;
import com.neighbor21.ggits.common.mapper.ScsEmrgVhclCurInfoMapper;
import com.neighbor21.ggits.common.mapper.ScsEmrgVhclLogInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;

@Service
public class MonitoringDashboardService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
	
	@Autowired
	ScsEmrgVhclLogInfoMapper scsEmrgVhclLogInfoMapper;
	
	@Autowired
	ScsEmrgVhclCurInfoMapper scsEmrgVhclCurInfoMapper;
	
	@Autowired
	GgsplBusPeriodicinfoCurMapper ggsplBusPeriodicinfoCurMapper;
	
	
   public MonitoringDashboardDTO getUserLayoutInfo(){
	   MonitoringDashboardDTO monitoringDashboardDTO = new MonitoringDashboardDTO();
	   
	   int layoutCnt = mOpLayoutMstInfoMapper.countByOprtrId(LoginSessionUtils.getOprtrId());
	   
	   if(layoutCnt == 0) {
			//레이아웃 디폴트값 추가
			List<LayoutMenuInfo> layOutMenuList = Stream.of(LayoutMenuInfo.values()).collect(Collectors.toList());

			for(LayoutMenuInfo layoutMenuInfo : layOutMenuList) {
			   MOpMenu mOpMenu = new MOpMenu();
			   mOpMenu.setMenuPttrnType(layoutMenuInfo.getCode());
			   String menuId = mOpMenuMapper.findOneMenuIdByMenuPttrnType(mOpMenu);
			   
			   MOpLayoutMstInfo mOpLayoutMstInfo = new MOpLayoutMstInfo();
			   if(!GgitsCommonUtils.isNull(menuId)) {
				   mOpLayoutMstInfo.setMenuId(menuId);
			   } else {
				   mOpLayoutMstInfo.setMenuId(layoutMenuInfo.getCode());
			   }
			   mOpLayoutMstInfo.setLayoutId(GgitsCommonUtils.getUuid(10));
			   mOpLayoutMstInfo.setOprtrId(LoginSessionUtils.getOprtrId());
			   mOpLayoutMstInfo.setLayoutMenuNm(layoutMenuInfo.getName());
			   mOpLayoutMstInfo.setLayoutSttsCd("1");
			   mOpLayoutMstInfo.setLayout1UseYn("Y");
			   mOpLayoutMstInfo.setLayout2UseYn("N");
			   mOpLayoutMstInfo.setLayout3UseYn("N");
			   mOpLayoutMstInfo.setFnctType(layoutMenuInfo.getFncType());
			   mOpLayoutMstInfo.setDataTypeCd(layoutMenuInfo.getDataTypeCd());
			   
			   mOpLayoutMstInfoMapper.saveMOpLayoutMstInfo(mOpLayoutMstInfo);
			}
	   }
	   
//	   String[] menuPttnTypeArr = {"EVC000","EVC001","EVC002","EVC004","EVC007"};
	   String[] menuCtgryTypeArr = {"MCT000","MCT001"}; //MCT000 경기도 통합 교통 현황, MCT001수집시스템 운영 현황
//	   MOpMenu mOpMenu = new MOpMenu();
//	   mOpMenu.setMenuPttrnTypeArr(menuPttnTypeArr);
//	   List<String> menuIdList = mOpMenuMapper.findMenuIdByMenuPttrnType(mOpMenu);
	   MOpLayoutMstInfo mOpLayoutMstInfo = new MOpLayoutMstInfo();
//	   mOpLayoutMstInfo.setMenuIdList(menuIdList);
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
		   dbMOpOperator.setLayoutNo(Long.parseLong(layoutNo));
		   mOpOperatorMapper.update(dbMOpOperator);
	   }
   }
   
   public List<MapChartDataDTO> getChartDataInfo(Map<String,Object> paramMap) {
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
					   try {
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
					   } catch(ParseException e) {
						   logger.info("교차로 및 구간 소통정보 에러발생");
					   } catch(CommonException e) {
						   logger.info("교차로 및 구간 소통정보 에러발생");
					   }
					   break;
				   case "FTC005":
					   try {
					   //시간대별 누적 교통량
					   mapChartDataDTO.setFnctType("FTC005");
					   MrtStdLinkSectnInfo trfVlmMrtStdLinkSectnInfo = new MrtStdLinkSectnInfo();
					   trfVlmMrtStdLinkSectnInfo.setStrDt(startToday);
					   trfVlmMrtStdLinkSectnInfo.setEndDt(endToday);
					   mapChartDataDTO.setTotalCnt(mrtStdLinkSectnInfoMapper.findOneSumVhclTrfvlmByAnlsDt(trfVlmMrtStdLinkSectnInfo));
					   //차트데이터
					   List<Map<String,Object>> chartDataList = mrtStdLinkSectnInfoMapper.findVhclFrfvlTodaysStatistics(trfVlmMrtStdLinkSectnInfo);
				
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
					   trfVlmMrtStdLinkSectnInfo.setEndDt(now);
					   int todaysCnt = mrtStdLinkSectnInfoMapper.findOneSumVhclTrfvlmByAnlsDt(trfVlmMrtStdLinkSectnInfo);
					   trfVlmMrtStdLinkSectnInfo.setStrDt(startYesterDay);
					   trfVlmMrtStdLinkSectnInfo.setEndDt(nowTimeYesterDay);
					   int yesterDayCnt = mrtStdLinkSectnInfoMapper.findOneSumVhclTrfvlmByAnlsDt(trfVlmMrtStdLinkSectnInfo);
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
					   trfVlmMrtStdLinkSectnInfo.setStrDt(oneHoursAgoTime);
					   trfVlmMrtStdLinkSectnInfo.setEndDt(now);
					   List<Map<String,Object>> top5MrtStdLinkSectnInfoList = mrtStdLinkSectnInfoMapper.findTop5ByAnlsDtOrderByVhclTrfVlm(trfVlmMrtStdLinkSectnInfo);

					   mapChartDataDTO.setStartDt(GgitsCommonUtils.getTimeForStringDate(oneHoursAgoTime, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setEndDt(GgitsCommonUtils.getTimeForStringDate(now, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setTableData(top5MrtStdLinkSectnInfoList);
					   } catch(ParseException e) {
						   logger.info("시간대별 누적 교통량 에러발생");
					   } catch(CommonException e) {
						   logger.info("시간대별 누적 교통량 에러발생");
					   }
					   break;
				   case "FTC006":
					   try{
					   //시간대별 평균 통행 속도
					   mapChartDataDTO.setFnctType("FTC006");
					   
					   MrtStdLinkSectnInfo speedMrtStdLinkSectnInfo = new MrtStdLinkSectnInfo();
					   speedMrtStdLinkSectnInfo.setStrDt(startToday);
					   speedMrtStdLinkSectnInfo.setEndDt(endToday);
					   mapChartDataDTO.setTotalCnt(mrtStdLinkSectnInfoMapper.findOneVclSpeedAvgByAnlsDt(speedMrtStdLinkSectnInfo));
					   //차트데이터
					   List<Map<String,Object>> speedChartDataList = mrtStdLinkSectnInfoMapper.findSpeedAvgTodaysStatistics(speedMrtStdLinkSectnInfo);
				
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
					   speedMrtStdLinkSectnInfo.setEndDt(now);
					   int todaysSpeedCnt = mrtStdLinkSectnInfoMapper.findOneVclSpeedAvgByAnlsDt(speedMrtStdLinkSectnInfo);
					   speedMrtStdLinkSectnInfo.setStrDt(startYesterDay);
					   speedMrtStdLinkSectnInfo.setEndDt(nowTimeYesterDay);
					   int yesterSpeedDayCnt = mrtStdLinkSectnInfoMapper.findOneVclSpeedAvgByAnlsDt(speedMrtStdLinkSectnInfo);
					   int compareSpeedCnt = 0;
					   //전일대비 증가
					   if(todaysSpeedCnt == yesterSpeedDayCnt) {
						   mapChartDataDTO.setCompareStts("CSC003");
					   } else if(todaysSpeedCnt == 0 && yesterSpeedDayCnt > 0) {
						   compareSpeedCnt = yesterSpeedDayCnt;
						   mapChartDataDTO.setCompareStts("CSC001");
					   } else if(todaysSpeedCnt > 0 && yesterSpeedDayCnt == 0) {
						   compareSpeedCnt = todaysSpeedCnt;
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
					   speedMrtStdLinkSectnInfo.setStrDt(oneHoursAgoTime);
					   speedMrtStdLinkSectnInfo.setEndDt(now);
					   List<Map<String,Object>> top5MrtStdLinkSectnInfoSpeedList = mrtStdLinkSectnInfoMapper.findTop5ByAvgVhclSpeedOrderByAvgVhclSpeed(speedMrtStdLinkSectnInfo);

						mapChartDataDTO.setStartDt(GgitsCommonUtils.getTimeForStringDate(oneHoursAgoTime, "yyyy-MM-dd HH:mm:ss"));
						mapChartDataDTO.setEndDt(GgitsCommonUtils.getTimeForStringDate(now, "yyyy-MM-dd HH:mm:ss"));
					   mapChartDataDTO.setTableData(top5MrtStdLinkSectnInfoSpeedList);
					   } catch (ParseException e) {
						   logger.info("시간대별 평균 통행 속도 에러 발생");
					   } catch (CommonException e) {
						   logger.info("시간대별 평균 통행 속도 에러 발생");
					   }
					   break;
				   case "FTC007":
					   try {
						   //시내버스 운행 현황
						   mapChartDataDTO.setFnctType("FTC007");
						   
						   int busRunningCnt = ggsplBusPeriodicinfoCurMapper.countAllRealTimeBusMoveInfo();
						   
						   //운행중
						   mapChartDataDTO.setStartCnt(busRunningCnt);
						   List<Map<String,Object>> chartDataMap = ggsplBusPeriodicinfoCurMapper.findRealTimeBusMoveInfoByCity();
						   if(chartDataMap != null &&!chartDataMap.isEmpty()) {
							   String[] chartLabelArr = new String[chartDataMap.size()];
							   String[] chartIdArr = new String[chartDataMap.size()];
							   int[] chartDataArr = new int[chartDataMap.size()];
							   
							   for(int i = 0; i < chartDataMap.size(); i++) {
			      					String chartLabel = String.valueOf(chartDataMap.get(i).get("adminNm"));
			      					String chartId = String.valueOf(chartDataMap.get(i).get("cdId"));
			      					String chartData = String.valueOf(chartDataMap.get(i).get("cnt"));
			      					chartLabelArr[i] = chartLabel;
			      					chartIdArr[i] = chartId;
			      					chartDataArr[i] = Integer.parseInt(chartData);
							   }
							   
							   mapChartDataDTO.setChartData(Arrays.toString(chartDataArr).replaceAll("[\\['\\]]",""));
							   mapChartDataDTO.setChartData2(Arrays.toString(chartIdArr).replaceAll("[\\['\\]]",""));
							   mapChartDataDTO.setChartLabel(Arrays.toString(chartLabelArr).replaceAll("[\\['\\]]",""));
						   }
					   } catch (CommonException e) {
						   logger.info("시내버스 운행현황 에러 발생");
					   }
					   break;
				   case "FTC008":
					   try {
					   //주요 정체 구간
					   List<Map<String,Object>> delayTop5List = new ArrayList<Map<String,Object>>();
					   mapChartDataDTO.setFnctType("FTC008");
					   String delayTabOption = paramMap.get("delayTabOption") != null ? String.valueOf(paramMap.get("delayTabOption")):"city";
					   
					   	switch(delayTabOption) {
					   	case "city" : 
					   		MrtSmcSpotAbn cityMrtSmcSpotAbn = new MrtSmcSpotAbn();
					   		cityMrtSmcSpotAbn.setStrDt(startToday);
					   		cityMrtSmcSpotAbn.setEndDt(now);
					   		
					   		delayTop5List = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlm(cityMrtSmcSpotAbn);
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
					   } catch (ParseException e) {
						   logger.info("주요 정체 구간 에러 발생");
					   } catch (CommonException e) {
						   logger.info("주요 정체 구간 에러 발생");
					   }
					   break;
				   case "FTC009":
					   try {
					   mapChartDataDTO.setFnctType("FTC009");
					   //돌발 현황
					   GimsMngInciDetail gimsMngInciDetail = new GimsMngInciDetail();
					   gimsMngInciDetail.setStrDt(startToday);
					   gimsMngInciDetail.setEndDt(endToday);
					   
					   Map<String,Object> waringChartData = gimsMngInciDetailMapper.countByMonitoringWaringInfo(gimsMngInciDetail);
					   int wrRunningCnt = Integer.parseInt(String.valueOf(waringChartData.get("runningCnt")));
					   int wrCompleteCnt = Integer.parseInt(String.valueOf(waringChartData.get("completeCnt")));
					   int wrTotalCnt = Integer.parseInt(String.valueOf(waringChartData.get("totalCnt")));
					   
					   //진행중
					   mapChartDataDTO.setStartCnt(wrRunningCnt);
					   //완료
					   mapChartDataDTO.setEndCnt(wrCompleteCnt);
					   //합계
					   mapChartDataDTO.setTotalCnt(wrTotalCnt);
					   
					   //통계 데이터 조회
					   //돌발 상황 비율 
					   Map<String, Object> uneptSitnRate = gimsMngInciDetailMapper.findByMonitoringChartData(gimsMngInciDetail);
					   mapChartDataDTO.setChartLabel((String)uneptSitnRate.get("uneptSitnRateLabelArray"));
					   mapChartDataDTO.setChartData((String)uneptSitnRate.get("uneptSitnRateDataArray"));
					   
					   //돌발발생 현황 리스트 조회
					   List<Map<String,Object>> wrTableData = gimsMngInciDetailMapper.findWarningTabInfo(gimsMngInciDetail); 
					    mapChartDataDTO.setTableData(wrTableData);
					   } catch (CommonException e) {
						   logger.info("돌발현황 에러 발생");
					   }
					   break;
				   case "FTC010":
					   try {
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
					   } catch (ParseException e) {
						   logger.info("데이터 수집장애 이력 에러 발생");
					   } catch (CommonException e) {
						   logger.info("데이터 수집장애 이력 에러 발생");
					   }
					   break;
				   case "FTC011":
					   try {
					   //긴급 차량 이동 현황
					   mapChartDataDTO.setFnctType("FTC011");
					   
					   ScsEmrgVhclLogInfo scsEmrgVhclLogInfo = new ScsEmrgVhclLogInfo();
					   scsEmrgVhclLogInfo.setStrDt(startToday);
					   scsEmrgVhclLogInfo.setEndDt(endToday);
					   
					   Map<String,Object> emrgChartData = scsEmrgVhclLogInfoMapper.findOneByChartDataForMonitoringDashboard(scsEmrgVhclLogInfo);
					   int runningCnt = Integer.parseInt(String.valueOf(emrgChartData.get("runningCnt")));
					   int completeCnt = Integer.parseInt(String.valueOf(emrgChartData.get("completeCnt")));
					   int totalCnt = Integer.parseInt(String.valueOf(emrgChartData.get("totalCnt")));
					   
					   //이동중
					   mapChartDataDTO.setStartCnt(runningCnt);
					   //종료
					   mapChartDataDTO.setEndCnt(completeCnt);
					   //합계
					   mapChartDataDTO.setTotalCnt(totalCnt);
					   
					   String chartLabel = "이동중,이동완료";
					   
					   mapChartDataDTO.setChartLabel(chartLabel);
					   mapChartDataDTO.setChartData(runningCnt+","+completeCnt);

					   //긴급차량 운행 현황 리스트 조회
					   List<Map<String,Object>> tableData = scsEmrgVhclCurInfoMapper.findTopTableDataForMonitoringDashboard();  
					    mapChartDataDTO.setTableData(tableData);
					   } catch (CommonException e) {
						   logger.info("데이터 수집장애 이력 에러 발생");
					   }
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
		   		MrtSmcSpotAbn cityMrtSmcSpotAbn = new MrtSmcSpotAbn();
		   		cityMrtSmcSpotAbn.setStrDt(startToday);
		   		cityMrtSmcSpotAbn.setEndDt(now);
		   		
		   		tableDataList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlm(cityMrtSmcSpotAbn);
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
