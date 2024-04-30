package com.neighbor21.ggits.web.service.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.dto.MapLoginStatisticsDTO;
import com.neighbor21.ggits.common.dto.MapMonitoringLinkDataDTO;
import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.dto.MapTotalServiceInfoDTO;
import com.neighbor21.ggits.common.dto.MapUseCaseInfoDTO;
import com.neighbor21.ggits.common.dto.MonitoringTrafficCurDto;
import com.neighbor21.ggits.common.entity.LOpPgmLogn;
import com.neighbor21.ggits.common.entity.LOpUseMenu;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.enums.MapMonitoringSubMenuCd;
import com.neighbor21.ggits.common.mapper.AdsiVdsColctInfoMapper;
import com.neighbor21.ggits.common.mapper.GgitsLinkStd1HMapper;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.mapper.LOpPgmLognMapper;
import com.neighbor21.ggits.common.mapper.LOpUseMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.mapper.MonitoringTrafficCurMapper;
import com.neighbor21.ggits.common.mapper.MrtStdLinkSectnInfoMapper;
import com.neighbor21.ggits.common.mapper.ScsEmrgVhclLogInfoMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

@Service
public class MapMonitoringService{
	
    @Autowired
    AdsiVdsColctInfoMapper adsiVdsColctInfoMapper;
    
    @Autowired
    MOpMenuMapper mOpMenuMapper;
    
    @Autowired
    LOpUseMenuMapper lOpUseMenuMapper;
    
    @Autowired
    MOpCodeMapper mOpCodeMapper;
    
    @Autowired
    LOpPgmLognMapper lOpPgmLognMapper;
    
    @Autowired
    MOpOperatorMapper mOpOperatorMapper;
    
    @Autowired
    MrtStdLinkSectnInfoMapper mrtStdLinkSectnInfoMapper;
    
    @Autowired
    GgitsLinkStd1HMapper ggitsLinkStd1HMapper;
    
    @Autowired
    ScsEmrgVhclLogInfoMapper scsEmrgVhclLogInfoMapper;
    
    @Autowired
    GimsMngInciDetailMapper gimsMngInciDetailMapper;

