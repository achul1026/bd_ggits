package com.neighbor21.ggits.common.entity;

public class TsDggdVhclRungInfoCur {
    private double vhclLcLon;
    private double vhclLcLat;
    private String vhclRegistNo;
    private String occurDt;
    private long speed;
    private String strtgCd;
    private String rungPlanYn;
    private String dggdCd;
    private String dggdNm;
    private String acdntYn;

    private Double targetLon;
    private Double targetLat;
    
    //2023-11-28 위험물 차량 컨텍스트 메뉴 발생일시
    private String mapOccurDtFormat;

    public double getVhclLcLon() {
        return vhclLcLon;
    }

    public void setVhclLcLon(double vhclLcLon) {
        this.vhclLcLon = vhclLcLon;
    }


    public double getVhclLcLat() {
        return vhclLcLat;
    }

    public void setVhclLcLat(double vhclLcLat) {
        this.vhclLcLat = vhclLcLat;
    }


    public String getVhclRegistNo() {
        return vhclRegistNo;
    }

    public void setVhclRegistNo(String vhclRegistNo) {
        this.vhclRegistNo = vhclRegistNo;
    }


    public String getOccurDt() {
        return occurDt;
    }

    public void setOccurDt(String occurDt) {
        this.occurDt = occurDt;
    }


    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }


    public String getStrtgCd() {
        return strtgCd;
    }

    public void setStrtgCd(String strtgCd) {
        this.strtgCd = strtgCd;
    }


    public String getRungPlanYn() {
        return rungPlanYn;
    }

    public void setRungPlanYn(String rungPlanYn) {
        this.rungPlanYn = rungPlanYn;
    }


    public String getDggdCd() {
        return dggdCd;
    }

    public void setDggdCd(String dggdCd) {
        this.dggdCd = dggdCd;
    }


    public String getDggdNm() {
        return dggdNm;
    }

    public void setDggdNm(String dggdNm) {
        this.dggdNm = dggdNm;
    }

    public Double getTargetLon() {
        return targetLon;
    }

    public void setTargetLon(Double targetLon) {
        this.targetLon = targetLon;
    }

    public Double getTargetLat() {
        return targetLat;
    }

    public void setTargetLat(Double targetLat) {
        this.targetLat = targetLat;
    }

	public String getMapOccurDtFormat() {
		return mapOccurDtFormat;
	}

	public void setMapOccurDtFormat(String mapOccurDtFormat) {
		this.mapOccurDtFormat = mapOccurDtFormat;
	}

    public String getAcdntYn() {
        return acdntYn;
    }

    public void setAcdntYn(String acdntYn) {
        this.acdntYn = acdntYn;
    }
}
