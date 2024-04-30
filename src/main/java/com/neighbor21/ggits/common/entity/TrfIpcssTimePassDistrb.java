package com.neighbor21.ggits.common.entity;

import java.util.List;

// 교통 영향평가 시간대별 통행분포
public class TrfIpcssTimePassDistrb {
	private String ipcssMngNo;			// 영향평가 관리 번호
	private String dywkDiv;				// 요일 구분
	private String usgCd;				// 용도 코드
	private String trfUseCd;			// 교통 사용 코드
	private String timeSctnNm;			// 시간 구간 명
	private double inflRt;				// 유입 비율
	private double outflRt;				// 유출 비율
	private double totInflRt;			// 전체 유입 비율
	private double totOutflRt;			// 전체 유출 비율
	private String etlDt;				// etl 일시
	private String usgNo;				// 용도 순서
	private String bizNm;				// 사업명
	private String bizUsg;				// 사업용도
	private List<String> usgCdList; 	// 용도 코드 리스트
	
	public String getIpcssMngNo() {
		return ipcssMngNo;
	}
	public void setIpcssMngNo(String ipcssMngNo) {
		this.ipcssMngNo = ipcssMngNo;
	}
	public String getDywkDiv() {
		return dywkDiv;
	}
	public void setDywkDiv(String dywkDiv) {
		this.dywkDiv = dywkDiv;
	}
	public String getUsgCd() {
		return usgCd;
	}
	public void setUsgCd(String usgCd) {
		this.usgCd = usgCd;
	}
	public String getTrfUseCd() {
		return trfUseCd;
	}
	public void setTrfUseCd(String trfUseCd) {
		this.trfUseCd = trfUseCd;
	}
	public String getTimeSctnNm() {
		return timeSctnNm;
	}
	public void setTimeSctnNm(String timeSctnNm) {
		this.timeSctnNm = timeSctnNm;
	}
	public double getInflRt() {
		return inflRt;
	}
	public void setInflRt(double inflRt) {
		this.inflRt = inflRt;
	}
	public double getOutflRt() {
		return outflRt;
	}
	public void setOutflRt(double outflRt) {
		this.outflRt = outflRt;
	}
	public double getTotInflRt() {
		return totInflRt;
	}
	public void setTotInflRt(double totInflRt) {
		this.totInflRt = totInflRt;
	}
	public double getTotOutflRt() {
		return totOutflRt;
	}
	public void setTotOutflRt(double totOutflRt) {
		this.totOutflRt = totOutflRt;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public List<String> getUsgCdList() {
		return usgCdList;
	}
	public void setUsgCdList(List<String> usgCdList) {
		this.usgCdList = usgCdList;
	}
	public String getUsgNo() {
		return usgNo;
	}
	public void setUsgNo(String usgNo) {
		this.usgNo = usgNo;
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
	
}