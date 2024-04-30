package com.neighbor21.ggits.web.service.monitoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.dto.MonitoringDashboardDTO;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.entity.MOpLayoutMstInfo;
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.entity.MrtBusSttnAnal;
import com.neighbor21.ggits.common.entity.MrtDynmcPopltnCell500Rslt;
import com.neighbor21.ggits.common.entity.MrtEvcPassAnal;
import com.neighbor21.ggits.common.entity.MrtTrfAcdntDngrPrdctn;
import com.neighbor21.ggits.common.entity.MrtTrfHlctcCngstnSctn;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclPathLog;
import com.neighbor21.ggits.common.enums.LayoutMenuInfo;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.mapper.MOpLayoutMstInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.mapper.MrtBusSttnAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtDynmcPopltnCell500RsltMapper;
import com.neighbor21.ggits.common.mapper.MrtEvcPassAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfAcdntDngrPrdctnMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfHlctcCngstnSctnMapper;
import com.neighbor21.ggits.common.mapper.ScsEmrgVhclPathLogMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;

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
   GimsMngInciDetailMapper gimsMngInciDetailMapper;
	
	@Autowired
	AdsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper adsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper;
	
	@Autowired
	MrtTrfAcdntDngrPrdctnMapper mrtTrfAcdntDngrPrdctnMapper;
	
	@Autowired
	MrtDynmcPopltnCell500RsltMapper mrtDynmcPopltnCell500RsltMapper;
	
	@Autowired
	MrtBusSttnAnalMapper mrtBusSttnAnalMapper;
	
	@Autowired 
	MrtEvcPassAnalMapper mrtEvcPassAnalMapper;
	
	@Autowired 
	ScsEmrgVhclPathLogMapper scsEmrgVhclPathLogMapper;
	
	@Autowired 
	MrtTrfHlctcCngstnSctnMapper mrtTrfHlctcCngstnSctnMapper;
	
	
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
   
   
	/**
	 * @Method Name : getSmcrdTop10Info
	 * @작성일 : 2023. 01. 04.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 스마트교차로 교차로별 top 10 
	 */	
   public List<AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur> getSmcrdTop10Info(){
	   return adsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper.findSmcrdTop10Info();
   }

   /**
    * @Method Name : getSvcCongestionTop10
    * @작성일 : 2023. 01. 23.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 도로별 주요 정체구간 TOP 10 
    */	
   public List<MrtTrfHlctcCngstnSctn> getSvcCongestionTop10(MrtTrfHlctcCngstnSctn mrtTrfHlctcCngstnSctn){
	   return mrtTrfHlctcCngstnSctnMapper.findSvcCongestionTop10(mrtTrfHlctcCngstnSctn);
   }
   
   /**
    * @Method Name : getSmcrdTop10Info
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 사고예측구간 지수 top 10
    */	
   public List<MrtTrfAcdntDngrPrdctn> getAcdntPredictionTop10Info(){
	   return mrtTrfAcdntDngrPrdctnMapper.findAcdntPredictionTop10Info();
   }
   
   /**
    * @Method Name : getPopulationPredictionTop10
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 유동인구 밀집 예측 TOP 10
    */	
   public List<MrtDynmcPopltnCell500Rslt> getPopulationPredictionTop10(){
	   List<MrtDynmcPopltnCell500Rslt> list = new ArrayList<>();
	   list = mrtDynmcPopltnCell500RsltMapper.findPopulationPredictionTop10();
	   if(list.isEmpty())
		   list = mrtDynmcPopltnCell500RsltMapper.findPopulationPredictionTop10Max();
	   return list;
   }

   /**
    * @Method Name : getBusStationUsageInit
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 버스정류장 이용량
    */	
   public List<MrtBusSttnAnal> getBusStationUsageInit(String rideYmd){
	   return mrtBusSttnAnalMapper.findBusStationUsageInit(rideYmd);
   }

   /**
    * @Method Name : getWarningByMnginstcd
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 돌발 수집원별 수
    */	
   public List<GimsMngInciDetail> getWarningByMnginstcd(){
	   return gimsMngInciDetailMapper.findWarningByMnginstcd();
   }

   /**
    * @Method Name : getWarningByMnginstcd
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 긴급차량
    */	
   public Map<String,Object> getEmergAcheivePtg(){
	   Map<String,Object> result = new HashMap<String,Object>();
	   
	   List<MrtEvcPassAnal> emergAcheivePtg = mrtEvcPassAnalMapper.findEmergAcheivePtgV2();
		   if(!emergAcheivePtg.isEmpty()) {
//			   int compareCnt = 100;
//			   double totalCnt = emergAcheivePtg.size();
//			   double goalCnt = 0;

			   String[] evnoArr = new String[emergAcheivePtg.size()];
			   String[] firenameArr = new String[emergAcheivePtg.size()];
			   int[] avgSrvcTimeArr = new int[emergAcheivePtg.size()];
			   int[] avgArvlPrnmntTimeArr = new int[emergAcheivePtg.size()];
			   int[] differnceTimeArr = new int[emergAcheivePtg.size()];
			   for(int i = 0; i < emergAcheivePtg.size(); i++) {
				   evnoArr[i] = emergAcheivePtg.get(i).getEvno();
				   firenameArr[i] = emergAcheivePtg.get(i).getFirename();
				   avgSrvcTimeArr[i] = emergAcheivePtg.get(i).getAvgSrvcTime().intValue();
				   avgArvlPrnmntTimeArr[i] = emergAcheivePtg.get(i).getAvgArvlPrnmntTime().intValue();
				   differnceTimeArr[i] = emergAcheivePtg.get(i).getDiffernceTime().intValue();
//				   if("Y".equals(emergAcheivePtg.get(i).getGoalYn())) {
//					   goalCnt++;
//				   }
			   }
//  			   if(goalCnt > 0) {
//				   compareCnt = (int) ((goalCnt/totalCnt)*100);
//			   }
			   
			   result.put("avgDifferentTime", mrtEvcPassAnalMapper.findAvgDifferentTimeForToday());
			   result.put("evnoArr", Arrays.toString(evnoArr).replaceAll("[\\['\\]]",""));
			   result.put("firenameArr", Arrays.toString(firenameArr).replaceAll("[\\['\\]]",""));
			   result.put("avgSrvcTimeArr", Arrays.toString(avgSrvcTimeArr).replaceAll("[\\['\\]]",""));
			   result.put("avgArvlPrnmntTimeArr", Arrays.toString(avgArvlPrnmntTimeArr).replaceAll("[\\['\\]]",""));
			   result.put("differnceTimeArr", Arrays.toString(differnceTimeArr).replaceAll("[\\['\\]]",""));
		   }

	   return result;
   }
   
   /**
    * @Method Name : getEmergByMnginstcd
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 지자체별 긴급차량 운행 현황 목록 조회
    * @return List<ScsEmrgVhclPathLog> 
    */	
   public List<ScsEmrgVhclPathLog> getEmergByMnginstcd(){
	   return scsEmrgVhclPathLogMapper.findEmergByMnginstcd();
   }

   /**
    * @Method Name : getEmergByMnginstcd
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 지자체별 긴급차량 운행 현황 목록 조회
    * @return List<ScsEmrgVhclPathLog> 
    */	
   public List<Map<String,Object>> getEmergByMnginstcdList(){
	   return scsEmrgVhclPathLogMapper.findEmergByMnginstcdList();
   }
   
   
   /**
    * @Method Name : getEmergByMnginstcdChartDataInfo
    * @작성일 : 2023. 01. 19.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 지자체별 긴급차량 운행 현황 차트 정보 조회
    * @return Map<String,Object>
    */	   
   public Map<String,Object> getEmergByMnginstcdChartDataInfo(){
		return scsEmrgVhclPathLogMapper.findEmergByMnginstcdChartDataInfo();
   }
   
   
}
