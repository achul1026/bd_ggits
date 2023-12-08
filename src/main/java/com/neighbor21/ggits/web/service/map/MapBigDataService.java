package com.neighbor21.ggits.web.service.map;

import java.text.ParseException;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

@Service
public class MapBigDataService {
	
	/**
     * @Method Name : setSearchDateInfo
     * @작성일 : 2023. 10. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 검색 일자 정보 세팅 
     * @param mapBigdataSearchDTO
     * @return
	 * @throws ParseException 
     */
	public MapBigdataSearchDTO setSearchDateInfo(MapBigdataSearchDTO mapBigdataSearchDTO) throws ParseException {
		
    	String startToday = "";
		String endToday = "";
		
		if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getStartDate())) {
			startToday = GgitsCommonUtils.dateToDatetimeStr(mapBigdataSearchDTO.getStartDate(), "startDate");
			if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getStartTime())) {
				int startTime = Integer.parseInt(mapBigdataSearchDTO.getStartTime());
				startToday = GgitsCommonUtils.setDateTimeToDateString(startToday, startTime, "yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
		}
    	
		if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getEndDate())) {
			endToday = GgitsCommonUtils.dateToDatetimeStr(mapBigdataSearchDTO.getEndDate(), "endDate");			
			if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getEndTime())) {
				int endTime = Integer.parseInt(mapBigdataSearchDTO.getEndTime());
				endToday = GgitsCommonUtils.setDateTimeToDateString(endToday, endTime, "yyyy-MM-dd HH:mm:ss",Calendar.HOUR);
			}
		}
		
		mapBigdataSearchDTO.setStartDate(startToday);
		mapBigdataSearchDTO.setEndDate(endToday);
		
		return mapBigdataSearchDTO;
	}
}
