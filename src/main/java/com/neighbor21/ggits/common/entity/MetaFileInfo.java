package com.neighbor21.ggits.common.entity;
public class MetaFileInfo {
    private String    fileId;        //파일 아이디
    private String    fileNm;        //파일 명
    private String    orgFileNm;        //원본 파일 명
    private String    saveStorgType;        //저장 스토리지 유형
    private String    saveLc;        //저장 위치
    private long    saveSize;        //저장 크기
    private String    dsetId;        //데이터셋 아이디
    
    //#MetaTabInfo
    private String tblId;			//테이블 아이디


  public String getFileId() {
    return fileId;
  }

  public void setFileId(String fileId) {
    this.fileId = fileId;
  }


  public String getFileNm() {
    return fileNm;
  }

  public void setFileNm(String fileNm) {
    this.fileNm = fileNm;
  }


  public String getOrgFileNm() {
    return orgFileNm;
  }

  public void setOrgFileNm(String orgFileNm) {
    this.orgFileNm = orgFileNm;
  }


  public String getSaveStorgType() {
    return saveStorgType;
  }

  public void setSaveStorgType(String saveStorgType) {
    this.saveStorgType = saveStorgType;
  }


  public String getSaveLc() {
    return saveLc;
  }

  public void setSaveLc(String saveLc) {
    this.saveLc = saveLc;
  }


  public long getSaveSize() {
    return saveSize;
  }

  public void setSaveSize(long saveSize) {
    this.saveSize = saveSize;
  }


  public String getDsetId() {
    return dsetId;
  }

  public void setDsetId(String dsetId) {
    this.dsetId = dsetId;
  }

  public String getTblId() {
	return tblId;
  }
	
  public void setTblId(String tblId) {
	this.tblId = tblId;
  }

}
