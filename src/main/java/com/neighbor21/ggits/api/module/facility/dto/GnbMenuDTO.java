package com.neighbor21.ggits.api.module.facility.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.neighbor21.ggits.common.entity.MOpMenu;


/**
 * GNB 메뉴 트리구조 DTO
 * @author : KY.LEE
 * @fileName :  GnbMenuDTO
 * @since : 2023-09-07
 */
public class GnbMenuDTO extends MOpMenu{
	
    List<MOpMenu> mOpMenuList;

    public GnbMenuDTO(MOpMenu entity) {
        BeanUtils.copyProperties(entity, this);
    }

	public List<MOpMenu> getmOpMenuList() {
		return mOpMenuList;
	}

	public void setmOpMenuList(List<MOpMenu> mOpMenuList) {
		this.mOpMenuList = mOpMenuList;
	}
}
