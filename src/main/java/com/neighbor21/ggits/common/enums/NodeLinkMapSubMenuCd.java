package com.neighbor21.ggits.common.enums;

public enum NodeLinkMapSubMenuCd {
	
	//기초 정보 조회 > 노드 찾기
	NODE_SEARCH("B_NODELINK_001","노드 찾기"),
	LINK_SEARCH("B_NODELINK_002","링크 찾기"),
	NODE_LINK_CURRENT_SITUATION("B_NODELINK_003","노드/링크 현황보기"),
	NODE_LINK_REFFERENCE("B_NODELINK_004","노드/링크 자료실");
	
	private String menuCode; 
	private String menuName;
	
	private NodeLinkMapSubMenuCd(String menuCode, String menuName) {
		this.menuCode = menuCode;
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public static NodeLinkMapSubMenuCd getNameForCode(String code) {
		for(NodeLinkMapSubMenuCd r : NodeLinkMapSubMenuCd.values()) {
			if(r.menuCode.equals(code)) {
				return r;
			}
		}
		return null;
	}

	public static boolean getSubMenuCode(String code) {
		for(NodeLinkMapSubMenuCd mapSubMenuCd : NodeLinkMapSubMenuCd.values()) {
			if(mapSubMenuCd.menuCode.equals(code)) {
				return true;
			}
		}
		return false;
	}
	
	
}
