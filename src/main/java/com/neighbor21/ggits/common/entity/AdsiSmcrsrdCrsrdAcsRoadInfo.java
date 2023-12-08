package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 교차로 접근 도로 정보
public class AdsiSmcrsrdCrsrdAcsRoadInfo extends CommonEntity {

    private String mngInstCd; //관리 기관 코드
    private String crsrdId; //교차로 아이디
    private String acsRoadId; //접근 도로 아이디
    private String acsRoadNm; //접근 도로 명
    private String linkId; //링크 아이디
    private long laneCnt; //차로 수
    private long angl; //각도
    private double lonCrdn; //경도 좌표
    private double latCrdn; //위도 좌표
    private long maxTrfvlm; //최대 교통량
    private long maxPdstCnt; //최대 보행자 수

    private String crsrdNm;
    private String geojson;
    private String roadName;
    private Long vhclTrfvlm;
    private Long pdstCnt;
    private Double avgVhclSpeed;
    private Double avgPdstSpeed;
    private Long ctrlDelayTime;
    private Timestamp clctDt;

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

    public String getAcsRoadId() {
        return acsRoadId;
    }

    public void setAcsRoadId(String acsRoadId) {
        this.acsRoadId = acsRoadId;
    }

    public String getAcsRoadNm() {
        return acsRoadNm;
    }

    public void setAcsRoadNm(String acsRoadNm) {
        this.acsRoadNm = acsRoadNm;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public long getLaneCnt() {
        return laneCnt;
    }

    public void setLaneCnt(long laneCnt) {
        this.laneCnt = laneCnt;
    }

    public long getAngl() {
        return angl;
    }

    public void setAngl(long angl) {
        this.angl = angl;
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

    public long getMaxTrfvlm() {
        return maxTrfvlm;
    }

    public void setMaxTrfvlm(long maxTrfvlm) {
        this.maxTrfvlm = maxTrfvlm;
    }

    public long getMaxPdstCnt() {
        return maxPdstCnt;
    }

    public void setMaxPdstCnt(long maxPdstCnt) {
        this.maxPdstCnt = maxPdstCnt;
    }

    public String getCrsrdNm() {
        return crsrdNm;
    }

    public void setCrsrdNm(String crsrdNm) {
        this.crsrdNm = crsrdNm;
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

    public Long getCtrlDelayTime() {
        return ctrlDelayTime;
    }

    public void setCtrlDelayTime(Long ctrlDelayTime) {
        this.ctrlDelayTime = ctrlDelayTime;
    }

    public Timestamp getClctDt() {
        return clctDt;
    }

    public void setClctDt(Timestamp clctDt) {
        this.clctDt = clctDt;
    }
}
