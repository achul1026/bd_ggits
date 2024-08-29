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

}
