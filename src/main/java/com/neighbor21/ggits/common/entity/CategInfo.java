package com.neighbor21.ggits.common.entity;

//카테고리
public class CategInfo {

  private String categId; //카테고리 아이디
  private long clschmNo; //분류체계 번호
  private String categNm; //카테고리 명
  private String categDescr; //카테고리 설명


  public String getCategId() {
    return categId;
  }

  public void setCategId(String categId) {
    this.categId = categId;
  }


  public long getClschmNo() {
    return clschmNo;
  }

  public void setClschmNo(long clschmNo) {
    this.clschmNo = clschmNo;
  }


  public String getCategNm() {
    return categNm;
  }

  public void setCategNm(String categNm) {
    this.categNm = categNm;
  }


  public String getCategDescr() {
    return categDescr;
  }

  public void setCategDescr(String categDescr) {
    this.categDescr = categDescr;
  }

}
