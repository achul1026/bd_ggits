package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSmcSpotLos {
    private String    mngInstCd;        //관리 기관 코드
    private Timestamp    anlsDt;        //분석 일시
    private String    crsrdId;        //교차로 아이디
    private String    acsRoadId;        //접근 도로 아이디
    private String    drctCd;        //방향 코드
    private String    dywkCd;        //요일 코드
    private long    vhclTrfvlm;        //차량 교통량
    private long    avgVhclSpeed;        //평균 차량 속도
    private String    cngrtGrd;        //혼잡도 등급
    private String    etlDt;        //etl 일시


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


  public String getDrctCd() {
    return drctCd;
  }

  public void setDrctCd(String drctCd) {
    this.drctCd = drctCd;
  }


  public String getDywkCd() {
    return dywkCd;
  }

  public void setDywkCd(String dywkCd) {
    this.dywkCd = dywkCd;
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


  public String getCngrtGrd() {
    return cngrtGrd;
  }

  public void setCngrtGrd(String cngrtGrd) {
    this.cngrtGrd = cngrtGrd;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
