package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.TrfIpcssActPopltnBsunt;

@Mapper
public interface TrfIpcssActPopltnBsuntMapper {
	
	/**
	 * @Method Name : countTrafficImpactList
	 * @작성일 : 2023. 10. 06.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 개수 조회
	 * @param trfIpcssActPopltnBsunt
	 */
	int countTrafficImpactList(TrfIpcssActPopltnBsunt trfIpcssActPopltnBsunt);

	/**
	 * @Method Name : findAllTrafficImpactList
	 * @작성일 : 2023. 10. 06.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 조회
	 * @param trfIpcssActPopltnBsunt
	 */
	List<TrfIpcssActPopltnBsunt> findAllTrafficImpactList(TrfIpcssActPopltnBsunt trfIpcssActPopltnBsunt);
	
	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 10. 17.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

}
