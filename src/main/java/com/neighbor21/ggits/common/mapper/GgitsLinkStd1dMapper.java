package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.GgitsLinkStd1d;

@Mapper
public interface GgitsLinkStd1dMapper {

    /**
     * @Method Name : findAllLinkStdList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통총괄지표 리스트 조회
     * @param : ggitsLinkStd1d
     * @return
     */
	List<GgitsLinkStd1d> findAllLinkStdList(GgitsLinkStd1d ggitsLinkStd1d);

    /**
     * @Method Name : findAllLinkStdList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통총괄지표 리스트 조회
     * @param : ggitsLinkStd1d
     * @return
     */
	Map<String, Object> trfVolRankMap(GgitsLinkStd1d ggitsLinkStd1d);

    /**
     * @Method Name : findAllLinkStdList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통총괄지표 리스트 조회
     * @param : ggitsLinkStd1d
     * @return
     */
	Map<String, Object> trfSpdRankMap(GgitsLinkStd1d ggitsLinkStd1d);

    /**
     * @Method Name : findAllLinkStdList
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통총괄지표 리스트 조회
     * @param : ggitsLinkStd1d
     * @return
     */
	Map<String, Object> trfCongGradeRankMap(GgitsLinkStd1d ggitsLinkStd1d);
	
    /**
     * @Method Name : countLinkStd
     * @작성일 : 2023. 11. 10.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석 > 교통 DB화 통계 > 교통총괄지표 개수 조회
     * @param : ggitsLinkStd1d
     * @return
     */
	Integer countLinkStd(GgitsLinkStd1d ggitsLinkStd1d);
}
