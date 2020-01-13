package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Dispensing;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:31
 */
public interface IDispensingService extends BaseService<Dispensing>{
    IPage<Dispensing> queryPageByCases(Page<Dispensing> page, QueryWrapper<Dispensing> wrapper, Integer cases);
    /**
     *
     * @param cases 病历号
     * @return 通过病历号查询单条数据
     */
    Dispensing queryByCasesAndMedicineId(Integer cases,Integer medicineId);
    List<Dispensing> queryByWrapper(QueryWrapper<Dispensing> wrapper);

    /**
     * 单个病例号的单个药品全部发药
     */
    void dispenAll(Dispensing dispensing);

    /**
     *
     * @param dispensing 修改的内容
     * @param queryWrapper 修改哪条数据的条件
     */
    void updateByWrapper(Dispensing dispensing,QueryWrapper<Dispensing> queryWrapper);

    List<Dispensing> queryByCases(Wrapper<Dispensing> queryWrapper);
}
