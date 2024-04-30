package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssMeanShareRt;

@Mapper
public interface TrfIpcssMeanShareRtMapper {
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);
	
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportForUsg(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : saveMeanShareRt
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 교통수단 분담률 조사 저장
	 * @param ipcssMngNo
	 */
	void saveMeanShareRt(TrfIpcssMeanShareRt meanShareRt);

	/**
	 * @Method Name : findAllMeanShareRtList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도/비주거용도 교통수단 분담률 조사 리스트 조회
	 * @param commonEntity
	 */
	List<TrfIpcssMeanShareRt> findAllMeanShareRtList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllMeanShareRtList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도 교통수단 분담률 조사 상세 리스트 조회
	 * @param meanShareRtForDwell
	 */
	List<TrfIpcssMeanShareRt> findOneMeanShareRtInfoForDwell(TrfIpcssMeanShareRt meanShareRtForDwell);

	/**
	 * @Method Name : findAllMeanShareRtList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 비주거용도 교통수단 분담률 조사 상세 리스트 조회
	 * @param meanShareRtForNonDwell
	 */
	List<TrfIpcssMeanShareRt> findOneMeanShareRtInfoForNonDwell(TrfIpcssMeanShareRt meanShareRtForNonDwell);

	/**
	 * @Method Name : findAllChartData
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 비주거용도 교통수단 분담률 조사 통계 데이터 조회
	 * @param paramMap
	 */
	Map<String, Object> findAllChartData(Map<String, Object> paramMap);

}
