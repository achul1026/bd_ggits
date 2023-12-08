package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSigCrsdTrfAnal extends CommonEntity{
    private String mngInstCd;
    private Timestamp anlsDt;
    private String linkId;
    private String srvcId;
    private String acsRoadId;
    private String crsrdId;
    private long vhclTrfvlm;
    private long avgVhclSpeed;
    private String etlDt;

    //맵용
    private double avgVhclSpeedAvg; // 시간대 평균속도
    private long vhclTrfvlmTotal; // 누적통행량

    //링크정보
    private long gid;
    private String fNode;
    private String tNode;
    private long lanes;
    private String roadRank;
    private String roadType;
    private String roadNo;
    private String roadName;
    private String roadUse;
    private String multiLink;
    private String connect;
    private long maxSpd;
    private String restVeh;
    private long restW;
    private long restH;
    private long length;
    private String remark;
    private String geom;
    private String geojson;


    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    public Timestamp getAnlsDt() {
        return anlsDt;
    }

    public void setAnlsDt(Timestamp anlsDt) {
        this.anlsDt = anlsDt;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getSrvcId() {
        return srvcId;
    }

    public void setSrvcId(String srvcId) {
        this.srvcId = srvcId;
    }

    public String getAcsRoadId() {
        return acsRoadId;
    }

    public void setAcsRoadId(String acsRoadId) {
        this.acsRoadId = acsRoadId;
    }

    public String getCrsrdId() {
        return crsrdId;
    }

    public void setCrsrdId(String crsrdId) {
        this.crsrdId = crsrdId;
    }

    public long getVhclTrfvlm() {
        return vhclTrfvlm;
    }

    public void setVhclTrfvlm(long vhclTrfvlm) {
        this.vhclTrfvlm = vhclTrfvlm;
    }

    public long getAvgVhclSpeed() {
        return avgVhclSpeed;
    }

    public void setAvgVhclSpeed(long avgVhclSpeed) {
        this.avgVhclSpeed = avgVhclSpeed;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public double getAvgVhclSpeedAvg() {
        return avgVhclSpeedAvg;
    }

    public void setAvgVhclSpeedAvg(double avgVhclSpeedAvg) {
        this.avgVhclSpeedAvg = avgVhclSpeedAvg;
    }

    public long getVhclTrfvlmTotal() {
        return vhclTrfvlmTotal;
    }

    public void setVhclTrfvlmTotal(long vhclTrfvlmTotal) {
        this.vhclTrfvlmTotal = vhclTrfvlmTotal;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getfNode() {
        return fNode;
    }

    public void setfNode(String fNode) {
        this.fNode = fNode;
    }

    public String gettNode() {
        return tNode;
    }

    public void settNode(String tNode) {
        this.tNode = tNode;
    }

    public long getLanes() {
        return lanes;
    }

    public void setLanes(long lanes) {
        this.lanes = lanes;
    }

    public String getRoadRank() {
        return roadRank;
    }

    public void setRoadRank(String roadRank) {
        this.roadRank = roadRank;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getRoadNo() {
        return roadNo;
    }

    public void setRoadNo(String roadNo) {
        this.roadNo = roadNo;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getRoadUse() {
        return roadUse;
    }

    public void setRoadUse(String roadUse) {
        this.roadUse = roadUse;
    }

    public String getMultiLink() {
        return multiLink;
    }

    public void setMultiLink(String multiLink) {
        this.multiLink = multiLink;
    }

    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public long getMaxSpd() {
        return maxSpd;
    }

    public void setMaxSpd(long maxSpd) {
        this.maxSpd = maxSpd;
    }

    public String getRestVeh() {
        return restVeh;
    }

    public void setRestVeh(String restVeh) {
        this.restVeh = restVeh;
    }

    public long getRestW() {
        return restW;
    }

    public void setRestW(long restW) {
        this.restW = restW;
    }

    public long getRestH() {
        return restH;
    }

    public void setRestH(long restH) {
        this.restH = restH;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGeom() {
        return geom;
    }

    public void setGeom(String geom) {
        this.geom = geom;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }
}
