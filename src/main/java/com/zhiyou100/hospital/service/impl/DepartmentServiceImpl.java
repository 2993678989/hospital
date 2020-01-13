package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.DepartmentMapper;
import com.zhiyou100.hospital.pojo.Department;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IDepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:34
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;


    @Override
    public IPage<Department> queryPage(Page<Department> page, QueryWrapper<Department> wrapper) {
        return null;
    }

    @Override
    public List<Department> queryAll() {
        return null;
    }

    @Override
    public Department queryById(Integer id) {
        return null;
    }

    @Override
    public void add(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void delete(Integer id) {
        departmentMapper.deleteById(id);
    }

    @Override
    public void update(Department department) {
        departmentMapper.updateById(department);
    }

    @Override
    public List<Department> selectList() {
        return departmentMapper.selectList(null);
    }

    @Override
    public List<Department> likeByName(String name) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name",name);
        return departmentMapper.selectList(queryWrapper);
    }
}
