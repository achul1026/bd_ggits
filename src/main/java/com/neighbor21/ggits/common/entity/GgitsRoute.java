package com.neighbor21.ggits.common.entity;
public class GgitsRoute {
    private String    routeId;        //노선id
    private String    routeImageId;        //vms표출을 위한 노선의 심볼 id
    private String    roadRank;        //도로 등급
    private String    routeTp;        //노선구분
    private String    routeNo;        //노선번호
    private String    routeKornm;        //노선한글명
    private String    routeKorShortnm;        //노선한글단축명
    private String    routeEngnm;        //노선 영문명
    private String    startNodeNm;        //상행 종점노드명
    private String    endNodeNm;        //하행 종점노드명
    private long    orderNum;        //홈페이지/앱 노출순번
    private String    mainrouteflag;        //mainrouteflag
    private String    roadDet1;        //도로개요 상세1
    private String    roadDet2;        //도로개요 상세2


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getRouteImageId() {
    return routeImageId;
  }

  public void setRouteImageId(String routeImageId) {
    this.routeImageId = routeImageId;
  }


  public String getRoadRank() {
    return roadRank;
  }

  public void setRoadRank(String roadRank) {
    this.roadRank = roadRank;
  }


  public String getRouteTp() {
    return routeTp;
  }

  public void setRouteTp(String routeTp) {
    this.routeTp = routeTp;
  }


  public String getRouteNo() {
    return routeNo;
  }

  public void setRouteNo(String routeNo) {
    this.routeNo = routeNo;
  }


  public String getRouteKornm() {
    return routeKornm;
  }

  public void setRouteKornm(String routeKornm) {
    this.routeKornm = routeKornm;
  }


  public String getRouteKorShortnm() {
    return routeKorShortnm;
  }

  public void setRouteKorShortnm(String routeKorShortnm) {
    this.routeKorShortnm = routeKorShortnm;
  }


  public String getRouteEngnm() {
    return routeEngnm;
  }

  public void setRouteEngnm(String routeEngnm) {
    this.routeEngnm = routeEngnm;
  }


  public String getStartNodeNm() {
    return startNodeNm;
  }

  public void setStartNodeNm(String startNodeNm) {
    this.startNodeNm = startNodeNm;
  }


  public String getEndNodeNm() {
    return endNodeNm;
  }

  public void setEndNodeNm(String endNodeNm) {
    this.endNodeNm = endNodeNm;
  }


  public long getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(long orderNum) {
    this.orderNum = orderNum;
  }


  public String getMainrouteflag() {
    return mainrouteflag;
  }

  public void setMainrouteflag(String mainrouteflag) {
    this.mainrouteflag = mainrouteflag;
  }


  public String getRoadDet1() {
    return roadDet1;
  }

  public void setRoadDet1(String roadDet1) {
    this.roadDet1 = roadDet1;
  }


  public String getRoadDet2() {
    return roadDet2;
  }

  public void setRoadDet2(String roadDet2) {
    this.roadDet2 = roadDet2;
  }

}
