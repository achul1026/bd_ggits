package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusRoutePasngAnal {
    private Timestamp anlsDt;        //분석 일시
    private String busRouteId;       //버스 노선 아이디
    private String meanCd;            //수단 코드
    private String rideBusId;        //승차역 id
    private String rideDt;            //승차 일시
    private String quitBusId;        //하차역id
    private String quitDt;            //하차일시
    private Long pass1;                //승객1
    private Long pass2;                //승객2
    private Long pass3;                //승객3
    private Long passengers;
    private Long lndiPassengers;
    private Long ridePassengers;
    private String etlDt;            //etl 일시

    private String routeNm;                //노선 명
    private String stStaNm;                // 출발지
    private String edStaNm;                // 도착지
    private String totalPasngCnt;        // 전체 승객수
    private String startStationId;        // 출발지 아이디
    private String startMobileNo;        // 출발지 아이디
    private String endStationId;        // 도착지 아이디
    private String endMobileNo;        // 도착지 아이디
    private String startStationNm;        // 출발지 아이디
    private String endStationNm;        // 도착지 아이디
    private String sidoCd;
    private String routeTp;
    private String mobileNo;
    private String stationNm;
    private String stationId;

    private Double mapX;
    private Double mapY;
    private String districtGnm;
    private String districtSnm;
    private Long pagingTotalCount;

    public Timestamp getAnlsDt() {
        return anlsDt;
    }

    public void setAnlsDt(Timestamp anlsDt) {
        this.anlsDt = anlsDt;
    }


    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }


    public String getMeanCd() {
        return meanCd;
    }

    public void setMeanCd(String meanCd) {
        this.meanCd = meanCd;
    }


    public String getRideBusId() {
        return rideBusId;
    }

    public void setRideBusId(String rideBusId) {
        this.rideBusId = rideBusId;
    }


    public String getRideDt() {
        return rideDt;
    }

    public void setRideDt(String rideDt) {
        this.rideDt = rideDt;
    }


    public String getQuitBusId() {
        return quitBusId;
    }

    public void setQuitBusId(String quitBusId) {
        this.quitBusId = quitBusId;
    }


    public String getQuitDt() {
        return quitDt;
    }

    public void setQuitDt(String quitDt) {
        this.quitDt = quitDt;
    }


    public Long getPass1() {
        return pass1;
    }

    public void setPass1(Long pass1) {
        this.pass1 = pass1;
    }


    public Long getPass2() {
        return pass2;
    }

    public void setPass2(Long pass2) {
        this.pass2 = pass2;
    }


    public Long getPass3() {
        return pass3;
    }

    public void setPass3(Long pass3) {
        this.pass3 = pass3;
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

    public String getTotalPasngCnt() {
        return totalPasngCnt;
    }

    public void setTotalPasngCnt(String totalPasngCnt) {
        this.totalPasngCnt = totalPasngCnt;
    }

    public String getStartStationId() {
        return startStationId;
    }

    public void setStartStationId(String startStationId) {
        this.startStationId = startStationId;
    }

    public String getEndStationId() {
        return endStationId;
    }

    public void setEndStationId(String endStationId) {
        this.endStationId = endStationId;
    }

    public Long getPassengers() {
        return passengers;
    }

    public void setPassengers(Long passengers) {
        this.passengers = passengers;
    }

    public String getStartStationNm() {
        return startStationNm;
    }

    public void setStartStationNm(String startStationNm) {
        this.startStationNm = startStationNm;
    }

    public String getEndStationNm() {
        return endStationNm;
    }

    public void setEndStationNm(String endStationNm) {
        this.endStationNm = endStationNm;
    }

    public String getRouteTp() {
        return routeTp;
    }

    public void setRouteTp(String routeTp) {
        this.routeTp = routeTp;
    }

    public Double getMapX() {
        return mapX;
    }

    public void setMapX(Double mapX) {
        this.mapX = mapX;
    }

    public Double getMapY() {
        return mapY;
    }

    public void setMapY(Double mapY) {
        this.mapY = mapY;
    }

    public String getSidoCd() {
        return sidoCd;
    }

    public void setSidoCd(String sidoCd) {
        this.sidoCd = sidoCd;
    }

    public String getStartMobileNo() {
        return startMobileNo;
    }

    public void setStartMobileNo(String startMobileNo) {
        this.startMobileNo = startMobileNo;
    }

    public String getEndMobileNo() {
        return endMobileNo;
    }

    public void setEndMobileNo(String endMobileNo) {
        this.endMobileNo = endMobileNo;
    }

    public String getDistrictGnm() {
        return districtGnm;
    }

    public void setDistrictGnm(String districtGnm) {
        this.districtGnm = districtGnm;
    }

    public String getDistrictSnm() {
        return districtSnm;
    }

    public void setDistrictSnm(String districtSnm) {
        this.districtSnm = districtSnm;
    }

    public Long getLndiPassengers() {
        return lndiPassengers;
    }

    public void setLndiPassengers(Long lndiPassengers) {
        this.lndiPassengers = lndiPassengers;
    }

    public Long getRidePassengers() {
        return ridePassengers;
    }

    public void setRidePassengers(Long ridePassengers) {
        this.ridePassengers = ridePassengers;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getStationNm() {
        return stationNm;
    }

    public void setStationNm(String stationNm) {
        this.stationNm = stationNm;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Long getPagingTotalCount() {
        return pagingTotalCount;
    }

    public void setPagingTotalCount(Long pagingTotalCount) {
        this.pagingTotalCount = pagingTotalCount;
    }
}
