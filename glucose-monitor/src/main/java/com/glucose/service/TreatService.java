package com.glucose.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glucose.entity.Treat;
import com.glucose.mapper.TreatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatService {
    @Autowired
    private TreatMapper treatMapper;

    // 根据患者账号查询诊疗方案
    public Treat getByPatientId(String patientId) {
        LambdaQueryWrapper<Treat> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Treat::getPatientId, patientId);
        wrapper.orderByDesc(Treat::getCreateTime); // 取最新一条
        return treatMapper.selectOne(wrapper);
    }
}