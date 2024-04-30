package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//마스터 운영 운영자
public class MOpOperator extends CommonEntity{

	private Long oprtrId; // 운영자 아이디
	private String grpId; // 그룹 아이디
	private String systemCd; // 시스템 코드
	private String oprtrPswd; // 운영자 비밀번호
	private String oprtrNm; // 운영자 명
	private Long authId; // 권한 아이디
	private String oprtrJbttl; // 운영자 직책
	private String oprtrDept; // 운영자 부서
	private Timestamp updtDt; // 수정 일시
	private String note; // 노트
	private String oprtrGrd; // 운영자 등급
	private String useYn; // 사용 여부
	private String oprtrEmail; // 운영자 이메일
	private String oprtrTel; // 운영자 이메일
	private String addngCd; // 행정동 코드
	private String useStplatAgre = "N"; // 사용 약관 동의
	private String prvcMngPolcyAgre = "N"; // 개인정보 관리 방침 동의
	private String oprtrSttsCd; // 운영자 상태 코드
	private String oprtrRegistDd; // 운영자 등록일
	private String mngInstCd; // 관리 기관코드
	private Long layoutNo;		//레이아웃 번호
	
	
	//#MOpGrpInfo 테이블 컬럼 참조
    private String grpNm;        //그룹 명
    private String upperOprtrAuthGrpYn = "N"; // 상위 관리자 그룹 여부
    
    //#MOpAuthority 권한 관련 테이블
    private String authCd;		//권한 코드
	
    
    //비밀번호 확인 컬럼
    private String oprtrPswdChk; 
    
    // common code
    private String cdNm;
    private String mngInstNm;
    
    // no table
    private String loginType;
    
	public Long getOprtrId() {
		return oprtrId;
	}

	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}
	
	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getSystemCd() {
		return systemCd;
	}

	public void setSystemCd(String systemCd) {
		this.systemCd = systemCd;
	}

	public String getOprtrPswd() {
		return oprtrPswd;
	}

	public void setOprtrPswd(String oprtrPswd) {
		this.oprtrPswd = oprtrPswd;
	}

	public String getOprtrNm() {
		return oprtrNm;
	}

	public void setOprtrNm(String oprtrNm) {
		this.oprtrNm = oprtrNm;
	}

	public Long getAuthId() {
		return authId;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public String getOprtrJbttl() {
		return oprtrJbttl;
	}

	public void setOprtrJbttl(String oprtrJbttl) {
		this.oprtrJbttl = oprtrJbttl;
	}

	public String getOprtrDept() {
		return oprtrDept;
	}

	public void setOprtrDept(String oprtrDept) {
		this.oprtrDept = oprtrDept;
	}

	public Timestamp getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(Timestamp updtDt) {
		this.updtDt = updtDt;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOprtrGrd() {
		return oprtrGrd;
	}

	public void setOprtrGrd(String oprtrGrd) {
		this.oprtrGrd = oprtrGrd;
	}

	@Override
	public String getUseYn() {
		return useYn;
	}

	@Override
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getOprtrEmail() {
		return oprtrEmail;
	}

	public void setOprtrEmail(String oprtrEmail) {
		this.oprtrEmail = oprtrEmail;
	}

	public String getOprtrTel() {
		return oprtrTel;
	}

	public void setOprtrTel(String oprtrTel) {
		this.oprtrTel = oprtrTel;
	}
	
	public String getAddngCd() {
		return addngCd;
	}

	public void setAddngCd(String addngCd) {
		this.addngCd = addngCd;
	}

	public String getUseStplatAgre() {
		return useStplatAgre;
	}

	public void setUseStplatAgre(String useStplatAgre) {
		this.useStplatAgre = useStplatAgre;
	}

	public String getPrvcMngPolcyAgre() {
		return prvcMngPolcyAgre;
	}

	public void setPrvcMngPolcyAgre(String prvcMngPolcyAgre) {
		this.prvcMngPolcyAgre = prvcMngPolcyAgre;
	}

	public String getOprtrSttsCd() {
		return oprtrSttsCd;
	}

	public void setOprtrSttsCd(String oprtrSttsCd) {
		this.oprtrSttsCd = oprtrSttsCd;
	}

	public String getOprtrRegistDd() {
		return oprtrRegistDd;
	}

	public void setOprtrRegistDd(String oprtrRegistDd) {
		this.oprtrRegistDd = oprtrRegistDd;
	}
	
	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	public Long getLayoutNo() {
		return layoutNo;
	}

	public void setLayoutNo(Long layoutNo) {
		this.layoutNo = layoutNo;
	}

	public String getGrpNm() {
		return grpNm;
	}

	public void setGrpNm(String grpNm) {
		this.grpNm = grpNm;
	}
	
	public String getUpperOprtrAuthGrpYn() {
		return upperOprtrAuthGrpYn;
	}

	public void setUpperOprtrAuthGrpYn(String upperOprtrAuthGrpYn) {
		this.upperOprtrAuthGrpYn = upperOprtrAuthGrpYn;
	}

	public String getOprtrPswdChk() {
		return oprtrPswdChk;
	}

	public void setOprtrPswdChk(String oprtrPswdChk) {
		this.oprtrPswdChk = oprtrPswdChk;
	}

	public String getCdNm() {
		return cdNm;
	}

	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}

	public String getMngInstNm() {
		return mngInstNm;
	}

	public void setMngInstNm(String mngInstNm) {
		this.mngInstNm = mngInstNm;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getAuthCd() {
		return authCd;
	}

	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
}
