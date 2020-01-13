package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.ServiceMapper;
import com.zhiyou100.hospital.pojo.MyService;
import com.zhiyou100.hospital.service.IMyServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:36
 */
@Service
public class MyServiceServiceImpl implements IMyServiceService {
    @Resource
    private ServiceMapper serviceMapper;


    @Override
    public IPage<MyService> queryPage(Page<MyService> page, QueryWrapper<MyService> wrapper) {
        return null;
    }

    @Override
    public List<MyService> queryAll() {
        return serviceMapper.selectList(null);
    }

    @Override
    public MyService queryById(Integer id) {
        return null;
    }

    @Override
    public void add(MyService myService) {
        serviceMapper.insert(myService);
    }

    @Override
    public void delete(Integer id) {
        serviceMapper.deleteById(id);
    }

    @Override
    public void update(MyService myService) {
        serviceMapper.updateById(myService);
    }

    @Override
    public MyService findByName(String name) {
        QueryWrapper<MyService> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return serviceMapper.selectOne(queryWrapper);
    }
}
