package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.GgdtdrBusSttnInfo;

@Mapper
public interface GgdtdrBusSttnInfoMapper {

	/**
	  * @Method Name : countAllBusSttnBusAndFclt
	  * @작성일 : 2023. 10. 27.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 빅데이터 분석 > 대중교통 이용현황 분석 > 정류장별 대중교통 및 노선 시설물 개수 조회
	  * @param : mapBigdataSearchDTO
	  * @return
	  */
	int countAllBusSttnBusAndFclt(MapBigdataSearchDTO mapBigdataSearchDTO);

	/**
	  * @Method Name : findAllBusSttnBusAndFclt
	  * @작성일 : 2023. 10. 27.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 빅데이터 분석 > 대중교통 이용현황 분석 > 정류장별 대중교통 및 노선 시설물 리스트 조회
	  * @param : mapBigdataSearchDTO
	  * @return
	  */
	List<GgdtdrBusSttnInfo> findAllBusSttnBusAndFclt(MapBigdataSearchDTO mapBigdataSearchDTO);

}
