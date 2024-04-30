package com.neighbor21.ggits.web.service.historymng;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.LTcDataLog;
import com.neighbor21.ggits.common.mapper.LTcDataLogMapper;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;

@Service
public class HistoryMngService{
	
	@Autowired
	LTcDataLogMapper lTcDataLogMapper;
	
	public List<LTcDataLog> findAllLTcDataLogList(LTcDataLog lTcDataLog){
		//검색조건 가공
//		if(lTcDataLog.getColTyCd() != null && !"".equals(lTcDataLog.getColTyCd())) {
//			if(lTcDataLog.getColTyCd().contains(",")) {
//				String[] colArr = lTcDataLog.getColTyCd().split(",");
//				lTcDataLog.setColArr(colArr);
//			}
//		}
		
		return lTcDataLogMapper.findAllLTcDataLogList(lTcDataLog);
	}

	/**
	 * 알람용 장애이력
	 * @return
	 */
	public List<LTcDataLog> findAllLTcDataLogTodayListForAlarm(){
		Date now = new Date();
		String stDt = BDDateFormatUtil.format(now, "yyyy-MM-dd") + " 00:00:00";
		String endDt = BDDateFormatUtil.format(now, "yyyy-MM-dd") + " 23:59:59";
		
		return lTcDataLogMapper.findAllLTcDataLogTodayListForAlarm(stDt, endDt);
	}


}
