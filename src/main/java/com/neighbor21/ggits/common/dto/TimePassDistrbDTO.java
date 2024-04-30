package com.neighbor21.ggits.common.dto;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

public class TimePassDistrbDTO {
	String timeSctnNm;
	String trfvlmStatisticsListJson;
	List<TimePassDistrb> trfvlmStatisticsList;
	
	public static class TimePassDistrb{
		private String timeSctnNm;
		private Double inflRt;
		private Double outflRt;
		
		public String getTimeSctnNm() {
			return timeSctnNm;
		}
		public void setTimeSctnNm(String timeSctnNm) {
			this.timeSctnNm = timeSctnNm;
		}
		public Double getInflRt() {
			return inflRt;
		}
		public void setInflRt(Double inflRt) {
			this.inflRt = inflRt;
		}
		public Double getOutflRt() {
			return outflRt;
		}
		public void setOutflRt(Double outflRt) {
			this.outflRt = outflRt;
		}	
	}

	public String getTimeSctnNm() {
		return timeSctnNm;
	}

	public void setTimeSctnNm(String timeSctnNm) {
		this.timeSctnNm = timeSctnNm;
	}

	public void setTrfvlmStatisticsList(List<TimePassDistrb> trfvlmStatisticsList) {
		this.trfvlmStatisticsList = trfvlmStatisticsList;
	}

	public List<TimePassDistrb> getTrfvlmStatisticsList() {
		return trfvlmStatisticsList;
	}


	public String getTrfvlmStatisticsListJson() {
		return trfvlmStatisticsListJson;
	}

	public void setTrfvlmStatisticsListJson(String trfvlmStatisticsListJson) {
		if(!GgitsCommonUtils.isNull(trfvlmStatisticsListJson)) {
		    ObjectMapper objectMapper = new ObjectMapper();
		    this.trfvlmStatisticsListJson = trfvlmStatisticsListJson;
		    try {
				List<TimePassDistrb> trfvlmStatisticsList = Arrays.asList(objectMapper.readValue(trfvlmStatisticsListJson, TimePassDistrb[].class));
				this.trfvlmStatisticsList = trfvlmStatisticsList;
			} catch (IOException e) {
				this.trfvlmStatisticsList = null;
	        }
		}
	}
	
	
}
