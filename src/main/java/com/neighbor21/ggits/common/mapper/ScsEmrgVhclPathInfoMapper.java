package com.neighbor21.ggits.common.mapper;


import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.ScsEmrgVhclPathInfo;
@Mapper
public interface ScsEmrgVhclPathInfoMapper {
	
	/**
	  * @Method Name : findAllEmergencyList
	  * @작성일 : 2023. 9. 19.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 일일 긴급 차량 이동 이력 목록
	  * @param paramMap
	  * @return
	  */
	public List<ScsEmrgVhclPathInfo> findAllEmergencyList(Map<String,Object> paramMap);
	
}
