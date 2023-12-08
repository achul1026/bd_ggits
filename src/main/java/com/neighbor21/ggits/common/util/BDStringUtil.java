package com.neighbor21.ggits.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  BDStringUtil
 * @since : 2023-08-31
 */
public class BDStringUtil extends StringUtils {

    public static boolean isEmail(){
        return true;
    }

    public static boolean isHp(){

        return true;
    }

    /**
     * String null || ""
     *
     * @param str the str
     * @return the boolean
     */
    public static boolean isNull(String str) {
        return StringUtils.isEmpty(str);
    }

    /**
     * String convert(snakeCase to CamelCase)
     * @param String(snakeCase)
     * @return String(CamelCase)
     */
    public static String convertCamelCase(String snakeStr) {
    	String[] words = snakeStr.split("[\\W_]+");
    	StringBuilder builder = new StringBuilder();
    	for (int i = 0; i < words.length; i++) {
    	    String word = words[i];
    	    if (i == 0) {
    	        word = word.isEmpty() ? word : word.toLowerCase();
    	    } else {
    	        word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();      
    	    }
    	    builder.append(word);
    	}
    	return builder.toString();
    }
}
