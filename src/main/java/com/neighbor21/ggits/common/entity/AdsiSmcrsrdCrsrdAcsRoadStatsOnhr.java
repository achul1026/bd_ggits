package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//스마트교차로 교차로 접근 도로 통계 1시간
public class AdsiSmcrsrdCrsrdAcsRoadStatsOnhr extends CommonEntity{

    private String mngInstCd; //관리 기관 코드
    private Timestamp clctDt; //수집 일시
    private String crsrdId; //교차로 아이디
    private String acsRoadId; //접근 도로 아이디
    private Long vhclTrfvlm; //차량 교통량
    private Long pdstCnt; //보행자 수
    private Double avgVhclSpeed; //평균 차량 속도
    private Double avgPdstSpeed; //평균 보행자 속도
    private Long ctrlDelayTime; //제어 지연 시간

  private String acsRoadNm; // 접근 도로명

  private Long sumTrfvlm;	// 누적교통량
  private Double avgTrfvlm;	// 평균교통량
  private Long sumSpd;		// 누적 속도
  private Double avgSpd;	// 평균 속도
  private String roadName;	// 도로명

    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }

    public Timestamp getClctDt() {
        return clctDt;
    }

    public void setClctDt(Timestamp clctDt) {
        this.clctDt = clctDt;
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

    public Long getVhclTrfvlm() {
        return vhclTrfvlm;
    }

    public void setVhclTrfvlm(Long vhclTrfvlm) {
        this.vhclTrfvlm = vhclTrfvlm;
    }

    public Long getPdstCnt() {
        return pdstCnt;
    }

    public void setPdstCnt(Long pdstCnt) {
        this.pdstCnt = pdstCnt;
    }

    public Double getAvgVhclSpeed() {
        return avgVhclSpeed;
    }

    public void setAvgVhclSpeed(Double avgVhclSpeed) {
        this.avgVhclSpeed = avgVhclSpeed;
    }

    public Double getAvgPdstSpeed() {
        return avgPdstSpeed;
    }

    public void setAvgPdstSpeed(Double avgPdstSpeed) {
        this.avgPdstSpeed = avgPdstSpeed;
    }

    public Long getCtrlDelayTime() {
        return ctrlDelayTime;
    }

    public void setCtrlDelayTime(Long ctrlDelayTime) {
        this.ctrlDelayTime = ctrlDelayTime;
    }

  public String getAcsRoadNm() {
	return acsRoadNm;
  }
	
  public void setAcsRoadNm(String acsRoadNm) {
	this.acsRoadNm = acsRoadNm;
  }

public Long getSumTrfvlm() {
	return sumTrfvlm;
}

public void setSumTrfvlm(Long sumTrfvlm) {
	this.sumTrfvlm = sumTrfvlm;
}

public Double getAvgTrfvlm() {
	return avgTrfvlm;
}

public void setAvgTrfvlm(Double avgTrfvlm) {
	this.avgTrfvlm = avgTrfvlm;
}

public Long getSumSpd() {
	return sumSpd;
}

public void setSumSpd(Long sumSpd) {
	this.sumSpd = sumSpd;
}

public Double getAvgSpd() {
	return avgSpd;
}

public void setAvgSpd(Double avgSpd) {
	this.avgSpd = avgSpd;
}

public String getRoadName() {
	return roadName;
}

public void setRoadName(String roadName) {
	this.roadName = roadName;
}
}
