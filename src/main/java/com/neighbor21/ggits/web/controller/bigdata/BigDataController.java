package com.neighbor21.ggits.web.controller.bigdata;

import java.util.List;

import com.neighbor21.ggits.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.api.module.bigdata.BDDangerZoneComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPatternComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPopulationComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPredictionComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPublicTransferDangerComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPublicTransferPredictionComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPublicTransferRouteAnalysisComponent;
import com.neighbor21.ggits.api.module.bigdata.BDPublicTransferUsageComponent;
import com.neighbor21.ggits.api.module.bigdata.BDTrafficActiveEffectAnalysisComponent;
import com.neighbor21.ggits.api.module.common.CMBusComponent;
import com.neighbor21.ggits.api.module.common.CMBusStationComponent;
import com.neighbor21.ggits.api.module.common.CMCrossRoadCameraComponent;
import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;

@Controller
@RequestMapping("/bigdata")
public class BigDataController {

    @Autowired
    CMBusStationComponent cmBusStationComponent;

    @Autowired
    CMBusComponent cmBusComponent;

    @Autowired
    CMCrossRoadCameraComponent cmCrossRoadCameraComponent;
    
    @Autowired
    BDDangerZoneComponent bdDangerZoneComponent;

    @Autowired
    BDPatternComponent bdPatternComponent;
    
    @Autowired
    BDPublicTransferDangerComponent bdPublicTransferDangerComponent;

    @Autowired
    BDPopulationComponent bdPopulationComponent;

    @Autowired
    BDPredictionComponent bdPredictionComponent;
    
    @Autowired
    BDTrafficActiveEffectAnalysisComponent bdTrafficActiveEffectAnalysisComponent;

    @Autowired
    BDPublicTransferUsageComponent bdPublicTransferUsageComponent;

    @Autowired
    BDPublicTransferRouteAnalysisComponent bdPublicTransferRouteAnalysisComponent;

    @Autowired
    BDPublicTransferPredictionComponent bdPublicTransferPredictionComponent;
	
    /**
      * @Method Name : viewDashboard
      * @작성일 : 2023. 8. 26.
      * @작성자 : NK.KIM
      * @Method 설명 : 빅데이터 대시보드 화면
      * @return
      */
    @GetMapping("/dashboard.do")
    public String viewDashboard(){
    	
        return "view/bigdata/dashboard";
    }

