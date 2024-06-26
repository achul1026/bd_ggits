package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class LTcOpenApiRqstLog extends CommonEntity{
    private Timestamp clctDt;        		// 수집일시
    private String apiId;        			// api 아이디
    private String filextId;        		// 확장자 아이디
    private String tagId;        			// 태그 아이디
    private String authCd;        			// 권한 아이디
    private long oprtrId;        			// 운영자 아이디
    private String apiRqstLog;      		// api 요청 로그
    private long srchCnt;					// 조회 수
    private long dwnldCnt;					// 다운로드 수
    private String parameterValue;			// 파라미터 값
    private String apiCallUrl;				// API 호출 URL
    private String resultStatus;    		// 호출 결과
    private int responseCnt = 0; 			// 결과 데이터 수
    private String openApiLogId;			// 오픈API 로그 ID
    
    private String oprtrIdStr;
    
    //#MOpOperator
	private String oprtrNm; // 운영자 명
	private String oprtrEmail; // 운영자 이메일

	public Timestamp getClctDt() {
    return clctDt;
  }

  public void setClctDt(Timestamp clctDt) {
    this.clctDt = clctDt;
  }


  public String getOprtrIdStr() {
	return oprtrIdStr;
  }

  public void setOprtrIdStr(String oprtrIdStr) {
	this.oprtrIdStr = oprtrIdStr;
  }

  public String getApiId() {
    return apiId;
  }

  public void setApiId(String apiId) {
    this.apiId = apiId;
  }


  public String getFilextId() {
    return filextId;
  }

  public void setFilextId(String filextId) {
    this.filextId = filextId;
  }


  public String getTagId() {
    return tagId;
  }

  public void setTagId(String tagId) {
    this.tagId = tagId;
  }


  public String getAuthCd() {
    return authCd;
  }

  public void setAuthCd(String authCd) {
    this.authCd = authCd;
  }


  public long getOprtrId() {
    return oprtrId;
  }

  public void setOprtrId(long oprtrId) {
    this.oprtrId = oprtrId;
  }


  public String getApiRqstLog() {
    return apiRqstLog;
  }

  public void setApiRqstLog(String apiRqstLog) {
    this.apiRqstLog = apiRqstLog;
  }

  public long getSrchCnt() {
	return srchCnt;
  }

  public void setSrchCnt(long srchCnt) {
	this.srchCnt = srchCnt;
  }

  public long getDwnldCnt() {
	return dwnldCnt;
  }

  public void setDwnldCnt(long dwnldCnt) {
	this.dwnldCnt = dwnldCnt;
  }

  public String getOprtrNm() {
	return oprtrNm;
  }

  public void setOprtrNm(String oprtrNm) {
	this.oprtrNm = oprtrNm;
  }

  public String getOprtrEmail() {
	return oprtrEmail;
  }

  public void setOprtrEmail(String oprtrEmail) {
	this.oprtrEmail = oprtrEmail;
  }

  public String getParameterValue() {
	return parameterValue;
  }

  public void setParameterValue(String parameterValue) {
	this.parameterValue = parameterValue;
  }

  public String getApiCallUrl() {
	return apiCallUrl;
  }

  public void setApiCallUrl(String apiCallUrl) {
	this.apiCallUrl = apiCallUrl;
  }

  public String getResultStatus() {
	return resultStatus;
  }

  public void setResultStatus(String resultStatus) {
	this.resultStatus = resultStatus;
  }

  public int getResponseCnt() {
	return responseCnt;
  }

  public void setResponseCnt(int responseCnt) {
	this.responseCnt = responseCnt;
  }

  public String getOpenApiLogId() {
		return openApiLogId;
  }
  
  public void setOpenApiLogId(String openApiLogId) {
	this.openApiLogId = openApiLogId;
  }
  
}
