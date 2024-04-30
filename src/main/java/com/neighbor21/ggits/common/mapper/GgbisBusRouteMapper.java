package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.GgbisBusRoute;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;

@Mapper
public interface GgbisBusRouteMapper {


    GgbisBusRoute findOneByRouteId(@Param("routeId") String routeId);
    /**
     * 정류장 버스노선정보 조회
     * @param stationId 정류장아이디
     * @return
     */
    List<GgbisBusRoute> findAllRouteInfoByStationId(@Param("stationId") String stationId);

    /**
     * @Method Name : findAllBusUserRankList
     * @작성일 : 2023. 10. 31.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 이용현황 분석 > 더 많은 데이터 보기 > 권역별 대중교통 이용현황 순위
     * @param : mapBigdataSearchDTO
     * @return
     */
	public Map<String, Object> findAllBusUserRankList(MapBigdataSearchDTO mapBigdataSearchDTO);

    /**
     * @Method Name : findAllBusTrnsitUserRankList
     * @작성일 : 2023. 10. 31.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 이용현황 분석 > 더 많은 데이터 보기 > 권역별 대중교통 환승현황 순위
     * @param : mapBigdataSearchDTO
     * @return
     */
	public Map<String, Object> findAllBusTrnsitUserRankList(MapBigdataSearchDTO mapBigdataSearchDTO);

    /**
     * @Method Name : countPubTrfRouteInfo
     * @작성일 : 2023. 11. 03.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 대중교통 안전 운행분석 개수 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	int countPubTrfRouteInfo(MapBigdataSearchDTO mapBigdataSearchDTO);

    /**
     * @Method Name : findAllPubTrfRouteInfoList
     * @작성일 : 2023. 11. 03.
     * @작성자 : KC.KIM
     * @Method 설명 : 빅데이터 분석 > 대중교통 위험운영 구간 분석 > 대중교통 안전 운행분석 리스트 조회
     * @param : mapBigdataSearchDTO
     * @return
     */
	List<GgbisBusRoute> findAllPubTrfRouteInfoList(MapBigdataSearchDTO mapBigdataSearchDTO);
}
