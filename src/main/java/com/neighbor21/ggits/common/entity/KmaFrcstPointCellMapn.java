package com.neighbor21.ggits.common.entity;
public class KmaFrcstPointCellMapn {
    private String    addngCd;        //행정동 코드
    private long    frcstPointXcord;        //예보 지점 x좌표
    private long    frcstPointYcord;        //예보 지점 y좌표
    private long    frcstPointLon;        //예보 지점 경도
    private long    frcstPointLat;        //예보 지점 위도
    private String    etlDt;        //etl 일시


  public String getAddngCd() {
    return addngCd;
  }

  public void setAddngCd(String addngCd) {
    this.addngCd = addngCd;
  }


  public long getFrcstPointXcord() {
    return frcstPointXcord;
  }

  public void setFrcstPointXcord(long frcstPointXcord) {
    this.frcstPointXcord = frcstPointXcord;
  }


  public long getFrcstPointYcord() {
    return frcstPointYcord;
  }

  public void setFrcstPointYcord(long frcstPointYcord) {
    this.frcstPointYcord = frcstPointYcord;
  }


  public long getFrcstPointLon() {
    return frcstPointLon;
  }

  public void setFrcstPointLon(long frcstPointLon) {
    this.frcstPointLon = frcstPointLon;
  }


  public long getFrcstPointLat() {
    return frcstPointLat;
  }

  public void setFrcstPointLat(long frcstPointLat) {
    this.frcstPointLat = frcstPointLat;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
