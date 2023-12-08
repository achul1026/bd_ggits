package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 카메라 상태 정보
public class AdsiSmcrsrdCameraSttsInfo {

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String cameraId; //카메라 아이디
  private String cmncStts; //통신 상태


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


  public String getCmncStts() {
    return cmncStts;
  }

  public void setCmncStts(String cmncStts) {
    this.cmncStts = cmncStts;
  }

}
