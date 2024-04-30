package com.neighbor21.ggits.common.util;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.postgresql.jdbc.PgArray;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

public class GgitsCommonUtils {

	private GgitsCommonUtils() {};
	
	
	/**
	  * @Method Name : getUuid
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : KY.LEE
	  * @Method 설명 : UUID 생성
	  * @return String
	  */
	public static String getUuid() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	
	/**
	 * @Method Name : getUuid
	 * @작성일 : 2023. 11. 02.
	 * @작성자 : KY.LEE
	 * @Method 설명 : UUID 생성 글자수 제한
	 * @param int maxLength
	 * @return String
	 */
	public static String getUuid(int maxLength) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		uuid = uuid.substring(0, maxLength);
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
			throw new CommonException(ErrorCode.DATA_PARSE_ERROR);
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
	
	/**
	  * @Method Name : isDouble
	  * @작성일 : 2023.11. 16.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 문자열이 double인지 체크
	  * @param String 
	  * @return
	  */
	public static boolean isDouble(String strValue) {
	    try {
	      Double.parseDouble(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	  }
	
	/**
	  * @Method Name : isLong
	  * @작성일 : 2023.11. 17.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 문자열이 long인지 체크
	  * @param String 
	  * @return
	  */
	public static boolean isLong(String strValue) {
	    try {
	      Long.parseLong(strValue);
	      return true;
	    } catch (NumberFormatException ex) {
	      return false;
	    }
	}
	
	/**
	  * @Method Name : extractDate
	  * @작성일 : 2023.11. 17.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 문자열에서 날짜 정보만 추출(2023년 11월 17일 > 20231117) / 날짜가 2개 이상일 경우 콤마(,) 포함
	  * @param dateStr
	  * @return
	  */
	public static String extractDateWithComma(String dateStr) {
		//dateStr = "0000년 00월 00일 (0요일) ";
		String result = "";
		// 2021년 04월 09일(금), 2021년 08월 13일 (금요일) 
		if(!GgitsCommonUtils.isNull(dateStr)) {
			result = dateStr.replaceAll("년", "").replaceAll("월", "").replaceAll("요일", "")
					.replaceAll("\\(", "").replaceAll("일", "").replaceAll("\\)", "").replaceAll(" ", "");					
		}
		return result;
	}
	
	public static boolean chkContainString(String value) {
		if(value.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	  * @Method Name : isDateChk
	  * @작성일 : 2023.12. 08.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 유효 날짜 체크
	  * @param 
	  * @return
	  */
	public static boolean isDateChk(String date, String pattern) {
		boolean isDateChk = true;
		if(date != null && !"".equals(date)) {
			try {
				SimpleDateFormat  dateFormat = new  SimpleDateFormat(pattern);
				dateFormat.setLenient(false);
				dateFormat.parse(date);
			} catch(ParseException e) {
				isDateChk = false;
			}
		} 
		return isDateChk;
	}
	
	/**
	  * @Method Name : isTimeChk
	  * @작성일 : 2023.12. 08.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 유효 시간 체크
	  * @param 
	  * @return
	  */
	public static boolean isTimeChk(String strTime) {
		boolean isDateChk = true;
		
		int time = Integer.parseInt(strTime);
		if(0 > time) {
			isDateChk = false;
		} else if(time > 24) {
			isDateChk = false;
		}
		
		return isDateChk;
	}
	
	/**
	  * @Method Name : 날짜 변환 String -> String yyyy-MM-dd
	  * @작성일 : 2023.12. 08.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 데이트 포맷 변경
	  * @param String dateStr,String beforePattern, String afterPattern
	  * @return String
	  * @throws ParseException
	  */
	public static String formatDate(String dateStr,String beforePattern, String afterPattern) throws ParseException {
		
		if(GgitsCommonUtils.isNull(dateStr) || GgitsCommonUtils.isNull(beforePattern) || GgitsCommonUtils.isNull(afterPattern)) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(beforePattern);
		SimpleDateFormat sdf2 = new SimpleDateFormat(afterPattern);
		
		Date formatDate = sdf.parse(dateStr);
		String newDateStr = sdf2.format(formatDate);
		
		return newDateStr;
	}
	/**
	  * @Method Name : jsonObjectToMap
	  * @작성일 : 2023.12. 08.
	  * @작성자 : NK.KIM
	  * @Method 설명 : json object To Map
	  * @param 
	  * @return
	  */
	public static Map<String,Object> jsonObjectToMap(String contents){
		Map<String,Object> contentsMap = new HashMap<String, Object>();
		
		try {
			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject jo = (JsonObject)parser.parse(contents);
			contentsMap = (HashMap)gson.fromJson(jo.toString(), contentsMap.getClass());
		}catch (Exception e) {
			contentsMap = new HashMap<String, Object>();
		}
		
		return contentsMap;
		
	}
}
