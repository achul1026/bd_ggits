package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgbisBusLineout {
    private String    vehId;        //차량 ID
    private String    routeId;        //노선 ID
    private Timestamp    collectDate;        //정보생성일시
    private long    gpsX;        //GPS X좌표
    private long    gpsY;        //GPS Y좌표


  public String getVehId() {
    return vehId;
  }

  public void setVehId(String vehId) {
    this.vehId = vehId;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public Timestamp getCollectDate() {
    return collectDate;
  }

  public void setCollectDate(Timestamp collectDate) {
    this.collectDate = collectDate;
  }


  public long getGpsX() {
    return gpsX;
  }

  public void setGpsX(long gpsX) {
    this.gpsX = gpsX;
  }


  public long getGpsY() {
    return gpsY;
  }

  public void setGpsY(long gpsY) {
    this.gpsY = gpsY;
  }

}
