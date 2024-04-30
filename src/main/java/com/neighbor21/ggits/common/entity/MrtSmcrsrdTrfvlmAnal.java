package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSmcrsrdTrfvlmAnal {

    private Timestamp prdctnDt;        //예측 일시
    private String prdctnTime;        //예측 시간
    private String prdctnType;        //예측 유형
    private String crsrdId;        //교차로 아이디
    private String cameraId;        //카메라 아이디
    private Double strghtTrfvlm;        //직진 교통량
    private Double trnghtTrfvlm;        //우회전 교통량
    private Double trnlftTrfvlm;        //좌회전 교통량
    private Long wtLnLen;        //대기 행렬 길이
    private String etlDt;        //ETL 일시

    private Double trfvlmTotal;
    private String nodeId;
    private String crsrdNm;
    private Double lonCrdn;
    private Double latCrdn;
    private String mngInstCd;
    private String adstdgCd;
    private String timeGroupTxt;
    private String yyyymmdd;

    private String acsRoadId;
    private String acsRoadNm;
    private Double angle;
    private String st;
    private String ed;

    public Timestamp getPrdctnDt() {
        return prdctnDt;
    }

    public void setPrdctnDt(Timestamp prdctnDt) {
        this.prdctnDt = prdctnDt;
    }

    public String getPrdctnTime() {
        return prdctnTime;
    }

    public void setPrdctnTime(String prdctnTime) {
        this.prdctnTime = prdctnTime;
    }

    public String getPrdctnType() {
        return prdctnType;
    }

    public void setPrdctnType(String prdctnType) {
        this.prdctnType = prdctnType;
    }

    public String getCrsrdId() {
        return crsrdId;
    }

    public void setCrsrdId(String crsrdId) {
        this.crsrdId = crsrdId;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public Double getStrghtTrfvlm() {
        return strghtTrfvlm;
    }

    public void setStrghtTrfvlm(Double strghtTrfvlm) {
        this.strghtTrfvlm = strghtTrfvlm;
    }

    public Double getTrnghtTrfvlm() {
        return trnghtTrfvlm;
    }

    public void setTrnghtTrfvlm(Double trnghtTrfvlm) {
        this.trnghtTrfvlm = trnghtTrfvlm;
    }

    public Double getTrnlftTrfvlm() {
        return trnlftTrfvlm;
    }

    public void setTrnlftTrfvlm(Double trnlftTrfvlm) {
        this.trnlftTrfvlm = trnlftTrfvlm;
    }

    public Long getWtLnLen() {
        return wtLnLen;
    }

    public void setWtLnLen(Long wtLnLen) {
        this.wtLnLen = wtLnLen;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public Double getTrfvlmTotal() {
        return trfvlmTotal;
    }

    public void setTrfvlmTotal(Double trfvlmTotal) {
        this.trfvlmTotal = trfvlmTotal;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getCrsrdNm() {
        return crsrdNm;
    }

    public void setCrsrdNm(String crsrdNm) {
        this.crsrdNm = crsrdNm;
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

    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    public String getAdstdgCd() {
        return adstdgCd;
    }

    public void setAdstdgCd(String adstdgCd) {
        this.adstdgCd = adstdgCd;
    }

    public String getTimeGroupTxt() {
        return timeGroupTxt;
    }

    public void setTimeGroupTxt(String timeGroupTxt) {
        this.timeGroupTxt = timeGroupTxt;
    }

    public String getYyyymmdd() {
        return yyyymmdd;
    }

    public void setYyyymmdd(String yyyymmdd) {
        this.yyyymmdd = yyyymmdd;
    }

    public String getAcsRoadId() {
        return acsRoadId;
    }

    public void setAcsRoadId(String acsRoadId) {
        this.acsRoadId = acsRoadId;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
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

    public String getAcsRoadNm() {
        return acsRoadNm;
    }

    public void setAcsRoadNm(String acsRoadNm) {
        this.acsRoadNm = acsRoadNm;
    }
}
