package com.neighbor21.ggits.common.entity;

//행정시 산업별 취업자
public class KosisAdsiIndustEmpprn {

  private String statsYyMm; //통계 년 월
  private String adsiNm; //행정시 명
  private String industClsfCd; //산업 분류 코드
  private long empprnCnt; //취업자 수


  public String getStatsYyMm() {
    return statsYyMm;
  }

  public void setStatsYyMm(String statsYyMm) {
    this.statsYyMm = statsYyMm;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getIndustClsfCd() {
    return industClsfCd;
  }

  public void setIndustClsfCd(String industClsfCd) {
    this.industClsfCd = industClsfCd;
  }


  public long getEmpprnCnt() {
    return empprnCnt;
  }

  public void setEmpprnCnt(long empprnCnt) {
    this.empprnCnt = empprnCnt;
  }

}
