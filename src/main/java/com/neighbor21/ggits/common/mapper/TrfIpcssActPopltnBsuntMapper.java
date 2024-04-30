package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssActPopltnBsunt;

@Mapper
public interface TrfIpcssActPopltnBsuntMapper {
	
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

	/**
	 * @Method Name : saveActPopltnBsunt
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 유사시설 활동인구 원단위 저장
	 * @param actPopltnBsunt
	 */
	void saveActPopltnBsunt(TrfIpcssActPopltnBsunt actPopltnBsunt);

	/**
	 * @Method Name : findAllActPopltnBsuntList
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 유사시설 활동인구 원단위 리스트 조회
	 * @param commonEntity
	 */
	List<TrfIpcssActPopltnBsunt> findAllActPopltnBsuntList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findOneActPopltnBsuntInfo
	 * @작성일 : 2023. 11. 21.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 유사시설 활동인구 원단위 상세 리스트 조회
	 * @param actPopltnBsunt
	 */
	List<TrfIpcssActPopltnBsunt> findOneActPopltnBsuntInfo(TrfIpcssActPopltnBsunt actPopltnBsunt);

	/**
	 * @Method Name : findAllusgNoList
	 * @작성일 : 2023. 11. 24.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 유사시설 활동인구 원단위 용도 리스트 조회
	 * @param actPopltnBsunt
	 */
	List<TrfIpcssActPopltnBsunt> findAllusgNoList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllusgNoList
	 * @작성일 : 2023. 11. 24.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 유사시설 활동인구 원단위 통계 데이터 조회
	 * @param actPopltnBsunt
	 */
	TrfIpcssActPopltnBsunt findAllChartData(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

}
