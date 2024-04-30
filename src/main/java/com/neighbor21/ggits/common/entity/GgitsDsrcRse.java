package com.neighbor21.ggits.common.entity;
public class GgitsDsrcRse {
    private String    rseid;        //rse 아이디
    private long    sendperiod;        //전송 단위
    private String    locationname;        //위치 명
    private String    ipaddress;        //ip 주소
    private long    rsemakeid;        //rse 제조사 아이디
    private long    rseoperid;        //rse 운영자 아이디
    private long    coordx;        //x 좌표
    private long    coordy;        //y 좌표
    private String    dsrcAreaCd;        //dsrc_area_cd


  public String getRseid() {
    return rseid;
  }

  public void setRseid(String rseid) {
    this.rseid = rseid;
  }


  public long getSendperiod() {
    return sendperiod;
  }

  public void setSendperiod(long sendperiod) {
    this.sendperiod = sendperiod;
  }


  public String getLocationname() {
    return locationname;
  }

  public void setLocationname(String locationname) {
    this.locationname = locationname;
  }


  public String getIpaddress() {
    return ipaddress;
  }

  public void setIpaddress(String ipaddress) {
    this.ipaddress = ipaddress;
  }


  public long getRsemakeid() {
    return rsemakeid;
  }

  public void setRsemakeid(long rsemakeid) {
    this.rsemakeid = rsemakeid;
  }


  public long getRseoperid() {
    return rseoperid;
  }

  public void setRseoperid(long rseoperid) {
    this.rseoperid = rseoperid;
  }


  public long getCoordx() {
    return coordx;
  }

  public void setCoordx(long coordx) {
    this.coordx = coordx;
  }


  public long getCoordy() {
    return coordy;
  }

  public void setCoordy(long coordy) {
    this.coordy = coordy;
  }


  public String getDsrcAreaCd() {
    return dsrcAreaCd;
  }

  public void setDsrcAreaCd(String dsrcAreaCd) {
    this.dsrcAreaCd = dsrcAreaCd;
  }

}
