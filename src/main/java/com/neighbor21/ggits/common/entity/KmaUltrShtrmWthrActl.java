package com.neighbor21.ggits.common.entity;

//초단기 기상 실황
public class KmaUltrShtrmWthrActl {

  private String prsntnYmd; //발표 일자
  private String prsntnTime; //발표 시간
  private long frcstPointXcord; //예보 지점 x좌표
  private long frcstPointYcord; //예보 지점 y좌표
  private String frcstDivCd; //예보 구분 코드
  private String actlVal; //실제 값


  public String getPrsntnYmd() {
    return prsntnYmd;
  }

  public void setPrsntnYmd(String prsntnYmd) {
    this.prsntnYmd = prsntnYmd;
  }


  public String getPrsntnTime() {
    return prsntnTime;
  }

  public void setPrsntnTime(String prsntnTime) {
    this.prsntnTime = prsntnTime;
  }


  public long getFrcstPointXcord() {
    return frcstPointXcord;
  }

  public void setFrcstPointXcord(long frcstPointXcord) {
    this.frcstPointXcord = frcstPointXcord;
  }


  public long getFrcstPointYcord() {
    return frcstPointYcord;
  }

  public void setFrcstPointYcord(long frcstPointYcord) {
    this.frcstPointYcord = frcstPointYcord;
  }


  public String getFrcstDivCd() {
    return frcstDivCd;
  }

  public void setFrcstDivCd(String frcstDivCd) {
    this.frcstDivCd = frcstDivCd;
  }


  public String getActlVal() {
    return actlVal;
  }

  public void setActlVal(String actlVal) {
    this.actlVal = actlVal;
  }

}
