package com.neighbor21.ggits.common.mapper;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdDrctStatsOnhr;

@Mapper
public interface AdsiSmcrsrdCrsrdDrctStatsOnhrMapper {
	
    /**
     * @Method Name : countVhclDivInfo
     * @작성일 : 2023. 8. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 소통정보 통계 차종 총 수 조회
     * @param : adsiSmcrsrdCrsrdDrctStatsOnhr
     * @return
     */
	public int countVhclDivInfo(AdsiSmcrsrdCrsrdDrctStatsOnhr adsiSmcrsrdCrsrdDrctStatsOnhr);

    /**
     * @Method Name : findOneVhclDivInfo
     * @작성일 : 2023. 8. 26.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 소통정보 통계 차종별 대 수 조회
     * @param : adsiSmcrsrdCrsrdDrctStatsOnhr
     * @return
     */
	public Map<String, Object> findOneVhclDivInfo(AdsiSmcrsrdCrsrdDrctStatsOnhr adsiSmcrsrdCrsrdDrctStatsOnhr);

}
