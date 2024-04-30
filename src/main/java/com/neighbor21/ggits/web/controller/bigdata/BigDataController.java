package com.neighbor21.ggits.web.controller.bigdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neighbor21.ggits.common.entity.*;
import com.neighbor21.ggits.common.hcisql.mapper.HciTsLogDriveanalMapper;
import com.neighbor21.ggits.common.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    MrtBusRoutePasngAnalMapper mrtBusRoutePasngAnalMapper;

    @Autowired
    GgbisBusEventinfoMapper ggbisBusEventinfoMapper;

    @Autowired
    MrtSmcTrfPatMapper mrtSmcTrfPatMapper;

    @Autowired
    MrtTrfHlctcCngstnSctnMapper mrtTrfHlctcCngstnSctnMapper;

    @Autowired
    MrtDsrcTrfvlmAnalMapper mrtDsrcTrfvlmAnalMapper;

    @Autowired
    MrtVdsTrfvlmAnalMapper mrtVdsTrfvlmAnalMapper;

    @Autowired
    MrtBusRouteDetAnalMapper mrtBusRouteDetAnalMapper;

    @Autowired
    MrtBusRouteSectnAnalMapper mrtBusRouteSectnAnalMapper;

    @Autowired
    TmsAnytmTrfvlmMapper tmsAnytmTrfvlmMapper;

    @Autowired
    TmsOrdtmTrfvlmMapper tmsOrdtmTrfvlmMapper;

    @Autowired
    HciTsLogDriveanalMapper hciTsLogDriveanalMapper;

    @Autowired
    MrtSigCrsdTrfAnalMapper mrtSigCrsdTrfAnalMapper;
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
     * 교통패턴 상습정체구간
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getPatternTrafficCngstn.ajax")
    public @ResponseBody
    ResponseEntity<?> getPatternTrafficCngstn(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtTrfHlctcCngstnSctn> list = mrtTrfHlctcCngstnSctnMapper.findAllBySearchOption(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 교통패턴 > 교통량/평균속도 조회
     * @return
     */
    @GetMapping("/getPatternTrafficQuantity.ajax")
    public @ResponseBody
    ResponseEntity<?> getPatternTrafficQuantity(MapBigdataSearchDTO mapBigdataSearchDTO){
        switch (mapBigdataSearchDTO.getCollectType()){
            case "vds" :
                return new ResponseEntity<>(mrtVdsTrfvlmAnalMapper.findAllGroupByLinkId(mapBigdataSearchDTO), HttpStatus.OK);
            case "dsrc" :
                return new ResponseEntity<>(mrtDsrcTrfvlmAnalMapper.findAllGroupByLinkId(mapBigdataSearchDTO), HttpStatus.OK);
            default :
                return new ResponseEntity<>(mrtSmcTrfPatMapper.findAllGroupByLinkId(mapBigdataSearchDTO), HttpStatus.OK);
        }
    }


    /**
     * 교통패턴 > 교통량/평균속도 조회 > 디테일 차트
     * @return
     */
    @GetMapping("/{collectType}/{type}/getPatternTrafficQuantityChart.ajax")
    public @ResponseBody
    ResponseEntity<?> getPatternTrafficQuantityChart(@PathVariable String collectType,@PathVariable String type, MapBigdataSearchDTO mapBigdataSearchDTO){
        switch(collectType) {
            case "smc" :
                switch (type){
                    case "total" :
                        return new ResponseEntity<>(mrtSmcTrfPatMapper.findAllByTotalChart(mapBigdataSearchDTO), HttpStatus.OK);
                    case "total-day" :
                        return new ResponseEntity<>(mrtSmcTrfPatMapper.findAllByTotalChartGroupDay(mapBigdataSearchDTO), HttpStatus.OK);
                    case "sgg" :
                        return new ResponseEntity<>(mrtSmcTrfPatMapper.findAllBySGGChart(mapBigdataSearchDTO), HttpStatus.OK);
                    case "sgg-day" :
                        return new ResponseEntity<>(mrtSmcTrfPatMapper.findAllBySGGChartGroupDay(mapBigdataSearchDTO), HttpStatus.OK);
                    case "top10" :
                        return new ResponseEntity<>(mrtSmcTrfPatMapper.findAllByTop10Chart(mapBigdataSearchDTO), HttpStatus.OK);
                    default:
                        return new ResponseEntity<>(null, HttpStatus.OK);
                }
            case "vds" :
                switch (type){
                    case "total" :
                        return new ResponseEntity<>(mrtVdsTrfvlmAnalMapper.findAllByTotalChart(mapBigdataSearchDTO), HttpStatus.OK);
                    case "total-day" :
                        return new ResponseEntity<>(mrtVdsTrfvlmAnalMapper.findAllByTotalChartGroupDay(mapBigdataSearchDTO), HttpStatus.OK);
                    case "sgg" :
                        return new ResponseEntity<>(mrtVdsTrfvlmAnalMapper.findAllBySGGChart(mapBigdataSearchDTO), HttpStatus.OK);
                    case "sgg-day" :
                        return new ResponseEntity<>(mrtVdsTrfvlmAnalMapper.findAllBySGGChartGroupDay(mapBigdataSearchDTO), HttpStatus.OK);
                    case "top10" :
                        return new ResponseEntity<>(mrtVdsTrfvlmAnalMapper.findAllByTop10Chart(mapBigdataSearchDTO), HttpStatus.OK);
                    default:
                        return new ResponseEntity<>(null, HttpStatus.OK);
                }
            case "dsrc" :
                switch (type){
                    case "total" :
                        return new ResponseEntity<>(mrtDsrcTrfvlmAnalMapper.findAllByTotalChart(mapBigdataSearchDTO), HttpStatus.OK);
                    case "total-day" :
                        return new ResponseEntity<>(mrtDsrcTrfvlmAnalMapper.findAllByTotalChartGroupDay(mapBigdataSearchDTO), HttpStatus.OK);
                    case "sgg" :
                        return new ResponseEntity<>(mrtDsrcTrfvlmAnalMapper.findAllBySGGChart(mapBigdataSearchDTO), HttpStatus.OK);
                    case "sgg-day" :
                        return new ResponseEntity<>(mrtDsrcTrfvlmAnalMapper.findAllBySGGChartGroupDay(mapBigdataSearchDTO), HttpStatus.OK);
                    case "top10" :
                        return new ResponseEntity<>(mrtDsrcTrfvlmAnalMapper.findAllByTop10Chart(mapBigdataSearchDTO), HttpStatus.OK);
                    default:
                        return new ResponseEntity<>(null, HttpStatus.OK);
                }
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/getPatternSvcCongetionTop10.ajax")
    public @ResponseBody
    ResponseEntity<?> getPatternSvcCongetionTop10(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtTrfHlctcCngstnSctn> list = mrtTrfHlctcCngstnSctnMapper.findSvcCongestionTop10BySearchOption(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 교통패턴 > 정체구간 조회
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getPatternTrafficAbnLos.ajax")
    public @ResponseBody
    ResponseEntity<?> getPatternTrafficAbnLos(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSmcAbnLos> list = bdPatternComponent.getTrafficAbnLos(mapBigdataSearchDTO);
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
     * 교통활동 효과분석 > 정체구간 개선효과 - 평균속도
     * @return
     */
    @GetMapping("/getSvcLinkTrafficActiveEffectAnalysis.ajax")
    public @ResponseBody
    ResponseEntity<?> getSvcLinkTrafficActiveEffectAnalysis(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSigCrsdTrfAnal> list = mrtSigCrsdTrfAnalMapper.findAllGroupBySvcLinkId(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }




    @GetMapping("/getTrafficEffectAnalysisChart.ajax")
    public @ResponseBody
    ResponseEntity<?> getTrafficEffectAnalysisChart(MapBigdataSearchDTO mapBigdataSearchDTO) {
        List<?> list = bdTrafficActiveEffectAnalysisComponent.getTrafficActiveEffectAnalysisChart(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getSvcLinkTrafficEffectAnalysisChart.ajax")
    public @ResponseBody
    ResponseEntity<?> getSvcLinkTrafficEffectAnalysisChart(MapBigdataSearchDTO mapBigdataSearchDTO) {
        List<MrtSigCrsdTrfAnal> list = mrtSigCrsdTrfAnalMapper.findAllSvcLinkForChart(mapBigdataSearchDTO);
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
            @RequestParam("candRouteId") String candRouteId,
            @RequestParam("baseym") String baseym,
            @RequestParam("btcId") String btcId
    ){
        List<MrtCndcyPathLinkInfo> list = bdPublicTransferPredictionComponent.getPublicTransferCndcyPathLinkInfo(btcId, baseym, candRouteId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 대중교통 최적화 후보노선 정류장 조회
     * @return
     */
    @GetMapping("/getPublicTransferCndcyStationInfo.ajax")
    public @ResponseBody ResponseEntity<?> getPublicTransferCndcyStationInfo(
            @RequestParam("candRouteId") String candRouteId,
            @RequestParam("baseym") String baseym,
            @RequestParam("btcId") String btcId
    ){
        List<MrtCndcyPathRouteBstpInfo> list = bdPublicTransferPredictionComponent.getPublicTransferCndcyStationInfo(btcId, baseym, candRouteId);
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
        List<MrtSmcrsrdTrfvlmAnal> list = bdPredictionComponent.getCrossRoadAngleInfo(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 교차로 교통량 예측 시간 추이용 데이터
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getCrossRoadTrafficQuantityPredictionGroupTime.ajax")
    public @ResponseBody
    ResponseEntity<?> getCrossRoadTrafficQuantityPredictionGroupTime(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSmcrsrdTrfvlmAnal> list = bdPredictionComponent.getTrfvlmGroupTime(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getCrossRoadTrafficQuantityPredictionTop10.ajax")
    public @ResponseBody
    ResponseEntity<?> getCrossRoadTrafficQuantityPredictionTop10(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSmcrsrdTrfvlmAnal> list = bdPredictionComponent.getTrfvlmTop10(mapBigdataSearchDTO);
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
        List<TsLogDriveanal> list = hciTsLogDriveanalMapper.findAllBySearchOptionForGIS(mapBigdataSearchDTO);
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
     * 대중교통 이용현황분석 > 노선별 정류장 BIT 조회 - 현재 버스 정류장 위치 정보
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getBusCurrentMoveInfo.ajax")
    public @ResponseBody
    ResponseEntity<?> getBusCurrentMoveInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<GgbisBusEventinfo> list = ggbisBusEventinfoMapper.findAllCurrentByRouteId(mapBigdataSearchDTO.getRouteId());
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

    @GetMapping("/{routeTp}/getPublicTransferRouteCurveAnalysisTop10.ajax")
    public @ResponseBody
    ResponseEntity<?> getPublicTransferRouteCurveAnalysisTop10(
            @PathVariable String routeTp
    ){
        List<MrtBusRouteDetAnal> data = new ArrayList<>();
        switch (routeTp) {
            case "type1" :
                data = mrtBusRouteDetAnalMapper.findAllTop10ByCurvtAndType1();
                break;
            case "type2" :
                data = mrtBusRouteDetAnalMapper.findAllTop10ByCurvtAndType2();
                break;
            case "type3" :
                data = mrtBusRouteDetAnalMapper.findAllTop10ByCurvtAndType3();
                break;
            case "type4" :
                data = mrtBusRouteDetAnalMapper.findAllTop10ByCurvtAndType4();
                break;
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /**
     * 대중교통 노션별 분석 > 노선구간별 중복구간 도출 및 적정성 분석(맵호출)
     * @return
     */
    @GetMapping("/getDuplicateRouteGeometryInfoByStationId.ajax")
    public @ResponseBody
    ResponseEntity<?> getDuplicateRouteGeometryInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusRouteSectnAnal> list = bdPublicTransferRouteAnalysisComponent.getDuplicateRouteGeometryInfo(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getDuplicateRouteListBySectionId.ajax")
    public @ResponseBody
    ResponseEntity<?> getDuplicateRouteListBySectionId(
            @RequestParam("stToEd") String stToEd
    ){
        String[] sectionIds = stToEd.split(",");
        List<Map<String, String>> sttoedlist = new ArrayList<>();
        for(String sted :sectionIds ){
            Map<String,String> map = new HashMap<>();
            String[] stedarr = sted.split("-");
            map.put("stStaId", stedarr[0]);
            map.put("edStaId", stedarr[1]);
            sttoedlist.add(map);
        }
        List<MrtBusRouteSectnAnal> list = mrtBusRouteSectnAnalMapper.findAllBySectionId(sttoedlist);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getDuplicateRouteTop10.ajax")
    public @ResponseBody
    ResponseEntity<?> getDuplicateRouteTop10(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusRouteSectnAnal> list = mrtBusRouteSectnAnalMapper.findTop10ByRouteId(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getPublicTransferLndiCntByRouteId.ajax")
    public @ResponseBody ResponseEntity<?> getPublicTransferLndiCntByRouteId(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusRoutePasngAnal> list = mrtBusRoutePasngAnalMapper.findAllCntByAllByRouteId(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/getPublicTransferLndiCntByAll.ajax")
    public @ResponseBody ResponseEntity<?> getPublicTransferLndiCntByAll(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusRoutePasngAnal> list = mrtBusRoutePasngAnalMapper.findAllLndiCntByAll(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /**
     * 대중교통 노션별 분석 > 노선구간별 이용자수(정류장 클릭시)
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getBusRouteSectionPassengerInfo.ajax")
    public @ResponseBody
    ResponseEntity<?> getBusRouteSectionPassengerInfo(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtBusRoutePasngAnal> list = mrtBusRoutePasngAnalMapper.findAllByRideStationId(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    /**
     * 상시교통량 조회 ( 국도만 있음 )
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getOrdTmTrfvlmInfo-nlrm.ajax")
    public @ResponseBody
    ResponseEntity<?> getOrdTmTrfvlmInfoNlrm(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<ExtTmsNlrdTimeOrdtmTrfvlm> list = tmsOrdtmTrfvlmMapper.findAllNlrdTimeByYmd(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 수시교통량 조회 - 국도
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getAnytmTrfvlmInfo-nlrm.ajax")
    public @ResponseBody
    ResponseEntity<?> getAnytmTrfvlmInfoNlrm(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<ExtTmsNlrdVhcclsAnytmTrfvlm> list = tmsAnytmTrfvlmMapper.findAllNlrdTimeByYmd(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 수시교통량 조회 - 고속도로
     * @param mapBigdataSearchDTO
     * @return
     */
    @GetMapping("/getAnytmTrfvlmInfo-hghw.ajax")
    public @ResponseBody
    ResponseEntity<?> getAnytmTrfvlmInfoHghw(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<ExtTmsHghwVhcclsAnytmTrfvlm> list = tmsAnytmTrfvlmMapper.findAllHghwTimeByYmd(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
