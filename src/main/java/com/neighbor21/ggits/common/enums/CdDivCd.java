package com.neighbor21.ggits.common.enums;

public enum CdDivCd {
		A_TAG_BUTTON("CDC000","A태그 버튼"),
		INPUT_BUTTON("CDC001","Input 버튼");
		
		private String code;
		private String name;
		
		private CdDivCd(String code, String name) {
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
			for(CdDivCd r : CdDivCd.values()) {
				if(r.code.equals(code)) {
					return r.name;
				}
			}
			return null;
		}
}
