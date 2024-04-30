package com.neighbor21.ggits.common.entity;
public class MrtInlayOd {
    private String    rideMonth;        //승차년월
    private String    stStationId;        //승차정류소ID
    private String    edStationId;        //하차정류소ID
    private String    baseym;        //기준년월
    private String    stOrgNm;        //승차지역명
    private String    edOrgNm;        //하차지역명
    private String    stNm;        //승차정류소명
    private String    edNm;        //하차정류소명
    private long    stNum;        //승차인원수
    private long    edNum;        //하차인원수
    private long    edTnsNum;        //환승하차인원수
    private long    od;        //하차인원합계
    private String    etlDt;        //ETL 일시


  public String getRideMonth() {
    return rideMonth;
  }

  public void setRideMonth(String rideMonth) {
    this.rideMonth = rideMonth;
  }


  public String getStStationId() {
    return stStationId;
  }

  public void setStStationId(String stStationId) {
    this.stStationId = stStationId;
  }


  public String getEdStationId() {
    return edStationId;
  }

  public void setEdStationId(String edStationId) {
    this.edStationId = edStationId;
  }


  public String getBaseym() {
    return baseym;
  }

  public void setBaseym(String baseym) {
    this.baseym = baseym;
  }


  public String getStOrgNm() {
    return stOrgNm;
  }

  public void setStOrgNm(String stOrgNm) {
    this.stOrgNm = stOrgNm;
  }


  public String getEdOrgNm() {
    return edOrgNm;
  }

  public void setEdOrgNm(String edOrgNm) {
    this.edOrgNm = edOrgNm;
  }


  public String getStNm() {
    return stNm;
  }

  public void setStNm(String stNm) {
    this.stNm = stNm;
  }


  public String getEdNm() {
    return edNm;
  }

  public void setEdNm(String edNm) {
    this.edNm = edNm;
  }


  public long getStNum() {
    return stNum;
  }

  public void setStNum(long stNum) {
    this.stNum = stNum;
  }


  public long getEdNum() {
    return edNum;
  }

  public void setEdNum(long edNum) {
    this.edNum = edNum;
  }


  public long getEdTnsNum() {
    return edTnsNum;
  }

  public void setEdTnsNum(long edTnsNum) {
    this.edTnsNum = edTnsNum;
  }


  public long getOd() {
    return od;
  }

  public void setOd(long od) {
    this.od = od;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
