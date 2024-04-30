package com.neighbor21.ggits.common.entity;
public class QrtzJobDetails {
    private String    schedName;        //null
    private String    jobName;        //null
    private String    jobGroup;        //null
    private String    description;        //null
    private String    jobClassName;        //null
    private String    isDurable;        //null
    private String    isNonconcurrent;        //null
    private String    isUpdateData;        //null
    private String    requestsRecovery;        //null
    private String    jobData;        //null


  public String getSchedName() {
    return schedName;
  }

  public void setSchedName(String schedName) {
    this.schedName = schedName;
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


  public String getJobClassName() {
    return jobClassName;
  }

  public void setJobClassName(String jobClassName) {
    this.jobClassName = jobClassName;
  }


  public String getIsDurable() {
    return isDurable;
  }

  public void setIsDurable(String isDurable) {
    this.isDurable = isDurable;
  }


  public String getIsNonconcurrent() {
    return isNonconcurrent;
  }

  public void setIsNonconcurrent(String isNonconcurrent) {
    this.isNonconcurrent = isNonconcurrent;
  }


  public String getIsUpdateData() {
    return isUpdateData;
  }

  public void setIsUpdateData(String isUpdateData) {
    this.isUpdateData = isUpdateData;
  }


  public String getRequestsRecovery() {
    return requestsRecovery;
  }

  public void setRequestsRecovery(String requestsRecovery) {
    this.requestsRecovery = requestsRecovery;
  }


  public String getJobData() {
    return jobData;
  }

  public void setJobData(String jobData) {
    this.jobData = jobData;
  }

}
