package com.neighbor21.ggits.support.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.support.exception.NoLoginException;

public class LoginSessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		MOpOperator mOpOperatorInfo = null;
		if (request.getSession() != null) {
			mOpOperatorInfo = (MOpOperator) request.getSession().getAttribute("mOpOperatorInfo");
		}

		if (mOpOperatorInfo == null) {
			throw new NoLoginException(ErrorCode.NOT_FOUNT_USER_INFO);
		}

		request.setAttribute("authCd", mOpOperatorInfo.getAuthCd());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}
}
