package com.neighbor21.ggits.api.module.monitoring;

import java.util.ArrayList;
import java.util.List;

import com.neighbor21.ggits.common.dto.MonitoringTrafficCurDto;
import com.neighbor21.ggits.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1m;

/**
 * 모니터링 교통현황 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  MTrafficInfoComponent
 * @since : 2023-09-04
 */
@Component
public class MTrafficComponent extends BaseMapDataComponent {


    @Autowired
    MrtSmcTrfPatMapper mrtSmcTrfPatMapper;

	@Autowired
	ExtGgitsLinkStd1mMapper extGgitsLinkStd1mMapper;

    @Autowired
    MrtSigCrsdTrfAnalMapper mrtSigCrsdTrfAnalMapper;
    
    @Autowired
    MrtStdLinkSectnInfoMapper mrtStdLinkSectnInfoMapper;
    
    @Autowired
    AdsiVdsColctInfoMapper adsiVdsColctInfoMapper;

	@Autowired
	MonitoringTrafficCurMapper monitoringTrafficCurMapper;

	static List<ExtGgitsLinkStd1m> savedTrafficInfo = new ArrayList<>();
	static List<ExtGgitsLinkStd1m> savedTrafficInfoLowerRoadRank = new ArrayList<>();

    /**
     * 실시간 지/정체 도로 정보 조회
     * @return
     */
    public List<ExtGgitsLinkStd1m> getRealtimeTrafficInfo(String minimize){
		List<ExtGgitsLinkStd1m> list = null;
		if(minimize.equals("true")){
			/*if(savedTrafficInfoLowerRoadRank == null || savedTrafficInfoLowerRoadRank.isEmpty()){
				savedTrafficInfoLowerRoadRank = extGgitsLinkStd1mMapper.findAllByRecentLowerRoadRank();
			}*/
			list = savedTrafficInfoLowerRoadRank;
		}else{
			/*if(savedTrafficInfo == null || savedTrafficInfo.isEmpty()){
				savedTrafficInfo = extGgitsLinkStd1mMapper.findAllByRecent();
			}*/
			list = savedTrafficInfo;
		}
		return list;
    }

	/**
	 * 실시간 스마트교차로 교통량 조회
	 * @return
	 */
	public List<MonitoringTrafficCurDto> getRealtimeVolumeSmart(){
		return monitoringTrafficCurMapper.getVolumeSmartForGIS();
	}

	/**
	 * 실시간 스마트교차로 방향별 교통량 조회
	 * @return
	 */
	public List<MonitoringTrafficCurDto> getRealtimeVolumeSmartDrct(){
		return monitoringTrafficCurMapper.getVolumeSmartDcrtForGIS();
	}

	/**
	 * 실시간 VDS 교통량 조회
	 * @return
	 */
	public List<MonitoringTrafficCurDto> getRealtimeVolumeVDS(){
		return monitoringTrafficCurMapper.getVolumeVDSForGIS();
	}

	public void setSavedTrafficInfo(List<ExtGgitsLinkStd1m> data) {
		savedTrafficInfo = data;
	}

	public void setSavedTrafficInfoLowerRoadRank(List<ExtGgitsLinkStd1m> data) {
		savedTrafficInfoLowerRoadRank = data;
	}
    
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
   	}
   	
   	return resultDto;
   }
}
