package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtBusRouteDetAnal;

@Mapper
public interface MrtBusRouteDetAnalMapper {
	/**
     * @param mapBigdataSearchDTO 
	 * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 수용성 및 굴곡도 분석 개수 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	int countAllPubTrfRouteReciveCurveList(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 수용성 및 굴곡도 분석 리스트 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<MrtBusRouteDetAnal> findAllPubTrfRouteReciveCurveList(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 수용성 및 굴곡도 분석 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();
}
