package com.neighbor21.ggits.common.entity;

//경기주택공사 택지 개발 사업 정보
public class GgdtdrGhBldlndDevBizInfo {

  private String adsiNm; //행정시 명
  private String adsiCd; //행정시 코드
  private String dstrctNm; //구역 명
  private long dstrctArea; //구역 면적
  private long hshldCnt; //세대 수
  private long acceptPopltn; //수용 인구
  private String bizOprtrNm; //사업 운영자 명
  private String prgrsStts; //진행 상태


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


  public String getDstrctNm() {
    return dstrctNm;
  }

  public void setDstrctNm(String dstrctNm) {
    this.dstrctNm = dstrctNm;
  }


  public long getDstrctArea() {
    return dstrctArea;
  }

  public void setDstrctArea(long dstrctArea) {
    this.dstrctArea = dstrctArea;
  }


  public long getHshldCnt() {
    return hshldCnt;
  }

  public void setHshldCnt(long hshldCnt) {
    this.hshldCnt = hshldCnt;
  }


  public long getAcceptPopltn() {
    return acceptPopltn;
  }

  public void setAcceptPopltn(long acceptPopltn) {
    this.acceptPopltn = acceptPopltn;
  }


  public String getBizOprtrNm() {
    return bizOprtrNm;
  }

  public void setBizOprtrNm(String bizOprtrNm) {
    this.bizOprtrNm = bizOprtrNm;
  }


  public String getPrgrsStts() {
    return prgrsStts;
  }

  public void setPrgrsStts(String prgrsStts) {
    this.prgrsStts = prgrsStts;
  }

}
