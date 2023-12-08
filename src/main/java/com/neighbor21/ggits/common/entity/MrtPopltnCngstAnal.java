package com.neighbor21.ggits.common.entity;
public class MrtPopltnCngstAnal {
    private String    crtrYmd;        //기준 일자
    private String    cellId;        //셀 아이디
    private String    crtrTime;        //기준 시간
    private long    xcord;        //x좌표
    private long    ycord;        //y좌표
    private long    dynmcPopltnCnt;        //유동 인구 수


  public String getCrtrYmd() {
    return crtrYmd;
  }

  public void setCrtrYmd(String crtrYmd) {
    this.crtrYmd = crtrYmd;
  }


  public String getCellId() {
    return cellId;
  }

  public void setCellId(String cellId) {
    this.cellId = cellId;
  }


  public String getCrtrTime() {
    return crtrTime;
  }

  public void setCrtrTime(String crtrTime) {
    this.crtrTime = crtrTime;
  }


  public long getXcord() {
    return xcord;
  }

  public void setXcord(long xcord) {
    this.xcord = xcord;
  }


  public long getYcord() {
    return ycord;
  }

  public void setYcord(long ycord) {
    this.ycord = ycord;
  }


  public long getDynmcPopltnCnt() {
    return dynmcPopltnCnt;
  }

  public void setDynmcPopltnCnt(long dynmcPopltnCnt) {
    this.dynmcPopltnCnt = dynmcPopltnCnt;
  }

}
