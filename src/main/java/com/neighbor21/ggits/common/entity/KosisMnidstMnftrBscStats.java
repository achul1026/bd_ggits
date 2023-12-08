package com.neighbor21.ggits.common.entity;

//행정시 광업 제조 기본 통계
public class KosisMnidstMnftrBscStats {

  private String statsYyMm; //통계 년 월
  private String adsiNm; //행정시 명
  private String industDtlClsfCd; //산업 상세 분류 코드
  private long coCnt; //회사 수
  private long enfsnCnt; //종사자 수
  private long salaryAmt; //급여 금액
  private long shipmntAmt; //출하 금액
  private long mainPrdctnAmt; //주요 생산 금액
  private long valaddAmt; //부가가치 금액
  private long tngastYndBlnc; //유형자산 연말 잔액


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


  public String getIndustDtlClsfCd() {
    return industDtlClsfCd;
  }

  public void setIndustDtlClsfCd(String industDtlClsfCd) {
    this.industDtlClsfCd = industDtlClsfCd;
  }


  public long getCoCnt() {
    return coCnt;
  }

  public void setCoCnt(long coCnt) {
    this.coCnt = coCnt;
  }


  public long getEnfsnCnt() {
    return enfsnCnt;
  }

  public void setEnfsnCnt(long enfsnCnt) {
    this.enfsnCnt = enfsnCnt;
  }


  public long getSalaryAmt() {
    return salaryAmt;
  }

  public void setSalaryAmt(long salaryAmt) {
    this.salaryAmt = salaryAmt;
  }


  public long getShipmntAmt() {
    return shipmntAmt;
  }

  public void setShipmntAmt(long shipmntAmt) {
    this.shipmntAmt = shipmntAmt;
  }


  public long getMainPrdctnAmt() {
    return mainPrdctnAmt;
  }

  public void setMainPrdctnAmt(long mainPrdctnAmt) {
    this.mainPrdctnAmt = mainPrdctnAmt;
  }


  public long getValaddAmt() {
    return valaddAmt;
  }

  public void setValaddAmt(long valaddAmt) {
    this.valaddAmt = valaddAmt;
  }


  public long getTngastYndBlnc() {
    return tngastYndBlnc;
  }

  public void setTngastYndBlnc(long tngastYndBlnc) {
    this.tngastYndBlnc = tngastYndBlnc;
  }

}
