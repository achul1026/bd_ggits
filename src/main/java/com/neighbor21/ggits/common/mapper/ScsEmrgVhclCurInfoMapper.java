package com.neighbor21.ggits.common.mapper;


import com.neighbor21.ggits.common.entity.ScsEmrgVhclCurInfo;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScsEmrgVhclCurInfoMapper {
    List<ScsEmrgVhclCurInfo> findAll();
   
	/**
	 * @Method Name : findTopTableDataForMonitoringDashboard
	 * @작성일 : 2023. 11. 16.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링대시보드 -> 긴급차량 이동현황 테이블 데이터
	 * @return
	 */
    public List<Map<String,Object>> findTopTableDataForMonitoringDashboard();
}
