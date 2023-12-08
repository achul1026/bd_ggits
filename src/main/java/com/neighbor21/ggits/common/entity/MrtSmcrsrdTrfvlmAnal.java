package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSmcrsrdTrfvlmAnal {
    private Timestamp prdctnDt;        //예측 일시
    private String prdctnTime;        //예측 시간
    private String prdctnType;        //예측 유형
    private String crsrdId;        //교차로 아이디
    private String cameraId;        //카메라 아이디
    private double strghtTrfvlm;        //직진 교통량
    private double trnghtTrfvlm;        //우회전 교통량
    private double trnlftTrfvlm;        //좌회전 교통량
    private double wtLnLen;        //대기 행렬 길이
    private String etlDt;        //ETL 일시

    private double trfvlmTotal;
    private String nodeId;
    private String crsrdNm;
    private double lonCrdn;
    private double latCrdn;
    private String mngInstCd;
    private String timeGroupTxt;


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


    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
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

    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    public double getStrghtTrfvlm() {
        return strghtTrfvlm;
    }

    public void setStrghtTrfvlm(double strghtTrfvlm) {
        this.strghtTrfvlm = strghtTrfvlm;
    }

    public double getTrnghtTrfvlm() {
        return trnghtTrfvlm;
    }

    public void setTrnghtTrfvlm(double trnghtTrfvlm) {
        this.trnghtTrfvlm = trnghtTrfvlm;
    }

    public double getTrnlftTrfvlm() {
        return trnlftTrfvlm;
    }

    public void setTrnlftTrfvlm(double trnlftTrfvlm) {
        this.trnlftTrfvlm = trnlftTrfvlm;
    }

    public double getWtLnLen() {
        return wtLnLen;
    }

    public void setWtLnLen(double wtLnLen) {
        this.wtLnLen = wtLnLen;
    }

    public double getTrfvlmTotal() {
        return trfvlmTotal;
    }

    public void setTrfvlmTotal(double trfvlmTotal) {
        this.trfvlmTotal = trfvlmTotal;
    }

    public String getTimeGroupTxt() {
        return timeGroupTxt;
    }

    public void setTimeGroupTxt(String timeGroupTxt) {
        this.timeGroupTxt = timeGroupTxt;
    }
}
