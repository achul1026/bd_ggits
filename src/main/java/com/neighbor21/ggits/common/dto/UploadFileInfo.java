package com.neighbor21.ggits.common.dto;

public class UploadFileInfo {
	String fileNm;
	String filePath;
	Long   fileSize = 0L;
	String originalFileNm;
	String uploadTime;
	byte[] atchFile;
	
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getOriginalFileNm() {
		return originalFileNm;
	}
	public void setOriginalFileNm(String originalFileNm) {
		this.originalFileNm = originalFileNm;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public byte[] getAtchFile() {
		return atchFile;
	}
	public void setAtchFile(byte[] atchFile) {
		this.atchFile = atchFile;
	}
}
