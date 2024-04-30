package com.neighbor21.ggits.common.entity;

import java.sql.Timestamp;

public class ScsEmrgVhclLogInfo extends CommonEntity {
    private Timestamp    date;        
    private String    serviceid;        
    private String    evno;        
    private double    currentlat;        
    private double    currentlng;        
    private long    speed;    
    
    private Timestamp arrivalDate;   
    private String arrivaltimeFormat;
    private String emrgCurSttsCd;
    private String ocrtype;
    private Double kilometer;
    
  public Timestamp getDate() {
    return date;
  }

  public void setDate(Timestamp date) {
    this.date = date;
  }


  public String getServiceid() {
    return serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }


  public String getEvno() {
    return evno;
  }

  public void setEvno(String evno) {
    this.evno = evno;
  }


  public double getCurrentlat() {
    return currentlat;
  }

  public void setCurrentlat(double currentlat) {
    this.currentlat = currentlat;
  }


  public double getCurrentlng() {
    return currentlng;
  }

  public void setCurrentlng(double currentlng) {
    this.currentlng = currentlng;
  }


  public long getSpeed() {
    return speed;
  }

  public void setSpeed(long speed) {
    this.speed = speed;
  }

public Timestamp getArrivalDate() {
	return arrivalDate;
}

public void setArrivalDate(Timestamp arrivalDate) {
	this.arrivalDate = arrivalDate;
}

public String getArrivaltimeFormat() {
	return arrivaltimeFormat;
}

public void setArrivaltimeFormat(String arrivaltimeFormat) {
	this.arrivaltimeFormat = arrivaltimeFormat;
}

public String getEmrgCurSttsCd() {
	return emrgCurSttsCd;
}

public void setEmrgCurSttsCd(String emrgCurSttsCd) {
	this.emrgCurSttsCd = emrgCurSttsCd;
}

public String getOcrtype() {
	return ocrtype;
}

public void setOcrtype(String ocrtype) {
	this.ocrtype = ocrtype;
}

public Double getKilometer() {
	return kilometer;
}

public void setKilometer(Double kilometer) {
	this.kilometer = kilometer;
}

}
