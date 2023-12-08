package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//서버 로그 정보
public class LTcSrvrLogInfo extends CommonEntity{

    private String    logId;        //로그 아이디
    private Timestamp    occurDt;        //발생 일시
    private long    oprtrId;        //운영자 아이디
    private String    srvrId;        //서버 아이디
    private String    sesnId;        //세션 아이디
    private String    lgnIp;        //로그인 ip
    private String    logType;        //로그 유형

    // no table
    private String occurDtStr;
    private String cdNm;
    
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


  public String getSrvrId() {
    return srvrId;
  }

  public void setSrvrId(String srvrId) {
    this.srvrId = srvrId;
  }


  public String getSesnId() {
    return sesnId;
  }

  public void setSesnId(String sesnId) {
    this.sesnId = sesnId;
  }


  public String getLgnIp() {
    return lgnIp;
  }

  public void setLgnIp(String lgnIp) {
    this.lgnIp = lgnIp;
  }


  public String getLogType() {
    return logType;
  }

  public void setLogType(String logType) {
    this.logType = logType;
  }

public String getOccurDtStr() {
	return occurDtStr;
}

public void setOccurDtStr(String occurDtStr) {
	this.occurDtStr = occurDtStr;
}

public String getCdNm() {
	return cdNm;
}

public void setCdNm(String cdNm) {
	this.cdNm = cdNm;
}

}
