package com.zhiyou100.hospital.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhiyou100.hospital.pojo.Dispensing;
import com.zhiyou100.hospital.pojo.Dispensing;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/11/30 17:44
 */
public interface DispensingMapper extends BaseMapper<Dispensing> {
    /**
     * 分页查询全部数据,表关联
     * @param page 分页条件
     * @param queryWrapper 数据查询条件
     * @return 查询结果为IPage格式可以通过getRecords方法拿到值,其中还有其他分页属性
     */
    IPage<Dispensing> queryAll(IPage<Dispensing> page, @Param(Constants.WRAPPER) Wrapper<Dispensing> queryWrapper);

    /**
     * 用于查询单条数据
     * @param page 分页条件
     * @param queryWrapper 数据查询条件是根据病历号精准查询
     * @return 精确查询的为单个返还值
     */
    IPage<Dispensing> queryPageByCases(IPage<Dispensing> page, @Param(Constants.WRAPPER) Wrapper<Dispensing> queryWrapper,@Param("cases")Integer cases);

    /**
     *
     * @param wrapper 查询条件,根据病历号查询
     * @return 查询结果为每个病历号的最后一次发药数据
     */
    List<Dispensing> queryByWrapper(@Param(Constants.WRAPPER) Wrapper<Dispensing> wrapper);

    List<Dispensing> queryByCases(@Param(Constants.WRAPPER) Wrapper<Dispensing> queryWrapper);



}
