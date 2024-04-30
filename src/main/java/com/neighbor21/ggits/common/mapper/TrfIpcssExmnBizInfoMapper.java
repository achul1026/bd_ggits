package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.TrfIpcssExmnBizInfo;

@Mapper
public interface TrfIpcssExmnBizInfoMapper {

	/**
	 * @Method Name : saveExmnBizInfo
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 조사 사업 정보
	 * @param trfIpcssExmnBizInfo
	 */
	void saveExmnBizInfo(TrfIpcssExmnBizInfo trfIpcssExmnBizInfo);

	/**
	 * @Method Name : deleteTrafficImpactReportByIpcssMngNo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 선택 삭제
	 * @param ipcssMngNo
	 */
	void deleteTrafficImpactReportByIpcssMngNo(String ipcssMngNo);

	/**
	 * @Method Name : findAllTrafficImpactList
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 리스트 조회
	 * @param trfIpcssExmnBizInfo
	 */
	List<TrfIpcssExmnBizInfo> findAllTrafficImpactList(TrfIpcssExmnBizInfo trfIpcssExmnBizInfo);

	/**
	 * @Method Name : findOneByMngNoNextVal
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 평가 관리 번호 조회
	 * @param 
	 */
	long findOneByMngNoNextVal();

	/**
	 * @Method Name : findOneExmnBizInfoByipcssMngNo
	 * @작성일 : 2023. 11. 27.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 정보 조회
	 * @param 
	 */
	TrfIpcssExmnBizInfo findOneExmnBizInfoByipcssMngNo(String ipcssMngNo);

	/**
	 * @Method Name : countTrafficImpact
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 교통영향평가 개수 조회
	 * @param 
	 */
	int countTrafficImpact(TrfIpcssExmnBizInfo trfIpcssExmnBizInfo);

}
