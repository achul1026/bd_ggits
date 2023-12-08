package com.neighbor21.ggits.common.entity;

//세부링크 도로 위험 분석
public class TaasLinkRoadDngrAnls {

  private String linkCrdn; //링크 좌표
  private String vhcclsDiv; //차종 구분
  private long linkSqno; //링크 순번
  private long roadDngrAnlsVal; //도로 위험 분석 값
  private String roadDngrAnlsGrd; //도로 위험 분석 등급


  public String getLinkCrdn() {
    return linkCrdn;
  }

  public void setLinkCrdn(String linkCrdn) {
    this.linkCrdn = linkCrdn;
  }


  public String getVhcclsDiv() {
    return vhcclsDiv;
  }

  public void setVhcclsDiv(String vhcclsDiv) {
    this.vhcclsDiv = vhcclsDiv;
  }


  public long getLinkSqno() {
    return linkSqno;
  }

  public void setLinkSqno(long linkSqno) {
    this.linkSqno = linkSqno;
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
