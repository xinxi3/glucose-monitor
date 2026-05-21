package com.glucose.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

@TableName("blood_sugar")
public class BloodSugar {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String patientId;
    private String patientName;
    private Date collectTime;
    private String timeType;
    private BigDecimal sugarVal;
    private Integer heartRate;
    private String bloodPressure;
    private String symptom;
    private Date createTime;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public Date getCollectTime() { return collectTime; }
    public void setCollectTime(Date collectTime) { this.collectTime = collectTime; }
    public String getTimeType() { return timeType; }
    public void setTimeType(String timeType) { this.timeType = timeType; }
    public BigDecimal getSugarVal() { return sugarVal; }
    public void setSugarVal(BigDecimal sugarVal) { this.sugarVal = sugarVal; }
    public Integer getHeartRate() { return heartRate; }
    public void setHeartRate(Integer heartRate) { this.heartRate = heartRate; }
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    public String getSymptom() { return symptom; }
    public void setSymptom(String symptom) { this.symptom = symptom; }
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }
}