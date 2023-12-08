package com.neighbor21.ggits.common.entity;
public class GgbisInetTerminal {
    private String    terId;        //터미널ID
    private String    terNm;        //터미널NM
    private String    adminNm;        //관할관청명
    private String    address;        //주소
    private String    tel;        //전화번호
    private String    homepage;        //홈페이지
    private long    kacX;        //X좌표
    private long    kacY;        //Y좌표
    private String    remark;        //비고


  public String getTerId() {
    return terId;
  }

  public void setTerId(String terId) {
    this.terId = terId;
  }


  public String getTerNm() {
    return terNm;
  }

  public void setTerNm(String terNm) {
    this.terNm = terNm;
  }


  public String getAdminNm() {
    return adminNm;
  }

  public void setAdminNm(String adminNm) {
    this.adminNm = adminNm;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getTel() {
    return tel;
  }

  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getHomepage() {
    return homepage;
  }

  public void setHomepage(String homepage) {
    this.homepage = homepage;
  }


  public long getKacX() {
    return kacX;
  }

  public void setKacX(long kacX) {
    this.kacX = kacX;
  }


  public long getKacY() {
    return kacY;
  }

  public void setKacY(long kacY) {
    this.kacY = kacY;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
