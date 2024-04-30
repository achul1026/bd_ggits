package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgbisVehicle {
    private String    vehId;        //차량 ID
    private String    plateNo;        //차량번호
    private String    companyId;        //버스회사ID
    private String    vehType;        //차종
    private Timestamp    registDate;        //버스등록일자
    private Timestamp    expireDate;        //차령만료일자
    private Timestamp    regReportDate;        //등록신고일자
    private String    vehMake;        //제조회사
    private String    modelName;        //모델명
    private String    airconYn;        //냉방유무
    private String    useYn;        //사용유무
    private String    sidoCd;        //행정구역 ID
    private String    lowPlate;        //저상차량여부
    private Long    vehCapa;        //승차정원
    private String    adminNm;        //관할관청
    private String    companyNm;        //운행업체
    private String    deviceNo;        //단말기기기번호
    private String    cdmaNo;        //CDMA번호
    private String    gpsModInfo;        //GPS모듈정보
    private String    destDeviceYn;        //행선지표시기여부
    private String    inCarDevYn;        //차내안내기여부
    private String    remark;        //비고
    private String    areaCd;        //지역코드
    private Long    totalSeatCnt;        //총좌석수
    private Long pagingTotalCount;


  public String getVehId() {
    return vehId;
  }

  public void setVehId(String vehId) {
    this.vehId = vehId;
  }

  public String getPlateNo() {
    return plateNo;
  }

  public void setPlateNo(String plateNo) {
    this.plateNo = plateNo;
  }

  public String getCompanyId() {
    return companyId;
  }

  public void setCompanyId(String companyId) {
    this.companyId = companyId;
  }

  public String getVehType() {
    return vehType;
  }

  public void setVehType(String vehType) {
    this.vehType = vehType;
  }

  public Timestamp getRegistDate() {
    return registDate;
  }

  public void setRegistDate(Timestamp registDate) {
    this.registDate = registDate;
  }

  public Timestamp getExpireDate() {
    return expireDate;
  }

  public void setExpireDate(Timestamp expireDate) {
    this.expireDate = expireDate;
  }

  public Timestamp getRegReportDate() {
    return regReportDate;
  }

  public void setRegReportDate(Timestamp regReportDate) {
    this.regReportDate = regReportDate;
  }

  public String getVehMake() {
    return vehMake;
  }

  public void setVehMake(String vehMake) {
    this.vehMake = vehMake;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getAirconYn() {
    return airconYn;
  }

  public void setAirconYn(String airconYn) {
    this.airconYn = airconYn;
  }

  public String getUseYn() {
    return useYn;
  }

  public void setUseYn(String useYn) {
    this.useYn = useYn;
  }

  public String getSidoCd() {
    return sidoCd;
  }

  public void setSidoCd(String sidoCd) {
    this.sidoCd = sidoCd;
  }

  public String getLowPlate() {
    return lowPlate;
  }

  public void setLowPlate(String lowPlate) {
    this.lowPlate = lowPlate;
  }

  public Long getVehCapa() {
    return vehCapa;
  }

  public void setVehCapa(Long vehCapa) {
    this.vehCapa = vehCapa;
  }

  public String getAdminNm() {
    return adminNm;
  }

  public void setAdminNm(String adminNm) {
    this.adminNm = adminNm;
  }

  public String getCompanyNm() {
    return companyNm;
  }

  public void setCompanyNm(String companyNm) {
    this.companyNm = companyNm;
  }

  public String getDeviceNo() {
    return deviceNo;
  }

  public void setDeviceNo(String deviceNo) {
    this.deviceNo = deviceNo;
  }

  public String getCdmaNo() {
    return cdmaNo;
  }

  public void setCdmaNo(String cdmaNo) {
    this.cdmaNo = cdmaNo;
  }

  public String getGpsModInfo() {
    return gpsModInfo;
  }

  public void setGpsModInfo(String gpsModInfo) {
    this.gpsModInfo = gpsModInfo;
  }

  public String getDestDeviceYn() {
    return destDeviceYn;
  }

  public void setDestDeviceYn(String destDeviceYn) {
    this.destDeviceYn = destDeviceYn;
  }

  public String getInCarDevYn() {
    return inCarDevYn;
  }

  public void setInCarDevYn(String inCarDevYn) {
    this.inCarDevYn = inCarDevYn;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getAreaCd() {
    return areaCd;
  }

  public void setAreaCd(String areaCd) {
    this.areaCd = areaCd;
  }

  public Long getTotalSeatCnt() {
    return totalSeatCnt;
  }

  public void setTotalSeatCnt(Long totalSeatCnt) {
    this.totalSeatCnt = totalSeatCnt;
  }

  public Long getPagingTotalCount() {
    return pagingTotalCount;
  }

  public void setPagingTotalCount(Long pagingTotalCount) {
    this.pagingTotalCount = pagingTotalCount;
  }
}
