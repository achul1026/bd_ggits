package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.LTcSrvrProcsCtrl;

@Mapper
public interface LTcSrvrProcsCtrlMapper {

	public List<LTcSrvrProcsCtrl> findLTcSrvrProcsCtrl(LTcSrvrProcsCtrl lTcSrvrProcsCtrl);
	
}
