package com.neighbor21.ggits.common.entity;

//운전면허 취득자
public class KosisDrvLcnsPoseser {

  private String statsYy; //통계 년
  private String drvLcnsCd; //운전 면허 코드
  private String sexdstnCd; //성별 코드
  private long drvLcnsPoseserCnt; //운전 면허 소지자 수


  public String getStatsYy() {
    return statsYy;
  }

  public void setStatsYy(String statsYy) {
    this.statsYy = statsYy;
  }


  public String getDrvLcnsCd() {
    return drvLcnsCd;
  }

  public void setDrvLcnsCd(String drvLcnsCd) {
    this.drvLcnsCd = drvLcnsCd;
  }


  public String getSexdstnCd() {
    return sexdstnCd;
  }

  public void setSexdstnCd(String sexdstnCd) {
    this.sexdstnCd = sexdstnCd;
  }


  public long getDrvLcnsPoseserCnt() {
    return drvLcnsPoseserCnt;
  }

  public void setDrvLcnsPoseserCnt(long drvLcnsPoseserCnt) {
    this.drvLcnsPoseserCnt = drvLcnsPoseserCnt;
  }

}
