package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
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
	  * @param mapBigdataSearchDTO 
	 * @Method Name : countAllBusUseStatAnal
	  * @작성일 : 2023. 10. 25.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 빅데이터 분석 > 대중교통 이용현황 분석 > 정류장별 버스 이용률 개수 조회
	  * @param 
	  * @return
	  */
	int countAllBusUseStatAnal(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	  * @param mapBigdataSearchDTO 
	 * @Method Name : findAllBusUseStatsAnal
	  * @작성일 : 2023. 10. 25.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 빅데이터 분석 > 대중교통 이용현황 분석 > 정류장별 버스 이용률 리스트 조회
	  * @param 
	  * @return
	  */
	List<MrtBusSttnAnal> findAllBusUseStatsAnal(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 정류장별 버스 이용량 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();

}
