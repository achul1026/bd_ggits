package com.neighbor21.ggits.common.enums;

public enum MapBigdataSubMenuCd {
	
	//교통패턴분석
	TRAFFIC_PATTERN_ANALYSIS_MORE_INFO("BD_PATTERN_001","더 많은 데이터 보기"), 	//더 많은 데이터 보기
	TRAFFIC_PATTERN_ANALYSIS_VHCL_TRFVLM("BD_PATTERN_002","교통량"),			//교통량
	TRAFFIC_PATTERN_ANALYSIS_AVG_SPPED("BD_PATTERN_003","평균속도"),			//평균속도
	TRAFFIC_PATTERN_ANALYSIS_CONGESTION_SECTION("BD_PATTERN_004","정체구간"),	//정체구간
	
	//교통활동 효과분석
	TRAFFIC_EFFECT_ANALYSIS_MORE_INFO("BD_EFFECT_ANALYSIS_001","더 많은 데이터 보기"), 	//더 많은 데이터 보기
	TRAFFIC_EFFECT_CONGESTION_SECTION("BD_EFFECT_ANALYSIS_002","정체구간 개선효과"), 		//정체구간 개선효과
	TRAFFIC_EFFECT_EMERGENCY_VEHICLE("BD_EFFECT_ANALYSIS_003","긴급차량 우선 신호시스템 제어효과"), 	//긴급차량 우선 신호시스템 제어효과
	
	// 대중교통 이용현황 분석
	PUBLIC_TRAFFIC_ANALYSIS_MORE_INFO("BD_PB_TRANS_001", "더 많은 데이터 보기"),		// 더 많은 데이터 보기
	PUBLIC_TRAFFIC_START_END_USAGE("BD_PB_TRANS_002", "기종점 대중교통 이용량"),		// 기종점 대중교통 이용량
	PUBLIC_TRAFFIC_REGION_USAGE("BD_PB_TRANS_003", "권역별 대중교통 이용현황"),			// 권역별 대중교통 이용현황
	PUBLIC_TRAFFIC_REGION_TRANSFER("BD_PB_TRANS_004", "권역별 환승현황 분석"),		// 권역별 환승현황 분석
	PUBLIC_TRAFFIC_FACILITY_INFO("BD_PB_TRANS_005", "정류장별 대중교통 및 노선 시설물"),	// 정류장별 대중교통 및 노선 시설물
	PUBLIC_TRAFFIC_BUS_USE_RATE("BD_PB_TRANS_006", "정류장별 버스 이용률"),			// 정류장별 버스 이용률
	PUBLIC_TRAFFIC_BUS_ROUTE_BIT("BD_PB_TRANS_007", "정류장별 버스노선 및 BIT"),		// 정류장별 버스노선 및 BIT
	
	// 대중교통 노선 별 분석
	PUBLIC_TRAFFIC_ROUTE_MORE_INFO("BD_BUS_ROAD_001", "더 많은 데이터 보기"),							// 더 많은 데이터 보기
	PUBLIC_TRAFFIC_ROUTE_USER_CNT("BD_BUS_ROAD_002", "노선구간별 승하차/재차 승객수 조회"),				// 노선구간별 승하차/재차 승객수 조회
	PUBLIC_TRAFFIC_ROUTE_RECIVE_CURVE("BD_BUS_ROAD_003", "노선구간별 수용성 및 굴곡도 조회"),				// 노선구간별 수용성 및 굴곡도 조회
	PUBLIC_TRAFFIC_ROUTE_DUPL_SEC_ADEQUACY("BD_BUS_ROAD_004", "노선구간별 중복구간 도출 및 적정성 조회");	// 노선구간별 중복구간 도출 및 적정성 조회
	
	private String menuCode; 
	private String menuName;
	
	private MapBigdataSubMenuCd(String menuCode, String menuName) {
		this.menuCode = menuCode;
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public String getMenuName() {
		return menuName;
	}


	public static MapBigdataSubMenuCd getEnum(String code) {
		for(MapBigdataSubMenuCd mapSubMenuCd : MapBigdataSubMenuCd.values()) {
			if(mapSubMenuCd.menuCode.equals(code)) {
				return mapSubMenuCd;
			}
		}
		return null;
	}
	
	
}
