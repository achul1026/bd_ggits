package com.neighbor21.ggits.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class EmergencyPathAnalysisResponse {

	@JsonProperty("mng_inst_cd")
	String mngInstCd;			//관리기관코드
	
	@JsonProperty("srvcId")
	String srvcId;				//서비스아이디

	@JsonProperty("avg_srvc_time")
	Long avgSrvcTime;			//평균 서비스 시간

	@JsonProperty("avg_srvc_dstne")
	Long avgSrvcDstne;			//평균 서비스 거리

	@JsonProperty("avg_srvc_prmmnt_time")
	Long avgSrvcPrmmntTime;		//평균 도착 예정 시간

	@JsonProperty("avg_srvg_prmmnt_dstne")
	Long avgSrvgPrmmntDstne;	//평균 도착 예정 거리
	
	public String getMngInstCd() {
		return mngInstCd;
	}
	public void setMngInstCd(String mngInstCd) {
		this.mngInstCd = mngInstCd;
	}
	public String getSrvcId() {
		return srvcId;
	}
	public void setSrvcId(String srvcId) {
		this.srvcId = srvcId;
	}
	public Long getAvgSrvcTime() {
		return avgSrvcTime;
	}
	public void setAvgSrvcTime(Long avgSrvcTime) {
		this.avgSrvcTime = avgSrvcTime;
	}
	public Long getAvgSrvcDstne() {
		return avgSrvcDstne;
	}
	public void setAvgSrvcDstne(Long avgSrvcDstne) {
		this.avgSrvcDstne = avgSrvcDstne;
	}
	public Long getAvgSrvcPrmmntTime() {
		return avgSrvcPrmmntTime;
	}
	public void setAvgSrvcPrmmntTime(Long avgSrvcPrmmntTime) {
		this.avgSrvcPrmmntTime = avgSrvcPrmmntTime;
	}
	public Long getAvgSrvgPrmmntDstne() {
		return avgSrvgPrmmntDstne;
	}
	public void setAvgSrvgPrmmntDstne(Long avgSrvgPrmmntDstne) {
		this.avgSrvgPrmmntDstne = avgSrvgPrmmntDstne;
	}
}
