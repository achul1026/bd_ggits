package com.neighbor21.ggits.common.entity;
public class GgbisInfounitSection {
    private String    unitSectionId;        //단위구간ID
    private String    stLocationId;        //시작위치ID
    private String    edLocationId;        //종료위치ID
    private String    stLocationTp;        //시작위치구분
    private String    edLocationTp;        //종료위치구분
    private long    sectionDist;        //구간거리
    private String    wbisYn;        //광역정보 사용여부


  public String getUnitSectionId() {
    return unitSectionId;
  }

  public void setUnitSectionId(String unitSectionId) {
    this.unitSectionId = unitSectionId;
  }


  public String getStLocationId() {
    return stLocationId;
  }

  public void setStLocationId(String stLocationId) {
    this.stLocationId = stLocationId;
  }


  public String getEdLocationId() {
    return edLocationId;
  }

  public void setEdLocationId(String edLocationId) {
    this.edLocationId = edLocationId;
  }


  public String getStLocationTp() {
    return stLocationTp;
  }

  public void setStLocationTp(String stLocationTp) {
    this.stLocationTp = stLocationTp;
  }


  public String getEdLocationTp() {
    return edLocationTp;
  }

  public void setEdLocationTp(String edLocationTp) {
    this.edLocationTp = edLocationTp;
  }


  public long getSectionDist() {
    return sectionDist;
  }

  public void setSectionDist(long sectionDist) {
    this.sectionDist = sectionDist;
  }


  public String getWbisYn() {
    return wbisYn;
  }

  public void setWbisYn(String wbisYn) {
    this.wbisYn = wbisYn;
  }

}
