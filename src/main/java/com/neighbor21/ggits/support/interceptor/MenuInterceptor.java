 package com.neighbor21.ggits.support.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neighbor21.ggits.api.module.facility.dto.GnbMenuDTO;
import com.neighbor21.ggits.common.entity.MOpAuthority;
import com.neighbor21.ggits.common.entity.MOpCode;
import com.neighbor21.ggits.common.entity.MOpGrpInfo;
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.entity.MOpOperator;
import com.neighbor21.ggits.common.mapper.MOpAuthorityMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpGrpInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.support.exception.NoLoginException;
import com.neighbor21.ggits.web.service.systemmng.MenuMngService;

public class MenuInterceptor implements HandlerInterceptor  {
	
	@Autowired
	MOpMenuMapper mOpMenuMapper;
	
	@Autowired
	MOpAuthorityMapper mOpAuthorityMapper;
	
	@Autowired
	MOpGrpInfoMapper mOpGrpInfoMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
	@Autowired
	MenuMngService menuMngService;

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		List<GnbMenuDTO> data = new ArrayList<>();
		List<MOpMenu> streamMenuList = new ArrayList<>();
		List<MOpCode> menuCtgryList = new ArrayList<>();
		
		MOpOperator mOpOperatorInfo = null;
		if (request.getSession() != null) {
			mOpOperatorInfo = (MOpOperator) request.getSession().getAttribute("mOpOperatorInfo");
		}
		
		if (mOpOperatorInfo == null) {
			throw new NoLoginException(ErrorCode.NOT_FOUNT_USER_INFO);
		}
		
		if(request.getSession().getAttribute("gnbMenuDTO") != null &&
				request.getSession().getAttribute("streamMenuList") != null &&
				request.getSession().getAttribute("menuCtgryList") != null ) {
			data = (List<GnbMenuDTO>) request.getSession().getAttribute("gnbMenuDTO");
			streamMenuList = (List<MOpMenu>) request.getSession().getAttribute("streamMenuList");
			menuCtgryList = (List<MOpCode>) request.getSession().getAttribute("menuCtgryList");
		} else {
			if(mOpOperatorInfo.getGrpId() != null && !"".equals(mOpOperatorInfo.getGrpId())) {
				String grpId = mOpOperatorInfo.getGrpId();
				MOpGrpInfo paramGrp = new MOpGrpInfo();
				paramGrp.setGrpId(grpId);
				MOpGrpInfo mOpGrpInfo =  mOpGrpInfoMapper.findOneGroupDetailByGrpId(paramGrp);
				
				MOpAuthority mOpAuthority = mOpAuthorityMapper.findOneByAuthId(mOpGrpInfo.getAuthId());
				
				if(mOpGrpInfo != null) {
					mOpOperatorInfo.setAuthId(mOpGrpInfo.getAuthId());
					List<MOpMenu> mOpMenuList = mOpMenuMapper.findAllByAuthId(mOpOperatorInfo);
					if(!mOpMenuList.isEmpty()) {
						for (MOpMenu mOpMenu : mOpMenuList) {
							GnbMenuDTO gnbMenuDTO = new GnbMenuDTO(mOpMenu);
							mOpMenu.setUpperMenuId(mOpMenu.getMenuId());
							mOpMenu.setAuthId(mOpGrpInfo.getAuthId());
							List<MOpMenu> subMenuList = mOpMenuMapper.findAllByUpperMenuIdAndAuthId(mOpMenu);
							streamMenuList.addAll(subMenuList);
							gnbMenuDTO.setmOpMenuList(subMenuList);
							data.add(gnbMenuDTO);
						}
					}
					menuCtgryList = mOpCodeMapper.findAllCodeListByGrpCdIdForMonitoring(mOpAuthority.getAuthId());
					
				}
			}
			request.getSession().setAttribute("gnbMenuDTO", data);
			request.getSession().setAttribute("menuCtgryList", menuCtgryList);
			request.getSession().setAttribute("streamMenuList", streamMenuList);
		}
		request.setAttribute("gnbMenuDTO", data);
		request.setAttribute("menuCtgryList", menuCtgryList);
		request.setAttribute("streamMenuList", streamMenuList);
		
	    AntPathMatcher pathMatcher = new AntPathMatcher();

		//권한관련 메뉴  리스트 조회
		String currentURI = request.getRequestURI().toString();
		boolean isAuthChk = false;
//			권한 없으면 error페이지
		isAuthChk = streamMenuList.stream().anyMatch(x -> pathMatcher.match(x.getUrlPttrn(), currentURI));
	    if(!isAuthChk) {
	    	response.sendRedirect("/errorMenuAuth.do");
	    }
	    
		return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    	String currentURI = request.getRequestURI().toString();
    	//메뉴 로그 삽입
    	if(!GgitsCommonUtils.isNull(currentURI)) {
    		//Map 쪽은 Ajax로 따로 관리
    		if(!currentURI.contains("dashboard.do")) {
	    	MOpMenu mOpMenu = new MOpMenu();
	    	mOpMenu.setUrlAddr(currentURI);
    			
	    	MOpMenu dbMOpMenu = mOpMenuMapper.findFirstByMenuAddr(mOpMenu);
		    	if(dbMOpMenu != null) {
		    		menuMngService.saveLOpUseMenu(dbMOpMenu.getMenuId());
		    	}
    		}
    	}
    }
}
