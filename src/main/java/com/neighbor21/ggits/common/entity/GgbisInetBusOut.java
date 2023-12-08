package com.neighbor21.ggits.common.entity;
public class GgbisInetBusOut {
    private String    routeId;        //노선ID
    private String    terId;        //터미널ID
    private String    edNm;        //도착지
    private String    beginTime;        //첫차
    private String    closeTime;        //막차
    private String    seqCnt;        //운행횟수
    private String    price;        //요금
    private long    routeLength;        //거리
    private String    routeTime;        //소요시간
    private String    edNmA;        //주요경유지1_도착지
    private String    priceA;        //주요경유지1_요금
    private String    edNmB;        //주요경유지2_도착지
    private String    priceB;        //주요경유지2_요금
    private String    edNmC;        //주요경유지3_도착지
    private String    priceC;        //주요경유지3_요금
    private String    remark;        //비고


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getTerId() {
    return terId;
  }

  public void setTerId(String terId) {
    this.terId = terId;
  }


  public String getEdNm() {
    return edNm;
  }

  public void setEdNm(String edNm) {
    this.edNm = edNm;
  }


  public String getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(String beginTime) {
    this.beginTime = beginTime;
  }


  public String getCloseTime() {
    return closeTime;
  }

  public void setCloseTime(String closeTime) {
    this.closeTime = closeTime;
  }


  public String getSeqCnt() {
    return seqCnt;
  }

  public void setSeqCnt(String seqCnt) {
    this.seqCnt = seqCnt;
  }


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }


  public long getRouteLength() {
    return routeLength;
  }

  public void setRouteLength(long routeLength) {
    this.routeLength = routeLength;
  }


  public String getRouteTime() {
    return routeTime;
  }

  public void setRouteTime(String routeTime) {
    this.routeTime = routeTime;
  }


  public String getEdNmA() {
    return edNmA;
  }

  public void setEdNmA(String edNmA) {
    this.edNmA = edNmA;
  }


  public String getPriceA() {
    return priceA;
  }

  public void setPriceA(String priceA) {
    this.priceA = priceA;
  }


  public String getEdNmB() {
    return edNmB;
  }

  public void setEdNmB(String edNmB) {
    this.edNmB = edNmB;
  }


  public String getPriceB() {
    return priceB;
  }

  public void setPriceB(String priceB) {
    this.priceB = priceB;
  }


  public String getEdNmC() {
    return edNmC;
  }

  public void setEdNmC(String edNmC) {
    this.edNmC = edNmC;
  }


  public String getPriceC() {
    return priceC;
  }

  public void setPriceC(String priceC) {
    this.priceC = priceC;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
