package com.neighbor21.ggits.common.entity;

//행정시 산업 규모별 회사 종사자
public class KosisAdsiIndustScaleCoEnfsn {

  private String statsYyMm; //통계 년 월
  private String adsiNm; //행정시 명
  private String industClsfCd; //산업 분류 코드
  private String scaleClsfCd; //규모 분류 코드
  private long coCnt; //회사 수
  private long totEnfsnCnt; //전체 종사자 수
  private long maleEnfsnCnt; //남성 종사자 수
  private long femaleEnfsnCnt; //여성 종사자 수


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


  public String getScaleClsfCd() {
    return scaleClsfCd;
  }

  public void setScaleClsfCd(String scaleClsfCd) {
    this.scaleClsfCd = scaleClsfCd;
  }


  public long getCoCnt() {
    return coCnt;
  }

  public void setCoCnt(long coCnt) {
    this.coCnt = coCnt;
  }


  public long getTotEnfsnCnt() {
    return totEnfsnCnt;
  }

  public void setTotEnfsnCnt(long totEnfsnCnt) {
    this.totEnfsnCnt = totEnfsnCnt;
  }


  public long getMaleEnfsnCnt() {
    return maleEnfsnCnt;
  }

  public void setMaleEnfsnCnt(long maleEnfsnCnt) {
    this.maleEnfsnCnt = maleEnfsnCnt;
  }


  public long getFemaleEnfsnCnt() {
    return femaleEnfsnCnt;
  }

  public void setFemaleEnfsnCnt(long femaleEnfsnCnt) {
    this.femaleEnfsnCnt = femaleEnfsnCnt;
  }

}
