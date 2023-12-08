package com.neighbor21.ggits.common.mapper;


import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.ScsEmrgVhclLogInfo;
@Mapper
public interface ScsEmrgVhclLogInfoMapper {
	/**
	  * @Method Name : countEmrgVhclLogList
	  * @작성일 : 2023. 9. 25.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 긴급차량 이동 현황 통계 개수 조회
	  * @param commonEntity
	  * @return
	  */
	int countEmrgVhclLogList(CommonEntity commonEntity);

	/**
	  * @Method Name : findAllEmrgVhclLogList
	  * @작성일 : 2023. 9. 25.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 일일 긴급 차량 이동 이력 목록
	  * @param commonEntity
	  * @return
	  */
	List<ScsEmrgVhclLogInfo> findAllEmrgVhclLogList(CommonEntity commonEntity);

}
