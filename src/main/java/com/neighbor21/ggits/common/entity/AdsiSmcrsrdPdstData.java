package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 보행자 데이터
public class AdsiSmcrsrdPdstData {

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String cameraId; //카메라 아이디
  private String drctCd; //방향 코드
  private long speed; //속도
  private String pdstType; //보행자 유형
  private long reqTimeSs; //소요 시간 초


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


  public String getCameraId() {
    return cameraId;
  }

  public void setCameraId(String cameraId) {
    this.cameraId = cameraId;
  }


  public String getDrctCd() {
    return drctCd;
  }

  public void setDrctCd(String drctCd) {
    this.drctCd = drctCd;
  }


  public long getSpeed() {
    return speed;
  }

  public void setSpeed(long speed) {
    this.speed = speed;
  }


  public String getPdstType() {
    return pdstType;
  }

  public void setPdstType(String pdstType) {
    this.pdstType = pdstType;
  }


  public long getReqTimeSs() {
    return reqTimeSs;
  }

  public void setReqTimeSs(long reqTimeSs) {
    this.reqTimeSs = reqTimeSs;
  }

}
