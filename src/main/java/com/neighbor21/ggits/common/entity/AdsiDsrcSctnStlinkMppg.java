package com.neighbor21.ggits.common.entity;

//dsrc 구간 표준링크 매핑
public class AdsiDsrcSctnStlinkMppg extends GyCommInfoLink {

    private String mngInstCd; //관리 기관 코드
    private String dsrcSctnId; //dsrc 구간 아이디
    private long sqno; //순번
    private String stdLinkId; //표준 링크 아이디

    private String dsrcSctnNm;
    private String sqNo;
    private Double speed;
    private String geojson;


    public String getMngInstCd() {
        return mngInstCd;
    }

    public void setMngInstCd(String mngInstCd) {
        this.mngInstCd = mngInstCd;
    }


    public String getDsrcSctnId() {
        return dsrcSctnId;
    }

    public void setDsrcSctnId(String dsrcSctnId) {
        this.dsrcSctnId = dsrcSctnId;
    }


    public long getSqno() {
        return sqno;
    }

    public void setSqno(long sqno) {
        this.sqno = sqno;
    }


    public String getStdLinkId() {
        return stdLinkId;
    }

    public void setStdLinkId(String stdLinkId) {
        this.stdLinkId = stdLinkId;
    }

    public String getDsrcSctnNm() {
        return dsrcSctnNm;
    }

    public void setDsrcSctnNm(String dsrcSctnNm) {
        this.dsrcSctnNm = dsrcSctnNm;
    }

    public String getSqNo() {
        return sqNo;
    }

    public void setSqNo(String sqNo) {
        this.sqNo = sqNo;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }
}
