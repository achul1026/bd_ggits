package com.neighbor21.ggits.common.entity;
public class SpatialRefSys {
    private long    srid;        
    private String    authName;        
    private long    authSrid;        
    private String    srtext;        
    private String    proj4Text;        


  public long getSrid() {
    return srid;
  }

  public void setSrid(long srid) {
    this.srid = srid;
  }


  public String getAuthName() {
    return authName;
  }

  public void setAuthName(String authName) {
    this.authName = authName;
  }


  public long getAuthSrid() {
    return authSrid;
  }

  public void setAuthSrid(long authSrid) {
    this.authSrid = authSrid;
  }


  public String getSrtext() {
    return srtext;
  }

  public void setSrtext(String srtext) {
    this.srtext = srtext;
  }


  public String getProj4Text() {
    return proj4Text;
  }

  public void setProj4Text(String proj4Text) {
    this.proj4Text = proj4Text;
  }

}
