package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusRouteOptm {
    private long    excnRsltId;        //실행 결과 아이디
    private Timestamp    excnDt;        //실행 일시
    private java.sql.Time    excnTime;        //실행 시간
    private String    routeId;        //노선 아이디
    private String    routeNm;        //노선 명


  public long getExcnRsltId() {
    return excnRsltId;
  }

  public void setExcnRsltId(long excnRsltId) {
    this.excnRsltId = excnRsltId;
  }


  public Timestamp getExcnDt() {
    return excnDt;
  }

  public void setExcnDt(Timestamp excnDt) {
    this.excnDt = excnDt;
  }


  public java.sql.Time getExcnTime() {
    return excnTime;
  }

  public void setExcnTime(java.sql.Time excnTime) {
    this.excnTime = excnTime;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getRouteNm() {
    return routeNm;
  }

  public void setRouteNm(String routeNm) {
    this.routeNm = routeNm;
  }

}
