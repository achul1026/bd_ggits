package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//교통사고 이력 데이터 집계
public class MrtAccntLog {
	private Timestamp anlsDt;				// 분석 일시
	private String acdntDstrctIdentifier;	// 사고 구역 식별자
	private String acdntDstrctId;			// 사고 구역 아이디
	private long acdntCnt;					// 사고 수
	private long dcsdCnt;					// 사망자 수
	private long injpsnCnt;					// 부상자 수
	private long totAcdntCnt;				// 전체 사고 수
	private long totDcsdCnt;				// 전체 사망자 수
	private long totInjpsnCnt;				// 전체 부상자 수
	private long popltn100kAcdntCnt;		// 인구 10만 사고 수
	private long vhcl10kAcdntCnt;			// 차량 1만 사고 수
	private long etlDt;						// etl 일시
	
	private String roadName;				// 도로명
	
	public Timestamp getAnlsDt() {
		return anlsDt;
	}
	public void setAnlsDt(Timestamp anlsDt) {
		this.anlsDt = anlsDt;
	}
	public String getAcdntDstrctIdentifier() {
		return acdntDstrctIdentifier;
	}
	public void setAcdntDstrctIdentifier(String acdntDstrctIdentifier) {
		this.acdntDstrctIdentifier = acdntDstrctIdentifier;
	}
	public String getAcdntDstrctId() {
		return acdntDstrctId;
	}
	public void setAcdntDstrctId(String acdntDstrctId) {
		this.acdntDstrctId = acdntDstrctId;
	}
	public long getAcdntCnt() {
		return acdntCnt;
	}
	public void setAcdntCnt(long acdntCnt) {
		this.acdntCnt = acdntCnt;
	}
	public long getDcsdCnt() {
		return dcsdCnt;
	}
	public void setDcsdCnt(long dcsdCnt) {
		this.dcsdCnt = dcsdCnt;
	}
	public long getInjpsnCnt() {
		return injpsnCnt;
	}
	public void setInjpsnCnt(long injpsnCnt) {
		this.injpsnCnt = injpsnCnt;
	}
	public long getTotAcdntCnt() {
		return totAcdntCnt;
	}
	public void setTotAcdntCnt(long totAcdntCnt) {
		this.totAcdntCnt = totAcdntCnt;
	}
	public long getTotDcsdCnt() {
		return totDcsdCnt;
	}
	public void setTotDcsdCnt(long totDcsdCnt) {
		this.totDcsdCnt = totDcsdCnt;
	}
	public long getTotInjpsnCnt() {
		return totInjpsnCnt;
	}
	public void setTotInjpsnCnt(long totInjpsnCnt) {
		this.totInjpsnCnt = totInjpsnCnt;
	}
	public long getPopltn100kAcdntCnt() {
		return popltn100kAcdntCnt;
	}
	public void setPopltn100kAcdntCnt(long popltn100kAcdntCnt) {
		this.popltn100kAcdntCnt = popltn100kAcdntCnt;
	}
	public long getVhcl10kAcdntCnt() {
		return vhcl10kAcdntCnt;
	}
	public void setVhcl10kAcdntCnt(long vhcl10kAcdntCnt) {
		this.vhcl10kAcdntCnt = vhcl10kAcdntCnt;
	}
	public long getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(long etlDt) {
		this.etlDt = etlDt;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
}
