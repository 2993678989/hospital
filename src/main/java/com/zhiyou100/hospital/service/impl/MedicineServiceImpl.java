package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.MedicineMapper;
import com.zhiyou100.hospital.pojo.Medicine;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IMedicineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:35
 */
@Service
public class MedicineServiceImpl implements IMedicineService {
    @Resource
    private MedicineMapper medicineMapper;


    @Override
    public IPage<Medicine> queryPage(Page<Medicine> page, QueryWrapper<Medicine> wrapper) {
        return medicineMapper.selectPage(page,wrapper);
    }

    @Override
    public List<Medicine> queryAll() {
        return medicineMapper.selectList(null);
    }

    @Override
    public Medicine queryById(Integer id) {
        return medicineMapper.selectById(id);
    }

    @Override
    public void add(Medicine medicine) {
        medicineMapper.insert(medicine);
    }

    @Override
    public void delete(Integer id) {
        medicineMapper.deleteById(id);
    }

    @Override
    public void update(Medicine medicine) {
        medicineMapper.updateById(medicine);
    }
}
