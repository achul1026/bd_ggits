package com.neighbor21.ggits.common.entity;

//데이터 유관기관
public class RltinstInfo {

  private String rltinstId; //유관기관 아이디
  private String rltinstNm; //유관기관 명
  private String rltinstEngNm; //유관기관 영문 명
  private String rltinstEngAbbrNm; //유관기관 영문 약어 명
  private String picNm; //담당자 명
  private String picJbttlNm; //담당자 직책 명
  private String picEmailAddr; //담당자 이메일 주소
  private String picTel; //담당자 전화번호


  public String getRltinstId() {
    return rltinstId;
  }

  public void setRltinstId(String rltinstId) {
    this.rltinstId = rltinstId;
  }


  public String getRltinstNm() {
    return rltinstNm;
  }

  public void setRltinstNm(String rltinstNm) {
    this.rltinstNm = rltinstNm;
  }


  public String getRltinstEngNm() {
    return rltinstEngNm;
  }

  public void setRltinstEngNm(String rltinstEngNm) {
    this.rltinstEngNm = rltinstEngNm;
  }


  public String getRltinstEngAbbrNm() {
    return rltinstEngAbbrNm;
  }

  public void setRltinstEngAbbrNm(String rltinstEngAbbrNm) {
    this.rltinstEngAbbrNm = rltinstEngAbbrNm;
  }


  public String getPicNm() {
    return picNm;
  }

  public void setPicNm(String picNm) {
    this.picNm = picNm;
  }


  public String getPicJbttlNm() {
    return picJbttlNm;
  }

  public void setPicJbttlNm(String picJbttlNm) {
    this.picJbttlNm = picJbttlNm;
  }


  public String getPicEmailAddr() {
    return picEmailAddr;
  }

  public void setPicEmailAddr(String picEmailAddr) {
    this.picEmailAddr = picEmailAddr;
  }


  public String getPicTel() {
    return picTel;
  }

  public void setPicTel(String picTel) {
    this.picTel = picTel;
  }

}
