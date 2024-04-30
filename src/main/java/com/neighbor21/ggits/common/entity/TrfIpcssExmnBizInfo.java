package com.neighbor21.ggits.common.entity;

//교통 영향평가 조사 사업 정보
public class TrfIpcssExmnBizInfo extends CommonEntity{
	private String ipcssMngNo;		// 영향평가 관리 번호
	private String exmnYy;			// 조사 년
	private String evalCoNm;		// 평가 회사 명
	private String evalPicNm;		// 평가 담당자명
	private String bizNm;			// 사업명
	private String bizUsg;			// 사업 용도
	private String exmnDd;			// 조사 일
	private String etlDt;			// etl 일시
	
	public String getIpcssMngNo() {
		return ipcssMngNo;
	}
	public void setIpcssMngNo(String ipcssMngNo) {
		this.ipcssMngNo = ipcssMngNo;
	}
	public String getExmnYy() {
		return exmnYy;
	}
	public void setExmnYy(String exmnYy) {
		this.exmnYy = exmnYy;
	}
	public String getEvalCoNm() {
		return evalCoNm;
	}
	public void setEvalCoNm(String evalCoNm) {
		this.evalCoNm = evalCoNm;
	}
	public String getEvalPicNm() {
		return evalPicNm;
	}
	public void setEvalPicNm(String evalPicNm) {
		this.evalPicNm = evalPicNm;
	}
	public String getBizNm() {
		return bizNm;
	}
	public void setBizNm(String bizNm) {
		this.bizNm = bizNm;
	}
	public String getBizUsg() {
		return bizUsg;
	}
	public void setBizUsg(String bizUsg) {
		this.bizUsg = bizUsg;
	}
	public String getExmnDd() {
		return exmnDd;
	}
	public void setExmnDd(String exmnDd) {
		this.exmnDd = exmnDd;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	
	
}
