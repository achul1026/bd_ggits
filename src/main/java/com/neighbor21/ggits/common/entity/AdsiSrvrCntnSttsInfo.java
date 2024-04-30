package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class AdsiSrvrCntnSttsInfo {
	private Timestamp clctDt; // 수집 일시
	private String asctnSrvrId; // 연계 서버 아이디
	private String mngInstCd; // 관리 기관 코드
	private String cntnSttsCd; // 접속 상태 코드
	private String etlDt; // etl 일시
	
	public Timestamp getClctDt() {
		return clctDt;
	}

	public void setClctDt(Timestamp clctDt) {
		this.clctDt = clctDt;
	}

	public String getAsctnSrvrId() {
		return asctnSrvrId;
	}

	public void setAsctnSrvrId(String asctnSrvrId) {
		this.asctnSrvrId = asctnSrvrId;
	}

	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	public String getCntnSttsCd() {
		return cntnSttsCd;
	}

	public void setCntnSttsCd(String cntnSttsCd) {
		this.cntnSttsCd = cntnSttsCd;
	}

	public String getEtlDt() {
		return etlDt;
	}

	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}

}
