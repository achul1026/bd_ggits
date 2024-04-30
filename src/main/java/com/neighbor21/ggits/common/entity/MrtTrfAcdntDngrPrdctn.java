package com.neighbor21.ggits.common.entity;

public class MrtTrfAcdntDngrPrdctn {
    private String linkId;        //링크 아이디
    private Long speed;        //속도
    private String safeIdex;        //안전 지수
    private String safeGrd;        //안전 등급	0:안전 1:주의 2:위험 3:심각

    private Long noneLinkCntBySgg;
    private Long speedOverCntBySgg;
    private Long speedUnderCntBySgg;
    private Long safeCntBySgg;
    private Long warnCntBySgg;
    private Long dangerCntBySgg;
    private Long seriousCntBySgg;
    private String sggCd;
    
    //#CGmStdLink
    private String roadName;	//도로명

    //#CGmStdLinkAdstdgMppg
    private String    adsiNm;        //행정시 명
    private String    adstdgCd;        //법정동 코드
    private String    adstdgNm;        //법정동 명

    
    //none col
    private String ordSafeIdex;
    
    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public Long getSpeed() {
		return speed;
	}

	public void setSpeed(Long speed) {
		this.speed = speed;
	}

	public String getSafeIdex() {
        return safeIdex;
    }

    public void setSafeIdex(String safeIdex) {
        this.safeIdex = safeIdex;
    }

    public String getSafeGrd() {
        return safeGrd;
    }

    public void setSafeGrd(String safeGrd) {
        this.safeGrd = safeGrd;
    }

    public Long getNoneLinkCntBySgg() {
        return noneLinkCntBySgg;
    }

    public void setNoneLinkCntBySgg(Long noneLinkCntBySgg) {
        this.noneLinkCntBySgg = noneLinkCntBySgg;
    }

    public Long getSpeedOverCntBySgg() {
        return speedOverCntBySgg;
    }

    public void setSpeedOverCntBySgg(Long speedOverCntBySgg) {
        this.speedOverCntBySgg = speedOverCntBySgg;
    }

    public Long getSpeedUnderCntBySgg() {
        return speedUnderCntBySgg;
    }

    public void setSpeedUnderCntBySgg(Long speedUnderCntBySgg) {
        this.speedUnderCntBySgg = speedUnderCntBySgg;
    }

    public Long getSafeCntBySgg() {
        return safeCntBySgg;
    }

    public void setSafeCntBySgg(Long safeCntBySgg) {
        this.safeCntBySgg = safeCntBySgg;
    }

    public Long getWarnCntBySgg() {
        return warnCntBySgg;
    }

    public void setWarnCntBySgg(Long warnCntBySgg) {
        this.warnCntBySgg = warnCntBySgg;
    }

    public Long getDangerCntBySgg() {
        return dangerCntBySgg;
    }

    public void setDangerCntBySgg(Long dangerCntBySgg) {
        this.dangerCntBySgg = dangerCntBySgg;
    }

    public Long getSeriousCntBySgg() {
        return seriousCntBySgg;
    }

    public void setSeriousCntBySgg(Long seriousCntBySgg) {
        this.seriousCntBySgg = seriousCntBySgg;
    }

    public String getSggCd() {
        return sggCd;
    }

    public void setSggCd(String sggCd) {
        this.sggCd = sggCd;
    }

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getAdsiNm() {
		return adsiNm;
	}

	public void setAdsiNm(String adsiNm) {
		this.adsiNm = adsiNm;
	}

	public String getAdstdgCd() {
		return adstdgCd;
	}

	public void setAdstdgCd(String adstdgCd) {
		this.adstdgCd = adstdgCd;
	}

	public String getAdstdgNm() {
		return adstdgNm;
	}

	public void setAdstdgNm(String adstdgNm) {
		this.adstdgNm = adstdgNm;
	}

	public String getOrdSafeIdex() {
		return ordSafeIdex;
	}

	public void setOrdSafeIdex(String ordSafeIdex) {
		this.ordSafeIdex = ordSafeIdex;
	}
}
