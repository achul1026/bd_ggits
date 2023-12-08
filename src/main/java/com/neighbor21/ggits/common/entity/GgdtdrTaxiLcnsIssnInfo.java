package com.neighbor21.ggits.common.entity;

//택시 면허 발급 정보
public class GgdtdrTaxiLcnsIssnInfo {

  private String adsiNm; //행정시 명
  private String adsiCd; //행정시 코드
  private long indvslLcnsCnt; //개인 면허 수
  private long indvslPrgnLcnsCnt; //개인 모범 면허 수
  private long indvslLrgszLcnsCnt; //개인 대형 면허 수
  private long crprtLcnsCnt; //법인 면허 수
  private long crprtCoCnt; //법인 회사 수


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getAdsiCd() {
    return adsiCd;
  }

  public void setAdsiCd(String adsiCd) {
    this.adsiCd = adsiCd;
  }


  public long getIndvslLcnsCnt() {
    return indvslLcnsCnt;
  }

  public void setIndvslLcnsCnt(long indvslLcnsCnt) {
    this.indvslLcnsCnt = indvslLcnsCnt;
  }


  public long getIndvslPrgnLcnsCnt() {
    return indvslPrgnLcnsCnt;
  }

  public void setIndvslPrgnLcnsCnt(long indvslPrgnLcnsCnt) {
    this.indvslPrgnLcnsCnt = indvslPrgnLcnsCnt;
  }


  public long getIndvslLrgszLcnsCnt() {
    return indvslLrgszLcnsCnt;
  }

  public void setIndvslLrgszLcnsCnt(long indvslLrgszLcnsCnt) {
    this.indvslLrgszLcnsCnt = indvslLrgszLcnsCnt;
  }


  public long getCrprtLcnsCnt() {
    return crprtLcnsCnt;
  }

  public void setCrprtLcnsCnt(long crprtLcnsCnt) {
    this.crprtLcnsCnt = crprtLcnsCnt;
  }


  public long getCrprtCoCnt() {
    return crprtCoCnt;
  }

  public void setCrprtCoCnt(long crprtCoCnt) {
    this.crprtCoCnt = crprtCoCnt;
  }

}
