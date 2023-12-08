package com.neighbor21.ggits.web.service.systemmng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.MOpAthrMenu;
import com.neighbor21.ggits.common.entity.MOpAuthority;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MOpAthrMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpAuthorityMapper;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class AuthMngService{

	@Autowired
	MOpAuthorityMapper mOpAuthorityMapper;
	
	@Autowired
	MOpAthrMenuMapper mOpAthrMenuMapper;
	
    /**
     * @Method Name : saveAuthorityAndMenuAuth
     * @작성일 : 2023. 9.5
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 등록 메뉴 권한 등록
     * @return
     */
	public void saveAuthorityAndMenuAuth(MOpAuthority mOpAuthority) {
		int authId = mOpAuthorityMapper.findAuthIdNextVal();
		mOpAuthority.setAuthId(authId);
		if(mOpAuthority.getMenuIdArr().isEmpty()) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		mOpAuthorityMapper.saveMOpAuthority(mOpAuthority);
		for(String menuId : mOpAuthority.getMenuIdArr()) {
			MOpAthrMenu mOpAthrMenu = new MOpAthrMenu();
			mOpAthrMenu.setMenuId(menuId);
			mOpAthrMenu.setAuthId(authId);
			mOpAthrMenuMapper.saveMOpAthrMenu(mOpAthrMenu);
		}
	}
	
    /**
     * @Method Name : getTotalCntForSearchOption
     * @작성일 : 2023. 9.5
     * @작성자 : KY.LEE
     * @Method 설명 : 카운트 조회
     * @return
     */
	public int getTotalCntForSearchOption(String searchType ,String searchContent) {
		MOpAuthority mOpAuthority = new MOpAuthority();
		if(searchType != null &&!"".equals(searchType) && !"all".equals(searchType)) {
			if(searchContent != null &&!"".equals(searchContent)) {
				switch (searchType) {
				case "authNm":
						mOpAuthority.setAuthNm(searchContent);
					break;
				case "descr":
					mOpAuthority.setDescr(searchContent);
					break;
				default:
					mOpAuthority.setAuthNm(searchContent);
					mOpAuthority.setDescr(searchContent);
					break;
				}
			}
		} else {
			if(searchContent != null &&!"".equals(searchContent)) {
				mOpAuthority.setAuthNm(searchContent);
				mOpAuthority.setDescr(searchContent);
			}			
		}
		return mOpAuthorityMapper.countAllBySearchOption(mOpAuthority);
	}
	
    /**
     * @Method Name : getAuthList
     * @작성일 : 2023. 9.5
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 목록 조회
     * @return
     */
	public List<MOpAuthority> getAuthList(String searchType, String searchContent , Paging paging){
		MOpAuthority mOpAuthority = new MOpAuthority();
		if(searchType != null &&!"".equals(searchType) && !"all".equals(searchType)) {
			if(searchContent != null &&!"".equals(searchContent)) {
				switch (searchType) {
				case "authNm":
						mOpAuthority.setAuthNm(searchContent);
					break;
				case "descr":
					mOpAuthority.setDescr(searchContent);
					break;
				default:
					mOpAuthority.setAuthNm(searchContent);
					mOpAuthority.setDescr(searchContent);
					break;
				}
			}
		} else {
			if(searchContent != null &&!"".equals(searchContent)) {
				mOpAuthority.setAuthNm(searchContent);
				mOpAuthority.setDescr(searchContent);
			}			
		}
		mOpAuthority.setPage(paging.getPageNo());
		return mOpAuthorityMapper.findAuthListBySearchOption(mOpAuthority);
	}
	
    /**
     * @Method Name : deleteAll
     * @작성일 : 2023. 9.5
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 전체 삭제
     * @return
     */
	public void deleteAll(Long authId) {
		//권한 메뉴 삭제
		mOpAthrMenuMapper.deleteAllByAuthId(authId);
		
		//권한 삭제
		mOpAuthorityMapper.deleteByAuthId(authId);
	}
	
    /**
     * @Method Name : updateMOpAuthority
     * @작성일 : 2023. 9.5
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 수정
     * @return
     */
	public void updateMOpAuthority(MOpAuthority mOpAuthority) {
		mOpAuthorityMapper.updateMOpAuthority(mOpAuthority);
		//신규 메뉴  insert
		if(!mOpAuthority.getMenuIdArr().isEmpty()) {
			for(String menuId : mOpAuthority.getMenuIdArr()) {
				MOpAthrMenu mOpAthrMenu = new MOpAthrMenu();
				mOpAthrMenu.setMenuId(menuId);
				mOpAthrMenu.setAuthId(mOpAuthority.getAuthId());
				mOpAthrMenuMapper.saveMOpAthrMenu(mOpAthrMenu);
			}
		}
		//기존 메뉴 선택해제시 delete
		if(!mOpAuthority.getDeleteMenuIdArr().isEmpty()) {
			for(String menuId : mOpAuthority.getDeleteMenuIdArr()) {
				MOpAthrMenu mOpAthrMenu = new MOpAthrMenu();
				mOpAthrMenu.setMenuId(menuId);
				mOpAthrMenu.setAuthId(mOpAuthority.getAuthId());
				mOpAthrMenuMapper.deleteByAuthIdAndMenuId(mOpAthrMenu);
			}
		}
	}
}
