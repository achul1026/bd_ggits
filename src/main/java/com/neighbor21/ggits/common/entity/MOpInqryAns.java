package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MOpInqryAns {
	
	private String inqryId;				//문의아이디
	private String inqryAnsId;			//문의 답변 아이디
	private Long oprtrId;               //운영자 아이디
	private String ansCnts;             //답변 내용
	private Timestamp AnsCrtDt;         //답변 생성 일시
	private Timestamp AnsUpdtDt;        //답변 수정 일시
	private String atchFileYn;          //첨부 파일 여부
	private String atchFileNm;          //첨부 파일 명
	private String atchFileOrgNm;       //첨부 파일 원본 명
	private String atchFilePath;        //첨부 파일 경로
	private Long atchFileSize;          //첨부 파일 크기
	
	//#MOpOperator
	private String oprtrNm;				//운영자 이름
	
	public String getInqryId() {
		return inqryId;
	}
	public void setInqryId(String inqryId) {
		this.inqryId = inqryId;
	}
	public String getInqryAnsId() {
		return inqryAnsId;
	}
	public void setInqryAnsId(String inqryAnsId) {
		this.inqryAnsId = inqryAnsId;
	}
	public Long getOprtrId() {
		return oprtrId;
	}
	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}
	public String getAnsCnts() {
		return ansCnts;
	}
	public void setAnsCnts(String ansCnts) {
		this.ansCnts = ansCnts;
	}
	public Timestamp getAnsCrtDt() {
		return AnsCrtDt;
	}
	public void setAnsCrtDt(Timestamp ansCrtDt) {
		AnsCrtDt = ansCrtDt;
	}
	public Timestamp getAnsUpdtDt() {
		return AnsUpdtDt;
	}
	public void setAnsUpdtDt(Timestamp ansUpdtDt) {
		AnsUpdtDt = ansUpdtDt;
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
