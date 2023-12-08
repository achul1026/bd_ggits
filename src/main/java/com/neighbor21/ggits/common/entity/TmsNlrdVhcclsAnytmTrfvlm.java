package com.neighbor21.ggits.common.entity;

//국도 차종별 수시 교통량
public class TmsNlrdVhcclsAnytmTrfvlm {

  private String exmnYy; //조사 년
  private String exmnPointId; //조사 지점 아이디
  private String roadDrctCd; //도로 방향 코드
  private String exmnTime; //조사 시간
  private String vhcclsCd; //차종 코드
  private long trfvlm; //교통량


  public String getExmnYy() {
    return exmnYy;
  }

  public void setExmnYy(String exmnYy) {
    this.exmnYy = exmnYy;
  }


  public String getExmnPointId() {
    return exmnPointId;
  }

  public void setExmnPointId(String exmnPointId) {
    this.exmnPointId = exmnPointId;
  }


  public String getRoadDrctCd() {
    return roadDrctCd;
  }

  public void setRoadDrctCd(String roadDrctCd) {
    this.roadDrctCd = roadDrctCd;
  }


  public String getExmnTime() {
    return exmnTime;
  }

  public void setExmnTime(String exmnTime) {
    this.exmnTime = exmnTime;
  }


  public String getVhcclsCd() {
    return vhcclsCd;
  }

  public void setVhcclsCd(String vhcclsCd) {
    this.vhcclsCd = vhcclsCd;
  }


  public long getTrfvlm() {
    return trfvlm;
  }

  public void setTrfvlm(long trfvlm) {
    this.trfvlm = trfvlm;
  }

}
