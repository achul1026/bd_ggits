package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgbisInetIncidentInfoHist {
    private String    regSeq;        //돌발상황 고유번호
    private String    routeId;        //ROUTE ID
    private Timestamp    startDate;        //시작시간
    private Timestamp    endDate;        //종료시간
    private String    linkId;        //링크 ID
    private String    stLinkId;        //표준링크ID
    private String    inciDesc;        //돌발 주석


  public String getRegSeq() {
    return regSeq;
  }

  public void setRegSeq(String regSeq) {
    this.regSeq = regSeq;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public Timestamp getStartDate() {
    return startDate;
  }

  public void setStartDate(Timestamp startDate) {
    this.startDate = startDate;
  }


  public Timestamp getEndDate() {
    return endDate;
  }

  public void setEndDate(Timestamp endDate) {
    this.endDate = endDate;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getStLinkId() {
    return stLinkId;
  }

  public void setStLinkId(String stLinkId) {
    this.stLinkId = stLinkId;
  }


  public String getInciDesc() {
    return inciDesc;
  }

  public void setInciDesc(String inciDesc) {
    this.inciDesc = inciDesc;
  }

}
