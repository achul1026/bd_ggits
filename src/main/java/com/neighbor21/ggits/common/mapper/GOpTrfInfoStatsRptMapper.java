package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.GOpTrfInfoStatsRpt;

@Mapper
public interface GOpTrfInfoStatsRptMapper {
	
	
	/**
	 * @Method Name : findOneByRptId
	 * @작성일 : 2023. 11. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 제목 단건 조회
	 */
	public GOpTrfInfoStatsRpt findOneByRptId(String rptId);
	
	/**
	 * @Method Name : countByRptTitle
	 * @작성일 : 2023. 11. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 제목 카운트 조회
	 */
	public Long countBySearchOption(GOpTrfInfoStatsRpt gOpTrfInfoStatsRpt);
	
	/**
	 * @Method Name : saveGOpTrfInfoStatsRpt
	 * @작성일 : 2023. 11. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 등록
	 */
	public void saveGOpTrfInfoStatsRpt(GOpTrfInfoStatsRpt gOpTrfInfoStatsRpt);
	
	/**
	 * @Method Name : findGOpTrfInfoStatsRptListBySearchOption
	 * @작성일 : 2023. 11. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 목록 조회
	 */
	public List<GOpTrfInfoStatsRpt> findGOpTrfInfoStatsRptListBySearchOption(GOpTrfInfoStatsRpt gOpTrfInfoStatsRpt);
	
	/**
	 * @Method Name : deleteGOpTrfInfoStatsRpt
	 * @작성일 : 2023. 11. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 삭제
	 */
	public void deleteGOpTrfInfoStatsRpt(String rptId);
	
}
