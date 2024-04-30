package com.neighbor21.ggits.common.dto;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

public class TimeInoutflExmnDTO {
	String timeSctnNm;
	String trfvlmStatisticsListJson;
	List<TimeInoutflExmn> trfvlmStatisticsList;
	
	public static class TimeInoutflExmn{
		private Double resdngInfl;
		private Double resdngOutfl;
		private Double visitInfl;
		private Double visitOutfl;
		private Double totInfl;
		private Double totOutfl;
		
		public Double getResdngInfl() {
			return resdngInfl;
		}
		public void setResdngInfl(Double resdngInfl) {
			this.resdngInfl = resdngInfl;
		}
		public Double getResdngOutfl() {
			return resdngOutfl;
		}
		public void setResdngOutfl(Double resdngOutfl) {
			this.resdngOutfl = resdngOutfl;
		}
		public Double getVisitInfl() {
			return visitInfl;
		}
		public void setVisitInfl(Double visitInfl) {
			this.visitInfl = visitInfl;
		}
		public Double getVisitOutfl() {
			return visitOutfl;
		}
		public void setVisitOutfl(Double visitOutfl) {
			this.visitOutfl = visitOutfl;
		}
		public Double getTotInfl() {
			return totInfl;
		}
		public void setTotInfl(Double totInfl) {
			this.totInfl = totInfl;
		}
		public Double getTotOutfl() {
			return totOutfl;
		}
		public void setTotOutfl(Double totOutfl) {
			this.totOutfl = totOutfl;
		}
	}

	public String getTimeSctnNm() {
		return timeSctnNm;
	}

	public void setTimeSctnNm(String timeSctnNm) {
		this.timeSctnNm = timeSctnNm;
	}
	
	public List<TimeInoutflExmn> getTrfvlmStatisticsList() {
		return trfvlmStatisticsList;
	}

	public void setTrfvlmStatisticsList(List<TimeInoutflExmn> trfvlmStatisticsList) {
		this.trfvlmStatisticsList = trfvlmStatisticsList;
	} 

	public String getTrfvlmStatisticsListJson() {
		return trfvlmStatisticsListJson;
	}

	public void setTrfvlmStatisticsListJson(String trfvlmStatisticsListJson) {
		if(!GgitsCommonUtils.isNull(trfvlmStatisticsListJson)) {
		    ObjectMapper objectMapper = new ObjectMapper();
		    this.trfvlmStatisticsListJson = trfvlmStatisticsListJson;
		    try {
				List<TimeInoutflExmn> trfvlmStatisticsList = Arrays.asList(objectMapper.readValue(trfvlmStatisticsListJson, TimeInoutflExmn[].class));
				this.trfvlmStatisticsList = trfvlmStatisticsList;
			} catch (IOException e) {
				this.trfvlmStatisticsList = null;
	        }
		}
	}

	
	
	
}
