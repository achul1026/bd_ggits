package com.neighbor21.ggits.common.mapper;
import java.util.List;

import com.neighbor21.ggits.common.entity.AdsiVdsColctInfo;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapFacilityMenuDTO;
import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;

@Mapper
public interface AdsiVdsColctInfoMapper {


	/**
	 * VDS별 수집정보 조회
	 * @param vdsId
	 * @return
	 */
	public List<AdsiVdsColctInfo> findRecentListByVdsID(@Param("vdsId") String vdsId);
	
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
	 * @Method Name : findOneAverageEntrainmentSpeedByTimeZoneAndRoad
	 * @작성일 : 2023. 9. 15.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 도로/시간대별 평균동행속도
	 * @param MapMonitoringMenuDTO
	 * @return MapMonitoringMenuDTO
	 */
	public List<MapMonitoringMenuDTO.TrafficInfo> findOneAverageEntrainmentSpeedByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO);
	
	
}
