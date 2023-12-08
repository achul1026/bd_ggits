package com.neighbor21.ggits.api.module.monitoring;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.neighbor21.ggits.common.entity.MrtSigCrsdTrfAnal;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdInfoMapper;
import com.neighbor21.ggits.common.mapper.MrtSigCrsdTrfAnalMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.neighbor21.ggits.api.module.BaseMapDataComponent;
import com.neighbor21.ggits.api.module.monitoring.dto.Itemlist;
import com.neighbor21.ggits.api.module.monitoring.dto.Serviceresult;
import com.neighbor21.ggits.common.dto.MapMonitoringMenuDTO;
import com.neighbor21.ggits.common.mapper.AdsiVdsColctInfoMapper;
import com.neighbor21.ggits.common.mapper.MrtSmcTrfPatMapper;

/**
 * 모니터링 교통현황 데이터 컴포넌트
 *
 * @author : Charles Kim
 * @fileName :  MTrafficInfoComponent
 * @since : 2023-09-04
 */
@Component
public class MTrafficComponent extends BaseMapDataComponent {

    private List<Itemlist> loadedTrafficInfoList = null;

    @Autowired
    MrtSmcTrfPatMapper mrtSmcTrfPatMapper;

    @Autowired
    MrtSigCrsdTrfAnalMapper mrtSigCrsdTrfAnalMapper;
    
    @Autowired
    AdsiVdsColctInfoMapper adsiVdsColctInfoMapper;
    
    /**
     * 실시간 지/정체 도로 정보 조회
     * @return
     */
    public List<MrtSigCrsdTrfAnal> getRealtimeTrafficInfo(){
        return mrtSigCrsdTrfAnalMapper.findAllRecent();
    }
    
