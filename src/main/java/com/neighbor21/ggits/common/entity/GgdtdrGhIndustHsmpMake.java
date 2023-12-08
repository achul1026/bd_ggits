package com.neighbor21.ggits.common.entity;

//경기주택공사 산업 단지 조성
public class GgdtdrGhIndustHsmpMake {

  private String adsiCd; //행정시 코드
  private String adsiNm; //행정시 명
  private String bizDstrctNm; //사업 구역 명
  private String prgrsStts; //진행 상태
  private long totArea; //전체 면적
  private long industFcltsArea; //산업 시설물 면적
  private long sprtFcltsArea; //지원 시설물 면적
  private long publicFcltsArea; //공공 시설물 면적
  private long prkgrnlndArea; //공원녹지 면적
  private long lttoutRt; //분양 비율


  public String getAdsiCd() {
    return adsiCd;
  }

  public void setAdsiCd(String adsiCd) {
    this.adsiCd = adsiCd;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public String getBizDstrctNm() {
    return bizDstrctNm;
  }

  public void setBizDstrctNm(String bizDstrctNm) {
    this.bizDstrctNm = bizDstrctNm;
  }


  public String getPrgrsStts() {
    return prgrsStts;
  }

  public void setPrgrsStts(String prgrsStts) {
    this.prgrsStts = prgrsStts;
  }


  public long getTotArea() {
    return totArea;
  }

  public void setTotArea(long totArea) {
    this.totArea = totArea;
  }


  public long getIndustFcltsArea() {
    return industFcltsArea;
  }

  public void setIndustFcltsArea(long industFcltsArea) {
    this.industFcltsArea = industFcltsArea;
  }


  public long getSprtFcltsArea() {
    return sprtFcltsArea;
  }

  public void setSprtFcltsArea(long sprtFcltsArea) {
    this.sprtFcltsArea = sprtFcltsArea;
  }


  public long getPublicFcltsArea() {
    return publicFcltsArea;
  }

  public void setPublicFcltsArea(long publicFcltsArea) {
    this.publicFcltsArea = publicFcltsArea;
  }


  public long getPrkgrnlndArea() {
    return prkgrnlndArea;
  }

  public void setPrkgrnlndArea(long prkgrnlndArea) {
    this.prkgrnlndArea = prkgrnlndArea;
  }


  public long getLttoutRt() {
    return lttoutRt;
  }

  public void setLttoutRt(long lttoutRt) {
    this.lttoutRt = lttoutRt;
  }

}
