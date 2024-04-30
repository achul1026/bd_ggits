package com.neighbor21.ggits.common.mapper;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapMonitoringLinkDataDTO;

@Mapper
public interface GgitsLinkStd1HMapper {
	
	/**
	  * @Method Name : findOneDataCntForMonitoring
	  * @작성일 : 2023. 11. 14.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 일일 소통정보 수집건수 조회(연계대상 수집데이터)
	  * @return
	  */
	public MapMonitoringLinkDataDTO findOneDataCntForMonitoring();
}
