package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusRoutePasngAnal;

@Mapper
public interface MrtBusRoutePasngAnalMapper {

	/**
	 * @Method Name : findAllBusRoutePasngMapper
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 노선구간별 승하차/재차 승객수 리스트 조회
	 * @param commonEntity
	 */
	public List<MrtBusRoutePasngAnal> findAllBusRoutePasngMapper(CommonEntity commonEntity);

	/**
	 * @Method Name : findAllRouteUserRankList
	 * @작성일 : 2023. 10. 31.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 승하차/재차 승객수 집계
	 * @param mapBigdataSearchDTO
	 */
	public Map<String, Object> findAllRouteUserRankList(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
	 * @Method Name : findAllDataYears
	 * @작성일 : 2023. 11. 07.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 승하차/재차 승객수 조회 연도 조회
	 * @param 
	 */
	public List<Map<String, Object>> findAllDataYears();

	/**
	 * @Method Name : findAllPubTrfRouteUserCnt
	 * @작성일 : 2023. 11. 07.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 빅데이터 분석 > 대중교통 노선별 분석 > 노선구간별 승하차/재차 승객수 조회 리스트 조회
	 * @param : mapBigdataSearchDTO
	 * @return : 
	 */
	public List<MrtBusRoutePasngAnal> findAllPubTrfRouteUserCnt(MapBigdataSearchDTO mapBigdataSearchDTO);

	public Integer countPubTrfRouteUserCnt(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * @Method Name : countBusRoutePasng
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 노선구간별 승하차/재차 승객수 리스트 개수 조회
	 * @param commonEntity
	 */
	public int countBusRoutePasng(CommonEntity commonEntity);

	public List<MrtBusRoutePasngAnal> findAllByRideStationId(MapBigdataSearchDTO mapBigdataSearchDTO);

	public List<MrtBusRoutePasngAnal> findAllLndiCntByRouteId(MapBigdataSearchDTO mapBigdataSearchDTO);
	public List<MrtBusRoutePasngAnal> findAllCntByAllByRouteId(MapBigdataSearchDTO mapBigdataSearchDTO);

	public List<MrtBusRoutePasngAnal> findAllLndiCntByAll(MapBigdataSearchDTO mapBigdataSearchDTO);

}
