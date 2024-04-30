package com.neighbor21.ggits.web.service.map;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mvel2.util.ArrayTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.api.module.bigdata.BDDangerZoneComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.dto.MapChartDataDTO;
import com.neighbor21.ggits.common.entity.TaasAcdntDstrctMaster;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcSpotAbnMapper;
import com.neighbor21.ggits.common.mapper.MrtStdLinkSectnInfoMapper;
import com.neighbor21.ggits.common.mapper.TaasAcdntDstrctMasterMapper;
import com.neighbor21.ggits.common.mapper.UticRoadDngrSttsFrcstMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

@Service
public class MapBigDataService {
	
	@Autowired
	MrtSmcSpotAbnMapper mrtSmcSpotAbnMapper;
	
	@Autowired
	MrtStdLinkSectnInfoMapper mrtStdLinkSectnInfoMapper;
	
	@Autowired
	GimsMngInciDetailMapper gimsMngInciDetailMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	BDDangerZoneComponent bDDangerZoneComponent;
	
	@Autowired
	TaasAcdntDstrctMasterMapper taasAcdntDstrctMasterMapper;
	
	@Autowired
	UticRoadDngrSttsFrcstMapper uticRoadDngrSttsFrcstMapper;
	
	/**
     * @Method Name : setSearchDateInfo
     * @작성일 : 2023. 10. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 검색 일자 정보 세팅 
     * @param mapBigdataSearchDTO
     * @return
	 * @throws ParseException 
     */
	public MapBigdataSearchDTO setSearchDateInfo(MapBigdataSearchDTO mapBigdataSearchDTO) throws ParseException {
		
    	String startToday = "";
		String endToday = "";
		
		if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getStartDate())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(mapBigdataSearchDTO.getStartDate(), "startDate");
			if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getStartTime())) {
				int startTime = Integer.parseInt(mapBigdataSearchDTO.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday, startTime, "yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
		}
    	
		if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getEndDate())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(mapBigdataSearchDTO.getEndDate(), "endDate");			
			if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getEndTime())) {
				int endTime = Integer.parseInt(mapBigdataSearchDTO.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday, endTime, "yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
		}
		
		mapBigdataSearchDTO.setStartDate(startToday);
		mapBigdataSearchDTO.setEndDate(endToday);
		
		return mapBigdataSearchDTO;
	}
	
	public MapChartDataDTO getTrafficPttrnInfoData(Map<String,Object> paramMap) {
		MapChartDataDTO mapChartDataDTO = new MapChartDataDTO();
		String dataCtgry = String.valueOf(paramMap.get("dataCtgry"));
		String dataOption = paramMap.get("dataOption") != null ? String.valueOf(paramMap.get("dataOption")):"city";
		String title = "";
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		switch(dataCtgry) {
			//교통량 순위
			case "trfVlm":
				title = "교통량 순위 보기";
				mapChartDataDTO.setSubTitle("trfVlm");
				switch(dataOption) {
				case "city" :
					mapChartDataDTO.setTableOption("city");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlm(null);
					break;
				case "town":
					mapChartDataDTO.setTableOption("town");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlmTown();
					break;
				case "cross":
					mapChartDataDTO.setTableOption("cross");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlmCross();
					break;
				case "link":
					mapChartDataDTO.setTableOption("link");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlmLink();
					break;
				}
				break;
			//평균 속도 순위
			case "speed":
				mapChartDataDTO.setSubTitle("speed");
				title = "평균 속도 순위 보기";
				switch(dataOption) {
				case "city" :
					mapChartDataDTO.setTableOption("city");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlm(null);
					break;
				case "town":
					mapChartDataDTO.setTableOption("town");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlmTown();
					break;
				case "cross":
					mapChartDataDTO.setTableOption("cross");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlmCross();
					break;
				case "link":
					mapChartDataDTO.setTableOption("link");
					resultList = mrtSmcSpotAbnMapper.findTop5SumVhclTrfVlmLink();
					break;
				}
				break;
			//정체 구간 순위
			case "delay":
				mapChartDataDTO.setSubTitle("delay");
				title = "정체 구간 순위 보기";
				switch(dataOption) {
				case "city" :
					mapChartDataDTO.setTableOption("city");
					resultList = mrtSmcSpotAbnMapper.findTop5ByMrtSmcSpotAbnInfo();
					break;
				case "town":
					mapChartDataDTO.setTableOption("town");
					resultList = mrtSmcSpotAbnMapper.findTop5ByMrtSmcSpotAbnInfo();
					break;
				case "cross":
					mapChartDataDTO.setTableOption("cross");
					resultList = mrtSmcSpotAbnMapper.findTop5ByMrtSmcSpotAbnInfo();
					break;
				case "link":
					mapChartDataDTO.setTableOption("link");
					resultList = mrtSmcSpotAbnMapper.findTop5ByMrtSmcSpotAbnInfo();
					break;
				}
				break;
		}
		mapChartDataDTO.setTitle(title);
		
		int[] chartArr = new int[5];
		int[] chartArr2 = new int[5];
		String[] labelArr = {"","","","",""};
		if(!resultList.isEmpty()) {
			for(int i = 0; i < resultList.size(); i++) {
				String label = String.valueOf(resultList.get(i).get("adsiNm"));
				int chartTrfVlmData = Integer.parseInt(String.valueOf(resultList.get(i).get("vhclTrfvlm")));
				int chartSpeedData = Integer.parseInt(String.valueOf(resultList.get(i).get("avgVhclSpeed")));
				
				chartArr[i] = chartTrfVlmData;
				chartArr2[i] = chartSpeedData;
				labelArr[i] = label;
			}
		}
		
		mapChartDataDTO.setChartData(Arrays.toString(chartArr).replaceAll("[\\['\\]]",""));
		mapChartDataDTO.setChartData2(Arrays.toString(chartArr2).replaceAll("[\\['\\]]",""));
		mapChartDataDTO.setChartLabel(Arrays.toString(labelArr).replaceAll("[\\['\\]]",""));
		
		mapChartDataDTO.setTableData(resultList);

		return mapChartDataDTO;
	}
	
	public MapChartDataDTO getTrafficAnalysisDelayInfo(Map<String,Object> paramMap) {
		MapChartDataDTO mapChartDataDTO = new MapChartDataDTO();
		String dataOption = String.valueOf(paramMap.get("dataOption"));
		String dataType = String.valueOf(paramMap.get("dataType"));

		switch(dataOption) {
		case "city":
			break;
		case "town":
			break;
		case "cross":
			break;
		case "link":
			break;
		}
		return mapChartDataDTO;
	}
	
	public MapChartDataDTO getTrafficAnalysisEmergencyInfo(Map<String,Object> paramMap) {
		MapChartDataDTO mapChartDataDTO = new MapChartDataDTO();
		String dataOption = String.valueOf(paramMap.get("dataOption"));
		String dataType = String.valueOf(paramMap.get("dataType"));
		return mapChartDataDTO;
	}
	
	public MapChartDataDTO getDangerZoneInfo(Map<String,Object> paramMap) {
		MapChartDataDTO mapChartDataDTO = new MapChartDataDTO();
		String dataCtgry = String.valueOf(paramMap.get("dataCtgry"));
		String dataOption = String.valueOf(paramMap.get("dataOption"));
// 		String dataType = String.valueOf(paramMap.get("dataType"));
 		Map<String,Object> chartDataMap = new HashMap<String,Object>();
 		
 		String[] chartLabel = {"","","",""};
		
		switch(dataCtgry) {
		//돌발
		case "outbreak":
			
			int[] notSafetyChartData = new int[4];
			int[] speedingChartData = new int[4];
			int[] sharpCurveChartData = new int[4];
			int[] scarpChartData = new int[4];
			int[] slipperyChartData = new int[4];
			int[] floodingChartData = new int[4];
			int[] rainChartData = new int[4];
			int[] snowChartData = new int[4];
			int[] frostRoadChartData = new int[4];
			
			//돌발 많은 상위 4개 시군 별 조회
			
			List<Map<String,Object>> dangerZoneList = uticRoadDngrSttsFrcstMapper.findTop4DangerZoneStatisticsCity("경기도");
			
			if(!dangerZoneList.isEmpty()) {
				for(int i = 0; i < dangerZoneList.size(); i++) {
					String roadNmAddr = String.valueOf(dangerZoneList.get(i).get("roadNmAddr"));
					int notSafetyCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("notSafetyCnt")));
					int speedingCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("speedingCnt")));
					int sharpCurveCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("sharpCurveCnt")));
					int scarpCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("scarpCnt")));
					int slipperyCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("slipperyCnt")));
					int floodingCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("floodingCnt")));
					int rainCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("rainCnt")));
					int snowCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("snowCnt")));
					int frostRoadCnt = Integer.parseInt(String.valueOf(dangerZoneList.get(i).get("frostRoadCnt")));
					
					chartLabel[i] = roadNmAddr;
					notSafetyChartData[i] = notSafetyCnt;
					speedingChartData[i] = speedingCnt;
					sharpCurveChartData[i] = sharpCurveCnt;
					scarpChartData[i] = scarpCnt;
					slipperyChartData[i] = slipperyCnt;
					floodingChartData[i] = floodingCnt;
					rainChartData[i] = rainCnt;
					snowChartData[i] = snowCnt;
					frostRoadChartData [i] = frostRoadCnt;
				}
			}
		
 			chartDataMap.put("chartLabel", Arrays.toString(chartLabel).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("notSafetyChartData", Arrays.toString(notSafetyChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("speedingChartData", Arrays.toString(speedingChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("sharpCurveChartData", Arrays.toString(sharpCurveChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("scarpChartData", Arrays.toString(scarpChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("slipperyChartData", Arrays.toString(slipperyChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("floodingChartData", Arrays.toString(floodingChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("rainChartData", Arrays.toString(rainChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("snowChartData", Arrays.toString(snowChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("frostRoadChartData", Arrays.toString(frostRoadChartData).replaceAll("[\\['\\]]",""));
 			
 			mapChartDataDTO.setObjectData(chartDataMap);
			break;
			
		//사고
		case "accident":
			int[] bcyclChartData = new int[4];
			int[] jaywkChartData = new int[4];
			int[] lawvltnChartData = new int[4];
			int[] hldyChartData = new int[4];
			int[] frostChartData = new int[4];
			int[] twhlvhChartData = new int[4];
			int[] pdsnChartData = new int[4];
			int[] drnkgChartData = new int[4];
			int[] truckChartData = new int[4];
			int[] olmanChartData = new int[4];
			int[] childChartData = new int[4];
			
			MapBigdataSearchDTO mapBigdataSearchDTO = new MapBigdataSearchDTO();
			mapBigdataSearchDTO.setLimit(4L);
			mapBigdataSearchDTO.setDataOption(dataOption);
			
			List<Map<String,Object>> top4AccidentCityList = taasAcdntDstrctMasterMapper.findAccidentCntAdstdgCd(mapBigdataSearchDTO);
			
			if("city".equals(dataOption)) {
				if(!top4AccidentCityList.isEmpty()){
					for(int i = 0; i < top4AccidentCityList.size(); i++) {
						String searchLocation = String.valueOf(top4AccidentCityList.get(i).get("adstdgCd"));
						String adstdgNm = String.valueOf(top4AccidentCityList.get(i).get("adstdgNm"));
						mapBigdataSearchDTO.setSearchLocation(searchLocation);
						
						List<TaasAcdntDstrctMaster> accidentList = taasAcdntDstrctMasterMapper.findAcdntStatisticsByAdstdgCd(mapBigdataSearchDTO);
						 
						if(!accidentList.isEmpty()) {
							chartLabel[i] = adstdgNm;
							for(TaasAcdntDstrctMaster taasAcdntDstrctMaster : accidentList) {
								switch(taasAcdntDstrctMaster.getType()) {
								//자전거 사고 구역
								case "BCYCL" :
									bcyclChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//무단횡단 보행자 사고 구역
								case "JAYWK" :
									jaywkChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//법위반 보행자 사고 구역
								case "LAWVLTN" :
									lawvltnChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//휴일기간 보행자 사고 구역
								case "HLDY" :
									hldyChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//결빙사고 사고 구역
								case "FROST" :
									frostChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//이륜차 사고 구역
								case "TWHLVH" :
									twhlvhChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//보행자 사고 구역
								case "PDSN" :
									pdsnChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//음주 사고 구역
								case "DRNKG" :
									drnkgChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//화물차 사고 구역
								case "TRUCK" :
									truckChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//노인 보행자 사고 구역
								case "OLMAN" :
									olmanChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								case "CHILD" :
									childChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								}
							}
						}
					}
				}
			} else if("town".equals(dataOption)) {
				if(!top4AccidentCityList.isEmpty()){
					for(int i = 0; i < top4AccidentCityList.size(); i++) {
						String searchLocation = String.valueOf(top4AccidentCityList.get(i).get("adstdgCd"));
						String adstdgNm = String.valueOf(top4AccidentCityList.get(i).get("adstdgNm"));
						mapBigdataSearchDTO.setSearchLocation(searchLocation);
						mapBigdataSearchDTO.setSearchLocationNm(adstdgNm);
						
						List<TaasAcdntDstrctMaster> accidentList = taasAcdntDstrctMasterMapper.findAcdntStatisticsByAdstdgCd(mapBigdataSearchDTO);
						 
						if(!accidentList.isEmpty()) {
							chartLabel[i] = adstdgNm;
							for(TaasAcdntDstrctMaster taasAcdntDstrctMaster : accidentList) {
								switch(taasAcdntDstrctMaster.getType()) {
								//자전거 사고 구역
								case "BCYCL" :
									bcyclChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//무단횡단 보행자 사고 구역
								case "JAYWK" :
									jaywkChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//법위반 보행자 사고 구역
								case "LAWVLTN" :
									lawvltnChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//휴일기간 보행자 사고 구역
								case "HLDY" :
									hldyChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//결빙사고 사고 구역
								case "FROST" :
									frostChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//이륜차 사고 구역
								case "TWHLVH" :
									twhlvhChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//보행자 사고 구역
								case "PDSN" :
									pdsnChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//음주 사고 구역
								case "DRNKG" :
									drnkgChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//화물차 사고 구역
								case "TRUCK" :
									truckChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								//노인 보행자 사고 구역
								case "OLMAN" :
									olmanChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								case "CHILD" :
									childChartData[i] += taasAcdntDstrctMaster.getAcdntCnt().intValue();
									break;
								}
							}
						}
					}
				}
			}
			
			chartDataMap.put("chartLabel", Arrays.toString(chartLabel).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("bcyclChartData", Arrays.toString(bcyclChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("jaywkChartData", Arrays.toString(jaywkChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("lawvltnChartData", Arrays.toString(lawvltnChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("hldyChartData", Arrays.toString(hldyChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("frostChartData", Arrays.toString(frostChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("twhlvhChartData", Arrays.toString(twhlvhChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("pdsnChartData", Arrays.toString(pdsnChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("drnkgChartData", Arrays.toString(drnkgChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("truckChartData", Arrays.toString(truckChartData).replaceAll("[\\['\\]]",""));
 			chartDataMap.put("olmanChartData", Arrays.toString(olmanChartData).replaceAll("[\\['\\]]",""));
			chartDataMap.put("childChartData", Arrays.toString(childChartData).replaceAll("[\\['\\]]",""));
 			
 			mapChartDataDTO.setObjectData(chartDataMap);
 			
			break;
		} 
		return mapChartDataDTO;
	}
}
