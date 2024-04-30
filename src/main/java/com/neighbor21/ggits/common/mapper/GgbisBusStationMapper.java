package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.GgbisBusStation;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface GgbisBusStationMapper {

    List<GgbisBusStation> findAll();

    List<GgbisBusStation> findAllByRouteId(@Param("routeId") String routeId);

    List<GgbisBusStation> findAllByRouteIds(@Param("routeIds")String[] routeIds);

	Integer countAllBusSttnRouteInfo(MapBigdataSearchDTO mapBigdataSearchDTO);

	List<GgbisBusStation> findAllBusSttnRouteInfo(MapBigdataSearchDTO mapBigdataSearchDTO);

	Integer countAllBusSttnFcltInfo(MapBigdataSearchDTO mapBigdataSearchDTO);

	List<GgbisBusStation> findAllBusSttnFcltInfo(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
	  * @Method Name : countAllBusSttnInfo
	  * @작성일 : 2023. 11. 30.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 빅데이터이 > 대중교통 이용현황 분석 > 정류장별 버스 이용률 정류장 검색 목록 개수
	  * @param mapBigdataSearchDTO
	  * @return
	  */
	Integer countAllBusSttnInfo(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
	  * @Method Name : countAllBusSttnInfo
	  * @작성일 : 2023. 11. 30.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 빅데이터이 > 대중교통 이용현황 분석 > 정류장별 버스 이용률 정류장 검색 목록
	  * @param mapBigdataSearchDTO
	  * @return
	  */
	List<GgbisBusStation> findAllBusSttnInfo(MapBigdataSearchDTO mapBigdataSearchDTO);


	/**
	 * 노선 기점 종점 정류장 조회
	 * @param routeId
	 * @return
	 */
	GgbisBusStation findOneStartEndStationInfoByRouteId(@Param("routeId") String routeId);
}
