package com.zhiyou100.hospital.service.impl;

import com.zhiyou100.hospital.mapper.MedicineEchartsMapper;
import com.zhiyou100.hospital.pojo.MedicineEcharts;
import com.zhiyou100.hospital.service.IMedicineEchartsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:WANGXIN
 * @Date:2020/1/12 14:21
 */
@Service
public class IMedicineEchartsServiceImpl implements IMedicineEchartsService {
    @Resource
    private MedicineEchartsMapper medicineEchartsMapper;
    @Override
    public List<MedicineEcharts> queryAll() {
        return medicineEchartsMapper.queryAll();
    }
}
