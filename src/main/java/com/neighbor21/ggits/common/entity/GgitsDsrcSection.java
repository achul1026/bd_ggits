package com.neighbor21.ggits.common.entity;
public class GgitsDsrcSection {
    private String    strseid;        //시작 rse 아이디
    private String    edrseid;        //종료 rse 아이디
    private long    dsrcseclength;        //구간 길이
    private String    alterconfigflag;        //구성 변경 플래그
    private String    missrseid;        //미스 rse 아이디


  public String getStrseid() {
    return strseid;
  }

  public void setStrseid(String strseid) {
    this.strseid = strseid;
  }


  public String getEdrseid() {
    return edrseid;
  }

  public void setEdrseid(String edrseid) {
    this.edrseid = edrseid;
  }


  public long getDsrcseclength() {
    return dsrcseclength;
  }

  public void setDsrcseclength(long dsrcseclength) {
    this.dsrcseclength = dsrcseclength;
  }


  public String getAlterconfigflag() {
    return alterconfigflag;
  }

  public void setAlterconfigflag(String alterconfigflag) {
    this.alterconfigflag = alterconfigflag;
  }


  public String getMissrseid() {
    return missrseid;
  }

  public void setMissrseid(String missrseid) {
    this.missrseid = missrseid;
  }

}
