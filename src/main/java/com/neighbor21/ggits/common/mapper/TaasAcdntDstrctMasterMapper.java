package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;
import com.neighbor21.ggits.common.entity.TaasAcdntDstrctMaster;

@Mapper
public interface TaasAcdntDstrctMasterMapper {

    public List<TaasAcdntDstrctMaster> findAll(MapBigdataSearchDTO mapBigdataSearchDTO);
    
	public List<Map<String, Object>> findAllDataYears();

	/**
	 * 설명 : 구역 별 총 사고 건수 조회 
	 * author : KY.LEE
	 * date : 2023-11-08
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	public List<Map<String,Object>> findAccidentCntAdstdgCd(MapBigdataSearchDTO mapBigdataSearchDTO);
	
	/**
	 * 설명 : 시,군별 사고 별 건수 조회 쿼리
	 * author : KY.LEE
	 * date : 2023-11-08
	 * @param mapBigdataSearchDTO
	 * @return
	 */
	public List<TaasAcdntDstrctMaster> findAcdntStatisticsByAdstdgCd(MapBigdataSearchDTO mapBigdataSearchDTO);
}
