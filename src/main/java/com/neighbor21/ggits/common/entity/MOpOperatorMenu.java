package com.neighbor21.ggits.common.entity;

//마스터 운영 운영자 메뉴
public class MOpOperatorMenu {

  private long oprtrId; //운영자 아이디
  private String menuId; //메뉴 아이디


  public long getOprtrId() {
    return oprtrId;
  }

  public void setOprtrId(long oprtrId) {
    this.oprtrId = oprtrId;
  }


  public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId;
  }

}
