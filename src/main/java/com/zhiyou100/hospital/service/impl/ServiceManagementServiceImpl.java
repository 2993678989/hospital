package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.ServiceManagementMapper;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.pojo.ServiceManagement;
import com.zhiyou100.hospital.service.IServiceManagementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:36
 */
@Service
public class ServiceManagementServiceImpl implements IServiceManagementService {
    @Resource
    private ServiceManagementMapper serviceManagementMapper;


    @Override
    public IPage<ServiceManagement> queryPage(Page<ServiceManagement> page, QueryWrapper<ServiceManagement> wrapper) {
        return serviceManagementMapper.queryAll(page,wrapper);
    }

    @Override
    public List<ServiceManagement> queryAll() {
        return null;
    }

    @Override
    public ServiceManagement queryById(Integer id) {
        return null;
    }

    @Override
    public void add(ServiceManagement serviceManagement) {
        serviceManagementMapper.insert(serviceManagement);
    }

    @Override
    public void delete(Integer id) {
        serviceManagementMapper.deleteById(id);
    }

    @Override
    public void update(ServiceManagement serviceManagement) {
        serviceManagementMapper.updateById(serviceManagement);
    }

    @Override
    public List<ServiceManagement> queryByCases(Wrapper<ServiceManagement> queryWrapper) {
        return serviceManagementMapper.queryByCases(queryWrapper);
    }
}
