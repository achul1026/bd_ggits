package com.neighbor21.ggits.common.entity;
public class GgbisAllocationPlan {
    private String    routeId;        //노선 ID
    private String    operWeek;        //운행요일
    private String    companyId;        //버스회사ID
    private String    upFirstTime;        //상행첫차시각
    private String    upLastTime;        //상행막차시각
    private String    downFirstTime;        //하행첫차시각
    private String    downLastTime;        //하행막차시각
    private long    laptimeMin;        //1회운행시간_최소
    private long    laptimeMax;        //1회운행시간_최대
    private long    workFrequency;        //운행횟수2
    private long    minHead;        //최소배차간격
    private long    maxHead;        //최대배차간격
    private long    peekAlloc;        //첨두시간대
    private long    npeekAlloc;        //비첨두시간대
    private long    nightAlloc;        //심야시간
    private long    rouCount;        //운행횟수
    private long    permVol;        //인가대수
    private String    beginDate;        //운행개시일
    private String    closeDate;        //운행종료일
    private String    remark;        //비고
    private String    companyNm;        //회사명
    private String    ebRouteId;        //EB노선 ID


  public String getRouteId() {
    return routeId;
  }

  public void setRouteId(String routeId) {
    this.routeId = routeId;
  }


  public String getOperWeek() {
    return operWeek;
  }

  public void setOperWeek(String operWeek) {
    this.operWeek = operWeek;
  }


  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }


  public String getUpFirstTime() {
    return upFirstTime;
  }

  public void setUpFirstTime(String upFirstTime) {
    this.upFirstTime = upFirstTime;
  }


  public String getUpLastTime() {
    return upLastTime;
  }

  public void setUpLastTime(String upLastTime) {
    this.upLastTime = upLastTime;
  }


  public String getDownFirstTime() {
    return downFirstTime;
  }

  public void setDownFirstTime(String downFirstTime) {
    this.downFirstTime = downFirstTime;
  }


  public String getDownLastTime() {
    return downLastTime;
  }

  public void setDownLastTime(String downLastTime) {
    this.downLastTime = downLastTime;
  }


  public long getLaptimeMin() {
    return laptimeMin;
  }

  public void setLaptimeMin(long laptimeMin) {
    this.laptimeMin = laptimeMin;
  }


  public long getLaptimeMax() {
    return laptimeMax;
  }

  public void setLaptimeMax(long laptimeMax) {
    this.laptimeMax = laptimeMax;
  }


  public long getWorkFrequency() {
    return workFrequency;
  }

  public void setWorkFrequency(long workFrequency) {
    this.workFrequency = workFrequency;
  }


  public long getMinHead() {
    return minHead;
  }

  public void setMinHead(long minHead) {
    this.minHead = minHead;
  }


  public long getMaxHead() {
    return maxHead;
  }

  public void setMaxHead(long maxHead) {
    this.maxHead = maxHead;
  }


  public long getPeekAlloc() {
    return peekAlloc;
  }

  public void setPeekAlloc(long peekAlloc) {
    this.peekAlloc = peekAlloc;
  }


  public long getNpeekAlloc() {
    return npeekAlloc;
  }

  public void setNpeekAlloc(long npeekAlloc) {
    this.npeekAlloc = npeekAlloc;
  }


  public long getNightAlloc() {
    return nightAlloc;
  }

  public void setNightAlloc(long nightAlloc) {
    this.nightAlloc = nightAlloc;
  }


  public long getRouCount() {
    return rouCount;
  }

  public void setRouCount(long rouCount) {
    this.rouCount = rouCount;
  }


  public long getPermVol() {
    return permVol;
  }

  public void setPermVol(long permVol) {
    this.permVol = permVol;
  }


  public String getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(String beginDate) {
    this.beginDate = beginDate;
  }


  public String getCloseDate() {
    return closeDate;
  }

  public void setCloseDate(String closeDate) {
    this.closeDate = closeDate;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getCompanyNm() {
    return companyNm;
  }

  public void setCompanyNm(String companyNm) {
    this.companyNm = companyNm;
  }


  public String getEbRouteId() {
    return ebRouteId;
  }

  public void setEbRouteId(String ebRouteId) {
    this.ebRouteId = ebRouteId;
  }

}
