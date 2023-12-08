package com.neighbor21.ggits.common.entity;

//버스 전용 차로 정보
public class GgdtdrBusPrvuseLaneInfo {

  private String aggYyMm; //집계 년 월
  private String adsiNm; //행정시 명
  private String adsiCd; //행정시 코드
  private String sctnNm; //구간 명
  private String routeNm; //노선 명
  private String prvuseLaneDivNm; //전용 차로 구분 명
  private String oprtTimeDivNm; //운영 시간 구분 명
  private String oprtPeriodYmd; //운영 기간 일자
  private long prvuseLaneLen; //전용 차로 길이
  private String oprtTimeInfo; //운영 시간 정보


  public String getAggYyMm() {
    return aggYyMm;
  }

  public void setAggYyMm(String aggYyMm) {
    this.aggYyMm = aggYyMm;
  }


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


  public String getSctnNm() {
    return sctnNm;
  }

  public void setSctnNm(String sctnNm) {
    this.sctnNm = sctnNm;
  }


  public String getRouteNm() {
    return routeNm;
  }

  public void setRouteNm(String routeNm) {
    this.routeNm = routeNm;
  }


  public String getPrvuseLaneDivNm() {
    return prvuseLaneDivNm;
  }

  public void setPrvuseLaneDivNm(String prvuseLaneDivNm) {
    this.prvuseLaneDivNm = prvuseLaneDivNm;
  }


  public String getOprtTimeDivNm() {
    return oprtTimeDivNm;
  }

  public void setOprtTimeDivNm(String oprtTimeDivNm) {
    this.oprtTimeDivNm = oprtTimeDivNm;
  }


  public String getOprtPeriodYmd() {
    return oprtPeriodYmd;
  }

  public void setOprtPeriodYmd(String oprtPeriodYmd) {
    this.oprtPeriodYmd = oprtPeriodYmd;
  }


  public long getPrvuseLaneLen() {
    return prvuseLaneLen;
  }

  public void setPrvuseLaneLen(long prvuseLaneLen) {
    this.prvuseLaneLen = prvuseLaneLen;
  }


  public String getOprtTimeInfo() {
    return oprtTimeInfo;
  }

  public void setOprtTimeInfo(String oprtTimeInfo) {
    this.oprtTimeInfo = oprtTimeInfo;
  }

}
