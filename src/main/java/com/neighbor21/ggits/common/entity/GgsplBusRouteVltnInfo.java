package com.neighbor21.ggits.common.entity;

import java.util.List;

/**
 * @author : Charles KimO
 * @fileName :  GgsplBusRouteVltnInfo
 * @since : 2024-02-21
 */
public class GgsplBusRouteVltnInfo extends CommonEntity {
    String clctDt;
    String vhclId;
    String routeId;
    String sttsCd;
    String etlDt;

    String adminNm;
    String routeNm;
    String plateNo;
    String routeTp;
    String companyId;
    String companyNm;
    String districtSnm;
    String districtGnm;

    List<String> routeTpList;

    public String getClctDt() {
        return clctDt;
    }

    public void setClctDt(String clctDt) {
        this.clctDt = clctDt;
    }

    public String getVhclId() {
        return vhclId;
    }

    public void setVhclId(String vhclId) {
        this.vhclId = vhclId;
    }

    @Override
    public String getRouteId() {
        return routeId;
    }

    @Override
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getSttsCd() {
        return sttsCd;
    }

    public void setSttsCd(String sttsCd) {
        this.sttsCd = sttsCd;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public String getRouteNm() {
        return routeNm;
    }

    public void setRouteNm(String routeNm) {
        this.routeNm = routeNm;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    @Override
    public String getRouteTp() {
        return routeTp;
    }

    @Override
    public void setRouteTp(String routeTp) {
        this.routeTp = routeTp;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public String getAdminNm() {
        return adminNm;
    }

    public void setAdminNm(String adminNm) {
        this.adminNm = adminNm;
    }

    public String getDistrictSnm() {
        return districtSnm;
    }

    public void setDistrictSnm(String districtSnm) {
        this.districtSnm = districtSnm;
    }

    public String getDistrictGnm() {
        return districtGnm;
    }

    public void setDistrictGnm(String districtGnm) {
        this.districtGnm = districtGnm;
    }

    public List<String> getRouteTpList() {
        return routeTpList;
    }

    public void setRouteTpList(List<String> routeTpList) {
        this.routeTpList = routeTpList;
    }
}
