package com.neighbor21.ggits.common.entity;

import java.util.List;

public class TrfIpcssEtcTrfvlm {
    private String 	ipcssMngNo; //영향평가 관리 번호
    private String 	dywkDiv;					//요일 구분
    private String  exmnYmd;        		//조사 일자
    private String  pointNm;        		//지점 명
    private String  timeSctnNm;       	//시간 구간 명
    private long pdstCnt;        		//보행자 수
    private long bcyclCnt;       	 	//자전거 수
    private long indvslMvmnEqpmntCnt;    //개인 이동 장비 수
    private String pointNo;					//지점 순서
    private String etlDt;
    private String[] useTypeCntArray;
    private List<TrfIpcssEtcTrfvlm> ipcssResultList; 
    
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


  public String getPointNm() {
    return pointNm;
  }

  public void setPointNm(String pointNm) {
    this.pointNm = pointNm;
  }


  public String getTimeSctnNm() {
    return timeSctnNm;
  }

  public void setTimeSctnNm(String timeSctnNm) {
    this.timeSctnNm = timeSctnNm;
  }


  public long getPdstCnt() {
    return pdstCnt;
  }

  public void setPdstCnt(long pdstCnt) {
    this.pdstCnt = pdstCnt;
  }


  public long getBcyclCnt() {
    return bcyclCnt;
  }

  public void setBcyclCnt(long bcyclCnt) {
    this.bcyclCnt = bcyclCnt;
  }


  public long getIndvslMvmnEqpmntCnt() {
    return indvslMvmnEqpmntCnt;
  }

  public void setIndvslMvmnEqpmntCnt(long indvslMvmnEqpmntCnt) {
    this.indvslMvmnEqpmntCnt = indvslMvmnEqpmntCnt;
  }

  public String[] getUseTypeCntArray() {
    String[] useTypeCntArrayCopy = null;
    if(this.useTypeCntArray != null) {
      useTypeCntArrayCopy = new String[useTypeCntArray.length];
      for (int i = 0; i < useTypeCntArray.length; i++) {
        useTypeCntArrayCopy[i] = this.useTypeCntArray[i];
      }
    }
    return useTypeCntArrayCopy;
  }

  public void setUseTypeCntArray(String[] useTypeCntArray) {
    if(useTypeCntArray != null) {
      this.useTypeCntArray = new String[useTypeCntArray.length];
      for (int i = 0; i <useTypeCntArray.length; i++) {
        this.useTypeCntArray[i] = useTypeCntArray[i];
      }
    }
  }

public String getDywkDiv() {
	return dywkDiv;
}

public void setDywkDiv(String dywkDiv) {
	this.dywkDiv = dywkDiv;
}

public List<TrfIpcssEtcTrfvlm> getIpcssResultList() {
	return ipcssResultList;
}

public void setIpcssResultList(List<TrfIpcssEtcTrfvlm> ipcssResultList) {
	this.ipcssResultList = ipcssResultList;
}

public String getEtlDt() {
	return etlDt;
}

public void setEtlDt(String etlDt) {
	this.etlDt = etlDt;
}

public String getPointNo() {
	return pointNo;
}

public void setPointNo(String pointNo) {
	this.pointNo = pointNo;
}

}
