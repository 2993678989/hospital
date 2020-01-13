package com.zhiyou100.hospital.service;

import com.zhiyou100.hospital.pojo.Doctor;

import java.util.List;

/**
 * @Author:li
 * @Date:2019/12/1 20:31
 */
public interface IDoctorService extends BaseService<Doctor>{
    List<Doctor> selectByDepartmentId(Integer departmentId);
    List<Doctor> likeByName(String name);
}
