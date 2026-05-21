package com.glucose.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glucose.entity.FollowExecute;
import com.glucose.entity.FollowUp;
import com.glucose.service.FollowExecuteService;
import com.glucose.service.FollowUpService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Resource
    private FollowUpService followUpService;

    @Resource
    private FollowExecuteService followExecuteService;

    // 护士端：获取待执行随访（未过期 + 未执行）
    @GetMapping("/list")
    public Map<String, Object> list(String patientId) {
        QueryWrapper<FollowUp> wrapper = new QueryWrapper<>();
        if (patientId != null && !patientId.isEmpty()) {
            wrapper.eq("patient_id", patientId);
        }
        wrapper.ge("follow_date", LocalDate.now());  // 1. 不过期
        wrapper.eq("is_execute", 0);                // 2. 未执行（你要加的就这行）
        wrapper.orderByDesc("id");

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", followUpService.list(wrapper));
        return map;
    }

    // 医生新增随访
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody FollowUp follow) {
        boolean ok = followUpService.save(follow);
        Map<String, Object> map = new HashMap<>();
        map.put("code", ok ? 200 : 500);
        map.put("msg", ok ? "保存成功" : "保存失败");
        return map;
    }

    @PostMapping("/execute")
    public Map<String, Object> execute(@RequestBody FollowExecute followExecute) {

        System.out.println("执行的随访ID：" + followExecute.getFollowId()); // 打印ID

        // 1. 保存执行记录
        boolean save = followExecuteService.save(followExecute);

        // 2. 更新原随访计划状态为【已执行】
        FollowUp update = new FollowUp();
        update.setId(followExecute.getFollowId());
        update.setIsExecute(1);
        followUpService.updateById(update);

        Map<String, Object> map = new HashMap<>();
        map.put("code", save ? 200 : 500);
        map.put("msg", save ? "随访执行成功，健康事件已记录" : "执行失败");
        return map;
    }

    // 医生端：查询【所有】随访计划（不过期、不隐藏、包含已执行）
    @GetMapping("/listAll")
    public Map<String, Object> listAll(@RequestParam(required = false) String patientId) {
        QueryWrapper<FollowUp> wrapper = new QueryWrapper<>();
        if (patientId != null && !patientId.isEmpty()) {
            wrapper.eq("patient_id", patientId);
        }
        // 医生端：**不做任何日期、执行状态过滤**，查全部
        wrapper.orderByDesc("follow_date");
        List<FollowUp> list = followUpService.list(wrapper);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", list);
        return map;
    }

    // 1. 删除随访计划
    @GetMapping("/delete")
    public Map<String, Object> delete(@RequestParam Long id) {
        boolean success = followUpService.removeById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("code", success ? 200 : 500);
        map.put("msg", success ? "删除成功" : "删除失败");
        return map;
    }

    // 2. 查看执行记录（修复500、字段映射）
    @GetMapping("/execute/record")
    public Map<String, Object> getExecuteRecord(Long followId) {
        QueryWrapper<FollowExecute> qw = new QueryWrapper<>();
        qw.eq("follow_id", followId);
        FollowExecute execute = followExecuteService.getOne(qw);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", execute);
        return map;
    }
}