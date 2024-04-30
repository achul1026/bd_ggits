package com.neighbor21.ggits.common.mapper;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import com.neighbor21.ggits.common.entity.AdsiSmcrsrdCrsrdAcsRoadStatsOnhr;

@Mapper
public interface AdsiSmcrsrdCrsrdAcsRoadStatsOnhrMapper {

	int countcomunicationList(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

	List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> findAllComunicationList(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

	List<AdsiSmcrsrdCrsrdAcsRoadStatsOnhr> findAllComunicationListWithoutPaging(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

	List<Map<String, Object>> findAllDataYears();

	int countVhclDivInfo(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

	Map<String, Object> findOneVhclDivInfo(AdsiSmcrsrdCrsrdAcsRoadStatsOnhr adsiSmcrsrdCrsrdAcsRoadStatsOnhr);

}
