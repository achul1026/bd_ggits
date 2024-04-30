package com.neighbor21.ggits.common.entity;
public class QrtzSchedulerState {
    private String    schedName;        //null
    private String    instanceName;        //null
    private long    lastCheckinTime;        //null
    private long    checkinInterval;        //null


  public String getSchedName() {
    return schedName;
  }

  public void setSchedName(String schedName) {
    this.schedName = schedName;
  }


  public String getInstanceName() {
    return instanceName;
  }

  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }


  public long getLastCheckinTime() {
    return lastCheckinTime;
  }

  public void setLastCheckinTime(long lastCheckinTime) {
    this.lastCheckinTime = lastCheckinTime;
  }


  public long getCheckinInterval() {
    return checkinInterval;
  }

  public void setCheckinInterval(long checkinInterval) {
    this.checkinInterval = checkinInterval;
  }

}
