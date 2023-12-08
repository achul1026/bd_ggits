package com.neighbor21.ggits.common.dto;

import java.util.List;
import java.util.Map;

import com.neighbor21.ggits.common.entity.MOpLayoutMstInfo;

public class MonitoringDashboardDTO {
	List<MOpLayoutMstInfo> mOpLayoutMstInfoList;		//레이아웃 메뉴 리스트
	List<Map<String,Object>> layoutList;				//전체 레이아웃 리스트
	List<String> menuPttrnTypeList;					//메뉴패턴타입 리스트
	
	//파라미터용
	String layoutNo; 								//레이아웃 번호
//	List<Map<String,Object>> LayoutTwo;				//레이아웃2
//	List<Map<String,Object>> LayoutThree;			//레이아웃3

	public List<MOpLayoutMstInfo> getmOpLayoutMstInfoList() {
		return mOpLayoutMstInfoList;
	}

	public void setmOpLayoutMstInfoList(List<MOpLayoutMstInfo> mOpLayoutMstInfoList) {
		this.mOpLayoutMstInfoList = mOpLayoutMstInfoList;
	}

	public List<Map<String, Object>> getLayoutList() {
		return layoutList;
	}

	public void setLayoutList(List<Map<String, Object>> layoutList) {
		this.layoutList = layoutList;
	}

	public List<String> getMenuPttrnTypeList() {
		return menuPttrnTypeList;
	}

	public void setMenuPttrnTypeList(List<String> menuPttrnTypeList) {
		this.menuPttrnTypeList = menuPttrnTypeList;
	}

	public String getLayoutNo() {
		return layoutNo;
	}

	public void setLayoutNo(String layoutNo) {
		this.layoutNo = layoutNo;
	}

	@Override
	public String toString() {
		return "MonitoringDashboardDTO [mOpLayoutMstInfoList=" + mOpLayoutMstInfoList + ", layoutList=" + layoutList
				+ ", menuPttrnTypeList=" + menuPttrnTypeList + ", layoutNo=" + layoutNo + "]";
	}

}
