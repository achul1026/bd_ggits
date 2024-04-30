package com.neighbor21.ggits.web.controller.systemmng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jcodings.exception.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.component.validate.ValidateBuilder;
import com.neighbor21.ggits.common.component.validate.ValidateChecker;
import com.neighbor21.ggits.common.component.validate.ValidateResult;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.MOpAuthority;
import com.neighbor21.ggits.common.entity.MOpGrpInfo;
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.mapper.MOpAthrMenuMapper;
import com.neighbor21.ggits.common.mapper.MOpAuthorityMapper;
import com.neighbor21.ggits.common.mapper.MOpGrpInfoMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.common.util.LoginSessionUtils;
import com.neighbor21.ggits.support.exception.CommonException;
import com.neighbor21.ggits.support.exception.ErrorCode;
import com.neighbor21.ggits.web.service.systemmng.AuthMngService;
import com.neighbor21.ggits.web.service.systemmng.MenuMngService;

@Controller
@RequestMapping("/system")
public class AuthMngController {
	
	@Autowired
	AuthMngService authMngService;
	
	@Autowired
	MenuMngService menuMngService;
	
	@Autowired
	MOpMenuMapper mOpMenuMapper;
	
	@Autowired
	MOpAuthorityMapper mOpAuthorityMapper;
	
	@Autowired
	MOpAthrMenuMapper mOpAthrMenuMapper;
	
	@Autowired
	MOpGrpInfoMapper mOpGrpInfoMapper; 
    
    /**
     * @Method Name : viewAuthMng
     * @작성일 : 2023. 8. 22.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 권한 관리
     * @return
     */
    @GetMapping("/auth/list.do") 
    public String viewAuthMng(Model model, MOpAuthority mOpAuthority){
    	model.addAttribute("searchType", mOpAuthority.getSearchType());
    	model.addAttribute("searchContent", mOpAuthority.getSearchContent());
    	int totalCnt = 0;
    	totalCnt = authMngService.getTotalCntForSearchOption(mOpAuthority.getSearchType(), mOpAuthority.getSearchContent());
    	
    	Paging paging = new Paging();
    	paging.setPageNo(mOpAuthority.getPage());
    	paging.setTotalCount(totalCnt);
    	model.addAttribute("paging", paging);
    	
    	model.addAttribute("authList", authMngService.getAuthList(mOpAuthority.getSearchType(), mOpAuthority.getSearchContent() ,paging));
    	return "view/systemmng/authMng";
    }
    
    /**
     * @Method Name : viewUpdateAuth
     * @작성일 : 2023. 9. 01.
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 상세 페이지
     * @return
     */
    @GetMapping("/auth/{authId}/detail.do") 
    public String viewAuthDetailInfo(@PathVariable("authId") Long authId, Model model){
    	//메뉴 List
    	List<Map<String,Object>> dataTbResult = new ArrayList<Map<String,Object>>( );
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	paramMap.put("useYn", "Y");
    	List<MOpMenu> upperMenuList = mOpMenuMapper.findAllMenuIdAndUpperMenuIdIsNullAndSortNoIsNotNullByMenuLvlAndUseYn(paramMap);
    	if(!upperMenuList.isEmpty()) {
    		for(int i = 0; i < upperMenuList.size(); i++) {
    			Map<String,Object> upperMenuMap = new HashMap<String,Object>();
    			List<MOpMenu> subMenuList = mOpMenuMapper.findAllBySortNoIsNotNullAndUpprMenuId(upperMenuList.get(i).getMenuId());
    			Long subMenuCnt = menuMngService.getSubMenuCount(upperMenuList.get(i).getMenuId());
    			
    			upperMenuMap.put("upperMenu", upperMenuList.get(i));
    			upperMenuMap.put("subMenuList", subMenuList);
    			upperMenuMap.put("subMenuCnt", subMenuCnt);
    			dataTbResult.add(upperMenuMap);
    		}
    	}
    	//권한 리스트
    	model.addAttribute("authMenuList", mOpAthrMenuMapper.findMenuAuthByAuthId(authId));
    	model.addAttribute("menuList", dataTbResult);
		model.addAttribute("mOpAuthority", mOpAuthorityMapper.findOneByAuthId(authId));
 	    return "view/systemmng/authDetail";
    }

