package com.neighbor21.ggits.web.service.statistics;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.mapper.TrfIpcssActPopltnBsuntMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssCrsrdTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssEtcTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssExmnBizInfoMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssMeanShareRtMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssNboplMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssParkngOccurBsuntMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssStrsctTrfvlmMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssTimeInoutflExmnMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssTimePassDistrbMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;

@Service
public class TrafficDatabaseStatisticsService {

	@Autowired
	TrfIpcssActPopltnBsuntMapper trfIpcssActPopltnBsuntMapper;
	
	@Autowired
	TrfIpcssMeanShareRtMapper trfIpcssMeanShareRtMapper;
	
	@Autowired
	TrfIpcssNboplMapper trfIpcssNboplMapper;
	
	@Autowired
	TrfIpcssParkngOccurBsuntMapper trfIpcssParkngOccurBsuntMapper;
	
	@Autowired
	TrfIpcssCrsrdTrfvlmMapper trfIpcssCrsrdTrfvlmMapper;
	
	@Autowired
	TrfIpcssStrsctTrfvlmMapper trfIpcssStrsctTrfvlmMapper;
	
	@Autowired
	TrfIpcssEtcTrfvlmMapper trfIpcssEtcTrfvlmMapper;
	
	@Autowired
	TrfIpcssTimeInoutflExmnMapper trfIpcssTimeInoutflExmnMapper;
	
	@Autowired
	TrfIpcssTimePassDistrbMapper trfIpcssTimePassDistrbMapper;
	
	@Autowired
	TrfIpcssExmnBizInfoMapper trfIpcssExmnBizInfoMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	/**
     * @Method Name : delectImpactReport
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 선택 삭제
     * @return
	 */
	public void delectImpactReport(String[] ipcssMngNoArr) throws SQLException {
		List<String> ipcssMngNoList = new ArrayList<String>(Arrays.asList(ipcssMngNoArr));
							
		for(String ipcssMngNo : ipcssMngNoList) {
			trfIpcssCrsrdTrfvlmMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);		// 교통 영향평가 교차로 교통량
			trfIpcssStrsctTrfvlmMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);		// 교통 영향평가 가로구간 교통량
			trfIpcssEtcTrfvlmMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);			// 교통 영향평가 기타 교통량
			trfIpcssActPopltnBsuntMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);		// 교통 영향평가 활동 인구 원단위
			trfIpcssMeanShareRtMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);		// 교통 영향평가 수단 분담율
			trfIpcssNboplMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);				// 교통 영향평가 재차인원
			trfIpcssTimeInoutflExmnMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);	// 교통 영향평가 시간대 유출입 조사
			trfIpcssParkngOccurBsuntMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);	// 교통 영향평가 주차 발생 원단위
			trfIpcssTimePassDistrbMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);		// 교통 영향평가 시간대별 통행분포
			
			trfIpcssExmnBizInfoMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);		// 교통 영향평가 조사 사업 정보
		}
		
	}
}
