package com.glucose.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;

@TableName("follow_up")
public class FollowUp {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String patientId;
    private String patientName;
    private String followType;
    private LocalDate followDate;
    private String followCycle;
    private String followContent;
    private Integer isExecute;


    public FollowUp() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getFollowDate() {
        return followDate;
    }

    public void setFollowDate(LocalDate followDate) {
        this.followDate = followDate;
    }

    public String getFollowCycle() {
        return followCycle;
    }

    public void setFollowCycle(String followCycle) {
        this.followCycle = followCycle;
    }

    public String getFollowContent() {
        return followContent;
    }

    public void setFollowContent(String followContent) {
        this.followContent = followContent;
    }

    public Integer getIsExecute() {
        return isExecute;
    }

    public void setIsExecute(Integer isExecute) {
        this.isExecute = isExecute;
    }

    @Override
    public String toString() {
        return "FollowUp{" +
                "id=" + id +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", followType='" + followType + '\'' +
                ", followDate=" + followDate +
                ", followCycle='" + followCycle + '\'' +
                ", followContent='" + followContent + '\'' +
                '}';
    }
}