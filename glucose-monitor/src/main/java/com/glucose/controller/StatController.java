package com.glucose.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glucose.entity.Patient;
import com.glucose.service.PatientService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") // 允许前端访问
@RestController
@RequestMapping("/api/stat")
public class StatController {
    @Resource
    private PatientService patientService;

    @RequestMapping("/dept")
    public Map<String,Object> dept(){
        Map<String,Object> map = new HashMap<>();
        List<Patient> list = patientService.list();
        Map<String,Long> deptCount = list.stream()
                .collect(Collectors.groupingBy(Patient::getDept,Collectors.counting()));
        map.put("code",200);
        map.put("data",deptCount);
        return map;
    }
}