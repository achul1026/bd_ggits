package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtBusRoutePrdctnAnls;

@Mapper
public interface MrtBusRoutePrdctnAnlsMapper {

    /**
     * @Method Name : countAllBusRouteOptPrdt
     * @작성일 : 2023. 11. 09.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 예측 분석 > 버스노선 최적화 예측 분석 개수 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	int countAllBusRouteOptPrdt(MapBigdataSearchDTO mapBigdataSearchDTO);

    /**
     * @Method Name : findAllBusRouteOptPrdt
     * @작성일 : 2023. 11. 09.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 예측 분석 > 버스노선 최적화 예측 분석 리스트 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<MrtBusRoutePrdctnAnls> findAllBusRouteOptPrdt(MapBigdataSearchDTO mapBigdataSearchDTO);

    /**
     * @Method Name : findAllBusRoutePrdtList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 예측 분석 > 더 많은 데이터 보기 > 버스노선 최적화 전후비교(거리)
     * @param : 
     * @return
     */
	Map<String, Object> findAllBusRouteLengthRank();

    /**
     * @Method Name : findAllBusRoutePrdtList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 예측 분석 > 더 많은 데이터 보기 > 버스노선 최적화 전후비교(굴곡도)
     * @param : 
     * @return
     */
	Map<String, Object> findAllBusRouteCurvtRank();

    /**
     * @Method Name : findAllBusRoutePrdtList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 예측 분석 > 더 많은 데이터 보기 > 버스노선 최적화 전후비교(평가점수)
     * @param : 
     * @return
     */
	Map<String, Object> findAllBusRouteScoreRank();
}
