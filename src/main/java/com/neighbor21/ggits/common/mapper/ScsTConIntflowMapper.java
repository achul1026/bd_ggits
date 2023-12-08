package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapFacilityMenuDTO;
import com.neighbor21.ggits.common.entity.GyCommInfoNode;

@Mapper
public interface ScsTConIntflowMapper {
	
	/**
	  * @Method Name : findAllSignalForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 연계 시설물 조회 > 신호 목록 
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public List<Object> findAllSignalForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);
	
	/**
	  * @Method Name : countSignalForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 :	연계 시설물 조회 > 신호정보 목록 개수
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public int countSignalForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);
	

	/**
	  * @Method Name : findAllNodeListForSearch
	  * @작성일 : 2023. 9. 25.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 키워드 검색 노드 목록
	  * @param gyCommInfoNode
	  * @return
	  */
	public List<Map<String,Object>> findAllSignalNodeList(GyCommInfoNode gyCommInfoNode);
}
