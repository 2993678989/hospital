package com.zhiyou100.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiyou100.hospital.mapper.UserMapper;
import com.zhiyou100.hospital.pojo.User;
import com.zhiyou100.hospital.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 14:57
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
//    @Override
//    public Integer login(String username, String password) {
//        QueryWrapper<User> qw =new QueryWrapper<>();
//        qw.eq("account",username);
//        List<User> users = userMapper.selectList(qw);
//        if (users.size()<1){
//            //返还0代表没有该账号
//            return 0;
//        }
//        for (User u :users
//             ) {
//            if(u.getPassword().equals(password)){
//                //返还2代表有且密码正确
//                return 2;
//            }
//        }
//        //返还1代表有但密码错误
//        return 1;
//    }

    @Override
    public User queryAll(String username, String password) {
        return userMapper.queryAll(username,password);
    }

    @Override
    public User findByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account",username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public IPage<User> findByLike(Page<User> page, QueryWrapper<User> wrapper) {
        return userMapper.findByLike(page,wrapper);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
