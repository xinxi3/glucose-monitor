package com.glucose.service;

import com.glucose.entity.SysUser;
import com.glucose.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public SysUser login(String username, String password) {
        SysUser user = userMapper.selectByUsername(username);
        if (user == null) return null;
        if (!user.getPassword().equals(password)) return null;
        return user;
    }
    // ================== 我加的：根据ID查用户（给患者页用） ==================
    public SysUser getById(Long id) {
        return userMapper.selectById(id);
    }
}