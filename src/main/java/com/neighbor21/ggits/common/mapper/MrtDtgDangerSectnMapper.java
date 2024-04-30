package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import com.neighbor21.ggits.common.entity.TsLogDriveanal;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtDtgDangerSectn;

@Mapper
public interface MrtDtgDangerSectnMapper {
	
	/**
     * @Method Name : countBusDtgInfo
     * @작성일 : 2023. 9. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 버스 DTG 개수 조회
     * @return
     */
	int countBusDtgInfo(CommonEntity commonEntity);

	/**
     * @Method Name : findAllBusDtgInfoList
     * @작성일 : 2023. 9. 18.
     * @작성자 : KC.KIM
     * @Method 설명 : 버스 DTG 리스트 조회
     * @return
     */
	List<MrtDtgDangerSectn> findAllBusDtgInfoList(CommonEntity commonEntity);

	/**
	 * 맵용 대중교통 운영 위험구간 분석 정보 조회
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<TsLogDriveanal> findAllBySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
     * @Method Name : countAllPubTrfSafeDrvAnal
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 대중교통 안전 운행분석 개수 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	int countAllPubTrfSafeDrvAnal(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
     * @Method Name : findAllPubTrfSafeDrvAnal
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 대중교통 안전 운행분석 리스트 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<MrtDtgDangerSectn> findAllPubTrfSafeDrvAnal(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 10. 25.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 대중교통 안전 운행분석 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();

	/**
     * @Method Name : findAllPubTrfDagrFrecRank
     * @작성일 : 2023. 11. 01.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석
     * @param : mapBigdataSearchDTO
     * @return
     */
	Map<String, Object> findAllPubTrfDagrFrecRank(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
     * @Method Name : findAllPubTrfDagrFrecRankList
     * @작성일 : 2023. 11. 01.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<Map<String, Object>> findAllPubTrfDagrFrecRankList(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * @Method Name : findBusDtgData
	 * @작성일 : 2023. 11. 02.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 대중교통 지표 총괄 통계 > 버스 DTG 통계정보 조회
	 * @param : commonEntity
	 * @return
	 */
	Map<String, Object> findOneBusDangrStatsMap(Map<String, Object> paramMap);


	/**
	 * 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 차트용
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtDtgDangerSectn> findByRouteIdGroupRoadNameAndHHForChart(MapBigdataSearchDTO mapBigdataSearchDTO);

}
