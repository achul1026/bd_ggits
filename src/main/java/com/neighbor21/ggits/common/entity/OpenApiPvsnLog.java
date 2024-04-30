package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;
import java.util.Map;

import com.neighbor21.ggits.common.util.GgitsCommonUtils;

public class OpenApiPvsnLog extends CommonEntity {
	private Timestamp clctDt; // 수집일시
	public String dsetId;
	public String apiNm;
	public String apiCallUrl;
	public String mngInstCd;
	public String contents;
	public String dataRegistYmd;
	public String dataUpdtYmd;
	public String dt;
	public String oprtrIp;
	public String apiDescr;
	public Long oprtrId;

	// 파라미터
	//#MOpOperator
	private String oprtrNm; // 운영자 명
	private String oprtrEmail; // 운영자 이메일
	public String mngInstNm;
	public String oprtrIdStr;

	private String parameterValue = "";			// 파라미터 값
    private String resultStatus = "RSC001";    	// 호출 결과 (기본값 실패)
    private int responseCnt = 0;
    
	public Timestamp getClctDt() {
		return clctDt;
	}

	public void setClctDt(Timestamp clctDt) {
		this.clctDt = clctDt;
	}

	public String getDsetId() {
		return dsetId;
	}

	public void setDsetId(String dsetId) {
		this.dsetId = dsetId;
	}

	public String getApiNm() {
		return apiNm;
	}

	public void setApiNm(String apiNm) {
		this.apiNm = apiNm;
	}

	public String getApiCallUrl() {
		return apiCallUrl;
	}

	public void setApiCallUrl(String apiCallUrl) {
		this.apiCallUrl = apiCallUrl;
	}

	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		if(!GgitsCommonUtils.isNull(contents)) {
			Map<String,Object> contentsMap = GgitsCommonUtils.jsonObjectToMap(contents);
			if(!contentsMap.isEmpty()) {
				String param = !GgitsCommonUtils.isNull(contentsMap.get("param")) ? (String)contentsMap.get("param") : "" ;
				String status = !GgitsCommonUtils.isNull(contentsMap.get("status")) ? (String)contentsMap.get("status") : "RSC001" ;
				Double cnt = !GgitsCommonUtils.isNull(contentsMap.get("cnt")) ? (Double) contentsMap.get("cnt") : 0 ;
				
				this.parameterValue = param; //파라미터
				this.resultStatus = status; //상태코드
				this.responseCnt = (int) Math.floor(cnt); //결과 값
			}
		}
		this.contents = contents;
	}

	public String getDataRegistYmd() {
		return dataRegistYmd;
	}

	public void setDataRegistYmd(String dataRegistYmd) {
		this.dataRegistYmd = dataRegistYmd;
	}

	public String getDataUpdtYmd() {
		return dataUpdtYmd;
	}

	public void setDataUpdtYmd(String dataUpdtYmd) {
		this.dataUpdtYmd = dataUpdtYmd;
	}

	public String getDt() {
		return dt;
	}

	public void setDt(String dt) {
		this.dt = dt;
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

	public String getMngInstNm() {
		return mngInstNm;
	}

	public void setMngInstNm(String mngInstNm) {
		this.mngInstNm = mngInstNm;
	}

	public Long getOprtrId() {
		return oprtrId;
	}

	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}

	public String getOprtrIdStr() {
		return oprtrIdStr;
	}

	public void setOprtrIdStr(String oprtrIdStr) {
		this.oprtrIdStr = oprtrIdStr;
	}

	public String getOprtrIp() {
		return oprtrIp;
	}

	public void setOprtrIp(String oprtrIp) {
		this.oprtrIp = oprtrIp;
	}

	public String getApiDescr() {
		return apiDescr;
	}

	public void setApiDescr(String apiDescr) {
		this.apiDescr = apiDescr;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public String getResultStatus() {
		return resultStatus;
	}

	public int getResponseCnt() {
		return responseCnt;
	}
	
}
