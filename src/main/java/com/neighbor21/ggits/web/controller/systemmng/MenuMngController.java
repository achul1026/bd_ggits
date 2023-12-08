package com.neighbor21.ggits.web.controller.systemmng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.neighbor21.ggits.common.entity.MOpMenu;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.mapper.MOpMenuMapper;
import com.neighbor21.ggits.web.service.systemmng.MenuMngService;

@Controller
@RequestMapping("/system")
public class MenuMngController {
	
	@Autowired
	MenuMngService menuMngService;
	
	@Autowired
	MOpMenuMapper mOpMenuMapper;
	
	@Autowired
	MOpCodeMapper mOpCodeMapper;
	
    /**
     * @Method Name : viewMenuMng
     * @작성일 : 2023. 8. 22.
     * @작성자 : NK.KIM
     * @Method 설명 : 시스템관리 > 메뉴 관리
     * @return
     */
    @GetMapping("/menu/list.do")
    public String viewMenuMng(@RequestParam (value="menuId", required= false) String menuId , Model model){
    	Map<String,Object> paramMap = new HashMap<String,Object>();
    	if("all".equals(menuId) || "".equals(menuId)) {
    		paramMap.put("menuId", null);
    	} else {
    		paramMap.put("menuId", menuId);
    	}
    	
    	//메뉴 List
    	List<Map<String,Object>> dataTbResult = new ArrayList<Map<String,Object>>( );
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
    	model.addAttribute("menuList", dataTbResult);
    	//검색조건
    	model.addAttribute("upperMenuList", mOpMenuMapper.findAllMenuIdAndUpperMenuIdIsNullAndSortNoIsNotNullByMenuLvlAndUseYn(null));
    	model.addAttribute("menuId", menuId);
    	return "view/systemmng/menuList";
    }
    
    /**
     * @Method Name : mainMenuSave
     * @작성일 : 2023. 8. 28
     * @작성자 : KY.LEE
     * @Method 설명 : 메인 메뉴 등록
     * @param paramMap
     * @return
     */
   @PostMapping("/menu/main/save.ajax")
   public @ResponseBody CommonResponse<?> mainMenuSave(@RequestBody MOpMenu mOpMenu){
		ValidateBuilder dtoValidator = new ValidateBuilder(mOpMenu);
		ValidateResult dtoValidatorResult = dtoValidator
			        .addRule("menuNm", new ValidateChecker().setRequired().setMaxLength(50, "메뉴명은 50자를 넘을 수 없습니다."))
			        .addRule("urlPttrn", new ValidateChecker().setRequired().setMaxLength(100, "URL 패턴은 100자를 넘을 수 없습니다."))
			        .addRule("urlAddr", new ValidateChecker().setRequired().setMaxLength(100, "URL 주소는 100자를 넘을 수 없습니다.")).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
		
	  try {
   		 menuMngService.saveMainMenu(mOpMenu);
   	 } catch(Exception e) {
   		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "메뉴 등록중 오류가 발생했습니다.");
	 }
   	 return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "메뉴 등록에 성공했습니다.");
   }
   
   /**
    * @Method Name : updateMainMenu
    * @작성일 : 2023. 8. 28
    * @작성자 : KY.LEE
    * @Method 설명 : 메인 메뉴 수정
    * @param paramMap
    * @return
    */
  @PostMapping("/menu/main/update.ajax")
  public @ResponseBody CommonResponse<?> updateMainMenu(@RequestBody MOpMenu mOpMenu){
 		ValidateBuilder dtoValidator = new ValidateBuilder(mOpMenu);
 		ValidateResult dtoValidatorResult =  dtoValidator
 			.addRule("menuNm", new ValidateChecker().setRequired().setMaxLength(50, "메뉴명은 50자를 넘을 수 없습니다."))
 			.addRule("urlPttrn", new ValidateChecker().setRequired().setMaxLength(100, "URL 패턴은 100자를 넘을 수 없습니다."))
 			.addRule("urlAddr", new ValidateChecker().setRequired().setMaxLength(100, "URL 주소는 100자를 넘을 수 없습니다.")).isValid();
 		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
 		
	  try {
  		 menuMngService.updateMainMenu(mOpMenu);
  	 } catch(Exception e) {
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "메뉴 수정중 오류가 발생했습니다.");
	 }
   	 return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK ,"메인 메뉴 수정에 성공했습니다.");
  }

  /**
   * @Method Name : updateSubMenu
   * @작성일 : 2023. 8. 28
   * @작성자 : KY.LEE
   * @Method 설명 : 메인 메뉴 수정
   * @param paramMap
   * @return
   */
  @PostMapping("/menu/sub/update.ajax")
  public @ResponseBody CommonResponse<?> updateSubMenu(@RequestBody MOpMenu mOpMenu){
	  ValidateBuilder dtoValidator = new ValidateBuilder(mOpMenu);
	  ValidateResult dtoValidatorResult = dtoValidator
	  			.addRule("menuId", new ValidateChecker().setRequired())
	 			.addRule("menuNm", new ValidateChecker().setRequired().setMaxLength(50, "메뉴명은 50자를 넘을 수 없습니다."))
	 			.addRule("urlPttrn", new ValidateChecker().setRequired().setMaxLength(100, "URL 패턴은 100자를 넘을 수 없습니다."))
	 			.addRule("urlAddr", new ValidateChecker().setRequired().setMaxLength(100, "URL 주소는 100자를 넘을 수 없습니다.")).isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
	  
	  try {
		  menuMngService.updateSubMenu(mOpMenu);
	  } catch(Exception e) {
		  return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "메뉴 수정중 오류가 발생했습니다.");
	  }
	  return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK ,"메인 메뉴 수정에 성공했습니다.");
  }
   
   /**
    * @Method Name : viewMenuDetailInfo
    * @작성일 : 2023. 8. 28
    * @작성자 : KY.LEE
    * @Method 설명 : 메뉴 상세 보기
    * @param paramMap
    * @return
    */
 //모달로 변경 주석
