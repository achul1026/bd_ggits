package com.neighbor21.ggits.web.controller.bigdata;

import java.util.List;

import com.neighbor21.ggits.api.module.bigdata.*;
import com.neighbor21.ggits.api.module.common.CMBusStationComponent;
import com.neighbor21.ggits.api.module.common.CMCrossRoadCameraComponent;
import com.neighbor21.ggits.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.dto.MapBigdataSearchDTO;

@Controller
@RequestMapping("/bigdata")
public class BigDataController {

    @Autowired
    CMBusStationComponent cmBusStationComponent;

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
        List<TaasAcdntDstrctMaster> list = bdDangerZoneComponent.getAccidentInfoAll(mapBigdataSearchDTO);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 유동인구 조회
     * @param date
     * @param time
     * @return
     */
    @GetMapping("/getPopulationInfo.ajax")
    public @ResponseBody ResponseEntity<?> getPopulationInfo(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "time", required = false) String time
    ){
        List<KtTimeZn> list = bdPopulationComponent.getPopulationInfoByTimeZnRecent();
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
     * 교차로 교통량 예측 데이터 조회
     * @param mapBigdataSearchDTO 검색옵션
     * @return
     */
    @GetMapping("/getCrossRoadTrafficQuantityPrediction.ajax")
    public @ResponseBody
    ResponseEntity<?> getCrossRoadTrafficQuantityPrediction(MapBigdataSearchDTO mapBigdataSearchDTO){
        List<MrtSmcrsrdTrfvlmAnal> list = bdPredictionComponent.getCrossRoadTrafficPrediction(mapBigdataSearchDTO);
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
}
