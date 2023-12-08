package com.neighbor21.ggits.common.entity;

//철도 역별 od 여객 수
public class KricRlroadSttnOdPasngrCnt {

  private String aggYy; //집계 년
  private String rideRlroadNm; //승차 철도 명
  private String rideSttnId; //승차 역 아이디
  private String lndiRlroadNm; //하차 철도 명
  private String lndiSttnId; //하차 역 아이디
  private String rideSttnNm; //승차 역 명
  private String lndiSttnNm; //하차 역 명
  private long sttnDstne; //역 거리
  private long psgrCnt; //승객 수


  public String getAggYy() {
    return aggYy;
  }

  public void setAggYy(String aggYy) {
    this.aggYy = aggYy;
  }


  public String getRideRlroadNm() {
    return rideRlroadNm;
  }

  public void setRideRlroadNm(String rideRlroadNm) {
    this.rideRlroadNm = rideRlroadNm;
  }


  public String getRideSttnId() {
    return rideSttnId;
  }

  public void setRideSttnId(String rideSttnId) {
    this.rideSttnId = rideSttnId;
  }


  public String getLndiRlroadNm() {
    return lndiRlroadNm;
  }

  public void setLndiRlroadNm(String lndiRlroadNm) {
    this.lndiRlroadNm = lndiRlroadNm;
  }


  public String getLndiSttnId() {
    return lndiSttnId;
  }

  public void setLndiSttnId(String lndiSttnId) {
    this.lndiSttnId = lndiSttnId;
  }


  public String getRideSttnNm() {
    return rideSttnNm;
  }

  public void setRideSttnNm(String rideSttnNm) {
    this.rideSttnNm = rideSttnNm;
  }


  public String getLndiSttnNm() {
    return lndiSttnNm;
  }

  public void setLndiSttnNm(String lndiSttnNm) {
    this.lndiSttnNm = lndiSttnNm;
  }


  public long getSttnDstne() {
    return sttnDstne;
  }

  public void setSttnDstne(long sttnDstne) {
    this.sttnDstne = sttnDstne;
  }


  public long getPsgrCnt() {
    return psgrCnt;
  }

  public void setPsgrCnt(long psgrCnt) {
    this.psgrCnt = psgrCnt;
  }

}
