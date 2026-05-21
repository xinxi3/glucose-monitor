package com.glucose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glucose.entity.Patient;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}