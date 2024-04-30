package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1d;

@Mapper
public interface ExtGgitsLinkStd1dMapper {
	
	/**
	  * @Method Name : findAllTrafficInfoStats
	  * @작성일 : 2023. 10. 30.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 교통 지표 총괄 통계 > 교통현황통계 리스트 조회
	  * @param commonEntity
	  * @return
	  */
	List<ExtGgitsLinkStd1d> findAllTrafficInfoStats(CommonEntity commonEntity);

	/**
	  * @Method Name : findAllTraffcAnalList
	  * @작성일 : 2023. 10. 30.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 교통 지표 총괄 통계 > 교통현황통계 자료 조회
	  * @param commonEntity
	  * @return
	  */
	List<ExtGgitsLinkStd1d> findAllTraffcAnalList(CommonEntity commonEntity);

	/**
	  * @Method Name : countTrafficInfoStats
	  * @작성일 : 2023. 12. 20.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 통계분석 > 교통정보 통계 분석 > 교통 지표 총괄 통계 > 교통현황통계 개수 조회
	  * @param commonEntity
	  * @return
	  */
	int countTrafficInfoStats(CommonEntity commonEntity);
}
