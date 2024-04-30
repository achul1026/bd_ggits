package com.neighbor21.ggits.web.service.systemmng;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.LOpUseMenu;
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.enums.MenuLvl;
import com.neighbor21.ggits.common.mapper.LOpUseMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class MenuMngService{

	@Autowired
	MOpMenuMapper mOpMenuMapper;
	
	@Autowired
	LOpUseMenuMapper lOpUseMenuMapper;

	public Long getUpperMenuTotalCount(Map<String, Object> paramMap) {
		paramMap.put("useYn", "Y");
		return mOpMenuMapper.countByUpperMenuIdIsNullAndUseYn(paramMap); 
	}

	
	public Long getSubMenuCount(String upperMenuId) {
		return mOpMenuMapper.countByUpperMenuId(upperMenuId);
	}
	
	
	public Long getMainMenuSortNo() {
		Long count = mOpMenuMapper.countBySortNoMaxCount(); 
		return count+1;
	}
	
	public String saveMainMenu(MOpMenu mOpMenu) throws SQLException {
		
		String menuId = this.countByMenuIdNextVal();
		Long menuLvl = MenuLvl.MAIN_MENU.getLevel();
		Long sortNo = this.getMainMenuSortNo();
		
		mOpMenu.setMenuId(menuId);
		mOpMenu.setMenuLvl(menuLvl);
		mOpMenu.setDescr(mOpMenu.getMenuNm());
		mOpMenu.setSortNo(sortNo);
		mOpMenu.setCategCd(mOpMenu.getCategCd());
		mOpMenu.setCdDivCd("CDC000");
		//메인메뉴는 무조건 0번
		mOpMenu.setSbmnuSortNo(0L);
		
		mOpMenuMapper.save(mOpMenu);
		return menuId;
	}


	
	public String countByMenuIdNextVal() {
		int totalCnt = mOpMenuMapper.countByMenuIdNextVal(); 
		return String.valueOf(totalCnt+1);
	}


	
	public MOpMenu getMenuDetailInfo(String menuId) {
		return mOpMenuMapper.findOneByMenuId(menuId);
	}


	
	public MOpMenu saveSubMenu(Map<String, Object> paramMap) throws SQLException{
		MOpMenu mOpMenu = new MOpMenu();
		String mainMenuId = String.valueOf(paramMap.get("menuId"));
		String menuId = this.countByMenuIdNextVal();
		String urlPttrn = String.valueOf(paramMap.get("urlPttrn"));
		String upperMenuId = String.valueOf(paramMap.get("menuId"));
		String categCd = String.valueOf(paramMap.get("categCd"));
 		Long sortNo = Long.parseLong(String.valueOf(paramMap.get("sortNo")));
		if(GgitsCommonUtils.isNull(menuId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		String menuNm = String.valueOf(paramMap.get("menuNm"));
		String urlAddr = String.valueOf(paramMap.get("urlAddr"));
		Long menuLvl = MenuLvl.SUB_MENU.getLevel();
		String useYn = paramMap.get("useYn")!=null && !"".equals(paramMap.get("useYn"))?String.valueOf(paramMap.get("useYn")):"N";
		Long sbmnuSortNo = this.getSubMenuCount(mainMenuId)+1;
		
		mOpMenu.setMenuId(menuId);
		//상위메뉴 데이터
		mOpMenu.setUpperMenuId(upperMenuId);
		mOpMenu.setUrlPttrn(urlPttrn);
		mOpMenu.setSortNo(sortNo);
		
		mOpMenu.setMenuNm(menuNm);
		mOpMenu.setMenuLvl(menuLvl);
		mOpMenu.setUseYn(useYn);
		mOpMenu.setUrlAddr(urlAddr);
		mOpMenu.setDescr(menuNm);
		mOpMenu.setSbmnuSortNo(sbmnuSortNo);
		mOpMenu.setCategCd(categCd);
		mOpMenu.setCdDivCd("CDC000");
		
		mOpMenuMapper.save(mOpMenu);
		return mOpMenu;
	}
	
	public void updateMainMenu(MOpMenu mOpMenu) throws SQLException {
		String menuId = mOpMenu.getMenuId();
		if(GgitsCommonUtils.isNull(menuId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		MOpMenu dbMOpMenu = this.getMenuDetailInfo(menuId);
		if(dbMOpMenu != null) {
			dbMOpMenu.setMenuNm(mOpMenu.getMenuNm());
			dbMOpMenu.setUseYn(mOpMenu.getUseYn());
			dbMOpMenu.setUrlPttrn(mOpMenu.getUrlPttrn());
			dbMOpMenu.setUrlAddr(mOpMenu.getUrlAddr());
			dbMOpMenu.setSortNo(mOpMenu.getSortNo());
			if(mOpMenu.getCategCd() != null && !"".equals(mOpMenu.getCategCd())) {
				dbMOpMenu.setCategCd(mOpMenu.getCategCd());
			}
			mOpMenuMapper.update(dbMOpMenu);
		} else {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
	}

	public void updateSubMenu(MOpMenu mOpMenu) throws SQLException {
		String menuId = mOpMenu.getMenuId();
		if(GgitsCommonUtils.isNull(menuId)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		MOpMenu dbMOpMenu = this.getMenuDetailInfo(menuId);
		if(dbMOpMenu != null) {
			dbMOpMenu.setMenuNm(mOpMenu.getMenuNm());
			dbMOpMenu.setUseYn(mOpMenu.getUseYn());
			dbMOpMenu.setUrlPttrn(mOpMenu.getUrlPttrn());
			dbMOpMenu.setUrlAddr(mOpMenu.getUrlAddr());
			if(!GgitsCommonUtils.isNull(mOpMenu.getCategCd())) {
				dbMOpMenu.setCategCd(mOpMenu.getCategCd());
			}
			mOpMenuMapper.update(dbMOpMenu);
		} else {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
	}
	
	public void saveLOpUseMenu(String menuId) {
		LOpUseMenu lOpUseMenu = new LOpUseMenu();
		lOpUseMenu.setMenuId(menuId);
		lOpUseMenu.setOprtrId(LoginSessionUtils.getOprtrId());
		lOpUseMenu.setGrpId(LoginSessionUtils.getGrpId());
		lOpUseMenu.setLgnIp(LoginSessionUtils.getUserIpAddr());
		
		lOpUseMenuMapper.saveLOpUseMenu(lOpUseMenu);
	}
	
	public void removeMenuSession() {
 		//기존 메뉴값 재세팅
 		HttpSession session = LoginSessionUtils.getSessionRequest().getSession();
 		
 		if(session != null) {
 			session.removeAttribute("gnbMenuDTO");
 			session.removeAttribute("streamMenuList");
 			session.removeAttribute("menuCtgryList");
 		}
	}
}
