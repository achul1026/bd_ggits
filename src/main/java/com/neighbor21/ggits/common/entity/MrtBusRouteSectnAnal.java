package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusRouteSectnAnal {
    private Timestamp anlsDt;        //분석 일시
    private String startBstpId;        //시작 버스정류장 아이디
    private String endBstpId;        //종료 버스정류장 아이디
    private String startMobileNo;        //시작 버스정류장 아이디
    private String endMobileNo;        //종료 버스정류장 아이디
    private String busRouteId;        //버스 노선 아이디
    private String sctnId;        //구간 아이디
    private String vhclId;        //차량 아이디
    private Long bstpSctnLen;        //버스정류장 구간 길이
    private Long passTime;        //통행 시간
    private Long stpvehTime;        //정차 시간
    private String etlDt;        //etl 일시

    private String routeNm;            // 노선명
    private String routeInterval;    // 배차 간격
    private String stStaNm;        // 출발지명
    private String edStaNm;        // 도착지명
    private String routeId;        // 노선 ID
    private String routeTp;
    private String districtGnm;
    private String districtSnm;
    Long duplCnt;
    private String geojson;
    private String companyNm;
    private String stToEd;

    public String getStToEd(){
        return stToEd;
    }

    public void setStToEd(String stToEd){
        this.stToEd = stToEd;
    }

    public Timestamp getAnlsDt() {
        return anlsDt;
    }

    public void setAnlsDt(Timestamp anlsDt) {
        this.anlsDt = anlsDt;
    }

    public String getStartBstpId() {
        return startBstpId;
    }

    public void setStartBstpId(String startBstpId) {
        this.startBstpId = startBstpId;
    }

    public String getEndBstpId() {
        return endBstpId;
    }

    public void setEndBstpId(String endBstpId) {
        this.endBstpId = endBstpId;
    }

    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }

    public String getSctnId() {
        return sctnId;
    }

    public void setSctnId(String sctnId) {
        this.sctnId = sctnId;
    }

    public String getVhclId() {
        return vhclId;
    }

    public void setVhclId(String vhclId) {
        this.vhclId = vhclId;
    }

    public Long getBstpSctnLen() {
        return bstpSctnLen;
    }

    public void setBstpSctnLen(Long bstpSctnLen) {
        this.bstpSctnLen = bstpSctnLen;
    }

    public Long getPassTime() {
        return passTime;
    }

    public void setPassTime(Long passTime) {
        this.passTime = passTime;
    }

    public Long getStpvehTime() {
        return stpvehTime;
    }

    public void setStpvehTime(Long stpvehTime) {
        this.stpvehTime = stpvehTime;
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

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteTp() {
        return routeTp;
    }

    public void setRouteTp(String routeTp) {
        this.routeTp = routeTp;
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

    public Long getDuplCnt() {
        return duplCnt;
    }

    public void setDuplCnt(Long duplCnt) {
        this.duplCnt = duplCnt;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
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
}
