package com.neighbor21.ggits.common.entity;

//직업별 취업자
public class KosisCareerEmpprn {

  private String statsYyMm; //통계 년 월
  private String careerClsfCd; //직업 분류 코드
  private long empprnCnt; //취업자 수


  public String getStatsYyMm() {
    return statsYyMm;
  }

  public void setStatsYyMm(String statsYyMm) {
    this.statsYyMm = statsYyMm;
  }


  public String getCareerClsfCd() {
    return careerClsfCd;
  }

  public void setCareerClsfCd(String careerClsfCd) {
    this.careerClsfCd = careerClsfCd;
  }


  public long getEmpprnCnt() {
    return empprnCnt;
  }

  public void setEmpprnCnt(long empprnCnt) {
    this.empprnCnt = empprnCnt;
  }

}
