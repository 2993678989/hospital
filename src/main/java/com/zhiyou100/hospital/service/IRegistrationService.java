package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.Registration;

/**
 * @Author:li
 * @Date:2019/12/1 16:32
 */
public interface IRegistrationService extends BaseService<Registration>{
    Registration findByCases(Integer cases);
    Registration queryByWrapper(QueryWrapper<Registration> wrapper);
}
