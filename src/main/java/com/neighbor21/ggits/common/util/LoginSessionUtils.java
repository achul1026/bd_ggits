package com.neighbor21.ggits.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.support.exception.NoLoginException;

/**
 * 설명
 * @author : KY.LEE
 * @fileName :  LoginSessionUtils
 * @since : 2023.9.11
 */
public class LoginSessionUtils {

	
	public LoginSessionUtils() {
		super();
	}
	
	/**
	  * @Method Name : getSessionRequest
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 현재 세션 값 조회
	  * @return HttpServletRequest
	  */
	public static HttpServletRequest getSessionRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	/**
	  * @Method Name : getOprtrNm
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 로그인 사용자 정보 가져오기
	  * @return String
	  */
	public static MOpOperator getMOpOperatorInfo() {
		MOpOperator mOpOperatorInfo = null;
		HttpServletRequest request = getSessionRequest();
		if (request.getSession() != null) {
			mOpOperatorInfo = (MOpOperator) request.getSession().getAttribute("mOpOperatorInfo");
		} else {
			throw new NoLoginException(ErrorCode.SESSION_NOT_FOUND);
		}
		return mOpOperatorInfo;
	}
	
	
	/**
	  * @Method Name : getOprtrId
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 로그인 사용자 ID(PK값) 가져오기
	  * @return String
	  */
	public static Long getOprtrId() {
		if(getMOpOperatorInfo()!= null) {
			return getMOpOperatorInfo().getOprtrId();
		} else {
			throw new NoLoginException(ErrorCode.NOT_FOUNT_USER_INFO);
		}
	}
	
	/**
	  * @Method Name : getOprtrNm
	  * @작성일 : 2023. 9. 11.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 로그인 사용자 명 가져오기
	  * @return String
	  */
	public static String getOprtrNm() {
		if(getMOpOperatorInfo()!= null) {
			return getMOpOperatorInfo().getOprtrNm();
		} else {
			throw new NoLoginException(ErrorCode.NOT_FOUNT_USER_INFO);
		}
	}

	/**
	 * @Method Name : getLayoutNo
	 * @작성일 : 2023. 10. 24.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 레이아웃 번호 가져오기
	 * @return String
	 */
	public static long getLayoutNo() {
		if(getMOpOperatorInfo()!= null) {
			return getMOpOperatorInfo().getLayoutNo();
		} else {
			throw new NoLoginException(ErrorCode.NOT_FOUNT_USER_INFO);
		}
	}
	
	/**
	  * @Method Name : getOprtrNm
	  * @작성일 : 2023. 10. 04.
	  * @작성자 : KY.LEE
	  * @Method 설명 : 로그인 사용자 명 그룹 아이디 가져오기
	  * @return String
	  */
	public static String getGrpId() {
		if(getMOpOperatorInfo()!= null) {
			return getMOpOperatorInfo().getGrpId();
		} else {
			throw new NoLoginException(ErrorCode.NOT_FOUNT_USER_INFO);
		}
	}
	
	/**
	 * @Method Name : loginUserChk
	 * @작성일 : 2023. 9. 25.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 로그인 사용자명과 가져온 파라미터 값을 비교 
	 * @return boolean
	 */
	public static boolean loginUserChk(String oprtrNm) {
		boolean isUserChk = false;
		String userNm = getOprtrNm();
		if(userNm.equals(oprtrNm)) {
			isUserChk = true;
		}
		return isUserChk;
	}
	
	public static String getUserIpAddr() {
		String ip = null;
		HttpServletRequest request = getSessionRequest();
		
		ip = request.getHeader("X-Forwarded-For");
        
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_CLIENT_IP"); 
        } 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-Real-IP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("X-RealIP"); 
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        }
        
		return ip;
	}
}
