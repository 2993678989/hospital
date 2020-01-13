package com.zhiyou100.hospital.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.pojo.User;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 14:50
 */

public interface IUserService {
//    Integer login(String username, String password);
    User queryAll(String username, String password);
    User findByName(String username);
    IPage<User> findByLike(Page<User> page, QueryWrapper<User> wrapper);
    void add(User user);
    void delete(Integer id);
    void update(User user);
    User findById(Integer id);
}
