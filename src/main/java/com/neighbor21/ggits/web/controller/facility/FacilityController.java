package com.neighbor21.ggits.web.controller.facility;

import java.util.ArrayList;
import java.util.List;

import com.neighbor21.ggits.api.module.facility.FacilityGeoMetricInfoComponent;
import com.neighbor21.ggits.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.api.module.facility.SmartIntersectionComponent;
import com.neighbor21.ggits.api.module.facility.dto.SmartIntersectionDTO;
import com.neighbor21.ggits.common.enums.ServerMngType;
import com.neighbor21.ggits.common.enums.SrvrSttsCd;
import com.neighbor21.ggits.common.mapper.LTcSrvrProcsCtrlMapper;
import com.neighbor21.ggits.common.mapper.MOpCodeMapper;
import com.neighbor21.ggits.common.util.GgitsCommonUtils;

@Controller
@RequestMapping("/facility")
public class FacilityController {

    @Autowired
    SmartIntersectionComponent smartIntersectionComponent;

    @Autowired
    FacilityGeoMetricInfoComponent facilityGeoMetricInfoComponent;

    @Autowired
    LTcSrvrProcsCtrlMapper lTcSrvrProcsCtrlMapper;
    
    @Autowired
    MOpCodeMapper mOpCodeMapper;
    

    /**     * @Method Name : viewDashboard
     * @작성일 : 2023. 8. 26.
     * @작성자 : NK.KIM
     * @Method 설명 : 연계 시설물 조회 대시보드 화면
     * @return
     */
   @GetMapping("/dashboard.do")
   public String viewDashboard(){
   	
       return "view/facility/dashboard";
   }

    /**
     * 스마트교차로 조회
     *
     * @return the response entity
     */
    @GetMapping("/getSmartIntersection.ajax")
    public @ResponseBody ResponseEntity<?> getSmartIntersection(){
        /*List<SmartIntersectionDTO> list = new ArrayList<>();
        list = smartIntersectionComponent.getSmartIntersectionInfo();*/
        List<AdsiSmcrsrdCrsrdInfo> list = facilityGeoMetricInfoComponent.getSmartCrossRoadList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/getSmartIntersectionLinkList.ajax")
    public @ResponseBody ResponseEntity<?> getSmartIntersectionLinkList(){
        /*List<SmartIntersectionDTO> list = new ArrayList<>();
        list = smartIntersectionComponent.getSmartIntersectionInfo();*/
        List<AdsiSmcrsrdCrsrdAcsRoadInfo> list = facilityGeoMetricInfoComponent.getSmartCrossRoadLinkList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * VDS 정보 조회
     * @return
     */
    @GetMapping("/getVDSList.ajax")
    public @ResponseBody ResponseEntity<?> getVdsGeometric(){
        List<AdsiMFaVds> list = facilityGeoMetricInfoComponent.getVDSList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * DSRC 정보 조회
     * @return
     */
    @GetMapping("/getDSRCList.ajax")
    public @ResponseBody ResponseEntity<?> getDSRCList(){
        List<AdsiMFaDsrc> list = facilityGeoMetricInfoComponent.getDSRCList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * DSRC 정보 조회
     * @return
     */
    @GetMapping("/getDSRCSectionInfoList.ajax")
    public @ResponseBody ResponseEntity<?> getDSRCSectionInfoList(){
        List<AdsiDsrcSctnStlinkMppg> list = facilityGeoMetricInfoComponent.getDSRCSectionList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 신호정보 조회
     * @return
     */
    @GetMapping("/getSignalList.ajax")
    public @ResponseBody ResponseEntity<?> getSignalList(){
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
	/**
     * @Method Name : viewDashboard
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 연계 시설물 조회 > 서버 정보 조회
     * @return
     */
   @GetMapping("/server/info/list.do")
   public String viewServerInfo(Model model, LTcSrvrProcsCtrl lTcSrvrProcsCtrl){
	   if(GgitsCommonUtils.isNull(lTcSrvrProcsCtrl.getSrvrMngType())) {
		   lTcSrvrProcsCtrl.setSrvrMngType(ServerMngType.LOC_GOVMNT_LINKAGE.getCode());
	   }
	   
	   	model.addAttribute("ctgryCdList", mOpCodeMapper.findAllCodeListByGrpCdId("SRVR_MNG_TYPE"));
	   	List<LTcSrvrProcsCtrl> resultList = lTcSrvrProcsCtrlMapper.findLTcSrvrProcsCtrl(lTcSrvrProcsCtrl);
	   	if(!resultList.isEmpty()) {
	   		for(LTcSrvrProcsCtrl dbLTcSrvrProcsCtrl : resultList) {
	   			dbLTcSrvrProcsCtrl.setSrvrSttsCd(SrvrSttsCd.getNameForCode(dbLTcSrvrProcsCtrl.getSrvrSttsCd()));
	   		}
	   	}
   		model.addAttribute("serverInfoList", resultList);
   		model.addAttribute("srvrMngType", lTcSrvrProcsCtrl.getSrvrMngType());
   		
       return "view/facility/severInfoList";
   }
}
