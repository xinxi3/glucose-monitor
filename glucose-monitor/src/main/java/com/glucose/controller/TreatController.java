package com.glucose.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glucose.entity.TreatRecord;
import com.glucose.service.TreatRecordService;
import com.glucose.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/treat")
public class TreatController {

    @Resource
    private TreatRecordService treatRecordService;

    // ======================
    // 【唯一正确】列表接口：支持全部 + 按患者筛选
    // ======================
    @GetMapping("/list")
    public Result<List<TreatRecord>> list(
            @RequestParam(required = false) String patientId
    ) {
        LambdaQueryWrapper<TreatRecord> wrapper = new LambdaQueryWrapper<>();

        // 按患者ID筛选
        if (patientId != null && !patientId.trim().isEmpty()) {
            wrapper.eq(TreatRecord::getPatientId, patientId);
        }

        // 按诊疗时间倒序，最新的在最上面
        wrapper.orderByDesc(TreatRecord::getTreatTime);

        List<TreatRecord> list = treatRecordService.list(wrapper);
        return Result.success(list);
    }

    // ======================
    // 新增诊疗记录
    // ======================
    // 新增诊疗
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody TreatRecord record) {
        boolean ok = treatRecordService.save(record);
        Map<String, Object> map = new HashMap<>();
        map.put("code", ok ? 200 : 500);
        map.put("msg", ok ? "保存成功" : "保存失败");
        return map;
    }
}