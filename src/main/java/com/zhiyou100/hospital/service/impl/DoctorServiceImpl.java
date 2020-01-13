package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.DoctorMapper;
import com.zhiyou100.hospital.pojo.Doctor;
import com.zhiyou100.hospital.service.IDoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:35
 */
@Service
public class DoctorServiceImpl implements IDoctorService {
    @Resource
    private DoctorMapper doctorMapper;


    @Override
    public IPage<Doctor> queryPage(Page<Doctor> page, QueryWrapper<Doctor> wrapper) {
        return doctorMapper.queryAll(page,wrapper);
    }

    @Override
    public List<Doctor> queryAll() {
        return doctorMapper.selectList(null);
    }

    @Override
    public Doctor queryById(Integer id) {
        return doctorMapper.queryById(id);
    }

    @Override
    public void add(Doctor doctor) {
        doctorMapper.insert(doctor);
    }

    @Override
    public void delete(Integer id) {
        doctorMapper.deleteById(id);
    }

    @Override
    public void update(Doctor doctor) {
        doctorMapper.updateById(doctor);
    }

    @Override
    public List<Doctor> selectByDepartmentId(Integer departmentId) {
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("department_id",departmentId);
        return doctorMapper.selectList(queryWrapper);
    }

    @Override
    public List<Doctor> likeByName(String name) {
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return doctorMapper.selectList(queryWrapper);
    }
}
