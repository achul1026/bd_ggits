package com.neighbor21.ggits.web.service.statistics;

import org.springframework.stereotype.Service;

import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadStatsOnhr;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

@Service
public class TrafficInfoStatisticsAnalysisService {

	public AdsiSmcrsrdCrsrdAcsRoadStatsOnhr chngSearchDatetime(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr) {
		switch (adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getTabNum()) {
		case "1":
			//시간 검색
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt())) {
				adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt() + " " + adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStartTime() + ":01");				
			}
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt())) {
				adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt() + " " + adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndTime() + ":59");				
			}
			break;
		case "2":
			//일 검색
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt())) {
				adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt() + " 00:00:01");
			}
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt())) {
				adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt() + " 23:59:59");
			}
			break;
		case "3":
			//월 검색
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt())) {
				adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt() + "-01 00:00:01");
			}
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt())) {
				if(Integer.parseInt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt().substring(5, 6)) / 2 != 0 || adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt().equals("8")) {
					adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt() + "-31 23:59:59");
					
				}else {
					adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt() + "-30 23:59:59");					
				}
			}
			break;
		case "4":
			//연 검색
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt())) {
				adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setStrDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getStrDt() + "-01-01 00:00:01");
			}
			if(!GgitsCommonUtils.isNull(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt())) {
				adsiSmcrsrdCrsrdAcsRoadStatsOnhr.setEndDt(adsiSmcrsrdCrsrdAcsRoadStatsOnhr.getEndDt() + "-12-31 23:59:59");
			}
			break;
		}
		return adsiSmcrsrdCrsrdAcsRoadStatsOnhr;
	}

}
