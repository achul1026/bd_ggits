package com.neighbor21.ggits.common.enums;

public enum CntnSystemCd {
		SUDDEN_INFOMATION_SYSTEM("GG001","돌발 정보 시스템","N"),
		REGIONAL_EMERGENCY_VEHICLE_SIGNALS_AND_MOVEMENT_STATUS("GG002","광역 긴급차량 신호 및 이동현황","N"),
		BIG_DATA_ANALYSIS_PLATFORM("GG003","빅데이터 분석 플랫폼","Y"),
		VISUALIZATION_REPORT_MENU("GG004","시각화 리포트 메뉴","N"),
		VISUALIZATION_ANALYSIS("GG005","시각화 분석","N"),
		SPATIAL_INFORMATION_CONTROL_SYSTEM("GG006","공간정보 제어 시스템","N");
		
		private String code;
		private String name;
		private String defaultYn;
		
		private CntnSystemCd(String code, String name,String defaultYn) {
			this.code = code;
			this.name = name;
			this.defaultYn = defaultYn;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
		
		public String getDefaultYn() {
			return defaultYn;
		}

		public static String getNameForCode(String code) {
			for(CntnSystemCd r : CntnSystemCd.values()) {
				if(r.code.equals(code)) {
					return r.name;
				}
			}
			return null;
		}
}
