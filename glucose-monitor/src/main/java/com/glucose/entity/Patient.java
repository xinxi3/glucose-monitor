package com.glucose.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

@TableName("patient")
public class Patient {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String patientId;
    private String name;
    private String gender;
    private Integer age;
    private String phone;
    private String idCard;
    private String dept;
    private String status;
    private String diabetesHistory;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getIdCard() { return idCard; }
    public void setIdCard(String idCard) { this.idCard = idCard; }
    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDiabetesHistory() { return diabetesHistory; }
    public void setDiabetesHistory(String diabetesHistory) { this.diabetesHistory = diabetesHistory; }

}