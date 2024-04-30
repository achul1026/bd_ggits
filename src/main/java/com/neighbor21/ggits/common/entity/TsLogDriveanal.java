package com.neighbor21.ggits.common.entity;
public class TsLogDriveanal {
    private String    carNo;        
    private String    driveDtm;        
    private String    workDt;        
    private String    corpId;        
    private String    driverCode;        
    private String    routeNo;        
    private String    lon;        
    private String    lat;        
    private Long    rpm;
    private Long    spd;
    private String    overspd;        
    private Long    overspdOvdwSpd;
    private Long    overspdDuraTm;
    private String    quickAccel;        
    private Long    quickAccelOvdwSpd;
    private Long    quickAccelDuraTm;
    private String    quickDecel;        
    private Long    quickDecelOvdwSpd;
    private Long    quickDecelDuraTm;
    private String    quickStop;        
    private Long    quickStopOvdwSpd;
    private Long    quickStopDuraTm;
    private String    quickStart;        
    private Long    quickStartOvdwSpd;
    private Long    quickStartDuraTm;
    private String    rpmInf;        
    private String    idleYn;        
    private Long    idleynDuraTm;
    private Long    idleynDuraTm2;
    private Long    longOverspd;
    private String quickRouteChange;
    private String quickOvertake;
    private String quickLfrtTurn;
    private String quickUturn;

    private Long pagingTotalCnt;
    private Long quickAccelCnt;
    private Long quickDecelCnt;
    private Long quickStopCnt;
    private Long quickStartCnt;
    private Long quickRouteChangeCnt;
    private Long quickOvertakeCnt;
    private Long quickLfrtTurnCnt;
    private Long quickUturnCnt;
    private String routeNm;
    private String routeId;
    private String routeTp;
    private String districtGnm;
    private String districtSnm;

  public String getCarNo() {
    return carNo;
  }

  public void setCarNo(String carNo) {
    this.carNo = carNo;
  }

  public String getDriveDtm() {
    return driveDtm;
  }

  public void setDriveDtm(String driveDtm) {
    this.driveDtm = driveDtm;
  }

  public String getWorkDt() {
    return workDt;
  }

  public void setWorkDt(String workDt) {
    this.workDt = workDt;
  }

  public String getCorpId() {
    return corpId;
  }

  public void setCorpId(String corpId) {
    this.corpId = corpId;
  }

  public String getDriverCode() {
    return driverCode;
  }

  public void setDriverCode(String driverCode) {
    this.driverCode = driverCode;
  }

  public String getRouteNo() {
    return routeNo;
  }

  public void setRouteNo(String routeNo) {
    this.routeNo = routeNo;
  }

  public String getLon() {
    return lon;
  }

  public void setLon(String lon) {
    this.lon = lon;
  }

  public String getLat() {
    return lat;
  }

  public void setLat(String lat) {
    this.lat = lat;
  }

  public Long getRpm() {
    return rpm;
  }

  public void setRpm(Long rpm) {
    this.rpm = rpm;
  }

  public Long getSpd() {
    return spd;
  }

  public void setSpd(Long spd) {
    this.spd = spd;
  }

  public String getOverspd() {
    return overspd;
  }

  public void setOverspd(String overspd) {
    this.overspd = overspd;
  }

  public Long getOverspdOvdwSpd() {
    return overspdOvdwSpd;
  }

  public void setOverspdOvdwSpd(Long overspdOvdwSpd) {
    this.overspdOvdwSpd = overspdOvdwSpd;
  }

  public Long getOverspdDuraTm() {
    return overspdDuraTm;
  }

  public void setOverspdDuraTm(Long overspdDuraTm) {
    this.overspdDuraTm = overspdDuraTm;
  }

  public String getQuickAccel() {
    return quickAccel;
  }

  public void setQuickAccel(String quickAccel) {
    this.quickAccel = quickAccel;
  }

  public Long getQuickAccelOvdwSpd() {
    return quickAccelOvdwSpd;
  }

  public void setQuickAccelOvdwSpd(Long quickAccelOvdwSpd) {
    this.quickAccelOvdwSpd = quickAccelOvdwSpd;
  }

  public Long getQuickAccelDuraTm() {
    return quickAccelDuraTm;
  }

  public void setQuickAccelDuraTm(Long quickAccelDuraTm) {
    this.quickAccelDuraTm = quickAccelDuraTm;
  }

  public String getQuickDecel() {
    return quickDecel;
  }

  public void setQuickDecel(String quickDecel) {
    this.quickDecel = quickDecel;
  }

  public Long getQuickDecelOvdwSpd() {
    return quickDecelOvdwSpd;
  }

  public void setQuickDecelOvdwSpd(Long quickDecelOvdwSpd) {
    this.quickDecelOvdwSpd = quickDecelOvdwSpd;
  }

  public Long getQuickDecelDuraTm() {
    return quickDecelDuraTm;
  }

  public void setQuickDecelDuraTm(Long quickDecelDuraTm) {
    this.quickDecelDuraTm = quickDecelDuraTm;
  }

  public String getQuickStop() {
    return quickStop;
  }

  public void setQuickStop(String quickStop) {
    this.quickStop = quickStop;
  }

  public Long getQuickStopOvdwSpd() {
    return quickStopOvdwSpd;
  }

