package com.neighbor21.ggits.common.enums;

public enum OpenApiErrorCodes {
	SUCCESS(200,"API 호출 성공"),
	BAD_REQUEST(400,"잘못된 파라미터입니다. 파라미터 값을 확인해주세요."),
	ACCESS_DENIED(403,"잘못된 파라미터나 잘못된 접근입니다."),
	METHOD_NOT_ALLOWED(405,"잘못된 API 호출 방식입니다."),
	API_NOT_FOUND(404,"API를 찾지 못했습니다."),
	SERVER_ERROR(500,"서버 상태가 원활 하지 않습니다. 잠시 후 다시 확인 해주세요.");
	
	private int code; 
	private String message;
	
	private OpenApiErrorCodes(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public static OpenApiErrorCodes getErrorForCode(int code) {
		for(OpenApiErrorCodes r : OpenApiErrorCodes.values()) {
			if(r.code == code) {
				return r;
			}
		}
		return OpenApiErrorCodes.SERVER_ERROR;
	}
	
}
