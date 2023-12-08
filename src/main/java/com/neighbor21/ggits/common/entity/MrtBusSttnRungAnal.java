package com.neighbor21.ggits.common.entity;

public class MrtBusSttnRungAnal extends CommonEntity{
	private String rideYmd;			// 승차 일자
	private String bstpId;			// 버스정류장 아이디
	private long busRouteRungCnt;	// 버스 노선 운행 수
	private long rideUserCnt;		// 승차 사용자 수
	private long lndiUserCnt;		// 하차 사용자 수
	private long trnsitUserCnt;		// 환승 사용자 수
	private String etlDt;			// etl 일시
	
	
	private String bstpNm;			// 정류장 명
	
	public String getRideYmd() {
		return rideYmd;
	}
	public void setRideYmd(String rideYmd) {
		this.rideYmd = rideYmd;
	}
	public String getBstpId() {
		return bstpId;
	}
	public void setBstpId(String bstpId) {
		this.bstpId = bstpId;
	}
	public long getBusRouteRungCnt() {
		return busRouteRungCnt;
	}
	public void setBusRouteRungCnt(long busRouteRungCnt) {
		this.busRouteRungCnt = busRouteRungCnt;
	}
	public long getRideUserCnt() {
		return rideUserCnt;
	}
	public void setRideUserCnt(long rideUserCnt) {
		this.rideUserCnt = rideUserCnt;
	}
	public long getLndiUserCnt() {
		return lndiUserCnt;
	}
	public void setLndiUserCnt(long lndiUserCnt) {
		this.lndiUserCnt = lndiUserCnt;
	}
	public long getTrnsitUserCnt() {
		return trnsitUserCnt;
	}
	public void setTrnsitUserCnt(long trnsitUserCnt) {
		this.trnsitUserCnt = trnsitUserCnt;
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
