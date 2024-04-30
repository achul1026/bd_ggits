package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface GgsplBusPeriodicinfoCurMapper {

	/**
	 * 설명 : 운행중인 버스 대수
	 * @param ggsplBusPeriodicinfoCur
	 * @return int
	 */
    int countAllRealTimeBusMoveInfo();
    
	/**
	 * 설명 : 운행중 버스 차트 데이터
	 * @param findRealTimeBusMoveInfoByCity
	 * @return List<Map<String,Object>>
	 */
    List<Map<String,Object>> findRealTimeBusMoveInfoByCity();
}
