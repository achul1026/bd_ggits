package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

//마스터 운영 코드
public class MOpCode extends CommonEntity{

  private String grpCdId; //그룹 코드 아이디
  private String cdId; //코드 아이디
  private String cdNm; //코드 명
  private String descr; //설명
  private String cdCond1Val; //코드 조건1 값
  private String cdCond2Val; //코드 조건2 값
  private String useYn; //사용 여부
  private long sortNo; //정렬 번호
  private String crtusrId; // 생성자 아이디
  private Timestamp crtDt; //등록 일시
  private String uptusrId; // 수정자 아이디
  private Timestamp updtDt; //수정 일시

  public MOpCode(String cdId) {
		super();
		this.cdId = cdId;
  }
  
  public MOpCode() {
		super();
  }
  
  public String getGrpCdId() {
    return grpCdId;
  }

  public void setGrpCdId(String grpCdId) {
    this.grpCdId = grpCdId;
  }


  public String getCdId() {
    return cdId;
  }

  public void setCdId(String cdId) {
    this.cdId = cdId;
  }


  public String getCdNm() {
    return cdNm;
  }

  public void setCdNm(String cdNm) {
    this.cdNm = cdNm;
  }


  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }


  public String getCdCond1Val() {
    return cdCond1Val;
  }

  public void setCdCond1Val(String cdCond1Val) {
    this.cdCond1Val = cdCond1Val;
  }


  public String getCdCond2Val() {
    return cdCond2Val;
  }

  public void setCdCond2Val(String cdCond2Val) {
    this.cdCond2Val = cdCond2Val;
  }


  public String getUseYn() {
    return useYn;
  }

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }


  public long getSortNo() {
    return sortNo;
  }

  public void setSortNo(long sortNo) {
    this.sortNo = sortNo;
  }
  
  public Timestamp getCrtDt() {
	return crtDt;
  }
	
  public void setCrtDt(Timestamp crtDt) {
	this.crtDt = crtDt;
  }

  public Timestamp getUpdtDt() {
    return updtDt;
  }

  public void setUpdtDt(Timestamp updtDt) {
    this.updtDt = updtDt;
  }
  
public String getCrtusrId() {
	return crtusrId;
}

public void setCrtusrId(String crtusrId) {
	this.crtusrId = crtusrId;
}

public String getUptusrId() {
	return uptusrId;
}

public void setUptusrId(String uptusrId) {
	this.uptusrId = uptusrId;
}
  

}
