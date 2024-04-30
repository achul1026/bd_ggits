package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TimePassDistrbDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssTimePassDistrb;

@Mapper
public interface TrfIpcssTimePassDistrbMapper {

	/**
	 * @Method Name : saveTimePassDistrb
	 * @작성일 : 2023. 11. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 통행 분포비 시트 저장
	 * @param timePassDistrb
	 */
	void saveTimePassDistrb(TrfIpcssTimePassDistrb timePassDistrb);

	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

	/**
	 * @Method Name : findAllTimePassDistrbList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 통행 분포비 리스트 조회
	 * @param commonEntity
	 */
	List<TimePassDistrbDTO> findAllTimePassDistrbList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllTimePassDistrbList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 통행 분포비 용도 리스트
	 * @param commonEntity
	 */
	Map<String, Object> findOneUsgCdNmList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllChartData
	 * @작성일 : 2023. 11. 22.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 통행 분포비 용도별, 교통 사용별 통계 데이터 조회
	 * @param commonEntity
	 */
	Map<String, Object> findAllChartDataForDwell(Map<String, Object> paramMap);

	/**
	 * @Method Name : findAllChartData
	 * @작성일 : 2023. 11. 22.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 통행 분포비 용도별, 교통 사용별 통계 데이터 조회
	 * @param commonEntity
	 */
	Map<String, Object> findAllChartDataForNonDwell(Map<String, Object> paramMap);

	List<String> findOneUsgCdNmListForChart(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	TrfIpcssTimePassDistrb findOneBizInfo(TrfvlmStatisticsDTO trfvlmStatisticsDTO);


}
