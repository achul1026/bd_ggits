package com.neighbor21.ggits.api.module.monitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;


public class Msgheader {
private static Logger log = Logger.getLogger(Msgheader.class.getName());

	@JsonProperty("headerCd")
    String headercd="";
	@JsonProperty("headerMsg")
    String headermsg="";
	@JsonProperty("itemCount")
    String itemcount="";
  public void setHeadercd(String headercd) { 
		this.headercd=headercd;
	} 
    public String getHeadercd() { 
		return headercd;
	} 
  public void setHeadermsg(String headermsg) { 
		this.headermsg=headermsg;
	} 
    public String getHeadermsg() { 
		return headermsg;
	} 
  public void setItemcount(String itemcount) { 
		this.itemcount=itemcount;
	} 
    public String getItemcount() { 
		return itemcount;
	} 

}