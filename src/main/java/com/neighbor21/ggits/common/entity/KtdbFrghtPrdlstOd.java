package com.neighbor21.ggits.common.entity;

//화물 품목별 od
public class KtdbFrghtPrdlstOd {

  private String crtrYy; //기준 년
  private String prdlstDivCd; //품목 구분 코드
  private String dptreZoneCd; //출발 zone 코드
  private String arvlZoneCd; //도착 zone 코드
  private long frghtGtqy; //화물 물동량


  public String getCrtrYy() {
    return crtrYy;
  }

  public void setCrtrYy(String crtrYy) {
    this.crtrYy = crtrYy;
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


  public long getFrghtGtqy() {
    return frghtGtqy;
  }

  public void setFrghtGtqy(long frghtGtqy) {
    this.frghtGtqy = frghtGtqy;
  }

}
