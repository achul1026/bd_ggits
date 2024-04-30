package com.neighbor21.ggits.common.entity;

import java.io.Serializable;

public class MrtDynmcPopltnCell500Rslt implements Serializable {

    private static final long serialVersionUID = 1L;

    private String baseymd;        //기준년월일
    private String timezn;        //기준시간대
    private String cell500;        //셀코드 500
    private Long fltPop;        //유동인구수
    private String etlDt;        //etl 일시
    private String cityCd;
    
    //none col
    private String admiCd;		
    private String adstsdgNm;
    
    //#MOpCode
    private String cdNm;
    
    public String getBaseymd() {
        return baseymd;
    }

    public void setBaseymd(String baseymd) {
        this.baseymd = baseymd;
    }


    public String getTimezn() {
        return timezn;
    }

    public void setTimezn(String timezn) {
        this.timezn = timezn;
    }


    public String getCell500() {
        return cell500;
    }

    public void setCell500(String cell500) {
        this.cell500 = cell500;
    }


    public Long getFltPop() {
        return fltPop;
    }

    public void setFltPop(Long fltPop) {
        this.fltPop = fltPop;
    }


    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public String getCityCd() {
        return cityCd;
    }

    public void setCityCd(String cityCd) {
        this.cityCd = cityCd;
    }

	public String getAdmiCd() {
		return admiCd;
	}

	public void setAdmiCd(String admiCd) {
		this.admiCd = admiCd;
	}

	public String getAdstsdgNm() {
		return adstsdgNm;
	}

	public void setAdstsdgNm(String adstsdgNm) {
		this.adstsdgNm = adstsdgNm;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
    
}
