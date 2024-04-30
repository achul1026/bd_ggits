package com.neighbor21.ggits.common.entity;
public class ScsEmrgVhclCrsrdVhclCur {
    private String    serviceid;        //서비스 id
    private long    intlcno;        //교차로번호
    private String    evno;        //차량 번호


  public String getServiceid() {
    return serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }


  public long getIntlcno() {
    return intlcno;
  }

  public void setIntlcno(long intlcno) {
    this.intlcno = intlcno;
  }


  public String getEvno() {
    return evno;
  }

  public void setEvno(String evno) {
    this.evno = evno;
  }

}
