package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.HospitalizationMapper;
import com.zhiyou100.hospital.pojo.Hospitalization;
import com.zhiyou100.hospital.service.IHospitalizationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:35
 */
@Service
public class HospitalizationServiceImpl implements IHospitalizationService {
    @Resource
    private HospitalizationMapper hospitalizationMapper;


    @Override
    public IPage<Hospitalization> queryPage(Page<Hospitalization> page, QueryWrapper<Hospitalization> wrapper) {
        return hospitalizationMapper.queryAll(page,wrapper);
    }

    @Override
    public List<Hospitalization> queryAll() {
        return null;
    }

    @Override
    public Hospitalization queryById(Integer id) {
        return hospitalizationMapper.queryById(id);
    }

    @Override
    public void add(Hospitalization hospitalization) {
        hospitalizationMapper.insert(hospitalization);
    }

    @Override
    public void delete(Integer id) {
        hospitalizationMapper.deleteById(id);
    }

    @Override
    public void update(Hospitalization hospitalization) {
        hospitalizationMapper.updateById(hospitalization);
    }

    @Override
    public Hospitalization queryByWrapper(QueryWrapper<Hospitalization> wrapper) {
        return hospitalizationMapper.selectOne(wrapper);
    }
}
