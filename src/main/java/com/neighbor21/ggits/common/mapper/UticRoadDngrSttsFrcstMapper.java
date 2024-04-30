package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.UticRoadDngrSttsFrcst;

@Mapper
public interface UticRoadDngrSttsFrcstMapper {
	
	/**
	 * @param String city 
	 * @Method Name : findTop4DangerZoneStatisticsCity
	 * @작성일 : 2023. 11. 15.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 빅데이터 조회 -> 교통위험 구간 분석 -> 시,군별 차트데이터
	 * @return List<Map<String,Object>>
	 */
	public List<Map<String,Object>> findTop4DangerZoneStatisticsCity(String city);

    List<UticRoadDngrSttsFrcst> findAllBySearchOptionForMapGroupSGG(MapBigdataSearchDTO mapBigdataSearchDTO);

    List<UticRoadDngrSttsFrcst> findAllBySearchOptionForMap(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	 * 설명 : 연도 조회 
	 * author : KY.LEE
	 * date : 2024-01-10
	 * @param findAllDataYears
	 * @return
	 */
    public List<Map<String, Object>> findAllDataYears();
}
