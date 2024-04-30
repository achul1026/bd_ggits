package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.GOpDataUseStatsRpt;

@Mapper
public interface GOpDataUseStatsRptMapper {
	
	/**
	 * @Method Name : findOneByRptId
	 * @작성일 : 2023. 11. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 제목 단건 조회
	 */
	public GOpDataUseStatsRpt findOneByRptId(String rptId);
	
	/**
	 * @Method Name : countBySearchOption
	 * @작성일 : 2023. 11. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 제목 카운트 조회
	 */
	public Long countBySearchOption(GOpDataUseStatsRpt gOpDataUseStatsRpt);
	
	/**
	 * @Method Name : saveGOpDataUseStatsRpt
	 * @작성일 : 2023. 11. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 등록
	 */
	public void saveGOpDataUseStatsRpt(GOpDataUseStatsRpt gOpDataUseStatsRpt);
	
	/**
	 * @Method Name : findGOpDataUseStatsRptListBySearchOption
	 * @작성일 : 2023. 11. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 목록 조회
	 */
	public List<GOpDataUseStatsRpt> findGOpDataUseStatsRptListBySearchOption(GOpDataUseStatsRpt gOpDataUseStatsRpt);
	
	/**
	 * @Method Name : deleteGOpDataUseStatsRpt
	 * @작성일 : 2023. 11. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 리포트 삭제
	 */
	public void deleteGOpDataUseStatsRpt(String rptId);
}
