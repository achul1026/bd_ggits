package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MOpCodeTmpDong {
    private String    grpCdId;        
    private String    cdId;        
    private String    cdNm;        
    private String    descr;        
    private String    cdCond1Val;        
    private String    cdCond2Val;        
    private String    useYn;        
    private long    sortNo;        
    private String    crtusrId;        
    private Timestamp    crtDt;        
    private String    uptusrId;        
    private Timestamp    updtDt;        


  public String getGrpCdId() {
    return grpCdId;
  }
	
  public void setGrpCdId(String grpCdId) {
    this.grpCdId = grpCdId;
  }


  public String getCdId() {
    return cdId;
  }

  public void setCdId(String cdId) {
    this.cdId = cdId;
  }


  public String getCdNm() {
    return cdNm;
  }

  public void setCdNm(String cdNm) {
    this.cdNm = cdNm;
  }


  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }


  public String getCdCond1Val() {
    return cdCond1Val;
  }

  public void setCdCond1Val(String cdCond1Val) {
    this.cdCond1Val = cdCond1Val;
  }


  public String getCdCond2Val() {
    return cdCond2Val;
  }

  public void setCdCond2Val(String cdCond2Val) {
    this.cdCond2Val = cdCond2Val;
  }


  public String getUseYn() {
    return useYn;
  }

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }


  public long getSortNo() {
    return sortNo;
  }

  public void setSortNo(long sortNo) {
    this.sortNo = sortNo;
  }


  public String getCrtusrId() {
    return crtusrId;
  }

  public void setCrtusrId(String crtusrId) {
    this.crtusrId = crtusrId;
  }


  public Timestamp getCrtDt() {
    return crtDt;
  }

  public void setCrtDt(Timestamp crtDt) {
    this.crtDt = crtDt;
  }


  public String getUptusrId() {
    return uptusrId;
  }

  public void setUptusrId(String uptusrId) {
    this.uptusrId = uptusrId;
  }


  public Timestamp getUpdtDt() {
    return updtDt;
  }

  public void setUpdtDt(Timestamp updtDt) {
    this.updtDt = updtDt;
  }

}
