package com.neighbor21.ggits.common.entity;
public class MrtLndiPopltnCell500Anls {
    private String    baseymd;        //기준년월일
    private String    timezn;        //기준시간대
    private String    cell500;        //셀코드 500
    private long    offPop;        //하차인원수
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


  public String getCell500() {
    return cell500;
  }

  public void setCell500(String cell500) {
    this.cell500 = cell500;
  }


  public long getOffPop() {
    return offPop;
  }

  public void setOffPop(long offPop) {
    this.offPop = offPop;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
