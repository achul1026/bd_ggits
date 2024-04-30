package com.neighbor21.ggits.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.TrfIpcssTimeInoutflExmn;

public class ExcelUploadUtil {

	/**
	 * @Method Name : getTimeInoutflExmnForUsgNo
     * @작성일 : 2023. 11. 16.
     * @작성자 : KC.KIM
     * @Method 설명 : 교통영향평가 시간대별 유출입 통행분포 해당 셀의 용도 번호 조회
     * @param : trfIpcssTimeInoutflExmn
     * @param : cellIndex 
     * @return : 
     */
	public static TrfIpcssTimeInoutflExmn getTimeInoutflExmnForUsgNoAndUsgNm(TrfIpcssTimeInoutflExmn trfIpcssTimeInoutflExmn, int cellIndex) {
		TrfIpcssTimeInoutflExmn resultDate = new TrfIpcssTimeInoutflExmn();
		String usgNo = "";
		String usgNm = "";
		String actPopltnExmnType = "";
		
		List<String> usgNoList = new ArrayList<String>();
		usgNoList = trfIpcssTimeInoutflExmn.getUsgNoList();
		
		List<String> usgNmList = new ArrayList<String>();
		usgNmList = trfIpcssTimeInoutflExmn.getUsgNmList();
		
		if(!usgNoList.isEmpty() && !usgNmList.isEmpty()) {
			if(cellIndex >= 1 && cellIndex <= 12) {
				usgNo = usgNoList.get(1);	
				usgNm = usgNmList.get(1);
			}else if(cellIndex >= 13 && cellIndex <= 24) {
				usgNo = usgNoList.get(2);	
				usgNm = usgNmList.get(3);
			}else if(cellIndex >= 25 && cellIndex <= 36) {
				usgNo = usgNoList.get(3);	
				usgNm = usgNmList.get(5);
			}else if(cellIndex >= 37 && cellIndex <= 48) {
				usgNo = usgNoList.get(4);	
				usgNm = usgNmList.get(7);
			}else if(cellIndex >= 49 && cellIndex <= 60) {
				usgNo = usgNoList.get(5);
				usgNm = usgNmList.get(9);
			}else if(cellIndex >= 61 && cellIndex <= 72) { 
				usgNo = usgNoList.get(6);
				usgNm = usgNmList.get(11);
			}else if(cellIndex >= 73 && cellIndex <= 84) {
				usgNo = usgNoList.get(7);
				usgNm = usgNmList.get(13);
			}else if(cellIndex >= 85 && cellIndex <= 96) {
				usgNo = usgNoList.get(8);
				usgNm = usgNmList.get(15);
			}else if(cellIndex >= 97 && cellIndex <= 108) {
				usgNo = usgNoList.get(9);
				usgNm = usgNmList.get(17);
			}else if(cellIndex >= 109 && cellIndex <= 120) {
				usgNo = usgNoList.get(10);
				usgNm = usgNmList.get(19);
			}else if(cellIndex >= 121 && cellIndex <= 132) {
				usgNo = usgNoList.get(11);
				usgNm = usgNmList.get(21);
			}else if(cellIndex >= 133 && cellIndex <= 144) {
				usgNo = usgNoList.get(12);
				usgNm = usgNmList.get(23);
			}else if(cellIndex >= 145 && cellIndex <= 156) {
				usgNo = usgNoList.get(13);
				usgNm = usgNmList.get(25);
			}else if(cellIndex >= 157 && cellIndex <= 168) {
				usgNo = usgNoList.get(14);
				usgNm = usgNmList.get(27);
			}else if(cellIndex >= 169 && cellIndex <= 180) {
				usgNo = usgNoList.get(15);
				usgNm = usgNmList.get(29);
			}else if(cellIndex >= 181 && cellIndex <= 192) {
				usgNo = usgNoList.get(16);
				usgNm = usgNmList.get(31);
			}else if(cellIndex >= 193 && cellIndex <= 204) {
				usgNo = usgNoList.get(17);
				usgNm = usgNmList.get(33);
			}else if(cellIndex >= 205 && cellIndex <= 216) {
				usgNo = usgNoList.get(18);
				usgNm = usgNmList.get(35);
			}else if(cellIndex >= 217 && cellIndex <= 228) {
				usgNo = usgNoList.get(19);
				usgNm = usgNmList.get(37);
			}else if(cellIndex >= 229 && cellIndex <= 240) {
				usgNo = usgNoList.get(20);
				usgNm = usgNmList.get(39);
			}
			resultDate.setUsgNo(usgNo);
			resultDate.setUsgNm(usgNm);
			resultDate.setActPopltnExmnType(actPopltnExmnType);
		}
		return resultDate;
	}

	/**
	  * @Method Name : findUsgCd
	  * @작성일 : 2023. 11. 17.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 교통영향평가 cell의 용도 코드정보를 공통 코드로 변환
	  * @param usgCdStr
	  * @return usgCdList
	  */
	public static String findUsgCd(String usgCdStr, List<MOpCode> usgCdList) {
		String result = "";
		
		for(MOpCode usgCdInfo :  usgCdList) {
			if(usgCdStr.equals(usgCdInfo.getCdNm())) {
				result = usgCdInfo.getCdId();
			}
		}
		return result;
	}
	
	/**
	  * @Method Name : findUsgCd
	  * @작성일 : 2023. 11. 17.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 교통영향평가 usgCd를 공통 코드명으로 변환
	  * @param usgCdStr
	  * @return usgCdList
	  */
	public static String findUsgCdNm(String usgCdStr, List<MOpCode> usgCdList) {
		String result = "";
		
		for(MOpCode usgCdInfo :  usgCdList) {
			if(usgCdStr.equals(usgCdInfo.getCdId())) {
				result = usgCdInfo.getCdNm();
			}
		}
		return result;
	}
	
	/**
	  * @Method Name : checkUsgCd
	  * @작성일 : 2023. 11. 17.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 교통영향평가 용도 코드가 주거용,비주거용인지 확인
	  * @param usgCd
	  * @return type
	  */
	public static boolean checkUsgCd(String usgCd, String type) {
		// 주거 코드 001, 101~112 / 비주거 코드 002~096
		String[] dwellArr = new String[] {"001","101","103", "106", "109", "111", "112"};
		List<String> dwellList = new ArrayList<>(Arrays.asList(dwellArr));
		boolean result = false;
		
		if(type.equals("dwell")) {
			result = dwellList.contains(usgCd);
		}else if(type.equals("nonDwell")) {
			if(dwellList.contains(usgCd)) {
				result = false;				
			}else {
				result = true;
			}
		}
		
		return result;
	}
	
	/**
	  * @Method Name : checkCellValue
	  * @작성일 : 2023. 11. 20.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 교통영향평가 cell value null 체크
	  * @param value
	  * @return 
	  */
	public static boolean checkCellValue(String value) {
		boolean result = false;
		
		if(!GgitsCommonUtils.isNull(value) && !value.equals("false")) {
			result = true;
		}
		return result;
	}
	
}
