package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtStdLinkSectnInfo;
import com.neighbor21.ggits.openapi.request.TrafficAnalysisRequest;
import com.neighbor21.ggits.openapi.request.TrafficSpeedByTimezoneRequest;
import com.neighbor21.ggits.openapi.request.TrafficVolumeByTimezoneRequest;
import com.neighbor21.ggits.openapi.response.TrafficAnalysisResponse;
import com.neighbor21.ggits.openapi.response.TrafficSpeedByTimezoneResponse;
import com.neighbor21.ggits.openapi.response.TrafficVolumeByTimezoneResponse;

@Mapper
public interface MrtStdLinkSectnInfoMapper {
    /**
     * @Method Name : countTrafficInfoLogList
     * @작성일 : 2023. 9. 27.
     * @작성자 : KC.KIM
     * @Method 설명 : 이력 집계 > 교통정보 이력집계 수 조회
     * @param : commonEntity
     * @return
     */
	int countTrafficInfoLogList(CommonEntity commonEntity);

    /**
     * @Method Name : findAllTrafficInfoLogList
     * @작성일 : 2023. 9. 27.
     * @작성자 : KC.KIM
     * @Method 설명 : 이력 집계 > 교통정보 이력집계 리스트 조회
     * @param : commonEntity
     * @return
     */
	List<MrtStdLinkSectnInfo> findAllTrafficInfoLogList(CommonEntity commonEntity);

	/**
	 * @Method Name : findTop5ByAnlsDt
	 * @작성일 : 2023. 10. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 링크별 교통량 TOP5 조회
	 * @param : MrtStdLinkSectnInfo
	 * @return
	 */
	List<Map<String,Object>> findTop5ByAnlsDt(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);

	/**
	 * @Method Name : findTop5DelayInfoByAnlsDt
	 * @작성일 : 2023. 10. 26.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 > 주요 정체구간 TOP5 조회
	 * @param : MrtStdLinkSectnInfo
	 * @return
	 */
	List<Map<String,Object>> findTop5DelayInfoByAnlsDt(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);
	
	

	/**
	 * 모니터링 최근 도로 소통정보 조회
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtStdLinkSectnInfo> findAllRecent();
	
	/**
	  * @Method Name : findOneTrafficVolumeCntByTimeZone
	  * @작성일 : 2023. 9. 15.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 시간대별 누적 교통량 및 전일 동시간 대비 비율
	  * @param MapMonitoringMenuDTO
	  * @return MapMonitoringMenuDTO
	  */
	public MapMonitoringMenuDTO findOneTrafficVolumeCntByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : findOneTrafficAvgSpeedByTimeZone
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시간대별 평균 동행속도
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public MapMonitoringMenuDTO findOneTrafficAvgSpeedByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	  * @Method Name : findOneTrafficVolumeGraphByTimeZone
	  * @작성일 : 2023. 9. 15.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 시간대별 교통량 그래프 데이터
	  * @param MapMonitoringMenuDTO
	  * @return MapMonitoringMenuDTO
	  */
	public MapMonitoringMenuDTO findOneTrafficVolumeGraphByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : findOneTrafficAvgSpeedGraphByTimeZone
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 평균 동행속도 그래프 데이터
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public MapMonitoringMenuDTO findOneTrafficAvgSpeedGraphByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : findAllTrafficVolumeTableByTimeZone
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시간대별 교통량 그래프 데이터
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public List<MapMonitoringMenuDTO.TrafficInfo> findAllTrafficVolumeTableByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : findAllTrafficVolumeTableByToday
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 소통정보(현재 날짜)
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public List<MapMonitoringMenuDTO.TrafficInfo> findAllTrafficVolumeTableByToday(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : findAllTrafficAvgSpeedTableByTimeZone
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시간대별 교통량 그래프 데이터
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public List<MapMonitoringMenuDTO.TrafficInfo> findAllTrafficAvgSpeedTableByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : findOneCumulativeTrafficVolumeByTimeZoneAndRoad
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 도로/시간대별 교통량
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public List<MapMonitoringMenuDTO.TrafficInfo> findOneCumulativeTrafficVolumeByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : countCumulativeTrafficVolumeByTimeZoneAndRoad
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 도로/시간대별 교통량 목록 개수
	 * @param MapMonitoringMenuDTO
	 * @return int
	 */
	public int countCumulativeTrafficVolumeByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	/**
	 * @Method Name : findOneAverageEntrainmentSpeedByTimeZoneAndRoad
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 도로/시간대별 평균동행속도
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public List<MapMonitoringMenuDTO.TrafficInfo> findOneAverageEntrainmentSpeedByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	/**
	 * @Method Name : countAverageEntrainmentSpeedByTimeZoneAndRoad
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 도로/시간대별 평균동행속도 개수 
	 * @param MapMonitoringMenuDTO
	 * @return int
	 */
	public int countAverageEntrainmentSpeedByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO);

