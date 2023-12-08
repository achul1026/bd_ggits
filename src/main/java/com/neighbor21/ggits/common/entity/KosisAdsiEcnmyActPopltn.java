package com.neighbor21.ggits.common.entity;

//행정시 경제 활동 인구
public class KosisAdsiEcnmyActPopltn {

  private String statsYyMm; //통계 년 월
  private String adsiNm; //행정시 명
  private long ov15YrPopltnCnt; //15세이상 인구 수
  private long ecnmyActPopltnCnt; //경제 활동 인구 수
  private long empprnCnt; //취업자 수
  private long unemprnCnt; //실업자 수
  private long noecnmyActPopltnCnt; //비경제 활동 인구 수
  private long ecnmyActPartcptRt; //경제 활동 참가 비율
  private long unemplRt; //실업 비율
  private long emplynRt; //고용 비율
  private long bw15An64YrEmplynRt; //15-64세 고용 비율


  public String getStatsYyMm() {
    return statsYyMm;
  }

  public void setStatsYyMm(String statsYyMm) {
    this.statsYyMm = statsYyMm;
  }


  public String getAdsiNm() {
    return adsiNm;
  }

  public void setAdsiNm(String adsiNm) {
    this.adsiNm = adsiNm;
  }


  public long getOv15YrPopltnCnt() {
    return ov15YrPopltnCnt;
  }

  public void setOv15YrPopltnCnt(long ov15YrPopltnCnt) {
    this.ov15YrPopltnCnt = ov15YrPopltnCnt;
  }


  public long getEcnmyActPopltnCnt() {
    return ecnmyActPopltnCnt;
  }

  public void setEcnmyActPopltnCnt(long ecnmyActPopltnCnt) {
    this.ecnmyActPopltnCnt = ecnmyActPopltnCnt;
  }


  public long getEmpprnCnt() {
    return empprnCnt;
  }

  public void setEmpprnCnt(long empprnCnt) {
    this.empprnCnt = empprnCnt;
  }


  public long getUnemprnCnt() {
    return unemprnCnt;
  }

  public void setUnemprnCnt(long unemprnCnt) {
    this.unemprnCnt = unemprnCnt;
  }


  public long getNoecnmyActPopltnCnt() {
    return noecnmyActPopltnCnt;
  }

  public void setNoecnmyActPopltnCnt(long noecnmyActPopltnCnt) {
    this.noecnmyActPopltnCnt = noecnmyActPopltnCnt;
  }


  public long getEcnmyActPartcptRt() {
    return ecnmyActPartcptRt;
  }

  public void setEcnmyActPartcptRt(long ecnmyActPartcptRt) {
    this.ecnmyActPartcptRt = ecnmyActPartcptRt;
  }


  public long getUnemplRt() {
    return unemplRt;
  }

  public void setUnemplRt(long unemplRt) {
    this.unemplRt = unemplRt;
  }


  public long getEmplynRt() {
    return emplynRt;
  }

  public void setEmplynRt(long emplynRt) {
    this.emplynRt = emplynRt;
  }


  public long getBw15An64YrEmplynRt() {
    return bw15An64YrEmplynRt;
  }

  public void setBw15An64YrEmplynRt(long bw15An64YrEmplynRt) {
    this.bw15An64YrEmplynRt = bw15An64YrEmplynRt;
  }

}
