package com.glucose.controller;

import com.glucose.entity.Patient;
import com.glucose.entity.SysUser;
import com.glucose.entity.Treat;
import com.glucose.service.PatientService;
import com.glucose.service.TreatService;
import com.glucose.service.UserService;
import com.glucose.util.Result;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Resource
    private PatientService patientService;

    @Autowired
    private TreatService treatService;

//    @GetMapping("/list")
//    public Map<String, Object> list() {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            List<Patient> list = patientService.getAll();
//            map.put("code", 200);
//            map.put("data", list);
//        } catch (Exception e) {
//            e.printStackTrace();
//            map.put("code", 500);
//            map.put("msg", "系统错误");
//        }
//        return map;
//    }
@GetMapping("/list")
public Result list() {
    List<Patient> list = patientService.list();
    return Result.success(list);
}

    @GetMapping("/search")
    public Map<String, Object> search(String name) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Patient> list = patientService.search(name);
            map.put("code", 200);
            map.put("data", list);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
        }
        return map;
    }

    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody Patient patient) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean b = patientService.add(patient);
            if (b) {
                map.put("code", 200);
                map.put("msg", "保存成功");
            } else {
                map.put("code", 500);
                map.put("msg", "保存失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "服务器错误");
        }
        return map;
    }

    @GetMapping("/info")
    public Map<String, Object> getPatientInfo(@RequestParam Long userId) {

        System.out.println("前端传入的患者ID：" + userId); // 后台看打印

        // 重点：根据 userId 查询 patient 表
        Patient patient = patientService.getById(userId);

        Map<String, Object> map = new HashMap<>();

        if (patient == null) {
            System.out.println("查无此患者！");
            map.put("code", 200);  // 这里强制返回200，不让前端报错
            map.put("data", null);
            map.put("msg", "无此患者信息");
        } else {
            map.put("code", 200);
            map.put("data", patient);
        }

        return map;
    }

    @GetMapping("/current")
    public Map<String, Object> getCurrentPatient(HttpSession session) {
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");
        if (loginUser == null) {
            return Map.of("code", 401, "msg", "未登录");
        }
        return Map.of("code", 200, "data", loginUser);
    }

    @GetMapping("/api/patient/info")
    public Map<String, Object> getMyInfo(HttpSession session) {
        // 这里必须写 SysUser，不是 User！
        SysUser loginUser = (SysUser) session.getAttribute("loginUser");

        if (loginUser == null) {
            return Map.of("code", 401, "msg", "未登录");
        }

        // 直接返回登录用户信息，不用查patient表
        return Map.of("code", 200, "data", loginUser);
    }

    @GetMapping("/treat/my")
    public Result<Treat> getMyTreat(HttpSession session) {
        SysUser user = (SysUser) session.getAttribute("loginUser");
        if(user == null) {
            return Result.fail(500,"未登录");
        }
        // user.getUsername() = HZ002，和你数据库patient_id一致
        Treat treat = treatService.getByPatientId(user.getUsername());
        return Result.success(treat);
    }
}