package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhiyou100.hospital.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:32
 */
public interface IRoleService extends BaseService<Role> {
    /**
     * 添加用户时使用
     */
    List<Role> findAll();
    Role findByName(String name);

}
