package com.neighbor21.ggits.common.entity;
public class MrtInlayOffPopInput50 {
    private String    baseymdh;        //기준년월일시
    private String    cellId;        //셀코드
    private long    tmIdx;        //시간대인덱스
    private long    cellIdx;        //격자인덱스
    private String    weekday;        //요일
    private double    rainAmt;        //강수량
    private double    ta;        //기온
    private double    offPop500;        //하차인원수
    private String    etlDt;        //ETL 일시


  public String getBaseymdh() {
    return baseymdh;
  }

  public void setBaseymdh(String baseymdh) {
    this.baseymdh = baseymdh;
  }


  public String getCellId() {
    return cellId;
  }

  public void setCellId(String cellId) {
    this.cellId = cellId;
  }


  public long getTmIdx() {
    return tmIdx;
  }

  public void setTmIdx(long tmIdx) {
    this.tmIdx = tmIdx;
  }


  public long getCellIdx() {
    return cellIdx;
  }

  public void setCellIdx(long cellIdx) {
    this.cellIdx = cellIdx;
  }


  public String getWeekday() {
    return weekday;
  }

  public void setWeekday(String weekday) {
    this.weekday = weekday;
  }


  public double getRainAmt() {
    return rainAmt;
  }

  public void setRainAmt(double rainAmt) {
    this.rainAmt = rainAmt;
  }


  public double getTa() {
    return ta;
  }

  public void setTa(double ta) {
    this.ta = ta;
  }


  public double getOffPop500() {
    return offPop500;
  }

  public void setOffPop500(double offPop500) {
    this.offPop500 = offPop500;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
