package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GimsInciDel {
    private String    inciId;        //돌발 정보 ID
    private Timestamp    registDtime;        //등록 시간


  public String getInciId() {
    return inciId;
  }

  public void setInciId(String inciId) {
    this.inciId = inciId;
  }


  public Timestamp getRegistDtime() {
    return registDtime;
  }

  public void setRegistDtime(Timestamp registDtime) {
    this.registDtime = registDtime;
  }

}
