package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssEtcTrfvlm;

@Mapper
public interface TrfIpcssEtcTrfvlmMapper {

	/**
	 * @Method Name : saveEtcTrfvlm
	 * @작성일 : 2023. 11. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 보행,자전거,PM 교통량 시트 저장
	 * @param strsctTrfvlm
	 */
	void saveEtcTrfvlm(TrfIpcssEtcTrfvlm etcTrfvlm);
	
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

	/**
	 * @Method Name : findAllPointNmList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 보행,자전거,PM 교통량 지점 명 조회
	 * @param commonEntity
	 */
	List<TrfIpcssEtcTrfvlm> findAllPointNmList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllPointNmList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 지점별, 시간대별 보행,자전거,PM 교통량 상세 조회
	 * @param etcTrfvlmMap
	 */
	List<TrfIpcssEtcTrfvlm> findOneEtcTrfvlmInfo(TrfIpcssEtcTrfvlm trfIpcssEtcTrfvlm);

	/**
	 * @Method Name : findallChartData
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 지점별, 시간대별 보행,자전거,PM 교통량 통계 데이터 조회
	 * @param headerInfo
	 */
	Map<String, Object> findallChartData(TrfIpcssEtcTrfvlm headerInfo);

	String findExmnYmd(TrfvlmStatisticsDTO trfvlmStatisticsDTO);
}
