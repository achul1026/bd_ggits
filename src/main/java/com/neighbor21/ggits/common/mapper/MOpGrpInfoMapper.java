package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpGrpInfo;

@Mapper
public interface MOpGrpInfoMapper {
	
	/**
	  * @Method Name : findOneMOpGrpInfoForDefaultGrp
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹정보 조회
	  * @param mOpAuthority
	  * @return
	  */
	public MOpGrpInfo findOneMOpGrpInfoForDefaultGrp(MOpGrpInfo mOpGrpInfo);
	
	/**
	  * @Method Name : findAllGroupList
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 목록 조회
	  * @param mOpGrpInfo
	  * @return
	  */
	public List<MOpGrpInfo> findAllGroupList(MOpGrpInfo mOpGrpInfo);

	
	/**
	  * @Method Name : countGroupList
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 목록 개수 조회
	  * @param mOpGrpInfo
	  * @return
	  */
	public int countAll(MOpGrpInfo mOpGrpInfo);
	/**
	  * @Method Name : findOneGroupDetailByGrpId
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 정보 조회 ( GRP_ID )
	  * @param mOpGrpInfo
	  * @return
	  */
	public MOpGrpInfo findOneGroupDetailByGrpId(MOpGrpInfo mOpGrpInfo);

	/**
	  * @Method Name : update
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그릅 정보 수정
	  * @param mOpGrpInfo
	  */
	public void update(MOpGrpInfo mOpGrpInfo);

	/**
	  * @Method Name : save
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 정보 저장
	  * @param mOpGrpInfo
	  */
	public void save(MOpGrpInfo mOpGrpInfo);

	/**
	  * @Method Name : delete
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 그룹 정보 삭제
	  * @param mOpGrpInfo
	  */
	public void delete(MOpGrpInfo mOpGrpInfo);
	
	/**
	  * @Method Name : findOneDefaultGrpCheck
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 기본 그룹 존재여부 체크
	  */
	public Map<String,Object> findOneDefaultGrpCheck();
	
	/**
	 * @Method Name : existsByAuthId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한ID로 그룹 존재 여부 체크
	 */
	public Long existsByAuthId(Long authId);
}
