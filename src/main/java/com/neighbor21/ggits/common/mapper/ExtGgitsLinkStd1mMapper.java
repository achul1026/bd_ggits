package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.CommonEntity;
import com.neighbor21.ggits.common.entity.ExtGgitsLinkStd1m;

@Mapper
public interface ExtGgitsLinkStd1mMapper {

	/**
	  * @Method Name : countTrafficInfoStats
	  * @작성일 : 2023. 10. 29.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 수집 이력 목록 조회
	  * @param commonEntity
	  * @return
	  */
	int countTrafficInfoStats(CommonEntity commonEntity);

	/**
	  * @Method Name : findAllTrafficInfoStats
	  * @작성일 : 2023. 10. 29.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 수집 이력 목록 조회
	  * @param commonEntity
	  * @return
	  */
	List<ExtGgitsLinkStd1m> findAllTrafficInfoStats(CommonEntity commonEntity);

}
