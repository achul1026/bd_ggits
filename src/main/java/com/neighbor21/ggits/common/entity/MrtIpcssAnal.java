package com.neighbor21.ggits.common.entity;

import java.util.List;
import java.util.Map;

public class MrtIpcssAnal {
    private String    exmnYmd;        //조사 일자
    private String    exmnDivNm;        //조사 구분 명
    private String    smlfactNm;        //유사시설 명
    private String    usgCd;        //용도 코드
    private String    trfUseCd;        //교통 사용 코드
    private String    trfMeanVhccls;        //교통 수단 차종
    private long    totar;        //연면적
    private String    scale;        //규모
    private long    visitBsunt;        //방문 원단위
    private String    bsuntUnit;        //원단위 단위
    private long    resdngBsunt;        //상주 원단위
    private String    usgTotfrar;        //용도 연면적
    private long    shareRt;        //분담 비율

	String[] ipcssTypeArray = new String[]{"001","002","003","004","005","006","007","008","009","010","011","012","013","014","015","016","017","018","019","020","021","022","091","092","093","094","095","096","101","103","106","109","111","112",};
	
	private List<Map<String, Object>> ipcssResultList; // 영향평가 총합 데이터
	
  public String getExmnYmd() {
    return exmnYmd;
  }

  public void setExmnYmd(String exmnYmd) {
    this.exmnYmd = exmnYmd;
  }


  public String getExmnDivNm() {
    return exmnDivNm;
  }

  public void setExmnDivNm(String exmnDivNm) {
    this.exmnDivNm = exmnDivNm;
  }


  public String getSmlfactNm() {
    return smlfactNm;
  }

  public void setSmlfactNm(String smlfactNm) {
    this.smlfactNm = smlfactNm;
  }


  public String getUsgCd() {
    return usgCd;
  }

  public void setUsgCd(String usgCd) {
    this.usgCd = usgCd;
  }


  public String getTrfUseCd() {
    return trfUseCd;
  }

  public void setTrfUseCd(String trfUseCd) {
    this.trfUseCd = trfUseCd;
  }


  public String getTrfMeanVhccls() {
    return trfMeanVhccls;
  }

  public void setTrfMeanVhccls(String trfMeanVhccls) {
    this.trfMeanVhccls = trfMeanVhccls;
  }


  public long getTotar() {
    return totar;
  }

  public void setTotar(long totar) {
    this.totar = totar;
  }


  public String getScale() {
    return scale;
  }

  public void setScale(String scale) {
    this.scale = scale;
  }


  public long getVisitBsunt() {
    return visitBsunt;
  }

  public void setVisitBsunt(long visitBsunt) {
    this.visitBsunt = visitBsunt;
  }


  public String getBsuntUnit() {
    return bsuntUnit;
  }

  public void setBsuntUnit(String bsuntUnit) {
    this.bsuntUnit = bsuntUnit;
  }


  public long getResdngBsunt() {
    return resdngBsunt;
  }

  public void setResdngBsunt(long resdngBsunt) {
    this.resdngBsunt = resdngBsunt;
  }


  public String getUsgTotfrar() {
    return usgTotfrar;
  }

  public void setUsgTotfrar(String usgTotfrar) {
    this.usgTotfrar = usgTotfrar;
  }


  public long getShareRt() {
    return shareRt;
  }

  public void setShareRt(long shareRt) {
    this.shareRt = shareRt;
  }

public String[] getIpcssTypeArray() {
	return ipcssTypeArray;
}

public void setIpcssTypeArray(String[] ipcssTypeArray) {
	this.ipcssTypeArray = ipcssTypeArray;
}

public List<Map<String, Object>> getIpcssResultList() {
	return ipcssResultList;
}

public void setIpcssResultList(List<Map<String, Object>> ipcssResultList) {
	this.ipcssResultList = ipcssResultList;
}


}
