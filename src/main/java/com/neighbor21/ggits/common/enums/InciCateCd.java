package com.neighbor21.ggits.common.enums;

public enum InciCateCd {
	
	ADD_MORE_INFO("1","추가정보필요"),
	CAR_ACCIDENT("2","차량사고"),
	WEATHER_RELATED_ACCIDENTS("3","기상관련사고"),
	VEHICLE_BREAKDOWN("4","차량고장"),
	CAR_FIRE("5","차량화재"),
	OBSTACLE("6","장애물"),
	HAZARDOUS_MATERIALS("7","위험물질방출"),
	EARTHQUAKE("8","지진"),
	LANDSLIDE("9","산사태"),
	FLOOD("10","홍수"),
	TYPHOON("11","태풍"),
	PROTEST_RALLY("12","시위집회"),
	INCREASE_VEHICLES("13","차량의 급격한 증가");
	
	
	private String code; 
	private String name;
	
	private InciCateCd(String code, String name) {
		this.code = code;
		this.name = name;
	}


	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}


	public static String getCodeName(String code) {
		for(InciCateCd inciCateCd : InciCateCd.values()) {
			if(inciCateCd.code.equals(code)) {
				return inciCateCd.name;
			}
		}
		return null;
	}
	
	
}
