package com.neighbor21.ggits.common.entity;

//링크기반 사고 위험 구역
public class TaasLinkareaAcdntDstrct {

  private String crtrYy; //기준 년
  private String acdntDngrDstrctId; //사고 위험 구역 아이디
  private String acdntDngrDstrctNm; //사고 위험 구역 명
  private long totAcdntCnt; //전체 사고 수
  private long totDcsdCnt; //전체 사망자 수
  private long totSwpsnCnt; //전체 중상자 수
  private long totSinjpsnCnt; //전체 경상자 수
  private long totInjDclrCnt; //전체 부상 신고 수
  private String acdntAnlsTypeNm; //사고 분석 유형 명
  private long xcord; //x좌표
  private long ycord; //y좌표
  private String acdntDngrDstrctPyn; //사고 위험 구역 폴리곤


  public String getCrtrYy() {
    return crtrYy;
  }

  public void setCrtrYy(String crtrYy) {
    this.crtrYy = crtrYy;
  }


  public String getAcdntDngrDstrctId() {
    return acdntDngrDstrctId;
  }

  public void setAcdntDngrDstrctId(String acdntDngrDstrctId) {
    this.acdntDngrDstrctId = acdntDngrDstrctId;
  }


  public String getAcdntDngrDstrctNm() {
    return acdntDngrDstrctNm;
  }

  public void setAcdntDngrDstrctNm(String acdntDngrDstrctNm) {
    this.acdntDngrDstrctNm = acdntDngrDstrctNm;
  }


  public long getTotAcdntCnt() {
    return totAcdntCnt;
  }

  public void setTotAcdntCnt(long totAcdntCnt) {
    this.totAcdntCnt = totAcdntCnt;
  }


  public long getTotDcsdCnt() {
    return totDcsdCnt;
  }

  public void setTotDcsdCnt(long totDcsdCnt) {
    this.totDcsdCnt = totDcsdCnt;
  }


  public long getTotSwpsnCnt() {
    return totSwpsnCnt;
  }

  public void setTotSwpsnCnt(long totSwpsnCnt) {
    this.totSwpsnCnt = totSwpsnCnt;
  }


  public long getTotSinjpsnCnt() {
    return totSinjpsnCnt;
  }

  public void setTotSinjpsnCnt(long totSinjpsnCnt) {
    this.totSinjpsnCnt = totSinjpsnCnt;
  }


  public long getTotInjDclrCnt() {
    return totInjDclrCnt;
  }

  public void setTotInjDclrCnt(long totInjDclrCnt) {
    this.totInjDclrCnt = totInjDclrCnt;
  }


  public String getAcdntAnlsTypeNm() {
    return acdntAnlsTypeNm;
  }

  public void setAcdntAnlsTypeNm(String acdntAnlsTypeNm) {
    this.acdntAnlsTypeNm = acdntAnlsTypeNm;
  }


  public long getXcord() {
    return xcord;
  }

  public void setXcord(long xcord) {
    this.xcord = xcord;
  }


  public long getYcord() {
    return ycord;
  }

  public void setYcord(long ycord) {
    this.ycord = ycord;
  }


  public String getAcdntDngrDstrctPyn() {
    return acdntDngrDstrctPyn;
  }

  public void setAcdntDngrDstrctPyn(String acdntDngrDstrctPyn) {
    this.acdntDngrDstrctPyn = acdntDngrDstrctPyn;
  }

}
