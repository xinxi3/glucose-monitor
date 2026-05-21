package com.glucose.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.glucose.entity.SugarPlan;
import com.glucose.service.SugarPlanService;
import com.glucose.util.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class SugarPlanController {

    @Resource
    private SugarPlanService sugarPlanService;

    // 列表（可按患者筛选）
    @GetMapping("/list")
    public Result<List<SugarPlan>> list(@RequestParam(required = false) String patientId) {
        LambdaQueryWrapper<SugarPlan> wrapper = new LambdaQueryWrapper<>();
        if (patientId != null && !patientId.isEmpty()) {
            wrapper.eq(SugarPlan::getPatientId, patientId);
        }
        wrapper.orderByDesc(SugarPlan::getCreateTime);
        return Result.success(sugarPlanService.list(wrapper));
    }

    // 新增控糖方案
    @PostMapping("/save")
    public Result<String> save(@RequestBody SugarPlan plan) {
        boolean ok = sugarPlanService.save(plan);
        if (ok) {
            // 成功：返回 200 + 消息
            return Result.success("保存成功");
        } else {
            // 失败：必须传 2 个参数（错误码 + 信息）
            return Result.fail(500, "保存失败");
        }
    }

    // 删除
    @GetMapping("/delete")
    public Result delete(Long id) {
        return Result.success(sugarPlanService.removeById(id));
    }
}