    /**
     * @Method Name : findOneCumulativeTrafficVolumeByTimeZone
     * @작성일 : 2023. 9. 18.
     * @작성자 : NK.KIM
     * @Method 설명 : 누적 교통량 정보 
     * @param mapMonitoringMenuDTO
     * @return
     */
   public MapMonitoringMenuDTO findOneCumulativeTrafficVolumeByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO){
   	
   	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
   	
   	//시간 대별 누적 교통량 상단 데이트
   	MapMonitoringMenuDTO trafficCntInfo = adsiVdsColctInfoMapper.findOneTrafficVolumeCntByTimeZone(mapMonitoringMenuDTO); 
   	
   	if(trafficCntInfo != null) {
   		resultDto.setAverageCnt(trafficCntInfo.getAverageCnt());
   		resultDto.setCompareCnt(trafficCntInfo.getCompareCnt());
   	}
   	
   	//시간 대별 누적 교통량 그래프 데이트
   	MapMonitoringMenuDTO trafficGraphInfo = adsiVdsColctInfoMapper.findOneTrafficVolumeGraphByTimeZone(mapMonitoringMenuDTO);
   	if(trafficGraphInfo != null) {
       	resultDto.setGraphTime(trafficGraphInfo.getGraphTime());
       	resultDto.setGraphCnt(trafficGraphInfo.getGraphCnt());
   	}
   	
   	return resultDto;
   }
   
   /**
    * @Method Name : findOneAverageEntrainmentSpeedByTimeZone
    * @작성일 : 2023. 9. 18.
    * @작성자 : NK.KIM
    * @Method 설명 : 평균 동행 속도 정보 
    * @param mapMonitoringMenuDTO
    * @return
    */
   public MapMonitoringMenuDTO findOneAverageEntrainmentSpeedByTimeZone(MapMonitoringMenuDTO mapMonitoringMenuDTO){
   	
   	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
   	
   	//시간대별 
   	MapMonitoringMenuDTO trafficCntInfo = adsiVdsColctInfoMapper.findOneTrafficAvgSpeedByTimeZone(mapMonitoringMenuDTO); 
   	
   	if(trafficCntInfo != null) {
   		resultDto.setAverageCnt(trafficCntInfo.getAverageCnt());
   		resultDto.setCompareCnt(trafficCntInfo.getCompareCnt());
   	}
   	
   	//시간 대별 누적 교통량 그래프 데이트
   	MapMonitoringMenuDTO trafficGraphInfo = adsiVdsColctInfoMapper.findOneTrafficAvgSpeedGraphByTimeZone(mapMonitoringMenuDTO);
   	if(trafficGraphInfo != null) {
   		resultDto.setGraphTime(trafficGraphInfo.getGraphTime());
   		resultDto.setGraphCnt(trafficGraphInfo.getGraphCnt());
   	}
   	
   	
   	return resultDto;
   }
	
   /**
    * @Method Name : findOneCumulativeTrafficVolumeByRoad
    * @작성일 : 2023. 9. 18.
    * @작성자 : NK.KIM
    * @Method 설명 : 도로별 누적 교통량 정보 
    * @param mapMonitoringMenuDTO
    * @return
    */
   public MapMonitoringMenuDTO findOneCumulativeTrafficVolumeByRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
   	
   	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
   	
   	//누적 교통량 테이블 데이트
   	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = adsiVdsColctInfoMapper.findAllTrafficVolumeTableByTimeZone(mapMonitoringMenuDTO);
   	if(!trafficTableInfo.isEmpty()) {
   		resultDto.setTrafficList(trafficTableInfo);
   	}
   	
   	return resultDto;
   }
   
   /**
    * @Method Name : findOneAverageEntrainmentSpeedByRoad
    * @작성일 : 2023. 9. 18.
    * @작성자 : NK.KIM
    * @Method 설명 : 도로별 평균동행속도 
    * @param mapMonitoringMenuDTO
    * @return
    */
   public MapMonitoringMenuDTO findOneAverageEntrainmentSpeedByRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
   	
   	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
   	
   	//평균 동행 속도 테이블 데이트
   	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = adsiVdsColctInfoMapper.findAllTrafficAvgSpeedTableByTimeZone(mapMonitoringMenuDTO);
   	if(!trafficTableInfo.isEmpty()) {
   		resultDto.setTrafficList(trafficTableInfo);
   	}
   	
   	return resultDto;
   }
   
   /**
    * @Method Name : findOneCumulativeTrafficVolumeByTimeZoneAndRoad
    * @작성일 : 2023. 9. 18.
    * @작성자 : NK.KIM
    * @Method 설명 : 도로/시간별 교통량
    * @param mapMonitoringMenuDTO
    * @return
    */
   public MapMonitoringMenuDTO findOneCumulativeTrafficVolumeByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
   	
   	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
   	
   	//평균 동행 속도 테이블 데이트
   	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = adsiVdsColctInfoMapper.findOneCumulativeTrafficVolumeByTimeZoneAndRoad(mapMonitoringMenuDTO);
   	if(!trafficTableInfo.isEmpty()) {
   		resultDto.setTrafficList(trafficTableInfo);
   	}
   	
   	return resultDto;
   }
   
   /**
    * @Method Name : findOneCumulativeTrafficVolumeByTimeZoneAndRoad
    * @작성일 : 2023. 9. 18.
    * @작성자 : NK.KIM
    * @Method 설명 : 도로/시간대별 평균동행속도
    * @param mapMonitoringMenuDTO
    * @return
    */
   public MapMonitoringMenuDTO findOneAverageEntrainmentSpeedByTimeZoneAndRoad(MapMonitoringMenuDTO mapMonitoringMenuDTO){
   	
   	MapMonitoringMenuDTO resultDto = new MapMonitoringMenuDTO();
   	
   	//평균 동행 속도 테이블 데이트
   	List<MapMonitoringMenuDTO.TrafficInfo> trafficTableInfo = adsiVdsColctInfoMapper.findOneAverageEntrainmentSpeedByTimeZoneAndRoad(mapMonitoringMenuDTO);
   	if(!trafficTableInfo.isEmpty()) {
   		resultDto.setTrafficList(trafficTableInfo);
   	}
   	
   	return resultDto;
   }
}
