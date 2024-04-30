package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsGraphDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssCrsrdTrfvlm;

@Mapper
public interface TrfIpcssCrsrdTrfvlmMapper {

	/**
	 * @Method Name : saveCrsrdTrfvlm
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 평일 주말 교통량 조사 저장
	 * @param crsrdTrfvlm
	 */
	void saveCrsrdTrfvlm(TrfIpcssCrsrdTrfvlm crsrdTrfvlm);

	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);
	
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportForDywkDiv(TrfvlmStatisticsDTO trfvlmStatisticsDTO);
	
	/**
	  * @Method Name : findAllCrsrdTrfvlmStatisticsList
	  * @작성일 : 2023. 11. 21.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 통계 분석 교통 영향 평가 > 교차로 목록
	  * @return
	  */
	List<TrfvlmStatisticsDTO> findAllCrsrdTrfvlmStatisticsList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	  * @Method Name : findAllCrsrdTrfvlmStatisticsGraphList
	  * @작성일 : 2023. 11. 23.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 썬버스트 그래프 데이터 개수 목록 호출
	  * @param trfvlmStatisticsDTO
	  * @return
	  */
	List<TrfvlmStatisticsGraphDTO> findAllCrsrdTrfvlmStatisticsGraphList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);
	
	/**
	  * @Method Name : findAllCrsrdNmAndCrsrdNo
	  * @작성일 : 2023. 11. 22.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 교차로명 목록
	  * @param trfvlmStatisticsDTO
	  * @return
	  */
	List<TrfIpcssCrsrdTrfvlm> findAllCrsrdNmAndCrsrdNo(TrfvlmStatisticsDTO trfvlmStatisticsDTO);
}
