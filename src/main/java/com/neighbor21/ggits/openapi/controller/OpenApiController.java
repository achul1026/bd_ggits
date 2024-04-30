package com.neighbor21.ggits.openapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.enums.OpenApiErrorCodes;
import com.neighbor21.ggits.common.enums.OpenApiInfo;
import com.neighbor21.ggits.common.enums.ResultStatus;
import com.neighbor21.ggits.common.util.BDDateFormatUtil;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.openapi.request.CongestionSestionInfoRequest;
import com.neighbor21.ggits.openapi.request.EmergencyPathAnalysisRequest;
import com.neighbor21.ggits.openapi.request.TrafficAccidentPredictionInfoRequest;
import com.neighbor21.ggits.openapi.request.TrafficAccidentStatisticsRequest;
import com.neighbor21.ggits.openapi.request.TrafficAnalysisRequest;
import com.neighbor21.ggits.openapi.request.TrafficSignalInfoRequest;
import com.neighbor21.ggits.openapi.request.TrafficSpeedByTimezoneRequest;
import com.neighbor21.ggits.openapi.request.TrafficVolumeByTimezoneRequest;
import com.neighbor21.ggits.openapi.response.CongestionSestionInfoResponse;
import com.neighbor21.ggits.openapi.response.EmergencyPathAnalysisResponse;
import com.neighbor21.ggits.openapi.response.OpenApiCommonResult;
import com.neighbor21.ggits.openapi.response.TrafficAccidentPredictionInfoResponse;
import com.neighbor21.ggits.openapi.response.TrafficAccidentStatisticsResponse;
import com.neighbor21.ggits.openapi.response.TrafficAnalysisResponse;
import com.neighbor21.ggits.openapi.response.TrafficSignalInfoResponse;
import com.neighbor21.ggits.openapi.response.TrafficSpeedByTimezoneResponse;
import com.neighbor21.ggits.openapi.response.TrafficVolumeByTimezoneResponse;
import com.neighbor21.ggits.openapi.service.OpenApiService;
import com.neighbor21.ggits.support.exception.ApiException;

@RestController
@RequestMapping(path = "/openapi")
public class OpenApiController {
	
	@Autowired
	OpenApiService openApiService;
	
	/**
     * @Method Name : getTrafficVolumeByTimezone
     * @작성일 : 2023. 12. 05.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 시간대별 교통량 정보
     * @return
     */
	@PostMapping(value="/trafficVolumeByTimezone.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getTrafficVolumeByTimezone(@RequestBody TrafficVolumeByTimezoneRequest trafficVolumeByTimezoneRequest) throws ApiException {
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(trafficVolumeByTimezoneRequest);
    	
    	ValidateResult dtoValidatorResult = dtoValidator
    	.addRule("date", new ValidateChecker().setRequired())
    	.addRule("time", new ValidateChecker().setRequired()).isValid();
    	
    	if(!dtoValidatorResult.isSuccess() 
    			|| !GgitsCommonUtils.isDateChk(trafficVolumeByTimezoneRequest.getDate().replaceAll("-", ""),"yyyyMMdd")
    			|| !GgitsCommonUtils.isTimeChk(trafficVolumeByTimezoneRequest.getTime())){
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,trafficVolumeByTimezoneRequest.toString());
    	}
		List<TrafficVolumeByTimezoneResponse> resultData = openApiService.getTrafficVolumeByTimezone(trafficVolumeByTimezoneRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.TRAFFIC_VOLUME_BY_TIME_ZONE,
					trafficVolumeByTimezoneRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					"");
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
	
