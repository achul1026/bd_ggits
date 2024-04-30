package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssNbopl;

@Mapper
public interface TrfIpcssNboplMapper {
	
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);
	
	/**
	 * @Method Name : deleteTrafficImpactReportForUsgByIpcssMngNo
	 * @작성일 : 2023. 11. 30.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제 용도별
	 * @param trfvlmStatisticsDTO
	 */
	void deleteTrafficImpactReportForUsg(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : saveNbopl
	 * @작성일 : 2023. 11. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 재차인차인원 조사(주거용도, 비주거용도) 시트 저장
	 * @param nbopl
	 */
	void saveNbopl(TrfIpcssNbopl nbopl);

	/**
	 * @Method Name : findAllNboplList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도, 비주거용도 재차인차인원 조사 리스트 조회
	 * @param commonEntity
	 */
	List<TrfIpcssNbopl> findAllNboplList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);
	
	/**
	 * @Method Name : findOneNboplInfo
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도 재차인차인원 조사 상세 리스트 조회
	 * @param nboplForNonDwell
	 */
	List<TrfIpcssNbopl> findOneNboplInfoForDwell(TrfIpcssNbopl nboplForNonDwell);

	/**
	 * @Method Name : findOneNboplInfo
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 비주거용도 재차인차인원 조사 상세 리스트 조회
	 * @param nboplForNonDwell
	 */
	List<TrfIpcssNbopl> findOneNboplInfoForNonDwell(TrfIpcssNbopl nboplForNonDwell);

	/**
	 * @Method Name : findAllChartData
	 * @작성일 : 2023. 11. 22.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주거용도, 비주거용도 재차인차인원 조사 통계 데이터 조회
	 * @param paramMap
	 */
	Map<String, Object> findAllChartData(Map<String, Object> paramMap);

}
