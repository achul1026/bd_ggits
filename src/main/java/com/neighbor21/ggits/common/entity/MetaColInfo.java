package com.neighbor21.ggits.common.entity;

//컬럼 정보
public class MetaColInfo {

  private String tblId; //테이블 아이디
  private long colSqno; //컬럼 순번
  private String dsetId; //db 아이디
  private String rltinstId; //유관기관 아이디
  private String colEngNm; //컬럼 영문 명
  private String colKoreanNm; //컬럼 한글 명
  private String colDescr; //컬럼 설명
  private String dataType; //데이터 유형
  private long dataLen; //데이터 길이
  private String dataFormat; //데이터 포맷
  private String nnYn; //notnull 여부
  private String pkInfo; //pk 정보
  private String fkInfo; //fk 정보
  private String ctrlCond; //제어 조건
  private String prvcYn; //개인정보 여부
  private String encptYn; //암호화 여부
  private String prvtYn; //비공개 여부


  public String getTblId() {
    return tblId;
  }

  public void setTblId(String tblId) {
    this.tblId = tblId;
  }


  public long getColSqno() {
    return colSqno;
  }

  public void setColSqno(long colSqno) {
    this.colSqno = colSqno;
  }


//  public String getDbId() {
//    return dbId;
//  }
//
//  public void setDbId(String dbId) {
//    this.dbId = dbId;
//  }

  
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


  public String getColEngNm() {
    return colEngNm;
  }

  public void setColEngNm(String colEngNm) {
    this.colEngNm = colEngNm;
  }


  public String getColKoreanNm() {
    return colKoreanNm;
  }

  public void setColKoreanNm(String colKoreanNm) {
    this.colKoreanNm = colKoreanNm;
  }


  public String getColDescr() {
    return colDescr;
  }

  public void setColDescr(String colDescr) {
    this.colDescr = colDescr;
  }


  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }


  public long getDataLen() {
    return dataLen;
  }

  public void setDataLen(long dataLen) {
    this.dataLen = dataLen;
  }


  public String getDataFormat() {
    return dataFormat;
  }

  public void setDataFormat(String dataFormat) {
    this.dataFormat = dataFormat;
  }


  public String getNnYn() {
    return nnYn;
  }

  public void setNnYn(String nnYn) {
    this.nnYn = nnYn;
  }


  public String getPkInfo() {
    return pkInfo;
  }

  public void setPkInfo(String pkInfo) {
    this.pkInfo = pkInfo;
  }


  public String getFkInfo() {
    return fkInfo;
  }

  public void setFkInfo(String fkInfo) {
    this.fkInfo = fkInfo;
  }


  public String getCtrlCond() {
    return ctrlCond;
  }

  public void setCtrlCond(String ctrlCond) {
    this.ctrlCond = ctrlCond;
  }


  public String getPrvcYn() {
    return prvcYn;
  }

  public void setPrvcYn(String prvcYn) {
    this.prvcYn = prvcYn;
  }


  public String getEncptYn() {
    return encptYn;
  }

  public void setEncptYn(String encptYn) {
    this.encptYn = encptYn;
  }


  public String getPrvtYn() {
    return prvtYn;
  }

  public void setPrvtYn(String prvtYn) {
    this.prvtYn = prvtYn;
  }

}
