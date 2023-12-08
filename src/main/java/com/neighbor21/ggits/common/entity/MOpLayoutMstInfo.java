package com.neighbor21.ggits.common.entity;

import java.util.List;

public class MOpLayoutMstInfo {
	String layoutId;			//레이아웃 아이디
	long oprtrId;				//운영자 아이디
	String menuId;				//메뉴 아이디
	String layoutMenuNm;		//레이아웃 메뉴 명
	String layoutSttsCd;		//레이아웃 상태 코드
	String layout1UseYn;		//레이아웃1 사용 여부
	String layout2UseYn;		//레이아웃2 사용 여부
	String layout3UseYn;		//레이아웃3 사용 여부
	String fnctType;			//기능 유형
	String dataTypeCd;			//데이터 유형 코드 EX) DTC000 : 차트형 , DTC001 레이어형, DTC002차트,레이어형
	
	//#MOpMenu
	String menuNm;				//메뉴 이름
	String menuPttrnType;		//메뉴 패턴 타입
	
	//#MOpOperator
	int layoutNo; 			//레이아웃번호 아직컬럼생성x
	
	//parameter
	List<String> menuIdList; 	//메뉴ID리스트
	
	public String getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(String layoutId) {
		this.layoutId = layoutId;
	}
	public long getOprtrId() {
		return oprtrId;
	}
	public void setOprtrId(long oprtrId) {
		this.oprtrId = oprtrId;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getLayoutMenuNm() {
		return layoutMenuNm;
	}
	public void setLayoutMenuNm(String layoutMenuNm) {
		this.layoutMenuNm = layoutMenuNm;
	}
	public String getLayoutSttsCd() {
		return layoutSttsCd;
	}
	public void setLayoutSttsCd(String layoutSttsCd) {
		this.layoutSttsCd = layoutSttsCd;
	}

	public String getLayout1UseYn() {
		return layout1UseYn;
	}
	public void setLayout1UseYn(String layout1UseYn) {
		this.layout1UseYn = layout1UseYn;
	}
	public String getLayout2UseYn() {
		return layout2UseYn;
	}
	public void setLayout2UseYn(String layout2UseYn) {
		this.layout2UseYn = layout2UseYn;
	}
	public String getLayout3UseYn() {
		return layout3UseYn;
	}
	public void setLayout3UseYn(String layout3UseYn) {
		this.layout3UseYn = layout3UseYn;
	}
	public String getFnctType() {
		return fnctType;
	}
	public void setFnctType(String fnctType) {
		this.fnctType = fnctType;
	}
	public List<String> getMenuIdList() {
		return menuIdList;
	}
	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}
	public String getMenuNm() {
		return menuNm;
	}
	public void setMenuNm(String menuNm) {
		this.menuNm = menuNm;
	}
	public String getMenuPttrnType() {
		return menuPttrnType;
	}
	public void setMenuPttrnType(String menuPttrnType) {
		this.menuPttrnType = menuPttrnType;
	}
	public int getLayoutNo() {
		return layoutNo;
	}
	public void setLayoutNo(int layoutNo) {
		this.layoutNo = layoutNo;
	}
	public String getDataTypeCd() {
		return dataTypeCd;
	}
	public void setDataTypeCd(String dataTypeCd) {
		this.dataTypeCd = dataTypeCd;
	}
	
}
