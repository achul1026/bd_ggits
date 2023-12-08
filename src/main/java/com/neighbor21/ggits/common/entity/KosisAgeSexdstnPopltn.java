package com.neighbor21.ggits.common.entity;

//연령 성별 추계인구
public class KosisAgeSexdstnPopltn {

  private String statsYy; //통계 년
  private String sexdstnCd; //성별 코드
  private String ageCd; //연령 코드
  private String adsiNm; //행정시 명
  private long estmatPopltnCnt; //추계 인구 수


  public String getStatsYy() {
    return statsYy;
  }

  public void setStatsYy(String statsYy) {
    this.statsYy = statsYy;
  }


  public String getSexdstnCd() {
    return sexdstnCd;
  }

  public void setSexdstnCd(String sexdstnCd) {
    this.sexdstnCd = sexdstnCd;
  }


  public String getAgeCd() {
    return ageCd;
  }

  public void setAgeCd(String ageCd) {
    this.ageCd = ageCd;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public long getEstmatPopltnCnt() {
    return estmatPopltnCnt;
  }

  public void setEstmatPopltnCnt(long estmatPopltnCnt) {
    this.estmatPopltnCnt = estmatPopltnCnt;
  }

}
