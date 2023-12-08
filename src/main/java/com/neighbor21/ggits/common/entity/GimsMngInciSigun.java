package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GimsMngInciSigun {
    private String    mngId;        
    private String    srcOrg;        
    private String    sigun;        
    private Timestamp    upDate;        


  public String getMngId() {
    return mngId;
  }

  public void setMngId(String mngId) {
    this.mngId = mngId;
  }


  public String getSrcOrg() {
    return srcOrg;
  }

  public void setSrcOrg(String srcOrg) {
    this.srcOrg = srcOrg;
  }


  public String getSigun() {
    return sigun;
  }

  public void setSigun(String sigun) {
    this.sigun = sigun;
  }


  public Timestamp getUpDate() {
    return upDate;
  }

  public void setUpDate(Timestamp upDate) {
    this.upDate = upDate;
  }

}
