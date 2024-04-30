package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsDsrcLinkStdCur {
	private String linkId; // 링크 아이디
	private Timestamp collDate; // 수집일시
	private long speed; // 속도
	private long traveltime; // 1분동안의 링크길이에 대한 링크평균속도를 시간으로 환산한 값
	private long datacnt; // 정보생성 자료수
	private String decision; // 혼잡도

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

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public long getTraveltime() {
		return traveltime;
	}

	public void setTraveltime(long traveltime) {
		this.traveltime = traveltime;
	}

	public long getDatacnt() {
		return datacnt;
	}

	public void setDatacnt(long datacnt) {
		this.datacnt = datacnt;
	}

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

}
