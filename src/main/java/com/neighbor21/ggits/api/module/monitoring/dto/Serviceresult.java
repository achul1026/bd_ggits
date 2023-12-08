package com.neighbor21.ggits.api.module.monitoring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;


public class Serviceresult {
private static Logger log = Logger.getLogger(Serviceresult.class.getName());

	@JsonProperty("comMsgHeader")
	String commsgheader="";
@JsonProperty("msgHeader")
    Msgheader msgheader ;
@JsonProperty("msgBody")
    Msgbody msgbody ;
  public void setCommsgheader(String commsgheader) { 
		this.commsgheader=commsgheader;
	} 
    public String getCommsgheader() { 
		return commsgheader;
	} 
    public Msgheader getMsgheader() { 
		if(msgheader==null) msgheader=new Msgheader(); 
		return msgheader;
	} 
  public void setMsgheader( Msgheader msgheader ) { 
		this.msgheader=msgheader;
	} 
    public Msgbody getMsgbody() { 
		if(msgbody==null) msgbody=new Msgbody(); 
		return msgbody;
	} 
  public void setMsgbody( Msgbody msgbody ) { 
		this.msgbody=msgbody;
	} 

}