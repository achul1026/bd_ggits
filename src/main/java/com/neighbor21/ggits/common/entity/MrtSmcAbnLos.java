package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtSmcAbnLos extends CommonEntity{
    private String    mngInstCd;        //관리 기관 코드
    private Timestamp    anlsDt;        //분석 일시
    private String    linkId;        //링크 아이디
    private String    dywkCd;        //요일 코드
    private long    avgVhclSpeed;        //평균 차량 속도
    private String    vhclTrfvlm;        //교통량 혼잡도
    private String    etlDt;        //etl 일시
    
    private String acsRoadNm;	// 도로명
    
  public MrtSmcAbnLos(CommonEntity commonEntity) {
	  super(commonEntity);
	}

public MrtSmcAbnLos() {
		super();
	}

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


  public String getLinkId() {
    return linkId;
  }

  public void setLinkId(String linkId) {
    this.linkId = linkId;
  }


  public String getDywkCd() {
    return dywkCd;
  }

  public void setDywkCd(String dywkCd) {
    this.dywkCd = dywkCd;
  }


  public long getAvgVhclSpeed() {
    return avgVhclSpeed;
  }

  public void setAvgVhclSpeed(long avgVhclSpeed) {
    this.avgVhclSpeed = avgVhclSpeed;
  }

  public String getVhclTrfvlm() {
	return vhclTrfvlm;
}

public void setVhclTrfvlm(String vhclTrfvlm) {
	this.vhclTrfvlm = vhclTrfvlm;
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

}
