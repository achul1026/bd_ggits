package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.TrfvlmStatisticsDTO;
import com.neighbor21.ggits.common.entity.TrfIpcssParkngOccurBsunt;

@Mapper
public interface TrfIpcssParkngOccurBsuntMapper {

	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

	/**
	 * @Method Name : saveParkngOccurBsunt
	 * @작성일 : 2023. 11. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주차발생 원단위 조사자료 시트 저장
	 * @param ipcssMngNo
	 */
	void saveParkngOccurBsunt(TrfIpcssParkngOccurBsunt parkngOccurBsunt);

	/**
	 * @Method Name : findAllTimeInoutflExmnList
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주차발생 원단위 조사 리스트 조회
	 * @param commonEntity
	 */
	List<TrfIpcssParkngOccurBsunt> findAllParkngOccurBsuntList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllTimeInoutflExmnList
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주차발생 원단위 조사 상세 리스트 조회
	 * @param timeInoutflExmn
	 */
	List<TrfIpcssParkngOccurBsunt> findOneParkngOccurBsuntInfo(TrfIpcssParkngOccurBsunt parkngOccurBsunt);

	/**
	 * @Method Name : findAllTimeInoutflExmnList
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주차발생 원단위 조사 용도 리스트 조회
	 * @param timeInoutflExmn
	 */
	List<TrfIpcssParkngOccurBsunt> findAllUsgNoList(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

	/**
	 * @Method Name : findAllTimeInoutflExmnList
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 주차발생 원단위 조사 통계 데이터 조회
	 * @param timeInoutflExmn
	 */
	TrfIpcssParkngOccurBsunt findAllChartData(TrfvlmStatisticsDTO trfvlmStatisticsDTO);

}
