package com.neighbor21.ggits.common.entity;

//스마트교차로 교차로 정보
public class AdsiSmcrsrdCrsrdInfo extends CommonEntity {

    private String mngInstCd; //관리 기관 코드
    private String crsrdId; //교차로 아이디
    private String crsrdNm; //교차로 명
    private String nodeId; //노드 아이디
    private Double lonCrdn; //경도 좌표
    private Double latCrdn; //위도 좌표
    private Long maxTrfvlm; //최대 교통량
    private Long maxPdstCnt; //최대 보행자 수
    private Double x;
    private Double y;
    private String geojson;

    private Long vhclTrfvlm;
    private Long pdstCnt;
    private Double avgVhclSpeed;
    private Double avgPdstSpeed;

    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    public String getCrsrdId() {
        return crsrdId;
    }

    public void setCrsrdId(String crsrdId) {
        this.crsrdId = crsrdId;
    }

    public String getCrsrdNm() {
        return crsrdNm;
    }

    public void setCrsrdNm(String crsrdNm) {
        this.crsrdNm = crsrdNm;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
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

    public Long getMaxTrfvlm() {
        return maxTrfvlm;
    }

    public void setMaxTrfvlm(Long maxTrfvlm) {
        this.maxTrfvlm = maxTrfvlm;
    }

    public Long getMaxPdstCnt() {
        return maxPdstCnt;
    }

    public void setMaxPdstCnt(Long maxPdstCnt) {
        this.maxPdstCnt = maxPdstCnt;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

    public Long getVhclTrfvlm() {
        return vhclTrfvlm;
    }

    public void setVhclTrfvlm(Long vhclTrfvlm) {
        this.vhclTrfvlm = vhclTrfvlm;
    }

    public Long getPdstCnt() {
        return pdstCnt;
    }

    public void setPdstCnt(Long pdstCnt) {
        this.pdstCnt = pdstCnt;
    }

    public Double getAvgVhclSpeed() {
        return avgVhclSpeed;
    }

    public void setAvgVhclSpeed(Double avgVhclSpeed) {
        this.avgVhclSpeed = avgVhclSpeed;
    }

    public Double getAvgPdstSpeed() {
        return avgPdstSpeed;
    }

    public void setAvgPdstSpeed(Double avgPdstSpeed) {
        this.avgPdstSpeed = avgPdstSpeed;
    }
}
