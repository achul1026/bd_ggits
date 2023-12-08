package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.MOpCodeGrp;

@Mapper
public interface MOpCodeGrpMapper {
	
    /**
     * @Method Name : findAllCodeGrpList
     * @작성일 : 2023. 8. 22.
      * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 리스트 조회
     * @param : mOpCodeGrp
     * @return
     */
	public List<MOpCodeGrp> findAllCodeGrpList(MOpCodeGrp mOpCodeGrp);
	
    /**
     * @Method Name : countCodeGrpList
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 코드 관리 리스트 개수 조회
     * @param : mOpCodeGrp
     * @return
     */
	public int countCodeGrpList(MOpCodeGrp mOpCodeGrp);
	
    /**
     * @Method Name : countCodeGrpByCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 아이디 중복 조회
     * @param : grpCdId
     * @return
     */
	public int countCodeGrpByCdId(String grpCdId);
	
    /**
     * @Method Name : save
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 저장
     * @param : mOpCodeGrp
     * @return
     */
	public void save(MOpCodeGrp mOpCodeGrp);

    /**
     * @Method Name : findOneCodeGrp
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 정보 조회
     * @param : grpCdId
     * @return
     */
	public MOpCodeGrp findOneCodeGrp(String grpCdId);

    /**
     * @Method Name : deleteCodeGrpByGrpCdId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 관리 코드 삭제
     * @param : grpCdId
     * @return
     */
	public void deleteCodeGrpByGrpCdId(String grpCdId);

    /**
     * @Method Name : findOneCodeGrpInfo
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹코드 조회
     * @param : CodeGrpNm
     * @return
     */
	public MOpCodeGrp findOneCodeGrpInfo(String CodeGrpNm);
	
    /**
     * @Method Name : updateCodeGrp
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 수정
     * @param : mOpCodeGrp
     * @return
     */
	public void updateCodeGrp(MOpCodeGrp mOpCodeGrp);

    /**
     * @Method Name : findOneCodeGrpId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 시스템관리 > 그룹 코드 아이디 조회 
     * @param : logType
     * @return
     */
	public String findOneCodeGrpId(String logType);
}
