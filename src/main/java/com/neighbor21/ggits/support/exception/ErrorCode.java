package com.neighbor21.ggits.support.exception;


public enum ErrorCode {
	
	
	
	// User 1000~2000
	NOT_FOUNT_USER_INFO(1001, "유저를 찾을 수 없습니다."),
	USER_INFO_IS_EXIST(1002, "유저 정보가 존재합니다."),
	NOT_FOUND_GROUP_INFO(1003, "그룹 정보가 존재하지 않습니다."),
	PASSWORD_MISMATCH(1004, "비밀번호가 불일치 합니다. 비밀번호를 확인해주세요."),
	
	// Common 8000~9000
	PARAMETER_DATA_NULL(8001, "파라미터의 값이 존재하지 않습니다."),
	ENTITY_DATA_NULL(8002, "조회 한 데이터가 존재하지 않습니다."),
	ENTITY_SAVE_FAIL(8003, "등록에 실패했습니다."),
	ENTITY_UPDATE_FAIL(8003, "수정에 실패했습니다."),
	SESSION_NOT_FOUND(8004,"세션 정보가 없습니다."),
	FILE_UPLOAD_FAIL(8005, "파일 업로드에 실패하였습니다."),
	DATE_PARSE_ERROR(8006, "날짜 변환에 실패했습니다.")
	;
	
	private int code;
	private String message;
	
	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
