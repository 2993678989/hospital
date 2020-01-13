package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.RegistrationMapper;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IRegistrationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 16:34
 */
@Service
public class RegistrationServiceImpl implements IRegistrationService {
    @Resource
    private RegistrationMapper registrationMapper;

    @Override
    public IPage<Registration> queryPage(Page<Registration> page, QueryWrapper<Registration> wrapper) {
        return registrationMapper.queryAll(page, wrapper);
    }

    @Override
    public List<Registration> queryAll() {
        return registrationMapper.selectList(null);
    }

    @Override
    public Registration queryById(Integer id) {
        return registrationMapper.queryById(id);
    }

    @Override
    public void add(Registration registration) {
        registrationMapper.insert(registration);
    }

    @Override
    public void delete(Integer id) {
        registrationMapper.deleteById(id);
    }

    @Override
    public void update(Registration registration) {
        registrationMapper.updateById(registration);

    }

    @Override
    public Registration findByCases(Integer cases) {
        return registrationMapper.selectById(cases);
    }

    @Override
    public Registration queryByWrapper(QueryWrapper<Registration> wrapper) {
        return registrationMapper.selectOne(wrapper);
    }
}
