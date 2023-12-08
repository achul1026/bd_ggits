package com.neighbor21.ggits.web.controller.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neighbor21.ggits.common.dto.MapFacilityMenuDTO;
import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.enums.MapFacilitySubMenuCd;
import com.neighbor21.ggits.common.mapper.AdsiMFaDsrcMapper;
import com.neighbor21.ggits.common.mapper.AdsiMFaVdsMapper;
import com.neighbor21.ggits.common.mapper.AdsiSmcrsrdCrsrdInfoMapper;
import com.neighbor21.ggits.common.mapper.ScsTConIntflowMapper;

/**
 * 연계 시설물 조회 맵 서브메뉴 컨트로러
 */
@Controller
@RequestMapping("/map/facility")
public class MapFacilityController {
	
	@Autowired
	AdsiMFaVdsMapper adsiMFaVdsMapper;
	
	@Autowired
	AdsiMFaDsrcMapper adsiMFaDsrcMapper;
	
	@Autowired
	AdsiSmcrsrdCrsrdInfoMapper adsiSmcrsrdCrsrdInfoMapper;
	
	@Autowired
	ScsTConIntflowMapper scsTConIntflowMapper;
	
	
    /**
     * 연계 시설물 조회 맵  viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/information/{type}.ajax")
    public String getPattern(Model model , @PathVariable String type, MapFacilityMenuDTO mapFacilityMenuDTO) {
    	List<Object> resultList = new ArrayList<Object>();
    	int totalCnt = 0;
    	switch (MapFacilitySubMenuCd.getEnum(type)) {
			case VDS: // VDS
				resultList = adsiMFaVdsMapper.findAllVDSForFacility(mapFacilityMenuDTO);
				totalCnt = adsiMFaVdsMapper.countVDSForFacility(mapFacilityMenuDTO);
				break;
			case DSRC: // DSRC
				resultList = adsiMFaDsrcMapper.findAllDSRCForFacility(mapFacilityMenuDTO);
				totalCnt = adsiMFaDsrcMapper.countDSRCForFacility(mapFacilityMenuDTO);
				break;
			case SMART_INTERSECTION: //스마트 교차로
				resultList = adsiSmcrsrdCrsrdInfoMapper.findAllSmartForFacility(mapFacilityMenuDTO);
				totalCnt = adsiSmcrsrdCrsrdInfoMapper.countSmartForFacility(mapFacilityMenuDTO);
				break;
			case SIGNAL: //신호정보
				resultList = scsTConIntflowMapper.findAllSignalForFacility(mapFacilityMenuDTO);
				totalCnt = scsTConIntflowMapper.countSignalForFacility(mapFacilityMenuDTO);
				break;
			default:
				break;
		}
    	
    	Paging paging = new Paging();
    	paging.setPageSize(5);
		paging.setPageNo(mapFacilityMenuDTO.getPage());
    	paging.setTotalCount(totalCnt);
    	model.addAttribute("resultList", resultList);
    	model.addAttribute("paging", paging);

    	return "map/"+type;
    }
    
    
    /**
	 * @Method Name : findFacilityAjax
	 * @작성일 : 2023. 8. 22.
	 * @작성자 : NK.KIM
	 * @Method 설명 : 연계시설물 비동기 호출
	 * @return
	 */
	@GetMapping(value ="/information/{type}/data.ajax")
    public @ResponseBody CommonResponse<?> findFacilityAjax(@PathVariable String type,MapFacilityMenuDTO mapFacilityMenuDTO){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		List<Object> resultList = new ArrayList<Object>();
    	int totalCnt = 0;
    	switch (MapFacilitySubMenuCd.getEnum(type)) {
	    	case VDS: // VDS
	    		resultList = adsiMFaVdsMapper.findAllVDSForFacility(mapFacilityMenuDTO);
	    		totalCnt = adsiMFaVdsMapper.countVDSForFacility(mapFacilityMenuDTO);
	    		break;
	    	case DSRC: // DSRC
	    		resultList = adsiMFaDsrcMapper.findAllDSRCForFacility(mapFacilityMenuDTO);
	    		totalCnt = adsiMFaDsrcMapper.countDSRCForFacility(mapFacilityMenuDTO);
	    		break;
	    	case SMART_INTERSECTION: //스마트 교차로
	    		resultList = adsiSmcrsrdCrsrdInfoMapper.findAllSmartForFacility(mapFacilityMenuDTO);
	    		totalCnt = adsiSmcrsrdCrsrdInfoMapper.countSmartForFacility(mapFacilityMenuDTO);
	    		break;
	    	case SIGNAL: //신호정보
				resultList = scsTConIntflowMapper.findAllSignalForFacility(mapFacilityMenuDTO);
				totalCnt = scsTConIntflowMapper.countSignalForFacility(mapFacilityMenuDTO);	
				break;
	    	default:
	    		break;
    	}
    	
		Paging paging = new Paging();
		paging.setPageSize(5);
		paging.setPageNo(mapFacilityMenuDTO.getPage());
    	paging.setTotalCount(totalCnt);
    	resultMap.put("list", resultList);
    	resultMap.put("paging", paging);
    	
		return CommonResponse.successToData(resultMap,"");
    }
}
