package com.neighbor21.ggits.common.entity;

/**
 * 데이터 사용 통계 보고서
 * @author qudck
 *
 */
public class GOpDataUseStatsRpt extends CommonEntity {
	public String rptId;			//리포트 아이디
	public Long oprtrId;			//운영자 Id
	public String rptTitle;			//보고서 제목
	public String dtlUrl;			//상세 URL
	public String registYmd;		//등록 일자
	
	//#MOpOperator
	public String oprtrNm;			//등록자 이름
	public String oprtrEmail;		//등록자 이메일
	
	public String getRptId() {
		return rptId;
	}
	public void setRptId(String rptId) {
		this.rptId = rptId;
	}
	public Long getOprtrId() {
		return oprtrId;
	}
	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}
	public String getRptTitle() {
		return rptTitle;
	}
	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}
	public String getDtlUrl() {
		return dtlUrl;
	}
	public void setDtlUrl(String dtlUrl) {
		this.dtlUrl = dtlUrl;
	}
	public String getRegistYmd() {
		return registYmd;
	}
	public void setRegistYmd(String registYmd) {
		this.registYmd = registYmd;
	}
	public String getOprtrNm() {
		return oprtrNm;
	}
	public void setOprtrNm(String oprtrNm) {
		this.oprtrNm = oprtrNm;
	}
	public String getOprtrEmail() {
		return oprtrEmail;
	}
	public void setOprtrEmail(String oprtrEmail) {
		this.oprtrEmail = oprtrEmail;
	}
}
