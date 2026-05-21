package com.glucose.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.glucose.entity.FollowExecute;
import com.glucose.mapper.FollowExecuteMapper;
import com.glucose.service.FollowExecuteService;
import org.springframework.stereotype.Service;

@Service
public class FollowExecuteServiceImpl extends ServiceImpl<FollowExecuteMapper, FollowExecute>
        implements FollowExecuteService {
}