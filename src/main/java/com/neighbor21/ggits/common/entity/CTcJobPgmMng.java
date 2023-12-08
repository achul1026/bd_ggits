package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class CTcJobPgmMng {
    private String    jobId;        
    private String    jobCtrlStts;        
    private Timestamp    registDt;        
    private Timestamp    updtDt;        


  public String getJobId() {
    return jobId;
  }

  public void setJobId(String jobId) {
    this.jobId = jobId;
  }


  public String getJobCtrlStts() {
    return jobCtrlStts;
  }

  public void setJobCtrlStts(String jobCtrlStts) {
    this.jobCtrlStts = jobCtrlStts;
  }


  public Timestamp getRegistDt() {
    return registDt;
  }

  public void setRegistDt(Timestamp registDt) {
    this.registDt = registDt;
  }


  public Timestamp getUpdtDt() {
    return updtDt;
  }

  public void setUpdtDt(Timestamp updtDt) {
    this.updtDt = updtDt;
  }

}
