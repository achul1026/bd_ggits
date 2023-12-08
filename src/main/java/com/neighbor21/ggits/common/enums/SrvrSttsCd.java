package com.neighbor21.ggits.common.enums;

public enum SrvrSttsCd {
	NOMAL("SSC000","정상"),
	STOP("SSC001","중지"),
	ERROR("SSC002","오류");
	
	private String code;
	private String name;
	
	private SrvrSttsCd(String code, String name) {
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
		for(SrvrSttsCd r : SrvrSttsCd.values()) {
			if(r.code.equals(code)) {
				return r.name;
			}
		}
		return null;
	}
}
