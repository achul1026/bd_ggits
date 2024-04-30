package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsSpotLinkStd5M {
	private Timestamp collDate; // 수집일시
	private String linkId; // link_id
	private long vol; // vol
	private long spd; // spd
	private long sec; // sec
	private String decision; // decision
	private String patCd; // 패턴 유형 코드
	private long dataCnt; // 정보생성 자료수
	private long occ; // 점유율

	public Timestamp getCollDate() {
		return collDate;
	}

	public void setCollDate(Timestamp collDate) {
		this.collDate = collDate;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public long getVol() {
		return vol;
	}

	public void setVol(long vol) {
		this.vol = vol;
	}

	public long getSpd() {
		return spd;
	}

	public void setSpd(long spd) {
		this.spd = spd;
	}

	public long getSec() {
		return sec;
	}

	public void setSec(long sec) {
		this.sec = sec;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getPatCd() {
		return patCd;
	}

	public void setPatCd(String patCd) {
		this.patCd = patCd;
	}

	public long getDataCnt() {
		return dataCnt;
	}

	public void setDataCnt(long dataCnt) {
		this.dataCnt = dataCnt;
	}

	public long getOcc() {
		return occ;
	}

	public void setOcc(long occ) {
		this.occ = occ;
	}

}
