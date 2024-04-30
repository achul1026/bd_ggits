package com.neighbor21.ggits.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrafficSignalInfoResponse {
	
	@JsonProperty("mng_inst_cd")
	String mngInstCd;					//관리기관 코드
	
	@JsonProperty("crsrd_id")
	String crsrdId;						//교차로 아이디
	
	@JsonProperty("acs_road_id")
	String acsRoadId;					//접근도로아이디
	
	@JsonProperty("drct_cd")
	String drctCd;						//방향코드
	
	@JsonProperty("vhcl_trfvlm")
	Long vhclTrfvlm;					//차량 교통량
	
	@JsonProperty("avg_vhcl_speed")
	double avgVhclSpeed;				//평균 속도
	
	@JsonProperty("cngrt_grd")
	String cngrtGrd;					//혼잡도 등급

	public String getMngInstCd() {
		return mngInstCd;
	}

	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}

	public String getCrsrdId() {
		return crsrdId;
	}

	public void setCrsrdId(String crsrdId) {
		this.crsrdId = crsrdId;
	}

	public String getAcsRoadId() {
		return acsRoadId;
	}

	public void setAcsRoadId(String acsRoadId) {
		this.acsRoadId = acsRoadId;
	}

	public String getDrctCd() {
		return drctCd;
	}

	public void setDrctCd(String drctCd) {
		this.drctCd = drctCd;
	}

	public Long getVhclTrfvlm() {
		return vhclTrfvlm;
	}

	public void setVhclTrfvlm(Long vhclTrfvlm) {
		this.vhclTrfvlm = vhclTrfvlm;
	}

	public double getAvgVhclSpeed() {
		return avgVhclSpeed;
	}

	public void setAvgVhclSpeed(double avgVhclSpeed) {
		this.avgVhclSpeed = avgVhclSpeed;
	}

	public String getCngrtGrd() {
		return cngrtGrd;
	}

	public void setCngrtGrd(String cngrtGrd) {
		this.cngrtGrd = cngrtGrd;
	}
}
