package com.neighbor21.ggits.api.module.monitoring;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.entity.GimsMngInciDetail;
import com.neighbor21.ggits.common.mapper.GimsMngInciDetailMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

/**
 * 돌발상황 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  MWarningComponent
 * @since : 2023-09-07
 */
@Component
public class MWarningComponent extends BaseMapDataComponent {
	
	@Autowired
	GimsMngInciDetailMapper gimsMngInciDetailMapper;

	/**
	 * 현재
	 * @return
	 */
	public List<GimsMngInciDetail> findAllWarningList(){
		
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String startToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 00:00:00", Calendar.HOUR);
		String endToday 	= GgitsCommonUtils.getCalculationDateToString(0, "yyyy-MM-dd 23:59:59", Calendar.HOUR);
		
		//TODO::추후 데이터 주석 해제
//		paramMap.put("startToday", startToday);
//		paramMap.put("endToday", endToday);
		
		List<GimsMngInciDetail> waringList = gimsMngInciDetailMapper.findAllDailyWarningList(paramMap);
		
		return waringList;
	}

	/**
	 * 모니터링 알람 리스트 조회
	 * @return
	 */
	public List<GimsMngInciDetail> findAllWarningAlarmList(){
		List<GimsMngInciDetail> waringList = gimsMngInciDetailMapper.findAllDailyWarningAlarmList();
		return waringList;
	}

	/**
	 * 맵에 돌발상황 마커를 그리기
	 */
	public List<GimsMngInciDetail> findAllForMapMarker(){
		List<GimsMngInciDetail> waringList = gimsMngInciDetailMapper.findAllWarningListForMapMarker();
		return waringList;
	}

	
}
