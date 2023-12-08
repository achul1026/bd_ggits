package com.neighbor21.ggits.common.entity;

//행정도 점유 유형별 세대
public class KosisAddoOccpTypeHshld {

  private String statsYy; //통계 년
  private String addoNm; //행정도 명
  private long totOccpRt; //전체 점유 비율
  private long owchusOccpRt; //자가 점유 비율
  private long rentOccpRt; //임대 점유 비율
  private long grtsOccpRt; //무상 점유 비율


  public String getStatsYy() {
    return statsYy;
  }

  public void setStatsYy(String statsYy) {
    this.statsYy = statsYy;
  }


  public String getAddoNm() {
    return addoNm;
  }

  public void setAddoNm(String addoNm) {
    this.addoNm = addoNm;
  }


  public long getTotOccpRt() {
    return totOccpRt;
  }

  public void setTotOccpRt(long totOccpRt) {
    this.totOccpRt = totOccpRt;
  }


  public long getOwchusOccpRt() {
    return owchusOccpRt;
  }

  public void setOwchusOccpRt(long owchusOccpRt) {
    this.owchusOccpRt = owchusOccpRt;
  }


  public long getRentOccpRt() {
    return rentOccpRt;
  }

  public void setRentOccpRt(long rentOccpRt) {
    this.rentOccpRt = rentOccpRt;
  }


  public long getGrtsOccpRt() {
    return grtsOccpRt;
  }

  public void setGrtsOccpRt(long grtsOccpRt) {
    this.grtsOccpRt = grtsOccpRt;
  }

}
