package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.LTcDataCatlogAply;

@Mapper
public interface LTcDataCatlogAplyMapper {
	
	/**
	 * @Method Name : countBySearchOption
	 * @작성일 : 2024. 02. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 신청 이력 카운트 쿼리
	 */	
	public int countBySearchOption(LTcDataCatlogAply lTcDataCatlogAply);

	/**
	 * @Method Name : findAllBySearchOption
	 * @작성일 : 2024. 02. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 신청 목록 조회 쿼리
	 */	
	public List<LTcDataCatlogAply> findAllBySearchOption(LTcDataCatlogAply lTcDataCatlogAply); 
	
	
	/**
	 * @Method Name : saveLTcDataCatlogAply
	 * @작성일 : 2024. 02. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 이관 신청 등록
	 */	
	public void saveLTcDataCatlogAply(LTcDataCatlogAply lTcDataCatlogAply);
	
	/**
	 * @Method Name : updateAplyCmptnYnByAplyDsetIdAndOprtrId
	 * @작성일 : 2024. 02. 13.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 이관 신청 수정
	 */	
	public void updateAplyCmptnYnByAplyDsetIdAndOprtrId(LTcDataCatlogAply lTcDataCatlogAply);

}
