package com.glucose.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glucose.entity.Blood;
import com.glucose.mapper.BloodMapper;
import org.springframework.stereotype.Service;

@Service
public class BloodService extends ServiceImpl<BloodMapper, Blood> {
}