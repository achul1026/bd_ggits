package com.neighbor21.ggits.common.mapper;
import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.LTcFcltsLogInfo;

@Mapper
public interface LTcFcltsLogInfoMapper {
    /**
     * @Method Name : countFcltsLogBySearchInfo
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 시설 로그 개수 조회
     * @param : lTcFcltsLogInfo
     * @return
     */
	public int countFcltsLogBySearchInfo(LTcFcltsLogInfo lTcFcltsLogInfo);

    /**
     * @Method Name : findAllFcltsLogList
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 시설 로그 리스트 조회
     * @param : lTcFcltsLogInfo
     * @return
     */
	public List<LTcFcltsLogInfo> findAllFcltsLogList(LTcFcltsLogInfo lTcFcltsLogInfo);
	
    /**
     * @Method Name : findOneLTcFcltsLogInfoByLogId
     * @작성일 : 2023. 8. 22.
     * @작성자 : KC.KIM
     * @Method 설명 : 운영관리 > 사고 로그 상세 조회
     * @param : logId
     * @return
     */
	public LTcFcltsLogInfo findOneLTcFcltsLogInfoByLogId(String logId);
}
