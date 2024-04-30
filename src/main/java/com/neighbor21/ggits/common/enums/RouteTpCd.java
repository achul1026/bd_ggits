package com.neighbor21.ggits.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * date : 2024-01-17
 * @author qudck
 * @CODE : RTC000(시외버스) , RTC001(일반버스), RTC002(마을버스), RTC003(광역버스), RTC004(공항버스) 
 *
 */
public enum RouteTpCd {

	DIRECT_SEATED_CITY_BUS("RTC003","11"),     //직행 좌석형 시내버스
	SEAT_TYPE_CITY_BUS("RTC001","12"),   	   //좌석형 시내버스
	REGULAR_CITY_BUS("RTC001","13"),    	   //일반형 시내버스
	GWANG_CITY_BUS("RTC001","14"),    	   //일반형 시내버스
	DIRECT_SEAT_RURAL_BUS("RTC003","21"),      //직행좌석농어촌버스
	SEAT_TYPE_RURAL_BUS("RTC003","22"),   	   //좌석형 농어촌버스
	STANDARD_RURAL_BUS("RTC001","23"),   	   //일반형 농어촌버스
	VILLAGE_BUS("RTC002","30"),    			   //마을버스
	HIGH_SPEED_INTERCITY_BUS("RTC000","41"),   //고속형 시외버스
	SEAT_TYPE_INTERCITY_BUS("RTC000","42"),    //좌석형 시외버스
	REGULAR_INTERCITY_BUS("RTC000","43"),      //일반형 시외버스
	LIMOUSINE_TYPE_AIRPORT_BUS("RTC004","51"), //리무진형 공항버스
	SEAT_TYPE_AIRPORT_BUS("RTC004","52"),      //좌석형 공항버스
	REGULAR_AIRPORT_BUS("RTC004","53");        //일반형 공항버스
	
	private String code;
	private String name;
	
	private RouteTpCd(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public static List<String> getRouteTpListFromRouteTpCd(String code) {
		List<String> resultList = new ArrayList<String>();
		for(RouteTpCd r : RouteTpCd.values()) {
			if(r.code.equals(code)) {
				resultList.add(r.name);
			}
		}
		return resultList;
	}
}
