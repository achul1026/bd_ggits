package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtEvcPassAnal {
    private String    mngInstCd;        //관리 기관 코드
    private Timestamp    anlsDt;        //분석 일시
    private String    srvcId;        //서비스 아이디
    private Long    avgSrvcTime;        //평균 서비스 시간
    private Long    avgSrvcDstne;        //평균 서비스 거리
    private Long    avgArvlPrnmntTime;        //평균 도착 예정 시간
    private Long    avgArvlPrnmntDstne;        //평균 도착 예정 거리
    private String    etlDt;        //ETL 일시
    
    //#ScsEmrgVhclPathLog
    private String evno;
    private String servicename;
    
    //#ScsEmrgVhclMstInfo
    private String firename;
    
    //none col
    private Long differnceTime;
    private String goalYn;


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


  public Long getAvgSrvcTime() {
    return avgSrvcTime;
  }

  public void setAvgSrvcTime(Long avgSrvcTime) {
    this.avgSrvcTime = avgSrvcTime;
  }


  public Long getAvgSrvcDstne() {
    return avgSrvcDstne;
  }

  public void setAvgSrvcDstne(Long avgSrvcDstne) {
    this.avgSrvcDstne = avgSrvcDstne;
  }


  public Long getAvgArvlPrnmntTime() {
    return avgArvlPrnmntTime;
  }

  public void setAvgArvlPrnmntTime(Long avgArvlPrnmntTime) {
    this.avgArvlPrnmntTime = avgArvlPrnmntTime;
  }


  public Long getAvgArvlPrnmntDstne() {
    return avgArvlPrnmntDstne;
  }

  public void setAvgArvlPrnmntDstne(Long avgArvlPrnmntDstne) {
    this.avgArvlPrnmntDstne = avgArvlPrnmntDstne;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

  public String getEvno() {
	return evno;
  }

  public void setEvno(String evno) {
	this.evno = evno;
  }

  public String getServicename() {
	return servicename;
  }

  public void setServicename(String servicename) {
	this.servicename = servicename;
  }

  public Long getDiffernceTime() {
	return differnceTime;
  }

  public void setDiffernceTime(Long differnceTime) {
	this.differnceTime = differnceTime;
  }

  public String getGoalYn() {
	return goalYn;
  }

  public void setGoalYn(String goalYn) {
	this.goalYn = goalYn;
  }

  public String getFirename() {
	return firename;
  }
	
  public void setFirename(String firename) {
	this.firename = firename;
  }
}
