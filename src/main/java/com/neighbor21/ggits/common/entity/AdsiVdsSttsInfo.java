package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//vds 상태 정보
public class AdsiVdsSttsInfo {

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String vdsId; //vds 아이디
  private String vdsStts; //vds 상태


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


  public String getVdsId() {
    return vdsId;
  }

  public void setVdsId(String vdsId) {
    this.vdsId = vdsId;
  }


  public String getVdsStts() {
    return vdsStts;
  }

  public void setVdsStts(String vdsStts) {
    this.vdsStts = vdsStts;
  }

}
