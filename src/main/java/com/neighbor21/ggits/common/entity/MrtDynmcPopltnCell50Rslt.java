package com.neighbor21.ggits.common.entity;
public class MrtDynmcPopltnCell50Rslt {
    private String    baseymd;        //기준년월일
    private String    timezn;        //기준시간대
    private String    cellId;        //셀코드
    private long    fltPop;        //유동인구수
    private String    etlDt;        //etl 일시


  public String getBaseymd() {
    return baseymd;
  }

  public void setBaseymd(String baseymd) {
    this.baseymd = baseymd;
  }


  public String getTimezn() {
    return timezn;
  }

  public void setTimezn(String timezn) {
    this.timezn = timezn;
  }


  public String getCellId() {
    return cellId;
  }

  public void setCellId(String cellId) {
    this.cellId = cellId;
  }


  public long getFltPop() {
    return fltPop;
  }

  public void setFltPop(long fltPop) {
    this.fltPop = fltPop;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
