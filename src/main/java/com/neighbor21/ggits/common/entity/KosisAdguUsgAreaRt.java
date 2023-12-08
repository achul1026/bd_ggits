package com.neighbor21.ggits.common.entity;

//행정구 용도지역별 면적 비율
public class KosisAdguUsgAreaRt {

  private String statsYy; //통계 년
  private String adguNm; //행정구 명
  private String usgDstrctCd; //용도 구역 코드
  private long usgArea; //용도 면적
  private long usgRt; //용도 비율


  public String getStatsYy() {
    return statsYy;
  }

  public void setStatsYy(String statsYy) {
    this.statsYy = statsYy;
  }


  public String getAdguNm() {
    return adguNm;
  }

  public void setAdguNm(String adguNm) {
    this.adguNm = adguNm;
  }


  public String getUsgDstrctCd() {
    return usgDstrctCd;
  }

  public void setUsgDstrctCd(String usgDstrctCd) {
    this.usgDstrctCd = usgDstrctCd;
  }


  public long getUsgArea() {
    return usgArea;
  }

  public void setUsgArea(long usgArea) {
    this.usgArea = usgArea;
  }


  public long getUsgRt() {
    return usgRt;
  }

  public void setUsgRt(long usgRt) {
    this.usgRt = usgRt;
  }

}
