package com.neighbor21.ggits.common.entity;
public class QrtzSimpleTriggers {
    private String    schedName;        //null
    private String    triggerName;        //null
    private String    triggerGroup;        //null
    private long    repeatCount;        //null
    private long    repeatInterval;        //null
    private long    timesTriggered;        //null


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


  public long getRepeatCount() {
    return repeatCount;
  }

  public void setRepeatCount(long repeatCount) {
    this.repeatCount = repeatCount;
  }


  public long getRepeatInterval() {
    return repeatInterval;
  }

  public void setRepeatInterval(long repeatInterval) {
    this.repeatInterval = repeatInterval;
  }


  public long getTimesTriggered() {
    return timesTriggered;
  }

  public void setTimesTriggered(long timesTriggered) {
    this.timesTriggered = timesTriggered;
  }

}
