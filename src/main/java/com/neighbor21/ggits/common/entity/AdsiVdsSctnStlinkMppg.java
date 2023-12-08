package com.neighbor21.ggits.common.entity;

//vds 구간 표준링크 매핑
public class AdsiVdsSctnStlinkMppg extends GyCommInfoLink {

  private String mngInstCd; //관리 기관 코드
  private String vdsSctnId; //vds 구간 아이디
  private long sqno; //순번
  private String stdLinkId; //표준 링크 아이디


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public String getVdsSctnId() {
    return vdsSctnId;
  }

  public void setVdsSctnId(String vdsSctnId) {
    this.vdsSctnId = vdsSctnId;
  }


  public long getSqno() {
    return sqno;
  }

  public void setSqno(long sqno) {
    this.sqno = sqno;
  }


  public String getStdLinkId() {
    return stdLinkId;
  }

  public void setStdLinkId(String stdLinkId) {
    this.stdLinkId = stdLinkId;
  }

}
