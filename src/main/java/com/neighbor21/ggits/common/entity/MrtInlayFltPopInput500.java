package com.neighbor21.ggits.common.entity;
public class MrtInlayFltPopInput500 {
    private String    baseym;        //기준년월
    private String    cell500;        //셀코드_500
    private long    tmIdx;        //시간대인덱스
    private String    timezn;        //시간대
    private long    cellIdx;        //격자인덱스
    private String    weekday;        //요일
    private double    fltPop500;        //유동인구수
    private String    etlDt;        //ETL 일시


  public String getBaseym() {
    return baseym;
  }

  public void setBaseym(String baseym) {
    this.baseym = baseym;
  }


  public String getCell500() {
    return cell500;
  }

  public void setCell500(String cell500) {
    this.cell500 = cell500;
  }


  public long getTmIdx() {
    return tmIdx;
  }

  public void setTmIdx(long tmIdx) {
    this.tmIdx = tmIdx;
  }


  public String getTimezn() {
    return timezn;
  }

  public void setTimezn(String timezn) {
    this.timezn = timezn;
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


  public double getFltPop500() {
    return fltPop500;
  }

  public void setFltPop500(double fltPop500) {
    this.fltPop500 = fltPop500;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
