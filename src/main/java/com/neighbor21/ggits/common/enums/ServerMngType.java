package com.neighbor21.ggits.common.enums;

public enum ServerMngType {
	LOC_GOVMNT_LINKAGE("SMT000","지자체 연계"),
	EXTERNAL_LINKAGE("SMT001","외부기관 연계"),
	SIGNAL_LINKAGE("SMT002","신호 연계"),
	BIG_DATA_STORAGE_PLATFORM("SMT003","빅데이터 저장 플랫폼"),
	INTERNAL_LINKAGE("SMT004","내부기관 연계");
	
	private String code;
	private String name;
	
	private ServerMngType(String code, String name) {
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
		for(ServerMngType r : ServerMngType.values()) {
			if(r.code.equals(code)) {
				return r.name;
			}
		}
		return null;
	}
	
	public static ServerMngType getServerMngTypeFromCode(String code) {
		for(ServerMngType r : ServerMngType.values()) {
			if(r.code.equals(code)) {
				return r;
			}
		}
		return null;
	}
}
