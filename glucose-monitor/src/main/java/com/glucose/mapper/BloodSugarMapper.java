package com.glucose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glucose.entity.BloodSugar;
import org.apache.ibatis.annotations.Select;
import java.util.List;
import java.util.Map;

public interface BloodSugarMapper extends BaseMapper<BloodSugar> {

    // 血糖趋势
    @Select("SELECT collect_time AS collectTime, sugar_val AS sugarVal FROM blood_sugar ORDER BY collect_time")
    List<Map<String, Object>> selectBloodTrend();

    // 综合统计
    @Select("SELECT " +
            "ROUND(AVG(CASE WHEN time_type='空腹' THEN sugar_val END),1) AS avgEmpty," +
            "ROUND(AVG(CASE WHEN time_type='餐后2h' THEN sugar_val END),1) AS avgAfter," +
            "COUNT(*) AS totalRecord " +
            "FROM blood_sugar")
    Map<String, Object> selectBloodStat();
}