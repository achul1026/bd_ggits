package com.neighbor21.ggits.common.entity;
public class GgitsInciLink {
    private String    regSeq;        //돌발등록번호
    private long    linkNum;        //link_num
    private String    linkId;        //링크 아이디


  public String getRegSeq() {
    return regSeq;
  }

  public void setRegSeq(String regSeq) {
    this.regSeq = regSeq;
  }


  public long getLinkNum() {
    return linkNum;
  }

  public void setLinkNum(long linkNum) {
    this.linkNum = linkNum;
  }


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }

}
