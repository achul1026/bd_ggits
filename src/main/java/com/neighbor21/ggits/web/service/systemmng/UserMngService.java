package com.neighbor21.ggits.web.service.systemmng;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MOpGrpInfo;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.enums.OprtrGrd;
import com.neighbor21.ggits.common.mapper.LOpPgmLognMapper;
import com.neighbor21.ggits.common.mapper.LOpUseMenuMapper;
import com.neighbor21.ggits.common.mapper.LTcSrvrLogInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpAuthorityMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpGrpInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMapper;
import com.neighbor21.ggits.common.mapper.MOpOperatorMenuMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.common.util.PasswordUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;

@Service
public class UserMngService{
	

	@Autowired
	MOpOperatorMapper mOpOperatorMapper;
	
	@Autowired
	MOpGrpInfoMapper mOpGrpInfoMapper;

	@Autowired
	MOpAuthorityMapper mOpAuthorityMapper;
	
	@Autowired
	MOpOperatorMenuMapper mOpOperatorMenuMapper;
	
	@Autowired
	LOpPgmLognMapper lOpPgmLognMapper;
	
	@Autowired
	LOpUseMenuMapper lOpUseMenuMapper;

	@Autowired
	LTcSrvrLogInfoMapper lTcSrvrLogInfoMapper;
	
	@Autowired
	MOpCodeMapper mopCodeMapper;
	
