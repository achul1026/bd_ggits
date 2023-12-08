package com.neighbor21.ggits.common.entity;
public class ScsTConIntlc {
    private long    intLcno;        //교차로번호
    private String    updateDate;        //갱신일시
    private String    intName;        //교차로명
    private long    intType;        //교차로유형
    private long    intLctype;        //제어기유형
    private long    intLamptype;        //램프타입
    private long    mainLcno;        //주요교차로번호
    private long    saNo;        //그룹번호
    private String    intNodeId;        //교차로 노드id
    private double    intLng;        //교차로경도
    private double    intLat;        //교차로위도
    private long    ppcType;        //ppc타입


  public long getIntLcno() {
    return intLcno;
  }

  public void setIntLcno(long intLcno) {
    this.intLcno = intLcno;
  }


  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }


  public String getIntName() {
    return intName;
  }

  public void setIntName(String intName) {
    this.intName = intName;
  }


  public long getIntType() {
    return intType;
  }

  public void setIntType(long intType) {
    this.intType = intType;
  }


  public long getIntLctype() {
    return intLctype;
  }

  public void setIntLctype(long intLctype) {
    this.intLctype = intLctype;
  }


  public long getIntLamptype() {
    return intLamptype;
  }

  public void setIntLamptype(long intLamptype) {
    this.intLamptype = intLamptype;
  }


  public long getMainLcno() {
    return mainLcno;
  }

  public void setMainLcno(long mainLcno) {
    this.mainLcno = mainLcno;
  }


  public long getSaNo() {
    return saNo;
  }

  public void setSaNo(long saNo) {
    this.saNo = saNo;
  }


  public String getIntNodeId() {
    return intNodeId;
  }

  public void setIntNodeId(String intNodeId) {
    this.intNodeId = intNodeId;
  }


  public double getIntLng() {
    return intLng;
  }

  public void setIntLng(double intLng) {
    this.intLng = intLng;
  }


  public double getIntLat() {
    return intLat;
  }

  public void setIntLat(double intLat) {
    this.intLat = intLat;
  }


  public long getPpcType() {
    return ppcType;
  }

  public void setPpcType(long ppcType) {
    this.ppcType = ppcType;
  }

}
