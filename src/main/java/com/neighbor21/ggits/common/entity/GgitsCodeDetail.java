package com.neighbor21.ggits.common.entity;
public class GgitsCodeDetail {
    private String    cdTpId;        //코드구분id
    private String    cd;        //코드
    private long    seq;        //순번
    private String    cdNm;        //코드 명칭
    private String    cdBizRule1Desc;        //코드업무규칙1내역
    private String    cdBizRule2Desc;        //코드업무규칙2내역
    private String    cdBizRule3Desc;        //cd_biz_rule_3_desc


  public String getCdTpId() {
    return cdTpId;
  }

  public void setCdTpId(String cdTpId) {
    this.cdTpId = cdTpId;
  }


  public String getCd() {
    return cd;
  }

  public void setCd(String cd) {
    this.cd = cd;
  }


  public long getSeq() {
    return seq;
  }

  public void setSeq(long seq) {
    this.seq = seq;
  }


  public String getCdNm() {
    return cdNm;
  }

  public void setCdNm(String cdNm) {
    this.cdNm = cdNm;
  }


  public String getCdBizRule1Desc() {
    return cdBizRule1Desc;
  }

  public void setCdBizRule1Desc(String cdBizRule1Desc) {
    this.cdBizRule1Desc = cdBizRule1Desc;
  }


  public String getCdBizRule2Desc() {
    return cdBizRule2Desc;
  }

  public void setCdBizRule2Desc(String cdBizRule2Desc) {
    this.cdBizRule2Desc = cdBizRule2Desc;
  }


  public String getCdBizRule3Desc() {
    return cdBizRule3Desc;
  }

  public void setCdBizRule3Desc(String cdBizRule3Desc) {
    this.cdBizRule3Desc = cdBizRule3Desc;
  }

}
