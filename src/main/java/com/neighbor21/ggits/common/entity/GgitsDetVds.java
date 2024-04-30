package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsDetVds {
	private String detId; // 검지기 id
	private String spotId; // spot_id
	private long paramId; // 운영파라미터 id
	private String ctrlId; // 제어기 id
	private String imgCamId; // 영상검지카메라 id
	private long instLane; // 설치차선
	private long lanePos; // lane_pos
	private String detKind; // 검지기 종류
	private String pseudoYn; // 가상검지기 여부
	private String upDetId; // 상류검지기 id
	private String dnDetId; // 하류검지기 id
	private long upDetDist; // 상류검지기와 거리
	private long dnDetDist; // 하류검지기와 거리
	private String upUseYn; // 상류검지기 사용여부
	private String dnUseYn; // 하류검지기 사용여부
	private String model; // 모델명
	private String instYear; // 설치년도
	private Timestamp lastUpdated; // 최종변경일자
	private long limSpd; // lim_spd
	private long desId; // des_id
	private long apidId; // apid_id
	private long mcmaId; // mcma_id
	private String spotProcYn; // spot_proc_yn
	private long piId; // 성능지표 id

	public String getDetId() {
		return detId;
	}

	public void setDetId(String detId) {
		this.detId = detId;
	}

	public String getSpotId() {
		return spotId;
	}

	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}

	public long getParamId() {
		return paramId;
	}

	public void setParamId(long paramId) {
		this.paramId = paramId;
	}

	public String getCtrlId() {
		return ctrlId;
	}

	public void setCtrlId(String ctrlId) {
		this.ctrlId = ctrlId;
	}

	public String getImgCamId() {
		return imgCamId;
	}

	public void setImgCamId(String imgCamId) {
		this.imgCamId = imgCamId;
	}

	public long getInstLane() {
		return instLane;
	}

	public void setInstLane(long instLane) {
		this.instLane = instLane;
	}

	public long getLanePos() {
		return lanePos;
	}

	public void setLanePos(long lanePos) {
		this.lanePos = lanePos;
	}

	public String getDetKind() {
		return detKind;
	}

	public void setDetKind(String detKind) {
		this.detKind = detKind;
	}

	public String getPseudoYn() {
		return pseudoYn;
	}

	public void setPseudoYn(String pseudoYn) {
		this.pseudoYn = pseudoYn;
	}

	public String getUpDetId() {
		return upDetId;
	}

	public void setUpDetId(String upDetId) {
		this.upDetId = upDetId;
	}

	public String getDnDetId() {
		return dnDetId;
	}

	public void setDnDetId(String dnDetId) {
		this.dnDetId = dnDetId;
	}

	public long getUpDetDist() {
		return upDetDist;
	}

	public void setUpDetDist(long upDetDist) {
		this.upDetDist = upDetDist;
	}

	public long getDnDetDist() {
		return dnDetDist;
	}

	public void setDnDetDist(long dnDetDist) {
		this.dnDetDist = dnDetDist;
	}

	public String getUpUseYn() {
		return upUseYn;
	}

	public void setUpUseYn(String upUseYn) {
		this.upUseYn = upUseYn;
	}

	public String getDnUseYn() {
		return dnUseYn;
	}

	public void setDnUseYn(String dnUseYn) {
		this.dnUseYn = dnUseYn;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getInstYear() {
		return instYear;
	}

	public void setInstYear(String instYear) {
		this.instYear = instYear;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public long getLimSpd() {
		return limSpd;
	}

	public void setLimSpd(long limSpd) {
		this.limSpd = limSpd;
	}

	public long getDesId() {
		return desId;
	}

	public void setDesId(long desId) {
		this.desId = desId;
	}

	public long getApidId() {
		return apidId;
	}

	public void setApidId(long apidId) {
		this.apidId = apidId;
	}

	public long getMcmaId() {
		return mcmaId;
	}

	public void setMcmaId(long mcmaId) {
		this.mcmaId = mcmaId;
	}

	public String getSpotProcYn() {
		return spotProcYn;
	}

	public void setSpotProcYn(String spotProcYn) {
		this.spotProcYn = spotProcYn;
	}

	public long getPiId() {
		return piId;
	}

	public void setPiId(long piId) {
		this.piId = piId;
	}

}
