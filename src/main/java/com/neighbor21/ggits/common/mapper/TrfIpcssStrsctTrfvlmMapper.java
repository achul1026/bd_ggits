package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.dto.TrfvlmStatisticsGraphDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssStrsctTrfvlm;
@Mapper
public interface TrfIpcssStrsctTrfvlmMapper {

	/**
	 * @Method Name : saveStrsctTrfvlm
	 * @작성일 : 2023. 11. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 가로 구간 교통량 조사 시트 저장
	 * @param strsctTrfvlm
	 */
	void saveStrsctTrfvlm(TrfIpcssStrsctTrfvlm strsctTrfvlm);

	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);
	
	/**
	 * @Method Name : deleteTrafficImpactReportForDywkDivByIpcssMngNo
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
	  * @Method 설명 : 통계 분석 교통 영향 평가 > 구간별 목록
	  * @return
	  */
	List<TrfvlmStatisticsDTO> findAllStrsctTrfvlmStatisticsList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	  * @Method Name : findAllStrsctTrfvlmStatisticsGraphList
	  * @작성일 : 2023. 11. 27.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 통계 분석 교통 영향 평가 > 구간별 그래프 목록
	  * @param trfvlmStatisticsDTO
	  * @return
	  */
	List<TrfvlmStatisticsGraphDTO> findAllStrsctTrfvlmStatisticsGraphList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);
}
