package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSmcSpotAbn extends CommonEntity {
    private String    mngInstCd;        //관리 기관 코드
    private Timestamp    anlsDt;        //분석 일시
    private String    crsrdId;        //교차로 아이디
    private String    acsRoadId;        //접근 도로 아이디
    private String    drctCd;        //방향 코드
    private String    dywkCd;        //요일 코드
    private long    laneNo;        //차로 번호
    private String    vhclDiv;        //차량 구분
    private long    vhclTrfvlm;        //차량 교통량
    private long    avgVhclSpeed;        //평균 차량 속도
    private String    etlDt;        //etl 일시
    
    private String acsRoadNm;	// 도로명

    private String orderByOption = "trfvlm"; //정렬옵션 ex)trfvlm 교통량 , speed 속도
    
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


  public String getCrsrdId() {
    return crsrdId;
  }

  public void setCrsrdId(String crsrdId) {
    this.crsrdId = crsrdId;
  }


  public String getAcsRoadId() {
    return acsRoadId;
  }

  public void setAcsRoadId(String acsRoadId) {
    this.acsRoadId = acsRoadId;
  }


  public String getDrctCd() {
    return drctCd;
  }

  public void setDrctCd(String drctCd) {
    this.drctCd = drctCd;
  }


  public String getDywkCd() {
    return dywkCd;
  }

  public void setDywkCd(String dywkCd) {
    this.dywkCd = dywkCd;
  }


  public long getLaneNo() {
    return laneNo;
  }

  public void setLaneNo(long laneNo) {
    this.laneNo = laneNo;
  }


  public String getVhclDiv() {
    return vhclDiv;
  }

  public void setVhclDiv(String vhclDiv) {
    this.vhclDiv = vhclDiv;
  }


  public long getVhclTrfvlm() {
    return vhclTrfvlm;
  }

  public void setVhclTrfvlm(long vhclTrfvlm) {
    this.vhclTrfvlm = vhclTrfvlm;
  }


  public long getAvgVhclSpeed() {
    return avgVhclSpeed;
  }

  public void setAvgVhclSpeed(long avgVhclSpeed) {
    this.avgVhclSpeed = avgVhclSpeed;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

  public String getAcsRoadNm() {
	return acsRoadNm;
  }
 
  public void setAcsRoadNm(String acsRoadNm) {
	this.acsRoadNm = acsRoadNm;
  }

  public String getOrderByOption() {
	return orderByOption;
  }
	
  public void setOrderByOption(String orderByOption) {
	this.orderByOption = orderByOption;
  }

}
