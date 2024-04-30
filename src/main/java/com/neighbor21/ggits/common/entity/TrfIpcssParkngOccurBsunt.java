package com.neighbor21.ggits.common.entity;

import java.util.List;

//교통 영향평가 주차 발생 원단위
public class TrfIpcssParkngOccurBsunt {

  private String ipcssMngNo; //영향평가 관리 번호
  private String smlfactNm; //유사시설 명
  private String smlfactAddr; //유사시설 주소
  private String scale; //규모
  private Double totfrar; //연면적
  private String exmnDivNm; //조사 구분 명
  private String exmnYmd; //조사 일자
  private String exmnDocNm; //조사 문서 명
  private String usgCd; //용도 코드
  private String usgTotfrar; //용도 연면적
  private Double wkdayBsunt; //평일 원단위
  private Double wkendBsunt; //주말 원단위
  private String bsuntUnit; //원단위 단위
  private String usgNo;		//용도 순서
  private String parkngOccurNo; 
  private String dtlDt;
  private String cdNm;		// 용도 코드명
  
  private List<TrfIpcssParkngOccurBsunt> ipcssResultList;


  public String getIpcssMngNo() {
    return ipcssMngNo;
  }

  public void setIpcssMngNo(String ipcssMngNo) {
    this.ipcssMngNo = ipcssMngNo;
  }


  public String getSmlfactNm() {
    return smlfactNm;
  }

  public void setSmlfactNm(String smlfactNm) {
    this.smlfactNm = smlfactNm;
  }


  public String getSmlfactAddr() {
    return smlfactAddr;
  }

  public void setSmlfactAddr(String smlfactAddr) {
    this.smlfactAddr = smlfactAddr;
  }


  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }
  
  public String getExmnDivNm() {
    return exmnDivNm;
  }

  public void setExmnDivNm(String exmnDivNm) {
    this.exmnDivNm = exmnDivNm;
  }


  public String getExmnYmd() {
    return exmnYmd;
  }

  public void setExmnYmd(String exmnYmd) {
    this.exmnYmd = exmnYmd;
  }


  public String getExmnDocNm() {
    return exmnDocNm;
  }

  public void setExmnDocNm(String exmnDocNm) {
    this.exmnDocNm = exmnDocNm;
  }


  public String getUsgCd() {
    return usgCd;
  }

  public void setUsgCd(String usgCd) {
    this.usgCd = usgCd;
  }


  public String getUsgTotfrar() {
    return usgTotfrar;
  }

  public void setUsgTotfrar(String usgTotfrar) {
    this.usgTotfrar = usgTotfrar;
  }

  public String getBsuntUnit() {
    return bsuntUnit;
  }

  public void setBsuntUnit(String bsuntUnit) {
    this.bsuntUnit = bsuntUnit;
  }

  public double getTotfrar() {
	return totfrar;
  }

  public void setTotfrar(double totfrar) {
	this.totfrar = totfrar;
  }

  public double getWkdayBsunt() {
	return wkdayBsunt;
  }

  public void setWkdayBsunt(double wkdayBsunt) {
	this.wkdayBsunt = wkdayBsunt;
  }

  public double getWkendBsunt() {
	return wkendBsunt;
  }

  public void setWkendBsunt(double wkendBsunt) {
	this.wkendBsunt = wkendBsunt;
  }

public List<TrfIpcssParkngOccurBsunt> getIpcssResultList() {
	return ipcssResultList;
}

public void setIpcssResultList(List<TrfIpcssParkngOccurBsunt> ipcssResultList) {
	this.ipcssResultList = ipcssResultList;
}

public String getCdNm() {
	return cdNm;
}

public void setCdNm(String cdNm) {
	this.cdNm = cdNm;
}

public void setTotfrar(Double totfrar) {
	this.totfrar = totfrar;
}

public void setWkdayBsunt(Double wkdayBsunt) {
	this.wkdayBsunt = wkdayBsunt;
}

public void setWkendBsunt(Double wkendBsunt) {
	this.wkendBsunt = wkendBsunt;
}

public String getUsgNo() {
	return usgNo;
}

public void setUsgNo(String usgNo) {
	this.usgNo = usgNo;
}

public String getParkngOccurNo() {
	return parkngOccurNo;
}

public void setParkngOccurNo(String parkngOccurNo) {
	this.parkngOccurNo = parkngOccurNo;
}

public String getDtlDt() {
	return dtlDt;
}

public void setDtlDt(String dtlDt) {
	this.dtlDt = dtlDt;
}
  
  
}
