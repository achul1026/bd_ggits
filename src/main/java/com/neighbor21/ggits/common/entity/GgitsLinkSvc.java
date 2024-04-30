package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsLinkSvc {
	private String linkId; // 링크 아이디
	private String startNodeId; // 시작 노드 id
	private String endNodeId; // 종료 노드 id
	private String linkDesc; // 비고
	private long linkLength; // 링크의 길이
	private long trafficsmthBasicTrvlspd; // 소통원활기준통행속도
	private long congestBasicTrvlspd; // 정체기준통행속도
	private String routeId; // 노선id
	private String routeWay; // 노선 방향
	private long routeSeq; // route_seq
	private long svcLvl; // svc_lvl
	private String roadGroup; // 도로 구분 코드
	private Timestamp updateDate; // 수정일시
	private String collectYn; // 데이터 수집/미수집 구간 여부
	private String importantNode; // 중요 노드

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

	public long getSvcLvl() {
		return svcLvl;
	}

	public void setSvcLvl(long svcLvl) {
		this.svcLvl = svcLvl;
	}

	public String getRoadGroup() {
		return roadGroup;
	}

	public void setRoadGroup(String roadGroup) {
		this.roadGroup = roadGroup;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public String getCollectYn() {
		return collectYn;
	}

	public void setCollectYn(String collectYn) {
		this.collectYn = collectYn;
	}

	public String getImportantNode() {
		return importantNode;
	}

	public void setImportantNode(String importantNode) {
		this.importantNode = importantNode;
	}

}
