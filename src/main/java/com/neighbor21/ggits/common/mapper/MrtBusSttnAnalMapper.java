package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusSttnAnal;

@Mapper
public interface MrtBusSttnAnalMapper {
	/**
	  * @Method Name : countBusSttnAnalList
	  * @작성일 : 2023. 10. 24.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 정류장별 버스노선, 버스유형 리스트 개수 조회
	  * @param commonEntity
	  * @return
	  */
	int countBusSttnAnalList(CommonEntity commonEntity);

	/**
	  * @Method Name : findAllBusSttnInfoList
	  * @작성일 : 2023. 10. 24.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 정류장별 버스노선, 버스유형 리스트 개수 조회
	  * @param paramMap
	  * @return
	  */
	List<MrtBusSttnAnal> findAllBusSttnInfoList(CommonEntity commonEntity);

	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 정류장별 버스 이용량 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();


	List<MrtBusSttnAnal> findAllByStationId(@Param("stationId") String stationId, @Param("searchYear") String searchYear, @Param("searchPeriod") String searchPeriod, @Param("searchTime") String searchTime);

	/**
     * @Method Name : findAllBusStatsList
     * @작성일 : 2023. 11. 02.
     * @작성자 : KC.KIM
     * @Method 설명 : 정류장별 버스노선, 버스유형 통계 데이터 조회
     * @param : commonEntity
     * @return
     */
	Map<String, Object> findAllBusStatsList(CommonEntity commonEntity);

	/**
	 * @Method Name : findMaxRideYmd
	 * @작성일 : 2023. 01. 04.
	 * @작성자 : KY.LEE
	 * @Method 설명 : RideYmd 최대 날짜 조회
	 */	
	String findMaxRideYmd();

	/**
    * @Method Name : findBusStationUsageInit
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 버스정류장 이용량
    */	
	List<MrtBusSttnAnal> findBusStationUsageInit(String rideYmd);

}
