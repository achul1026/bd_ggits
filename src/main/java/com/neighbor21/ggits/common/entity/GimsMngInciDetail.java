package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

import com.neighbor21.ggits.common.enums.InciCateCd;
import com.neighbor21.ggits.common.util.BDStringUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

public class GimsMngInciDetail extends CommonEntity {

	private String mngId; // 돌발정보관리ID
	private Long detailSeq; // 상세(이력) 순차번호(MNG_ID 기준으로 1,2,3...)
	private String updateCate; // 갱신사유유형[INIT(최초등록) | TIME(시각) | LANE(차선) | DESC(부연설명) | LOCA(위치) | TERM(종료)
	private String inciCate; // 돌발유형(교통정보교환 기술기준 준용)
	private String inciCateNm; // 돌발유형명(교통정보교환 기술기준 준용)
	private String beginDate; // 시작(개시, 발생) 일시
	private String endDate; // 종료(예정) 일시
	private String occurredLane; // 발생 차로
	private String closedLane; // 통제 차로
	private String severityLvl; // 심각도[상(3) | 중(2) | 하(1)]
	private double gpsX; // 위치정보(위경도 좌표 X)
	private double gpsY; // 위치정보(위경도 좌표 Y)
	private String description; // 상세 부연 설명
	private String operId; // 운영자 ID
	private Timestamp logDate; // 갱신 일시
	private String isAutoTerm; // 자동종료 여부
	private String roadwayNm; // 돌발구간의 도로, 방향, 시점, 종점(진출입로) 명칭 (구분자 '|' )
	private String subwayCallNum; // 철도사고 담당자
	
	//timeData
	private String timeData;
	
	//#GyCommInfoLink
	private String linkId; //링크 아이디
	
	private String infoSrcOrg; // 정보 출처 기관
	
	//none col
	private Long totalCnt;
	private String addressjibun;
	private String inciTyNm;
	private String geojson;
	
	public String getMngId() {
		return mngId;
	}

	public void setMngId(String mngId) {
		this.mngId = mngId;
	}

	public Long getDetailSeq() {
		return detailSeq;
	}

	public void setDetailSeq(Long detailSeq) {
		this.detailSeq = detailSeq;
	}

	public String getUpdateCate() {
		return updateCate;
	}

	public void setUpdateCate(String updateCate) {
		this.updateCate = updateCate;
	}

	public String getInciCate() {
		return inciCate;
	}

	public void setInciCate(String inciCate) {
		this.inciCate = inciCate;
		if(!GgitsCommonUtils.isNull(inciCate)) {
			String inciCateNm = InciCateCd.getCodeName(inciCate);
			if(!BDStringUtil.isNull(inciCateNm)) {
				this.inciCateNm	= inciCateNm;
			}
		}
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOccurredLane() {
		return occurredLane;
	}

	public void setOccurredLane(String occurredLane) {
		this.occurredLane = occurredLane;
	}

	public String getClosedLane() {
		return closedLane;
	}

	public void setClosedLane(String closedLane) {
		this.closedLane = closedLane;
	}

	public String getSeverityLvl() {
		return severityLvl;
	}

	public void setSeverityLvl(String severityLvl) {
		this.severityLvl = severityLvl;
	}

	public double getGpsX() {
		return gpsX;
	}

	public void setGpsX(double gpsX) {
		this.gpsX = gpsX;
	}

	public double getGpsY() {
		return gpsY;
	}

	public void setGpsY(double gpsY) {
		this.gpsY = gpsY;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public Timestamp getLogDate() {
		return logDate;
	}

	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}

	public String getIsAutoTerm() {
		return isAutoTerm;
	}

	public void setIsAutoTerm(String isAutoTerm) {
		this.isAutoTerm = isAutoTerm;
	}

	public String getRoadwayNm() {
		return roadwayNm;
	}

	public void setRoadwayNm(String roadwayNm) {
		this.roadwayNm = roadwayNm;
	}

	public String getSubwayCallNum() {
		return subwayCallNum;
	}

	public void setSubwayCallNum(String subwayCallNum) {
		this.subwayCallNum = subwayCallNum;
	}

	public String getTimeData() {
		return timeData;
	}

	public void setTimeData(String timeData) {
		this.timeData = timeData;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getInfoSrcOrg() {
		return infoSrcOrg;
	}

	public void setInfoSrcOrg(String infoSrcOrg) {
		this.infoSrcOrg = infoSrcOrg;
	}

	public String getInciCateNm() {
		return inciCateNm;
	}

	public Long getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(Long totalCnt) {
		this.totalCnt = totalCnt;
	}

	public void setInciCateNm(String inciCateNm) {
		this.inciCateNm = inciCateNm;
	}

	public String getAddressjibun() {
		return addressjibun;
	}

	public void setAddressjibun(String addressjibun) {
		this.addressjibun = addressjibun;
	}

	public String getInciTyNm() {
		return inciTyNm;
	}

	public void setInciTyNm(String inciTyNm) {
		this.inciTyNm = inciTyNm;
	}

	public String getGeojson() {
		return geojson;
	}

	public void setGeojson(String geojson) {
		this.geojson = geojson;
	}
}
