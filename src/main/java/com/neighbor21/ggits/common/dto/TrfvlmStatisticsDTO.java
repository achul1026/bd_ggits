package com.neighbor21.ggits.common.dto;

import java.util.Arrays;
import java.util.List;

import org.postgresql.util.PGobject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

public class TrfvlmStatisticsDTO extends CommonEntity{

	String ipcssMngNo;		// 영향평가 관리 번호
	String dataNo;
	String dywkDiv;
	String lon;
	String lat;
    String name;
    String usgCd;
    String dwellYn;
    String exmnYmd;
    List<Object> headerList;
    List<TrfvlmStatistics> timeTrfvlmResultList;
    
    public static class TrfvlmStatistics {
    	
    	String timeSctnNm;
    	List<TimeTrfvlmData> timeTrfvlmDataList;
    	
		public String getTimeSctnNm() {
			return timeSctnNm;
		}
		public void setTimeSctnNm(String timeSctnNm) {
			if(!timeSctnNm.contains("~")) {
				timeSctnNm = "합계(대)";
			}
			this.timeSctnNm = timeSctnNm;
		}
		
		public List<TimeTrfvlmData> getTimeTrfvlmDataList() {
			return timeTrfvlmDataList;
		}
		public void setTimeTrfvlmDataList(Object timeTrfvlmDataList) {
			this.timeTrfvlmDataList = (List<TimeTrfvlmData>) timeTrfvlmDataList;
		}

		public static class TimeTrfvlmData {
			
			double psgvhclTrfvlm;
			double busTrfvlm;
			double busLrgszTrfvlm;
			double frghtSmlszTrfvlm;
			double frghtMdmszTrfvlm;
			double frghtLrgszTrfvlm;
	    	String crsrDdrctCd;
	    	
	    	double totalCnt;

			public double getPsgvhclTrfvlm() {
				return psgvhclTrfvlm;
			}

			public void setPsgvhclTrfvlm(double psgvhclTrfvlm) {
				this.psgvhclTrfvlm = psgvhclTrfvlm;
			}
			public double getBusTrfvlm() {
				return busTrfvlm;
			}

			public void setBusTrfvlm(double busTrfvlm) {
				this.busTrfvlm = busTrfvlm;
			}

			public double getBusLrgszTrfvlm() {
				return busLrgszTrfvlm;
			}

			public void setBusLrgszTrfvlm(double busLrgszTrfvlm) {
				this.busLrgszTrfvlm = busLrgszTrfvlm;
			}

			public double getFrghtSmlszTrfvlm() {
				return frghtSmlszTrfvlm;
			}

			public void setFrghtSmlszTrfvlm(double frghtSmlszTrfvlm) {
				this.frghtSmlszTrfvlm = frghtSmlszTrfvlm;
			}

			public double getFrghtMdmszTrfvlm() {
				return frghtMdmszTrfvlm;
			}

			public void setFrghtMdmszTrfvlm(double frghtMdmszTrfvlm) {
				this.frghtMdmszTrfvlm = frghtMdmszTrfvlm;
			}

			public double getFrghtLrgszTrfvlm() {
				return frghtLrgszTrfvlm;
			}

			public void setFrghtLrgszTrfvlm(double frghtLrgszTrfvlm) {
				this.frghtLrgszTrfvlm = frghtLrgszTrfvlm;
			}

			public String getCrsrDdrctCd() {
				return crsrDdrctCd;
			}

			public void setCrsrDdrctCd(String crsrDdrctCd) {
				this.crsrDdrctCd = crsrDdrctCd;
			}

			public double getTotalCnt() {
				return totalCnt;
			}

			public void setTotalCnt(double totalCnt) {
				this.totalCnt = totalCnt;
			}
	    	
		}
		
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Object> getHeaderList() {
		return headerList;
	}
	
	public void setHeaderList(Object strHearderList) {
		if(!GgitsCommonUtils.isNull(strHearderList)) {
			this.headerList = GgitsCommonUtils.pgArrayToJavaArray(strHearderList);
		}
	}

	public List<TrfvlmStatistics> getTimeTrfvlmResultList() {
		return timeTrfvlmResultList;
	}
	

	public void setTimeTrfvlmResultList(Object timeTrfvlmResultList) {
		if(!GgitsCommonUtils.isNull(timeTrfvlmResultList)) {
			PGobject pgObject = (PGobject)timeTrfvlmResultList;
			String strTimeTrfvlmResultList = pgObject.getValue();
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				List<TrfvlmStatistics> trfvlmStatisticsList = Arrays.asList(objectMapper.readValue(strTimeTrfvlmResultList, TrfvlmStatistics[].class));
				this.timeTrfvlmResultList = trfvlmStatisticsList;
			} catch (JsonProcessingException e) {
			    this.timeTrfvlmResultList = null;
			}
		}
	}

	public String getIpcssMngNo() {
		return ipcssMngNo;
	}

	public void setIpcssMngNo(String ipcssMngNo) {
		this.ipcssMngNo = ipcssMngNo;
	}

	public String getDywkDiv() {
		return dywkDiv;
	}

	public void setDywkDiv(String dywkDiv) {
		this.dywkDiv = dywkDiv;
	}

	public String getDataNo() {
		return dataNo;
	}

	public void setDataNo(String dataNo) {
		this.dataNo = dataNo;
	}

	public String getUsgCd() {
		return usgCd;
	}

	public void setUsgCd(String usgCd) {
		this.usgCd = usgCd;
	}

	public String getDwellYn() {
		return dwellYn;
	}

	public void setDwellYn(String dwellYn) {
		this.dwellYn = dwellYn;
	}
	public String getExmnYmd() {
		return exmnYmd;
	}

	public void setExmnYmd(String exmnYmd) {
		this.exmnYmd = exmnYmd;
	}
	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
}
