package com.neighbor21.ggits.common.entity;

// 버스 노선 운행정보 이력 집계
public class MrtBusRungLogAnal extends CommonEntity{
	private String clctYmd;		// 수집 일자
	private String busRouteId;	// 버스 노선 아이디
	private String coId;		// 회사 아이디
	private long trsfrCnt;		// 환승 수
	private long psgrCnt;		// 승객 수
	private String rideBstpId;	// 승차 버스정류장 아이디
	private String rideDt;		// 승차 일시
	private String lndiBstpId;	// 하차 버스정류장 아이디
	private String lndiDt;		// 하차 일시
	private long busUserCnt;	// 버스 사용자 수
	private long busRungDstne;	// 버스 운행 거리
	private String etlDt;		// etl 일시
	
	private String routeNm;		// 구간 명
	private String stStaNm;		// 출발지
	private String edStaNm;		// 도착지
	
	public String getClctYmd() {
		return clctYmd;
	}
	public void setClctYmd(String clctYmd) {
		this.clctYmd = clctYmd;
	}
	public String getBusRouteId() {
		return busRouteId;
	}
	public void setBusRouteId(String busRouteId) {
		this.busRouteId = busRouteId;
	}
	public String getCoId() {
		return coId;
	}
	public void setCoId(String coId) {
		this.coId = coId;
	}
	public long getTrsfrCnt() {
		return trsfrCnt;
	}
	public void setTrsfrCnt(long trsfrCnt) {
		this.trsfrCnt = trsfrCnt;
	}
	public long getPsgrCnt() {
		return psgrCnt;
	}
	public void setPsgrCnt(long psgrCnt) {
		this.psgrCnt = psgrCnt;
	}
	public String getRideBstpId() {
		return rideBstpId;
	}
	public void setRideBstpId(String rideBstpId) {
		this.rideBstpId = rideBstpId;
	}
	public String getRideDt() {
		return rideDt;
	}
	public void setRideDt(String rideDt) {
		this.rideDt = rideDt;
	}
	public String getLndiBstpId() {
		return lndiBstpId;
	}
	public void setLndiBstpId(String lndiBstpId) {
		this.lndiBstpId = lndiBstpId;
	}
	public String getLndiDt() {
		return lndiDt;
	}
	public void setLndiDt(String lndiDt) {
		this.lndiDt = lndiDt;
	}
	public long getBusUserCnt() {
		return busUserCnt;
	}
	public void setBusUserCnt(long busUserCnt) {
		this.busUserCnt = busUserCnt;
	}
	public long getBusRungDstne() {
		return busRungDstne;
	}
	public void setBusRungDstne(long busRungDstne) {
		this.busRungDstne = busRungDstne;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public String getRouteNm() {
		return routeNm;
	}
	public void setRouteNm(String routeNm) {
		this.routeNm = routeNm;
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
