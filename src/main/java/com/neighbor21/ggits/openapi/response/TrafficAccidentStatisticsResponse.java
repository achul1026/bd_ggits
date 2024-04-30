package com.neighbor21.ggits.openapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrafficAccidentStatisticsResponse {
	
	@JsonProperty("acdnt_dstrct_identifier")
	private String acdntDstrctIdentifier;
	
	@JsonProperty("acdnt_dstrct_id")
	private String acdntDstrctId;

	@JsonProperty("acdnt_cnt")
	private Long acdntCnt;
	
	@JsonProperty("dscd_cnt")
	private Long dcsdCnt;
	
	@JsonProperty("injpsn_cnt")
	private Long injpsnCnt;
	
	@JsonProperty("tot_acdnt_cnt")
	private Long totAcdntCnt;
	
	@JsonProperty("tot_dcsd_cnt")
	private Long totDcsdCnt;
	
	@JsonProperty("tot_injpsn_cnt")
	private Long totInjpsnCnt;
	
	@JsonProperty("popltn_100k_acdnt_cnt")
	private double popltn100kAcdntCnt;
	
	@JsonProperty("vhcl_10k_acdnt_cnt")
	private double vhcl10kAcdntCnt;

	public String getAcdntDstrctIdentifier() {
		return acdntDstrctIdentifier;
	}

	public void setAcdntDstrctIdentifier(String acdntDstrctIdentifier) {
		this.acdntDstrctIdentifier = acdntDstrctIdentifier;
	}

	public String getAcdntDstrctId() {
		return acdntDstrctId;
	}

	public void setAcdntDstrctId(String acdntDstrctId) {
		this.acdntDstrctId = acdntDstrctId;
	}

	public Long getAcdntCnt() {
		return acdntCnt;
	}

	public void setAcdntCnt(Long acdntCnt) {
		this.acdntCnt = acdntCnt;
	}

	public Long getDcsdCnt() {
		return dcsdCnt;
	}

	public void setDcsdCnt(Long dcsdCnt) {
		this.dcsdCnt = dcsdCnt;
	}

	public Long getInjpsnCnt() {
		return injpsnCnt;
	}

	public void setInjpsnCnt(Long injpsnCnt) {
		this.injpsnCnt = injpsnCnt;
	}

	public Long getTotAcdntCnt() {
		return totAcdntCnt;
	}

	public void setTotAcdntCnt(Long totAcdntCnt) {
		this.totAcdntCnt = totAcdntCnt;
	}

	public Long getTotDcsdCnt() {
		return totDcsdCnt;
	}

	public void setTotDcsdCnt(Long totDcsdCnt) {
		this.totDcsdCnt = totDcsdCnt;
	}

	public Long getTotInjpsnCnt() {
		return totInjpsnCnt;
	}

	public void setTotInjpsnCnt(Long totInjpsnCnt) {
		this.totInjpsnCnt = totInjpsnCnt;
	}

	public double getPopltn100kAcdntCnt() {
		return popltn100kAcdntCnt;
	}

	public void setPopltn100kAcdntCnt(double popltn100kAcdntCnt) {
		this.popltn100kAcdntCnt = popltn100kAcdntCnt;
	}

	public double getVhcl10kAcdntCnt() {
		return vhcl10kAcdntCnt;
	}

	public void setVhcl10kAcdntCnt(double vhcl10kAcdntCnt) {
		this.vhcl10kAcdntCnt = vhcl10kAcdntCnt;
	}
}
