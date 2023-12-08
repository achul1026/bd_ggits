package com.neighbor21.ggits.common.entity;

//vds 구간 정보
public class AdsiVdsSctnInfo {

  private String mngInstCd; //관리 기관 코드
  private String vdsSctnId; //vds 구간 아이디
  private String vdsSctnNm; //vds 구간 명
  private long vdsSctnLen; //vds 구간 길이
  private String roadGrd; //도로 등급
  private long minLimitSpeed; //최소 제한 속도
  private long maxLimitSpeed; //최대 제한 속도
  private String vdsId; //vds 아이디


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public String getVdsSctnId() {
    return vdsSctnId;
  }

  public void setVdsSctnId(String vdsSctnId) {
    this.vdsSctnId = vdsSctnId;
  }


  public String getVdsSctnNm() {
    return vdsSctnNm;
  }

  public void setVdsSctnNm(String vdsSctnNm) {
    this.vdsSctnNm = vdsSctnNm;
  }


  public long getVdsSctnLen() {
    return vdsSctnLen;
  }

  public void setVdsSctnLen(long vdsSctnLen) {
    this.vdsSctnLen = vdsSctnLen;
  }


  public String getRoadGrd() {
    return roadGrd;
  }

  public void setRoadGrd(String roadGrd) {
    this.roadGrd = roadGrd;
  }


  public long getMinLimitSpeed() {
    return minLimitSpeed;
  }

  public void setMinLimitSpeed(long minLimitSpeed) {
    this.minLimitSpeed = minLimitSpeed;
  }


  public long getMaxLimitSpeed() {
    return maxLimitSpeed;
  }

  public void setMaxLimitSpeed(long maxLimitSpeed) {
    this.maxLimitSpeed = maxLimitSpeed;
  }


  public String getVdsId() {
    return vdsId;
  }

  public void setVdsId(String vdsId) {
    this.vdsId = vdsId;
  }

}