    /**
     * @Method Name : viewAuthMngSave
     * @작성일 : 2023. 8. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 관리 저장
     * @return
     */
    @GetMapping("/auth/save.do")
    public String viewAuthMngSave(@RequestParam Map<String,Object> paramMap,Model model){
    	//메뉴 List
    	List<MOpMenu> upperMenuList = mOpMenuMapper.findAllMenuIdAndUpperMenuIdIsNullAndSortNoIsNotNullByMenuLvlAndUseYn(paramMap);
    	model.addAttribute("menuList", upperMenuList);
    	return "view/systemmng/authSave";
    }
    
    /**
     * @Method Name : getChkMenuListAjax
     * @작성일 : 2023. 8. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 목록 조회 AJAX
     * @return
     */
    @PostMapping("/auth/sub/menu/list.ajax")
    public @ResponseBody List<MOpMenu> getChkMenuListAjax(@RequestParam (value="upperMenuId") String upperMenuId){
    	List<MOpMenu> subMenuList = mOpMenuMapper.findAllBySortNoIsNotNullAndUpprMenuId(upperMenuId);
    	return subMenuList;
    }

    /**
     * @Method Name : saveAuthInfo
     * @작성일 : 2023. 8. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 저장
     * @return
     */
    @PostMapping("/auth/save.ajax")
    public @ResponseBody CommonResponse<?> saveAuthInfo(@RequestBody MOpAuthority mOpAuthority){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(mOpAuthority);
		dtoValidator
			        .addRule("authNm", new ValidateChecker().setRequired().setMaxLength(50, "권한 이름은 50자를 넘을 수 없습니다."))
			        .addRule("authCd", new ValidateChecker().setRequired());
		
    	ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
		try {
			authMngService.saveAuthorityAndMenuAuth(mOpAuthority);
		} catch(CommonException e) {
			if(e.getErrorCode().getCode() == ErrorCode.DATA_DUPLICATE.getCode()) {
				return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "VIP권한은 한개만 생성가능합니다.");
			} else {
				return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST, "권한 등록 실패");
			}
		}
		
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "권한 등록 성공");
    }
    
    /**
     * @Method Name : deleteAuthority
     * @작성일 : 2023. 8. 29.
     * @작성자 : KY.LEE
     * @Method 설명 : 권한 삭제
     * @return
     */
    @GetMapping("/auth/{authId}/delete.ajax")
    public @ResponseBody CommonResponse<?> deleteAuthority(@PathVariable("authId") Long authId){
    	// validation check
    	ValidateBuilder dtoValidator = new ValidateBuilder(new MOpAuthority(authId));
		dtoValidator
			        .addRule("authId", new ValidateChecker().setRequired());
		
		ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
		
  		Long cnt = mOpGrpInfoMapper.existsByAuthId(authId);
  		if(cnt > 0) {
  			return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST ,"권한을 사용중인 그룹이 존재하여 삭제 할 수 없습니다.");
  		} else {
  			authMngService.deleteAll(authId);
  		}
  		
		return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK, "권한을 성공적으로 삭제 했습니다.");
    }
    
    /**
     * @Method Name : updateMainMenu
     * @작성일 : 2023. 8. 28
     * @작성자 : KY.LEE
     * @Method 설명 : 메인 메뉴 수정
     * @param paramMap
     * @return
     */
   @PostMapping("/auth/update.ajax")
   public @ResponseBody CommonResponse<?> updateAuthInfo(@RequestBody MOpAuthority mOpAuthority){
		ValidateBuilder dtoValidator = new ValidateBuilder(mOpAuthority);
		dtoValidator
			        .addRule("authNm", new ValidateChecker().setRequired().setMaxLength(50, "권한 이름은 50자를 넘을 수 없습니다."));
		
		ValidateResult dtoValidatorResult = dtoValidator.isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
		
   		 authMngService.updateMOpAuthority(mOpAuthority);
   		 
    	 return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK ,"권한 수정에 성공했습니다.");
   }
}
