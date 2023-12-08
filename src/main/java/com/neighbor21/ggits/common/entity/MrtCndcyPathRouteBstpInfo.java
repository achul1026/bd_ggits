package com.neighbor21.ggits.common.entity;
public class MrtCndcyPathRouteBstpInfo {
    private long    excnRsltId;        //실행 결과 아이디
    private long    cndcyPathId;        //후보 경로 아이디
    private long    bstpSqno;        //버스정류장 순번
    private String    bstpId;        //버스정류장 아이디
    private String    bstpNm;        //버스정류장 명


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


  public long getBstpSqno() {
    return bstpSqno;
  }

  public void setBstpSqno(long bstpSqno) {
    this.bstpSqno = bstpSqno;
  }


  public String getBstpId() {
    return bstpId;
  }

  public void setBstpId(String bstpId) {
    this.bstpId = bstpId;
  }


  public String getBstpNm() {
    return bstpNm;
  }

  public void setBstpNm(String bstpNm) {
    this.bstpNm = bstpNm;
  }

}
