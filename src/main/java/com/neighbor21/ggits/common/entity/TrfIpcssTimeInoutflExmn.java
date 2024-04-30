package com.neighbor21.ggits.common.entity;

import java.util.List;

public class TrfIpcssTimeInoutflExmn {
    private String    ipcssMngNo;        		//영향평가 관리 번호
    private String    exmnYmd;        			//조사 일자
    private String    dywkDiv;        			//요일 구분
    private String    usgNo;        			//용도 번호
    private String    usgNm;        			//용도 명
    private String    timeSctnNm;        		//시간 구간 명
    private String    actPopltnExmnType;        //활동 인구 조사 유형
    private double    resdngInfl;        		//상주/상근 유입
    private double    resdngOutfl;        		//상주/상근 유출 
    private double    visitInfl;        		//방문/이용 유입
    private double    visitOutfl;        		//방문/이용  유출
    private double    totInfl;        			//전체 유입
    private double    totOutfl;        			//전체 유출
    private String etlDt;

    private List<String> usgNoList;				// 용도 번호 리스트
    private List<String> usgNmList;				// 용도 명 리스트
    
    String trfvlmStatisticsListJson;
    
  public String getIpcssMngNo() {
    return ipcssMngNo;
  }

  public void setIpcssMngNo(String ipcssMngNo) {
    this.ipcssMngNo = ipcssMngNo;
  }


  public String getExmnYmd() {
    return exmnYmd;
  }

  public void setExmnYmd(String exmnYmd) {
    this.exmnYmd = exmnYmd;
  }


  public String getDywkDiv() {
    return dywkDiv;
  }

  public void setDywkDiv(String dywkDiv) {
    this.dywkDiv = dywkDiv;
  }


  public String getUsgNo() {
    return usgNo;
  }

  public void setUsgNo(String usgNo) {
    this.usgNo = usgNo;
  }


  public String getUsgNm() {
    return usgNm;
  }

  public void setUsgNm(String usgNm) {
    this.usgNm = usgNm;
  }


  public String getTimeSctnNm() {
    return timeSctnNm;
  }

  public void setTimeSctnNm(String timeSctnNm) {
    this.timeSctnNm = timeSctnNm;
  }


  public String getActPopltnExmnType() {
    return actPopltnExmnType;
  }

  public void setActPopltnExmnType(String actPopltnExmnType) {
    this.actPopltnExmnType = actPopltnExmnType;
  }

  public double getResdngInfl() {
	return resdngInfl;
  }

  public void setResdngInfl(double resdngInfl) {
	this.resdngInfl = resdngInfl;
  }

  public double getResdngOutfl() {
	return resdngOutfl;
  }

  public void setResdngOutfl(double resdngOutfl) {
	this.resdngOutfl = resdngOutfl;
  }

  public double getVisitInfl() {
	return visitInfl;
  }

  public void setVisitInfl(double visitInfl) {
	this.visitInfl = visitInfl;
  }

  public double getVisitOutfl() {
	return visitOutfl;
  }

  public void setVisitOutfl(double visitOutfl) {
	this.visitOutfl = visitOutfl;
  }

  public double getTotInfl() {
	return totInfl;
  }

  public void setTotInfl(double totInfl) {
	this.totInfl = totInfl;
  }

  public double getTotOutfl() {
	return totOutfl;
  }

  public void setTotOutfl(double totOutfl) {
	this.totOutfl = totOutfl;
  }

  public List<String> getUsgNoList() {
	return usgNoList;
  }

  public void setUsgNoList(List<String> usgNoList) {
	this.usgNoList = usgNoList;
  }

  public List<String> getUsgNmList() {
	return usgNmList;
  }

  public void setUsgNmList(List<String> usgNmList) {
	this.usgNmList = usgNmList;
  }

  public String getTrfvlmStatisticsListJson() {
	return trfvlmStatisticsListJson;
  }

  public void setTrfvlmStatisticsListJson(String trfvlmStatisticsListJson) {
	this.trfvlmStatisticsListJson = trfvlmStatisticsListJson;
  }

public String getEtlDt() {
	return etlDt;
}

public void setEtlDt(String etlDt) {
	this.etlDt = etlDt;
}
  
}
