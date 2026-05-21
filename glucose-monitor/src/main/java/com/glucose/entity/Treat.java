package com.glucose.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("treat_record")
public class Treat {
    private Long id;
    private String patientId;
    private String patientName;
    private String diagnosis;     // 诊断
    private String treatPlan;      // 控糖方案
    private String monitorFreq;    // 监测频率
    private String doctorName;     // 医生
    private Date treatTime;
    private Date createTime;

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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatPlan() {
        return treatPlan;
    }

    public void setTreatPlan(String treatPlan) {
        this.treatPlan = treatPlan;
    }

    public String getMonitorFreq() {
        return monitorFreq;
    }

    public void setMonitorFreq(String monitorFreq) {
        this.monitorFreq = monitorFreq;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getTreatTime() {
        return treatTime;
    }

    public void setTreatTime(Date treatTime) {
        this.treatTime = treatTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}