package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//rse 상태 정보
public class AdsiRseSttsInfo {

  private String mngInstCd; //관리 기관 코드
  private Timestamp clctDt; //수집 일시
  private String rseId; //rse 아이디
  private String rseStts; //rse 상태


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


  public String getRseId() {
    return rseId;
  }

  public void setRseId(String rseId) {
    this.rseId = rseId;
  }


  public String getRseStts() {
    return rseStts;
  }

  public void setRseStts(String rseStts) {
    this.rseStts = rseStts;
  }

}
