package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapFacilityMenuDTO;
import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdInfo;

@Mapper
public interface AdsiSmcrsrdCrsrdInfoMapper {

    public List<AdsiSmcrsrdCrsrdInfo> findAll();

	public List<AdsiSmcrsrdCrsrdInfo> findAllOneHourStats(@Param("mngInstCd") String mngInstCd);
    
    /**
	  * @Method Name : findAllSmartForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 연계 시설물 조회 > 스마트 교차로 목록 
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public List<Object> findAllSmartForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);
	
	/**
	  * @Method Name : countSmartForFacility
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 :	연계 시설물 조회 > 스마트 교차로 목록 개수
	  * @param mapFacilityMenuDTO
	  * @return
	  */
	public int countSmartForFacility(MapFacilityMenuDTO mapFacilityMenuDTO);
	
	/**
	  * @Method Name : findAllForMapList
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 교차로 목록 조회	
	  * @param adsiSmcrsrdCrsrdInfo
	  * @return
	  */
	public List<AdsiSmcrsrdCrsrdInfo> findAllForMapList(AdsiSmcrsrdCrsrdInfo adsiSmcrsrdCrsrdInfo);

	/**
	  * @Method Name : countAll
	  * @작성일 : 2023. 9. 20.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 교차로 목록 개수 조회
	  * @param adsiSmcrsrdCrsrdInfo
	  * @return
	  */
    public int countAllForMapList(AdsiSmcrsrdCrsrdInfo adsiSmcrsrdCrsrdInfo);
}
