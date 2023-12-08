package com.neighbor21.ggits.common.entity;
public class MrtTrfAcdntDngrPrdctn {
    private String    linkId;        //링크 아이디
    private long    speed;        //속도
    private long    safeIdex;        //안전 지수
    private String    safeGrd;        //안전 등급


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public long getSpeed() {
    return speed;
  }

  public void setSpeed(long speed) {
    this.speed = speed;
  }


  public long getSafeIdex() {
    return safeIdex;
  }

  public void setSafeIdex(long safeIdex) {
    this.safeIdex = safeIdex;
  }


  public String getSafeGrd() {
    return safeGrd;
  }

  public void setSafeGrd(String safeGrd) {
    this.safeGrd = safeGrd;
  }

}
