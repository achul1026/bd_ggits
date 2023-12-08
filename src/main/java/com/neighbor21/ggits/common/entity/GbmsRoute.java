package com.neighbor21.ggits.common.entity;
public class GbmsRoute {
    private String    routeId;        //노선id
    private String    routeNm;        //노선명칭
    private String    routeTy;        //노선타입
    private String    stStaId;        //기점정류소id
    private String    edStaId;        //종점정류소id
    private String    routeEx;        //노선설명
    private String    adminNm;        //관할관청
    private String    areaCode;        //인허가 지역
    private long    turnSeq;        //회차점순번
    private String    routeAlloc;        //공동배차여부
    private String    useYn;        //노선사용유무


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


  public String getRouteTy() {
    return routeTy;
  }

  public void setRouteTy(String routeTy) {
    this.routeTy = routeTy;
  }


  public String getStStaId() {
    return stStaId;
  }

  public void setStStaId(String stStaId) {
    this.stStaId = stStaId;
  }


  public String getEdStaId() {
    return edStaId;
  }

  public void setEdStaId(String edStaId) {
    this.edStaId = edStaId;
  }


  public String getRouteEx() {
    return routeEx;
  }

  public void setRouteEx(String routeEx) {
    this.routeEx = routeEx;
  }


  public String getAdminNm() {
    return adminNm;
  }

  public void setAdminNm(String adminNm) {
    this.adminNm = adminNm;
  }


  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }


  public long getTurnSeq() {
    return turnSeq;
  }

  public void setTurnSeq(long turnSeq) {
    this.turnSeq = turnSeq;
  }


  public String getRouteAlloc() {
    return routeAlloc;
  }

  public void setRouteAlloc(String routeAlloc) {
    this.routeAlloc = routeAlloc;
  }


  public String getUseYn() {
    return useYn;
  }

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }

}
