package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtDynmcPopltnCell500Rslt;
import org.apache.ibatis.annotations.CacheNamespace;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MrtDynmcPopltnCell500RsltMapper {

    /**
     * 기간에 맞는 유동인구 예측 조회
     * @param mapBigdataSearchDTO
     * @return
     */
    List<MrtDynmcPopltnCell500Rslt> findAllBySearchDto(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtDynmcPopltnCell500Rslt> findAllBySearchDtoMax(MapBigdataSearchDTO mapBigdataSearchDTO);

    /**
     * 기간에 맞는 유동인구 예측 조회(차트플레이어용)
     * @param mapBigdataSearchDTO
     * @return
     */
    List<MrtDynmcPopltnCell500Rslt> findAllBySearchDtoForChart(MapBigdataSearchDTO mapBigdataSearchDTO);
    List<MrtDynmcPopltnCell500Rslt> findAllBySearchDtoForChartMax(MapBigdataSearchDTO mapBigdataSearchDTO);

    
	Map<String, Object> findAllFlatPopltnRank();

	/**
     * @Method Name : findAllDataYears
     * @작성일 : 2023. 11. 15.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 유동인구 밀집 예측 분석 연도 데이터 조회
     * @param : 
     * @return
     */
	List<Map<String, Object>> findAllDataYears();
	
   /**
    * @Method Name : findPopulationPredictionTop10
    * @작성일 : 2023. 01. 04.
    * @작성자 : KY.LEE
    * @Method 설명 : 모니터링 대시보드 -> 유동인구 밀집 예측 TOP 10
    */	
	List<MrtDynmcPopltnCell500Rslt> findPopulationPredictionTop10();
    List<MrtDynmcPopltnCell500Rslt> findPopulationPredictionTop10Max();
}
