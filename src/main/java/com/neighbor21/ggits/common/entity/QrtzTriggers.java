package com.neighbor21.ggits.common.entity;
public class QrtzTriggers {
    private String    schedName;        //null
    private String    triggerName;        //null
    private String    triggerGroup;        //null
    private String    jobName;        //null
    private String    jobGroup;        //null
    private String    description;        //null
    private long    nextFireTime;        //null
    private long    prevFireTime;        //null
    private long    priority;        //null
    private String    triggerState;        //null
    private String    triggerType;        //null
    private long    startTime;        //null
    private long    endTime;        //null
    private String    calendarName;        //null
    private long    misfireInstr;        //null
    private String    jobData;        //null


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


  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }


  public String getJobGroup() {
    return jobGroup;
  }

  public void setJobGroup(String jobGroup) {
    this.jobGroup = jobGroup;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public long getNextFireTime() {
    return nextFireTime;
  }

  public void setNextFireTime(long nextFireTime) {
    this.nextFireTime = nextFireTime;
  }


  public long getPrevFireTime() {
    return prevFireTime;
  }

  public void setPrevFireTime(long prevFireTime) {
    this.prevFireTime = prevFireTime;
  }


  public long getPriority() {
    return priority;
  }

  public void setPriority(long priority) {
    this.priority = priority;
  }


  public String getTriggerState() {
    return triggerState;
  }

  public void setTriggerState(String triggerState) {
    this.triggerState = triggerState;
  }


  public String getTriggerType() {
    return triggerType;
  }

  public void setTriggerType(String triggerType) {
    this.triggerType = triggerType;
  }


  public long getStartTime() {
    return startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }


  public long getEndTime() {
    return endTime;
  }

  public void setEndTime(long endTime) {
    this.endTime = endTime;
  }


  public String getCalendarName() {
    return calendarName;
  }

  public void setCalendarName(String calendarName) {
    this.calendarName = calendarName;
  }


  public long getMisfireInstr() {
    return misfireInstr;
  }

  public void setMisfireInstr(long misfireInstr) {
    this.misfireInstr = misfireInstr;
  }


  public String getJobData() {
    return jobData;
  }

  public void setJobData(String jobData) {
    this.jobData = jobData;
  }

}
