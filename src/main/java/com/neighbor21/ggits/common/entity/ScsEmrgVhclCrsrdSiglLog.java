package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ScsEmrgVhclCrsrdSiglLog {
	private Timestamp collectdt; // 수집일시
	private String serviceid; // 서비스 id
	private long intlcno; // 교차로 번호
	private long inttype; // 교차로 타입
	private long remaindist; // 교차로 남은거리
	private long state; // 교차로운영상태
	private long plan; // 현재 운영중인 맵 번호
	private long aringphase; // a링 현시번호
	private long bringphase; // b링 현시번호
	private long holdphase; // 유지현시번호
	private long ovlpintlcno; // 중첩 교차로번호
	private String evno; // 차량 번호

	public Timestamp getCollectdt() {
		return collectdt;
	}

	public void setCollectdt(Timestamp collectdt) {
		this.collectdt = collectdt;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public long getIntlcno() {
		return intlcno;
	}

	public void setIntlcno(long intlcno) {
		this.intlcno = intlcno;
	}

	public long getInttype() {
		return inttype;
	}

	public void setInttype(long inttype) {
		this.inttype = inttype;
	}

	public long getRemaindist() {
		return remaindist;
	}

	public void setRemaindist(long remaindist) {
		this.remaindist = remaindist;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public long getPlan() {
		return plan;
	}

	public void setPlan(long plan) {
		this.plan = plan;
	}

	public long getAringphase() {
		return aringphase;
	}

	public void setAringphase(long aringphase) {
		this.aringphase = aringphase;
	}

	public long getBringphase() {
		return bringphase;
	}

	public void setBringphase(long bringphase) {
		this.bringphase = bringphase;
	}

	public long getHoldphase() {
		return holdphase;
	}

	public void setHoldphase(long holdphase) {
		this.holdphase = holdphase;
	}

	public long getOvlpintlcno() {
		return ovlpintlcno;
	}

	public void setOvlpintlcno(long ovlpintlcno) {
		this.ovlpintlcno = ovlpintlcno;
	}

	public String getEvno() {
		return evno;
	}

	public void setEvno(String evno) {
		this.evno = evno;
	}

}
