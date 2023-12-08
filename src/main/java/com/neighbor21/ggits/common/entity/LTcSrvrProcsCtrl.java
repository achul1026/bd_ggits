package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//서버 정보 조회
public class LTcSrvrProcsCtrl extends CommonEntity{

    private Timestamp	clctDt;		//수집 일시
    private String srvrProcsId; 	//서버 프로세스 아이디
    private String procsCtrlCd;		//프로세스 제어 코드
    private String srvrMngType;		//서버 관리 유형
    private String srvrSttsCd;		//서버 상태 코드
    private String srvrNm;			//서버 명
    
	public LTcSrvrProcsCtrl() {
		super();
	}
	
	public Timestamp getClctDt() {
		return clctDt;
	}
	
	public void setClctDt(Timestamp clctDt) {
		this.clctDt = clctDt;
	}
	
	public String getSrvrProcsId() {
		return srvrProcsId;
	}
	
	public void setSrvrProcsId(String srvrProcsId) {
		this.srvrProcsId = srvrProcsId;
	}
	
	public String getProcsCtrlCd() {
		return procsCtrlCd;
	}
	
	public void setProcsCtrlCd(String procsCtrlCd) {
		this.procsCtrlCd = procsCtrlCd;
	}
	
	public String getSrvrMngType() {
		return srvrMngType;
	}
	
	public void setSrvrMngType(String srvrMngType) {
		this.srvrMngType = srvrMngType;
	}

	public String getSrvrSttsCd() {
		return srvrSttsCd;
	}

	public void setSrvrSttsCd(String srvrSttsCd) {
		this.srvrSttsCd = srvrSttsCd;
	}

	public String getSrvrNm() {
		return srvrNm;
	}

	public void setSrvrNm(String srvrNm) {
		this.srvrNm = srvrNm;
	}
	
}
