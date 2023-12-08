package com.neighbor21.ggits.common.dto;

public class MapFacilityMenuDTO {
	
	String id;		
	String name;		
	String descr;		
	String stts;		
	String nodeId;		//노드 아이디		
	String nodeName;	//노드명		
	String mngInstNm;	//기관명		
	double lon;			//경도(x축)
	double lat;			//위도(y축)
	int page = 1;		//pageNum
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getStts() {
		return stts;
	}
	public void setStts(String stts) {
		this.stts = stts;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getMngInstNm() {
		return mngInstNm;
	}
	public void setMngInstNm(String mngInstNm) {
		this.mngInstNm = mngInstNm;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
