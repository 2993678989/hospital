package com.zhiyou100.hospital.service;

import com.zhiyou100.hospital.pojo.Department;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:31
 */
public interface IDepartmentService extends BaseService<Department>{
    List<Department> selectList();
    List<Department> likeByName(String name);
}
