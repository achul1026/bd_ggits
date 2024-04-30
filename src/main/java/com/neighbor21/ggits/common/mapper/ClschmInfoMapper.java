package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.ClschmInfo;

@Mapper
public interface ClschmInfoMapper {

	/**
	 * @Method Name : findAllClschNm
	 * @작성일 : 2023. 11. 6.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 분류체계 목록 조회
	 */	
	public List<String> findAllClschNm();
	
	/**
	 * @Method Name : findAllClschNm
	 * @작성일 : 2023. 11. 6.
	 * @작성자 : KY.LEE
	 * @Method 설명 :  분류체계명 중복 조회
	 */
	public String findClschmIdByClschmNm(String clschmNm);
	
	/**
	 * @Method Name : findAllClschNm
	 * @작성일 : 2023. 11. 6.
	 * @작성자 : KY.LEE
	 * @Method 설명 :  분류체계 등록
	 */
	public void saveClschmInfo(ClschmInfo clschmInfo);
}
