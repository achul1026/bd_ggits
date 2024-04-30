package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

/**
 * 설명
 *
 * @author : KY.LEE
 * @fileName :  GgsplBusPeriodicinfoCur
 * @since : 2023-12-04
 */
public class GgsplBusPeriodicinfoCur extends CommonEntity {
    String collectId;
    Long serverNo;
    String vehId;
    Timestamp collectDate;
    String routeId;
    Timestamp systemDate;
    Double gpsX;
    Double gpsY;
    Timestamp ebcollectDate;
    Timestamp bmscollectDate;
    Long pacNo;
    Long collectPeriod;
    String lvehCd;
    String lvehStationid;
    Long remainseatCnt;
    Long passengerCnt;
    Long totalseatCnt;
    Double partNo;

     String routeNm; //노선이름
     String plateNo;
     String companyId;

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public Long getServerNo() {
        return serverNo;
    }

    public void setServerNo(Long serverNo) {
        this.serverNo = serverNo;
    }

    public String getVehId() {
        return vehId;
    }

    public void setVehId(String vehId) {
        this.vehId = vehId;
    }

    public Timestamp getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Timestamp collectDate) {
        this.collectDate = collectDate;
    }

    @Override
    public String getRouteId() {
        return routeId;
    }

    @Override
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Timestamp getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Timestamp systemDate) {
        this.systemDate = systemDate;
    }

    public Double getGpsX() {
        return gpsX;
    }

    public void setGpsX(Double gpsX) {
        this.gpsX = gpsX;
    }

    public Double getGpsY() {
        return gpsY;
    }

    public void setGpsY(Double gpsY) {
        this.gpsY = gpsY;
    }

    public Timestamp getEbcollectDate() {
        return ebcollectDate;
    }

    public void setEbcollectDate(Timestamp ebcollectDate) {
        this.ebcollectDate = ebcollectDate;
    }

    public Timestamp getBmscollectDate() {
        return bmscollectDate;
    }

    public void setBmscollectDate(Timestamp bmscollectDate) {
        this.bmscollectDate = bmscollectDate;
    }

    public Long getPacNo() {
        return pacNo;
    }

    public void setPacNo(Long pacNo) {
        this.pacNo = pacNo;
    }

    public Long getCollectPeriod() {
        return collectPeriod;
    }

    public void setCollectPeriod(Long collectPeriod) {
        this.collectPeriod = collectPeriod;
    }

    public String getLvehCd() {
        return lvehCd;
    }

    public void setLvehCd(String lvehCd) {
        this.lvehCd = lvehCd;
    }

    public String getLvehStationid() {
        return lvehStationid;
    }

    public void setLvehStationid(String lvehStationid) {
        this.lvehStationid = lvehStationid;
    }

    public Long getRemainseatCnt() {
        return remainseatCnt;
    }

    public void setRemainseatCnt(Long remainseatCnt) {
        this.remainseatCnt = remainseatCnt;
    }

    public Long getPassengerCnt() {
        return passengerCnt;
    }

    public void setPassengerCnt(Long passengerCnt) {
        this.passengerCnt = passengerCnt;
    }

    public Long getTotalseatCnt() {
        return totalseatCnt;
    }

    public void setTotalseatCnt(Long totalseatCnt) {
        this.totalseatCnt = totalseatCnt;
    }

    public Double getPartNo() {
        return partNo;
    }

    public void setPartNo(Double partNo) {
        this.partNo = partNo;
    }

    public String getRouteNm() {
        return routeNm;
    }

    public void setRouteNm(String routeNm) {
        this.routeNm = routeNm;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
