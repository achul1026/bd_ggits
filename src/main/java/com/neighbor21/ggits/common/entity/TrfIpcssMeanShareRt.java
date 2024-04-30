package com.neighbor21.ggits.common.entity;

import java.util.List;

//교통 영향평가 수단 분담율
public class TrfIpcssMeanShareRt {

  private String ipcssMngNo; //영향평가 관리 번호
  private String smlfactNm; //유사시설 명
  private String smlfactAddr; //유사시설 주소
  private String scale; //규모
  private double totfrar; //연면적
  private String exmnDivNm; //조사 구분 명
  private String exmnYmd; //조사 일자
  private String exmnDocNm; //조사 문서 명
  private String usgCd; //용도 코드
  private String trfUseCd; //교통 사용 코드
  private String trfMeanVhccls; //교통 수단 차종
  private double shareRt; //분담 비율
  private String etlDt;
  private String usgNo;		// 용도 순서
  private String meanShareNo;
  
  private int tremNum;	// 셀의 해당하는 용도 코드 셀 인덱스
  private List<TrfIpcssMeanShareRt> ipcssResultList;
  private String cdNm;
  private String shareRtArray;
  
  private String[] trfUseCdListForDwell = new String[] {"1","2","3","4","5"};
  private String[] trfUseCdListForNonDwell = new String[] {"5","6"};


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

public double getTotfrar() {
	return totfrar;
}

public void setTotfrar(double totfrar) {
	this.totfrar = totfrar;
}

public double getShareRt() {
	return shareRt;
}

public void setShareRt(double shareRt) {
	this.shareRt = shareRt;
}

public int getTremNum() {
	return tremNum;
}

public void setTremNum(int tremNum) {
	this.tremNum = tremNum;
}

public List<TrfIpcssMeanShareRt> getIpcssResultList() {
	return ipcssResultList;
}

public void setIpcssResultList(List<TrfIpcssMeanShareRt> ipcssResultList) {
	this.ipcssResultList = ipcssResultList;
}

public String getShareRtArray() {
	return shareRtArray;
}

public void setShareRtArray(String shareRtArray) {
	this.shareRtArray = shareRtArray;
}

public String getUsgNo() {
	return usgNo;
}

public void setUsgNo(String usgNo) {
	this.usgNo = usgNo;
}

public String getMeanShareNo() {
	return meanShareNo;
}

public void setMeanShareNo(String meanShareNo) {
	this.meanShareNo = meanShareNo;
}

public String[] getTrfUseCdListForDwell() {
	return trfUseCdListForDwell;
}

public void setTrfUseCdListForDwell(String[] trfUseCdListForDwell) {
	this.trfUseCdListForDwell = trfUseCdListForDwell;
}

public String[] getTrfUseCdListForNonDwell() {
	return trfUseCdListForNonDwell;
}

public void setTrfUseCdListForNonDwell(String[] trfUseCdListForNonDwell) {
	this.trfUseCdListForNonDwell = trfUseCdListForNonDwell;
}

public String getCdNm() {
	return cdNm;
}

public void setCdNm(String cdNm) {
	this.cdNm = cdNm;
}

public String getEtlDt() {
	return etlDt;
}

public void setEtlDt(String etlDt) {
	this.etlDt = etlDt;
}
  

}
