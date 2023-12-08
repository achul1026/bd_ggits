package com.neighbor21.ggits.web.controller.statistics;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class AnalysisReportController {
	
	/**
     * @Method Name : viewTrafficInfoStatsReport
     * @작성일 : 2023. 9. 7.
     * @작성자 : KC.KIM
     * @Method 설명 : 통계분석  > 분석 리포트
     * @return
     */
	@GetMapping("/analysis/report/list.do")
    public String viewAnalyReport(){
    	
        return "view/statistics/analyReportList";
    }
	
	/**
	 * @Method Name : viewTrafficInfoStatsReport
	 * @작성일 : 2023. 9. 18.
	 * @작성자 : KC.KIM
	 * @Method 설명 : 통계분석  > 분석 리포트 > 데이터 활용 통계 리포트
	 * @return
	 */
	@GetMapping("/analysis/data/report/list.do")
	public String viewDataReport(){
		
		return "view/statistics/dataReportList";
	}
}
