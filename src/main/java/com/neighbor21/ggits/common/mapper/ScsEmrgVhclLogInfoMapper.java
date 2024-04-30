package com.neighbor21.ggits.common.mapper;


import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapMonitoringLinkDataDTO;
import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclLogInfo;
@Mapper
public interface ScsEmrgVhclLogInfoMapper {

	/**
	  * @Method Name : findAllEmrgVhclLogList
	  * @작성일 : 2023. 9. 25.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 일일 긴급 차량 이동 이력 목록
	  * @param commonEntity
	  * @return
	  */
	List<ScsEmrgVhclLogInfo> findAllEmrgVhclLogList(CommonEntity commonEntity);
	
	/**
	  * @Method Name : findOneDataCntForMonitoring
	  * @작성일 : 2023. 11. 14.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 일일 긴급차량 데이터 개수 조회 (연계대상 수집 이력)
	  * @return
	  */
	public MapMonitoringLinkDataDTO findOneDataCntForMonitoring();
	
	/**
	  * @Method Name : findOneByChartDataForMonitoringDashboard
	  * @작성일 : 2023. 11. 16.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 모니터링 대시보드 -> 긴급차량 이동현황 데이터 조회
	  * @return
	  */
	public Map<String,Object> findOneByChartDataForMonitoringDashboard(ScsEmrgVhclLogInfo scsEmrgVhclLogInfo);

	/**
	  * @Method Name : findAllEmrgCntMap
	  * @작성일 : 2023. 12. 19.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 통계 분석 > 
	  * @return
	  */
	public Map<String, Object> findAllEmrgCntMap(CommonEntity commonEntity);

	/**
	  * @Method Name : countEmrgVhclLog
	  * @작성일 : 2023. 12. 20.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 일일 긴급 차량 이동 이력 개수 조회
	  * @param commonEntity
	  * @return
	  */
	public int countEmrgVhclLog(CommonEntity commonEntity);

}
