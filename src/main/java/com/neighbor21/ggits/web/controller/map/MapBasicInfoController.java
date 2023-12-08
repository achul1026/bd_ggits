package com.neighbor21.ggits.web.controller.map;

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

import com.neighbor21.ggits.common.entity.CommonResponse;
import com.neighbor21.ggits.common.entity.GyCommInfoLink;
import com.neighbor21.ggits.common.entity.GyCommInfoNode;
import com.neighbor21.ggits.common.entity.Paging;
import com.neighbor21.ggits.common.enums.NodeLinkMapSubMenuCd;
import com.neighbor21.ggits.common.mapper.GyCommInfoLinkMapper;
import com.neighbor21.ggits.common.mapper.GyCommInfoNodeMapper;

/**
 * 연계 시설물 조회 맵 서브메뉴 컨트로러
 */
@Controller
@RequestMapping("/map/basicinfo")
public class MapBasicInfoController {
	
	@Autowired
	GyCommInfoNodeMapper gyCommInfoNodeMapper;
	
	@Autowired
	GyCommInfoLinkMapper gyCommInfoLinkMapper;

    /**
     * 연계 시설물 조회 맵  viewpage 호출
     * @param type the type
     * @return the bigdata sub page
     */
    @GetMapping("/nodelink/{type}.ajax")
    public String getPattern(Model model , @PathVariable String type) {
    	Paging paging = new Paging();
    	switch (NodeLinkMapSubMenuCd.getNameForCode(type)) {
		case NODE_SEARCH:
	    	paging.setPageNo(1);
	    	paging.setPageSize(5);
	    	paging.setTotalCount(gyCommInfoNodeMapper.countAll());
    		model.addAttribute("nodeList", gyCommInfoNodeMapper.findAllNodeList());
    		model.addAttribute("paging", paging);
			break;
		case LINK_SEARCH:
			paging.setPageNo(1);
			paging.setPageSize(5);
			paging.setTotalCount(gyCommInfoLinkMapper.countAll());
			model.addAttribute("linkList", gyCommInfoLinkMapper.findAllGyCommInfoLink());
			model.addAttribute("paging", paging);
			break;
		case NODE_LINK_CURRENT_SITUATION:
			break;
		case NODE_LINK_REFFERENCE:
			break;
		default:
			break;
		}
        return "map/"+type;
    }
    
	/**
	 * @Method Name : nodeList 비동기 호출
	 * @작성일 : 2023. 9. 18.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 노드 목록 비동기 호출
	 * @return
	 */
	@GetMapping(value ="/node/search/list.ajax")
    public @ResponseBody CommonResponse<?> searchNodeList(GyCommInfoNode gyCommInfoNode){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
    	List<GyCommInfoNode> gyCommInfoNodeList = gyCommInfoNodeMapper.findNodeListByNodeIdAndNodeName(gyCommInfoNode);
    	int totalCnt = gyCommInfoNodeMapper.countByNodeNmAndNodeNo(gyCommInfoNode);
		
		Paging paging = new Paging();
		paging.setPageNo(gyCommInfoNode.getPage());
		paging.setPageSize(5);
		paging.setTotalCount(totalCnt);
    	resultMap.put("list", gyCommInfoNodeList);
    	resultMap.put("paging", paging);
    	
		return CommonResponse.successToData(resultMap,"");
    }

	/**
	 * @Method Name : linkList 비동기 호출
	 * @작성일 : 2023. 9. 18.
	 * @작성자 : KY.LEE
	 * @Method 설명 : 링크 목록 비동기 호출
	 * @return
	 */
	@GetMapping(value ="/link/search/list.ajax")
	public @ResponseBody CommonResponse<?> searchLinkList(GyCommInfoLink gyCommInfoLink){
		Map<String,Object> resultMap = new HashMap<String, Object>();
		
		List<GyCommInfoLink> gyCommInfoLinkList = gyCommInfoLinkMapper.findLinkListByLinkId(gyCommInfoLink);
		int totalCnt = gyCommInfoLinkMapper.countByLinkId(gyCommInfoLink);
		
		Paging paging = new Paging();
		paging.setPageNo(gyCommInfoLink.getPage());
		paging.setPageSize(5);
		paging.setTotalCount(totalCnt);
		resultMap.put("list", gyCommInfoLinkList);
		resultMap.put("paging", paging);
		
		return CommonResponse.successToData(resultMap,"");
	}

}
