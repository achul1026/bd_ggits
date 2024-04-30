package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpInqryAns;

@Mapper
public interface MOpInqryAnsMapper {

	/**
	 * @Method Name : findAllByInqryId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 조회
	 */	
	public List<MOpInqryAns> findAllByInqryId(String inqryId);

	/**
	 * @Method Name : findAllByInqryId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 등록
	 */	
	public void saveMOpInqryAns(MOpInqryAns mOpInqryAns);

	/**
	 * @Method Name : deleteMOpInqryAnsByInqryId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 복수 제거
	 */	
	public void deleteMOpInqryAnsByInqryId(String inqryId);
	
	/**
	 * @Method Name : deleteMOpInqryAnsByInqryAnsId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 단일 제거
	 */	
	public void deleteMOpInqryAnsByInqryAnsId(String inqryAnsId);

	/**
	 * @Method Name : findOneByInqryAnsId
	 * @작성일 : 2024. 01. 29.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 조회
	 */	
	public MOpInqryAns findOneByInqryAnsId(String inqryAnsId);

	/**
	 * @Method Name : updateMOpInqryAns
	 * @작성일 : 2024. 01. 30.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 답변 수정
	 */	
	public void updateMOpInqryAns(MOpInqryAns mOpInqryAns);

}
