package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//마스터 운영 그룹 정보
public class MOpGrpInfo extends CommonEntity{
	
	private String grpId; // 그룹 아이디
	private String grpNm; // 그룹 명
	private String grpDescr; // 그룹 설명
	private String bscGrpYn = "N"; // 기본 그룹 여부
	private String upperOprtrAuthGrpYn = "N"; // 상위 관리자 그룹 여부
	private long authId; // 권한 아이디
	private Timestamp crtDt; //등록일
	private Timestamp updtDt; //수정일
	
	
	//#MOpAuthority 
	private String authNm;
	
	//그룹원 수
	private long userCnt;
	
	
	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getGrpNm() {
		return grpNm;
	}

	public void setGrpNm(String grpNm) {
		this.grpNm = grpNm;
	}

	public String getGrpDescr() {
		return grpDescr;
	}

	public void setGrpDescr(String grpDescr) {
		this.grpDescr = grpDescr;
	}

	public String getBscGrpYn() {
		return bscGrpYn;
	}

	public void setBscGrpYn(String bscGrpYn) {
		this.bscGrpYn = bscGrpYn;
	}

	public String getUpperOprtrAuthGrpYn() {
		return upperOprtrAuthGrpYn;
	}

	public void setUpperOprtrAuthGrpYn(String upperOprtrAuthGrpYn) {
		this.upperOprtrAuthGrpYn = upperOprtrAuthGrpYn;
	}

	public long getAuthId() {
		return authId;
	}

	public void setAuthId(long authId) {
		this.authId = authId;
	}

	
	public String getAuthNm() {
		return authNm;
	}

	public void setAuthNm(String authNm) {
		this.authNm = authNm;
	}

	public long getUserCnt() {
		return userCnt;
	}

	public void setUserCnt(long userCnt) {
		this.userCnt = userCnt;
	}

	public Timestamp getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Timestamp crtDt) {
		this.crtDt = crtDt;
	}

	public Timestamp getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(Timestamp updtDt) {
		this.updtDt = updtDt;
	}
	
	
}
