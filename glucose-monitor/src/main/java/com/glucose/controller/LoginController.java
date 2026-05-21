package com.glucose.controller;

import com.glucose.entity.SysUser;
import com.glucose.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/login")
    public Map<String, Object> login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session
    ) {
        SysUser user = userService.login(username, password);
        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            map.put("code", 500);
            map.put("msg", "用户名或密码错误");
        } else {
            session.setAttribute("loginUser", user); // 存入会话
            map.put("code", 200);
            map.put("name", user.getName()); // 存患者姓名
            map.put("role", user.getRole());
        }
        return map;
    }

    @GetMapping("/api/current")
    public SysUser getCurrentUser(HttpSession session) {
        return (SysUser) session.getAttribute("loginUser");
    }
}