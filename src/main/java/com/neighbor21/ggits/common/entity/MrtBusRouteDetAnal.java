package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class MrtBusRouteDetAnal {
    private Timestamp anlsDt;        //분석 일시
    private String busRouteId;        //버스 노선 아이디
    private String routeNm;        //노선 명
    private String rungType;        //운행 유형
    private Double routeLen;        //노선 길이
    private Long rungIntv;        //운행 간격
    private String addngCd;        //행정동 코드
    private Long totBstpCnt;        //전체 버스정류장 수
    private Long totUserCnt;        //전체 사용자 수
    private Double curvt;        //굴곡도
    private String etlDt;        //etl 일시

    private String stStaNm;        // 출발지명
    private String edStaNm;        // 도착지명
    private String districtSnm;
    private String districtGnm;
    private String routeTp;

    public Timestamp getAnlsDt() {
        return anlsDt;
    }

    public void setAnlsDt(Timestamp anlsDt) {
        this.anlsDt = anlsDt;
    }

    public String getBusRouteId() {
        return busRouteId;
    }

    public void setBusRouteId(String busRouteId) {
        this.busRouteId = busRouteId;
    }

    public String getRouteNm() {
        return routeNm;
    }

    public void setRouteNm(String routeNm) {
        this.routeNm = routeNm;
    }

    public String getRungType() {
        return rungType;
    }

    public void setRungType(String rungType) {
        this.rungType = rungType;
    }

    public Double getRouteLen() {
        return routeLen;
    }

    public void setRouteLen(Double routeLen) {
        this.routeLen = routeLen;
    }

    public Long getRungIntv() {
        return rungIntv;
    }

    public void setRungIntv(Long rungIntv) {
        this.rungIntv = rungIntv;
    }

    public String getAddngCd() {
        return addngCd;
    }

    public void setAddngCd(String addngCd) {
        this.addngCd = addngCd;
    }

    public Long getTotBstpCnt() {
        return totBstpCnt;
    }

    public void setTotBstpCnt(Long totBstpCnt) {
        this.totBstpCnt = totBstpCnt;
    }

    public Long getTotUserCnt() {
        return totUserCnt;
    }

    public void setTotUserCnt(Long totUserCnt) {
        this.totUserCnt = totUserCnt;
    }

    public Double getCurvt() {
        return curvt;
    }

    public void setCurvt(Double curvt) {
        this.curvt = curvt;
    }

    public String getEtlDt() {
        return etlDt;
    }

    public void setEtlDt(String etlDt) {
        this.etlDt = etlDt;
    }

    public String getStStaNm() {
        return stStaNm;
    }

    public void setStStaNm(String stStaNm) {
        this.stStaNm = stStaNm;
    }

    public String getEdStaNm() {
        return edStaNm;
    }

    public void setEdStaNm(String edStaNm) {
        this.edStaNm = edStaNm;
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

    public String getRouteTp() {
        return routeTp;
    }

    public void setRouteTp(String routeTp) {
        this.routeTp = routeTp;
    }
}
