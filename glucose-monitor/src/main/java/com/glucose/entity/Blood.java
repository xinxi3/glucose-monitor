package com.glucose.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

public class Blood {

    private Long id;
    private String patientId;
    private String patientName;
    private String timeType;
    private Double sugarVal;
    private Date collectTime;
    private String symptom;
    private Date createTime;

    // 空构造
    public Blood() {}

    // getter & setter
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

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public Double getSugarVal() {
        return sugarVal;
    }

    public void setSugarVal(Double sugarVal) {
        this.sugarVal = sugarVal;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}