package com.neighbor21.ggits.common.entity;

//교통 영향평가 수단 분담율
public class TrfIpcssMeanShareRt {

  private String ipcssMngNo; //영향평가 관리 번호
  private String smlfactNm; //유사시설 명
  private String smlfactAddr; //유사시설 주소
  private String scale; //규모
  private long totfrar; //연면적
  private String exmnDivNm; //조사 구분 명
  private String exmnYmd; //조사 일자
  private String exmnDocNm; //조사 문서 명
  private String usgCd; //용도 코드
  private String trfUseCd; //교통 사용 코드
  private String trfMeanVhccls; //교통 수단 차종
  private long shareRt; //분담 비율


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


  public String getTrfUseCd() {
    return trfUseCd;
  }

  public void setTrfUseCd(String trfUseCd) {
    this.trfUseCd = trfUseCd;
  }


  public String getTrfMeanVhccls() {
    return trfMeanVhccls;
  }

  public void setTrfMeanVhccls(String trfMeanVhccls) {
    this.trfMeanVhccls = trfMeanVhccls;
  }


  public long getShareRt() {
    return shareRt;
  }

  public void setShareRt(long shareRt) {
    this.shareRt = shareRt;
  }

}