	@Autowired
	MonitoringTrafficCurMapper monitoringTrafficCurMapper;
    
    
    /**
      * @Method Name : findOneCumulativeTrafficVolumeByTimeZone
      * @작성일 : 2023. 9. 18.
      * @작성자 : NK.KIM
      * @Method 설명 : 누적 교통량 정보 
      * @param mapMonitoringMenuDTO
      * @return
      */
    public MapMonitoringMenuDTO findOneCumulativeTrafficVolumeByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO){
    	
    	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
    	
    	//시간 대별 누적 교통량 상단 데이트
    	MapMonitoringMenuDTO trafficCntInfo = mrtStdLinkSectnInfoMapper.findOneTrafficVolumeCntByTimeZone(mapMonitoringMenuDTO); 
    	
    	if(trafficCntInfo != null) {
    		resultDto.setAverageCnt(trafficCntInfo.getAverageCnt());
    		resultDto.setCompareCnt(trafficCntInfo.getCompareCnt());
    	}
    	
    	//시간 대별 누적 교통량 그래프 데이트
    	MapMonitoringMenuDTO trafficGraphInfo = mrtStdLinkSectnInfoMapper.findOneTrafficVolumeGraphByTimeZone(mapMonitoringMenuDTO);
    	if(trafficGraphInfo != null) {
        	resultDto.setGraphTime(trafficGraphInfo.getGraphTime());
        	resultDto.setGraphCnt(trafficGraphInfo.getGraphCnt());
    	}
    	
    	return resultDto;
    }
    
    /**
     * @Method Name : findOneAverageEntrainmentSpeedByTimeZone
     * @작성일 : 2023. 9. 18.
     * @작성자 : NK.KIM
     * @Method 설명 : 평균 동행 속도 정보 
     * @param mapMonitoringMenuDTO
     * @return
     */
    public MapMonitoringMenuDTO findOneAverageEntrainmentSpeedByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO){
    	
    	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
    	
    	//시간대별 
    	MapMonitoringMenuDTO trafficCntInfo = mrtStdLinkSectnInfoMapper.findOneTrafficAvgSpeedByTimeZone(mapMonitoringMenuDTO); 
    	
    	if(trafficCntInfo != null) {
    		resultDto.setAverageCnt(trafficCntInfo.getAverageCnt());
    		resultDto.setCompareCnt(trafficCntInfo.getCompareCnt());
    	}
    	
    	//시간 대별 누적 교통량 그래프 데이트
    	MapMonitoringMenuDTO trafficGraphInfo = mrtStdLinkSectnInfoMapper.findOneTrafficAvgSpeedGraphByTimeZone(mapMonitoringMenuDTO);
    	if(trafficGraphInfo != null) {
    		resultDto.setGraphTime(trafficGraphInfo.getGraphTime());
    		resultDto.setGraphCnt(trafficGraphInfo.getGraphCnt());
    	}
    	
    	
    	return resultDto;
    }
	
    /**
     * @Method Name : findOneCumulativeTrafficVolumeByRoad
     * @작성일 : 2023. 9. 18.
     * @작성자 : NK.KIM
     * @Method 설명 : 도로별 누적 교통량 정보 
     * @param mapMonitoringMenuDTO
     * @return
     */
    public MapMonitoringMenuDTO findOneCumulativeTrafficVolumeByRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
    	
    	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();

    	//누적 교통량 테이블 데이트
    	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = mrtStdLinkSectnInfoMapper.findAllTrafficVolumeTableByTimeZone(mapMonitoringMenuDTO);
    	if(!trafficTableInfo.isEmpty()) {
    		resultDto.setTrafficList(trafficTableInfo);
    	}
    	
    	return resultDto;
    }
    
    /**
     * @Method Name : findOneCumulativeTrafficVolume
     * @작성일 : 2023. 9. 18.
     * @작성자 : NK.KIM
     * @Method 설명 : 소통 정보
     * @param mapMonitoringMenuDTO
     * @return
     */
    public MapMonitoringMenuDTO findOneCumulativeTrafficVolumeByToday(MapMonitoringMenuDTO mapMonitoringMenuDTO){
    	
    	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();

    	//누적 교통량 테이블 데이트
    	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = mrtStdLinkSectnInfoMapper.findAllTrafficVolumeTableByToday(mapMonitoringMenuDTO);
    	if(!trafficTableInfo.isEmpty()) {
    		resultDto.setTrafficList(trafficTableInfo);
    	}
    	
    	return resultDto;
    }
    
    /**
     * @Method Name : findOneAverageEntrainmentSpeedByRoad
     * @작성일 : 2023. 9. 18.
     * @작성자 : NK.KIM
     * @Method 설명 : 도로별 평균동행속도 
     * @param mapMonitoringMenuDTO
     * @return
     */
    public MapMonitoringMenuDTO findOneAverageEntrainmentSpeedByRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
    	
    	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
    	//평균 동행 속도 테이블 데이트
    	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = mrtStdLinkSectnInfoMapper.findAllTrafficAvgSpeedTableByTimeZone(mapMonitoringMenuDTO);
    	if(!trafficTableInfo.isEmpty()) {
    		resultDto.setTrafficList(trafficTableInfo);
    	}
    	
    	return resultDto;
    }
    
    /**
     * @Method Name : findOneCumulativeTrafficVolumeByTimeZoneAndRoad
     * @작성일 : 2023. 9. 18.
     * @작성자 : NK.KIM
     * @Method 설명 : 도로/시간별 교통량
     * @param mapMonitoringMenuDTO
     * @return
     */
    public MapMonitoringMenuDTO findOneCumulativeTrafficVolumeByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
    	
    	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
    	
    	//평균 동행 속도 테이블 데이트
    	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = mrtStdLinkSectnInfoMapper.findOneCumulativeTrafficVolumeByTimeZoneAndRoad(mapMonitoringMenuDTO);
    	if(!trafficTableInfo.isEmpty()) {
    		resultDto.setTrafficList(trafficTableInfo);
    		int totalCnt = mrtStdLinkSectnInfoMapper.countCumulativeTrafficVolumeByTimeZoneAndRoad(mapMonitoringMenuDTO);
    		resultDto.setTotalCnt(totalCnt);
    	}
    	
    	return resultDto;
    }
    
    /**
     * @Method Name : findOneCumulativeTrafficVolumeByTimeZoneAndRoad
     * @작성일 : 2023. 9. 18.
     * @작성자 : NK.KIM
     * @Method 설명 : 도로/시간대별 평균동행속도
     * @param mapMonitoringMenuDTO
     * @return
     */
    public MapMonitoringMenuDTO findOneAverageEntrainmentSpeedByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
    	
    	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
    	
    	//평균 동행 속도 테이블 데이트
    	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = mrtStdLinkSectnInfoMapper.findOneAverageEntrainmentSpeedByTimeZoneAndRoad(mapMonitoringMenuDTO);
    	if(!trafficTableInfo.isEmpty()) {
    		resultDto.setTrafficList(trafficTableInfo);
    		int totalCnt = mrtStdLinkSectnInfoMapper.countAverageEntrainmentSpeedByTimeZoneAndRoad(mapMonitoringMenuDTO);
    		resultDto.setTotalCnt(totalCnt);
    	}
    	
    	return resultDto;
    }
    
    
    /**
     * @Method Name : getUseCaseInfo
     * @작성일 : 2023. 10. 13.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 -> 서비스 운영현황 -> 유스케이스 항목 접속 현황
     * @return List<MapUseCaseInfoDTO> 
     */
    public List<MapUseCaseInfoDTO> getUseCaseInfo() {
    	List<MapUseCaseInfoDTO> mapUseCaseInfoDTOList = new ArrayList<MapUseCaseInfoDTO>();
    	
    	List<MOpMenu> mOpMenuList = mOpMenuMapper.findAllByUrlPttrn("/bigdata/**");
    	if(!mOpMenuList.isEmpty()) {
    		for(MOpMenu mOpMenu : mOpMenuList) {
    			MapUseCaseInfoDTO mapUseCaseInfoDTO = new MapUseCaseInfoDTO();
    			mapUseCaseInfoDTO.setMenuId(mOpMenu.getMenuId());
     			mapUseCaseInfoDTO.setMenuNm(mOpMenu.getMenuNm());
    			LOpUseMenu lOpUseMenu = new LOpUseMenu();
    			lOpUseMenu.setMenuId(mOpMenu.getMenuId());
     			lOpUseMenu.setStrDt(GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR));
    			lOpUseMenu.setEndDt(GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR));
    			List<Map<String,Object>> resultList = lOpUseMenuMapper.findUseCaseChartInfo(lOpUseMenu);
    			
     			int[] chartArr = new int[24];
    			
     			if(!resultList.isEmpty()) {
      				for(Map<String,Object> resultMap : resultList) {
      					Boolean dataChk = false;
      					String hoursStr = String.valueOf(resultMap.get("hours"));
       					int hours = Integer.parseInt(hoursStr);
     					chartArr[hours] = Integer.parseInt(String.valueOf(resultMap.get("cnt")));
     					
     					if(Integer.parseInt(String.valueOf(resultMap.get("cnt"))) != 0) {
     						dataChk = true;
     					}
     					mapUseCaseInfoDTO.setDataChk(dataChk);
     				}
     			}
    			mapUseCaseInfoDTO.setChartData(Arrays.toString(chartArr));
//    			mapUseCaseInfoDTO.setRenuwalCnt(lOpUseMenuMapper.countByMenuIdAndOccurDt(lOpUseMenu));
//    			mapUseCaseInfoDTO.setRenuwalDt(new Date());
//    			mapUseCaseInfoDTO.setRenuwalMn(60L);
    			mapUseCaseInfoDTOList.add(mapUseCaseInfoDTO);
    		}
    	}
    	return mapUseCaseInfoDTOList;
    }
    
    /**
     * @Method Name : getUserLoginStats
     * @작성일 : 2023. 10. 13.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 -> 서비스 운영현황 -> 시군 이용 대상별 접속 현황
     * @return List<MapLoginStatisticsDTO>
     */
    public List<MapLoginStatisticsDTO> getUserLoginStats(){
    	List<MapLoginStatisticsDTO> mapLoginStatisticsDTOList = new ArrayList<MapLoginStatisticsDTO>();

    	List<MOpCode> admAgencyList = mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD");
    	
    	if(!admAgencyList.isEmpty()) {
    		int colorCnt = 0;
    		for(MOpCode mOpCode : admAgencyList) {
    			LOpPgmLogn lOpPgmLogn = new LOpPgmLogn();
    			lOpPgmLogn.setCdId(mOpCode.getCdId());
    			lOpPgmLogn.setStrDt(GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR));
    			lOpPgmLogn.setEndDt(GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR));
    			lOpPgmLogn.setLogType("ULC001");
    			List<Map<String,Object>> resultList = lOpPgmLognMapper.countByLoginCnt(lOpPgmLogn);
    			if(!resultList.isEmpty()) {
    				String[] colorArr = {"#EA1C07","#EA640C","#F88C15","#FBD01F","#FEEC25","#E2E01C","#9CBD24","#66A530","#118D44"
    						,"#0B8B74","#058BA6","#068FDD","#0696F7","#0B65D3","#0037FF","#3E3DFF","#7300EE","#B801CC","#CD016D",
    						"#D91430","#B0AF58","#BF881C","#95DA5A","#41AC6D","#8F45A1"};
    				MapLoginStatisticsDTO mapLoginStatisticsDTO = new MapLoginStatisticsDTO();
    				mapLoginStatisticsDTO.setCdNm(mOpCode.getCdNm());
         			int[] chartArr = new int[24];
      				for(Map<String,Object> resultMap : resultList) {
      					String hoursStr = String.valueOf(resultMap.get("hours"));
       					int hours = Integer.parseInt(hoursStr);
     					chartArr[hours] = Integer.parseInt(String.valueOf(resultMap.get("cnt")));
     				}
      				//차트 컬러가 25개 까지 있어서 없을때 다시 0번부터 처리
      				if(colorCnt < 24) {
      					colorCnt++;
      				} else {
      					colorCnt = 0;
      				}
      				mapLoginStatisticsDTO.setColor(colorArr[colorCnt]);
      				mapLoginStatisticsDTO.setChartData(Arrays.toString(chartArr).replaceAll("[\\['\\]]",""));
      				mapLoginStatisticsDTOList.add(mapLoginStatisticsDTO);
    			}
    		}
    	}
    	return mapLoginStatisticsDTOList;
    }
    
    /**
     * @Method Name : getTotalServiceInfo
     * @작성일 : 2023. 10. 13.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 -> 서비스 운영현황 -> 전체 서비스 운영 현황
     * @return MapTotalServiceInfoDTO
     */
    public MapTotalServiceInfoDTO getTotalServiceInfo(){
    	MapTotalServiceInfoDTO mapTotalServiceInfoDTO = new MapTotalServiceInfoDTO();
    	mapTotalServiceInfoDTO.setToday(GgitsCommonUtils.getCalculationDateToString(0, "yyyy년 MM월 dd일", Calendar.HOUR));
    	mapTotalServiceInfoDTO.setRenuwalTime(GgitsCommonUtils.getCalculationDateToString(0, "HH시 mm분 ss초", Calendar.HOUR));
    	LOpPgmLogn lOpPgmLogn = new LOpPgmLogn();
		lOpPgmLogn.setLogType("ULC001");
		String today = GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd", Calendar.HOUR);
		lOpPgmLogn.setStrDt(today);
    	mapTotalServiceInfoDTO.setTotalLoginCnt(lOpPgmLognMapper.countAllByLogType(lOpPgmLogn));
    	MOpOperator mOpOperator = new MOpOperator();
    	mOpOperator.setOprtrSttsCd("OSC002");
    	mapTotalServiceInfoDTO.setTotalAdminCnt(mOpOperatorMapper.countAll(mOpOperator));
    	
    	//차트 라벨 만들기 
    	String[] monthArr = new String[5];
    	String[] dayArr = new String[5];
    	for(int i = 0; i < monthArr.length; i++) {
    		monthArr[i] = GgitsCommonUtils.getCalculationDateToString(i*-1,"MM월 dd일", Calendar.DAY_OF_MONTH);
    		dayArr[i] = GgitsCommonUtils.getCalculationDateToString(i*-1,"yyyy-MM-dd", Calendar.DAY_OF_MONTH);
    	}
    	List<String> monthList = Arrays.asList(monthArr);
    	List<String> dayList = Arrays.asList(dayArr);
    	Collections.reverse(monthList);
    	Collections.reverse(dayList);
    	monthArr = monthList.toArray(monthArr);
    	dayArr = dayList.toArray(dayArr);

    	List<MOpCode> admAgencyList = mOpCodeMapper.findAllCodeListByGrpCdId("SGG_CD");
    	List<MapLoginStatisticsDTO> mapLoginStatisticsDTOList = new ArrayList<MapLoginStatisticsDTO>();
		if(!admAgencyList.isEmpty()) {
			int colorCnt = 0;
			for(MOpCode mOpCode : admAgencyList) {
				MapLoginStatisticsDTO mapLoginStatisticsDTO = new MapLoginStatisticsDTO();
				mapLoginStatisticsDTO.setCdNm(mOpCode.getCdNm());
				String[] colorArr = {"#EA1C07","#EA640C","#F88C15","#FBD01F","#FEEC25","#E2E01C","#9CBD24","#66A530","#118D44"
						,"#0B8B74","#058BA6","#068FDD","#0696F7","#0B65D3","#0037FF","#3E3DFF","#7300EE","#B801CC","#CD016D",
						"#D91430","#B0AF58","#BF881C","#95DA5A","#41AC6D","#8F45A1"};
//  				//차트 컬러가 25개 까지 있어서 없을때 다시 0번부터 처리
  				if(colorCnt < 24) {
  					colorCnt++;
  				} else {
  					colorCnt = 0;
  				}
     			int[] chartArr = new int[5];
    			lOpPgmLogn.setCdId(mOpCode.getCdId());
       			lOpPgmLogn.setStrDt(GgitsCommonUtils.getCalculationDateToString(-4, "yyyy-MM-dd 00:00:00", Calendar.DAY_OF_MONTH));
    			lOpPgmLogn.setEndDt(GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR));
       			List<Map<String,Object>> resultList = lOpPgmLognMapper.countByLoginCntDays(lOpPgmLogn);
    			
       			if(!resultList.isEmpty()) {
       				for(int i = 0; i < dayArr.length; i++) {
       					for(Map<String,Object> resultMap : resultList) {
       						String daysStr = String.valueOf(resultMap.get("days"));
       						if(daysStr != null && dayArr[i].equals(daysStr)) {
       							int index = GgitsCommonUtils.getArrIdx(dayArr, daysStr);
       							if(index != -1) {
       								chartArr[index] = Integer.parseInt(String.valueOf(resultMap.get("cnt")));
       							}
       						}
       					}
       				}
       				mapLoginStatisticsDTO.setChartData(Arrays.toString(chartArr).replaceAll("[\\['\\]]",""));
       				mapLoginStatisticsDTO.setColor(colorArr[colorCnt]);
       				mapLoginStatisticsDTOList.add(mapLoginStatisticsDTO);
       			}
			}
		}
 		mapTotalServiceInfoDTO.setChartDataList(mapLoginStatisticsDTOList);
    	mapTotalServiceInfoDTO.setMonthArr(Arrays.toString(monthArr).replaceAll("[\\['\\]]",""));
    	return mapTotalServiceInfoDTO;
    }
    
    public List<MapMonitoringLinkDataDTO> getDataCollectHistory(){
    	
    	List<MapMonitoringLinkDataDTO> mapMonitoringLinkDataList = new ArrayList<MapMonitoringLinkDataDTO>(); 
    	
    	long totalCnt = 0;
    	
    	//소통 정보 쿼리 조회 추가
    	MapMonitoringLinkDataDTO linkDataCntForMonitoring = ggitsLinkStd1HMapper.findOneDataCntForMonitoring();
    	linkDataCntForMonitoring.setJobNm("소통정보");
    	mapMonitoringLinkDataList.add(linkDataCntForMonitoring);
    	long linkDataCnt = linkDataCntForMonitoring.getDataCnt();
    	totalCnt += linkDataCnt;
    	
    	//긴급차량 이동 정보 쿼리 조회 추가
    	MapMonitoringLinkDataDTO scsEmrgDataCntForMonitoring = scsEmrgVhclLogInfoMapper.findOneDataCntForMonitoring();
    	scsEmrgDataCntForMonitoring.setJobNm("긴급차량 이동");
    	mapMonitoringLinkDataList.add(scsEmrgDataCntForMonitoring);
    	long scsDataCnt = scsEmrgDataCntForMonitoring.getDataCnt();
    	totalCnt += scsDataCnt;
    	
    	//돌발현황 쿼리 조회 추가
    	MapMonitoringLinkDataDTO inciDataCntForMonitoring = gimsMngInciDetailMapper.findOneDataCntForMonitoring();
    	inciDataCntForMonitoring.setJobNm("돌발이력");
    	mapMonitoringLinkDataList.add(inciDataCntForMonitoring);
    	long inciDataCnt = inciDataCntForMonitoring.getDataCnt();
    	totalCnt += inciDataCnt;
    	
    	if(mapMonitoringLinkDataList.size() > 0) {
    		mapMonitoringLinkDataList.get(0).setDataTotalCnt(totalCnt);
    	}
    	
    	return mapMonitoringLinkDataList;
    }
    public List<MonitoringTrafficCurDto> findOneCumulativeTrafficVolumeByVhclDiv(){
		return monitoringTrafficCurMapper.findOneCumulativeTrafficVolumeByVhclDiv();
    }
    
	public List<MonitoringTrafficCurDto> getMonitoringChartData(String type, String collectType, String collectTimeType){
		List<MonitoringTrafficCurDto> list = new ArrayList<>();
		switch (MapMonitoringSubMenuCd.getEnum(type)) {
			case CUMULATIVE_TRAFFIC_VOLUME_BY_TIME_ZONE :
				switch (collectType) {
					case "vds" :
						list = monitoringTrafficCurMapper.findAllTrafficVolumeByVDSForChart(collectTimeType);
						break;
					case "dsrc" :
						list = monitoringTrafficCurMapper.findAllTrafficVolumeByDSRCForChart(collectTimeType);
						break;
					case "smc" :
						list = monitoringTrafficCurMapper.findAllTrafficVolumeBySmartForChart(collectTimeType);
						break;
					case "smc-drct-mngcd" :
						list = monitoringTrafficCurMapper.findAllTrafficVolumeBySmartDrctGroupByMngInstCdForChart(collectTimeType);
						break;
				}
				break;
			case AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE :
				switch (collectType) {
					case "vds" :
						list = monitoringTrafficCurMapper.findAllAvgSpeedByVDSForChart(collectTimeType);
						break;
					case "smc" :
						list = monitoringTrafficCurMapper.findAllAvgSpeedBySmartForChart(collectTimeType);
						break;
					case "dsrc" :
						list = monitoringTrafficCurMapper.findAllAvgSpeedByDSRCForChart(collectTimeType);
						break;
					case "smc-drct-mngcd" :
						list = monitoringTrafficCurMapper.findAllAvgSpeedBySmartDrctGroupByMngInstCdForChart(collectTimeType);
						break;
				}
				break;
		}
		return list;
	}
    
}
