package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MOpNtcBbs extends CommonEntity{
	
	private String ntcId;				//공지아이디
	private Long oprtrId;               //운영자 아이디
	private String ntcTitle;            //공지 제목
	private String ntcCnts;             //공지 내용
	private Timestamp ntcCrtDt;         //공지 생성 일시
	private Timestamp ntcUpdtDt;        //공지 수정 일시
	private Long ntcSrchCnt;            //공지 조회 수
	private String atchFileYn;          //첨부 파일 여부
	private String atchFileNm;          //첨부 파일 명
	private String atchFileOrgNm;       //첨부 파일 원본 명
	private String atchFilePath;        //첨부 파일 경로
	private Long atchFileSize;          //첨부 파일 크기
	
	//#MOpOperator
	private String oprtrNm;				//작성자 명
	
	public String getNtcId() {
		return ntcId;
	}
	public void setNtcId(String ntcId) {
		this.ntcId = ntcId;
	}
	public Long getOprtrId() {
		return oprtrId;
	}
	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}
	public String getNtcTitle() {
		return ntcTitle;
	}
	public void setNtcTitle(String ntcTitle) {
		this.ntcTitle = ntcTitle;
	}
	public String getNtcCnts() {
		return ntcCnts;
	}
	public void setNtcCnts(String ntcCnts) {
		this.ntcCnts = ntcCnts;
	}
	public Timestamp getNtcCrtDt() {
		return ntcCrtDt;
	}
	public void setNtcCrtDt(Timestamp ntcCrtDt) {
		this.ntcCrtDt = ntcCrtDt;
	}
	public Timestamp getNtcUpdtDt() {
		return ntcUpdtDt;
	}
	public void setNtcUpdtDt(Timestamp ntcUpdtDt) {
		this.ntcUpdtDt = ntcUpdtDt;
	}
	public Long getNtcSrchCnt() {
		return ntcSrchCnt;
	}
	public void setNtcSrchCnt(Long ntcSrchCnt) {
		this.ntcSrchCnt = ntcSrchCnt;
	}
	public String getAtchFileYn() {
		return atchFileYn;
	}
	public void setAtchFileYn(String atchFileYn) {
		this.atchFileYn = atchFileYn;
	}
	public String getAtchFileNm() {
		return atchFileNm;
	}
	public void setAtchFileNm(String atchFileNm) {
		this.atchFileNm = atchFileNm;
	}
	public String getAtchFileOrgNm() {
		return atchFileOrgNm;
	}
	public void setAtchFileOrgNm(String atchFileOrgNm) {
		this.atchFileOrgNm = atchFileOrgNm;
	}
	public String getAtchFilePath() {
		return atchFilePath;
	}
	public void setAtchFilePath(String atchFilePath) {
		this.atchFilePath = atchFilePath;
	}
	public Long getAtchFileSize() {
		return atchFileSize;
	}
	public void setAtchFileSize(Long atchFileSize) {
		this.atchFileSize = atchFileSize;
	}
	public String getOprtrNm() {
		return oprtrNm;
	}
	public void setOprtrNm(String oprtrNm) {
		this.oprtrNm = oprtrNm;
	}
}
