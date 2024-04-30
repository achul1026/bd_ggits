package com.neighbor21.ggits.common.entity;
public class MrtHldyInfo {
    private String    hldyYmd;        //휴일 일자
    private long    sqno;        //순번
    private String    hldyType;        //휴일 유형
    private String    hldyYn;        //휴일 여부
    private String    hldyNm;        //휴일 명
    private String    etlDt;        //etl 일시


  public String getHldyYmd() {
    return hldyYmd;
  }

  public void setHldyYmd(String hldyYmd) {
    this.hldyYmd = hldyYmd;
  }


  public long getSqno() {
    return sqno;
  }

  public void setSqno(long sqno) {
    this.sqno = sqno;
  }


  public String getHldyType() {
    return hldyType;
  }

  public void setHldyType(String hldyType) {
    this.hldyType = hldyType;
  }


  public String getHldyYn() {
    return hldyYn;
  }

  public void setHldyYn(String hldyYn) {
    this.hldyYn = hldyYn;
  }


  public String getHldyNm() {
    return hldyNm;
  }

  public void setHldyNm(String hldyNm) {
    this.hldyNm = hldyNm;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
