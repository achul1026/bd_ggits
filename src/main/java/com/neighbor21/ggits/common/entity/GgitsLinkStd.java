package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsLinkStd {
	private String linkId; // 링크 아이디
	private String startNodeId; // 시작노드 아이디
	private String endNodeId; // 종료노드 아이디
	private long laneCnt; // 일련번호
	private String roadRank; // 도로 등급
	private String roadTp; // 도로 종류
	private String roadNo; // 도로 번호
	private String roadNm; // 도로 명창
	private String roadUse; // 도로 사용여부
	private String mulpurSect; // 중용구간여부
	private String rampCd; // 램프 코드
	private long maxLmtspd; // 최고 제한 속도
	private String trvlRestricCar; // 통행제한차량
	private long trvlRestricLoad; // 통과제한 하중
	private long trvlRestricHeight; // 통과제한 높이
	private String linkDesc; // 비고
	private long linkLength; // 링크의 길이
	private long trafficsmthBasicTrvlspd; // 소통원활기준통행속도
	private long congestBasicTrvlspd; // 정체기준통행속도
	private long weight; // 패턴가중치
	private long linkSmthFactor; // 링크형활화계수

	private String prcYn; // prc_yn
	private String gitsYn; // gits 관련 yn
	private String routeId; // 노선id
	private String routeWay; // 노선 방향
	private long routeSeq; // route_seq
	private Timestamp updateDate; // 수정일시

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getStartNodeId() {
		return startNodeId;
	}

	public void setStartNodeId(String startNodeId) {
		this.startNodeId = startNodeId;
	}

	public String getEndNodeId() {
		return endNodeId;
	}

	public void setEndNodeId(String endNodeId) {
		this.endNodeId = endNodeId;
	}

	public long getLaneCnt() {
		return laneCnt;
	}

	public void setLaneCnt(long laneCnt) {
		this.laneCnt = laneCnt;
	}

	public String getRoadRank() {
		return roadRank;
	}

	public void setRoadRank(String roadRank) {
		this.roadRank = roadRank;
	}

	public String getRoadTp() {
		return roadTp;
	}

	public void setRoadTp(String roadTp) {
		this.roadTp = roadTp;
	}

	public String getRoadNo() {
		return roadNo;
	}

	public void setRoadNo(String roadNo) {
		this.roadNo = roadNo;
	}

	public String getRoadNm() {
		return roadNm;
	}

	public void setRoadNm(String roadNm) {
		this.roadNm = roadNm;
	}

	public String getRoadUse() {
		return roadUse;
	}

	public void setRoadUse(String roadUse) {
		this.roadUse = roadUse;
	}

	public String getMulpurSect() {
		return mulpurSect;
	}

	public void setMulpurSect(String mulpurSect) {
		this.mulpurSect = mulpurSect;
	}

	public String getRampCd() {
		return rampCd;
	}

	public void setRampCd(String rampCd) {
		this.rampCd = rampCd;
	}

	public long getMaxLmtspd() {
		return maxLmtspd;
	}

	public void setMaxLmtspd(long maxLmtspd) {
		this.maxLmtspd = maxLmtspd;
	}

	public String getTrvlRestricCar() {
		return trvlRestricCar;
	}

	public void setTrvlRestricCar(String trvlRestricCar) {
		this.trvlRestricCar = trvlRestricCar;
	}

	public long getTrvlRestricLoad() {
		return trvlRestricLoad;
	}

	public void setTrvlRestricLoad(long trvlRestricLoad) {
		this.trvlRestricLoad = trvlRestricLoad;
	}

	public long getTrvlRestricHeight() {
		return trvlRestricHeight;
	}

	public void setTrvlRestricHeight(long trvlRestricHeight) {
		this.trvlRestricHeight = trvlRestricHeight;
	}

	public String getLinkDesc() {
		return linkDesc;
	}

	public void setLinkDesc(String linkDesc) {
		this.linkDesc = linkDesc;
	}

	public long getLinkLength() {
		return linkLength;
	}

	public void setLinkLength(long linkLength) {
		this.linkLength = linkLength;
	}

	public long getTrafficsmthBasicTrvlspd() {
		return trafficsmthBasicTrvlspd;
	}

	public void setTrafficsmthBasicTrvlspd(long trafficsmthBasicTrvlspd) {
		this.trafficsmthBasicTrvlspd = trafficsmthBasicTrvlspd;
	}

	public long getCongestBasicTrvlspd() {
		return congestBasicTrvlspd;
	}

	public void setCongestBasicTrvlspd(long congestBasicTrvlspd) {
		this.congestBasicTrvlspd = congestBasicTrvlspd;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getLinkSmthFactor() {
		return linkSmthFactor;
	}

	public void setLinkSmthFactor(long linkSmthFactor) {
		this.linkSmthFactor = linkSmthFactor;
	}

	public String getPrcYn() {
		return prcYn;
	}

	public void setPrcYn(String prcYn) {
		this.prcYn = prcYn;
	}

	public String getGitsYn() {
		return gitsYn;
	}

	public void setGitsYn(String gitsYn) {
		this.gitsYn = gitsYn;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteWay() {
		return routeWay;
	}

	public void setRouteWay(String routeWay) {
		this.routeWay = routeWay;
	}

	public long getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(long routeSeq) {
		this.routeSeq = routeSeq;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

}
