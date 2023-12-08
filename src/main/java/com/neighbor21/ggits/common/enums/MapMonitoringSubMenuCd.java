package com.neighbor21.ggits.common.enums;

public enum MapMonitoringSubMenuCd {
	
	//모니터링 > 교통현황 > 시간대별 누적 교통량
	CUMULATIVE_TRAFFIC_VOLUME_BY_TIME_ZONE("M_TRAFFIC_001","사간대별 누적교통량"),
	
	//모니터링 > 교통현황 > 평균 동행 속도
	AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE("M_TRAFFIC_002","시간대별 평균동행속도"),
	
	//모니터링 > 교통현황 > 도로별 누적교통량
	CUMULATIVE_TRAFFIC_VOLUME_BY_ROAD("M_TRAFFIC_003","도로별 누적교통량"),
	
	//모니터링 > 교통현황 > 도로별 평균동행속도
	AVERAGE_ENTRAINMENT_SPEED_BY_ROAD("M_TRAFFIC_004","도로별 평균동행속도"),
	
	//모니터링 > 교통현황 > 소통 정보
	TRAFFIC_INFORMATION("M_TRAFFIC_005","소통정보"),
	
	//모니터링 > 교통현황 > 도로/시간대별 교통량
	TRAFFIC_VOLUME_BY_TIME_ZONE_AND_ROAD("M_TRAFFIC_006","도로/시간대별 교통량"),
	
	//모니터링 > 교통현황 > 도로/시간대별 평균동행속도
	AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE_AND_ROAD("M_TRAFFIC_007","도로/시간대별 평균동행속도");
	
	private String menuCode; 
	private String menuName;
	
	private MapMonitoringSubMenuCd(String menuCode, String menuName) {
		this.menuCode = menuCode;
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public String getMenuName() {
		return menuName;
	}


	public static MapMonitoringSubMenuCd getEnum(String code) {
		for(MapMonitoringSubMenuCd mapSubMenuCd : MapMonitoringSubMenuCd.values()) {
			if(mapSubMenuCd.menuCode.equals(code)) {
				return mapSubMenuCd;
			}
		}
		return null;
	}
	
	
}
