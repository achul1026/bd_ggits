package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsSpotLinkStdCur {
	private String linkId; // 링크 아이디
	private Timestamp collDate; // 수집일시
	private long spd; // 속도
	private long vol; // 교통량
	private long sec; // 소요시간
	private String decision; // 혼잡도
	private long dataCnt; // 정보생성 자료수
	private long occ; // 점유율

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public Timestamp getCollDate() {
		return collDate;
	}

	public void setCollDate(Timestamp collDate) {
		this.collDate = collDate;
	}

	public long getSpd() {
		return spd;
	}

	public void setSpd(long spd) {
		this.spd = spd;
	}

	public long getVol() {
		return vol;
	}

	public void setVol(long vol) {
		this.vol = vol;
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
