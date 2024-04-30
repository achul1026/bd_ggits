package com.neighbor21.ggits.openapi.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.neighbor21.ggits.common.enums.OpenApiErrorCodes;

public class OpenApiCommonResult {
	
	private List<?> data;	// data list
	private Map<String,Object> result;	// code , message
	
	public OpenApiCommonResult() {
		super();
	}

	public OpenApiCommonResult(List<?> data, Map<String, Object> result) {
		super();
		this.data = data;
		this.result = result;
	}
	
	public OpenApiCommonResult(OpenApiErrorCodes openApiErrorCodes) {
		super();
		this.data = null;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("code", openApiErrorCodes.getCode());
		resultMap.put("message", openApiErrorCodes.getMessage());
		this.result = resultMap;
	}
	
	//코드메세지로 생성
	public OpenApiCommonResult(List<?> data, int code , String message) {
		super();
		this.data = data;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("code", code);
		resultMap.put("message", message);
		this.result = resultMap;
	}
	
	//코드메세지로 생성
	public OpenApiCommonResult(List<?> data, OpenApiErrorCodes openApiErrorCodes) {
		super();
		this.data = data;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("code", openApiErrorCodes.getCode());
		resultMap.put("message", openApiErrorCodes.getMessage());
		this.result = resultMap;
	}
	
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public Map<String, Object> getResult() {
		return result;
	}
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
}
