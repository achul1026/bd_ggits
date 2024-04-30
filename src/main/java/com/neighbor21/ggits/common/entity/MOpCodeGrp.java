package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

// 마스터 운영 코드 그룹
public class MOpCodeGrp extends CommonEntity {

  private String grpCdId; // 그룹 코드 아이디
  private String grpCdNm; // 그룹 코드 명
  private String upperGrpCdId; // 상위 그룹 코드 아이디
  private String cdCond1Descr; // 코드 조건1 설명
  private String cdCond2Descr; // 코드 조건2 설명
  private String descr; // 설명
  private String useYn; // 사용 여부
  private String crtusrId; // 생성자 아이디
  private Timestamp crtDt; // 생성 일시
  private String uptusrId; // 수정자 아이디
  private Timestamp updtDt; // 수정 일시

  public String getGrpCdId() {
    return grpCdId;
  }

  public void setGrpCdId(String grpCdId) {
    this.grpCdId = grpCdId;
  }


  public String getGrpCdNm() {
    return grpCdNm;
  }

  public void setGrpCdNm(String grpCdNm) {
    this.grpCdNm = grpCdNm;
  }


  public String getUpperGrpCdId() {
    return upperGrpCdId;
  }

  public void setUpperGrpCdId(String upperGrpCdId) {
    this.upperGrpCdId = upperGrpCdId;
  }


  public String getCdCond1Descr() {
    return cdCond1Descr;
  }

  public void setCdCond1Descr(String cdCond1Descr) {
    this.cdCond1Descr = cdCond1Descr;
  }


  public String getCdCond2Descr() {
    return cdCond2Descr;
  }

  public void setCdCond2Descr(String cdCond2Descr) {
    this.cdCond2Descr = cdCond2Descr;
  }


  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }

  @Override
  public String getUseYn() {
    return useYn;
  }

  @Override
  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }

  public String getCrtusrId() {
    return crtusrId;
  }

  public void setCrtusrId(String crtusrId) {
    this.crtusrId = crtusrId;
  }

  public Timestamp getCrtDt() {
    return crtDt;
  }

  public void setCrtDt(Timestamp crtDt) {
    this.crtDt = crtDt;
  }

  public String getUptusrId() {
    return uptusrId;
  }

  public void setUptusrId(String uptusrId) {
    this.uptusrId = uptusrId;
  }

  public Timestamp getUpdtDt() {
    return updtDt;
  }

  public void setUpdtDt(Timestamp updtDt) {
    this.updtDt = updtDt;
  }
}
