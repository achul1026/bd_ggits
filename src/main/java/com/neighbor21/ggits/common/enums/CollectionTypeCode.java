package com.neighbor21.ggits.common.enums;

public enum CollectionTypeCode {
	JSON("CTC000","JSON"),
	HWP("CTC001","HWP"),
	CSV("CTC002","CSV"),
	PDF("CTC003","PDF"),
	TXT("CTC004","TXT"),
	XML("CTC005","XML"),
	ZIP("CTC006","ZIP");
	
	private String code;
	private String name;
	
	private CollectionTypeCode(String code, String name) {
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
		for(CollectionTypeCode r : CollectionTypeCode.values()) {
			if(r.code.equals(code)) {
				return r.name;
			}
		}
		return null;
	}
}
