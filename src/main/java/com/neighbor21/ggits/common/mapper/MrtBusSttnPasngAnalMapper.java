package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusSttnPasngAnal;

@Mapper
public interface MrtBusSttnPasngAnalMapper {

    /**
     * @Method Name : countBusSttnPasngList
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 정류장별 승하차 승객 리스트 개수 조회
     * @param : commonEntity
     * @return
     */
	int countBusSttnPasngList(CommonEntity commonEntity);

    /**
     * @Method Name : findAllBusSttnPasngList
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 정류장별 승하차 승객 리스트 조회
     * @param : commonEntity
     * @return
     */
	List<MrtBusSttnPasngAnal> findAllBusSttnPasngList(CommonEntity commonEntity);

    /**
     * @Method Name : countAllRegionTransStats
     * @작성일 : 2023. 10. 27.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 위험운영 구간 분석 > 권역별 환승현황 분석 개수 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	int countAllRegionTransStats(MapBigdataSearchDTO mapBigdataSearchDTO);

    /**
     * @Method Name : findAllRegionTransStats
     * @작성일 : 2023. 10. 27.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통정보 통계 분석 > 대중교통 위험운영 구간 분석 > 권역별 환승현황 분석 리스트 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<MrtBusSttnPasngAnal> findAllRegionTransStats(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 권역별 환승현황 분석 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();

}
