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
	AVERAGE_ENTRAINMENT_SPEED_BY_TIME_ZONE_AND_ROAD("M_TRAFFIC_007","도로/시간대별 평균동행속도"),

	//모니터링 > 교통현황 > 차종별 평균 교통량
	CUMULATIVE_TRAFFIC_VOLUME_BY_VHCL_DIV("M_TRAFFIC_008","차종별 평균 교통량 그래프"),

	//모니터링 > 서비스 운영 현황 
	//전체서비스 운영현황
	ALL_SERVICE_OPERATION_STATUS("M_SERVICE_OPERATION_001","전체 서비스 운영현황"),
	//유스케이스 항목 접속 현황
	USE_CASE_CONNECTION_STATUS("M_SERVICE_OPERATION_002","유스케이스 항목 접속 현황"),
	//유스케이스 항목 접속 현황
	LOGIN_CONNECTION_STATUS("M_SERVICE_OPERATION_003","시군이용 대상별 접속 현황"),
	
	
	//모니터링 > 연계대상 데이터
	//데이터 수집현황
	DATA_COLLECTION_STATUS("M_LINKDATA_001","데이터 수집현황"),
	//데이터 수집이력
	DATA_COLLECTION_HISTORY("M_LINKDATA_002","데이터 수집이력"),
	//데이터 수집장애
	DATA_COLLECTION_FAILURE("M_LINKDATA_003","데이터 수집장애");

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
