package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//도로 사고 유형 분석
public class MrtRoadAccntAnal {
	private Timestamp infoOccurDt;			// 정보 발생 일시
	private String acdntDstrctIdentifier;	// 사고 구역 식별자
	private String acdntDstrctId;			// 사고 구역 아이디
	private String acdntTypeCd;				// 사고 유형 코드
	private long acdntOccurCnt;				// 사고 발생 수
	private long totCasltCnt;				// 전체 사상자 수
	private long dcsdCnt;					// 사망자 수
	private String etlDt;					// etl 일시
	
	private String roadName;				// 도로명
	private String cdNm;					// 공통 코드 명
	private long ftltyRate;					// 치사율
	private String roadRank;				// 도로유형
	private long maxAcdntCnt;				// 주요 사고 건수
	
	public Timestamp getInfoOccurDt() {
		return infoOccurDt;
	}
	public void setInfoOccurDt(Timestamp infoOccurDt) {
		this.infoOccurDt = infoOccurDt;
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
	public String getAcdntTypeCd() {
		return acdntTypeCd;
	}
	public void setAcdntTypeCd(String acdntTypeCd) {
		this.acdntTypeCd = acdntTypeCd;
	}
	public long getAcdntOccurCnt() {
		return acdntOccurCnt;
	}
	public void setAcdntOccurCnt(long acdntOccurCnt) {
		this.acdntOccurCnt = acdntOccurCnt;
	}
	public long getTotCasltCnt() {
		return totCasltCnt;
	}
	public void setTotCasltCnt(long totCasltCnt) {
		this.totCasltCnt = totCasltCnt;
	}
	public long getDcsdCnt() {
		return dcsdCnt;
	}
	public void setDcsdCnt(long dcsdCnt) {
		this.dcsdCnt = dcsdCnt;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public long getFtltyRate() {
		return ftltyRate;
	}
	public void setFtltyRate(long ftltyRate) {
		this.ftltyRate = ftltyRate;
	}
	public String getRoadRank() {
		return roadRank;
	}
	public void setRoadRank(String roadRank) {
		this.roadRank = roadRank;
	}
	public long getMaxAcdntCnt() {
		return maxAcdntCnt;
	}
	public void setMaxAcdntCnt(long maxAcdntCnt) {
		this.maxAcdntCnt = maxAcdntCnt;
	}

}
