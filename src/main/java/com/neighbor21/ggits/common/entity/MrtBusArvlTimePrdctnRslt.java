package com.neighbor21.ggits.common.entity;

import java.sql.Time;

public class MrtBusArvlTimePrdctnRslt {
    private String baseymd;        //기준년월일
    private java.sql.Time updateTm;        //갱신시간
    private String routeId;        //노선id
    private long operSeq;        //운행회차
    private String stStaId;        //출발지id
    private String arrStaId;        //도착지id
    private java.sql.Time stTm;        //출발시간
    private java.sql.Time arrTm;        //도착시간
    private java.sql.Time elTm;        //소요시간
    private String etlDt;        //etl 일시
    private java.sql.Time realArrTm;


    public String getBaseymd() {
        return baseymd;
    }

    public void setBaseymd(String baseymd) {
        this.baseymd = baseymd;
    }


    public java.sql.Time getUpdateTm() {
        return updateTm;
    }

    public void setUpdateTm(java.sql.Time updateTm) {
        this.updateTm = updateTm;
    }


    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }


    public long getOperSeq() {
        return operSeq;
    }

    public void setOperSeq(long operSeq) {
        this.operSeq = operSeq;
    }


    public String getStStaId() {
        return stStaId;
    }

    public void setStStaId(String stStaId) {
        this.stStaId = stStaId;
    }


    public String getArrStaId() {
        return arrStaId;
    }

    public void setArrStaId(String arrStaId) {
        this.arrStaId = arrStaId;
    }


    public java.sql.Time getStTm() {
        return stTm;
    }

    public void setStTm(java.sql.Time stTm) {
        this.stTm = stTm;
    }


    public java.sql.Time getArrTm() {
        return arrTm;
    }

    public void setArrTm(java.sql.Time arrTm) {
        this.arrTm = arrTm;
    }


    public java.sql.Time getElTm() {
        return elTm;
    }

    public void setElTm(java.sql.Time elTm) {
        this.elTm = elTm;
    }


    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public Time getRealArrTm() {
        return realArrTm;
    }

    public void setRealArrTm(Time realArrTm) {
        this.realArrTm = realArrTm;
    }
}
