package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TimeInoutflExmnDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssTimeInoutflExmn;

@Mapper
public interface TrfIpcssTimeInoutflExmnMapper {

	/**
	 * @Method Name : saveTimeInoutflExmn
	 * @작성일 : 2023. 11. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 조사 시트 저장
	 * @param timeInoutflExmn
	 */
	void saveTimeInoutflExmn(TrfIpcssTimeInoutflExmn timeInoutflExmn);

	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

	/**
	 * @Method Name : findAllTimeInoutflExmnList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 조사 리스트 조회
	 * @param commonEntity
	 */
	List<TimeInoutflExmnDTO> findAllTimeInoutflExmnList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findOneUsgCdNmList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 조사 용도명칭 리스트
	 * @param trfvlmStatisticsDTO
	 */
	Map<String, Object> findOneUsgCdNmList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);
	
	/**
	 * @Method Name : findAllChartData
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 조사 통계 데이터 조회
	 * @param trfvlmStatisticsDTO
	 */
	Map<String, Object> findAllChartData(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllInoutCntSumList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 합계 데이터 조회
	 * @param paramMap
	 */
	TrfIpcssTimeInoutflExmn findAllInoutCntSumList(Map<String, Object> paramMap);

	/**
	 * @Method Name : findAllInoutRateSumList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 합계 데이터 조회
	 * @param paramMap
	 */
	TrfIpcssTimeInoutflExmn findAllInoutRateSumList(Map<String, Object> paramMap);
}
