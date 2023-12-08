package com.neighbor21.ggits.web.service.login;


import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.LOpPgmLogn;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.mapper.LOpPgmLognMapper;
import com.neighbor21.ggits.common.mapper.MOpGrpInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.PasswordUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class LoginService{
		
	@Autowired
	MOpOperatorMapper mOpOperatorMapper;
	
	@Autowired
	MOpGrpInfoMapper mOpGrpInfoMapper;
	
	@Autowired
	LOpPgmLognMapper lOpPgmLognMapper;
	
	/**
	  * @Method Name : findOneOprtrByEmail
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : 82103
	  * @Method 설명 : 로그인 유저 존재 유무 체크
	  * @return
	  */
	public MOpOperator findOneMOpOperatorByEmail(MOpOperator mOpOperator) {
		String oprtrPswd = mOpOperator.getOprtrPswd();
		
		if(GgitsCommonUtils.isNull(oprtrPswd) || GgitsCommonUtils.isNull(mOpOperator.getOprtrEmail())) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		} 

		MOpOperator mOpOperatorInfo = mOpOperatorMapper.findOneMOpOperatorByEmail(mOpOperator); 
		
		if(mOpOperatorInfo == null) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"유저 정보가 존재하지 않습니다.");
		}
		
		if(!PasswordUtils.checkPassword(oprtrPswd, mOpOperatorInfo.getOprtrPswd())) {
			throw new CommonException(ErrorCode.PASSWORD_MISMATCH);
		}
		
		if(!"SUPER".equals(mOpOperatorInfo.getOprtrGrd())) {
			if(!mOpOperatorInfo.getOprtrSttsCd().equals("OSC002")) { //승인 상태가 아니면
				throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"미승인 상태이거나 중지된 사용자입니다. 관리자에게 문의하세요.");
			}
		}
		
		return mOpOperatorInfo;
	}

	public String findPw(MOpOperator mOpOperator) throws Exception {
		if(GgitsCommonUtils.isNull(mOpOperator.getOprtrNm()) || GgitsCommonUtils.isNull(mOpOperator.getNote())) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		} 
		String orginalPw =  "";
		MOpOperator dbMOpOperator= mOpOperatorMapper.findOneMOpOperatorByEmailAndName(mOpOperator); 
		if(dbMOpOperator != null) {
			Decoder decoder = Base64.getDecoder();
			byte[] encodePw =  decoder.decode(dbMOpOperator.getOprtrPswd());
			orginalPw = new String(encodePw);
		}
		return orginalPw;
	}

	public List<MOpOperator> findAllMOpOperatorByNmAndTel(MOpOperator mOpOperator) throws Exception {

		if(GgitsCommonUtils.isNull(mOpOperator.getOprtrNm()) || GgitsCommonUtils.isNull(mOpOperator.getOprtrTel())) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		} 
		return mOpOperatorMapper.findAllMOpOperatorByNmAndTel(mOpOperator);
	}

	public MOpOperator findOneMOpOperatorByNmAndTelAndId(MOpOperator mOpOperator) {
		
		if(GgitsCommonUtils.isNull(mOpOperator.getOprtrNm()) || GgitsCommonUtils.isNull(mOpOperator.getOprtrTel())) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		} 
		return mOpOperatorMapper.findOneMOpOperatorByNmAndTelAndId(mOpOperator);
	}

	public void updateMOpOperatorPw(MOpOperator mOpOperator ,String newPw) throws Exception {
		String oprtrPswd = newPw;
		
		if(GgitsCommonUtils.isNull(newPw)) {
			throw new CommonException(ErrorCode.PARAMETER_DATA_NULL);
		}
		
		//비밀번호 암호화
		oprtrPswd = PasswordUtils.hashPassword(oprtrPswd); 
		mOpOperator.setOprtrPswd(oprtrPswd);
		//try catch 추가 필요
		mOpOperatorMapper.update(mOpOperator);
	}

	/**
	 * @Method Name : saveLOpPgmLogn
	 * @작성일 : 2023. 8. 30.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 사용자 로그 저장
	 * @param mOpOperatorInfo, req
	 * @return 
	 */
	public void saveLOpPgmLogn(MOpOperator mOpOperatorInfo, HttpServletRequest req, String logType) {
		if(GgitsCommonUtils.isNull(mOpOperatorInfo.getOprtrId())) {
			throw new CommonException(ErrorCode.NOT_FOUNT_USER_INFO);
		}
		
		LOpPgmLogn lOpPgmLogn = new LOpPgmLogn();
		lOpPgmLogn.setLogId(this.getTotalCnt());
		lOpPgmLogn.setOprtrId(mOpOperatorInfo.getOprtrId());
		lOpPgmLogn.setGrpId(mOpOperatorInfo.getGrpId());
		lOpPgmLogn.setPrgrmSesnId(mOpOperatorInfo.getOprtrEmail());
		lOpPgmLogn.setRqstrNm(mOpOperatorInfo.getOprtrNm());
		lOpPgmLogn.setLgnIp(req.getRemoteAddr());
		// 사용자 로그 유형(ULC001: 로그인, ULC002: 로그아웃)
		lOpPgmLogn.setLogType(logType);
		lOpPgmLognMapper.saveLOpPgmLogn(lOpPgmLogn);
	}
	
	public String getTotalCnt() {
		int totalCnt = lOpPgmLognMapper.countAll(); 
		return String.valueOf(totalCnt+1);
	}
}
