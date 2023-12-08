package com.neighbor21.ggits.common.entity;
public class GbmsStation {
    private String    stationId;        //정류소 id
    private String    stationNm;        //정류소명칭
    private String    stationTp;        //정류소유형
    private String    centerYn;        //중앙차로여부
    private String    adminNm;        //관할관청
    private String    areaCode;        //인허가 지역
    private long    mapX;        //map x좌표
    private long    mapY;        //map y좌표
    private long    gpsX;        //gps x좌표
    private long    gpsY;        //gps y좌표
    private long    linkX;        //eb 링크상 x 좌표(경도)
    private long    linkY;        //eb 링크상 y 좌표(위도)
    private long    stLinkX;        //표준 링크상 x 좌표(경도)
    private long    stLinkY;        //표준 링크상 y 좌표(위도)
    private long    mobileNo;        //모바일번호
    private String    useYn;        //사용여부
    private String    ebLinkId;        //eb링크 id
    private String    ebStationId;        //eb 정류소 id
    private long    tmX;        //tm x 좌표
    private long    tmY;        //tm y 좌표
    private String    districtid;        //구역 id


  public String getStationId() {
    return stationId;
  }

  public void setStationId(String stationId) {
    this.stationId = stationId;
  }


  public String getStationNm() {
    return stationNm;
  }

  public void setStationNm(String stationNm) {
    this.stationNm = stationNm;
  }


  public String getStationTp() {
    return stationTp;
  }

  public void setStationTp(String stationTp) {
    this.stationTp = stationTp;
  }


  public String getCenterYn() {
    return centerYn;
  }

  public void setCenterYn(String centerYn) {
    this.centerYn = centerYn;
  }


  public String getAdminNm() {
    return adminNm;
  }

  public void setAdminNm(String adminNm) {
    this.adminNm = adminNm;
  }


  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }


  public long getMapX() {
    return mapX;
  }

  public void setMapX(long mapX) {
    this.mapX = mapX;
  }


  public long getMapY() {
    return mapY;
  }

  public void setMapY(long mapY) {
    this.mapY = mapY;
  }


  public long getGpsX() {
    return gpsX;
  }

  public void setGpsX(long gpsX) {
    this.gpsX = gpsX;
  }


  public long getGpsY() {
    return gpsY;
  }

  public void setGpsY(long gpsY) {
    this.gpsY = gpsY;
  }


  public long getLinkX() {
    return linkX;
  }

  public void setLinkX(long linkX) {
    this.linkX = linkX;
  }


  public long getLinkY() {
    return linkY;
  }

  public void setLinkY(long linkY) {
    this.linkY = linkY;
  }


  public long getStLinkX() {
    return stLinkX;
  }

  public void setStLinkX(long stLinkX) {
    this.stLinkX = stLinkX;
  }


  public long getStLinkY() {
    return stLinkY;
  }

  public void setStLinkY(long stLinkY) {
    this.stLinkY = stLinkY;
  }


  public long getMobileNo() {
    return mobileNo;
  }

  public void setMobileNo(long mobileNo) {
    this.mobileNo = mobileNo;
  }


  public String getUseYn() {
    return useYn;
  }

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }


  public String getEbLinkId() {
    return ebLinkId;
  }

  public void setEbLinkId(String ebLinkId) {
    this.ebLinkId = ebLinkId;
  }


  public String getEbStationId() {
    return ebStationId;
  }

  public void setEbStationId(String ebStationId) {
    this.ebStationId = ebStationId;
  }


  public long getTmX() {
    return tmX;
  }

  public void setTmX(long tmX) {
    this.tmX = tmX;
  }


  public long getTmY() {
    return tmY;
  }

  public void setTmY(long tmY) {
    this.tmY = tmY;
  }


  public String getDistrictid() {
    return districtid;
  }

  public void setDistrictid(String districtid) {
    this.districtid = districtid;
  }

}
