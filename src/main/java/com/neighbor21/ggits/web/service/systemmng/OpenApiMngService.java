package com.neighbor21.ggits.web.service.systemmng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.MOpOpenApiInfo;
import com.neighbor21.ggits.common.mapper.MOpOpenApiInfoMapper;

@Service
public class OpenApiMngService{

	@Autowired
	MOpOpenApiInfoMapper mOpOpenApiInfoMapper;
	
	public List<MOpOpenApiInfo> getOpenApiInfoList(MOpOpenApiInfo MOpOpenApiInfo){
		return mOpOpenApiInfoMapper.findAllMOpOpenApiInfo(MOpOpenApiInfo);
	};

	public MOpOpenApiInfo getOpenApiInfoDetail(String apiId){
		return mOpOpenApiInfoMapper.findOneByApiId(apiId);
	};
	

}
