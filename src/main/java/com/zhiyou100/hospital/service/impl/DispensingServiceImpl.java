package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.DispensingMapper;
import com.zhiyou100.hospital.pojo.Dispensing;
import com.zhiyou100.hospital.pojo.Registration;
import com.zhiyou100.hospital.service.IDispensingService;
import com.zhiyou100.hospital.service.IRegistrationService;
import com.zhiyou100.hospital.util.MyDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:34
 */
@Service
public class DispensingServiceImpl implements IDispensingService {
    @Resource
    private DispensingMapper dispensingMapper;
    @Resource
    private IRegistrationService registrationService;

    @Override
    public IPage<Dispensing> queryPage(Page<Dispensing> page, QueryWrapper<Dispensing> wrapper) {
        wrapper.groupBy("d.cases");
        return dispensingMapper.queryAll(page,wrapper);
    }

    @Override
    public List<Dispensing> queryAll() {
        return null;
    }

    @Override
    public Dispensing queryById(Integer id) {
        return null;
    }

    @Override
    public void add(Dispensing dispensing) {
        Dispensing dispensing1 = queryByCasesAndMedicineId(dispensing.getCases(),dispensing.getMedicineId());
        System.out.println(dispensing1);
        if (dispensing1!=null){
            dispensing1.setDispensingNumber(dispensing1.getDispensingNumber()+dispensing.getDispensingNumber());
            dispensing1.setUnissued(dispensing1.getUnissued()+dispensing.getDispensingNumber());
            System.out.println(dispensing1);
            update(dispensing1);
        }else {
            Registration registration = registrationService.queryById(dispensing.getCases());
            System.out.println(registration);
            dispensing.setHead(registration.getDoctor().getName());
            dispensing.setAlready(0);
            dispensing.setUnissued(dispensing.getDispensingNumber());
            dispensing.setDispensingTime(MyDate.date(new Date()));
            System.out.println(dispensing);
            dispensingMapper.insert(dispensing);
        }

    }

    @Override
    public void delete(Integer id) {
        dispensingMapper.deleteById(id);
    }

    @Override
    public void update(Dispensing dispensing) {
        dispensingMapper.updateById(dispensing);
    }
    @Override
    public void updateByWrapper(Dispensing dispensing,QueryWrapper<Dispensing> queryWrapper) {
        dispensingMapper.update(dispensing,queryWrapper);
    }

    @Override
    public List<Dispensing> queryByCases(Wrapper<Dispensing> queryWrapper) {
        return dispensingMapper.queryByCases(queryWrapper);
    }

    @Override
    public IPage<Dispensing> queryPageByCases(Page<Dispensing> page, QueryWrapper<Dispensing> wrapper, Integer cases) {
        return dispensingMapper.queryPageByCases(page,wrapper,cases);
    }

    @Override
    public Dispensing queryByCasesAndMedicineId(Integer cases,Integer medicineId) {
        QueryWrapper<Dispensing> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cases",cases);
        queryWrapper.eq("medicine_id",medicineId);
        return dispensingMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Dispensing> queryByWrapper(QueryWrapper<Dispensing> wrapper) {
        return dispensingMapper.queryByWrapper(wrapper);
    }

    @Override
    public void dispenAll(Dispensing dispensing) {
        dispensingMapper.insert(dispensing);
    }


}
