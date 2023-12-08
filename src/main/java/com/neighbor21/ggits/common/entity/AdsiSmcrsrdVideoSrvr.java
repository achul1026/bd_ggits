package com.neighbor21.ggits.common.entity;

//스마트교차로 동영상분석 서버
public class AdsiSmcrsrdVideoSrvr {

  private String mngInstCd; //관리 기관 코드
  private String srvrId; //서버 아이디
  private String srvrNm; //서버 명


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public String getSrvrId() {
    return srvrId;
  }

  public void setSrvrId(String srvrId) {
    this.srvrId = srvrId;
  }


  public String getSrvrNm() {
    return srvrNm;
  }

  public void setSrvrNm(String srvrNm) {
    this.srvrNm = srvrNm;
  }

}