	/**
	 * @Method Name : findVhclFrfvlTodaysStatistics
	 * @작성일 : 2023. 11. 7.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 시간별 누적 교통량 -> 차트
	 * @param MrtStdLinkSectnInfo
	 * @return Map<String,Object>
	 */
	public List<Map<String, Object>> findVhclFrfvlTodaysStatistics(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);
	/**
	 * @Method Name : findSpeedAvgTodaysStatistics
	 * @작성일 : 2023. 11. 7.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 시간별 평균속도 -> 차트
	 * @param MrtStdLinkSectnInfo
	 * @return Map<String,Object>
	 */
	public List<Map<String, Object>> findSpeedAvgTodaysStatistics(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);

	/**
	 * @Method Name : findTop5ByAnlsDtOrderByVhclTrfVlm
	 * @작성일 : 2023. 11. 7.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 시간별 누적 교통량 -> TOP보기
	 * @param MrtStdLinkSectnInfo
	 * @return Map<String,Object>
	 */
	public List<Map<String, Object>> findTop5ByAnlsDtOrderByVhclTrfVlm(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);

	/**
	 * @Method Name : findTop5ByAvgVhclSpeedOrderByAvgVhclSpeed
	 * @작성일 : 2023. 11. 7.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 시간별 평균 속도 -> TOP보기
	 * @param MrtStdLinkSectnInfo
	 * @return Map<String,Object>
	 */
	public List<Map<String, Object>> findTop5ByAvgVhclSpeedOrderByAvgVhclSpeed(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);
	
	/**
	 * @Method Name : findOneSumVhclTrfvlmByAnlsDt
	 * @작성일 : 2023. 11. 7.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 시간별 누적 교통량 -> 전체건수
	 * @param MrtStdLinkSectnInfo
	 * @return Map<String,Object>
	 */
	public int findOneSumVhclTrfvlmByAnlsDt(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);

	/**
	 * @Method Name : findOneSumVhclTrfvlmByAnlsDt
	 * @작성일 : 2023. 11. 7.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 시간대별 평균 속도 -> 평균속도
	 * @param MrtStdLinkSectnInfo
	 * @return Map<String,Object>
	 */
	public int findOneVclSpeedAvgByAnlsDt(MrtStdLinkSectnInfo mrtStdLinkSectnInfo);
	
	/**
	 * @Method Name : findTrafficVolumeByTimezone
	 * @작성일 : 2023. 12. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 OPEN API -> 시간대별 교통량 정보
	 * @param TrafficVolumeByTimezoneRequest
	 * @return List<TrafficVolumeByTimezoneResponse>
	 */
	public List<TrafficVolumeByTimezoneResponse> findTrafficVolumeByTimezone(TrafficVolumeByTimezoneRequest trafficVolumeByTimezoneRequest);

	/**
	 * @Method Name : findTrafficSpeedByTimezone
	 * @작성일 : 2023. 12. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 OPEN API -> 시간대별 평균 속도
	 * @param TrafficSpeedByTimezoneRequest
	 * @return List<TrafficSpeedByTimezoneResponse>
	 */
	public List<TrafficSpeedByTimezoneResponse> findTrafficSpeedByTimezone(TrafficSpeedByTimezoneRequest trafficSpeedByTimezoneRequest);

	/**
	 * @Method Name : findTrafficAnalysis
	 * @작성일 : 2023. 12. 11.
	 * @작성자 : KY.LEE
	 * @Method 설명 OPEN API -> 교통정보 분석 데이터
	 * @param TrafficAnalysisRequest
	 * @return List<TrafficAnalysisResponse>
	 */
	public List<TrafficAnalysisResponse> findTrafficAnalysis(TrafficAnalysisRequest trafficAnalysisRequest);


}
