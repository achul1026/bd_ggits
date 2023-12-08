package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgbisSectionInfo {
    private Timestamp    collectDate;        //정보수집일시
    private String    routeId;        //노선ID
    private String    vehId;        //차량ID
    private String    infoTp;        //정보구분
    private String    sectionId;        //구간ID
    private long    ttime;        //통행시간
    private long    stime;        //정차시간
    private long    length;        //구간길이
    private long    sectionOrder;        //구간순서(검토)


  public Timestamp getCollectDate() {
    return collectDate;
  }

  public void setCollectDate(Timestamp collectDate) {
    this.collectDate = collectDate;
  }


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getVehId() {
    return vehId;
  }

  public void setVehId(String vehId) {
    this.vehId = vehId;
  }


  public String getInfoTp() {
    return infoTp;
  }

  public void setInfoTp(String infoTp) {
    this.infoTp = infoTp;
  }


  public String getSectionId() {
    return sectionId;
  }

  public void setSectionId(String sectionId) {
    this.sectionId = sectionId;
  }


  public long getTtime() {
    return ttime;
  }

  public void setTtime(long ttime) {
    this.ttime = ttime;
  }


  public long getStime() {
    return stime;
  }

  public void setStime(long stime) {
    this.stime = stime;
  }


  public long getLength() {
    return length;
  }

  public void setLength(long length) {
    this.length = length;
  }


  public long getSectionOrder() {
    return sectionOrder;
  }

  public void setSectionOrder(long sectionOrder) {
    this.sectionOrder = sectionOrder;
  }

}