  public void setQuickStopOvdwSpd(Long quickStopOvdwSpd) {
    this.quickStopOvdwSpd = quickStopOvdwSpd;
  }

  public Long getQuickStopDuraTm() {
    return quickStopDuraTm;
  }

  public void setQuickStopDuraTm(Long quickStopDuraTm) {
    this.quickStopDuraTm = quickStopDuraTm;
  }

  public String getQuickStart() {
    return quickStart;
  }

  public void setQuickStart(String quickStart) {
    this.quickStart = quickStart;
  }

  public Long getQuickStartOvdwSpd() {
    return quickStartOvdwSpd;
  }

  public void setQuickStartOvdwSpd(Long quickStartOvdwSpd) {
    this.quickStartOvdwSpd = quickStartOvdwSpd;
  }

  public Long getQuickStartDuraTm() {
    return quickStartDuraTm;
  }

  public void setQuickStartDuraTm(Long quickStartDuraTm) {
    this.quickStartDuraTm = quickStartDuraTm;
  }

  public String getRpmInf() {
    return rpmInf;
  }

  public void setRpmInf(String rpmInf) {
    this.rpmInf = rpmInf;
  }

  public String getIdleYn() {
    return idleYn;
  }

  public void setIdleYn(String idleYn) {
    this.idleYn = idleYn;
  }

  public Long getIdleynDuraTm() {
    return idleynDuraTm;
  }

  public void setIdleynDuraTm(Long idleynDuraTm) {
    this.idleynDuraTm = idleynDuraTm;
  }

  public Long getIdleynDuraTm2() {
    return idleynDuraTm2;
  }

  public void setIdleynDuraTm2(Long idleynDuraTm2) {
    this.idleynDuraTm2 = idleynDuraTm2;
  }

  public Long getLongOverspd() {
    return longOverspd;
  }

  public void setLongOverspd(Long longOverspd) {
    this.longOverspd = longOverspd;
  }

  public Long getQuickAccelCnt() {
    return quickAccelCnt;
  }

  public void setQuickAccelCnt(Long quickAccelCnt) {
    this.quickAccelCnt = quickAccelCnt;
  }

  public Long getQuickDecelCnt() {
    return quickDecelCnt;
  }

  public void setQuickDecelCnt(Long quickDecelCnt) {
    this.quickDecelCnt = quickDecelCnt;
  }

  public Long getQuickStopCnt() {
    return quickStopCnt;
  }

  public void setQuickStopCnt(Long quickStopCnt) {
    this.quickStopCnt = quickStopCnt;
  }

  public Long getQuickStartCnt() {
    return quickStartCnt;
  }

  public void setQuickStartCnt(Long quickStartCnt) {
    this.quickStartCnt = quickStartCnt;
  }

  public Long getQuickRouteChangeCnt() {
    return quickRouteChangeCnt;
  }

  public void setQuickRouteChangeCnt(Long quickRouteChangeCnt) {
    this.quickRouteChangeCnt = quickRouteChangeCnt;
  }

  public Long getQuickOvertakeCnt() {
    return quickOvertakeCnt;
  }

  public void setQuickOvertakeCnt(Long quickOvertakeCnt) {
    this.quickOvertakeCnt = quickOvertakeCnt;
  }

  public Long getQuickLfrtTurnCnt() {
    return quickLfrtTurnCnt;
  }

  public void setQuickLfrtTurnCnt(Long quickLfrtTurnCnt) {
    this.quickLfrtTurnCnt = quickLfrtTurnCnt;
  }

  public Long getQuickUturnCnt() {
    return quickUturnCnt;
  }

  public void setQuickUturnCnt(Long quickUturnCnt) {
    this.quickUturnCnt = quickUturnCnt;
  }

  public String getRouteNm() {
    return routeNm;
  }

  public void setRouteNm(String routeNm) {
    this.routeNm = routeNm;
  }

  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }

  public String getRouteTp() {
    return routeTp;
  }

  public void setRouteTp(String routeTp) {
    this.routeTp = routeTp;
  }

  public String getDistrictGnm() {
    return districtGnm;
  }

  public void setDistrictGnm(String districtGnm) {
    this.districtGnm = districtGnm;
  }

  public String getDistrictSnm() {
    return districtSnm;
  }

  public void setDistrictSnm(String districtSnm) {
    this.districtSnm = districtSnm;
  }

  public Long getPagingTotalCnt() {
    return pagingTotalCnt;
  }

  public void setPagingTotalCnt(Long pagingTotalCnt) {
    this.pagingTotalCnt = pagingTotalCnt;
  }

  public String getQuickRouteChange() {
    return quickRouteChange;
  }

  public void setQuickRouteChange(String quickRouteChange) {
    this.quickRouteChange = quickRouteChange;
  }

  public String getQuickOvertake() {
    return quickOvertake;
  }

  public void setQuickOvertake(String quickOvertake) {
    this.quickOvertake = quickOvertake;
  }

  public String getQuickLfrtTurn() {
    return quickLfrtTurn;
  }

  public void setQuickLfrtTurn(String quickLfrtTurn) {
    this.quickLfrtTurn = quickLfrtTurn;
  }

  public String getQuickUturn() {
    return quickUturn;
  }

  public void setQuickUturn(String quickUturn) {
    this.quickUturn = quickUturn;
  }
}
