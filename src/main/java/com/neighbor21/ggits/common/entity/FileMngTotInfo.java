package com.neighbor21.ggits.common.entity;

public class FileMngTotInfo extends CommonEntity {
	String fileMngId;			//파일 관리 아이디
	String fileDivCd;			//파일 구분 코드
	String fileNm;				//파일이름
	String fileOrgNm;			//파일 원본 명
	String registYmd;			//등록 일자
	String savePath; 			//저장 경로
	Long saveSize;			    //저장 크기
	String atchFileStr;			//Select용 bytea Type
	byte[] atchFile;			//Insert용 bytea
	
	public String getFileMngId() {
		return fileMngId;
	}
	public void setFileMngId(String fileMngId) {
		this.fileMngId = fileMngId;
	}
	public String getFileDivCd() {
		return fileDivCd;
	}
	public void setFileDivCd(String fileDivCd) {
		this.fileDivCd = fileDivCd;
	}
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFileOrgNm() {
		return fileOrgNm;
	}
	public void setFileOrgNm(String fileOrgNm) {
		this.fileOrgNm = fileOrgNm;
	}
	public String getRegistYmd() {
		return registYmd;
	}
	public void setRegistYmd(String registYmd) {
		this.registYmd = registYmd;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getAtchFileStr() {
		return atchFileStr;
	}
	public void setAtchFileStr(String atchFileStr) {
		this.atchFileStr = atchFileStr;
	}
	public byte[] getAtchFile() {
		return atchFile;
	}
	public void setAtchFile(byte[] atchFile) {
		this.atchFile = atchFile;
	}
	public Long getSaveSize() {
		return saveSize;
	}
	public void setSaveSize(Long saveSize) {
		this.saveSize = saveSize;
	}
}
