package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusSttnFctlAnal extends CommonEntity{
	private Timestamp anlsDt;	// 분석 일시
	private String bstpId;		// 버스정류장 아이디
	private String bstpType;	// 버스정류장 유형
	private long bitCnt;		// bit 수
	private String bitType;		// bit 유형
	private String addngCd;		// 행정동 코드 
	private String etlDt;		// etl 일시
	
	private String bstpNm;		// 버스정류장 명
	
	public Timestamp getAnlsDt() {
		return anlsDt;
	}
	public void setAnlsDt(Timestamp anlsDt) {
		this.anlsDt = anlsDt;
	}
	public String getBstpId() {
		return bstpId;
	}
	public void setBstpId(String bstpId) {
		this.bstpId = bstpId;
	}
	public String getBstpType() {
		return bstpType;
	}
	public void setBstpType(String bstpType) {
		this.bstpType = bstpType;
	}
	public long getBitCnt() {
		return bitCnt;
	}
	public void setBitCnt(long bitCnt) {
		this.bitCnt = bitCnt;
	}
	public String getBitType() {
		return bitType;
	}
	public void setBitType(String bitType) {
		this.bitType = bitType;
	}
	public String getAddngCd() {
		return addngCd;
	}
	public void setAddngCd(String addngCd) {
		this.addngCd = addngCd;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public String getBstpNm() {
		return bstpNm;
	}
	public void setBstpNm(String bstpNm) {
		this.bstpNm = bstpNm;
	}
	
	
}
