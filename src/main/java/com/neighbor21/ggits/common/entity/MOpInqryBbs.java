package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;
import java.util.List;

public class MOpInqryBbs extends CommonEntity {
	private String inqryId;				//문의 아이디
	private Long oprtrId;               //운영자 아이디
	private String inqryTitle;            //문의 제목
	private String inqryCnts;             //문의 내용
	private Timestamp inqryCrtDt;         //문의 생성 일시
	private Timestamp inqryUpdtDt;        //문의 수정 일시
	private Timestamp inqryAnsDt;        //문의 답변 일시
	private Long inqrySrchCnt;            //문의 조회 수
	private String inqryAnsYn;			//문의 답변 여부
	private String atchFileYn;          //첨부 파일 여부
	private String atchFileNm;          //첨부 파일 명
	private String atchFileOrgNm;       //첨부 파일 원본 명
	private String atchFilePath;        //첨부 파일 경로
	private Long atchFileSize;          //첨부 파일 크기
	
	//#MOpOperator
	private String oprtrNm;				//운영자 이름
	
	//답변 목록
	List<MOpInqryAns> mOpInqryAnsList;
	
	public String getInqryId() {
		return inqryId;
	}
	public void setInqryId(String inqryId) {
		this.inqryId = inqryId;
	}
	public Long getOprtrId() {
		return oprtrId;
	}
	public void setOprtrId(Long oprtrId) {
		this.oprtrId = oprtrId;
	}
	public String getInqryTitle() {
		return inqryTitle;
	}
	public void setInqryTitle(String inqryTitle) {
		this.inqryTitle = inqryTitle;
	}
	public String getInqryCnts() {
		return inqryCnts;
	}
	public void setInqryCnts(String inqryCnts) {
		this.inqryCnts = inqryCnts;
	}
	public Timestamp getInqryCrtDt() {
		return inqryCrtDt;
	}
	public void setInqryCrtDt(Timestamp inqryCrtDt) {
		this.inqryCrtDt = inqryCrtDt;
	}
	public Timestamp getInqryUpdtDt() {
		return inqryUpdtDt;
	}
	public void setInqryUpdtDt(Timestamp inqryUpdtDt) {
		this.inqryUpdtDt = inqryUpdtDt;
	}
	public Timestamp getInqryAnsDt() {
		return inqryAnsDt;
	}
	public void setInqryAnsDt(Timestamp inqryAnsDt) {
		this.inqryAnsDt = inqryAnsDt;
	}
	public Long getInqrySrchCnt() {
		return inqrySrchCnt;
	}
	public void setInqrySrchCnt(Long inqrySrchCnt) {
		this.inqrySrchCnt = inqrySrchCnt;
	}
	public String getInqryAnsYn() {
		return inqryAnsYn;
	}
	public void setInqryAnsYn(String inqryAnsYn) {
		this.inqryAnsYn = inqryAnsYn;
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
	public List<MOpInqryAns> getmOpInqryAnsList() {
		return mOpInqryAnsList;
	}
	public void setmOpInqryAnsList(List<MOpInqryAns> mOpInqryAnsList) {
		this.mOpInqryAnsList = mOpInqryAnsList;
	}
}
