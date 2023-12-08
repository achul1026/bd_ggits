package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//이력 운영 프로그램 로그인
public class LOpPgmLogn extends CommonEntity{

    private String    logId;        //로그 아이디
    private Timestamp    occurDt;        //발생 일시
    private long    oprtrId;        //운영자 아이디
    private String    grpId;        //그룹 아이디
    private String    prgrmSesnId;        //프로그램 세션 아이디
    private String    lgnIp;        //로그인 ip
    private String    etc;        //기타
    private Timestamp    logoutDt;        //로그아웃 일시
    private String    rqstrNm;        //요청자 명
    private String    logType;        //로그 유형

    // no table
    private String occurDtStr;
    private String cdNm;
    private String cdId;
    
    public String getOccurDtStr() {
    	return occurDtStr;
	}

	public void setOccurDtStr(String occurDtStr) {
		this.occurDtStr = occurDtStr;
	}

public String getLogId() {
    return logId;
  }

  public void setLogId(String logId) {
    this.logId = logId;
  }


  public Timestamp getOccurDt() {
    return occurDt;
  }

  public void setOccurDt(Timestamp occurDt) {
    this.occurDt = occurDt;
  }


  public long getOprtrId() {
    return oprtrId;
  }

  public void setOprtrId(long oprtrId) {
    this.oprtrId = oprtrId;
  }


  public String getGrpId() {
    return grpId;
  }

  public void setGrpId(String grpId) {
    this.grpId = grpId;
  }


  public String getPrgrmSesnId() {
    return prgrmSesnId;
  }

  public void setPrgrmSesnId(String prgrmSesnId) {
    this.prgrmSesnId = prgrmSesnId;
  }


  public String getLgnIp() {
    return lgnIp;
  }

  public void setLgnIp(String lgnIp) {
    this.lgnIp = lgnIp;
  }


  public String getEtc() {
    return etc;
  }

  public void setEtc(String etc) {
    this.etc = etc;
  }


  public Timestamp getLogoutDt() {
    return logoutDt;
  }

  public void setLogoutDt(Timestamp logoutDt) {
    this.logoutDt = logoutDt;
  }


  public String getRqstrNm() {
    return rqstrNm;
  }

  public void setRqstrNm(String rqstrNm) {
    this.rqstrNm = rqstrNm;
  }


  public String getLogType() {
    return logType;
  }

  public void setLogType(String logType) {
    this.logType = logType;
  }

  public String getCdNm() {
	return cdNm;
  }

  public void setCdNm(String cdNm) {
	this.cdNm = cdNm;
  }

  public String getCdId() {
	return cdId;
  }
	
  public void setCdId(String cdId) {
	this.cdId = cdId;
  }
  
}
