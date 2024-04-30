package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiVdsColctInfo;

@Mapper
@CacheNamespace(flushInterval = 60000)
public interface AdsiVdsColctInfoMapper {


	/**
	 * VDS별 수집정보 조회
	 * @param vdsId
	 * @return
	 */
	public List<AdsiVdsColctInfo> findRecentListByVdsID(@Param("vdsId") String vdsId, @Param("mngInstCd") String mngInstCd);

}
