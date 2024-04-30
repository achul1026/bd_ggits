package com.neighbor21.ggits.common.entity;

//분류체계
public class ClschmInfo {

  private String clschmId; //분류체계 아이디
  private String clschmNm; //분류체계 명
  private String clschmDescr; //분류체계 설명
  private String categNm;	//카테고리명
  private String keyword; //키워드

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

  public String getClschmId() {
	return clschmId;
  }

  public void setClschmId(String clschmId) {
	this.clschmId = clschmId;
  }

  public String getCategNm() {
	return categNm;
  }

  public void setCategNm(String categNm) {
	this.categNm = categNm;
  }

  public String getKeyword() {
	return keyword;
  }

  public void setKeyword(String keyword) {
	this.keyword = keyword;
  }
}
