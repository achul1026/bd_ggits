package com.neighbor21.ggits.common.entity;
public class MrtBusArvlTimePrdctn {
    private String    crtrYmd;        //기준 일자
    private String    crtrTime;        //기준 시간
    private String    dptreLcId;        //출발 위치 아이디
    private String    dptreLcType;        //출발 위치 유형
    private String    arvlLcId;        //도착 위치 아이디
    private String    arvlLcType;        //도착 위치 유형
    private long    passTime;        //통행 시간


  public String getCrtrYmd() {
    return crtrYmd;
  }

  public void setCrtrYmd(String crtrYmd) {
    this.crtrYmd = crtrYmd;
  }


  public String getCrtrTime() {
    return crtrTime;
  }

  public void setCrtrTime(String crtrTime) {
    this.crtrTime = crtrTime;
  }


  public String getDptreLcId() {
    return dptreLcId;
  }

  public void setDptreLcId(String dptreLcId) {
    this.dptreLcId = dptreLcId;
  }


  public String getDptreLcType() {
    return dptreLcType;
  }

  public void setDptreLcType(String dptreLcType) {
    this.dptreLcType = dptreLcType;
  }


  public String getArvlLcId() {
    return arvlLcId;
  }

  public void setArvlLcId(String arvlLcId) {
    this.arvlLcId = arvlLcId;
  }


  public String getArvlLcType() {
    return arvlLcType;
  }

  public void setArvlLcType(String arvlLcType) {
    this.arvlLcType = arvlLcType;
  }


  public long getPassTime() {
    return passTime;
  }

  public void setPassTime(long passTime) {
    this.passTime = passTime;
  }

}
