package com.neighbor21.ggits.common.entity;
public class MrtCndcyPathLinkInfo {
    private long    excnRsltId;        //실행 결과 아이디
    private long    cndcyPathId;        //후보 경로 아이디
    private long    linkSqno;        //링크 순번
    private String    stdLinkId;        //표준 링크 아이디


  public long getExcnRsltId() {
    return excnRsltId;
  }

  public void setExcnRsltId(long excnRsltId) {
    this.excnRsltId = excnRsltId;
  }


  public long getCndcyPathId() {
    return cndcyPathId;
  }

  public void setCndcyPathId(long cndcyPathId) {
    this.cndcyPathId = cndcyPathId;
  }


  public long getLinkSqno() {
    return linkSqno;
  }

  public void setLinkSqno(long linkSqno) {
    this.linkSqno = linkSqno;
  }


  public String getStdLinkId() {
    return stdLinkId;
  }

  public void setStdLinkId(String stdLinkId) {
    this.stdLinkId = stdLinkId;
  }

}
