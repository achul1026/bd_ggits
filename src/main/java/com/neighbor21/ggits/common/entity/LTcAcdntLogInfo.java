package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//사고 로그 정보
public class LTcAcdntLogInfo extends CommonEntity {

	private String logId; // 로그 아이디
	private Timestamp occurDt; // 발생 일시
	private String acdntId; // 사고 아이디
	private String sesnId; // 세션 아이디
	private String lgnIp; // 로그인 ip
	private String rqstrNm; // 요청자 명
	private String logType; // 로그 유형

	// no table
	private String occurDtStr;
	private String cdNm;

	private String mngInstCd; // 관리 기관코드
	private String oprtrGrd; // 운영자 등급

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Timestamp getOccurDt() {
		return occurDt;
	}

	public void setOccurDt(Timestamp occurDt) {
		this.occurDt = occurDt;
	}

	public String getAcdntId() {
		return acdntId;
	}

	public void setAcdntId(String acdntId) {
		this.acdntId = acdntId;
	}

	public String getSesnId() {
		return sesnId;
	}

	public void setSesnId(String sesnId) {
		this.sesnId = sesnId;
	}

	public String getLgnIp() {
		return lgnIp;
	}

	public void setLgnIp(String lgnIp) {
		this.lgnIp = lgnIp;
	}

	public String getRqstrNm() {
		return rqstrNm;
	}

	public void setRqstrNm(String rqstrNm) {
		this.rqstrNm = rqstrNm;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getOccurDtStr() {
		return occurDtStr;
	}

	public void setOccurDtStr(String occurDtStr) {
		this.occurDtStr = occurDtStr;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	public String getOprtrGrd() {
		return oprtrGrd;
	}

	public void setOprtrGrd(String oprtrGrd) {
		this.oprtrGrd = oprtrGrd;
	}
	
}
