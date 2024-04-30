package com.neighbor21.ggits.web.service.systemmng;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MOpCodeGrp;
import com.neighbor21.ggits.common.mapper.MOpCodeGrpMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

@Service
public class CodeMngService{
	
	@Autowired
	MOpCodeGrpMapper mOpCodeGrpMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	MOpOperatorMapper mOpOperatorMapper;

	/**
	  * @Method Name : findAllCodeGrpList
	  * @작성일 : 2023. 8. 30.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 그룹 코드 정보 조회
	  * @param mOpCodeGrp
	  * @return
	  */
	public List<MOpCodeGrp> findAllCodeGrpList(MOpCodeGrp mOpCodeGrp) {
		List<MOpCodeGrp> codeGrpList = new ArrayList<MOpCodeGrp>();
		
		codeGrpList = mOpCodeGrpMapper.findAllCodeGrpList(mOpCodeGrp);
		
		for(MOpCodeGrp codeGrp : codeGrpList) {
			// 등록 및 수정자 정보 조회
			Pattern pt = Pattern.compile("^[0-9]*$");
			if(codeGrp.getUptusrId() != null && codeGrp.getCrtusrId() != null) {
				Matcher crtusrIdMc = pt.matcher(codeGrp.getCrtusrId());
				Matcher uptusrIdMc = pt.matcher(codeGrp.getUptusrId());

				if (!GgitsCommonUtils.isNull(codeGrp.getCrtusrId()) && crtusrIdMc.find()) {
					String crtusrId = mOpOperatorMapper.findOneMOpoperatorByOprtrId(Long.parseLong(codeGrp.getCrtusrId()));
					codeGrp.setCrtusrId(crtusrId);
				}
				if (!GgitsCommonUtils.isNull(codeGrp.getUptusrId()) && uptusrIdMc.find()) {
					String uptusrId = mOpOperatorMapper.findOneMOpoperatorByOprtrId(Long.parseLong(codeGrp.getUptusrId()));
					codeGrp.setUptusrId(uptusrId);
				}
			}
		}
		
		return codeGrpList;
	}

	/**
	  * @Method Name : findAllCodeList
	  * @작성일 : 2023. 8. 30.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 그룹 코드 정보 조회
	  * @param mOpCode
	  * @return
	  */
	public List<MOpCode> findAllCodeList(MOpCode mOpCode) {
		List<MOpCode> codeList = new ArrayList<MOpCode>();
		
		codeList = mOpCodeMapper.findAllCodeList(mOpCode);
		
		for(MOpCode code : codeList) {
			// 등록 및 수정자 정보 조회
			Pattern pt = Pattern.compile("^[0-9]*$");
			Matcher crtusrIdMc = pt.matcher(code.getCrtusrId());
			Matcher uptusrIdMc = pt.matcher(code.getUptusrId());
			if(!GgitsCommonUtils.isNull(code.getCrtusrId()) && crtusrIdMc.find()) {
				String crtusrId = mOpOperatorMapper.findOneMOpoperatorByOprtrId(Long.parseLong(code.getCrtusrId()));				
				code.setCrtusrId(crtusrId);
			}
			if(!GgitsCommonUtils.isNull(code.getUptusrId()) && uptusrIdMc.find()) {
				String uptusrId = mOpOperatorMapper.findOneMOpoperatorByOprtrId(Long.parseLong(code.getUptusrId()));				
				code.setUptusrId(uptusrId);
			}
		}
		
		return codeList;
	}
}
