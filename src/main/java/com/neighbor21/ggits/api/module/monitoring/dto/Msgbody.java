package com.neighbor21.ggits.api.module.monitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class Msgbody {
private static Logger log = Logger.getLogger(Msgbody.class.getName());
@JsonProperty("itemList")
    List< Itemlist> itemlistList ;
  public void setItemlistList(List<Itemlist> itemlistList) { 
		this.itemlistList=itemlistList;
	} 
public List<Itemlist> getItemlistList() { 
		if(itemlistList == null)
		itemlistList=new ArrayList<Itemlist>(); 
		return itemlistList;
	} 

}