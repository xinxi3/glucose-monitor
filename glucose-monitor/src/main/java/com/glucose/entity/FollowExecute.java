package com.glucose.entity;

import java.time.LocalDateTime;

public class FollowExecute {
    private Long id;
    private Long followId;
    private String patientId;
    private String patientName;
    private String followType;
    private String healthEvent;
    private LocalDateTime executeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getFollowType() {
        return followType;
    }

    public void setFollowType(String followType) {
        this.followType = followType;
    }

    public String getHealthEvent() {
        return healthEvent;
    }

    public void setHealthEvent(String healthEvent) {
        this.healthEvent = healthEvent;
    }

    public LocalDateTime getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(LocalDateTime executeTime) {
        this.executeTime = executeTime;
    }

    //  getter / setter
}