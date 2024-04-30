package com.neighbor21.ggits.common.entity;

public class MrtCndcyPathLinkInfo {
    private String baseym;
    private String btcId;
    private String candRouteId;
    private Long linkSeq;
    private String stLinkId;
    private String etlDt;

    private Double length;
    private Double lengthRatio;
    private Double scoreImprv;
    private Double lengthVar;
    private Long numPsngr;
    private Long numPsngrVar;
    private Long score;
    private String geojson;
    private String roadName;
    private String roadRank;

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

    public Long getLinkSeq() {
        return linkSeq;
    }

    public void setLinkSeq(Long linkSeq) {
        this.linkSeq = linkSeq;
    }

    public String getStLinkId() {
        return stLinkId;
    }

    public void setStLinkId(String stLinkId) {
        this.stLinkId = stLinkId;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
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

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getRoadRank() {
        return roadRank;
    }

    public void setRoadRank(String roadRank) {
        this.roadRank = roadRank;
    }

    public Double getScoreImprv() {
        return scoreImprv;
    }

    public void setScoreImprv(Double scoreImprv) {
        this.scoreImprv = scoreImprv;
    }

    public Double getLengthVar() {
        return lengthVar;
    }

    public void setLengthVar(Double lengthVar) {
        this.lengthVar = lengthVar;
    }

    public Long getNumPsngr() {
        return numPsngr;
    }

    public void setNumPsngr(Long numPsngr) {
        this.numPsngr = numPsngr;
    }

    public Long getNumPsngrVar() {
        return numPsngrVar;
    }

    public void setNumPsngrVar(Long numPsngrVar) {
        this.numPsngrVar = numPsngrVar;
    }
}
