package com.neighbor21.ggits.common.entity;

//단기 기상 예보
public class KmaShtrmWthrFrcst {

  private String prsntnYmd; //발표 일자
  private String prsntnTime; //발표 시간
  private String prdctnYmd; //예측 일자
  private String prdctnTime; //예측 시간
  private long frcstPointXcord; //예보 지점 x좌표
  private long frcstPointYcord; //예보 지점 y좌표
  private String frcstDivCd; //예보 구분 코드
  private String frcstVal; //예보 값


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


  public String getPrdctnYmd() {
    return prdctnYmd;
  }

  public void setPrdctnYmd(String prdctnYmd) {
    this.prdctnYmd = prdctnYmd;
  }


  public String getPrdctnTime() {
    return prdctnTime;
  }

  public void setPrdctnTime(String prdctnTime) {
    this.prdctnTime = prdctnTime;
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


  public String getFrcstVal() {
    return frcstVal;
  }

  public void setFrcstVal(String frcstVal) {
    this.frcstVal = frcstVal;
  }

}
