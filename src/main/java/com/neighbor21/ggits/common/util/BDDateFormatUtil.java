package com.neighbor21.ggits.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  BDDateFormatUtil
 * @since : 2023-08-31
 */
public class BDDateFormatUtil extends DateFormatUtils {
	/**
	  * @Method Name : isNow
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 현재 날짜 구하기(LocalDate 반환)
	  * @param 
	  * @return LocalDate
	  */
	public static LocalDate isNow() {
		return LocalDate.now();
	}
	
	/**
	  * @Method Name : isNowStr
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 현재 날짜 구하기(String 반환)
	  * @param pattern
	  * @return String
	  */
	public static String isNowStr(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date now = new Date();
		
		return sdf.format(now);
	}
	
	public static String isDateCal(String pattern, int calNum) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, calNum); // 일 계산
				
		Date date = new Date(cal.getTimeInMillis());
		
		return sdf.format(date);
	}

	public static String formatDate(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일");
		String result = "";
		// 20231119금,20231120금
		if(!dateStr.equals("000000000")) {
			if(dateStr.contains(",")) {
				String[] dateArr = dateStr.split(",");
				for(int i=0; i< dateArr.length; i++) {
					String datePartStr = dateArr[i].substring(0,8);
					String dayOfWeekStr = dateArr[i].substring(8);
					
					Date datePart = sdf.parse(datePartStr);
					String date = sdf2.format(datePart);
					if(i == 0) {
						result = date + "(" + dayOfWeekStr + ")";					
					}else {
						result += " , " + date + "(" + dayOfWeekStr + ")";										
					}
				}
			}else {
				String datePartStr = dateStr.substring(0,7);
				String dayOfWeekStr = dateStr.substring(8);
				
				Date datePart = sdf.parse(datePartStr);
				String date = sdf2.format(datePart);
				result += date + "(" + dayOfWeekStr + ")";
			}
		}else {
			result = "0000년 00월 00일(0요일)";
		}
		
		
		return result;
	}
}
