package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtTrfAcdntDngrPrdctn;
import com.neighbor21.ggits.openapi.request.TrafficAccidentPredictionInfoRequest;
import com.neighbor21.ggits.openapi.response.TrafficAccidentPredictionInfoResponse;

@Mapper
public interface MrtTrfAcdntDngrPrdctnMapper {
    List<MrtTrfAcdntDngrPrdctn> findAll();

    List<MrtTrfAcdntDngrPrdctn> findAllBySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);

    List<MrtTrfAcdntDngrPrdctn> findAllBySearchOptionGroupSGG(MapBigdataSearchDTO mapBigdataSearchDTO);
    
    /**
      * @Method Name : findAllBySearchOptionForDataView
      * @작성일 : 2023. 11. 7.
      * @작성자 : NK.KIM
      * @Method 설명 : 빅데이터 > 교통예측분석 > 사고예측 지역 순위 > 더 많은 데이터 보기 조회 
      * @param searchMap
      * @return
      */
    List<Map<String,Object>> findAllBySearchOptionForDataView(Map<String,Object> searchMap);
    
    /**
	  * @Method Name : findAllDataYears
	  * @작성일 : 2023. 11. 8.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 데이터 존재하는 연도 조회
	  * @return
	  */
	List<Map<String,Object>> findAllDataYears();
	
    /**
	  * @Method Name : findTrafficAccidentPredictionInfo
	  * @작성일 : 2023. 12. 11.
	  * @작성자 : KY.LEE
	  * @Method 설명 : OPEN API -> 위험 도로 정보
	  * @param TrafficAccidentPredictionInfoRequest
	  * @return List<TrafficAccidentPredictionInfoResponse> 
	  */
	List<TrafficAccidentPredictionInfoResponse> findTrafficAccidentPredictionInfo(TrafficAccidentPredictionInfoRequest trafficAccidentPredictionInfoRequest);


	   /**
	    * @Method Name : findAcdntPredictionTop10Info
	    * @작성일 : 2023. 01. 04.
	    * @작성자 : KY.LEE
	    * @Method 설명 : 모니터링 대시보드 -> 사고예측구간 지수 top 10
	    */	
	public List<MrtTrfAcdntDngrPrdctn> findAcdntPredictionTop10Info();
	
}