	/**
	  * @Method Name : findOneUserDetailByOprtrId
	  * @작성일 : 2023. 8. 28.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 정보 상세 조회
	  * @param oprtrId
	  * @return
	  */
	public MOpOperator findOneUserDetailByOprtrId(long oprtrId) {
		MOpOperator mOpOperator = new MOpOperator();
		mOpOperator.setOprtrId(oprtrId);
		MOpOperator userDetailInfo = mOpOperatorMapper.findOneUserDetailByOprtrId(mOpOperator); 
		if(userDetailInfo == null) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"유저 정보가 존재하지 않습니다.");
		}
		
		if(!GgitsCommonUtils.isNull(userDetailInfo.getAddngCd())) {
			MOpCode mOpCode = new MOpCode();
			mOpCode.setCdId(userDetailInfo.getAddngCd());
			MOpCode dbMOpCode = mopCodeMapper.findOneMOpCodeByCdId(mOpCode);
			if(mOpCode != null) {
				userDetailInfo.setCdNm(dbMOpCode.getCdNm());				
			}
		}
		
		return userDetailInfo;
	}

	/**
	  * @Method Name : updateUserInfo
	  * @작성일 : 2023. 8. 28.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 정보 수정
	  * @param mOpOperator
	  */
	public void updateUserInfo(MOpOperator mOpOperator) {
		
		MOpOperator userDetailInfo = mOpOperatorMapper.findOneUserDetailByOprtrId(mOpOperator);
		if(userDetailInfo == null) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"유저 정보가 존재하지 않습니다.");
		}
		try {
			userDetailInfo.setOprtrNm(mOpOperator.getOprtrNm());
			userDetailInfo.setAddngCd(mOpOperator.getAddngCd());
			userDetailInfo.setOprtrTel(mOpOperator.getOprtrTel());
			userDetailInfo.setGrpId(mOpOperator.getGrpId());
			userDetailInfo.setOprtrSttsCd(mOpOperator.getOprtrSttsCd());
			userDetailInfo.setOprtrTel(GgitsCommonUtils.removeHyphen(mOpOperator.getOprtrTel()));
			
			mOpOperatorMapper.update(userDetailInfo);
		}catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"유저 정보가 수정되지 않았습니다. 관리자에게 문의하세요.");
		}
	}
	
	/**
	 * @Method Name : deleteUserInfo
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 유저 정보 삭제
	 * @param mOpOperator
	 */
	public void deleteUserInfo(MOpOperator mOpOperator) {
		
		try {
			
			long oprtrId = mOpOperator.getOprtrId();

			lOpPgmLognMapper.deleteByOprtrId(oprtrId);
			
			lTcSrvrLogInfoMapper.deleteByOprtrId(oprtrId);

			lOpUseMenuMapper.deleteByOprtrId(oprtrId);
			
			mOpOperatorMapper.deleteByOprtrId(oprtrId);
		}catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"유저 정보가 삭제되지 않았습니다. 관리자에게 문의하세요.");
		}
	}

	/**
	  * @Method Name : findAllGroupList
	  * @작성일 : 2023. 8. 28.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 목록 조회
	  * @param paramMap
	  * @return
	  */
	public List<MOpGrpInfo> findAllGroupList(Map<String, Object> paramMap) {
		MOpGrpInfo mOpGrpInfo = new MOpGrpInfo();
		return mOpGrpInfoMapper.findAllGroupList(mOpGrpInfo);
	}

	/**
	  * @Method Name : findOneGroupDetailByGroupId
	  * @작성일 : 2023. 8. 29.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 그룹 정보 상세 조회
	  * @param groupId
	  * @return
	  */
	public MOpGrpInfo findOneGroupDetailByGrpId(String grpId) {
		MOpGrpInfo mOpGrpInfo = new MOpGrpInfo();
		mOpGrpInfo.setGrpId(grpId);
		MOpGrpInfo groupDetailInfo = mOpGrpInfoMapper.findOneGroupDetailByGrpId(mOpGrpInfo); 
		if(groupDetailInfo == null) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"그룹 정보가 존재하지 않습니다.");
		}
		return groupDetailInfo;
	}
	
	/**
	  * @Method Name : saveUserInfo
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : 82103
	  * @Method 설명 : 관리자 등록
	  * @param paramMap
	  */
	public void saveUserInfo(MOpOperator mOpOperator) {

		//Email 중복 체크
		int emailDupleChk = mOpOperatorMapper.countMOpOperatorByOprtrEmail(mOpOperator);
		if(emailDupleChk > 0) {
			throw new CommonException(ErrorCode.USER_INFO_IS_EXIST);
		}
		
		//관리자 권한 확인
		MOpGrpInfo mOpGrpInfo = new MOpGrpInfo();
		mOpGrpInfo.setBscGrpYn("Y");//기본 그룹
		mOpGrpInfo.setUpperOprtrAuthGrpYn(mOpOperator.getUpperOprtrAuthGrpYn());
		MOpGrpInfo operatorGrpInfo = mOpGrpInfoMapper.findOneMOpGrpInfoForDefaultGrp(mOpGrpInfo);
		
		if(GgitsCommonUtils.isNull(operatorGrpInfo.getGrpId())) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"일반 관리자 기본 그룹이 존재하지 않습니다. 생성 후 삭제해 주세요.");
		}
		
		try {
			
			//비밀번호 암호화
			String oprtrPswd = PasswordUtils.hashPassword(mOpOperator.getOprtrPswd()); 
			
			// 유저 상태 코드 
			String oprtrSttsCd = "OSC001"; //OSC001 : 미승인 , OSC002 : 승인 , OSC003 : 중지 
			mOpOperator.setOprtrGrd(OprtrGrd.GENERAL_ADMIN.getGrade());
			mOpOperator.setOprtrPswd(oprtrPswd);
			mOpOperator.setOprtrSttsCd(oprtrSttsCd);
			mOpOperator.setGrpId(operatorGrpInfo.getGrpId());
			mOpOperator.setOprtrTel(GgitsCommonUtils.removeHyphen(mOpOperator.getOprtrTel()));
			mOpOperatorMapper.save(mOpOperator);
			
		} catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_SAVE_FAIL,"관리자 등록 실패했습니다.");    
		}
	}
	
	/**
	* @Method Name : saveAdminInfo
	* @작성일 : 2023. 9. 07.
	* @작성자 : 82103
	* @Method 설명 : 슈퍼 관리자 등록
	* @param paramMap
	*/
	public void saveAdminInfo(MOpOperator mOpOperator) {
		
		//Email 중복 체크
		int emailDupleChk = mOpOperatorMapper.countMOpOperatorByOprtrEmail(mOpOperator);
		if(emailDupleChk > 0) {
			throw new CommonException(ErrorCode.USER_INFO_IS_EXIST);
		}
		
		//최초 메뉴 
		
		//관리자 기본 그룹 확인
		Map<String,Object> defaultGrpInfoCheck = mOpGrpInfoMapper.findOneDefaultGrpCheck(); 
		
		if(defaultGrpInfoCheck != null) {
			for(String defaultGrpKey : defaultGrpInfoCheck.keySet()) {
				String defaultGrpChk 		=  "FALSE";
				if("upperGrpChk".equals(defaultGrpKey)) {
					defaultGrpChk 		= defaultGrpInfoCheck.get(defaultGrpKey) 	!= null ? (String)defaultGrpInfoCheck.get(defaultGrpKey) 	: "FALSE";
				}
				if("generalGrpChk".equals(defaultGrpKey)) {
					defaultGrpChk 		= defaultGrpInfoCheck.get(defaultGrpKey) 	!= null ? (String)defaultGrpInfoCheck.get(defaultGrpKey) 	: "FALSE";
				}
				
				if("TRUE".equals(defaultGrpChk)) {
					
				}
			}
		}
	}
	
	/**
	  * @Method Name : updateUserPswd
	  * @작성일 : 2023. 8. 22.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 비밀번호 수정
	  * @param paramMap
	  */
	public void updateUserPswd(MOpOperator mOpOperator) {
		
		MOpOperator updateUserInfo = mOpOperatorMapper.findOneUserDetailByOprtrId(mOpOperator);
		if(updateUserInfo == null) {
			throw new CommonException(ErrorCode.USER_INFO_IS_EXIST);
		}
		
		try {
			
			//비밀번호 암호화
			String oprtrPswd = PasswordUtils.hashPassword(mOpOperator.getOprtrPswd()); 
			updateUserInfo.setOprtrPswd(oprtrPswd);
			mOpOperatorMapper.update(updateUserInfo);
			
		} catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_SAVE_FAIL,"관리자 등록 실패했습니다.");    
		}
	}
	/**
	  * @Method Name : updateGrpInfo
	  * @작성일 : 2023. 8. 31.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 정보 수정
	  * @param mOpGrpInfo
	  * @param oprtrIdArr
	  */
	public void updateGrpInfo(MOpGrpInfo mOpGrpInfo,String strOprtrIdArr) {
		try {
			
			MOpGrpInfo groupDetail = mOpGrpInfoMapper.findOneGroupDetailByGrpId(mOpGrpInfo);
			if(groupDetail == null) {
				throw new CommonException(ErrorCode.ENTITY_DATA_NULL, "그룹 정보가 존재하지 않습니다.");
			}
			groupDetail.setGrpNm(mOpGrpInfo.getGrpNm());
			groupDetail.setGrpDescr(mOpGrpInfo.getGrpDescr());
			groupDetail.setAuthId(mOpGrpInfo.getAuthId());
			mOpGrpInfoMapper.update(groupDetail);
			updateUserGrpInfo(mOpGrpInfo.getGrpId(),strOprtrIdArr,"UPDATE");		 
			
		} catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"그룹정보 수정 실패했습니다.");
		}
	}
	/**
	 * @Method Name : saveGrpInfo
	 * @작성일 : 2023. 8. 31.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 그룹 정보 수정
	 * @param mOpGrpInfo
	 * @param oprtrIdArr
	 */
	public void saveGrpInfo(MOpGrpInfo mOpGrpInfo,String strOprtrIdArr) {
		Map<String,Object> defaultGrpInfoCheck = mOpGrpInfoMapper.findOneDefaultGrpCheck();
		if(defaultGrpInfoCheck != null) {
			String upperGrpChk 		= defaultGrpInfoCheck.get("upperGrpChk") 	!= null ? (String)defaultGrpInfoCheck.get("upperGrpChk") 	: "FALSE";
			String generalGrpChk 	= defaultGrpInfoCheck.get("generalGrpChk") 	!= null ? (String)defaultGrpInfoCheck.get("generalGrpChk") 	: "FALSE";
			if("Y".equals(mOpGrpInfo.getBscGrpYn())) {
				if("Y".equals(mOpGrpInfo.getUpperOprtrAuthGrpYn()) && !"TRUE".equals(upperGrpChk)) {
					throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"상위 운영자 기본 그룹은 1개 이상 등록할 수 없습니다.");
				}
				if("N".equals(mOpGrpInfo.getUpperOprtrAuthGrpYn()) && !"TRUE".equals(generalGrpChk)) {
					throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"일반 운영자 기본 그룹은 1개 이상 등록할 수 없습니다.");
				}
			}
		}
		
		try {
			if(GgitsCommonUtils.isNull(mOpGrpInfo.getBscGrpYn())) {
				mOpGrpInfo.setBscGrpYn("N");
			}
			if(GgitsCommonUtils.isNull(mOpGrpInfo.getUpperOprtrAuthGrpYn())) {
				mOpGrpInfo.setUpperOprtrAuthGrpYn("N");
			}
			mOpGrpInfoMapper.save(mOpGrpInfo);
			updateUserGrpInfo(mOpGrpInfo.getGrpId(),strOprtrIdArr,"SAVE");
		} catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"그룹정보 등록 실패했습니다.");
		}
	}
	
	/**
	 * @Method Name : deleteGrpInfo
	 * @작성일 : 2023. 8. 31.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 그룹 정보 삭제
	 * @param mOpGrpInfo
	 */
	public void deleteGrpInfo(MOpGrpInfo mOpGrpInfo, String strOprtrIdArr) {
		
		//기본 그룹, 일반 관리자 그룹 DB 조회
		MOpGrpInfo defaultGrpInfo = new MOpGrpInfo();
		defaultGrpInfo.setBscGrpYn("Y");//기본 그룹
		defaultGrpInfo.setUpperOprtrAuthGrpYn("N");//일반 관리자 그룹
		
		MOpGrpInfo defaultGeneralGrpInfo = mOpGrpInfoMapper.findOneMOpGrpInfoForDefaultGrp(defaultGrpInfo);
		if(defaultGeneralGrpInfo == null) {
			throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"일반 관리자 기본 그룹이 존재하지 않습니다. 생성 후 삭제해 주세요.");
		}
		
		try {
			updateUserGrpInfo(defaultGeneralGrpInfo.getGrpId(),strOprtrIdArr,"DELETE");
			mOpGrpInfoMapper.delete(mOpGrpInfo);
		} catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"그룹정보 삭제 실패했습니다.");
		}
	}
	
	
	/**
	 * @Method Name : updateUserGrpInfo
	 * @작성일 : 2023. 8. 31.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 유저 테이블 그룹 정보 수정
	 * @param mOpGrpInfo
	 */
	public void updateUserGrpInfo(String grpId,String strOprtrIdArr,String type) {
		
		try {
			String[] oprtrIdArr = null;
			
			//UPDATE 시 기존에 DB유저와 파라미터 유저 비교 후 없으면 기본 일반 그룹으로 GRP_ID 수정
			List<MOpOperator> userList = new ArrayList<MOpOperator>();
			List<Long> deleteOprtrIdList = new ArrayList<Long>();
			if("UPDATE".equals(type)) {
				MOpOperator mOpOperator = new MOpOperator();
				mOpOperator.setGrpId(grpId);
				userList = mOpOperatorMapper.findAllUserListByGrpId(mOpOperator);
				//추가할 사용자가 존재하지 않을경우 
				if(userList.size() > 0) {
					deleteOprtrIdList = userList.stream().map(x -> x.getOprtrId()).collect(Collectors.toList());  
				}
			}
			
			//선택된 사용자 ID 목록 
			if(!strOprtrIdArr.isEmpty()) {
				oprtrIdArr = strOprtrIdArr.split(",");
				if(oprtrIdArr.length > 0) {
					
					//그룹에 추가 할 유저 목록 OpOperator Update
					List<Long> updateOprtrIdList = Arrays.stream(oprtrIdArr).filter(x -> !GgitsCommonUtils.isNull(x)).map(Long::parseLong).collect(Collectors.toList());
					Map<String,Object> grpInfoUpdateMap = new HashMap<String, Object>();
					
					grpInfoUpdateMap.put("grpId", grpId);
					grpInfoUpdateMap.put("updateOprtrIdList", updateOprtrIdList);
					
					mOpOperatorMapper.updateForGrpInfo(grpInfoUpdateMap);
					
					//UPDATE 시 기존에 DB유저와 파라미터 유저 비교 후 없으면 기본 일반 그룹으로 GRP_ID 수정
					if(userList.size() > 0) {
						deleteOprtrIdList = userList.stream().filter(x -> !updateOprtrIdList.contains(x.getOprtrId())).map(x -> x.getOprtrId()).collect(Collectors.toList());
					}
				}
			}
			
			//삭제할 목록이 존재하면 MOpOperator Update
			if(deleteOprtrIdList.size() > 0) {
				//기본 그룹, 일반 관리자 그룹 DB 조회
				MOpGrpInfo defaultGrpInfo = new MOpGrpInfo();
				defaultGrpInfo.setBscGrpYn("Y");//기본 그룹
				defaultGrpInfo.setUpperOprtrAuthGrpYn("N");//일반 관리자 그룹
				
				MOpGrpInfo defaultGeneralGrpInfo = mOpGrpInfoMapper.findOneMOpGrpInfoForDefaultGrp(defaultGrpInfo);
				if(defaultGeneralGrpInfo == null) {
					throw new CommonException(ErrorCode.ENTITY_DATA_NULL,"일반 관리자 기본 그룹이 존재하지 않습니다. 생성 후 삭제해 주세요.");
				}
				
				Map<String,Object>  mOpOperatorUpdateMap = new HashMap<String, Object>();
				mOpOperatorUpdateMap.put("updateOprtrIdList", deleteOprtrIdList);
				mOpOperatorUpdateMap.put("grpId", defaultGeneralGrpInfo.getGrpId());
				mOpOperatorMapper.updateForGrpInfo(mOpOperatorUpdateMap);
			}
			
		} catch (Exception e) {
			throw new CommonException(ErrorCode.ENTITY_UPDATE_FAIL,"유저 그룹정보가 수정되지 않았습니다.");
		}
	}
}
