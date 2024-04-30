package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtTrfHlctcCngstnSctn;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MrtTrfHlctcCngstnSctnMapper {
	
    public List<Map<String, Object>> findAllDataYears();

    public List<MrtTrfHlctcCngstnSctn> findAllBySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);
    
    /**
     * @Method Name : findSvcCongestionTop10
     * @작성일 : 2023. 01. 23.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 대시보드 -> 도로별 주요 정체구간 TOP 10 
     */	
    public List<MrtTrfHlctcCngstnSctn> findSvcCongestionTop10(MrtTrfHlctcCngstnSctn mrtTrfHlctcCngstnSctn);

    public List<MrtTrfHlctcCngstnSctn> findSvcCongestionTop10BySearchOption(MapBigdataSearchDTO mapBigdataSearchDTO);
    
    /**
     * @Method Name : findRoadDivList
     * @작성일 : 2023. 01. 23.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 대시보드 -> 도로별 주요 정체구간 TOP 10 -> 검색조건
     */	
    public List<String> findRoadDivList();

    /**
     * @Method Name : findRoadDivList
     * @작성일 : 2023. 01. 23.
     * @작성자 : KY.LEE
     * @Method 설명 : 모니터링 대시보드 -> 도로별 주요 정체구간 TOP 10 -> 검색날짜
     */	
    public String findMaxAnlsMm();
    
}
