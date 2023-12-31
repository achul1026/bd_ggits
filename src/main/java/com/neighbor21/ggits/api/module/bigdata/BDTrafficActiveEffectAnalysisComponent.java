package com.neighbor21.ggits.api.module.bigdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSigCrsdTrfAnal;
import com.neighbor21.ggits.common.enums.MapBigdataSubMenuCd;
import com.neighbor21.ggits.common.mapper.MrtSigCrsdTrfAnalMapper;
import com.neighbor21.ggits.common.mapper.MrtSigCtrlLogMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

/**
 * 빅데이터-교통패턴분석 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  BDPatternComponent
 * @since : 2023-09-07
 */
@Component
public class BDTrafficActiveEffectAnalysisComponent extends BaseMapDataComponent {
    
    @Autowired
    MrtSigCrsdTrfAnalMapper mrtSigCrsdTrfAnalMapper;
    
    @Autowired
    MrtSigCtrlLogMapper mrtSigCtrlLogMapper;
    
    /**
      * @Method Name : getTrafficActiveEffectAnalysis
      * @작성일 : 2023. 10. 16.
      * @작성자 : NK.KIM
      * @Method 설명 :	교통활동 효과분석
      * @param mapBigdataSearchDTO
      * @return
      */
    public <T> List<T> getTrafficActiveEffectAnalysis(MapBigdataSearchDTO mapBigdataSearchDTO){
    	
    	List<?> result = new ArrayList<T>();
    	String menuCode = mapBigdataSearchDTO.getMenuCode(); 
    	
    	if(MapBigdataSubMenuCd.TRAFFIC_EFFECT_CONGESTION_SECTION.getMenuCode().equals(menuCode)) {			//정체구간
			Long endTime = Long.parseLong(mapBigdataSearchDTO.getStartTime()) + 1L;
			if(endTime >= 24) {
				endTime = 0L;
			}
			mapBigdataSearchDTO.setEndTime(endTime > 10 ? String.valueOf(endTime) : "0"+String.valueOf(endTime));
    		result = mrtSigCrsdTrfAnalMapper.findAllGroupByLinkId(mapBigdataSearchDTO);
    	}else if(MapBigdataSubMenuCd.TRAFFIC_EFFECT_EMERGENCY_VEHICLE.getMenuCode().equals(menuCode)) {	
    		//긴급차량 우선 신호시스템 제어 효과
    		//TODO:: 테이블 데이터 확인 후 컬럼 결과값 재수정
    		result = mrtSigCtrlLogMapper.findAllGroupByLinkId(mapBigdataSearchDTO);
    	}
    	
        return (List<T>) result;
    }

	public <T> List<T> getTrafficActiveEffectAnalysisMerge(MapBigdataSearchDTO mapBigdataSearchDTO){

		List<?> result = new ArrayList<T>();
		String menuCode = mapBigdataSearchDTO.getMenuCode();

		if(MapBigdataSubMenuCd.TRAFFIC_EFFECT_CONGESTION_SECTION.getMenuCode().equals(menuCode)) {			//정체구간
			result = mrtSigCrsdTrfAnalMapper.findAllMergeDataGroupByLinkId(mapBigdataSearchDTO);
		}else if(MapBigdataSubMenuCd.TRAFFIC_EFFECT_EMERGENCY_VEHICLE.getMenuCode().equals(menuCode)) {
			//긴급차량 우선 신호시스템 제어 효과
			//TODO:: 테이블 데이터 확인 후 컬럼 결과값 재수정
			result = mrtSigCtrlLogMapper.findAllGroupByLinkId(mapBigdataSearchDTO);
		}

		return (List<T>) result;
	}
    
    /**
      * @Method Name : findAllDataYears
      * @작성일 : 2023. 10. 5.
      * @작성자 : NK.KIM
      * @Method 설명 : 교통량 데이터 연도 목록 조회
      * @return
      */
    public List<Map<String,Object>> findAllDataYears(String menuType){
    	List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
    	
    	//정체구간 개선효과
    	if(menuType.endsWith("002")) {
    		resultList = mrtSigCrsdTrfAnalMapper.findAllDataYears();
    	}else if(menuType.endsWith("003")) {
    		resultList = mrtSigCtrlLogMapper.findAllDataYears();
    	}
    	
    	return resultList;
    }
}
