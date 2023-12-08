package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSigCtrlLog;

@Mapper
public interface MrtSigCtrlLogMapper {

	/**
	 * @Method Name : findAllGroupByLinkId
	 * @작성일 : 2023. 10. 16.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 교통활동 효과분석 > 긴급차량 우선 신호시스템 제어효과 
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	List<MrtSigCtrlLog> findAllGroupByLinkId(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * @Method Name : findAllDataYears
	 * @작성일 : 2023. 10. 5.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 데이터 연도별 목록 조회
	 * @return
	 */
	List<Map<String, Object>> findAllDataYears();
}
