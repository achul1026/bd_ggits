package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.MrtSmcTrfPat;

@Mapper
public interface MrtSmcTrfPatMapper {
    /**
     * TODO :: 가데이터 수집 추후 제거
     * @param linkId
     * @param vol
     * @param spd
     */
    void insertDev(@Param("linkId") String linkId, @Param("vol") long vol, @Param("spd") double spd);

    /**
     * 교통량 전체 조회
     * @return
     */
    List<MrtSmcTrfPat> findAll();

    /**
     * 교통량 전체 링크아이디로 그룹
     * @return
     */
    List<MrtSmcTrfPat> findAllGroupByLinkId(MapBigdataSearchDTO mapBigdataSearchDTO);
    
    /**
     * 교통량 전체 링크아이디로 그룹(지도에서 선택)
     * @return
     */
    List<MrtSmcTrfPat> findAllGroupByLinkIdForMap(MapBigdataSearchDTO mapBigdataSearchDTO);
    
    /**
      * @Method Name : findAllDataYears
      * @작성일 : 2023. 10. 5.
      * @작성자 : NK.KIM
      * @Method 설명 : 데이터 연도별 목록 조회
      * @return
      */
    List<Map<String,Object>> findAllDataYears();
    
}
