package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class GgitsInciCheck {
	private String regSeq; // 돌발등록번호
	private String routeId; // 노선id
	private String sensCd; // 감지방법(돌발종류 : 자동돌발, 제보돌발)
	private long inciStatus; // 돌발상황상태코드
	private Timestamp confirmDate; // 확인일시=>감지시간
	private Timestamp startDate; // 시작일시=>대응시간,시작시간
	private Timestamp estEndDate; // 돌발종료예정일시=>종료예정시간
	private Timestamp endDate; // 종료일시
	private String spotId; // 지점id
	private String autoinciAlgolyzm; // 자동돌발알고리즘
	private String reportName; // 제보자이름
	private String reportPhone; // 제보자연락처
	private String serialNo; // 생성방법 : 돌발상황감지일자 + 일련번호
	private String evntName; // 이벤트 명
	private long estDelay; // 지체예상시간
	private String externFlag; // 외부제공여부
	private String inciType; // 돌발유형 대표코드
	private String inciSubtype; // 돌발유형 세부코드
	private String collMethod1; // 자료수집방법1
	private String collMethod2; // 자료수집방법2
	private String restrictType; // 통제유형
	private String closeLaneType; // 폐쇄차로유형
	private String trafCongest; // 차량통행량=>첨두시,비첨두시
	private String ampm; // 오전오후
	private String isInciView; // 현장확인가능
	private long queueLength; // 대기행렬길이
	private long inciLane; // 돌발차선
	private long closeLane; // 폐쇄차로
	private long relCars; // 관련차량대수
	private long relTrucks; // 관련 트럭대수
	private long injuryPersons; // 부상자수
	private long deadPersons; // 사망자수
	private String weather; // 날씨
	private String decSeverityLevel; // 디시전트리 심각도
	private String useSeverityLevel; // 사용자대응 심각도
	private long decEstDelay; // 디시전트리 지체 예상시간
	private String modified; // 수정여부
	private String userId; // 사용자 id
	private String inciDesc; // 돌발설명
	private String inciPlaceManualIn; // 돌발 지점 수동 입력
	private String inciPlaceManualIn2; // 돌발 지점 수동 입력2 (vms 종점)
	private String externWeb; // 외부정보제공 인터넷
	private String externRelat; // 외부정보제공 외부연계
	private String externVms; // 외부정보제공 vms
	private long spd; // 속도
	private long vol; // 교통량
	private String roadStatus; // 노면상태
	private String inciMapImg; // 돌발 지도 이미지
	private String inciAreaCd; // 돌발 발생지역 코드
	private long inciX; // 돌발 위치 x 좌표
	private long inciY; // 돌발 위치 y 좌표

	public String getRegSeq() {
		return regSeq;
	}

	public void setRegSeq(String regSeq) {
		this.regSeq = regSeq;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getSensCd() {
		return sensCd;
	}

	public void setSensCd(String sensCd) {
		this.sensCd = sensCd;
	}

	public long getInciStatus() {
		return inciStatus;
	}

	public void setInciStatus(long inciStatus) {
		this.inciStatus = inciStatus;
	}

	public Timestamp getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Timestamp confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEstEndDate() {
		return estEndDate;
	}

	public void setEstEndDate(Timestamp estEndDate) {
		this.estEndDate = estEndDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public String getSpotId() {
		return spotId;
	}

	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}

	public String getAutoinciAlgolyzm() {
		return autoinciAlgolyzm;
	}

	public void setAutoinciAlgolyzm(String autoinciAlgolyzm) {
		this.autoinciAlgolyzm = autoinciAlgolyzm;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getReportPhone() {
		return reportPhone;
	}

	public void setReportPhone(String reportPhone) {
		this.reportPhone = reportPhone;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getEvntName() {
		return evntName;
	}

	public void setEvntName(String evntName) {
		this.evntName = evntName;
	}

	public long getEstDelay() {
		return estDelay;
	}

	public void setEstDelay(long estDelay) {
		this.estDelay = estDelay;
	}

	public String getExternFlag() {
		return externFlag;
	}

	public void setExternFlag(String externFlag) {
		this.externFlag = externFlag;
	}

	public String getInciType() {
		return inciType;
	}

	public void setInciType(String inciType) {
		this.inciType = inciType;
	}

	public String getInciSubtype() {
		return inciSubtype;
	}

	public void setInciSubtype(String inciSubtype) {
		this.inciSubtype = inciSubtype;
	}

	public String getCollMethod1() {
		return collMethod1;
	}

	public void setCollMethod1(String collMethod1) {
		this.collMethod1 = collMethod1;
	}

	public String getCollMethod2() {
		return collMethod2;
	}

	public void setCollMethod2(String collMethod2) {
		this.collMethod2 = collMethod2;
	}

	public String getRestrictType() {
		return restrictType;
	}

	public void setRestrictType(String restrictType) {
		this.restrictType = restrictType;
	}

	public String getCloseLaneType() {
		return closeLaneType;
	}

	public void setCloseLaneType(String closeLaneType) {
		this.closeLaneType = closeLaneType;
	}

	public String getTrafCongest() {
		return trafCongest;
	}

	public void setTrafCongest(String trafCongest) {
		this.trafCongest = trafCongest;
	}

	public String getAmpm() {
		return ampm;
	}

	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}

	public String getIsInciView() {
		return isInciView;
	}

	public void setIsInciView(String isInciView) {
		this.isInciView = isInciView;
	}

	public long getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(long queueLength) {
		this.queueLength = queueLength;
	}

	public long getInciLane() {
		return inciLane;
	}

	public void setInciLane(long inciLane) {
		this.inciLane = inciLane;
	}

	public long getCloseLane() {
		return closeLane;
	}

	public void setCloseLane(long closeLane) {
		this.closeLane = closeLane;
	}

	public long getRelCars() {
		return relCars;
	}

	public void setRelCars(long relCars) {
		this.relCars = relCars;
	}

	public long getRelTrucks() {
		return relTrucks;
	}

	public void setRelTrucks(long relTrucks) {
		this.relTrucks = relTrucks;
	}

	public long getInjuryPersons() {
		return injuryPersons;
	}

	public void setInjuryPersons(long injuryPersons) {
		this.injuryPersons = injuryPersons;
	}

	public long getDeadPersons() {
		return deadPersons;
	}

	public void setDeadPersons(long deadPersons) {
		this.deadPersons = deadPersons;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getDecSeverityLevel() {
		return decSeverityLevel;
	}

	public void setDecSeverityLevel(String decSeverityLevel) {
		this.decSeverityLevel = decSeverityLevel;
	}

	public String getUseSeverityLevel() {
		return useSeverityLevel;
	}

	public void setUseSeverityLevel(String useSeverityLevel) {
		this.useSeverityLevel = useSeverityLevel;
	}

	public long getDecEstDelay() {
		return decEstDelay;
	}

	public void setDecEstDelay(long decEstDelay) {
		this.decEstDelay = decEstDelay;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInciDesc() {
		return inciDesc;
	}

	public void setInciDesc(String inciDesc) {
		this.inciDesc = inciDesc;
	}

	public String getInciPlaceManualIn() {
		return inciPlaceManualIn;
	}

	public void setInciPlaceManualIn(String inciPlaceManualIn) {
		this.inciPlaceManualIn = inciPlaceManualIn;
	}

	public String getInciPlaceManualIn2() {
		return inciPlaceManualIn2;
	}

	public void setInciPlaceManualIn2(String inciPlaceManualIn2) {
		this.inciPlaceManualIn2 = inciPlaceManualIn2;
	}

	public String getExternWeb() {
		return externWeb;
	}

	public void setExternWeb(String externWeb) {
		this.externWeb = externWeb;
	}

	public String getExternRelat() {
		return externRelat;
	}

	public void setExternRelat(String externRelat) {
		this.externRelat = externRelat;
	}

	public String getExternVms() {
		return externVms;
	}

	public void setExternVms(String externVms) {
		this.externVms = externVms;
	}

	public long getSpd() {
		return spd;
	}

	public void setSpd(long spd) {
		this.spd = spd;
	}

	public long getVol() {
		return vol;
	}

	public void setVol(long vol) {
		this.vol = vol;
	}

	public String getRoadStatus() {
		return roadStatus;
	}

	public void setRoadStatus(String roadStatus) {
		this.roadStatus = roadStatus;
	}

	public String getInciMapImg() {
		return inciMapImg;
	}

	public void setInciMapImg(String inciMapImg) {
		this.inciMapImg = inciMapImg;
	}

	public String getInciAreaCd() {
		return inciAreaCd;
	}

	public void setInciAreaCd(String inciAreaCd) {
		this.inciAreaCd = inciAreaCd;
	}

	public long getInciX() {
		return inciX;
	}

	public void setInciX(long inciX) {
		this.inciX = inciX;
	}

	public long getInciY() {
		return inciY;
	}

	public void setInciY(long inciY) {
		this.inciY = inciY;
	}

}
