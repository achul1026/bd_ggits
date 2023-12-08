package com.neighbor21.ggits.web.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {

	
    /**
     * @Method Name : viewErrorPage
     * @작성일 : 2023. 9. 26.
     * @작성자 : KY.LEE
     * @Method 설명 : 에러페이지
     * @return
     */
   @GetMapping("/error.do")
   public String viewErrorPage(){
	   
       return "error/error";
   }

}
