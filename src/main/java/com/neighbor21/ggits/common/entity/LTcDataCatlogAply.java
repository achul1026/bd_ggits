package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//데이터 카탈로그 신청 이력 테이블
public class LTcDataCatlogAply extends CommonEntity{

    private String    	aplyDsetId;     //신청 아이디
    private Timestamp   aplyDt;   		//신청 일자
    private Long 		oprtrId;		//운영자 아이디
    private String 		mngInstCd;		//기관 코드
    private String 		aplyCmptnYn;	//신청 여부
    
    //#MOpOperator
    private String		oprtrNm;		//운영자 이름
    
    //#MOpCode
    private String 		cdNm;			//코드이름
    
    //#MetaTabInfo
    private String 		tblEngNm;		//테이블 영어 이름
    
	public String getAplyDsetId() {
		return aplyDsetId;
	}
	public void setAplyDsetId(String aplyDsetId) {
		this.aplyDsetId = aplyDsetId;
	}
	public Timestamp getAplyDt() {
		return aplyDt;
	}
	public void setAplyDt(Timestamp aplyDt) {
		this.aplyDt = aplyDt;
	}
	public Long getOprtrId() {
		return oprtrId;
	}
	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}
	public String getMngInstCd() {
		return mngInstCd;
	}
	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}
	public String getAplyCmptnYn() {
		return aplyCmptnYn;
	}
	public void setAplyCmptnYn(String aplyCmptnYn) {
		this.aplyCmptnYn = aplyCmptnYn;
	}
	public String getOprtrNm() {
		return oprtrNm;
	}
	public void setOprtrNm(String oprtrNm) {
		this.oprtrNm = oprtrNm;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getTblEngNm() {
		return tblEngNm;
	}
	public void setTblEngNm(String tblEngNm) {
		this.tblEngNm = tblEngNm;
	}
}
