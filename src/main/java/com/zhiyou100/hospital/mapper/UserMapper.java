package com.zhiyou100.hospital.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/11/30 17:44
 */
public interface UserMapper extends BaseMapper<User> {
    User queryAll(@Param("account") String account,@Param("password") String password);
    IPage<User> findByLike(Page<User> page, @Param("ew") QueryWrapper<User> wrapper);
    User findById(Integer id);
}
