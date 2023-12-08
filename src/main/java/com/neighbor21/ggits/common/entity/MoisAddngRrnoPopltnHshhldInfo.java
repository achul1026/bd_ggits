package com.neighbor21.ggits.common.entity;

//행정동 주민등록번호 인구 세대 정보
public class MoisAddngRrnoPopltnHshhldInfo {

  private String statsYyMm; //통계 년 월
  private String admnmhCd; //행정기관 코드
  private String adsiNm; //행정시 명
  private String adguNm; //행정구 명
  private String addngNm; //행정동 명
  private String adtongNm; //행정통 명
  private String adbanNm; //행정반 명
  private long totPopltnCnt; //전체 인구 수
  private long hshldCnt; //세대 수
  private long hshldPopltnCnt; //세대 인구 수
  private long malePopltnCnt; //남성 인구 수
  private long femalePopltnCnt; //여성 인구 수
  private long maleFemaleRt; //남성 여성 비율


  public String getStatsYyMm() {
    return statsYyMm;
  }

  public void setStatsYyMm(String statsYyMm) {
    this.statsYyMm = statsYyMm;
  }


  public String getAdmnmhCd() {
    return admnmhCd;
  }

  public void setAdmnmhCd(String admnmhCd) {
    this.admnmhCd = admnmhCd;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getAdguNm() {
    return adguNm;
  }

  public void setAdguNm(String adguNm) {
    this.adguNm = adguNm;
  }


  public String getAddngNm() {
    return addngNm;
  }

  public void setAddngNm(String addngNm) {
    this.addngNm = addngNm;
  }


  public String getAdtongNm() {
    return adtongNm;
  }

  public void setAdtongNm(String adtongNm) {
    this.adtongNm = adtongNm;
  }


  public String getAdbanNm() {
    return adbanNm;
  }

  public void setAdbanNm(String adbanNm) {
    this.adbanNm = adbanNm;
  }


  public long getTotPopltnCnt() {
    return totPopltnCnt;
  }

  public void setTotPopltnCnt(long totPopltnCnt) {
    this.totPopltnCnt = totPopltnCnt;
  }


  public long getHshldCnt() {
    return hshldCnt;
  }

  public void setHshldCnt(long hshldCnt) {
    this.hshldCnt = hshldCnt;
  }


  public long getHshldPopltnCnt() {
    return hshldPopltnCnt;
  }

  public void setHshldPopltnCnt(long hshldPopltnCnt) {
    this.hshldPopltnCnt = hshldPopltnCnt;
  }


  public long getMalePopltnCnt() {
    return malePopltnCnt;
  }

  public void setMalePopltnCnt(long malePopltnCnt) {
    this.malePopltnCnt = malePopltnCnt;
  }


  public long getFemalePopltnCnt() {
    return femalePopltnCnt;
  }

  public void setFemalePopltnCnt(long femalePopltnCnt) {
    this.femalePopltnCnt = femalePopltnCnt;
  }


  public long getMaleFemaleRt() {
    return maleFemaleRt;
  }

  public void setMaleFemaleRt(long maleFemaleRt) {
    this.maleFemaleRt = maleFemaleRt;
  }

}
