package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtEvcPassAnal {
    private String    mngInstCd;        //관리 기관 코드
    private Timestamp    anlsDt;        //분석 일시
    private String    srvcId;        //서비스 아이디
    private long    avgSrvcTime;        //평균 서비스 시간
    private long    avgSrvcDstne;        //평균 서비스 거리
    private long    avgArvlPrnmntTime;        //평균 도착 예정 시간
    private long    avgArvlPrnmntDstne;        //평균 도착 예정 거리
    private String    etlDt;        //ETL 일시


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public Timestamp getAnlsDt() {
    return anlsDt;
  }

  public void setAnlsDt(Timestamp anlsDt) {
    this.anlsDt = anlsDt;
  }


  public String getSrvcId() {
    return srvcId;
  }

  public void setSrvcId(String srvcId) {
    this.srvcId = srvcId;
  }


  public long getAvgSrvcTime() {
    return avgSrvcTime;
  }

  public void setAvgSrvcTime(long avgSrvcTime) {
    this.avgSrvcTime = avgSrvcTime;
  }


  public long getAvgSrvcDstne() {
    return avgSrvcDstne;
  }

  public void setAvgSrvcDstne(long avgSrvcDstne) {
    this.avgSrvcDstne = avgSrvcDstne;
  }


  public long getAvgArvlPrnmntTime() {
    return avgArvlPrnmntTime;
  }

  public void setAvgArvlPrnmntTime(long avgArvlPrnmntTime) {
    this.avgArvlPrnmntTime = avgArvlPrnmntTime;
  }


  public long getAvgArvlPrnmntDstne() {
    return avgArvlPrnmntDstne;
  }

  public void setAvgArvlPrnmntDstne(long avgArvlPrnmntDstne) {
    this.avgArvlPrnmntDstne = avgArvlPrnmntDstne;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
