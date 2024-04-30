package com.neighbor21.ggits.common.entity;
public class QrtzBlobTriggers {
    private String    schedName;        //null
    private String    triggerName;        //null
    private String    triggerGroup;        //null
    private String    blobData;        //null


  public String getSchedName() {
    return schedName;
  }

  public void setSchedName(String schedName) {
    this.schedName = schedName;
  }


  public String getTriggerName() {
    return triggerName;
  }

  public void setTriggerName(String triggerName) {
    this.triggerName = triggerName;
  }


  public String getTriggerGroup() {
    return triggerGroup;
  }

  public void setTriggerGroup(String triggerGroup) {
    this.triggerGroup = triggerGroup;
  }


  public String getBlobData() {
    return blobData;
  }

  public void setBlobData(String blobData) {
    this.blobData = blobData;
  }

}
