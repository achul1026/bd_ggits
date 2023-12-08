package com.neighbor21.ggits.common.entity;

import java.util.List;

//dsrc 시설물 정보
public class AdsiMFaDsrc extends AdsiRseSttsInfo {

    private String mngInstCd; //관리 기관 코드
    private String rseId; //rse 아이디
    private String rseNm; //rse 명
    private String dsrcId; //dsrc 아이디
    private double lonCrdn; //경도 좌표
    private double latCrdn; //위도 좌표
    private String descr; //설명

    private List<AdsiDsrcColctInfo> colctInfo;

    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }


    public String getRseId() {
        return rseId;
    }

    public void setRseId(String rseId) {
        this.rseId = rseId;
    }


    public String getRseNm() {
        return rseNm;
    }

    public void setRseNm(String rseNm) {
        this.rseNm = rseNm;
    }


    public String getDsrcId() {
        return dsrcId;
    }

    public void setDsrcId(String dsrcId) {
        this.dsrcId = dsrcId;
    }

    public double getLonCrdn() {
        return lonCrdn;
    }

    public void setLonCrdn(double lonCrdn) {
        this.lonCrdn = lonCrdn;
    }

    public double getLatCrdn() {
        return latCrdn;
    }

    public void setLatCrdn(double latCrdn) {
        this.latCrdn = latCrdn;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<AdsiDsrcColctInfo> getColctInfo() {
        return colctInfo;
    }

    public void setColctInfo(List<AdsiDsrcColctInfo> colctInfo) {
        this.colctInfo = colctInfo;
    }
}
