package com.neighbor21.ggits.common.entity;

public class CGmStdLink extends CommonEntity {
    private String linkId;
    private String fNode;
    private String tNode;
    private String fNodeNm;
    private String tNodeNm;
    private Long lanes;
    private String roadRank;
    private String roadType;
    private String roadNo;
    private String roadName;
    private String roadUse;
    private String multiLink;
    private String connect;
    private Long maxSpd;
    private String restVeh;
    private Long restW;
    private Long restH;
    private String length;
    private String remark;
    private String aplcnYmd;
    private String geometry;
    private String etlDt;


    private double x;    //미보유 컬럼 geometry lag값
    private double y;    //미보유 컬럼 geometry lng 값

    private String st;
    private String ed;
    private Double angle;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
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

    public String getfNodeNm() {
        return fNodeNm;
    }

    public void setfNodeNm(String fNodeNm) {
        this.fNodeNm = fNodeNm;
    }

    public String gettNodeNm() {
        return tNodeNm;
    }

    public void settNodeNm(String tNodeNm) {
        this.tNodeNm = tNodeNm;
    }

    public Long getLanes() {
        return lanes;
    }

    public void setLanes(Long lanes) {
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

    public Long getMaxSpd() {
        return maxSpd;
    }

    public void setMaxSpd(Long maxSpd) {
        this.maxSpd = maxSpd;
    }

    public String getRestVeh() {
        return restVeh;
    }

    public void setRestVeh(String restVeh) {
        this.restVeh = restVeh;
    }

    public Long getRestW() {
        return restW;
    }

    public void setRestW(Long restW) {
        this.restW = restW;
    }

    public Long getRestH() {
        return restH;
    }

    public void setRestH(Long restH) {
        this.restH = restH;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAplcnYmd() {
        return aplcnYmd;
    }

    public void setAplcnYmd(String aplcnYmd) {
        this.aplcnYmd = aplcnYmd;
    }

    public String getGeometry() {
        return geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }
}
