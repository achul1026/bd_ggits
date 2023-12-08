package com.neighbor21.ggits.common.entity;
public class GgbisRouteCompose {
    private String    routeId;        //노선ID
    private long    sequence;        //순번
    private String    type;        //구분(1:정류장 2:교차로(LINK_ID가 null 이면 노선이탈 포인트) 3:포인트 5:미수집데이터)
    private String    locationId;        //교차로/정류소 ID
    private long    subLength;        //단위거리
    private long    sumLength;        //누적거리
    private String    linkId;        //링크 ID(null 이고 type 값이 2 이면 노선이탈 포인트)
    private long    vertexX;        //버텍스 X
    private long    vertexY;        //버텍스 Y


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public long getSequence() {
    return sequence;
  }

  public void setSequence(long sequence) {
    this.sequence = sequence;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getLocationId() {
    return locationId;
  }

  public void setLocationId(String locationId) {
    this.locationId = locationId;
  }


  public long getSubLength() {
    return subLength;
  }

  public void setSubLength(long subLength) {
    this.subLength = subLength;
  }


  public long getSumLength() {
    return sumLength;
  }

  public void setSumLength(long sumLength) {
    this.sumLength = sumLength;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public long getVertexX() {
    return vertexX;
  }

  public void setVertexX(long vertexX) {
    this.vertexX = vertexX;
  }


  public long getVertexY() {
    return vertexY;
  }

  public void setVertexY(long vertexY) {
    this.vertexY = vertexY;
  }

}
