package com.neighbor21.ggits.common.enums;

/**
 * 레이아웃 메뉴 DEFAULT
 * @author qudck
 *
 */
public enum LayoutMenuInfo {
	TRAFFIC_INFO("EVC000","교통량","FTC000","DTC001"),
//	TRAFFIC_SIGNAL("EVC001","교통신호","FTC001","DTC001"),
	OUT_BREAK_RAYER("EVC002","돌발현황","FTC002","DTC001"),
	EMRG_OPERATION_RAYER("EVC004","긴급차량 운행","FTC003","DTC001"),
	TRAFFIC_COMMUNICATION_INFO("EVC000","교차로 및 구간 소통 정보","FTC004","DTC000"),
	ACCUMULATE_TRAFFIC_INFO("EVC000","시간대별 누적 교통량","FTC005","DTC000"),
	AVG_SPEED_INFO("EVC000","시간대별 평균 통행 속도","FTC006","DTC000"),
	BUS_OPERATION_INFO("EVC000","시내버스 운행 현황","FTC007","DTC000"),
	DELAY_SECTION("EVC000","주요 정체 구간","FTC008","DTC000"),
	OUT_BREAK_CHART("EVC002","돌발현황","FTC009","DTC000"),
	COLLECTION_ERROR("EVC007","데이터 수집 장애 알림","FTC010","DTC000"),
	EMRG_OPERATION_CHART("EVC004","긴급차량 운행 현황","FTC011","DTC000");
	
	private String code;
	private String name;
	private String fncType;
	private String dataTypeCd; //DTC000 차트 DTC001레이어
	
	private LayoutMenuInfo(String code, String name, String fncType, String dataTypeCd) {
		this.code = code;
		this.name = name;
		this.fncType = fncType;
		this.dataTypeCd = dataTypeCd;
	}

	public String getFncType() {
		return fncType;
	}

	public String getDataTypeCd() {
		return dataTypeCd;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
//	public static List<String> getLinkedTableInfoList(ServerMngType serverMngType) {
//		List<String> resultList = new ArrayList<String>();
//		for(LayoutMenuInfo r : LayoutMenuInfo.values()) {
//			if(r.serverMngType.equals(serverMngType)) {
//				resultList.add(r.code);
//			}
//		}
//		return resultList;
//	}
}
