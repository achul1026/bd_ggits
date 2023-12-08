package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtColctWd {
    private Timestamp    prsntnCrtrDt;        //발표 기준 일시
    private String    prdctnType;        //예측 유형
    private Timestamp    prdctnStartDt;        //예측 시작 일시
    private Timestamp    prdctnEndDt;        //예측 종료 일시
    private long    lonCrdn;        //경도 좌표
    private long    latCrdn;        //위도 좌표
    private String    prdctnPctt;        //예측 강수량
    private String    prdctnSnow;        //예측 강설량
    private String    etlDt;        //ETL 일시


  public Timestamp getPrsntnCrtrDt() {
    return prsntnCrtrDt;
  }

  public void setPrsntnCrtrDt(Timestamp prsntnCrtrDt) {
    this.prsntnCrtrDt = prsntnCrtrDt;
  }


  public String getPrdctnType() {
    return prdctnType;
  }

  public void setPrdctnType(String prdctnType) {
    this.prdctnType = prdctnType;
  }


  public Timestamp getPrdctnStartDt() {
    return prdctnStartDt;
  }

  public void setPrdctnStartDt(Timestamp prdctnStartDt) {
    this.prdctnStartDt = prdctnStartDt;
  }


  public Timestamp getPrdctnEndDt() {
    return prdctnEndDt;
  }

  public void setPrdctnEndDt(Timestamp prdctnEndDt) {
    this.prdctnEndDt = prdctnEndDt;
  }


  public long getLonCrdn() {
    return lonCrdn;
  }

  public void setLonCrdn(long lonCrdn) {
    this.lonCrdn = lonCrdn;
  }


  public long getLatCrdn() {
    return latCrdn;
  }

  public void setLatCrdn(long latCrdn) {
    this.latCrdn = latCrdn;
  }


  public String getPrdctnPctt() {
    return prdctnPctt;
  }

  public void setPrdctnPctt(String prdctnPctt) {
    this.prdctnPctt = prdctnPctt;
  }


  public String getPrdctnSnow() {
    return prdctnSnow;
  }

  public void setPrdctnSnow(String prdctnSnow) {
    this.prdctnSnow = prdctnSnow;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
