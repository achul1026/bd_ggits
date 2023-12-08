package com.neighbor21.ggits.common.entity;

//마스터 운영 권한 메뉴
public class MOpAthrMenu {

  private long authId; //권한 아이디
  private String menuId; //메뉴 아이디
  private String bttnCd; //버튼 코드


  public long getAuthId() {
    return authId;
  }

  public void setAuthId(long authId) {
    this.authId = authId;
  }


  public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId;
  }


  public String getBttnCd() {
    return bttnCd;
  }

  public void setBttnCd(String bttnCd) {
    this.bttnCd = bttnCd;
  }

}
