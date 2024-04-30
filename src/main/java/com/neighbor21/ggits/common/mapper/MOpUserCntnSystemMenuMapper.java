package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpUserCntnSystemMenu;

@Mapper
public interface MOpUserCntnSystemMenuMapper {

	/**
	 * @Method Name : findAllMOpCntnSystemMenuByOprtrId
	 * @작성일 : 2023. 12.27
	 * @작성자 : KY.LEE
	 * @Method 설명 : 대문페이지 메뉴 리스트 조회
	 * @param MOpUserCntnSystemMenu
	 * @return List<MOpUserCntnSystemMenu>
	 */
	List<MOpUserCntnSystemMenu> findAllMOpCntnSystemMenuByOprtrIdAndUseYn(MOpUserCntnSystemMenu mOpUserCntnSystemMenu);

	/**
	 * @Method Name : countByMOpCntnSystemMenuByOprtrId
	 * @작성일 : 2023. 12.27
	 * @작성자 : KY.LEE
	 * @Method 설명 : 대문페이지 메뉴 카운트 쿼리
	 * @param oprtrId
	 * @return int
	 */
	int countByMOpCntnSystemMenuByOprtrId(Long oprtrId);
	
	/**
	 * @Method Name : saveMOpUserCntnSystemMenu
	 * @작성일 : 2023. 12.27
	 * @작성자 : KY.LEE
	 * @Method 설명 : 대문페이지 메뉴 등록
	 * @param MOpUserCntnSystemMenu
	 */
	void saveMOpUserCntnSystemMenu(MOpUserCntnSystemMenu mOpUserCntnSystemMenu);
	
	/**
	 * @Method Name : updateMOpUserCntnSystemMenuUseYn
	 * @작성일 : 2023. 12.27
	 * @작성자 : KY.LEE
	 * @Method 설명 : 대문페이지 메뉴 권한 수정
	 * @param MOpUserCntnSystemMenu
	 */
	void updateMOpUserCntnSystemMenuUseYn(MOpUserCntnSystemMenu mOpUserCntnSystemMenu);

	void deleteByOprtrId(long oprtrId);
}
