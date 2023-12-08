package com.neighbor21.ggits.common.enums;

public enum MapFacilitySubMenuCd {
	
	//모니터링 > 교통현황 > 시간대별 누적 교통량
	VDS("F_FACILITY_001","VDS"),
	DSRC("F_FACILITY_002","DSRC"),
	SMART_INTERSECTION("F_FACILITY_003","스마트 교차로"),
	SIGNAL("F_FACILITY_004","신호정보");
	
	
	private String menuCode; 
	private String menuName;
	
	private MapFacilitySubMenuCd(String menuCode, String menuName) {
		this.menuCode = menuCode;
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public String getMenuName() {
		return menuName;
	}


	public static MapFacilitySubMenuCd getEnum(String code) {
		for(MapFacilitySubMenuCd mapSubMenuCd : MapFacilitySubMenuCd.values()) {
			if(mapSubMenuCd.menuCode.equals(code)) {
				return mapSubMenuCd;
			}
		}
		return null;
	}
	
	
}
