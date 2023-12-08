package com.neighbor21.ggits.common.entity;
public class ScsEmrgVhclCurInfo extends ScsEmrgVhclPathInfo{
    private String    serviceid;        
    private String    evno;        
    private double    currentlat;        
    private double    currentlng;        
    private long    speed;        


  public String getServiceid() {
    return serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }


  public String getEvno() {
    return evno;
  }

  public void setEvno(String evno) {
    this.evno = evno;
  }


  public double getCurrentlat() {
    return currentlat;
  }

  public void setCurrentlat(double currentlat) {
    this.currentlat = currentlat;
  }


  public double getCurrentlng() {
    return currentlng;
  }

  public void setCurrentlng(double currentlng) {
    this.currentlng = currentlng;
  }


  public long getSpeed() {
    return speed;
  }

  public void setSpeed(long speed) {
    this.speed = speed;
  }

}
