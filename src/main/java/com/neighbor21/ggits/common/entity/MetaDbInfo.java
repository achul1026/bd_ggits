package com.neighbor21.ggits.common.entity;

//db 정보
public class MetaDbInfo {

  private String dbId; //db 아이디
  private String rltinstId; //유관기관 아이디
  private String lgclDbNm; //논리 db 명
  private String phsclDbNm; //물리 db 명
  private String dbDescr; //db 설명
  private String aplcnSystemNm; //적용 시스템 명
  private String dbInfo; //db 정보
  private String osInfo; //운영체제 정보
  private String instlYmd; //설치 일자
  private long tblCnt; //테이블 수
  private long dataSize; //데이터 크기
  private String clctExclRsn; //수집 제외 사유


  public String getDbId() {
    return dbId;
  }

  public void setDbId(String dbId) {
    this.dbId = dbId;
  }


  public String getRltinstId() {
    return rltinstId;
  }

  public void setRltinstId(String rltinstId) {
    this.rltinstId = rltinstId;
  }


  public String getLgclDbNm() {
    return lgclDbNm;
  }

  public void setLgclDbNm(String lgclDbNm) {
    this.lgclDbNm = lgclDbNm;
  }


  public String getPhsclDbNm() {
    return phsclDbNm;
  }

  public void setPhsclDbNm(String phsclDbNm) {
    this.phsclDbNm = phsclDbNm;
  }


  public String getDbDescr() {
    return dbDescr;
  }

  public void setDbDescr(String dbDescr) {
    this.dbDescr = dbDescr;
  }


  public String getAplcnSystemNm() {
    return aplcnSystemNm;
  }

  public void setAplcnSystemNm(String aplcnSystemNm) {
    this.aplcnSystemNm = aplcnSystemNm;
  }


  public String getDbInfo() {
    return dbInfo;
  }

  public void setDbInfo(String dbInfo) {
    this.dbInfo = dbInfo;
  }


  public String getOsInfo() {
    return osInfo;
  }

  public void setOsInfo(String osInfo) {
    this.osInfo = osInfo;
  }


  public String getInstlYmd() {
    return instlYmd;
  }

  public void setInstlYmd(String instlYmd) {
    this.instlYmd = instlYmd;
  }


  public long getTblCnt() {
    return tblCnt;
  }

  public void setTblCnt(long tblCnt) {
    this.tblCnt = tblCnt;
  }


  public long getDataSize() {
    return dataSize;
  }

  public void setDataSize(long dataSize) {
    this.dataSize = dataSize;
  }


  public String getClctExclRsn() {
    return clctExclRsn;
  }

  public void setClctExclRsn(String clctExclRsn) {
    this.clctExclRsn = clctExclRsn;
  }

}
