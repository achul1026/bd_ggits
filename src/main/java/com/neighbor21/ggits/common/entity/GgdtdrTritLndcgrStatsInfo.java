package com.neighbor21.ggits.common.entity;

//국토 지목 통계 정보
public class GgdtdrTritLndcgrStatsInfo {

  private String crtrYyMm; //기준 년 월
  private String adsiCd; //행정시 코드
  private String adsiNm; //행정시 명
  private String adstdgDivCd; //법정동 구분 코드
  private String adstdgNm; //법정동 명
  private String lndcgrCd; //지목 코드
  private String lndcgrNm; //지목 명
  private long lotnoCnt; //지번 수
  private long landArea; //토지 면적
  private long avgLandArea; //평균 토지 면적
  private long maxLandArea; //최대 토지 면적
  private long minLandArea; //최소 토지 면적


  public String getCrtrYyMm() {
    return crtrYyMm;
  }

  public void setCrtrYyMm(String crtrYyMm) {
    this.crtrYyMm = crtrYyMm;
  }


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


  public String getAdstdgDivCd() {
    return adstdgDivCd;
  }

  public void setAdstdgDivCd(String adstdgDivCd) {
    this.adstdgDivCd = adstdgDivCd;
  }


  public String getAdstdgNm() {
    return adstdgNm;
  }

  public void setAdstdgNm(String adstdgNm) {
    this.adstdgNm = adstdgNm;
  }


  public String getLndcgrCd() {
    return lndcgrCd;
  }

  public void setLndcgrCd(String lndcgrCd) {
    this.lndcgrCd = lndcgrCd;
  }


  public String getLndcgrNm() {
    return lndcgrNm;
  }

  public void setLndcgrNm(String lndcgrNm) {
    this.lndcgrNm = lndcgrNm;
  }


  public long getLotnoCnt() {
    return lotnoCnt;
  }

  public void setLotnoCnt(long lotnoCnt) {
    this.lotnoCnt = lotnoCnt;
  }


  public long getLandArea() {
    return landArea;
  }

  public void setLandArea(long landArea) {
    this.landArea = landArea;
  }


  public long getAvgLandArea() {
    return avgLandArea;
  }

  public void setAvgLandArea(long avgLandArea) {
    this.avgLandArea = avgLandArea;
  }


  public long getMaxLandArea() {
    return maxLandArea;
  }

  public void setMaxLandArea(long maxLandArea) {
    this.maxLandArea = maxLandArea;
  }


  public long getMinLandArea() {
    return minLandArea;
  }

  public void setMinLandArea(long minLandArea) {
    this.minLandArea = minLandArea;
  }

}
