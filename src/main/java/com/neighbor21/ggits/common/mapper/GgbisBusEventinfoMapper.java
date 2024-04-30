package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.GgbisBusEventinfo;

@Mapper
public interface GgbisBusEventinfoMapper {
	
	/**
	  * @Method Name : findAllRealTimeBusMoveInfo
	  * @작성일 : 2023. 11. 3.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 시내버스 이동 현황 
	  * @return
	  */
	public List<GgbisBusEventinfo> findAllRealTimeBusMoveInfo(GgbisBusEventinfo ggbisBusEventinfo);
	
	/**
	 * @Method Name : countAllRealTimeBusMoveInfo
	 * @작성일 : 2023. 11. 3.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 시내버스 이동 현황 
	 * @return
	 */
	public int countAllRealTimeBusMoveInfo(GgbisBusEventinfo ggbisBusEventinfo);

	public List<GgbisBusEventinfo> findAllCurrentByRouteId(@Param("routeId") String routeId);
}
