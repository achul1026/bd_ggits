package com.neighbor21.ggits.common.entity;


//dsrc 시설물 정보
public class AdsiMFaDsrc extends AdsiRseSttsInfo {

    private String mngInstCd; //관리 기관 코드
    private String rseId; //rse 아이디
    private String rseNm; //rse 명
    private String dsrcId; //dsrc 아이디
    private Double lonCrdn; //경도 좌표
    private Double latCrdn; //위도 좌표
    private String descr; //설명

    private String colctInfo;
    
    @Override
    public String getMngInstCd() {
        return mngInstCd;
    }

    @Override
    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    @Override
    public String getRseId() {
        return rseId;
    }

    @Override
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

    public Double getLonCrdn() {
        return lonCrdn;
    }

    public void setLonCrdn(Double lonCrdn) {
        this.lonCrdn = lonCrdn;
    }

    public Double getLatCrdn() {
        return latCrdn;
    }

    public void setLatCrdn(Double latCrdn) {
        this.latCrdn = latCrdn;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getColctInfo() {
        return colctInfo;
    }

    public void setColctInfo(String colctInfo) {
        this.colctInfo = colctInfo;
    }
}
