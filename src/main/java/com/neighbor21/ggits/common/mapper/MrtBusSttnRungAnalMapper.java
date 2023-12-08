package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusSttnRungAnal;

@Mapper
public interface MrtBusSttnRungAnalMapper {

	/**
	  * @Method Name : countBusSttnRungAnal
	  * @작성일 : 2023. 10. 17.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 데이터 활용 통계 > 이력 집계 > 정류장별 운행이력 정보 집계 개수 조회
	  * @param commonEntity
	  * @return
	  */
	int countBusSttnRungAnal(CommonEntity commonEntity);

	/**
	  * @Method Name : findAllBusSttnRungAnal
	  * @작성일 : 2023. 10. 17.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 데이터 활용 통계 > 이력 집계 > 정류장별 운행이력 정보 집계 리스트 조회
	  * @param commonEntity
	  * @return
	  */
	List<MrtBusSttnRungAnal> findAllBusSttnRungAnal(CommonEntity commonEntity);

}
