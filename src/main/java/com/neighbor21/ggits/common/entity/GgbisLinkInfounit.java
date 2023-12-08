package com.neighbor21.ggits.common.entity;
public class GgbisLinkInfounit {
    private String    routeId;        //노선 ID
    private String    linkId;        //도로 ID
    private String    unitSectionId;        //단위구간ID
    private long    unitOrder;        //단위구간순서
    private long    linkSeq;        //링크순번
    private long    groupSeq;        //그룹순번


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getUnitSectionId() {
    return unitSectionId;
  }

  public void setUnitSectionId(String unitSectionId) {
    this.unitSectionId = unitSectionId;
  }


  public long getUnitOrder() {
    return unitOrder;
  }

  public void setUnitOrder(long unitOrder) {
    this.unitOrder = unitOrder;
  }


  public long getLinkSeq() {
    return linkSeq;
  }

  public void setLinkSeq(long linkSeq) {
    this.linkSeq = linkSeq;
  }


  public long getGroupSeq() {
    return groupSeq;
  }

  public void setGroupSeq(long groupSeq) {
    this.groupSeq = groupSeq;
  }

}
