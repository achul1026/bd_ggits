package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class TsLogFileInfoUpload {
    private String    corpId;        
    private String    workDt;        
    private String    carNo;        
    private String    originFileNm;        
    private Timestamp    centerInstDate;        
    private String    status;        
    private String    statList;        


  public String getCorpId() {
    return corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }


  public String getWorkDt() {
    return workDt;
  }

  public void setWorkDt(String workDt) {
    this.workDt = workDt;
  }


  public String getCarNo() {
    return carNo;
  }

  public void setCarNo(String carNo) {
    this.carNo = carNo;
  }


  public String getOriginFileNm() {
    return originFileNm;
  }

  public void setOriginFileNm(String originFileNm) {
    this.originFileNm = originFileNm;
  }


  public Timestamp getCenterInstDate() {
    return centerInstDate;
  }

  public void setCenterInstDate(Timestamp centerInstDate) {
    this.centerInstDate = centerInstDate;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getStatList() {
    return statList;
  }

  public void setStatList(String statList) {
    this.statList = statList;
  }

}
