package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ExFtnminSctnTrfvlm {
    private Timestamp    clctDt;        
    private String    cnznId;        
    private String    cnznNm;        
    private String    laneTypeDivCd;        
    private String    dtlCdNm;        
    private long    trfvlm;        
    private String    etlDt;        


  public Timestamp getClctDt() {
    return clctDt;
  }

  public void setClctDt(Timestamp clctDt) {
    this.clctDt = clctDt;
  }


  public String getCnznId() {
    return cnznId;
  }

  public void setCnznId(String cnznId) {
    this.cnznId = cnznId;
  }


  public String getCnznNm() {
    return cnznNm;
  }

  public void setCnznNm(String cnznNm) {
    this.cnznNm = cnznNm;
  }


  public String getLaneTypeDivCd() {
    return laneTypeDivCd;
  }

  public void setLaneTypeDivCd(String laneTypeDivCd) {
    this.laneTypeDivCd = laneTypeDivCd;
  }


  public String getDtlCdNm() {
    return dtlCdNm;
  }

  public void setDtlCdNm(String dtlCdNm) {
    this.dtlCdNm = dtlCdNm;
  }


  public long getTrfvlm() {
    return trfvlm;
  }

  public void setTrfvlm(long trfvlm) {
    this.trfvlm = trfvlm;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
