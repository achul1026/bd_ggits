package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;
import java.util.List;

//테이블 정보
public class MetaTabInfo extends CommonEntity{

  private String tblId; //테이블 아이디
  private String dsetId; //db 아이디
  private String rltinstId; //유관기관 아이디
  private String tblOwnrNm; //테이블 소유자 명
  private String tblEngNm; //테이블 영문 명
  private String tblKoreanNm; //테이블 한글 명
  private String tblType; //테이블 유형
  private String tblDescr; //테이블 설명
  private String tpbizClsf; //업종 분류
  private long prsrvPeriod; //보존 기간
  private long tblSize; //테이블 크기
  private long occurCycl; //발생 주기
  private String prvtYn; //비공개 여부
  private String opngDataListNm; //개방 데이터 목록 명
  private String dataKeyword; //데이터 키워드
  private String orgDataNm; //원본 데이터 명
  private String clschmId; //분류체계 명
  private long dataSaveCycl; //데이터 저장 주기
  private Timestamp updtDt; // 수정 일시
  
  //#MetaColInfo
  private String dataType;	//데이터 타입
  
  //수집유형컬럼 아직없음 파라미터전용
  private String collDataType;	//수집 유형 타입
  
  //List View용
  private List<String> collDataList;

  public String getTblId() {
    return tblId;
  }

  public void setTblId(String tblId) {
    this.tblId = tblId;
  }

  public String getDsetId() {
	return dsetId;
  }
	
  public void setDsetId(String dsetId) {
	this.dsetId = dsetId;
  }

public String getRltinstId() {
    return rltinstId;
  }

  public void setRltinstId(String rltinstId) {
    this.rltinstId = rltinstId;
  }


  public String getTblOwnrNm() {
    return tblOwnrNm;
  }

  public void setTblOwnrNm(String tblOwnrNm) {
    this.tblOwnrNm = tblOwnrNm;
  }


  public String getTblEngNm() {
    return tblEngNm;
  }

  public void setTblEngNm(String tblEngNm) {
    this.tblEngNm = tblEngNm;
  }


  public String getTblKoreanNm() {
    return tblKoreanNm;
  }

  public void setTblKoreanNm(String tblKoreanNm) {
    this.tblKoreanNm = tblKoreanNm;
  }


  public String getTblType() {
    return tblType;
  }

  public void setTblType(String tblType) {
    this.tblType = tblType;
  }


  public String getTblDescr() {
    return tblDescr;
  }

  public void setTblDescr(String tblDescr) {
    this.tblDescr = tblDescr;
  }


  public String getTpbizClsf() {
    return tpbizClsf;
  }

  public void setTpbizClsf(String tpbizClsf) {
    this.tpbizClsf = tpbizClsf;
  }


  public long getPrsrvPeriod() {
    return prsrvPeriod;
  }

  public void setPrsrvPeriod(long prsrvPeriod) {
    this.prsrvPeriod = prsrvPeriod;
  }


  public long getTblSize() {
    return tblSize;
  }

  public void setTblSize(long tblSize) {
    this.tblSize = tblSize;
  }


  public long getOccurCycl() {
    return occurCycl;
  }

  public void setOccurCycl(long occurCycl) {
    this.occurCycl = occurCycl;
  }


  public String getPrvtYn() {
    return prvtYn;
  }

  public void setPrvtYn(String prvtYn) {
    this.prvtYn = prvtYn;
  }


  public String getOpngDataListNm() {
    return opngDataListNm;
  }

  public void setOpngDataListNm(String opngDataListNm) {
    this.opngDataListNm = opngDataListNm;
  }


  public String getDataKeyword() {
    return dataKeyword;
  }

  public void setDataKeyword(String dataKeyword) {
    this.dataKeyword = dataKeyword;
  }


  public String getOrgDataNm() {
    return orgDataNm;
  }

  public void setOrgDataNm(String orgDataNm) {
    this.orgDataNm = orgDataNm;
  }
  
  public String getClschmId() {
		return clschmId;
	}
	
	public void setClschmId(String clschmId) {
		this.clschmId = clschmId;
	}
	
  public long getDataSaveCycl() {
	    return dataSaveCycl;
  }

public void setDataSaveCycl(long dataSaveCycl) {
    this.dataSaveCycl = dataSaveCycl;
  }

  public String getDataType() {
	return dataType;
  }

  public void setDataType(String dataType) {
	this.dataType = dataType;
	}

	public String getCollDataType() {
		return collDataType;
	}
	
	public void setCollDataType(String collDataType) {
		this.collDataType = collDataType;
	}

	public List<String> getCollDataList() {
		return collDataList;
	}

	public void setCollDataList(List<String> collDataList) {
		this.collDataList = collDataList;
	}

	public Timestamp getUpdtDt() {
		return updtDt;
	}

	public void setUpdtDt(Timestamp updtDt) {
		this.updtDt = updtDt;
	}
}