    /**
     * 도로안전정보
     * @return
     */
    @GetMapping("/getDangerRoadInfo.ajax")
    public @ResponseBody
    ResponseEntity<?> getDangerRoadInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<UticRoadDngrSttsFrcst> list = bdDangerZoneComponent.getRoadDangerInfo(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 도로안전정보(시균별 통합정보)
     * @return
     */
    @GetMapping("/getDangerRoadInfoGroupSGG.ajax")
    public @ResponseBody
    ResponseEntity<?> getDangerRoadInfoGroupSGG(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<UticRoadDngrSttsFrcst> list = bdDangerZoneComponent.getRoadDangerInfoBySGG(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 행정시 사고정보 조회
     * @return
     */
    @GetMapping("/getCityAccidentInfo.ajax")
    public @ResponseBody
    ResponseEntity<?> getCityAccdientInfo(){
        List<TaasAdsiAcdntDstrct> list = bdDangerZoneComponent.getSggAccidentInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 행정시 도로안전 정보
     * @return
     */
    @GetMapping("/getRoadAccidentInfoGroupBySGG.ajax")
    public @ResponseBody
    ResponseEntity<?> getRoadAccidentInfoGroupBySGG(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<TaasDthTrfAcdntInfo> list = bdDangerZoneComponent.getRoadAccidentInfoGroupBySGG(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 교통량 조회
     * @return
     */
    @GetMapping("/getPatternTrafficQuantity.ajax")
    public @ResponseBody
    ResponseEntity<?> getPatternTrafficQuantity(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSmcTrfPat> list = bdPatternComponent.getTrafficQuantityInfo(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * 교통활동 효과분석 > 정체구간 개선효과
     * @return
     */
    @GetMapping("/getTrafficActiveEffectAnalysis.ajax")
    public @ResponseBody
    ResponseEntity<?> getTrafficActiveEffectAnalysis(MapBigdataSearchDTO mapBigdataSearchDTO){
    	List<?> list = bdTrafficActiveEffectAnalysisComponent.getTrafficActiveEffectAnalysis(mapBigdataSearchDTO);
    	return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 교통활동 효과분석 > 정체구간 개선효과(병합데이터)
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getTrafficActiveEffectAnalysisMerge.ajax")
    public @ResponseBody
    ResponseEntity<?> getTrafficActiveEffectAnalysisMerge(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<?> list = bdTrafficActiveEffectAnalysisComponent.getTrafficActiveEffectAnalysisMerge(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /**
     * 사고종류 전체 조회
     * @return
     */
    @GetMapping("/getAllAccidentInfo.ajax")
    public @ResponseBody
    ResponseEntity<?> getAllAccidentInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        TaasMaster list = bdDangerZoneComponent.getAccidentInfoAllAndSGG(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 유동인구 밀집 예측 조회
     * @return
     */
    @GetMapping("/getPopulationInfo.ajax")
    public @ResponseBody ResponseEntity<?> getPopulationInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtDynmcPopltnCell500Rslt> list = bdPublicTransferPredictionComponent.getPopulationInfoBySearchDto(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 유동인구 밀집 예측 조회(플레이어 차트용)
     * @return
     */
    @GetMapping("/getPopulationInfoForChart.ajax")
    public @ResponseBody ResponseEntity<?> getPopulationInfoForChart(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtDynmcPopltnCell500Rslt> list = bdPublicTransferPredictionComponent.getPopulationInfoBySearchDtoForChart(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 최적화 후보노선 경로 조회
     * @return
     */
    @GetMapping("/getPublicTransferCndcyPathLinkInfo.ajax")
    public @ResponseBody ResponseEntity<?> getPublicTransferCndcyPathLinkInfo(
            @RequestParam("candRouteId") String candRouteId
    ){
        List<MrtCndcyPathLinkInfo> list = bdPublicTransferPredictionComponent.getPublicTransferCndcyPathLinkInfo(candRouteId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 최적화 후보노선 정류장 조회
     * @return
     */
    @GetMapping("/getPublicTransferCndcyStationInfo.ajax")
    public @ResponseBody ResponseEntity<?> getPublicTransferCndcyStationInfo(
            @RequestParam("candRouteId") String candRouteId
    ){
        List<MrtCndcyPathRouteBstpInfo> list = bdPublicTransferPredictionComponent.getPublicTransferCndcyStationInfo(candRouteId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    

    /**
     * 사고 예측구간 데이터 조회
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getTrafficAccidentPrediction.ajax")
    public @ResponseBody
    ResponseEntity<?> getTrafficAccidentPrediction(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtTrfAcdntDngrPrdctn> list = bdPredictionComponent.getTrafficAccidentPrediction(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 시군구별 사고 예측구간 데이터 조회
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getTrafficAccidentPredictionGroupSgg.ajax")
    public @ResponseBody
    ResponseEntity<?> getTrafficAccidentPredictionGroupSgg(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtTrfAcdntDngrPrdctn> list = bdPredictionComponent.getTrafficAccidentPredictionGroupSgg(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 교차로 교통량 예측 데이터 조회
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getCrossRoadTrafficQuantityPrediction.ajax")
    public @ResponseBody
    ResponseEntity<?> getCrossRoadTrafficQuantityPrediction(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSmcrsrdTrfvlmAnal> list = bdPredictionComponent.getCrossRoadTrafficPredictionByYmd(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 교차로 교통량 예측 데이터 조회(차트 플레이어용 시군구)
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getCrossRoadTrafficQuantityPredictionForChat.ajax")
    public @ResponseBody
    ResponseEntity<?> getCrossRoadTrafficQuantityPredictionForChat(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSmcrsrdTrfvlmAnal> list = bdPredictionComponent.getCrossRoadTrafficPredictionForChart(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 스마트교차로 카메라 정보 조회
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getCrossRoadCameraList.ajax")
    public @ResponseBody
    ResponseEntity<?> getCrossRoadCameraList(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<AdsiSmcrsrdCameraInfo> list = cmCrossRoadCameraComponent.getCrossRoadCameraList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 위험운영 구간 분석 정보 조회
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getPublicTransferDangerInfo.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferDangerInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtDtgDangerSectn> list = bdPublicTransferDangerComponent.getBusDtgDangerSectionInfo(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 위험운영 구간 분석 정보 조회(차트용)
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getPublicTransferDangerInfoForChart.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferDangerInfoForChart(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtDtgDangerSectn> list = bdPublicTransferDangerComponent.getBusDtgDangerSectionInfoForChart(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /**
     * 대중교통 이용현황분석 > 기종점 대중교통 이용량
     * @return
     */
    @GetMapping("/getPublicTransferStartEndUsage.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferStartEndUsage(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusRungLogAnal> list = bdPublicTransferUsageComponent.getPublicTransferStartEndUsage(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 이용현황분석 > 기종점 대중교통 이용량
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getPublicTransferUsageBySGG.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferUsageBySGG(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusSttnPasngAnal> list = bdPublicTransferUsageComponent.getPublicTransferUsageBySGG(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 이용현황분석 > 정류장별 버스이용률 조회
     * @param stationId
     * @param searchYear
     * @param searchPeriod
     * @param searchTime
     * @return
     */
    @GetMapping("/getPublicTransferUsageByStation.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferUsageBySGG(
            @RequestParam("stationId") String stationId
            ,@RequestParam(name = "searchYear", required = false) String searchYear
            ,@RequestParam(name = "searchPeriod", required = false) String searchPeriod
            ,@RequestParam(name = "searchTime", required = false) String searchTime
    ){
        List<MrtBusSttnAnal> list = bdPublicTransferUsageComponent.getPublicTransferUsageByStation(stationId, searchYear, searchPeriod, searchTime);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 이용현황분석 > 버스 도착정보 예측 조회 
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getPublicTransferBIT.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferBIT(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusArvlTimePrdctnRslt> list = bdPublicTransferUsageComponent.getPublicTransferBIT(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 출발지 도착지 정류장 아이디로 버스노선 경로 조회
     * @param stStationId
     * @param edStationId
     * @param routeId
     * @return
     */
    @GetMapping("/getBusRouteInfoByStationIdAndRouteId.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferUsageBySGG(
            @RequestParam("stStationId") String stStationId
            ,@RequestParam("edStationId") String edStationId
            ,@RequestParam("routeId") String routeId
    ){
        List<GgbisBusrouteLink> list = cmBusComponent.getBusRouteLinkInfoByStationIdAndRouteId(stStationId, edStationId, routeId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 노선별 분석 > 노선구간별 수용성 및 굴곡도 분석
     * @return
     */
    @GetMapping("/getPublicTransferRouteCurveAnalysis.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferRouteCurveAnalysis(MapBigdataSearchDTO mapBigdataSearchDTO){
        MrtBusRouteDetAnal data = bdPublicTransferRouteAnalysisComponent.getRouteCurveInfo(mapBigdataSearchDTO);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * 대중교통 노션별 분석 > 노선구간별 중복구간 도출 및 적정성 분석(맵호출)
     * @return
     */
    @GetMapping("/getDuplicateRouteGeometryInfoByStationId.ajax")
    public @ResponseBody
    ResponseEntity<?> getDuplicateRouteGeometryInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<GgbisBusrouteInfounit> list = bdPublicTransferRouteAnalysisComponent.getDuplicateRouteGeometryInfo(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
