package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpInqryBbs;

@Mapper
public interface MOpInqryBbsMapper {

	/**
	 * @Method Name : saveMOpInqryBbs
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의하기 등록
	 */		
	public void saveMOpInqryBbs(MOpInqryBbs mOpInqryBbs);

	
	/**
	 * @Method Name : countBySearchOption
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의하기 목록 카운트
	 */		
	public Long countBySearchOption(MOpInqryBbs mOpInqryBbs);

	/**
	 * @Method Name : findMOpInqryBbsBySearchOption
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의하기 목록 조회 쿼리
	 */		
	public List<MOpInqryBbs> findMOpInqryBbsBySearchOption(MOpInqryBbs mOpInqryBbs);

	/**
	 * @Method Name : updateNtcSrchCnt
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의하기 조회수 수정
	 */	
	public void updateInqrySrchCnt(MOpInqryBbs mOpInqryBbs);
	
	/**
	 * @Method Name : findOneByInqryId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 상세조회
	 */	
	public MOpInqryBbs findOneByInqryId(String inqryId);


	/**
	 * @Method Name : updateMOpInqryBbs
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 수정
	 */	
	public void updateMOpInqryBbs(MOpInqryBbs mOpInqryBbs);

	/**
	 * @Method Name : deleteMOpInqryBbs
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 삭제
	 */	
	public void deleteMOpInqryBbs(MOpInqryBbs mOpInqryBbs);

	/**
	 * @Method Name : updateInqryAnsYnAndInqryAnsDt
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 문의 답변 상태 수정
	 */	
	public void updateInqryAnsYnAndInqryAnsDt(MOpInqryBbs dbMOpInqryBbs);

}
