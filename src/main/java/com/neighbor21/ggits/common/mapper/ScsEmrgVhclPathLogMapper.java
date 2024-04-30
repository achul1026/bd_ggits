package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.ScsEmrgVhclPathLog;

@Mapper
public interface ScsEmrgVhclPathLogMapper {
	
	   /**
	    * @Method Name : findEmergByMnginstcd
	    * @작성일 : 2023. 01. 04.
	    * @작성자 : KY.LEE
	    * @Method 설명 : 모니터링 대시보드 -> 지자체별 긴급차량 운행 현황 목록 조회
	    * @return List<ScsEmrgVhclPathLog> 
	    */	
	List<ScsEmrgVhclPathLog> findEmergByMnginstcd();

	/**
	 * @Method Name : findEmergByMnginstcd
	 * @작성일 : 2023. 01. 04.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 지자체별 긴급차량 운행 현황 목록 조회
	 * @return List<ScsEmrgVhclPathLog> 
	 */	
	List<Map<String,Object>> findEmergByMnginstcdList();

	
   /**
    * @Method Name : getEmergByMnginstcdChartDataInfo
    * @작성일 : 2023. 01. 19.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 지자체별 긴급차량 운행 현황 차트 정보 조회
    * @return Map<String,Object>
    */	
	Map<String, Object> findEmergByMnginstcdChartDataInfo();
}
