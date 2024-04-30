package com.neighbor21.ggits.common.entity;
public class MrtInlayLink {
    private String    baseym;        //기준년월
    private String    routeId;        //노선ID
    private String    updown;        //노선방향
    private String    linkId;        //링크ID
    private String    fNode;        //시작노드ID
    private String    tNode;        //종료노드ID
    private String    roadName;        //도로명
    private double    lanes;        //차로수
    private String    roadRank;        //도로등급
    private String    roadType;        //도로유형
    private String    roadNo;        //도로번호
    private String    roadUse;        //도로사용여부
    private String    multiLink;        //중용구간여부
    private String    connect;        //연결로코드
    private double    maxSpd;        //최고제한속도
    private String    restVeh;        //통과제한차량
    private double    restW;        //통과제한하중
    private double    restH;        //통과제한높이
    private double    length;        //도로길이
    private String    remark;        //비고
    private String    linestringWgs84;        //WGS84_좌표
    private String    etlDt;        //ETL 일시


  public String getBaseym() {
    return baseym;
  }

  public void setBaseym(String baseym) {
    this.baseym = baseym;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getUpdown() {
    return updown;
  }

  public void setUpdown(String updown) {
    this.updown = updown;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getFNode() {
    return fNode;
  }

  public void setFNode(String fNode) {
    this.fNode = fNode;
  }


  public String getTNode() {
    return tNode;
  }

  public void setTNode(String tNode) {
    this.tNode = tNode;
  }


  public String getRoadName() {
    return roadName;
  }

  public void setRoadName(String roadName) {
    this.roadName = roadName;
  }


  public double getLanes() {
    return lanes;
  }

  public void setLanes(double lanes) {
    this.lanes = lanes;
  }


  public String getRoadRank() {
    return roadRank;
  }

  public void setRoadRank(String roadRank) {
    this.roadRank = roadRank;
  }


  public String getRoadType() {
    return roadType;
  }

  public void setRoadType(String roadType) {
    this.roadType = roadType;
  }


  public String getRoadNo() {
    return roadNo;
  }

  public void setRoadNo(String roadNo) {
    this.roadNo = roadNo;
  }


  public String getRoadUse() {
    return roadUse;
  }

  public void setRoadUse(String roadUse) {
    this.roadUse = roadUse;
  }


  public String getMultiLink() {
    return multiLink;
  }

  public void setMultiLink(String multiLink) {
    this.multiLink = multiLink;
  }


  public String getConnect() {
    return connect;
  }

  public void setConnect(String connect) {
    this.connect = connect;
  }


  public double getMaxSpd() {
    return maxSpd;
  }

  public void setMaxSpd(double maxSpd) {
    this.maxSpd = maxSpd;
  }


  public String getRestVeh() {
    return restVeh;
  }

  public void setRestVeh(String restVeh) {
    this.restVeh = restVeh;
  }


  public double getRestW() {
    return restW;
  }

  public void setRestW(double restW) {
    this.restW = restW;
  }


  public double getRestH() {
    return restH;
  }

  public void setRestH(double restH) {
    this.restH = restH;
  }


  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getLinestringWgs84() {
    return linestringWgs84;
  }

  public void setLinestringWgs84(String linestringWgs84) {
    this.linestringWgs84 = linestringWgs84;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
