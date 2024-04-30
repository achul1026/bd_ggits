package com.neighbor21.ggits.common.entity;
public class GgitsLinkDsrcsection {
    private String    strseid;        //시작 rse 아이디
    private String    edrseid;        //종료 rse 아이디
    private String    linkId;        //link 아이디
    private long    configseq;        //구성 순번
    private long    configlength;        //구성 길이
    private long    roadlength;        //노선 길이


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


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public long getConfigseq() {
    return configseq;
  }

  public void setConfigseq(long configseq) {
    this.configseq = configseq;
  }


  public long getConfiglength() {
    return configlength;
  }

  public void setConfiglength(long configlength) {
    this.configlength = configlength;
  }


  public long getRoadlength() {
    return roadlength;
  }

  public void setRoadlength(long roadlength) {
    this.roadlength = roadlength;
  }

}
