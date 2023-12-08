package com.neighbor21.ggits.common.entity;

//분류체계
public class ClschmInfo {

  private long clschmNo; //분류체계 번호
  private String clschmNm; //분류체계 명
  private String clschmDescr; //분류체계 설명


  public long getClschmNo() {
    return clschmNo;
  }

  public void setClschmNo(long clschmNo) {
    this.clschmNo = clschmNo;
  }


  public String getClschmNm() {
    return clschmNm;
  }

  public void setClschmNm(String clschmNm) {
    this.clschmNm = clschmNm;
  }


  public String getClschmDescr() {
    return clschmDescr;
  }

  public void setClschmDescr(String clschmDescr) {
    this.clschmDescr = clschmDescr;
  }

}
