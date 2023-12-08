package com.neighbor21.ggits.common.entity;
public class ScsTConIntflow {
    private long    intLcno;        //교차로번호
    private long    intPlanclss;        //일반시차제구분
    private long    intRing;        //링번호
    private long    intPhaseno;        //현시번호
    private String    updateDate;        //갱신일시
    private long    flowNo;        //이동류번호
    private double    flowSLng;        //이동류시작점경도
    private double    flowSLat;        //이동류시작점위도
    private double    flowMLng;        //이동류중간점경도
    private double    flowMLat;        //이동류중간점위도
    private double    flowELng;        //이동류종료점경도
    private double    flowELat;        //이동류종료점위도


  public long getIntLcno() {
    return intLcno;
  }

  public void setIntLcno(long intLcno) {
    this.intLcno = intLcno;
  }


  public long getIntPlanclss() {
    return intPlanclss;
  }

  public void setIntPlanclss(long intPlanclss) {
    this.intPlanclss = intPlanclss;
  }


  public long getIntRing() {
    return intRing;
  }

  public void setIntRing(long intRing) {
    this.intRing = intRing;
  }


  public long getIntPhaseno() {
    return intPhaseno;
  }

  public void setIntPhaseno(long intPhaseno) {
    this.intPhaseno = intPhaseno;
  }


  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }


  public long getFlowNo() {
    return flowNo;
  }

  public void setFlowNo(long flowNo) {
    this.flowNo = flowNo;
  }


  public double getFlowSLng() {
    return flowSLng;
  }

  public void setFlowSLng(double flowSLng) {
    this.flowSLng = flowSLng;
  }


  public double getFlowSLat() {
    return flowSLat;
  }

  public void setFlowSLat(double flowSLat) {
    this.flowSLat = flowSLat;
  }


  public double getFlowMLng() {
    return flowMLng;
  }

  public void setFlowMLng(double flowMLng) {
    this.flowMLng = flowMLng;
  }


  public double getFlowMLat() {
    return flowMLat;
  }

  public void setFlowMLat(double flowMLat) {
    this.flowMLat = flowMLat;
  }


  public double getFlowELng() {
    return flowELng;
  }

  public void setFlowELng(double flowELng) {
    this.flowELng = flowELng;
  }


  public double getFlowELat() {
    return flowELat;
  }

  public void setFlowELat(double flowELat) {
    this.flowELat = flowELat;
  }

}
