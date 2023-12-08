package com.neighbor21.ggits.common.entity;

//데이터셋 정보
public class DsetInfo {

  private String dsetId; //데이터셋 아이디
  private String rltinstId; //유관기관 아이디
  private String keywordId; //키워드 아이디
  private String srvcNm; //서비스 명
  private String clschmNm; //분류체계 명
  private String orgDataNm; //원본 데이터 명
  private String dataPvsnCol; //데이터 제공 컬럼
  private String dataType; //데이터 유형
  private String dataKeyword; //데이터 키워드
  private String ymdColNm; //일자 컬럼 명
  private String ymdTypeFormat; //일자 유형 포맷
  private String dataClctType; //데이터 수집 유형
  private String dsetFileNm; //데이터셋 파일 명
  private String srvcStts; //서비스 상태
  private String clctSchd; //수집 스케줄
  private String dsetDescr; //데이터셋 설명
  private String pvsnYn; //제공 여부


  public String getDsetId() {
    return dsetId;
  }

  public void setDsetId(String dsetId) {
    this.dsetId = dsetId;
  }


  public String getRltinstId() {
    return rltinstId;
  }

  public void setRltinstId(String rltinstId) {
    this.rltinstId = rltinstId;
  }


  public String getKeywordId() {
    return keywordId;
  }

  public void setKeywordId(String keywordId) {
    this.keywordId = keywordId;
  }


  public String getSrvcNm() {
    return srvcNm;
  }

  public void setSrvcNm(String srvcNm) {
    this.srvcNm = srvcNm;
  }


  public String getClschmNm() {
    return clschmNm;
  }

  public void setClschmNm(String clschmNm) {
    this.clschmNm = clschmNm;
  }


  public String getOrgDataNm() {
    return orgDataNm;
  }

  public void setOrgDataNm(String orgDataNm) {
    this.orgDataNm = orgDataNm;
  }


  public String getDataPvsnCol() {
    return dataPvsnCol;
  }

  public void setDataPvsnCol(String dataPvsnCol) {
    this.dataPvsnCol = dataPvsnCol;
  }


  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }


  public String getDataKeyword() {
    return dataKeyword;
  }

  public void setDataKeyword(String dataKeyword) {
    this.dataKeyword = dataKeyword;
  }


  public String getYmdColNm() {
    return ymdColNm;
  }

  public void setYmdColNm(String ymdColNm) {
    this.ymdColNm = ymdColNm;
  }


  public String getYmdTypeFormat() {
    return ymdTypeFormat;
  }

  public void setYmdTypeFormat(String ymdTypeFormat) {
    this.ymdTypeFormat = ymdTypeFormat;
  }


  public String getDataClctType() {
    return dataClctType;
  }

  public void setDataClctType(String dataClctType) {
    this.dataClctType = dataClctType;
  }


  public String getDsetFileNm() {
    return dsetFileNm;
  }

  public void setDsetFileNm(String dsetFileNm) {
    this.dsetFileNm = dsetFileNm;
  }


  public String getSrvcStts() {
    return srvcStts;
  }

  public void setSrvcStts(String srvcStts) {
    this.srvcStts = srvcStts;
  }


  public String getClctSchd() {
    return clctSchd;
  }

  public void setClctSchd(String clctSchd) {
    this.clctSchd = clctSchd;
  }


  public String getDsetDescr() {
    return dsetDescr;
  }

  public void setDsetDescr(String dsetDescr) {
    this.dsetDescr = dsetDescr;
  }


  public String getPvsnYn() {
    return pvsnYn;
  }

  public void setPvsnYn(String pvsnYn) {
    this.pvsnYn = pvsnYn;
  }

}
