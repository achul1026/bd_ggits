package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

//테이블 정보
public class MetaTabInfo extends CommonEntity{

  private String tblId; //테이블 아이디
  private String dsetId; //db 아이디
  private String tblOwnrNm; //테이블 소유자 명
  private String tblEngNm; //테이블 영문 명
  private String tblKoreanNm; //테이블 한글 명
  private String tblType; //테이블 유형
  private String tblDescr; //테이블 설명
  private String tpbizClsf; //업종 분류
  private Long prsrvPeriod; //보존 기간
  private Long tblSize; //테이블 크기
  private Long occurCycl; //발생 주기
  private String prvtYn; //비공개 여부
  private String opngDataListNm; //개방 데이터 목록 명
  private String dataKeyword; //데이터 키워드
  private String orgDataNm; //원본 데이터 명
  private Long dataSaveCycl; //데이터 저장 주기
  private Timestamp updtDt; // 수정 일시
  
  //조인테이블 #ClschmInfo #MetaInfsysInfo
  private String clschmId; //분류체계 아이디
  private String clschmNm; //분류체계 명
  private String rltinstId; //유관기관 아이디
  
  //#MetaColInfo
  private String dataType;	//데이터 타입
  
  //수집유형컬럼 아직없음 파라미터전용
  private String collDataType;	//수집 유형 타입
 
  
  //List View용
  private List<String> collDataList;
  private String fileExstYn;
  
  //메타데이터 추출용
  List<Map<String,Object>> colInfoList;

  //메타컬럼인포 목록
  private List<MetaColInfo> metaColInfoList;
  
  private String strDataTypeArr;
  private String strColEngNmArr;
  private String strColKoreanNmArr;
  
  
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

    public Long getPrsrvPeriod() {
        return prsrvPeriod;
    }

    public void setPrsrvPeriod(Long prsrvPeriod) {
        this.prsrvPeriod = prsrvPeriod;
    }

    public Long getTblSize() {
        return tblSize;
    }

    public void setTblSize(Long tblSize) {
        this.tblSize = tblSize;
    }

    public Long getOccurCycl() {
        return occurCycl;
    }

    public void setOccurCycl(Long occurCycl) {
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

    public Long getDataSaveCycl() {
        return dataSaveCycl;
    }

    public void setDataSaveCycl(Long dataSaveCycl) {
        this.dataSaveCycl = dataSaveCycl;
    }

    public Timestamp getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(Timestamp updtDt) {
        this.updtDt = updtDt;
    }

    public String getClschmId() {
        return clschmId;
    }

    public void setClschmId(String clschmId) {
        this.clschmId = clschmId;
    }

    public String getClschmNm() {
        return clschmNm;
    }

    public void setClschmNm(String clschmNm) {
        this.clschmNm = clschmNm;
    }

    public String getRltinstId() {
        return rltinstId;
    }

    public void setRltinstId(String rltinstId) {
        this.rltinstId = rltinstId;
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

	public List<Map<String, Object>> getColInfoList() {
		return colInfoList;
	}

	public void setColInfoList(List<Map<String, Object>> colInfoList) {
		this.colInfoList = colInfoList;
	}

	public List<MetaColInfo> getMetaColInfoList() {
		return metaColInfoList;
	}

	public void setMetaColInfoList(List<MetaColInfo> metaColInfoList) {
		this.metaColInfoList = metaColInfoList;
	}

	public String getStrDataTypeArr() {
		return strDataTypeArr;
	}

	public void setStrDataTypeArr(String strDataTypeArr) {
		this.strDataTypeArr = strDataTypeArr;
	}

	public String getStrColEngNmArr() {
		return strColEngNmArr;
	}

	public void setStrColEngNmArr(String strColEngNmArr) {
		this.strColEngNmArr = strColEngNmArr;
	}

	public String getStrColKoreanNmArr() {
		return strColKoreanNmArr;
	}

	public void setStrColKoreanNmArr(String strColKoreanNmArr) {
		this.strColKoreanNmArr = strColKoreanNmArr;
	}

	public String getFileExstYn() {
		return fileExstYn;
	}

	public void setFileExstYn(String fileExstYn) {
		this.fileExstYn = fileExstYn;
	}
	
}
