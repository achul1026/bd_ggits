package com.neighbor21.ggits.web.service.systemmng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.mapper.LOpPgmLognMapper;
import com.neighbor21.ggits.common.mapper.LTcAcdntLogInfoMapper;
import com.neighbor21.ggits.common.mapper.LTcFcltsLogInfoMapper;
import com.neighbor21.ggits.common.mapper.LTcSrvrLogInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeGrpMapper;

@Service
public class OperateMngService{
	
	@Autowired
	LOpPgmLognMapper lOpPgmLognMapper;
	
	@Autowired
	LTcFcltsLogInfoMapper lTcFcltsLogInfoMapper;
	
	@Autowired
	LTcSrvrLogInfoMapper lTcSrvrLogInfoMapper;
	
	@Autowired
	LTcAcdntLogInfoMapper lTcAcdntLogInfoMapper;
	
	@Autowired
	MOpCodeGrpMapper mOpCodeGrpMapper;


}
