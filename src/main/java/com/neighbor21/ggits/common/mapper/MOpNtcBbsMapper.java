package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpNtcBbs;

@Mapper
public interface MOpNtcBbsMapper {
	
	/**
	 * @Method Name : saveNotice
	 * @작성일 : 2024. 01. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 저장
	 */	
	public void saveMOpNtcBbs(MOpNtcBbs mOpNtcBbs);
	
	/**
	 * @Method Name : countBySearchOption
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 전체 카운트 
	 */	
	public Long countBySearchOption(MOpNtcBbs mOpNtcBbs);
	
	/**
	 * @Method Name : findMOpNtcBbsBySearchOption
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 목록 조회 쿼리 
	 */	
	public List<MOpNtcBbs> findMOpNtcBbsBySearchOption(MOpNtcBbs mOpNtcBbs);


	/**
	 * @Method Name : findOneByNtcId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 상세 조회
	 */	
	public MOpNtcBbs findOneByNtcId(String ntcId);
	
	/**
	 * @Method Name : updateMOpNtcBbs
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 수정
	 */	
	public void updateMOpNtcBbs(MOpNtcBbs mOpNtcBbs);

	/**
	 * @Method Name : findntcSrchCntByNtcId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 조회수 조회
	 */	
	public Long findntcSrchCntByNtcId(String ntcId);

	/**
	 * @Method Name : updateNtcSrchCnt
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 조회수 수정
	 */	
	public void updateNtcSrchCnt(MOpNtcBbs mOpNtcBbs);
	
	/**
	 * @Method Name : deleteMOpNtcBbs
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 공지사항 삭제
	 */	
	public void deleteMOpNtcBbs(String ntcId);

}
