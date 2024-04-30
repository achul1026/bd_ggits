package com.neighbor21.ggits.common.mapper;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.FileMngTotInfo;

@Mapper
public interface FileMngTotInfoMapper {
	
	/**
	  * @Method Name : saveFileMngTotInfo
	  * @작성일 : 2023. 11. 20.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 자료관리 파일 등록
	  * @return
	  */
	public void saveFileMngTotInfo(FileMngTotInfo fileMngTotInfo);
	
	/**
	 * @Method Name : findAllFileMngTotListByFileDivCd
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 자료관리 목록 조회
	 * @return
	 */
	public List<FileMngTotInfo> findAllFileMngTotListByFileDivCd(FileMngTotInfo fileMngTotInfo);
	
	/**
	 * @Method Name : countFileMngTotListByFileDivCd
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 자료관리 목록 조회 카운트 조회
	 * @return
	 */
	public Long countFileMngTotListByFileDivCd(FileMngTotInfo fileMngTotInfo);
	
	/**
	 * @Method Name : findOneByFileMngId
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 자료관리 단건 조회
	 * @return
	 */
	public FileMngTotInfo findOneByFileMngId(String fileMngId);
	
	/**
	 * @Method Name : deleteFileMngTotInfo
	 * @작성일 : 2023. 11. 20.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 자료 관리 제거
	 * @return
	 */
	public void deleteFileMngTotInfo(String fileMngId);
	
	
	/**
	  * @Method Name : saveFileMngTotInfo
	  * @작성일 : 2023. 11. 21.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 자료관리 파일 수정
	  * @return
	  */
	public void updateFileMngTotInfo(FileMngTotInfo fileMngTotInfo);
	
}
