package com.neighbor21.ggits.common.mapper;
import java.util.List;

import com.neighbor21.ggits.common.entity.AdsiMFaDsrc;
import com.neighbor21.ggits.common.entity.AdsiMFaVds;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapFacilityMenuDTO;

@Mapper
public interface AdsiMFaDsrcMapper {
	
	
	/**
	  * @Method Name : findAllDSRCForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 연계 시설물 조회 > DSRC 목록 
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public List<Object> findAllDSRCForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);
	
	/**
	  * @Method Name : countDSRCForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 :	연계 시설물 조회 > DSRC 목록 개수
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public int countDSRCForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);

	/**
	 * 시설물 DSRC GeoMetric 정보 조회
	 * @return
	 */
	List<AdsiMFaDsrc> findAllDSRCInfoForMap();
}
