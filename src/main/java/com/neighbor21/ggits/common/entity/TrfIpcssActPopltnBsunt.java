package com.neighbor21.ggits.common.entity;

//교통 영향평가 활동 인구 원단위
public class TrfIpcssActPopltnBsunt extends CommonEntity{

  private String ipcssMngNo; //영향평가 관리 번호
  private String smlfactNm; //유사시설 명
  private String smlfactAddr; //유사시설 주소
  private String scale; //규모
  private long totfrar; //연면적
  private String exmnDivNm; //조사 구분 명
  private String exmnYmd; //조사 일자
  private String exmnDocNm; //조사 문서 명
  private String usgCd; //용도 코드
  private String usgTotfrar; //용도 연면적
  private long resdngBsunt; //상주 원단위
  private long visitBsunt; //방문 원단위
  private String bsuntUnit; //원단위 단위


  public String getIpcssMngNo() {
    return ipcssMngNo;
  }

  public void setIpcssMngNo(String ipcssMngNo) {
    this.ipcssMngNo = ipcssMngNo;
  }


  public String getSmlfactNm() {
    return smlfactNm;
  }

  public void setSmlfactNm(String smlfactNm) {
    this.smlfactNm = smlfactNm;
  }


  public String getSmlfactAddr() {
    return smlfactAddr;
  }

  public void setSmlfactAddr(String smlfactAddr) {
    this.smlfactAddr = smlfactAddr;
  }


  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }


  public long getTotfrar() {
    return totfrar;
  }

  public void setTotfrar(long totfrar) {
    this.totfrar = totfrar;
  }


  public String getExmnDivNm() {
    return exmnDivNm;
  }

  public void setExmnDivNm(String exmnDivNm) {
    this.exmnDivNm = exmnDivNm;
  }


  public String getExmnYmd() {
    return exmnYmd;
  }

  public void setExmnYmd(String exmnYmd) {
    this.exmnYmd = exmnYmd;
  }


  public String getExmnDocNm() {
    return exmnDocNm;
  }

  public void setExmnDocNm(String exmnDocNm) {
    this.exmnDocNm = exmnDocNm;
  }


  public String getUsgCd() {
    return usgCd;
  }

  public void setUsgCd(String usgCd) {
    this.usgCd = usgCd;
  }


  public String getUsgTotfrar() {
    return usgTotfrar;
  }

  public void setUsgTotfrar(String usgTotfrar) {
    this.usgTotfrar = usgTotfrar;
  }


  public long getResdngBsunt() {
    return resdngBsunt;
  }

  public void setResdngBsunt(long resdngBsunt) {
    this.resdngBsunt = resdngBsunt;
  }


  public long getVisitBsunt() {
    return visitBsunt;
  }

  public void setVisitBsunt(long visitBsunt) {
    this.visitBsunt = visitBsunt;
  }


  public String getBsuntUnit() {
    return bsuntUnit;
  }

  public void setBsuntUnit(String bsuntUnit) {
    this.bsuntUnit = bsuntUnit;
  }

}
