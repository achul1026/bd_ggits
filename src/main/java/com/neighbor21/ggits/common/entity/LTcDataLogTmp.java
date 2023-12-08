package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class LTcDataLogTmp {
    private String    dsetId;        
    private String    jobId;        
    private String    etlDt;        
    private String    jobNm;        
    private String    rltinstId;        
    private String    etlClsf;        
    private Timestamp    clctStartDt;        
    private Timestamp    clctEndDt;        
    private String    clctTbl;        
    private String    trgtTbl;        
    private long    clctDataCnt;        
    private String    prgrsStts;        
    private String    failRsn;        


  public String getDsetId() {
    return dsetId;
  }

  public void setDsetId(String dsetId) {
    this.dsetId = dsetId;
  }


  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }


  public String getJobNm() {
    return jobNm;
  }

  public void setJobNm(String jobNm) {
    this.jobNm = jobNm;
  }


  public String getRltinstId() {
    return rltinstId;
  }

  public void setRltinstId(String rltinstId) {
    this.rltinstId = rltinstId;
  }


  public String getEtlClsf() {
    return etlClsf;
  }

  public void setEtlClsf(String etlClsf) {
    this.etlClsf = etlClsf;
  }


  public Timestamp getClctStartDt() {
    return clctStartDt;
  }

  public void setClctStartDt(Timestamp clctStartDt) {
    this.clctStartDt = clctStartDt;
  }


  public Timestamp getClctEndDt() {
    return clctEndDt;
  }

  public void setClctEndDt(Timestamp clctEndDt) {
    this.clctEndDt = clctEndDt;
  }


  public String getClctTbl() {
    return clctTbl;
  }

  public void setClctTbl(String clctTbl) {
    this.clctTbl = clctTbl;
  }


  public String getTrgtTbl() {
    return trgtTbl;
  }

  public void setTrgtTbl(String trgtTbl) {
    this.trgtTbl = trgtTbl;
  }


  public long getClctDataCnt() {
    return clctDataCnt;
  }

  public void setClctDataCnt(long clctDataCnt) {
    this.clctDataCnt = clctDataCnt;
  }


  public String getPrgrsStts() {
    return prgrsStts;
  }

  public void setPrgrsStts(String prgrsStts) {
    this.prgrsStts = prgrsStts;
  }


  public String getFailRsn() {
    return failRsn;
  }

  public void setFailRsn(String failRsn) {
    this.failRsn = failRsn;
  }

}
