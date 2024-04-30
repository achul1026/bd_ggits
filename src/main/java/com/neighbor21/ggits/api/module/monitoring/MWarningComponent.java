package com.neighbor21.ggits.api.module.monitoring;

import java.util.*;

import org.postgresql.util.PSQLException;
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

	static List<GimsMngInciDetail> waringMarkerList = new ArrayList<>();
	static List<GimsMngInciDetail> waringAlarmList = new ArrayList<>();

	/**
	 * 모니터링 알람 리스트 조회
	 * @return
	 */
	public List<GimsMngInciDetail> findAllWarningAlarmList(){
		return waringAlarmList;
	}

	/**
	 * 맵에 돌발상황 마커를 그리기
	 */
	public List<GimsMngInciDetail> findAllForMapMarker(){
		return waringMarkerList;
	}
	public List<GimsMngInciDetail> findAllToday() {
		return gimsMngInciDetailMapper.findAllWarningInfoToday();
	}

	public void setWaringMarkerList(List<GimsMngInciDetail> savedWaringMarkerList) {
		waringMarkerList = savedWaringMarkerList;
	}

	public void setWaringAlarmList(List<GimsMngInciDetail> savedWaringAlarmList) {
		waringAlarmList = savedWaringAlarmList;
	}
}
