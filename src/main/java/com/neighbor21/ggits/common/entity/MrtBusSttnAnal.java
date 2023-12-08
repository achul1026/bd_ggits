package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// 버스 정류장 노선유형 정보
public class MrtBusSttnAnal extends CommonEntity{
	private Timestamp anlsDt;			// 분석 일시
	private String bstpId;				// 버스정류장 아이디
	private String busRouteId;			// 버스 노선 아이디
	private String useYn;				// 사용 여부
	private String routeNm;				// 노선 명
	private String bstpNm;				// 버스정류장 명
	private long rideUserCnt;			// 승차 사용자 수
	private long lndiUserCnt;			// 하차 사용자 수
	private long trnsitUserCnt;			// 환승 사용자 수
	private String etlDt;				// etl 일시
	
	private String routeInterval;		// 배차간격
	private String stStaNm;				// 출발지
	private String edStaNm;				// 종착지
	
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
	public String getBusRouteId() {
		return busRouteId;
	}
	public void setBusRouteId(String busRouteId) {
		this.busRouteId = busRouteId;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getRouteNm() {
		return routeNm;
	}
	public void setRouteNm(String routeNm) {
		this.routeNm = routeNm;
	}
	public String getBstpNm() {
		return bstpNm;
	}
	public void setBstpNm(String bstpNm) {
		this.bstpNm = bstpNm;
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
	public String getRouteInterval() {
		return routeInterval;
	}
	public void setRouteInterval(String routeInterval) {
		this.routeInterval = routeInterval;
	}
	public String getStStaNm() {
		return stStaNm;
	}
	public void setStStaNm(String stStaNm) {
		this.stStaNm = stStaNm;
	}
	public String getEdStaNm() {
		return edStaNm;
	}
	public void setEdStaNm(String edStaNm) {
		this.edStaNm = edStaNm;
	}
}
