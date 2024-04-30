package com.neighbor21.ggits.common.mapper;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpOperator;

@Mapper
public interface MOpOperatorMapper {
	
	/**
	 * @Method Name : countMOpOperatorByOprtrEmail
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 유저 정보 목록 개수 조회 (이메일)
	 * @param mOpOperator
	 */
	public int countMOpOperatorByOprtrEmail(MOpOperator mOpOperator);
	
	/**
	 * @Method Name : save
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 유저 정보 저장
	 * @param mOpOperator
	 */
	public void save(MOpOperator mOpOperator);
	
	/**
	 * @Method Name : findOneMOpOperatorByEmailAndPw
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 유저 정보 조회 (이메일 , 비밀번호)
	 * @param mOpOperator
	 */
	public MOpOperator findOneMOpOperatorByEmail(MOpOperator mOpOperator);

	/**
	  * @Method Name : findOneMOpOperatorByEmailAndNameAndTel
	  * @작성일 : 2023. 11. 14.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 정보 조회 (이메일 , 이름, 전화번호)
	  * @param mOpOperator
	  * @return
	  */
	public MOpOperator findOneMOpOperatorByEmailAndNameAndTel(MOpOperator mOpOperator);
	
	/**
	 * @Method Name : findOneMOpOperatorByEmailAndName
	 * @작성일 : 2023. 8. 28.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 유저 정보 조회 (이메일 , 이름)
	 * @param mOpOperator
	 */
	public MOpOperator findOneMOpOperatorByEmailAndName(MOpOperator mOpOperator);

	/**
	  * @Method Name : findAllMOpOperatorByNmAndTel
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 정보 조회 (이메일 , 전화번호)
	  * @param mOpOperator
	  * @return List<MOpOperator>
	  */
	public List<MOpOperator> findAllMOpOperatorByNmAndTel(MOpOperator mOpOperator);

	/**
	 * @Method Name : findOneMOpOperatorByNmAndTelAndId
	 * @작성일 : 2023. 9. 4.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 유저 정보 조회 (이메일 , 전화번호)
	 * @param mOpOperator
	 * @return
	 */
	public MOpOperator findOneMOpOperatorByNmAndTelAndId(MOpOperator mOpOperator);
	
	/**
	  * @Method Name : update
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 정보 수정
	  * @param mOpOperator
	  */
	public void update(MOpOperator mOpOperator);

	/**
	  * @Method Name : findAllUserList
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 목록 조회
	  * @param commonEntity
	  * @return
	  */
	public List<MOpOperator> findAllUserList(MOpOperator mOpOperator);

	/**
	  * @Method Name : findOneUserDetailByOprtrId
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 정보 조회 ( OPRTR_ID )
	  * @param mOpOperator
	  * @return
	  */
	public MOpOperator findOneUserDetailByOprtrId(MOpOperator mOpOperator);

	/**
	  * @Method Name : findOneMOpoperatorByOprtrId
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 정보 조회 
	  * @param oprtrId
	  * @return String
	  */
	public String findOneMOpoperatorByOprtrId(Long oprtrId);
	
	/**
	  * @Method Name : findAllUserListByGrpId
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 목록 조회 ( GRP_ID )
	  * @param mOpOperator
	  * @return
	  */
	public List<MOpOperator> findAllUserListByGrpId(MOpOperator mOpOperator);

	/**
	  * @Method Name : updateForGrpInfo
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 그룹 정보 조회 
	  * @param paramMap
	  */
	public void updateForGrpInfo(Map<String,Object> paramMap);
	
	/**
	  * @Method Name : deleteByOprtrId
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 삭제
	  * @param mOpOperator
	  */
	public void deleteByOprtrId(long OprtrId);
	
	
	/**
	  * @Method Name : countAll
	  * @작성일 : 2023. 9. 4.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 유저 목록 개수 조회
	  * @param paramMap
	  * @return
	  */
	public int countAll(MOpOperator mOpOperator);
	
	/**
	  * @Method Name : findLayoutNoByOprtrId
	  * @작성일 : 2023. 10. 24.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 레이아웃 번호조회
	  * @param oprtrId
	  * @return
	  */
	public long findLayoutNoByOprtrId(long oprtrId);
	
	/**
	  * @Method Name : findOneByOprtrIdNextVal
	  * @작성일 : 2023. 11. 02.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 관리자 신규 PK값 조회
	  * @return long OprtrId
	  */
	public long findOneByOprtrIdNextVal();
}
