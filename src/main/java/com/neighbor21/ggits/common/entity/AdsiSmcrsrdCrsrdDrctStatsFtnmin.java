package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 교차로 방향 통계 15분
public class AdsiSmcrsrdCrsrdDrctStatsFtnmin {

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String crsrdId; //교차로 아이디
  private String acsRoadId; //접근 도로 아이디
  private String drctCd; //방향 코드
  private long laneNo; //차로 번호
  private String vhclDiv; //차량 구분
  private long vhclTrfvlm; //차량 교통량
  private long avgVhclSpeed; //평균 차량 속도


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


  public String getDrctCd() {
    return drctCd;
  }

  public void setDrctCd(String drctCd) {
    this.drctCd = drctCd;
  }


  public long getLaneNo() {
    return laneNo;
  }

  public void setLaneNo(long laneNo) {
    this.laneNo = laneNo;
  }


  public String getVhclDiv() {
    return vhclDiv;
  }

  public void setVhclDiv(String vhclDiv) {
    this.vhclDiv = vhclDiv;
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

}
