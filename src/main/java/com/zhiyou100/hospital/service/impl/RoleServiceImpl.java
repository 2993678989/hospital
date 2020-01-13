package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.RoleMapper;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.pojo.Role;
import com.zhiyou100.hospital.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author:li
 * @Date:2019/12/1 20:35
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Resource
    private RoleMapper roleMapper;


    @Override
    public IPage<Role> queryPage(Page<Role> page, QueryWrapper<Role> wrapper) {
        return roleMapper.queryAllPage(page,wrapper);
    }

    @Override
    public List<Role> queryAll() {
        return null;
    }

    @Override
    public Role queryById(Integer id) {
        return roleMapper.queryById(id);
    }

    @Override
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void delete(Integer id) {
        roleMapper.deleteById(id);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateById(role);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.selectList(null);
    }

    @Override
    public Role findByName(String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return roleMapper.selectOne(queryWrapper);
    }
}
