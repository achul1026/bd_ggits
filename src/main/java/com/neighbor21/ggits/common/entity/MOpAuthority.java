package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;
import java.util.List;

//마스터 운영 권한
public class MOpAuthority extends CommonEntity{

	private long authId; // 권한 아이디
	private String authNm; // 권한 명
	private String descr; // 설명
	private String authUseYn; // 권한 사용 여부
	
	private Timestamp crtDt; // 수정 일시
	private Timestamp updtDt; // 수정 일시
	
	private String authCd; //권한 코드
	
	//미존재 컬럼 조회값
	private int grpCnt; //그룹 수
	
	//#MOpAthrMenu
	List<String> menuIdArr; //Request용 insert menuId리스트

	List<String> deleteMenuIdArr; //Request용 delete menuId리스트

	public MOpAuthority(long authId) {
		super();
		this.authId = authId;
	}

	public MOpAuthority() {
		super();
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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getAuthUseYn() {
		return authUseYn;
	}

	public void setAuthUseYn(String authUseYn) {
		this.authUseYn = authUseYn;
	}

	public List<String> getMenuIdArr() {
		return menuIdArr;
	}

	public void setMenuIdArr(List<String> menuIdArr) {
		this.menuIdArr = menuIdArr;
	}

	public int getGrpCnt() {
		return grpCnt;
	}

	public void setGrpCnt(int grpCnt) {
		this.grpCnt = grpCnt;
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

	public List<String> getDeleteMenuIdArr() {
		return deleteMenuIdArr;
	}

	public void setDeleteMenuIdArr(List<String> deleteMenuIdArr) {
		this.deleteMenuIdArr = deleteMenuIdArr;
	}

	public String getAuthCd() {
		return authCd;
	}

	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}

	@Override
	public String toString() {
		return "MOpAuthority [authId=" + authId + ", authNm=" + authNm + ", descr=" + descr + ", authUseYn=" + authUseYn
				+ ", crtDt=" + crtDt + ", updtDt=" + updtDt + ", grpCnt=" + grpCnt + ", menuIdArr=" + menuIdArr
				+ ", deleteMenuIdArr=" + deleteMenuIdArr + "]";
	}
	
}
