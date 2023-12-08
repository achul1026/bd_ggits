package com.neighbor21.ggits.common.entity;

//화물 톤급별 od
public class KtdbFrghtTonlvlOd {

  private String crtrYy; //기준 년
  private String tonlvlCd; //톤급 코드
  private String prdlstDivCd; //품목 구분 코드
  private String dptreZoneCd; //출발 zone 코드
  private String arvlZoneCd; //도착 zone 코드
  private long frghtOdTrfvlm; //화물 od 교통량


  public String getCrtrYy() {
    return crtrYy;
  }

  public void setCrtrYy(String crtrYy) {
    this.crtrYy = crtrYy;
  }


  public String getTonlvlCd() {
    return tonlvlCd;
  }

  public void setTonlvlCd(String tonlvlCd) {
    this.tonlvlCd = tonlvlCd;
  }


  public String getPrdlstDivCd() {
    return prdlstDivCd;
  }

  public void setPrdlstDivCd(String prdlstDivCd) {
    this.prdlstDivCd = prdlstDivCd;
  }


  public String getDptreZoneCd() {
    return dptreZoneCd;
  }

  public void setDptreZoneCd(String dptreZoneCd) {
    this.dptreZoneCd = dptreZoneCd;
  }


  public String getArvlZoneCd() {
    return arvlZoneCd;
  }

  public void setArvlZoneCd(String arvlZoneCd) {
    this.arvlZoneCd = arvlZoneCd;
  }


  public long getFrghtOdTrfvlm() {
    return frghtOdTrfvlm;
  }

  public void setFrghtOdTrfvlm(long frghtOdTrfvlm) {
    this.frghtOdTrfvlm = frghtOdTrfvlm;
  }

}
