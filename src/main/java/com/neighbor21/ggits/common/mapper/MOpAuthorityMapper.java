package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpAuthority;

@Mapper
public interface MOpAuthorityMapper {
	
	/**
	 * @Method Name : findAllAuthList
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한 목록 조회
	 */
	public List<MOpAuthority> findAllAuthList(MOpAuthority mOpAuthority);

	/**
	 * @Method Name : countAllBySearchOption
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 목록 카운트 조회
	 */
	public int countAllBySearchOption(MOpAuthority mOpAutority);

	/**
	 * @Method Name : countByAuthCd
	 * @작성일 : 2023. 12. 20.
	 * @작성자 : KY.LEE
	 * @Method 설명 : authCd로 카운트 조회
	 */
	public int countByAuthCd(String authCd);
	
	/**
	 * @Method Name : findAuthIdNextVal
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한 아이디 value 조회
	 */
	public int findAuthIdNextVal();
	
	/**
	 * @Method Name : saveMOpAuthority
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한 저장
	 */
	public void saveMOpAuthority(MOpAuthority mOpAutority);
	
	/**
	 * @Method Name : findAuthListBySearchOption
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한 목록 조회
	 */
	public List<MOpAuthority> findAuthListBySearchOption(MOpAuthority mOpAutority);
	
	/**
	 * @Method Name : findOneByAuthId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한 단건 조회
	 */
	public MOpAuthority findOneByAuthId(Long authId);
	
	/**
	 * @Method Name : deleteByAuthId
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한 삭제
	 */
	public void deleteByAuthId(Long authId);
	
	/**
	 * @Method Name : updateMOpAuthority
	 * @작성일 : 2023. 9. 5.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 권한 정보 수정
	 */
	public void updateMOpAuthority(MOpAuthority mOpAutority);
}
