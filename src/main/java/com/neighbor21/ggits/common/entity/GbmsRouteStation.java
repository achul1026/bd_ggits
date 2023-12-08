package com.neighbor21.ggits.common.entity;
public class GbmsRouteStation {
    private String    routeId;        //노선 id
    private long    order;        //운행 순서
    private String    stationId;        //정류소 id
    private long    orgStaLength;        //누적거리
    private long    staStaLength;        //정류소간거리
    private String    ebStationId;        //eb 정류소 id


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public long getOrder() {
    return order;
  }

  public void setOrder(long order) {
    this.order = order;
  }


  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }


  public long getOrgStaLength() {
    return orgStaLength;
  }

  public void setOrgStaLength(long orgStaLength) {
    this.orgStaLength = orgStaLength;
  }


  public long getStaStaLength() {
    return staStaLength;
  }

  public void setStaStaLength(long staStaLength) {
    this.staStaLength = staStaLength;
  }


  public String getEbStationId() {
    return ebStationId;
  }

  public void setEbStationId(String ebStationId) {
    this.ebStationId = ebStationId;
  }

}
