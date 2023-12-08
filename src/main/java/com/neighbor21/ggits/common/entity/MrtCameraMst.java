package com.neighbor21.ggits.common.entity;
public class MrtCameraMst {
    private String    mngInstCd;        //관리 기관 코드
    private String    cameraId;        //카메라 아이디
    private String    cameraNm;        //카메라 명
    private long    lonCrdn;        //경도 좌표
    private long    latCrdn;        //위도 좌표
    private String    crsrdId;        //교차로 아이디
    private String    acsRoadId;        //접근 도로 아이디
    private String    etlDt;        //ETL 일시


  public String getMngInstCd() {
    return mngInstCd;
  }

  public void setMngInstCd(String mngInstCd) {
    this.mngInstCd = mngInstCd;
  }


  public String getCameraId() {
    return cameraId;
  }

  public void setCameraId(String cameraId) {
    this.cameraId = cameraId;
  }


  public String getCameraNm() {
    return cameraNm;
  }

  public void setCameraNm(String cameraNm) {
    this.cameraNm = cameraNm;
  }


  public long getLonCrdn() {
    return lonCrdn;
  }

  public void setLonCrdn(long lonCrdn) {
    this.lonCrdn = lonCrdn;
  }


  public long getLatCrdn() {
    return latCrdn;
  }

  public void setLatCrdn(long latCrdn) {
    this.latCrdn = latCrdn;
  }


  public String getCrsrdId() {
    return crsrdId;
  }

  public void setCrsrdId(String crsrdId) {
    this.crsrdId = crsrdId;
  }


  public String getAcsRoadId() {
    return acsRoadId;
  }

  public void setAcsRoadId(String acsRoadId) {
    this.acsRoadId = acsRoadId;
  }


  public String getEtlDt() {
    return etlDt;
  }

  public void setEtlDt(String etlDt) {
    this.etlDt = etlDt;
  }

}
