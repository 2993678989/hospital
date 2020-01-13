package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.RolePowerMapper;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.pojo.RolePower;
import com.zhiyou100.hospital.service.IRolePowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:40
 */
@Service
public class RolePowerServiceImpl implements IRolePowerService {
    @Resource
    private RolePowerMapper rolePowerMapper;


    @Override
    public IPage<RolePower> queryPage(Page<RolePower> page, QueryWrapper<RolePower> wrapper) {
        return null;
    }

    @Override
    public List<RolePower> queryAll() {
        return null;
    }

    @Override
    public RolePower queryById(Integer id) {
        return null;
    }

    @Override
    public void add(RolePower rolePower) {
        rolePowerMapper.insert(rolePower);
    }

    @Override
    public void delete(Integer id) {
        rolePowerMapper.deleteById(id);
    }

    @Override
    public void update(RolePower rolePower) {
        rolePowerMapper.updateById(rolePower);
    }

    @Override
    public void deleteByWrapper(QueryWrapper<RolePower> queryWrapper) {
        rolePowerMapper.delete(queryWrapper);
    }
}
