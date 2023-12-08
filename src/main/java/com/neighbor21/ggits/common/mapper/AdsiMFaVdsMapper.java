package com.neighbor21.ggits.common.mapper;
import java.util.List;

import com.neighbor21.ggits.common.entity.AdsiMFaVds;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapFacilityMenuDTO;

@Mapper
public interface AdsiMFaVdsMapper {
	
	/**
	  * @Method Name : findAllForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 연계 시설물 조회 > VDS 목록 
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public List<Object> findAllVDSForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);
	
	/**
	  * @Method Name : countForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 :	연계 시설물 조회 > VDS 목록 개수
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public int countVDSForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);

	/**
	 * 연계 시설물 조회 > VDS geometric정보 조회
	 * @return
	 */
	List<AdsiMFaVds> findAllVdsInfoForMap();
}
