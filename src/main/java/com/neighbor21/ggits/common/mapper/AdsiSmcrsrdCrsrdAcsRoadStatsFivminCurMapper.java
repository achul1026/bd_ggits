package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur;

@Mapper
public interface AdsiSmcrsrdCrsrdAcsRoadStatsFivminCurMapper {
	/**
	 * @Method Name : findAdsiSmcrsrdColctInfoList
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(스마트 교차로) 리스트 조회
	 */	
	List<AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur> findAdsiSmcrsrdColctInfoList(
			AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur adsiSmcrsrdCrsrdAcsRoadStatsFivminCur);

	/**
	 * @Method Name : findAdsiSmcrsrdColctInfoList
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 수집원별 소통정보(스마트 교차로) 리스트 개수 조회
	 */	
	int countsmcrsrdColctInfo(AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur adsiSmcrsrdCrsrdAcsRoadStatsFivminCur);

	/**
	 * @Method Name : findSmcrdTop10Info
	 * @작성일 : 2023. 01. 04.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 모니터링 대시보드 -> 스마트교차로 교차로별 top 10 
	 */	
	List<AdsiSmcrsrdCrsrdAcsRoadStatsFivminCur> findSmcrdTop10Info();
	
}
