package com.neighbor21.ggits.support.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.neighbor21.ggits.common.enums.OpenApiInfo;
import com.neighbor21.ggits.common.enums.ResultStatus;
import com.neighbor21.ggits.openapi.response.OpenApiCommonResult;
import com.neighbor21.ggits.openapi.service.OpenApiService;

@ControllerAdvice
@RestControllerAdvice
public class GgitsExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	OpenApiService openApiService;

	@ExceptionHandler(value = Exception.class)
	public ModelAndView exceptionHandler(Exception e){
		e.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/error.do");
		//URL 주소에 에러 메시지 출력되어 주석
//		mav.addObject("message", e.getMessage());
		return mav;
	}
	
	// LoginCheckException
	@ExceptionHandler(value = NoLoginException.class)
	public Object loginChkExceptionHandler(NoLoginException ne,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/login.do");
		if(request.getHeader("X-GGITS-MAP-DATA") != null){
			return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
		}else{
			return mav;
		}

	}
	
	// customRuntimeException 
	@ExceptionHandler(value = CommonException.class)
	public ResponseEntity<ErrorResponse> adminExceptionHandler(CommonException ce, String message) {
		ErrorResponse response = new ErrorResponse(ce.getErrorCode());
		return new ResponseEntity<>(response, HttpStatus.valueOf(ce.getErrorCode().getCode()));
	}

	//SQLException
	@ExceptionHandler(value = SQLException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(CommonException ce, String message) {
		ErrorResponse response = new ErrorResponse(ErrorCode.DEFAULT_ERROR);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//ApiExcpetion
	@ExceptionHandler(value = ApiException.class)
	public ResponseEntity<OpenApiCommonResult> exceptionHandler(ApiException e, HttpServletRequest request){
		OpenApiCommonResult response = new OpenApiCommonResult(e.getOpenApiErrorCodes());
		String currentURI = request.getRequestURI().toString();
		String parameterStr = e.getParameterStr() != null && !"".equals(e.getParameterStr())?e.getParameterStr():"[Unknown Parameter]";
		
		openApiService.insertOpenApiLog(OpenApiInfo.getApiInfoForApiUrl(currentURI), parameterStr, 0, ResultStatus.FAILED,"");
		
		return new ResponseEntity<OpenApiCommonResult>(response, HttpStatus.OK);
	}

	private boolean isAjax(HttpServletRequest request) {
		String requestedWithHeader = request.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(requestedWithHeader);
	}
	
}
