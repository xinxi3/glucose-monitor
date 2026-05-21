package com.glucose.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glucose.entity.BloodSugar;
import com.glucose.mapper.BloodSugarMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class BloodSugarService extends ServiceImpl<BloodSugarMapper, BloodSugar> {

    @Resource
    private BloodSugarMapper bloodSugarMapper;

    public boolean saveBlood(BloodSugar bloodSugar) {
        try {
            if (bloodSugar.getCreateTime() == null) {
                bloodSugar.setCreateTime(new Date());
            }
            return bloodSugarMapper.insert(bloodSugar) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 趋势图表
    public List<Map<String, Object>> getBloodTrend() {
        return bloodSugarMapper.selectBloodTrend();
    }

    // 统计数据
    public Map<String, Object> getStatData() {
        return bloodSugarMapper.selectBloodStat();
    }
}