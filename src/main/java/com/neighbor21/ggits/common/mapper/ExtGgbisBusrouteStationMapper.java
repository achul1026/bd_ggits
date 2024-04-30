package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.ExtGgbisBusrouteStation;

@Mapper
public interface ExtGgbisBusrouteStationMapper {

	/**
     * @Method Name : findAllSttnPbTrfUseStatsAnsl
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 기종점 대중교통 이용량 개수 조회
     * @param : 
     * @return
     */
	Integer countAllSttnPbTrfUseStatsAnal(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
     * @Method Name : findAllSttnPbTrfUseStatsAnsl
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 기종점 대중교통 이용량 리스트 조회
     * @param : 
     * @return
     */
	List<ExtGgbisBusrouteStation> findAllSttnPbTrfUseStatsAnsl(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
     * @Method Name : findAllStationNmListBySearchContent
     * @작성일 : 2023. 11. 13.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 출발지,도착지 정보 자동완성
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllStationNmListBySearchContent(Map<String, Object> paramMap);

}
