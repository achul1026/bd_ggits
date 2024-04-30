package com.neighbor21.ggits.common.enums;

public enum FiledivCd {
		ROAD("FDC000","road"),
		RAIL("FDC001","rail"),
		TRAFFIC_FACILLITIES("FDC002","trfFacillities"),
		TRAFFIC_INFO_CENTER("FDC003","trfInfoCenter"),
		TRANSPOT_FACILLITY("FDC004","transpotFac");
		
		private String code;
		private String name;
		
		private FiledivCd(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public String getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
		
		public static String getNameForCode(String code) {
			for(FiledivCd r : FiledivCd.values()) {
				if(r.code.equals(code)) {
					return r.name;
				}
			}
			return null;
		}
		
		public static String getCodeForName(String name) {
			for(FiledivCd r : FiledivCd.values()) {
				if(r.name.equals(name)) {
					return r.code;
				}
			}
			return null;
		}
}
