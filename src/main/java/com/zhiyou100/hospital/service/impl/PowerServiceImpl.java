package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.PowerMapper;
import com.zhiyou100.hospital.pojo.Power;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IPowerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:35
 */
@Service
public class PowerServiceImpl implements IPowerService {
    @Resource
    private PowerMapper powerMapper;


    @Override
    public IPage<Power> queryPage(Page<Power> page, QueryWrapper<Power> wrapper) {
        return null;
    }

    @Override
    public List<Power> queryAll() {
        return powerMapper.selectList(null);
    }

    @Override
    public Power queryById(Integer id) {
        return null;
    }

    @Override
    public void add(Power power) {
        powerMapper.insert(power);
    }

    @Override
    public void delete(Integer id) {
        powerMapper.deleteById(id);
    }

    @Override
    public void update(Power power) {
        powerMapper.updateById(power);
    }
}
