package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpAthrMenu;

@Mapper
public interface MOpAthrMenuMapper {

	/**
	 * @Method Name : saveMOpAthrMenu
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 권한 등록
	 */
	void saveMOpAthrMenu(MOpAthrMenu mOpAthrMenu);
	
	/**
	 * @Method Name : findMenuAuthByAuthId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 권한 목록 조회
	 */
	List<MOpAthrMenu> findMenuAuthByAuthId(Long authId);
	
	
	/**
	 * @Method Name : deleteByAuthId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 권한 전체 삭제
	 */
	void deleteAllByAuthId(Long authId);
	
	/**
	 * @Method Name : deleteByAuthIdAndMenuId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 메뉴 권한 일부 삭제
	 */
	void deleteByAuthIdAndMenuId(MOpAthrMenu mOpAthrMenu);
}
