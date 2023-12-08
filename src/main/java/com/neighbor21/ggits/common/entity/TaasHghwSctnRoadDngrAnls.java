package com.neighbor21.ggits.common.entity;

//고속도로 구간 도로 위험 분석
public class TaasHghwSctnRoadDngrAnls {

  private String hghwNm; //고속도로 명
  private String hghwSctnNm; //고속도로 구간 명
  private String vhcclsDiv; //차종 구분
  private long sqno; //순번
  private String sctnCrdn; //구간 좌표
  private long roadDngrAnlsVal; //도로 위험 분석 값
  private String roadDngrAnlsGrd; //도로 위험 분석 등급


  public String getHghwNm() {
    return hghwNm;
  }

  public void setHghwNm(String hghwNm) {
    this.hghwNm = hghwNm;
  }


  public String getHghwSctnNm() {
    return hghwSctnNm;
  }

  public void setHghwSctnNm(String hghwSctnNm) {
    this.hghwSctnNm = hghwSctnNm;
  }


  public String getVhcclsDiv() {
    return vhcclsDiv;
  }

  public void setVhcclsDiv(String vhcclsDiv) {
    this.vhcclsDiv = vhcclsDiv;
  }


  public long getSqno() {
    return sqno;
  }

  public void setSqno(long sqno) {
    this.sqno = sqno;
  }


  public String getSctnCrdn() {
    return sctnCrdn;
  }

  public void setSctnCrdn(String sctnCrdn) {
    this.sctnCrdn = sctnCrdn;
  }


  public long getRoadDngrAnlsVal() {
    return roadDngrAnlsVal;
  }

  public void setRoadDngrAnlsVal(long roadDngrAnlsVal) {
    this.roadDngrAnlsVal = roadDngrAnlsVal;
  }


  public String getRoadDngrAnlsGrd() {
    return roadDngrAnlsGrd;
  }

  public void setRoadDngrAnlsGrd(String roadDngrAnlsGrd) {
    this.roadDngrAnlsGrd = roadDngrAnlsGrd;
  }

}
