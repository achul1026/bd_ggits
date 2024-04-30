package com.neighbor21.ggits.api.module.bigdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neighbor21.ggits.common.entity.MrtSmcAbnLos;
import com.neighbor21.ggits.common.mapper.MrtSmcAbnLosMapper;
import com.neighbor21.ggits.common.mapper.MrtTrfHlctcCngstnSctnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSmcTrfPat;
import com.neighbor21.ggits.common.mapper.MrtSmcTrfPatMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

/**
 * 빅데이터-교통패턴분석 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  BDPatternComponent
 * @since : 2023-09-07
 */
@Component
public class BDPatternComponent extends BaseMapDataComponent {

    @Autowired
    MrtSmcTrfPatMapper mrtSmcTrfPatMapper;

    @Autowired
    MrtSmcAbnLosMapper mrtSmcAbnLosMapper;
    
    /**
     * 교통량조회
     */
    public List<MrtSmcTrfPat> getTrafficQuantityInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
    	List<MrtSmcTrfPat> result = new ArrayList<>();
    	
    	//교차로별 지도에서 선택일 경우 
    	if(!GgitsCommonUtils.isNull(mapBigdataSearchDTO.getSearchCrossroadsType()) 
    			&& mapBigdataSearchDTO.getSearchCrossroadsType().equals("map")) {
    		result = mrtSmcTrfPatMapper.findAllGroupByLinkIdForMap(mapBigdataSearchDTO);
    	}else {
    		result = mrtSmcTrfPatMapper.findAllGroupByLinkId(mapBigdataSearchDTO);
    	}
    	
        return result;
    }

    /**
     * 정체구간조회
     */
    public List<MrtSmcAbnLos> getTrafficAbnLos(MapBigdataSearchDTO mapBigdataSearchDTO){

        //교차로별 지도에서 선택일 경우
        List<MrtSmcAbnLos> result = mrtSmcAbnLosMapper.findAllSmcAbnLosListForMap(mapBigdataSearchDTO);


        return result;
    }
    
    
    /**
      * @Method Name : findAllTraficDataYears
      * @작성일 : 2023. 10. 5.
      * @작성자 : NK.KIM
      * @Method 설명 : 교통량 데이터 연도 목록 조회
      * @return
      */
    public List<Map<String,Object>> findAllDataYears(String menuType){
    	
    	return mrtSmcTrfPatMapper.findAllDataYears();
    }
}
