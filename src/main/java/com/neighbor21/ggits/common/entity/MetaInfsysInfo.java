package com.neighbor21.ggits.common.entity;

//정보시스템 정보
public class MetaInfsysInfo {

  private String rltinstId; //유관기관 아이디
  private String rltinstNm; //유관기관 명
  private String infoSystemNm; //정보 시스템 명
  private String cstdRsn; //보관 사유
  private String instlYy; //설치 년
  private String oprtDeptNm; //운영 부서 명
  private String picNm; //담당자 명


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


  public String getInfoSystemNm() {
    return infoSystemNm;
  }

  public void setInfoSystemNm(String infoSystemNm) {
    this.infoSystemNm = infoSystemNm;
  }


  public String getCstdRsn() {
    return cstdRsn;
  }

  public void setCstdRsn(String cstdRsn) {
    this.cstdRsn = cstdRsn;
  }


  public String getInstlYy() {
    return instlYy;
  }

  public void setInstlYy(String instlYy) {
    this.instlYy = instlYy;
  }


  public String getOprtDeptNm() {
    return oprtDeptNm;
  }

  public void setOprtDeptNm(String oprtDeptNm) {
    this.oprtDeptNm = oprtDeptNm;
  }


  public String getPicNm() {
    return picNm;
  }

  public void setPicNm(String picNm) {
    this.picNm = picNm;
  }

}
