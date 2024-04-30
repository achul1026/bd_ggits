package com.neighbor21.ggits.web.controller.facility;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.neighbor21.ggits.common.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.api.module.facility.FacilityGeoMetricInfoComponent;
import com.neighbor21.ggits.api.module.facility.SmartIntersectionComponent;
import com.neighbor21.ggits.common.enums.ServerMngType;
import com.neighbor21.ggits.common.enums.SrvrSttsCd;
import com.neighbor21.ggits.common.mapper.AdsiSrvrCntnSttsInfoMapper;
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
    AdsiSrvrCntnSttsInfoMapper adsiSrvrCntnSttsInfoMapper;
    
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
    public @ResponseBody ResponseEntity<?> getSmartIntersection(@RequestParam(name = "mngInstCd" ,required = false) String mngInstCd){
        /*List<SmartIntersectionDTO> list = new ArrayList<>();
        list = smartIntersectionComponent.getSmartIntersectionInfo();*/
        List<AdsiSmcrsrdCrsrdInfo> list = facilityGeoMetricInfoComponent.getSmartCrossRoadList(mngInstCd);
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

    @GetMapping("/getVDSCollectList.ajax")
    public @ResponseBody ResponseEntity<?> getVDSCollectList(
            @RequestParam(name = "vdsId") String vdsId,
            @RequestParam(name = "mngInstCd") String mngInstCd
    ){
        List<AdsiVdsColctInfo> list = facilityGeoMetricInfoComponent.getVDSCollectInfo(vdsId, mngInstCd);
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

    @GetMapping("/getDSRCCollectList.ajax")
    public @ResponseBody ResponseEntity<?> getDSRCCollectList(
            @RequestParam(name = "rseId") String rseId
    ){
        List<AdsiDsrcColctInfo> list = facilityGeoMetricInfoComponent.getDSRCCollectInfo(rseId);
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
        List<ScsTConIntlc> list = facilityGeoMetricInfoComponent.getSignalList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * 신호 이동류연계 데이터
     * @return
     */
    @GetMapping("/getSignalInfo.ajax")
    public @ResponseBody ResponseEntity<?> getSignalInfo(
            @RequestParam("intLcno") Long intLcno
    ){
        List<ScsTConIntflow> list = facilityGeoMetricInfoComponent.getSignalInfo(intLcno);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
	/**
     * @Method Name : viewDashboard
     * @작성일 : 2023. 9. 19.
     * @작성자 : NK.KIM
     * @Method 설명 : 연계 시설물 조회 > 서버 정보 조회
     * @return
     */
    @GetMapping("/server/info/list.do")
    public String viewServerInfo(Model model, AdsiSrvrCntnSttsInfo adsiSrvrCntnSttsInfo){
    	List<MOpCode> mngInstCdList = mOpCodeMapper.findAllCodeListByGrpCdId("MNG_INST_CD");
	   	
	   	if(GgitsCommonUtils.isNull(adsiSrvrCntnSttsInfo.getMngInstCd())) {
	   		adsiSrvrCntnSttsInfo.setMngInstCd(mngInstCdList.get(0).getCdId());
	   	}
	   	List<AdsiSrvrCntnSttsInfo> resultList = adsiSrvrCntnSttsInfoMapper.findAllServerInfoList(adsiSrvrCntnSttsInfo);
	   	
   		model.addAttribute("serverInfoList", resultList);
   		model.addAttribute("searchInfo", adsiSrvrCntnSttsInfo);
   		model.addAttribute("mngInstCdList", mngInstCdList);
   		
   		return "view/facility/severInfoList";
   }
    
    @GetMapping("/server/info/list.ajax")
    public @ResponseBody CommonResponse<?> getServerInfo(@RequestParam(required = true, value = "mngInstCd") String mngInstCd) {
    	AdsiSrvrCntnSttsInfo adsiSrvrCntnSttsInfo = new AdsiSrvrCntnSttsInfo();
    	adsiSrvrCntnSttsInfo.setMngInstCd(mngInstCd);
    	List<AdsiSrvrCntnSttsInfo> resultList = adsiSrvrCntnSttsInfoMapper.findAllServerInfoList(adsiSrvrCntnSttsInfo);
    	
    	return CommonResponse.ResponseCodeAndMessage(HttpStatus.OK,"",resultList);
    }
}
