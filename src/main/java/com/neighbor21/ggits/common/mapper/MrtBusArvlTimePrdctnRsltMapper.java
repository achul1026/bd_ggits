package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtBusArvlTimePrdctnRslt;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface MrtBusArvlTimePrdctnRsltMapper {



    public List<MrtBusArvlTimePrdctnRslt> findAllByRouteIdAndStationId(MapBigdataSearchDTO mapBigdataSearchDTO);



	/**
	  * @Method Name : findAllDataYears
	  * @작성일 : 2023. 11. 21.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 빅데이터 > 대중교통 이용현황 분석 > 버스 도착정보 예측 데이터 연도 목록
	  * @return
	  */
	List<Map<String, Object>> findAllDataYears();
}
