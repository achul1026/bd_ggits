package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;
import java.util.List;

//시설물 로그 정보
public class LTcDataLog extends CommonEntity{

    private String    	dsetId;        	//데이타셋 아이디
    private String    	jobId;        	//JOB 아이디
    private String    	etlDt;        	//ETL 일시
    private String    	jobNm;      	//JOB 명
    private String    	rltinstId;    	//유관기관아이디
    private String    	etlClsf;      	//ETL분류
    private Timestamp   clctStartDt;   	//수집시작일시
    private String    	clctEndDt;      //수집종료일시
    private String    	clctTbl;       	//수집테이블
    private String    	trgtTbl;       	//대상테이블
    private long    	clctDataCnt;   	//수집데이터수
    private String    	prgrsStts;     	//진행상태
    private String    	failRsn;     	//실패사유
    
    private String 		cdNm;			//유관기관명
    
    //parameter
    private String 		linkedType;		//연계기관 타입
    private List<String> linkedList; 	//연계기관 목록
    private String 		colTyCd;		//컬럼 미추가 수집상태 코드
    private String[]    colArr;			//수집상태코드 Array
    
    //#DsetInfo
    private String dataClctType; 		//데이터 수집유형코드

    public String getDsetId() {
		return dsetId;
	}
	public void setDsetId(String dsetId) {
		this.dsetId = dsetId;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getEtlDt() {
		return etlDt;
	}
	public void setEtlDt(String etlDt) {
		this.etlDt = etlDt;
	}
	public String getJobNm() {
		return jobNm;
	}
	public void setJobNm(String jobNm) {
		this.jobNm = jobNm;
	}
	public String getRltinstId() {
		return rltinstId;
	}
	public void setRltinstId(String rltinstId) {
		this.rltinstId = rltinstId;
	}
	public String getEtlClsf() {
		return etlClsf;
	}
	public void setEtlClsf(String etlClsf) {
		this.etlClsf = etlClsf;
	}
	public Timestamp getClctStartDt() {
		return clctStartDt;
	}
	public void setClctStartDt(Timestamp clctStartDt) {
		this.clctStartDt = clctStartDt;
	}
	public String getClctEndDt() {
		return clctEndDt;
	}
	public void setClctEndDt(String clctEndDt) {
		this.clctEndDt = clctEndDt;
	}
	public String getClctTbl() {
		return clctTbl;
	}
	public void setClctTbl(String clctTbl) {
		this.clctTbl = clctTbl;
	}
	public String getTrgtTbl() {
		return trgtTbl;
	}
	public void setTrgtTbl(String trgtTbl) {
		this.trgtTbl = trgtTbl;
	}
	public long getClctDataCnt() {
		return clctDataCnt;
	}
	public void setClctDataCnt(long clctDataCnt) {
		this.clctDataCnt = clctDataCnt;
	}
	public String getPrgrsStts() {
		return prgrsStts;
	}
	public void setPrgrsStts(String prgrsStts) {
		this.prgrsStts = prgrsStts;
	}
	public String getFailRsn() {
		return failRsn;
	}
	public void setFailRsn(String failRsn) {
		this.failRsn = failRsn;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getColTyCd() {
		return colTyCd;
	}
	public void setColTyCd(String colTyCd) {
		this.colTyCd = colTyCd;
	}
	public String getDataClctType() {
		return dataClctType;
	}
	public void setDataClctType(String dataClctType) {
		this.dataClctType = dataClctType;
	}
	public String[] getColArr() {
		return colArr;
	}
	public void setColArr(String[] colArr) {
		this.colArr = colArr;
	}
	public String getLinkedType() {
		return linkedType;
	}
	public void setLinkedType(String linkedType) {
		this.linkedType = linkedType;
	}
	public List<String> getLinkedList() {
		return linkedList;
	}
	public void setLinkedList(List<String> linkedList) {
		this.linkedList = linkedList;
	}
}
