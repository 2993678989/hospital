package com.zhiyou100.hospital.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhiyou100.hospital.pojo.Registration;
import org.apache.ibatis.annotations.Param;


/**
 * @Author:li
 * @Date:2019/11/30 17:44
 */
public interface RegistrationMapper extends BaseMapper<Registration> {
    /**
     * 分页查询全部数据,表关联
     * @param page 分页条件
     * @param queryWrapper 数据查询条件
     * @return 查询结果为IPage格式可以通过getRecords方法拿到值,其中还有其他分页属性
     */
    IPage<Registration> queryAll(IPage<Registration> page, @Param(Constants.WRAPPER) Wrapper<Registration> queryWrapper);

    /**
     * 用于查询单条数据
     * @param id 查询条件的id
     * @return 精确查询的为单个返还值
     */
    Registration queryById(Integer id);
}
