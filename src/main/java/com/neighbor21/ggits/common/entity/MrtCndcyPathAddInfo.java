package com.neighbor21.ggits.common.entity;

public class MrtCndcyPathAddInfo {
    private String baseym;
    private String btcId;
    private String candRouteId;
    private Long objFuncId;
    private Double length;
    private Double lengthRatio;
    private Long score;
    private String etlDt;

    public String getBaseym() {
        return baseym;
    }

    public void setBaseym(String baseym) {
        this.baseym = baseym;
    }

    public String getBtcId() {
        return btcId;
    }

    public void setBtcId(String btcId) {
        this.btcId = btcId;
    }

    public String getCandRouteId() {
        return candRouteId;
    }

    public void setCandRouteId(String candRouteId) {
        this.candRouteId = candRouteId;
    }

    public Long getObjFuncId() {
        return objFuncId;
    }

    public void setObjFuncId(Long objFuncId) {
        this.objFuncId = objFuncId;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getLengthRatio() {
        return lengthRatio;
    }

    public void setLengthRatio(Double lengthRatio) {
        this.lengthRatio = lengthRatio;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }
}
