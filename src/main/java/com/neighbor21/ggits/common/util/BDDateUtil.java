package com.neighbor21.ggits.common.util;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  BDDateUtil
 * @since : 2023-08-31
 */
public class BDDateUtil extends DateUtils {
	
	/**
	  * @Method Name : findWeekdaysAndWeekend
	  * @작성일 : 2023. 10. 25.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 평일, 주말 배열 구하기
	  * @param 
	  * @return String[]
	  */
	public static String[] findWeekdaysAndWeekend(String type) {
		String[] weekArr = null;
		if(!GgitsCommonUtils.isNull(type)) {
			switch (type) {
			case "weekdays":
				weekArr = new String[]{"2","3","4","5","6"};
				break;
			case "weekend":
				weekArr = new String[]{"1","7"};
				break;
			}
		}
		return weekArr;
	}
}
