package com.neighbor21.ggits.common.mapper;

import com.neighbor21.ggits.common.entity.LOpExtnlAsctnTblCrtLog;
import com.neighbor21.ggits.common.entity.LTcSrvrProcsCtrl;
import org.apache.ibatis.annotations.Param;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import java.util.List;

@Mapper
public interface LOpExtnlAsctnTblCrtLogMapper {

	public LOpExtnlAsctnTblCrtLog findOneByExtnlAsctnTblNm(@Param("extnlAsctnTblNm") String extnlAsctnTblNm);

	public void insert(LOpExtnlAsctnTblCrtLog lOpExtnlAsctnTblCrtLog);

	public void update(LOpExtnlAsctnTblCrtLog lOpExtnlAsctnTblCrtLog);
	
}
