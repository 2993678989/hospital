package com.zhiyou100.hospital.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhiyou100.hospital.pojo.ServiceManagement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/11/30 17:44
 */
public interface ServiceManagementMapper extends BaseMapper<ServiceManagement> {
    /**
     * 分页查询全部数据,表关联
     * @param page 分页条件
     * @param queryWrapper 数据查询条件
     * @return 查询结果为IPage格式可以通过getRecords方法拿到值,其中还有其他分页属性
     */
    IPage<ServiceManagement> queryAll(IPage<ServiceManagement> page, @Param(Constants.WRAPPER) Wrapper<ServiceManagement> queryWrapper);

    /**
     * 不分页的查询全部数据,表关联
     */
    List<ServiceManagement> queryByCases(@Param(Constants.WRAPPER) Wrapper<ServiceManagement> queryWrapper);
}
