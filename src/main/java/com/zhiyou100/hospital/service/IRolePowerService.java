package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhiyou100.hospital.pojo.RolePower;

/**
 * @Author:li
 * @Date:2019/12/1 20:40
 */
public interface IRolePowerService extends BaseService<RolePower> {
    void deleteByWrapper(QueryWrapper<RolePower> queryWrapper);
}
