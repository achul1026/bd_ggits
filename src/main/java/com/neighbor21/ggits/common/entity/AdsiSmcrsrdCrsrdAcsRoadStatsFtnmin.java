package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 교차로 접근 도로 통계 15분
public class AdsiSmcrsrdCrsrdAcsRoadStatsFtnmin {

    private String mngInstCd; //관리 기관 코드
    private Timestamp clctDt; //수집 일시
    private String crsrdId; //교차로 아이디
    private String acsRoadId; //접근 도로 아이디
    private Long vhclTrfvlm; //차량 교통량
    private Long pdstCnt; //보행자 수
    private Double avgVhclSpeed; //평균 차량 속도
    private Double avgPdstSpeed; //평균 보행자 속도
    private Long ctrlDelayTime; //제어 지연 시간

    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    public Timestamp getClctDt() {
        return clctDt;
    }

    public void setClctDt(Timestamp clctDt) {
        this.clctDt = clctDt;
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
}
