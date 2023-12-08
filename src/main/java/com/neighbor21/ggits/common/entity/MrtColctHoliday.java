package com.neighbor21.ggits.common.entity;
public class MrtColctHoliday {
    private String    hldyYmd;        //휴일 일자
    private String    hldyNm;        //휴일 명
    private String    etlDt;        //ETL 일시


  public String getHldyYmd() {
    return hldyYmd;
  }

  public void setHldyYmd(String hldyYmd) {
    this.hldyYmd = hldyYmd;
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
