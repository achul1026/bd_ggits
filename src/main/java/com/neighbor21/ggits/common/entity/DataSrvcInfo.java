package com.neighbor21.ggits.common.entity;

//데이터 서비스 정보
public class DataSrvcInfo {

  private String dataSrvcId; //데이터 서비스 아이디
  private String dataSrvcNm; //데이터 서비스 명
  private String rltinstNm; //유관기관 명
  private String clschmNm; //분류체계 명
  private String dataSrvcType; //데이터 서비스 유형
  private String dataSrvcKeyword; //데이터 서비스 키워드
  private String endpntUrl; //엔드포인트 url
  private String endpntDescr; //엔드포인트 설명


  public String getDataSrvcId() {
    return dataSrvcId;
  }

  public void setDataSrvcId(String dataSrvcId) {
    this.dataSrvcId = dataSrvcId;
  }


  public String getDataSrvcNm() {
    return dataSrvcNm;
  }

  public void setDataSrvcNm(String dataSrvcNm) {
    this.dataSrvcNm = dataSrvcNm;
  }


  public String getRltinstNm() {
    return rltinstNm;
  }

  public void setRltinstNm(String rltinstNm) {
    this.rltinstNm = rltinstNm;
  }


  public String getClschmNm() {
    return clschmNm;
  }

  public void setClschmNm(String clschmNm) {
    this.clschmNm = clschmNm;
  }


  public String getDataSrvcType() {
    return dataSrvcType;
  }

  public void setDataSrvcType(String dataSrvcType) {
    this.dataSrvcType = dataSrvcType;
  }


  public String getDataSrvcKeyword() {
    return dataSrvcKeyword;
  }

  public void setDataSrvcKeyword(String dataSrvcKeyword) {
    this.dataSrvcKeyword = dataSrvcKeyword;
  }


  public String getEndpntUrl() {
    return endpntUrl;
  }

  public void setEndpntUrl(String endpntUrl) {
    this.endpntUrl = endpntUrl;
  }


  public String getEndpntDescr() {
    return endpntDescr;
  }

  public void setEndpntDescr(String endpntDescr) {
    this.endpntDescr = endpntDescr;
  }

}
