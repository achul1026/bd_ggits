package com.neighbor21.ggits.common.entity;
public class MrtInlayCandStation {
    private String    baseym;        //기준년월
    private String    routeId;        //노선ID
    private String    updown;        //노선방향
    private String    stationId;        //정류소ID
    private String    stationNm;        //정류소명
    private String    adminNm;        //관할관청
    private String    useYn;        //사용여부
    private double    X;        //경도
    private double    Y;        //위도
    private String    pointWgs84;        //WGS84_좌표
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


  public String getStationNm() {
    return stationNm;
  }

  public void setStationNm(String stationNm) {
    this.stationNm = stationNm;
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


  public String getPointWgs84() {
    return pointWgs84;
  }

  public void setPointWgs84(String pointWgs84) {
    this.pointWgs84 = pointWgs84;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
