package com.glucose.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glucose.entity.BloodSugar;
import com.glucose.entity.SysUser;
import com.glucose.mapper.BloodSugarMapper;
import com.glucose.service.BloodSugarService;
import com.glucose.util.Result;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/blood")
public class BloodSugarController {

    private final BloodSugarService bloodSugarService;
    private final BloodSugarMapper bloodSugarMapper;

    // 构造注入
    public BloodSugarController(BloodSugarService bloodSugarService, BloodSugarMapper bloodSugarMapper) {
        this.bloodSugarService = bloodSugarService;
        this.bloodSugarMapper = bloodSugarMapper;
    }

    // 旧保存接口
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody BloodSugar bloodSugar) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean ok = bloodSugarService.saveBlood(bloodSugar);
            if (ok) {
                map.put("code", 200);
                map.put("msg", "血糖保存成功");
            } else {
                map.put("code", 500);
                map.put("msg", "保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "服务器错误：" + e.getMessage());
        }
        return map;
    }

//    // 趋势
//    @GetMapping("/trend")
//    public Result trend(@RequestParam String patientId) {
//        QueryWrapper<BloodSugar> wrapper = new QueryWrapper<>();
//        wrapper.eq("patient_id", patientId);
//        wrapper.orderByAsc("collect_time");
//        List<BloodSugar> list = bloodSugarService.list(wrapper);
//        return Result.success(list);
//    }

    // 统计
    @GetMapping("/stat")
    public Map<String, Object> stat() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("data", bloodSugarService.getStatData());
        return map;
    }

    // ======================= ✅ 查询我的血糖（正常显示旧数据） =======================
    @GetMapping("/list")
    public Result list(HttpSession session) {
        SysUser user = (SysUser) session.getAttribute("loginUser");
        if (user == null) {
            return Result.fail(500,"未登录");
        }

        QueryWrapper<BloodSugar> wrapper = new QueryWrapper<>();
        wrapper.eq("patient_id", user.getUsername());
        wrapper.orderByAsc("collect_time");

        List<BloodSugar> list = bloodSugarMapper.selectList(wrapper);
        return Result.success(list);
    }

    @PostMapping("/submit")
    public Map<String,Object> submit(@RequestBody BloodSugar blood, HttpSession session) {
        Map<String,Object> map = new HashMap<>();
        SysUser user = (SysUser) session.getAttribute("loginUser");
        blood.setPatientId(user.getUsername());
        blood.setPatientName(user.getName());
        blood.setCreateTime(new Date());
        int i = bloodSugarMapper.insert(blood);
        if(i>0){
            map.put("code",200);
            map.put("msg","上报成功");
        }else{
            map.put("code",500);
            map.put("msg","上报失败");
        }
        return map;
    }

    @GetMapping("/trend")
    public Result trend(@RequestParam String patientId) {
        QueryWrapper<BloodSugar> wrapper = new QueryWrapper<>();
        wrapper.eq("patient_id", patientId).orderByAsc("collect_time");
        return Result.success(bloodSugarMapper.selectList(wrapper));
    }
}