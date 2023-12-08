package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtDtgAdsiDngrSctnAnls {
    private Timestamp    anlsDt;        //분석 일시
    private String    adguSttnCd;        //행정구역 코드
    private String    linkId;        //링크 아이디
    private long    spdngRungCnt;        //과속 운행 수
    private long    sdacelRungCnt;        //급가속 운행 수
    private long    rpddclRungCnt;        //급감속 운행 수
    private long    sdstopRungCnt;        //급정지 운행 수
    private long    sdstrtRungCnt;        //급출발 운행 수
    private long    lngtrmaclRungCnt;        //장기과속 운행 수


  public Timestamp getAnlsDt() {
    return anlsDt;
  }

  public void setAnlsDt(Timestamp anlsDt) {
    this.anlsDt = anlsDt;
  }


  public String getAdguSttnCd() {
    return adguSttnCd;
  }

  public void setAdguSttnCd(String adguSttnCd) {
    this.adguSttnCd = adguSttnCd;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public long getSpdngRungCnt() {
    return spdngRungCnt;
  }

  public void setSpdngRungCnt(long spdngRungCnt) {
    this.spdngRungCnt = spdngRungCnt;
  }


  public long getSdacelRungCnt() {
    return sdacelRungCnt;
  }

  public void setSdacelRungCnt(long sdacelRungCnt) {
    this.sdacelRungCnt = sdacelRungCnt;
  }


  public long getRpddclRungCnt() {
    return rpddclRungCnt;
  }

  public void setRpddclRungCnt(long rpddclRungCnt) {
    this.rpddclRungCnt = rpddclRungCnt;
  }


  public long getSdstopRungCnt() {
    return sdstopRungCnt;
  }

  public void setSdstopRungCnt(long sdstopRungCnt) {
    this.sdstopRungCnt = sdstopRungCnt;
  }


  public long getSdstrtRungCnt() {
    return sdstrtRungCnt;
  }

  public void setSdstrtRungCnt(long sdstrtRungCnt) {
    this.sdstrtRungCnt = sdstrtRungCnt;
  }


  public long getLngtrmaclRungCnt() {
    return lngtrmaclRungCnt;
  }

  public void setLngtrmaclRungCnt(long lngtrmaclRungCnt) {
    this.lngtrmaclRungCnt = lngtrmaclRungCnt;
  }

}
