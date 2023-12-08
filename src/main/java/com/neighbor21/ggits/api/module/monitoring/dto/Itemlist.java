package com.neighbor21.ggits.api.module.monitoring.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Itemlist {
    private static Logger log = Logger.getLogger(Itemlist.class.getName());
    String isMulti = "";

    @JsonProperty("roadRank")
    String roadrank = "";
    @JsonProperty("routeId")
    String routeid = "";
    @JsonProperty("routeNm")
    String routenm = "";
    @JsonProperty("routeNo")
    String routeno = "";
    @JsonProperty("routeTp")
    String routetp = "";
    @JsonProperty("congGrade")
    String conggrade = "";
    @JsonProperty("linkId")
    String linkId = "";
    @JsonProperty("spd")
    String spd = "";
    @JsonProperty("vol")
    String vol = "";
    @JsonProperty("trvlTime")
    String trvlTime = "";
    @JsonProperty("linkLength")
    String linkLength = "";


    public void setIsMulti(String isMulti) {
        this.isMulti = isMulti;
    }

    public String getIsMulti() {
        return isMulti;
    }

    public void setRoadrank(String roadrank) {
        this.roadrank = roadrank;
    }

    public String getRoadrank() {
        return roadrank;
    }

    public void setRouteid(String routeid) {
        this.routeid = routeid;
    }

    public String getRouteid() {
        return routeid;
    }

    public void setRoutenm(String routenm) {
        this.routenm = routenm;
    }

    public String getRoutenm() {
        return routenm;
    }

    public void setRouteno(String routeno) {
        this.routeno = routeno;
    }

    public String getRouteno() {
        return routeno;
    }

    public void setRoutetp(String routetp) {
        this.routetp = routetp;
    }

    public String getRoutetp() {
        return routetp;
    }

    public String getConggrade() {
        return conggrade;
    }

    public void setConggrade(String conggrade) {
        this.conggrade = conggrade;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkid) {
        this.linkId = linkid;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getTrvlTime() {
        return trvlTime;
    }

    public void setTrvlTime(String trvlTime) {
        this.trvlTime = trvlTime;
    }

    public String getLinkLength() {
        return linkLength;
    }

    public void setLinkLength(String linkLength) {
        this.linkLength = linkLength;
    }
}