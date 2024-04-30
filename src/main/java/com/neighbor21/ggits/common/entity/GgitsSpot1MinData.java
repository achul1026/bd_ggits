package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsSpot1MinData {
	private Timestamp collDate; // 수집일시
	private String spotId; // 지점 id
	private long vol; // 교통량
	private long spd; // 속도
	private long occ; // 점유율
	private String decision; // 혼잡도
	private long dataCnt; // 자료수(정보생성 자료수)

	public Timestamp getCollDate() {
		return collDate;
	}

	public void setCollDate(Timestamp collDate) {
		this.collDate = collDate;
	}

	public String getSpotId() {
		return spotId;
	}

	public void setSpotId(String spotId) {
		this.spotId = spotId;
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

	public long getOcc() {
		return occ;
	}

	public void setOcc(long occ) {
		this.occ = occ;
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

}
