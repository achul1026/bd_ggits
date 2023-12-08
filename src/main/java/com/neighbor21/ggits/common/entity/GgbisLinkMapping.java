package com.neighbor21.ggits.common.entity;
public class GgbisLinkMapping {
    private String    linkId;        //도로 ID
    private long    linkSeq;        //링크순번
    private String    stLinkId;        //표준링크 ID
    private long    configSeq;        //구성순서
    private long    linkLength;        //링크길이
    private long    stLinkLength;        //표준링크길이
    private String    updateDate;        //업데이트 일자


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public long getLinkSeq() {
    return linkSeq;
  }

  public void setLinkSeq(long linkSeq) {
    this.linkSeq = linkSeq;
  }


  public String getStLinkId() {
    return stLinkId;
  }

  public void setStLinkId(String stLinkId) {
    this.stLinkId = stLinkId;
  }


  public long getConfigSeq() {
    return configSeq;
  }

  public void setConfigSeq(long configSeq) {
    this.configSeq = configSeq;
  }


  public long getLinkLength() {
    return linkLength;
  }

  public void setLinkLength(long linkLength) {
    this.linkLength = linkLength;
  }


  public long getStLinkLength() {
    return stLinkLength;
  }

  public void setStLinkLength(long stLinkLength) {
    this.stLinkLength = stLinkLength;
  }


  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

}
