package com.neighbor21.ggits.common.enums;

public enum ResultStatus {
	
	SUCCESS("RSC000","성공"),
	FAILED("RSC001","실패");
	
	private String code;
	private String name;
	
	private ResultStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
}
