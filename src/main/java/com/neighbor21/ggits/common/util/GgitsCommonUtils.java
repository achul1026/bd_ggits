package com.neighbor21.ggits.common.util;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.postgresql.jdbc.PgArray;

public class GgitsCommonUtils {

	private GgitsCommonUtils() {};
	
	
	/**
	  * @Method Name : getUuid
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : KY.LEE
	  * @Method 설명 : UUID 생성
	  * @param paramVal
	  * @return String
	  */
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
	/**
	  * @Method Name : isNull
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : 82103
	  * @Method 설명 : Null Check
	  * @param paramVal
	  * @return boolean
	  */
	public static boolean isNull(Object paramVal) {
		boolean result = false;
		if("".equals(paramVal) || paramVal == null) {
			result = true;
		}
		return result;
	}
	
	/**
	  * @Method Name : phoneNumAddHyphen
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 휴대전화번호 하이픈 추가
	  * @param 
	  * @return number
	  */
	public static String phoneNumAddHyphen(String number) {
		  if(GgitsCommonUtils.isNull(number)) {
			  return "";
		  }
	      String regEx = "(\\d{3})(\\d{3,4})(\\d{4})";
	      return number.replaceAll(regEx, "$1-$2-$3");
	}
	
	/**
	  * @Method Name : removeHyphen
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : KC.KIM
	  * @Method 설명 : removeHyphen
	  * @param 
	  * @return number
	  */
	public static String removeHyphen(String str) {
		  if(GgitsCommonUtils.isNull(str)) {
			  return "";
		  }
	      return str.replace("-", "");
	}

	/**
	  * @Method Name : removeHyphen
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : KC.KIM
	  * @Method 설명 : removeHyphen
	  * @param 
	  * @return number
	  */
	public static String dateToDatetimeStr(String date, String type) {
		if(!GgitsCommonUtils.isNull(date)) {
			String result = "";
			switch (type) {
			case "startDate":
				result = date + " 00:00:00";
				break;
			case "endDate":
				result = date + " 23:59:59";
				break;
			case "startDateHour":
				result = date + " 00";
				break;
			case "endDateHour":
				result = date + " 23";
				break;
			}
			
			return result;
		  }
		return null;
	}
	
	/**
	  * @Method Name : setDateTimeToDateString
	  * @작성일 : 2023. 9. 25.
	  * @작성자 : KY.LEE
	  * @Method 설명 : DateString 값 + n값 Set하기
	  * @param String Date(yyyy-MM-dd) , Integer Time 00-23,String pattern(Date pattern ex)yyyy-MM-dd HH:mm:ss) , int calenderType Calendar.HOUR 
	  * @return String
	  * @throws ParseException 
	  */
	public static String setDateTimeToDateString(String strDate,Integer time, String pattern,int calendarType) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(strDate + " " + time);

		Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		cal.add(calendarType, time);
		
		return format.format(cal.getTime());
	}
	
	
	/**
	  * @Method Name : pgArrayToJavaArray
	  * @작성일 : 2023. 9. 12.
	  * @작성자 : NK.KIM
	  * @Method 설명 : PostgresSql Array - > Java Array
	  * @param pgArray
	  * @return
	  */
	public static List<Object> pgArrayToJavaArray(Object pgArray){
		PgArray arrayData = (PgArray) pgArray;
		Object deserializedArray = null;
		try {
			deserializedArray = arrayData.getArray();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Arrays.asList((Object[]) deserializedArray);
	}
	
	/**
	  * @Method Name : getCalculationDateToString
	  * @작성일 : 2023. 9. 12.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 날짜 연산
	  * @param Integer  , String , int ex)Calendar.HOUR
	  * @return
	  */
	public static String getCalculationDateToString(Integer number,String pattern,int calendarType) {
		
		String returnTime = null;
		Date today = new Date();
		
		SimpleDateFormat sdformat = new SimpleDateFormat(pattern);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(calendarType, number);
		
		returnTime = sdformat.format(cal.getTime());
		
		return returnTime;
	}
	
	/**
	  * @Method Name : getTimeForStringDate
	  * @작성일 : 2023. 10. 25.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 날짜 String값을 시간값으로 return HH:mm
	  * @param String 데이트 형식
	  * @return
	 * @throws ParseException 
	  */
	public static String getTimeForStringDate(String date,String pattern) throws ParseException {
		String result = "";
		
		SimpleDateFormat sdformat = new SimpleDateFormat(pattern);
		Date paramDate = sdformat.parse(date);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(paramDate);
		
		int hours = cal.get(Calendar.HOUR_OF_DAY);
		int times = cal.get(Calendar.MINUTE);
		
		String strHours = "";
		String strTimes = "";
		
		if(hours < 10) {
			strHours = "0"+String.valueOf(hours);
		} else {
			strHours = String.valueOf(hours);
		}
		
		if(times < 10) {
			strTimes = "0"+String.valueOf(times);
		} else {
			strTimes = String.valueOf(times);
		}
		
		result = strHours+":"+strTimes;
		
		return result;
	}
	
	/**
	  * @Method Name : getArrIdx
	  * @작성일 : 2023.10. 16.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 배열 인덱스 찾기
	  * @param String[], String 
	  * @return
	  */
	public static int getArrIdx(String[] strArr, String target) {
		int idx = Arrays.binarySearch(strArr, target);
		return (idx < 0)? -1 : idx;
	}
	
	
}
