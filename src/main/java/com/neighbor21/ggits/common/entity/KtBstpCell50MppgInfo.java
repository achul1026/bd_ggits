package com.neighbor21.ggits.common.entity;
public class KtBstpCell50MppgInfo {
    private String    stationId;        //정류소 id
    private String    stationNm;        //정류소명칭
    private long    lonCrdn;        //경도 좌표
    private long    latCrdn;        //위도 좌표
    private String    cell50Id;        //셀 50 id


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


  public long getLonCrdn() {
    return lonCrdn;
  }

  public void setLonCrdn(long lonCrdn) {
    this.lonCrdn = lonCrdn;
  }


  public long getLatCrdn() {
    return latCrdn;
  }

  public void setLatCrdn(long latCrdn) {
    this.latCrdn = latCrdn;
  }


  public String getCell50Id() {
    return cell50Id;
  }

  public void setCell50Id(String cell50Id) {
    this.cell50Id = cell50Id;
  }

}