//   @GetMapping("/menu/{menuId}/detail.do")
//   public String viewMenuDetailInfo(@PathVariable("menuId") String menuId, Model model){
//	   model.addAttribute("mOpMenu", menuMngService.getMenuDetailInfo(menuId));
//	   model.addAttribute("subMenuList", mOpMenuMapper.findAllBySortNoIsNotNullAndUpprMenuId(menuId));
//	   model.addAttribute("ctgryCdList", mOpCodeMapper.findAllCodeListByGrpCdId("MENU_CTGRY_CD"));
//	   return "view/systemmng/menuDetail";
//   } 
   
   /**
    * @Method Name : saveSubMenu
    * @작성일 : 2023. 8. 28
    * @작성자 : KY.LEE
    * @Method 설명 : 서브 메뉴 등록
    * @param paramMap
    * @return
    */
  @PostMapping("/menu/sub/save.ajax")
  public @ResponseBody CommonResponse<?> saveSubMenu(@RequestParam Map<String,Object> paramMap){
		ValidateBuilder dtoValidator = new ValidateBuilder(paramMap);
		ValidateResult dtoValidatorResult = dtoValidator
					.addRule("menuNm", new ValidateChecker().setRequired().setMaxLength(50, "메뉴명은 50자를 넘을 수 없습니다."))
					.addRule("urlAddr", new ValidateChecker().setRequired().setMaxLength(100, "URL 주소는 100자를 넘을 수 없습니다.")).isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) { 
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
		
	  MOpMenu mOpMenu = new MOpMenu();
	  try {
		  mOpMenu = menuMngService.saveSubMenu(paramMap);
  	 } catch(Exception e) {
  		 return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , "하위 메뉴 등록중 오류가 발생했습니다.");
	 }
  	return CommonResponse.ResponseSuccess(HttpStatus.OK , "하위 메뉴 등록에 성공했습니다.", null , mOpMenu);
  }
  
  /**
   * @Method Name : deleteAuthority
   * @작성일 : 2023. 8. 29.
   * @작성자 : KY.LEE
   * @Method 설명 : 서브 메뉴 삭제
   * @return
   */
  @GetMapping("/menu/sub/{menuId}/delete.ajax")
  public @ResponseBody CommonResponse<?> deleteSubMenu(@PathVariable("menuId") String menuId){
		ValidateBuilder dtoValidator = new ValidateBuilder(new MOpMenu(menuId));
		ValidateResult dtoValidatorResult = dtoValidator.addRule("menuId", new ValidateChecker().setRequired()).isValid();
		
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
		
	try {
  		mOpMenuMapper.deleteMOpMenuByMenuId(menuId);
  	} catch (Exception e) {
  		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST ,"하위 메뉴 삭제중 오류가 발생했습니다.");
	}
  	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "하위 메뉴가 삭제 되었습니다.");
  }

  /**
   * @Method Name : deleteAuthority
   * @작성일 : 2023. 8. 29.
   * @작성자 : KY.LEE
   * @Method 설명 : 서브 메뉴 삭제
   * @return
   */
  @GetMapping("/menu/main/{menuId}/delete.ajax")
  public @ResponseBody CommonResponse<?> deleteMainMenu(@PathVariable("menuId") String menuId){
		ValidateBuilder dtoValidator = new ValidateBuilder(new MOpMenu(menuId));
		ValidateResult dtoValidatorResult = dtoValidator.addRule("menuId", new ValidateChecker().setRequired()).isValid();
    	
    	if(!dtoValidatorResult.isSuccess()) {
    		return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST , dtoValidatorResult.getMessage());
    	}
		
	 try {
		  Long subMenuCnt = mOpMenuMapper.countByUpperMenuId(menuId);
		  if(subMenuCnt > 0) {
			  return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST ,"하위 메뉴가 존재하여 상위 메뉴를 삭제 할 수 없습니다.");
		  } else {
			  mOpMenuMapper.deleteMOpMenuByMenuId(menuId);
		  }
	  } catch (Exception e) {
		  return CommonResponse.ResponseCodeAndMessage(HttpStatus.BAD_REQUEST ,"메뉴 삭제중 오류가 발생했습니다.");
	  }
	  return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK , "메뉴가 삭제 되었습니다.");
  }
}

