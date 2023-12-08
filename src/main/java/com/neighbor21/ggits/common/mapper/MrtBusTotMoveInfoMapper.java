package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.MrtBusTotMoveInfo;

@Mapper
public interface MrtBusTotMoveInfoMapper {
	/**
	  * @Method Name : countBusTotMoveInfoList
	  * @작성일 : 2023. 10. 23.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 시내버스 이동 현황통계 리스트 개수 조회
	  * @param commonEntity
	  * @return
	  */
	int countBusTotMoveInfoList(CommonEntity commonEntity);

	/**
	  * @Method Name : findAllMrtBusToMoveInfo
	  * @작성일 : 2023. 10. 23.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 시내버스 이동 현황통계 리스트 조회
	  * @param commonEntity
	  * @return
	  */
	List<MrtBusTotMoveInfo> findAllMrtBusToMoveInfo(CommonEntity commonEntity);
}
