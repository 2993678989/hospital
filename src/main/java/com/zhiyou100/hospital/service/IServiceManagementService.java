package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zhiyou100.hospital.pojo.ServiceManagement;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:33
 */
public interface IServiceManagementService  extends BaseService<ServiceManagement>{
    List<ServiceManagement> queryByCases(Wrapper<ServiceManagement> queryWrapper);
}
