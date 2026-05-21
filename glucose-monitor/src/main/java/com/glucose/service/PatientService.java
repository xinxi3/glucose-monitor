package com.glucose.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glucose.entity.Patient;
import com.glucose.mapper.PatientMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PatientService extends ServiceImpl<PatientMapper, Patient> {

    @Resource
    private PatientMapper patientMapper;

    // 查询所有
    public List<Patient> getAll() {
        return patientMapper.selectList(null);
    }

    // 搜索
    public List<Patient> search(String name) {
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            qw.like("name", name);
        }
        return patientMapper.selectList(qw);
    }

    // 新增（绝对不冲突、不报错）
    public boolean add(Patient patient) {
        try {
            List<Patient> list = getAll();
            int max = 0;
            for (Patient p : list) {
                String no = p.getPatientId().replace("HZ", "");
                int num = Integer.parseInt(no);
                if (num > max) max = num;
            }
            patient.setPatientId("HZ" + String.format("%03d", max + 1));
            return patientMapper.insert(patient) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}