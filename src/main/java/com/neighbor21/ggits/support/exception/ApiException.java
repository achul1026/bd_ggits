package com.neighbor21.ggits.support.exception;

import com.neighbor21.ggits.common.enums.OpenApiErrorCodes;

public class ApiException extends RuntimeException{

	private OpenApiErrorCodes openApiErrorCodes;
	private String message;
	private String parameterStr;

	public ApiException() {
		super();
	}
	
	public ApiException(OpenApiErrorCodes openApiErrorCodes) {
		super();
		this.openApiErrorCodes = openApiErrorCodes;
		this.message = openApiErrorCodes.getMessage();
	}

	public ApiException(OpenApiErrorCodes openApiErrorCodes ,String parameterStr) {
		super();
		this.openApiErrorCodes = openApiErrorCodes;
		this.message = openApiErrorCodes.getMessage();
		this.parameterStr = parameterStr;
	}

	public ApiException(OpenApiErrorCodes openApiErrorCodes, String message , String parameterStr) {
		super();
		this.openApiErrorCodes = openApiErrorCodes;
		this.message = message;
		this.parameterStr = parameterStr;
	}

	public OpenApiErrorCodes getOpenApiErrorCodes() {
		return openApiErrorCodes;
	}

	public void setOpenApiErrorCodes(OpenApiErrorCodes openApiErrorCodes) {
		this.openApiErrorCodes = openApiErrorCodes;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getParameterStr() {
		return parameterStr;
	}

	public void setParameterStr(String parameterStr) {
		this.parameterStr = parameterStr;
	}
	
}
