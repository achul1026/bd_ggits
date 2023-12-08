package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.LTcAcdntLogInfo;

@Mapper
public interface LTcAcdntLogInfoMapper {
    /**
     * @Method Name : countAcdntLogBySearchInfo
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사고 로그 개수 조회 
     * @param : lTcAcdntLogInfo
     * @return
     */
	public int countAcdntLogBySearchInfo(LTcAcdntLogInfo lTcAcdntLogInfo);
	
    /**
     * @Method Name : findAllAcdntLogList
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사고 로그 리스트 조회
     * @param : lTcAcdntLogInfo
     * @return
     */
	public List<LTcAcdntLogInfo> findAllAcdntLogList(LTcAcdntLogInfo lTcAcdntLogInfo);

    /**
     * @Method Name : findOneLTcFcltsLogInfoByLogId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사고 로그 상세 조회
     * @param : logId
     * @return
     */
	public LTcAcdntLogInfo findOneLTcFcltsLogInfoByLogId(String logId);
}
