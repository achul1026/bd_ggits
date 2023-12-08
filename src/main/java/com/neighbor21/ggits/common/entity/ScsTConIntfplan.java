package com.neighbor21.ggits.common.entity;
public class ScsTConIntfplan {
    private long    intLcno;        //교차로번호
    private long    intFplanindex;        //예약계획번호
    private String    updateDate;        //갱신일시
    private long    intRsrvmonth;        //예약월
    private long    intRsrvday;        //예약일
    private long    intRsrvwkd;        //예약요일
    private long    intCntlstarthour;        //제어시작시
    private long    intCntlstartmin;        //제어시작분
    private long    intCntlendhour;        //제어종료시
    private long    intCntlendmin;        //제어종료분
    private long    intCntltype;        //제어유형
    private long    intCntlspcno;        //시차제번호


  public long getIntLcno() {
    return intLcno;
  }

  public void setIntLcno(long intLcno) {
    this.intLcno = intLcno;
  }


  public long getIntFplanindex() {
    return intFplanindex;
  }

  public void setIntFplanindex(long intFplanindex) {
    this.intFplanindex = intFplanindex;
  }


  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }


  public long getIntRsrvmonth() {
    return intRsrvmonth;
  }

  public void setIntRsrvmonth(long intRsrvmonth) {
    this.intRsrvmonth = intRsrvmonth;
  }


  public long getIntRsrvday() {
    return intRsrvday;
  }

  public void setIntRsrvday(long intRsrvday) {
    this.intRsrvday = intRsrvday;
  }


  public long getIntRsrvwkd() {
    return intRsrvwkd;
  }

  public void setIntRsrvwkd(long intRsrvwkd) {
    this.intRsrvwkd = intRsrvwkd;
  }


  public long getIntCntlstarthour() {
    return intCntlstarthour;
  }

  public void setIntCntlstarthour(long intCntlstarthour) {
    this.intCntlstarthour = intCntlstarthour;
  }


  public long getIntCntlstartmin() {
    return intCntlstartmin;
  }

  public void setIntCntlstartmin(long intCntlstartmin) {
    this.intCntlstartmin = intCntlstartmin;
  }


  public long getIntCntlendhour() {
    return intCntlendhour;
  }

  public void setIntCntlendhour(long intCntlendhour) {
    this.intCntlendhour = intCntlendhour;
  }


  public long getIntCntlendmin() {
    return intCntlendmin;
  }

  public void setIntCntlendmin(long intCntlendmin) {
    this.intCntlendmin = intCntlendmin;
  }


  public long getIntCntltype() {
    return intCntltype;
  }

  public void setIntCntltype(long intCntltype) {
    this.intCntltype = intCntltype;
  }


  public long getIntCntlspcno() {
    return intCntlspcno;
  }

  public void setIntCntlspcno(long intCntlspcno) {
    this.intCntlspcno = intCntlspcno;
  }

}
