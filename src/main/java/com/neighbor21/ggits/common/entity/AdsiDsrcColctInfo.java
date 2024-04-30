package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//dsrc 수집 정보
public class AdsiDsrcColctInfo extends AdsiDsrcSctnInfo {

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String dsrcSctnId; //dsrc 구간 아이디
  private String obuId; //obu 아이디
  private double speed; //속도

  @Override
  public String getMngInstCd() {
    return mngInstCd;
  }
  
  @Override
  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }

  public Timestamp getClctDt() {
    return clctDt;
  }

  public void setClctDt(Timestamp clctDt) {
    this.clctDt = clctDt;
  }

  @Override
  public String getDsrcSctnId() {
    return dsrcSctnId;
  }
  
  @Override
  public void setDsrcSctnId(String dsrcSctnId) {
    this.dsrcSctnId = dsrcSctnId;
  }


  public String getObuId() {
    return obuId;
  }

  public void setObuId(String obuId) {
    this.obuId = obuId;
  }


  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

}