	/**
     * @Method Name : getTrafficSpeedByTimezone
     * @작성일 : 2023. 12. 05.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 시간대별 평균속도 정보
     * @return
     */
	@PostMapping(value="/trafficSpeedByTimezone.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getTrafficSpeedByTimezone(@RequestBody TrafficSpeedByTimezoneRequest trafficSpeedByTimezoneRequest) throws ApiException {
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(trafficSpeedByTimezoneRequest);
		dtoValidator
			        .addRule("date", new ValidateChecker().setRequired())
			        .addRule("time", new ValidateChecker().setRequired());
		
		ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()
    			|| !GgitsCommonUtils.isDateChk(trafficSpeedByTimezoneRequest.getDate().replaceAll("-", ""),"yyyyMMdd")
    			|| !GgitsCommonUtils.isTimeChk(trafficSpeedByTimezoneRequest.getTime())) {
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,trafficSpeedByTimezoneRequest.toString());
    	}
    	
		List<TrafficSpeedByTimezoneResponse> resultData = openApiService.getTrafficSpeedByTimezone(trafficSpeedByTimezoneRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.TRAFFIC_SPEED_BY_TIME_ZONE,
					trafficSpeedByTimezoneRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					"");
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
	
	
	/**
     * @Method Name : getCongestionSestionInfo
     * @작성일 : 2023. 12. 11.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 정체구간 발생정보
     * @return
     */
	@PostMapping(value="/congestionSestionInfo.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getCongestionSestionInfo(@RequestBody CongestionSestionInfoRequest congestionSestionInfoRequest) throws ApiException {
    	//오늘날짜 default
    	String date = BDDateFormatUtil.isNowStr("yyyyMMdd");
    	
    	if(!GgitsCommonUtils.isNull(congestionSestionInfoRequest.getDate())) {
    		date = congestionSestionInfoRequest.getDate().replaceAll("-", "");
    	}
    	
    	congestionSestionInfoRequest.setDate(date);

    	if(!GgitsCommonUtils.isDateChk(congestionSestionInfoRequest.getDate().replaceAll("-", ""),"yyyyMMdd")) {
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,congestionSestionInfoRequest.toString());
    	}
    	
		List<CongestionSestionInfoResponse> resultData = openApiService.getCongestionSestionInfo(congestionSestionInfoRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.CONGESTION_SESTION_INFO,
					congestionSestionInfoRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					"");
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
	
	/**
     * @Method Name : getTrafficAccidentPredictionInfo
     * @작성일 : 2023. 12. 11.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 위험 도로 정보
     * @return
     */
	@PostMapping(value="/trafficAccidentPredictionInfo.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getTrafficAccidentPredictionInfo(@RequestBody TrafficAccidentPredictionInfoRequest trafficAccidentPredictionInfoRequest) throws ApiException{
    	String date = BDDateFormatUtil.isNowStr("yyyyMMdd");
    	
    	if(!GgitsCommonUtils.isNull(trafficAccidentPredictionInfoRequest.getDate())) {
    		date = trafficAccidentPredictionInfoRequest.getDate().replaceAll("-", "");
    	} 
    	
    	trafficAccidentPredictionInfoRequest.setDate(date);
    	
    	if(!GgitsCommonUtils.isDateChk(trafficAccidentPredictionInfoRequest.getDate().replaceAll("-", ""),"yyyyMMdd")) {
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,trafficAccidentPredictionInfoRequest.toString());
    	}
    	
		List<TrafficAccidentPredictionInfoResponse> resultData = openApiService.getTrafficAccidentPredictionInfo(trafficAccidentPredictionInfoRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.TRAFFIC_ACCIDENT_PREDICTION_INFO,
					trafficAccidentPredictionInfoRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					"");
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
	
	/**
     * @Method Name : getTrafficAccidentPredictionInfo
     * @작성일 : 2023. 12. 11.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 위치 기반 사고 분포 정보
     * @return
     */
	@PostMapping(value = "/trafficAccidentStatistics.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getTrafficAccidentStatistics(@RequestBody TrafficAccidentStatisticsRequest trafficAccidentStatisticsRequest) throws ApiException {
   	
    	String date = BDDateFormatUtil.isNowStr("yyyyMMdd");
    	
    	if(!GgitsCommonUtils.isNull(trafficAccidentStatisticsRequest.getDate())) {
    		date = trafficAccidentStatisticsRequest.getDate().replaceAll("-", "");
    	} 
    	
    	trafficAccidentStatisticsRequest.setDate(date);
    	
    	if(!GgitsCommonUtils.isDateChk(trafficAccidentStatisticsRequest.getDate().replaceAll("-", ""),"yyyyMMdd")) {
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,trafficAccidentStatisticsRequest.toString());
    	}
    	
		List<TrafficAccidentStatisticsResponse> resultData = openApiService.getTrafficAccidentStatistics(trafficAccidentStatisticsRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.TRAFFIC_ACCIDENT_STATISTICS,
					trafficAccidentStatisticsRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					"");
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
	
	/**
     * @Method Name : getTrafficSignalInfo
     * @작성일 : 2023. 12. 11.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 교통 통계 정보
     * @return
     */
	@PostMapping(value="/trafficSignalInfo.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getTrafficSignalInfo(@RequestBody TrafficSignalInfoRequest trafficSignalInfoRequest) throws ApiException {
    	
    	String date = BDDateFormatUtil.isNowStr("yyyyMMdd");
    	
    	if(!GgitsCommonUtils.isNull(trafficSignalInfoRequest.getDate())) {
    		date = trafficSignalInfoRequest.getDate().replaceAll("-", "");
    	} 
    	
    	trafficSignalInfoRequest.setDate(date);
    	
    	if(!GgitsCommonUtils.isDateChk(trafficSignalInfoRequest.getDate().replaceAll("-", ""),"yyyyMMdd")) {
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,trafficSignalInfoRequest.toString());
    	}
    	
		List<TrafficSignalInfoResponse> resultData = openApiService.getTrafficSignalInfo(trafficSignalInfoRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.TRAFFIC_SIGNAL_INFO,
					trafficSignalInfoRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					trafficSignalInfoRequest.getMngInstCd());
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
	
	/**
     * @Method Name : getEmergencyPathAnalysis
     * @작성일 : 2023. 12. 11.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 긴급차량 이동 경로 분석
     * @return
     */
	@PostMapping(value="/emergencyPathAnalysis.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getEmergencyPathAnalysis(@RequestBody EmergencyPathAnalysisRequest emergencyPathAnalysisRequest) throws ApiException {
    	
    	String date = BDDateFormatUtil.isNowStr("yyyyMMdd");
    	
    	if(!GgitsCommonUtils.isNull(emergencyPathAnalysisRequest.getDate())) {
    		date = emergencyPathAnalysisRequest.getDate().replaceAll("-", "");
    	} 
    	
    	emergencyPathAnalysisRequest.setDate(date);
    	
    	if(!GgitsCommonUtils.isDateChk(emergencyPathAnalysisRequest.getDate().replaceAll("-", ""),"yyyyMMdd")) {
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,emergencyPathAnalysisRequest.toString());
    	}
    	
		List<EmergencyPathAnalysisResponse> resultData = openApiService.getEmergencyPathAnalysis(emergencyPathAnalysisRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.EMERGENCY_PATH_ANALYSIS,
					emergencyPathAnalysisRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					emergencyPathAnalysisRequest.getMngInstCd());
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
	
	/**
     * @Method Name : getTrafficAnalysis
     * @작성일 : 2023. 12. 11.
     * @작성자 : KY.LEE
     * @Method 설명 : Open Api -> 교통정보 분석 데이터
     * @return
     */
	@PostMapping(value="/trafficAnalysis.do", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public OpenApiCommonResult getTrafficAnalysis(@RequestBody TrafficAnalysisRequest trafficAnalysisRequest) throws ApiException {
    	
    	String date = BDDateFormatUtil.isNowStr("yyyyMMdd");
    	
    	if(!GgitsCommonUtils.isNull(trafficAnalysisRequest.getDate())) {
    		date = trafficAnalysisRequest.getDate().replaceAll("-", "");
    	}
    	
    	trafficAnalysisRequest.setDate(date);
    	
    	if(!GgitsCommonUtils.isDateChk(trafficAnalysisRequest.getDate().replaceAll("-", ""),"yyyyMMdd")) {
    		throw new ApiException(OpenApiErrorCodes.BAD_REQUEST,trafficAnalysisRequest.toString());
    	}
    	
		List<TrafficAnalysisResponse> resultData = openApiService.getTrafficAnalysis(trafficAnalysisRequest);
		
		//로그삽입
		openApiService.insertOpenApiLog(
					OpenApiInfo.TRAFFIC_ANALYSIS,
					trafficAnalysisRequest.toString(),
					resultData.size(),
					ResultStatus.SUCCESS,
					"");
		
		return new OpenApiCommonResult(resultData,OpenApiErrorCodes.SUCCESS);
	}
}
