package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// 버스 정류장 노선유형 정보
public class MrtBusSttnAnal extends CommonEntity {
    private Timestamp anlsDt;            // 분석 일시
    private String bstpId;                // 버스정류장 아이디
    private String busRouteId;            // 버스 노선 아이디
    private String useYn;                // 사용 여부
    private String routeNm;                // 노선 명
    private String bstpNm;                // 버스정류장 명
    private Long rideUserCnt;            // 승차 사용자 수
    private Long lndiUserCnt;            // 하차 사용자 수
    private Long trnsitUserCnt;            // 환승 사용자 수
    private String etlDt;                // etl 일시

    private String routeTp;        		// 노선종류
    private String routeInterval;        // 배차간격
    private String stStaNm;                // 출발지
    private String edStaNm;                // 종착지
    private Long mobileNo;
    
    //#GgbisBusStation
    private String stationNm;
    private String adminNm;
    
    //none col
    private Long total;
    
    
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

    @Override
    public String getUseYn() {
        return useYn;
    }

    @Override
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

    public Long getRideUserCnt() {
        return rideUserCnt;
    }

    public void setRideUserCnt(Long rideUserCnt) {
        this.rideUserCnt = rideUserCnt;
    }

    public Long getLndiUserCnt() {
        return lndiUserCnt;
    }

    public void setLndiUserCnt(Long lndiUserCnt) {
        this.lndiUserCnt = lndiUserCnt;
    }

    public Long getTrnsitUserCnt() {
        return trnsitUserCnt;
    }

    public void setTrnsitUserCnt(Long trnsitUserCnt) {
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

    @Override
    public String getRouteTp() {
        return routeTp;
    }

    @Override
    public void setRouteTp(String routeTp) {
        this.routeTp = routeTp;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

	public String getStationNm() {
		return stationNm;
	}

	public void setStationNm(String stationNm) {
		this.stationNm = stationNm;
	}

	public String getAdminNm() {
		return adminNm;
	}

	public void setAdminNm(String adminNm) {
		this.adminNm = adminNm;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
