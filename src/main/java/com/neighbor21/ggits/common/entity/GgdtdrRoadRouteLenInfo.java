package com.neighbor21.ggits.common.entity;

//도로 노선 길이 정보
public class GgdtdrRoadRouteLenInfo {

  private String roadTypeNm; //도로 유형 명
  private String divNm; //구분 명
  private String aggYmd; //집계 일자
  private long routeCnt; //노선 수
  private long routeLen; //노선 길이
  private long paveLen; //포장도 길이
  private long paveRt; //포장도 비율
  private long unpaveLen; //미포장도 길이


  public String getRoadTypeNm() {
    return roadTypeNm;
  }

  public void setRoadTypeNm(String roadTypeNm) {
    this.roadTypeNm = roadTypeNm;
  }


  public String getDivNm() {
    return divNm;
  }

  public void setDivNm(String divNm) {
    this.divNm = divNm;
  }


  public String getAggYmd() {
    return aggYmd;
  }

  public void setAggYmd(String aggYmd) {
    this.aggYmd = aggYmd;
  }


  public long getRouteCnt() {
    return routeCnt;
  }

  public void setRouteCnt(long routeCnt) {
    this.routeCnt = routeCnt;
  }


  public long getRouteLen() {
    return routeLen;
  }

  public void setRouteLen(long routeLen) {
    this.routeLen = routeLen;
  }


  public long getPaveLen() {
    return paveLen;
  }

  public void setPaveLen(long paveLen) {
    this.paveLen = paveLen;
  }


  public long getPaveRt() {
    return paveRt;
  }

  public void setPaveRt(long paveRt) {
    this.paveRt = paveRt;
  }


  public long getUnpaveLen() {
    return unpaveLen;
  }

  public void setUnpaveLen(long unpaveLen) {
    this.unpaveLen = unpaveLen;
  }

}
