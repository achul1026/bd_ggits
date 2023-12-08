package com.neighbor21.ggits.web.service.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.mapper.TrfIpcssActPopltnBsuntMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssMeanShareRtMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssNboplMapper;
import com.neighbor21.ggits.common.mapper.TrfIpcssParkngOccurBsuntMapper;

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
	
	/**
     * @Method Name : delectImpactReport
     * @작성일 : 2023. 10. 17.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계 분석 > 교통 DB화 통계 > 교통영향평가 > 선택 삭제
     * @return
	 */
	public void delectImpactReport(String[] ipcssMngNoArr) {
		
		List<String> ipcssMngNoList = new ArrayList<String>(Arrays.asList(ipcssMngNoArr));
		
		for(String ipcssMngNo : ipcssMngNoList) {
			trfIpcssActPopltnBsuntMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);
			
			trfIpcssMeanShareRtMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);
			
			trfIpcssNboplMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);
			
			trfIpcssParkngOccurBsuntMapper.deleteTrafficImpactReportByIpcssMngNo(ipcssMngNo);
		}
		
	}

}
