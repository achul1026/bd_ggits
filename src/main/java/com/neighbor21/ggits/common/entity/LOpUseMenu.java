package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//이력 운영 사용 메뉴
public class LOpUseMenu extends CommonEntity {

  private Timestamp occurDt; //발생 일시
  private long oprtrId; //운영자 아이디
  private String menuId; //메뉴 아이디
  private String bttnCd; //버튼 코드
  private String lgnIp; //로그인 ip
  private String cond; //조건
  private String grpId; //그룹 아이디


  public Timestamp getOccurDt() {
    return occurDt;
  }

  public void setOccurDt(Timestamp occurDt) {
    this.occurDt = occurDt;
  }


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


  public String getBttnCd() {
    return bttnCd;
  }

  public void setBttnCd(String bttnCd) {
    this.bttnCd = bttnCd;
  }


  public String getLgnIp() {
    return lgnIp;
  }

  public void setLgnIp(String lgnIp) {
    this.lgnIp = lgnIp;
  }


  public String getCond() {
    return cond;
  }

  public void setCond(String cond) {
    this.cond = cond;
  }

  public String getGrpId() {
	return grpId;
  }

  public void setGrpId(String grpId) {
	this.grpId = grpId;
  }
  
}
