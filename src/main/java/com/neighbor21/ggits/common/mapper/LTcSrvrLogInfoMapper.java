package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.LTcSrvrLogInfo;

@Mapper
public interface LTcSrvrLogInfoMapper {
	/**
	  * @Method Name : countSrvrLogBySearchInfo
	  * @작성일 : 2023. 9. 5.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 서버 로그 개수 조회 
	  * @param lTcSrvrLogInfo
	  */
	public int countSrvrLogBySearchInfo(LTcSrvrLogInfo lTcSrvrLogInfo);
	
	/**
	  * @Method Name : findAllSrvrLogList
	  * @작성일 : 2023. 9. 5.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 서버 로그 리스트 조회
	  * @param lTcSrvrLogInfo
	  */
	public List<LTcSrvrLogInfo> findAllSrvrLogList(LTcSrvrLogInfo lTcSrvrLogInfo);

	/**
	  * @Method Name : findOneLTcSrvrLogInfoByLogId
	  * @작성일 : 2023. 9. 5.
	  * @작성자 : KC.KIM
	  * @Method 설명 : 서버 로그 상세 조회
	  * @param logId
	  */
	public LTcSrvrLogInfo findOneLTcSrvrLogInfoByLogId(String logId);
	
	/**
	  * @Method Name : deleteByOprtrId
	  * @작성일 : 2023. 9. 5.
	  * @작성자 : NK.KIM
	  * @Method 설명 : 서버 로그 삭제
	  * @param oprtrId
	  */
	public void deleteByOprtrId(long oprtrId);
}
