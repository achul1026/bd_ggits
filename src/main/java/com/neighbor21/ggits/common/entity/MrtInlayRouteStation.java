package com.neighbor21.ggits.common.entity;
public class MrtInlayRouteStation {
    private String    baseym;        //기준년월
    private String    routeId;        //노선ID
    private String    updown;        //노선방향
    private String    stationId;        //정류소ID
    private long    staOrder;        //정류소순서
    private String    stationNm;        //정류소명
    private long    gisLength;        //정류소간거리
    private String    routeNm;        //노선명
    private String    adminNm;        //관할관청
    private String    useYn;        //사용여부
    private double    X;        //경도
    private double    Y;        //위도
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


  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }


  public long getStaOrder() {
    return staOrder;
  }

  public void setStaOrder(long staOrder) {
    this.staOrder = staOrder;
  }


  public String getStationNm() {
    return stationNm;
  }

  public void setStationNm(String stationNm) {
    this.stationNm = stationNm;
  }


  public long getGisLength() {
    return gisLength;
  }

  public void setGisLength(long gisLength) {
    this.gisLength = gisLength;
  }


  public String getRouteNm() {
    return routeNm;
  }

  public void setRouteNm(String routeNm) {
    this.routeNm = routeNm;
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


  public double getX() {
    return X;
  }

  public void setX(double X) {
    this.X = X;
  }


  public double getY() {
    return Y;
  }

  public void setY(double Y) {
    this.Y = Y;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
