package com.neighbor21.ggits.common.entity;
public class MrtInlayRoute {
    private String    baseym;        //기준년월
    private String    routeId;        //노선ID
    private String    updown;        //노선방향
    private String    routeNm;        //노선명
    private String    routeTp;        //노선유형
    private double    routeLength;        //노선길이
    private String    stStaNm;        //기점정류소명
    private String    edStaNm;        //종점정류소명
    private String    stStaId;        //기점정류소ID
    private String    edStaId;        //종점정류소ID
    private long    turnSeq;        //회차점순번
    private String    adminNm;        //관할관청
    private String    useYn;        //노선사용유무
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


  public String getRouteNm() {
    return routeNm;
  }

  public void setRouteNm(String routeNm) {
    this.routeNm = routeNm;
  }


  public String getRouteTp() {
    return routeTp;
  }

  public void setRouteTp(String routeTp) {
    this.routeTp = routeTp;
  }


  public double getRouteLength() {
    return routeLength;
  }

  public void setRouteLength(double routeLength) {
    this.routeLength = routeLength;
  }


  public String getStStaNm() {
    return stStaNm;
  }

  public void setStStaNm(String stStaNm) {
    this.stStaNm = stStaNm;
  }


  public String getEdStaNm() {
    return edStaNm;
  }

  public void setEdStaNm(String edStaNm) {
    this.edStaNm = edStaNm;
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


  public long getTurnSeq() {
    return turnSeq;
  }

  public void setTurnSeq(long turnSeq) {
    this.turnSeq = turnSeq;
  }


  public String getAdminNm() {
    return adminNm;
  }

  public void setAdminNm(String adminNm) {
    this.adminNm = adminNm;
  }


  public String getUseYn() {
    return useYn;
  }

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
