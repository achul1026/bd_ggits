package com.neighbor21.ggits.common.entity;
public class ScsTConIntweek {
    private long    intLcno;        //교차로번호
    private long    weekNo;        //요일번호
    private String    updateDate;        //갱신일시
    private long    intWplan;        //주간계획번호


  public long getIntLcno() {
    return intLcno;
  }

  public void setIntLcno(long intLcno) {
    this.intLcno = intLcno;
  }


  public long getWeekNo() {
    return weekNo;
  }

  public void setWeekNo(long weekNo) {
    this.weekNo = weekNo;
  }


  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }


  public long getIntWplan() {
    return intWplan;
  }

  public void setIntWplan(long intWplan) {
    this.intWplan = intWplan;
  }

}
