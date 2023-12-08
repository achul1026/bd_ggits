package com.neighbor21.ggits.common.entity;

//구역 인구 이동 정보
public class MoisDstrctPopltnMoveInfo {

  private String statsYyMm; //통계 년 월
  private String trnsfrnAdmnmhCd; //전입 행정기관 코드
  private String mvtAdmnmhCd; //전출 행정기관 코드
  private String trnsfrnAdsiNm; //전입 행정시 명
  private String trnsfrnAdguNm; //전입 행정구 명
  private String trnsfrnAddngNm; //전입 행정동 명
  private String mvtAdsiNm; //전출 행정시 명
  private String mvtAdguNm; //전출 행정구 명
  private String mvtAddngNm; //전출 행정동 명
  private long totPopltnCnt; //전체 인구 수
  private long malePopltnCnt; //남성 인구 수
  private long femalePopltnCnt; //여성 인구 수


  public String getStatsYyMm() {
    return statsYyMm;
  }

  public void setStatsYyMm(String statsYyMm) {
    this.statsYyMm = statsYyMm;
  }


  public String getTrnsfrnAdmnmhCd() {
    return trnsfrnAdmnmhCd;
  }

  public void setTrnsfrnAdmnmhCd(String trnsfrnAdmnmhCd) {
    this.trnsfrnAdmnmhCd = trnsfrnAdmnmhCd;
  }


  public String getMvtAdmnmhCd() {
    return mvtAdmnmhCd;
  }

  public void setMvtAdmnmhCd(String mvtAdmnmhCd) {
    this.mvtAdmnmhCd = mvtAdmnmhCd;
  }


  public String getTrnsfrnAdsiNm() {
    return trnsfrnAdsiNm;
  }

  public void setTrnsfrnAdsiNm(String trnsfrnAdsiNm) {
    this.trnsfrnAdsiNm = trnsfrnAdsiNm;
  }


  public String getTrnsfrnAdguNm() {
    return trnsfrnAdguNm;
  }

  public void setTrnsfrnAdguNm(String trnsfrnAdguNm) {
    this.trnsfrnAdguNm = trnsfrnAdguNm;
  }


  public String getTrnsfrnAddngNm() {
    return trnsfrnAddngNm;
  }

  public void setTrnsfrnAddngNm(String trnsfrnAddngNm) {
    this.trnsfrnAddngNm = trnsfrnAddngNm;
  }


  public String getMvtAdsiNm() {
    return mvtAdsiNm;
  }

  public void setMvtAdsiNm(String mvtAdsiNm) {
    this.mvtAdsiNm = mvtAdsiNm;
  }


  public String getMvtAdguNm() {
    return mvtAdguNm;
  }

  public void setMvtAdguNm(String mvtAdguNm) {
    this.mvtAdguNm = mvtAdguNm;
  }


  public String getMvtAddngNm() {
    return mvtAddngNm;
  }

  public void setMvtAddngNm(String mvtAddngNm) {
    this.mvtAddngNm = mvtAddngNm;
  }


  public long getTotPopltnCnt() {
    return totPopltnCnt;
  }

  public void setTotPopltnCnt(long totPopltnCnt) {
    this.totPopltnCnt = totPopltnCnt;
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

}
