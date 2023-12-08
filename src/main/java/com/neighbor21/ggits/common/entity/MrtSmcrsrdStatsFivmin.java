package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSmcrsrdStatsFivmin {
    private String    mngInstCd;        //관리 기관 코드
    private Timestamp    clctDt;        //수집 일시
    private String    crsrdId;        //교차로 아이디
    private String    acsRoadId;        //접근 도로 아이디
    private long    vhclTrfvlm;        //차량 교통량
    private long    pdstCnt;        //보행자 수
    private long    avgVhclSpeed;        //평균 차량 속도
    private long    avgPdstSpeed;        //평균 보행자 속도
    private String    etlDt;        //ETL 일시


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


  public long getVhclTrfvlm() {
    return vhclTrfvlm;
  }

  public void setVhclTrfvlm(long vhclTrfvlm) {
    this.vhclTrfvlm = vhclTrfvlm;
  }


  public long getPdstCnt() {
    return pdstCnt;
  }

  public void setPdstCnt(long pdstCnt) {
    this.pdstCnt = pdstCnt;
  }


  public long getAvgVhclSpeed() {
    return avgVhclSpeed;
  }

  public void setAvgVhclSpeed(long avgVhclSpeed) {
    this.avgVhclSpeed = avgVhclSpeed;
  }


  public long getAvgPdstSpeed() {
    return avgPdstSpeed;
  }

  public void setAvgPdstSpeed(long avgPdstSpeed) {
    this.avgPdstSpeed = avgPdstSpeed;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
