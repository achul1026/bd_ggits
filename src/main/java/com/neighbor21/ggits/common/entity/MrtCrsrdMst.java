package com.neighbor21.ggits.common.entity;
public class MrtCrsrdMst {
    private String    mngInstCd;        //관리 기관 코드
    private String    crsrdId;        //교차로 아이디
    private String    crsrdNm;        //교차로 명
    private String    nodeId;        //노드 아이디
    private long    lonCrdn;        //경도 좌표
    private long    latCrdn;        //위도 좌표
    private long    maxPdstCnt;        //최대 보행자 수
    private String    etlDt;        //ETL 일시


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public String getCrsrdId() {
    return crsrdId;
  }

  public void setCrsrdId(String crsrdId) {
    this.crsrdId = crsrdId;
  }


  public String getCrsrdNm() {
    return crsrdNm;
  }

  public void setCrsrdNm(String crsrdNm) {
    this.crsrdNm = crsrdNm;
  }


  public String getNodeId() {
    return nodeId;
  }

  public void setNodeId(String nodeId) {
    this.nodeId = nodeId;
  }


  public long getLonCrdn() {
    return lonCrdn;
  }

  public void setLonCrdn(long lonCrdn) {
    this.lonCrdn = lonCrdn;
  }


  public long getLatCrdn() {
    return latCrdn;
  }

  public void setLatCrdn(long latCrdn) {
    this.latCrdn = latCrdn;
  }


  public long getMaxPdstCnt() {
    return maxPdstCnt;
  }

  public void setMaxPdstCnt(long maxPdstCnt) {
    this.maxPdstCnt = maxPdstCnt;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
